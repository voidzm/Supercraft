#!/bin/bash
echo ---------------- Building ----------------
echo ------------ Supercraft 0.3.0 ------------
cp -p -R ../forge/mcp/src ../forge/mcp/src-backup
echo Finished backing up forge sources.
cp -p -R source ../forge/mcp/src/minecraft
echo Finished copying Supercraft sources to build path.
cd ../forge/mcp
./recompile.sh
./reobfuscate.sh
echo Source compiled and obfuscated.
cd ../../supercraft
cp -p -R resources/com/voidzm/supercraft/img ../forge/mcp/reobf/minecraft/com/voidzm/supercraft/img
cp mcmod.info ../forge/mcp/reobf/minecraft/mcmod.info
echo Added resources and assets to build target.
rm -rf ../forge/mcp/src
cd ../forge/mcp
mv src-backup src
echo ----- Build complete! Built target is located at forge\mcp\reobf\minecraft. -----
read -p "Press any key to exit..." -n1 -s
