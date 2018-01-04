SET FILE=%1

IF not x%FILE:.webm=%==x%FILE% ffmpeg\windows\ffmpeg.exe -i "%FILE%" -vn -y "%FILE:.webm=.ogg%"

IF not x%FILE:.mp4=%==x%FILE% ffmpeg\windows\ffmpeg.exe -i "%FILE%" -vn -y "%FILE:.mp4=.ogg%"

del /Q "%FILE%"


