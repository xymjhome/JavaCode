#!/bin/sh

# user dir
SHDIR=$(cd "$(dirname "$0")"; pwd)
echo current path:$SHDIR
#PROPERFILE="../conf/config.jmq.consumer.json"
# if the server is already running
#PIDFILE="./start.pid"
#if [ -f $PIDFILE ]; then
#    if kill -0 `cat $PIDFILE` > /dev/null 2>&1; then
#        echo server already running as process `cat $PIDFILE`.
#        exit 0
#    fi
#fi


JVM_OPTS="$JVM_OPTS -XX:+PrintGCDetails"
JVM_OPTS="$JVM_OPTS -XX:+PrintGCDateStamps"
JVM_OPTS="$JVM_OPTS -Xloggc:$SHDIR/logs/gc.log"
JVM_OPTS="$JVM_OPTS -XX:+UseGCLogFileRotation"
JVM_OPTS="$JVM_OPTS -XX:NumberOfGCLogFiles=10"
JVM_OPTS="$JVM_OPTS -XX:GCLogFileSize=10M"

JVM_OPTS="$JVM_OPTS -XX:+UseG1GC"
JVM_OPTS="$JVM_OPTS -XX:MaxGCPauseMillis=100"
JVM_OPTS="$JVM_OPTS -XX:G1RSetUpdatingPauseTimePercent=5"

# exec 
LOGFILE="nohup.out"
nohup java  -Xmx8G -XX:+UseTLAB -XX:+DisableExplicitGC $JVM_OPTS -cp $SHDIR:../conf/:../lib/* com.jd.si.ressync.deamon.ThreadMonitor $PROPERFILE > $LOGFILE &


# wirte pid to file
if [ $? -eq 0 ] 
then
    if /bin/echo -n $! > "$PIDFILE"
    then
        sleep 1
        echo STARTED SUCCESS
    else
        echo FAILED TO WRITE PID
        exit 1
    fi
else
    echo SERVER DID NOT START
    exit 1
fi
