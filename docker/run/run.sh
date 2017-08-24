#!/bin/sh
VERSION=1.1.0
rm mcdiscPack-*.zip
sh prepare.sh
java -jar mcdiscResourcePackBuilder-all-${VERSION}.jar .
./zip -r mcdiscPack-$(date +%d-%m-%Y).zip assets pack.mcmeta
