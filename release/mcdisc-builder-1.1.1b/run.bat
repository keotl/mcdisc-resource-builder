set VERSION=1.1.1b
del /Q mcdiscPack-*.zip
del /Q build
call prepare.bat
java -jar mcdiscResourcePackBuilder-all-%VERSION%.jar . --disc-list https://raw.githubusercontent.com/KEOTL/mcdisc/master/sample-disc-config.json --pool-size 20 --max-retry 5
del assets\mcdisc\sounds\*.webm
rem md build
rem move /Y assets build\assets
rem move /Y pack.mcmeta build\pack.mcmeta
rem powershell.exe -nologo -noprofile -command "& { Add-Type -A 'System.IO.Compression.FileSystem'; [IO.Compression.ZipFile]::CreateFromDirectory('build', 'mcdiscPack-.zip', [System.IO.Compression.CompressionLevel]::Optimal, $false); }"
7za.exe a mcdiscPack-.zip assets
7za.exe a mcdiscPack-.zip pack.mcmeta
