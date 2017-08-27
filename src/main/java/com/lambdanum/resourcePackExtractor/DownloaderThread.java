package com.lambdanum.resourcePackExtractor;

import com.github.axet.vget.VGet;
import com.github.axet.vget.info.VideoFileInfo;
import com.github.axet.vget.info.VideoInfo;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

public class DownloaderThread implements Runnable {

    private Disc disc;
    private int retryCount = 0;
    private String targetDirectory;
    private final static String AUDIO_CONTENT_TYPE = "audio/webm";

    private final static int MAX_RETRY = 5;

    public DownloaderThread(Disc disc, String targetDirectory) {
        this.disc = disc;
        this.targetDirectory = targetDirectory;
    }

    @Override
    public void run() {
        try {
            VGet v = new VGet(new URL(disc.getUrl()), new File(targetDirectory));
            v.extract();
            setVideoInfoFileTarget(v.getVideo());
            v.download();
            if (!v.getVideo().getInfo().stream().anyMatch(d -> d.getContentType().equals(AUDIO_CONTENT_TYPE))) {
                convertFile(getVideoFilename());
            } else {
                convertFile(getAudioFilename());
            }
            deleteVideoFile();

        } catch (MalformedURLException e) {
            System.out.println("Malformed URL for " + disc.getId() + "(" + disc.getUrl() + ")");
        } catch (RuntimeException e) {
            deleteVideoFile();
            deleteAudioFile();

            System.err.println("download error on " + disc.getUrl() + ". Retrying in 5s.");
            if (retryCount < MAX_RETRY) {
                retryCount++;
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e1) {
                    e1.printStackTrace();
                }

                System.out.println("Retrying " + disc.getId());
                run();
            } else {
                System.err.println("Could not download " + disc.getId());
            }
        }
    }

    private void setVideoInfoFileTarget(VideoInfo videoInfo) {
        for (VideoFileInfo d : videoInfo.getInfo()) {
            if (AUDIO_CONTENT_TYPE.equals(d.getContentType())) {
                d.targetFile = new File(getAudioFilename());
            }
            else {
                d.setTarget(new File(getVideoFilename()));
            }
        }
    }

    private void convertFile(String path) {
        String os = System.getProperty("os.name").toLowerCase();
        try {
            if (os.contains("win")) {
                Runtime.getRuntime().exec("cmd /C " + targetDirectory + "\\convert.bat " + path).waitFor();
            } else if (os.contains("nix") || os.contains("nux") || os.contains("aix")) {
                Process convertProcess = Runtime.getRuntime().exec("bash " + targetDirectory + "/convert.sh " + path);
                redirectThreadStreamToNull(convertProcess.getErrorStream());
                redirectThreadStreamToNull(convertProcess.getInputStream());
                convertProcess.waitFor();
            } else if (os.contains("mac")) {
                Runtime.getRuntime().exec("bash " + targetDirectory + "/convert.mac.sh " + path).waitFor();
            }
        } catch (InterruptedException | IOException e) {
            e.printStackTrace();
        }
    }

    private void redirectThreadStreamToNull(InputStream inputStream) {
        final Thread redirectToNull = new Thread(() -> {
            final InputStream stdout = inputStream;
            try {
                while (stdout.read() != -1);
            } catch (final Exception e) {
                // Don't care
            }
        }, "Output Consumer Thread");
        redirectToNull.setDaemon(true);
        redirectToNull.start();
    }

    private void deleteVideoFile() {
        new File(getVideoFilename()).delete();
    }
    private void deleteAudioFile() {
        new File(getAudioFilename()).delete();
    }

    private String getVideoFilename() {
        return targetDirectory + "/assets/mcdisc/sounds/sound.mcdisc." + disc.getId() + ".mp4";
    }

    private String getAudioFilename() {
        return targetDirectory + "/assets/mcdisc/sounds/sound.mcdisc." + disc.getId() + ".webm";
    }
}
