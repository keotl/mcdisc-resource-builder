import youtube_dl
from youtube_dl.utils import YoutubeDLError


def download(url: str, destination: str):
    try:
        ydl = youtube_dl.YoutubeDL(
            {'outtmpl': destination, 'quiet': True, 'merge_output_format': "mkv", 'postprocessors': [{
                'key': 'FFmpegExtractAudio',
                'preferredquality': '5',
                'preferredcodec': "vorbis"
            }]})
        ydl.download([url])
    except YoutubeDLError:
        print("Error downloading {}".format(url))
