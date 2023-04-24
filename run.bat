where /q mvn -v
IF ERRORLEVEL 1 (
    ECHO The application is missing. Ensure it is installed and placed in your PATH.
    EXIT /B
) ELSE (
    mvn javafx:run
)
