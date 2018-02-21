import youtube_dl


def download(url: str, destination: str):
    ydl = youtube_dl.YoutubeDL({'outtmpl': destination, 'quiet': True, 'postprocessors': [{
        'key': 'FFmpegExtractAudio',
        'preferredquality': '5',
        'preferredcodec': "vorbis"
    }]})
    ydl.download([url])
