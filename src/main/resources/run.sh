#!/bin/sh
rm mcdiscPack-*.zip
java -jar yt-all-1.0.jar .
zip -r mcdiscPack-$(date +%d-%m-%Y).zip assets pack.mcmeta
