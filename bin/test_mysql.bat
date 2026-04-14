@echo off
echo Testing MySQL installation...
mysql --version
if %ERRORLEVEL% EQU 0 (
    echo MySQL is installed and working!
) else (
    echo MySQL is not installed or not in PATH
)
pause
