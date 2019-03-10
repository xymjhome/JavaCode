PIDFILE="./start$1.pid"
echo $PIDFILE

SHDIR=$(cd "$(dirname "$0")"; pwd)


JVM_OPTS="$JVM_OPTS -XX:+PrintGCDetails"
JVM_OPTS="$JVM_OPTS -XX:+PrintGCDateStamps"
JVM_OPTS="$JVM_OPTS -Xloggc:gc.log"
JVM_OPTS="$JVM_OPTS -XX:+UseGCLogFileRotation"
JVM_OPTS="$JVM_OPTS -XX:NumberOfGCLogFiles=10"
JVM_OPTS="$JVM_OPTS -XX:GCLogFileSize=10M"

JVM_OPTS="$JVM_OPTS -XX:+UseG1GC"
JVM_OPTS="$JVM_OPTS -XX:MaxGCPauseMillis=100"
JVM_OPTS="$JVM_OPTS -XX:G1RSetUpdatingPauseTimePercent=5"

# exec
LOGFILE="nohup.out"
nohup java  -Xmx8G -XX:+UseTLAB -XX:+DisableExplicitGC $JVM_OPTS -cp $SHDIR:../conf/:../lib/* com.jd.si.ressync.deamon.ThreadMonitorConsole "stop" > $LOGFILE &


if [ -f "$PIDFILE" ]
then
    echo "error to stop, force to stop the pid "
    kill $(cat "$PIDFILE")
    rm -f "$PIDFILE"
    echo STOPPED
fi
exit 0
