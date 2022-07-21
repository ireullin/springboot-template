#!/bin/bash

RUN_ENV=dev
SPRINGBOOTAPP_HOME="/opt/service"
SPRINGBOOTAPP_JAR="$SPRINGBOOTAPP_HOME/springboot-template.jar"

JAVA_OPTIONS="-server -Xss256K -Dfile.encoding=UTF-8 -XX:MaxMetaspaceSize=256M -XX:ActiveProcessorCount=8"

cd $SPRINGBOOTAPP_HOME
$JAVA_HOME/bin/java $JAVA_OPTIONS -jar $SPRINGBOOTAPP_JAR

