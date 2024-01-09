@echo off

set "JAR_PATH=C:\Users\cabre\Documents\Backend\file_to_database\out\artifacts\file_to_database_jar\file_to_database.jar"
set "LOCATION_PATH=C:\Users\cabre\Documents\Backend\file_to_database\file_to_database\src\main\resources\application.properties"

java -jar "%JAR_PATH%" --spring.config.location="%LOCATION_PATH%"

pause
exit

