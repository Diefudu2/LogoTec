@echo off
REM Script para construir logotec-vm.jar (VM standalone)
REM Ejecuta este script desde la raíz del proyecto

REM Cambiar al directorio donde está el script
cd /d "%~dp0"

echo ================================================
echo   Compilando LogoTec Virtual Machine
echo ================================================
echo.
echo Directorio actual: %CD%
echo.

REM Compilar el proyecto con Maven
echo [1/3] Compilando proyecto con Maven...
cd compilador
call mvn clean compile
if %ERRORLEVEL% NEQ 0 (
    echo ERROR: Fallo en la compilacion
    pause
    exit /b 1
)
echo.

REM Crear directorio para el JAR
echo [2/3] Creando JAR de la VM...
if not exist "target\vm-jar" mkdir target\vm-jar

REM Copiar clases compiladas necesarias
xcopy /E /I /Y target\classes\com target\vm-jar\com

REM Crear MANIFEST.MF
echo Main-Class: com.miorganizacion.logotec.vm.VMMain > target\vm-jar\MANIFEST.MF
echo.

REM Crear JAR
cd target\vm-jar
jar cfm ..\..\logotec-vm.jar MANIFEST.MF com
cd ..\..

echo [3/3] Moviendo JAR a la raiz del proyecto...
move /Y logotec-vm.jar ..\logotec-vm.jar

cd ..

echo [4/4] Creando launcher de Windows...
call :CREATE_LAUNCHER

echo.
echo ================================================
echo   EXITO: logotec-vm.jar creado
echo ================================================
echo.
echo Para ejecutar un programa compilado:
echo   java -jar logotec-vm.jar compilador\output\programa.ltbc
echo.
echo O hacer doble clic en el archivo .ltbc
echo.
echo Para ver la ayuda:
echo   java -jar logotec-vm.jar --help
echo.
pause
exit /b 0

:CREATE_LAUNCHER
REM Crear ejecutar-ltbc.bat
(
echo @echo off
echo REM Launcher para archivos .ltbc de LogoTec
echo REM Uso: ejecutar-ltbc.bat archivo.ltbc
echo.
echo if "%%1"=="" ^(
echo     echo ERROR: Debes especificar un archivo .ltbc
echo     echo Uso: ejecutar-ltbc.bat archivo.ltbc
echo     pause
echo     exit /b 1
echo ^)
echo.
echo if not exist "%%1" ^(
echo     echo ERROR: Archivo no encontrado: %%1
echo     pause
echo     exit /b 1
echo ^)
echo.
echo REM Obtener la ruta del script
echo set SCRIPT_DIR=%%~dp0
echo.
echo REM Ejecutar con el VM
echo java -jar "%%SCRIPT_DIR%%logotec-vm.jar" "%%1"
echo.
echo pause
) > ejecutar-ltbc.bat

echo Launcher creado: ejecutar-ltbc.bat
goto :EOF

