#!/bin/sh
WEBAPPS=/usr/share/tomcat5/webapps

STARTTIME=$(date +%s)

service tomcat5 stop

echo "removing old files"
rm -rf "${WEBAPPS}"/echarts*
if [ $? != 0 ]; then
	echo "error removing old files"
	exit 1
fi

echo "copying echarts.war to ${WEBAPPS}/echarts.war"
cp echarts.war "${WEBAPPS}"/echarts.war
if [ $? != 0 ]
then
    echo "fatal: copy to ${WEBAPPS}/echarts.war failed"
fi

echo "Release Finished: $(date)"

service tomcat5 start

echo "Total time (sec): $((`date -d "now" +%s` - $STARTTIME))"
