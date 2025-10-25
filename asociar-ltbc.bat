@echo off
REM Script para asociar archivos .ltbc con el launcher de LogoTec
REM DEBE EJECUTARSE COMO ADMINISTRADOR

echo.
echo ================================================
echo   Asociando archivos .ltbc con LogoTec VM
echo ================================================
echo.
echo IMPORTANTE: Este script debe ejecutarse como ADMINISTRADOR
echo.
pause

REM Cambiar al directorio donde está el script
cd /d "%~dp0"
echo Directorio actual: %CD%
echo.

REM Verificar que existe logotec-vm.jar
echo Verificando logotec-vm.jar...
if not exist "logotec-vm.jar" (
    echo.
    echo ERROR: No se encuentra logotec-vm.jar
    echo Por favor ejecuta build-vm.bat primero
    echo.
    pause
    exit /b 1
)
echo OK: logotec-vm.jar encontrado
echo.

REM Obtener ruta completa
set SCRIPT_DIR=%~dp0
set JAR_PATH=%SCRIPT_DIR%logotec-vm.jar

echo Ruta del JAR: %JAR_PATH%
echo.

REM Buscar la ruta de Java
echo [0/5] Buscando Java...
for /f "tokens=*" %%i in ('where javaw.exe 2^>nul') do set JAVA_PATH=%%i

if "%JAVA_PATH%"=="" (
    echo ADVERTENCIA: No se encontro javaw.exe en el PATH
    echo Usando 'javaw.exe' por defecto
    set JAVA_PATH=javaw.exe
) else (
    echo Java encontrado: %JAVA_PATH%
)
echo.

REM Crear tipo de archivo .ltbc
echo [1/5] Registrando extension .ltbc...
reg add "HKEY_CLASSES_ROOT\.ltbc" /ve /d "LogoTecBytecode" /f
reg add "HKEY_CLASSES_ROOT\.ltbc" /v "Content Type" /d "application/x-logotec-bytecode" /f

REM Crear clase de archivo
echo [2/5] Creando clase de archivo LogoTecBytecode...
reg add "HKEY_CLASSES_ROOT\LogoTecBytecode" /ve /d "Archivo Bytecode de LogoTec" /f
reg add "HKEY_CLASSES_ROOT\LogoTecBytecode\DefaultIcon" /ve /d "%%SystemRoot%%\System32\imageres.dll,3" /f

REM Crear comando de apertura
echo [3/5] Asociando comando de apertura...
reg add "HKEY_CLASSES_ROOT\LogoTecBytecode\shell\open\command" /ve /d "\"%JAVA_PATH%\" -jar \"%JAR_PATH%\" \"%%1\"" /f

REM Agregar al menú contextual "Ejecutar con LogoTec VM"
echo [4/5] Agregando al menu contextual...
reg add "HKEY_CLASSES_ROOT\LogoTecBytecode\shell\runvm" /ve /d "Ejecutar con LogoTec VM" /f
reg add "HKEY_CLASSES_ROOT\LogoTecBytecode\shell\runvm\command" /ve /d "\"%JAVA_PATH%\" -jar \"%JAR_PATH%\" \"%%1\"" /f

REM Refrescar el explorador
echo [5/5] Refrescando asociaciones del explorador...
assoc .ltbc=LogoTecBytecode
if %ERRORLEVEL% NEQ 0 (
    echo ERROR: Fallo al asociar extension
    pause
    exit /b 1
)

ftype LogoTecBytecode="%JAVA_PATH%" -jar "%JAR_PATH%" "%%1"
if %ERRORLEVEL% NEQ 0 (
    echo ERROR: Fallo al configurar tipo de archivo
    pause
    exit /b 1
)

echo.
echo ================================================
echo   ASOCIACION COMPLETADA
echo ================================================
echo.
echo Ahora puedes:
echo   - Hacer doble clic en archivos .ltbc para ejecutarlos
echo   - Click derecho ^> "Ejecutar con LogoTec VM"
echo.
echo NOTA: Cierra y reabre el Explorador de Windows
echo       para que los cambios surtan efecto
echo.
echo Comando configurado:
echo   "%JAVA_PATH%" -jar "%JAR_PATH%" [archivo.ltbc]
echo.
pause
