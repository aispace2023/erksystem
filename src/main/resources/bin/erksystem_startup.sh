#!/bin/sh

SERVICE_NAME=ERKSYSTEM

HOME=/home/erksystem
AUTHORIZED_USER_NAME=erksystem
PACKAGE_DIR=$HOME/erksystem

PATH_TO_JAR=$PACKAGE_DIR/lib/erksystem.jar

export HOME UASYS_HOME LD_LIBRARY_PATH

JAVA_CONF=$PACKAGE_DIR/config/
JAVA_OPT=" -Dlogback.configurationFile=$PACKAGE_DIR/config/logback.xml"
#JAVA_OPT="$JAVA_OPT -Dio.netty.leakDetectionLevel=simple -Djdk.nio.maxCachedBufferSize=262144 -Dio.netty.allocator.type=unpooled -Dio.netty.noPreferDirect=true"
JAVA_OPT="$JAVA_OPT -XX:+UseG1GC -XX:G1RSetUpdatingPauseTimePercent=5 -XX:MaxGCPauseMillis=500 -verbosegc  -Xms4G -Xmx4G"
JAVA_OPT="$JAVA_OPT -XX:+UnlockDiagnosticVMOptions -XX:+LogVMOutput"
#JAVA_OPT="$JAVA_OPT -Xlog:gc*:file=$PACKAGE_DIR/logs/gc.log:time:filecount=10,filesize=10M:gc+heap=trace:age*=debug:safepoint:gc+promotion=debug  -XX:LogFile=$PACKAGE_DIR/logs/thd_dmp.log"

if [ "$USER" != "$AUTHORIZED_USER_NAME" ]; then
  echo "Need to be application account($AUTHORIZED_USER_NAME)"
  exit 1
fi

checkfile() {
  if [ ! -e $1 ]; then
    echo "$1" file does not exist.
    exit 2
  fi
}
checkdir() {
  if [ ! -d $1 ]; then
    echo "$1" directory does not exist.
    exit 3
  fi
}

case $1 in
start)
  if [ -f "$PATH_TO_JAR" ]; then
    java $JAVA_OPT $DEBUG -classpath $PATH_TO_JAR com.aispace.erksystem.ErkSystemMain $JAVA_CONF >/dev/null 2>&1 &
    echo "$SERVICE_NAME started ..."
    /usr/bin/logger -p info -t "$0" "$SERVICE_NAME started"
  else
    echo "(ERROR) start fail : $?"
    exit 4
  fi
  ;;
start-foreground)
  if [ -f "$PATH_TO_JAR" ]; then
    java $JAVA_OPT $DEBUG -classpath $PATH_TO_JAR com.aispace.erksystem.ErkSystemMain $JAVA_CONF
    echo "$SERVICE_NAME started ..."
    /usr/bin/logger -p info -t "$0" "$SERVICE_NAME started"
  else
    echo "(ERROR) start fail : $?"
    exit 4
  fi
  ;;
stop)
  PID=$(ps -ef | grep java | grep ErkSystemMain | awk '{print $2}')
  if [ -z $PID ]; then
    echo "$SERVICE_NAME is not running"
  else
    echo "stopping $SERVICE_NAME"
    kill $PID
    sleep 1
    PID=$(ps -ef | grep java | grep ErkSystemMain | awk '{print $2}')
    if [ ! -z $PID ]; then
      echo "kill -9"
      kill -9 $PID
    fi
    echo "$SERVICE_NAME stopped"
    /usr/bin/logger -p info -t "$0" "$SERVICE_NAME stopped"
  fi
  ;;
restart)
  PID=$(ps -ef | grep java | grep ErkSystemMain | awk '{print $2}')
  if [ -z $PID ]; then
    echo "$SERVICE_NAME is not running"
  else
    echo "stopping $SERVICE_NAME"
    kill $PID
    sleep 1
    PID=$(ps -ef | grep java | grep ErkSystemMain | awk '{print $2}')
    if [ ! -z $PID ]; then
      echo "kill -9"
      kill -9 $PID
    fi
    echo "$SERVICE_NAME stopped"
    /usr/bin/logger -p info -t "$0" "$SERVICE_NAME stopped"
  fi

  if [ -f "$PATH_TO_JAR" ]; then
    java $JAVA_OPT $DEBUG -classpath $PATH_TO_JAR com.aispace.erksystem.ErkSystemMain $JAVA_CONF >/dev/null 2>&1 &
    echo "$SERVICE_NAME started ..."
    /usr/bin/logger -p info -t "$0" "$SERVICE_NAME started"
  else
    echo "(ERROR) start fail : $?"
    exit 4
  fi
  ;;
esac
