@echo off
REM Launcher simple para archivos .ltbc
REM Si no se pasa argumento, ejecuta programa.ltbc por defecto

REM Cambiar al directorio del script
cd /d "%~dp0"

echo ================================================
echo   LogoTec Virtual Machine Launcher
echo ================================================
echo.

REM Si no hay argumento, usar programa.ltbc por defecto
if "%~1"=="" (
    set ARCHIVO=compilador\output\programa.ltbc
    echo Usando archivo por defecto: programa.ltbc
) else (
    set ARCHIVO=%~1
    echo Archivo especificado: %~1
)

echo.

REM Verificar que el archivo existe
if not exist "%ARCHIVO%" (
    echo ERROR: Archivo no encontrado: %ARCHIVO%
    echo.
    echo OPCIONES:
    echo   1. Compila primero un programa en el IDE para generar programa.ltbc
    echo   2. Arrastra un archivo .ltbc sobre este script
    echo   3. Usa: EJECUTAR_LTBC.bat ruta\al\archivo.ltbc
    echo.
    pause
    exit /b 1
)

echo Archivo a ejecutar: %ARCHIVO%
echo JAR: %CD%\logotec-vm.jar
echo.
echo Ejecutando...
echo.

java -jar "%CD%\logotec-vm.jar" "%ARCHIVO%"

echo.
echo Ejecucion completada.
pause
