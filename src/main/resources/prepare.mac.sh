cd "$1";
rm -rf assets;

mkdir -p assets/mcdisc/sounds assets/mcdisc/lang;
echo '{"pack" : {"pack_format": 3,"description": "mcdisc resource pack"}}'> pack.mcmeta

cat > convert.mac.sh << EOF
FILE="\$1";
if [[ \$FILE == *".webm"* ]]; then
ffmpeg/mac/ffmpeg -i "\$FILE" -vn -y "\${FILE%.webm}.ogg";
elif [[ \$FILE == *".mp4"* ]]; then
ffmpeg/mac/ffmpeg -i "\$FILE" -vn -y "\${FILE%.mp4}.ogg";
fi
rm "\$FILE";
EOF
