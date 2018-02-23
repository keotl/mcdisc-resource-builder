import youtube_dl
from youtube_dl.utils import YoutubeDLError
import sys


def download(url: str, destination: str):
    try:
        ydl = youtube_dl.YoutubeDL(
            {'outtmpl': destination, 'quiet': True, 'merge_output_format': "mkv", 'postprocessors': [{
                'key': 'FFmpegExtractAudio',
                'preferredquality': '5',
                'preferredcodec': "vorbis"
            }],
             'ffmpeg_location': _get_ffmpeg_location()})
        ydl.download([url])
    except YoutubeDLError:
        print("Error downloading {}".format(url))

def _get_ffmpeg_location():
    if sys.platform == 'linux' or sys.platform == 'darwin':
        return 'ffmpeg/ffmpeg'
    elif sys.platform == 'win32' or sys.platform == 'cygwin':
        return 'ffmpeg/ffmpeg.exe'
