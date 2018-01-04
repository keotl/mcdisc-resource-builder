cd %1
rmdir /s /Q assets

md assets\mcdisc\sounds assets\mcdisc\lang


echo {"pack" : {"pack_format": 3,"description": "mcdisc resource pack"}}> pack.mcmeta
