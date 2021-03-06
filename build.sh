#!/bin/bash

OUTPUT_JAR=KillerSokoban.jar

# prepare the output dir for the classes
[ -d "bin" ] && rm -rf bin
mkdir bin

# and jar file
[ -f "$OUTPUT_JAR" ] && rm "$OUTPUT_JAR" # not necessary btw

# compile classes, and build jar file
javac -verbose -d ./bin "@options.txt" "@files.txt" || exit 1
jar cvfm "$OUTPUT_JAR" src/MANIFEST.MF -C ./bin . || exit 2

echo "Building done!"
