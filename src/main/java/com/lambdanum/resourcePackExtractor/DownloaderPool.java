package com.lambdanum.resourcePackExtractor;

import java.util.ArrayList;
import java.util.List;

public class DownloaderPool implements Runnable {

    private final int poolSize;
    private final String targetDirectory;

    private volatile List<Thread> threads = new ArrayList<>();

    private Thread cleanup = new Thread(this);
    private volatile boolean exit = false;

    public DownloaderPool(int poolSize, String targetDirectory) {
        this.poolSize = poolSize;
        this.targetDirectory = targetDirectory;
        cleanup.start();
    }

    public void queueDownload(Disc disc) {
        while (!canCreateThread()) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        Thread runner = new Thread(new DownloaderThread(disc,targetDirectory));
        addThread(runner);
    }

    synchronized boolean canCreateThread() {
        return threads.size() < poolSize;
    }

    synchronized public void addThread(Thread thread) {
        threads.add(thread);
        thread.start();
    }

    @Override
    public void run() {
        while (!exit) {
            checkForInactiveThreads();
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    synchronized private void checkForInactiveThreads() {
        for (Thread thread : threads) {
            if (!thread.isAlive()) {
                removeThread(thread);
                break;
            }
        }
    }

    synchronized private void removeThread(Thread thread) {
        threads.remove(thread);
    }

    public void join() {

        while(!canCreateThread()) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        exit = true;
    }
}
