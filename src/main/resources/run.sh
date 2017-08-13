#!/bin/sh
VERSION=1.0.2
rm mcdiscPack-*.zip
java -jar mcdiscResourcePackBuilder-all-${VERSION}.jar .
zip -r mcdiscPack-$(date +%d-%m-%Y).zip assets pack.mcmeta
