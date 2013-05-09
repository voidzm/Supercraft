@echo off
echo ---------------- Building ----------------
echo ------------ Supercraft 0.4.0 ------------
xcopy ..\forge\mcp\src ..\forge\mcp\src-backup /E /I /Q
echo Finished backing up forge sources.
xcopy source ..\forge\mcp\src\minecraft /E /Q
echo Finished copying Supercraft sources to build path.
cd ..\forge\mcp
call recompile.bat
call reobfuscate.bat
echo Source compiled and obfuscated.
cd ..\..\supercraft
xcopy resources ..\forge\mcp\reobf\minecraft /E /Q
echo Added resources and assets to artifact.
cd ..\forge\mcp
rmdir /S /Q src
xcopy src-backup src /E /I /Q
rmdir /S /Q src-backup
echo ----- Build complete! Artifact is located at forge\mcp\reobf\minecraft. -----
pause