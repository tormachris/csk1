@echo off
break off

REM prepare the output dir for the classes
if exist bin (rmdir /s /q bin)
mkdir bin

REM and jar file
if exist KillerSokoban.jar (del KillerSokoban.jar)

REM compile classes, and build jar file
javac -verbose -d .\bin @options.txt @files.txt
jar cvfm KillerSokoban.jar src\MANIFEST.MF -C .\bin .

echo Building done!

