import youtube_dl
from youtube_dl.utils import YoutubeDLError
import sys
import os.path


def download(url: str, destination: str) -> int:
    """returns the duration"""
    try:
        if _local_ffmpeg_exists():
            ydl = youtube_dl.YoutubeDL(
                {'outtmpl': destination, 'quiet': True, 'merge_output_format': "mkv", 'postprocessors': [{
                    'key': 'FFmpegExtractAudio',
                    'preferredquality': '5',
                    'preferredcodec': "vorbis"
                }],
                 'ffmpeg_location': _get_local_ffmpeg_location()})
        else:
            ydl = youtube_dl.YoutubeDL(
                {'outtmpl': destination, 'quiet': True, 'merge_output_format': "mkv", 'postprocessors': [{
                    'key': 'FFmpegExtractAudio',
                    'preferredquality': '5',
                    'preferredcodec': "vorbis"
                }]})
        ydl.download([url])
        return ydl.extract_info(url, download=False)["duration"]
    except YoutubeDLError:
        print("Error downloading {}".format(url))


def _local_ffmpeg_exists() -> bool:
    return os.path.exists('ffmpeg')


def _get_local_ffmpeg_location() -> str:
    if sys.platform == 'linux' or sys.platform == 'darwin':
        return 'ffmpeg/ffmpeg'
    elif sys.platform == 'win32' or sys.platform == 'cygwin':
        return 'ffmpeg/ffmpeg.exe'
