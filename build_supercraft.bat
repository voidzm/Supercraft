@echo off
echo ---------------- Building ----------------
echo ------------ Supercraft 0.4.0 ------------
xcopy ..\..\mcp\src ..\..\mcp\src-backup /E /I /Q
echo Finished backing up forge sources.
xcopy source ..\..\mcp\src\minecraft /E /Q
xcopy ..\novamenu\source ..\..\mcp\src\minecraft /E /Q
echo Finished copying Supercraft sources to build path.
cd ..\..\mcp
call recompile.bat
call reobfuscate_srg.bat
echo Source compiled and obfuscated.
cd ..\projects\supercraft
xcopy resources ..\..\mcp\reobf\minecraft /E /Q
cd ..\novamenu
xcopy resources\mods ..\..\mcp\reobf\minecraft\mods /E /Q
echo Added resources and assets to artifact.
cd ..\..\mcp
rmdir /S /Q src
xcopy src-backup src /E /I /Q
rmdir /S /Q src-backup
cd reobf\minecraft
jar cmf META-INF\MANIFEST.MF Supercraft0.4.0.jar com mods mcmod.info remap.csv supercraft_at.cfg
echo ----- Build complete! Artifact is located at mcp\reobf\minecraft\Supercraft0.4.0.jar -----
pause