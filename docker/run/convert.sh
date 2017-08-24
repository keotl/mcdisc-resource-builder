FILE="$1";
if [[ $FILE == *".webm"* ]]; then
ffmpeg/linux/ffmpeg -i "$FILE" -vn -y "${FILE%.webm}.ogg";
elif [[ $FILE == *".mp4"* ]]; then
ffmpeg/linux/ffmpeg -i "$FILE" -vn -y "${FILE%.mp4}.ogg";
fi
rm "$FILE";
