@echo off
echo ---------------- Building ----------------
echo ------------ Supercraft 0.1.0 ------------
XCOPY ..\forge\mcp\src ..\forge\mcp\src-backup /E /I /Q
echo Finished backing up forge sources.
XCOPY source ..\forge\mcp\src\minecraft /E /I /Q
echo Finished copying Supercraft sources to build path.
cd ..\forge\mcp
call recompile.bat
call reobfuscate.bat
echo Source compiled and obfuscated.
cd ..\..\supercraft
XCOPY resources\com\voidzm\supercraft\img ..\forge\mcp\reobf\minecraft\com\voidzm\supercraft\img /E /I /Q
echo Added resources and assets to artifact.
RMDIR /S /Q ..\forge\mcp\src
cd ..\forge\mcp
REN src-backup src
echo ----- Build complete! Artifact is located at forge\mcp\reobf. -----
PAUSE