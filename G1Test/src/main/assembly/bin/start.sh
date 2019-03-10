#!/bin/sh

# user dir
SHDIR=$(cd "$(dirname "$0")"; pwd)
echo current path:$SHDIR

JVM_OPTS="$JVM_OPTS -XX:+PrintGCDetails"
JVM_OPTS="$JVM_OPTS -XX:+PrintGCDateStamps"
JVM_OPTS="$JVM_OPTS -Xloggc:$SHDIR/Logs/gc.log"
JVM_OPTS="$JVM_OPTS -XX:+HeapDumpOnOutOfMemoryError"
JVM_OPTS="$JVM_OPTS -XX:HeapDumpPath=$SHDIR/Logs/heapdump.hprof"
JVM_OPTS="$JVM_OPTS -XX:+UseGCLogFileRotation"
JVM_OPTS="$JVM_OPTS -XX:NumberOfGCLogFiles=10"
JVM_OPTS="$JVM_OPTS -XX:GCLogFileSize=10M"

JVM_OPTS="$JVM_OPTS -XX:+UseG1GC"
JVM_OPTS="$JVM_OPTS -XX:MaxGCPauseMillis=350"
JVM_OPTS="$JVM_OPTS -XX:G1RSetUpdatingPauseTimePercent=5"

JVM_OPTS="$JVM_OPTS -XX:+UseTLAB"
JVM_OPTS="$JVM_OPTS -XX:+DisableExplicitGC"

# exec
LOGFILE="nohup.out"
nohup java  -Xmx256M -Xms256M  $JVM_OPTS -cp $SHDIR:../lib/* com.g1.G1Test 10 $PROPERFILE > $LOGFILE &

# wirte pid to file
if [ $? -eq 0 ]
then
    echo "process: STARTED SUCCESS"
else
    echo "SERVER: DID NOT START"
    exit 1
fi
