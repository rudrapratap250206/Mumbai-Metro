@echo off
setlocal
cd /d "%~dp0"
set "JAVA_HOME=C:\Users\admin\AppData\Roaming\Code\User\globalStorage\pleiades.java-extension-pack-jdk\java\25"
set "PATH=%JAVA_HOME%\bin;%PATH%"
if not exist "%JAVA_HOME%\bin\javac.exe" (
  echo Java 25 was not found at the expected Pleiades path.
  pause
  exit /b 1
)
if exist "src\main\java\com\mumbai\metro\service" rmdir /s /q "src\main\java\com\mumbai\metro\service"
if exist "src\main\java\com\mumbai\metro\dto" rmdir /s /q "src\main\java\com\mumbai\metro\dto"
echo Building existing Mumbai Metro project with Java 25...
call mvn clean package
if errorlevel 1 (
  echo.
  echo BUILD FAILED. Keep this window open and send the final error lines.
  pause
  exit /b 1
)
echo.
echo BUILD SUCCESS. Starting Mumbai Metro...
call mvn spring-boot:run
pause
