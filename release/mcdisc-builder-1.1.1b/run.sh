#!/bin/sh
VERSION=1.1.1b
rm mcdiscPack-*.zip
sh prepare.sh
java -jar mcdiscResourcePackBuilder-all-${VERSION}.jar . --disc-list https://raw.githubusercontent.com/KEOTL/mcdisc/master/sample-disc-config.json --pool-size 20 --max-retry 5
./zip -r mcdiscPack-$(date +%d-%m-%Y).zip assets pack.mcmeta
