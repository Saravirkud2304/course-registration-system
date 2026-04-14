@echo off
echo Compiling Course Registration System...
echo.

REM Create lib directory if it doesn't exist
if not exist lib mkdir lib

REM Download MySQL JDBC connector (you need to download this manually)
echo Please download MySQL Connector/J from: https://dev.mysql.com/downloads/connector/j/
echo Save it as mysql-connector-java-8.0.33.jar in the lib directory
echo.

REM Set classpath
set CLASSPATH=lib\mysql-connector-java-8.0.33.jar;src\main\java

REM Compile all Java files
echo Compiling Java files...
javac -cp %CLASSPATH% -d bin src\main\java\com\course\*.java src\main\java\com\course\*.java src\main\java\com\course\*.java src\main\java\com\course\*.java src\main\java\com\course\*.java

if %ERRORLEVEL% EQU 0 (
    echo Compilation successful!
    echo.
    echo Running application...
    java -cp bin;lib\mysql-connector-java-8.0.33.jar com.course.MainApp
) else (
    echo Compilation failed!
)

pause
