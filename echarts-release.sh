#!/bin/sh
ROOT=/var/www/html
WEBAPPS=/usr/share/tomcat5/webapps/echarts
if [ ! -d ${ROOT}/echarts ]
then
	mkdir ${ROOT}/echarts
	if [ $? != 0 ]; then
		exit 1
	fi
fi

if [ ! -f ${ROOT}/echarts.war ]
then
	echo "fatal: can't find echarts.war in ${ROOT}"
	exit 1
fi
if [ -f ${ROOT}/echarts/echarts.war ]
then
	echo "moving ${ROOT}/echarts/echarts.war to ${ROOT}/echarts.old"
	mv ${ROOT}/echarts/echarts.war ${ROOT}/echarts.old
	if [ $? != 0 ]; then
		echo "fatal: move unsuccessful"
		exit 1
	fi
fi
echo "remove old files? [Y/N]"
while :
do
	read RESPONSE 
	case $RESPONSE in
		Y)
			echo "removing old files"
			rm -rf ${ROOT}/echarts ${WEBAPPS}
			if [ $? != 0 ]; then
				echo "error removing old files"
				exit 1
			fi
			break
			;;
		N)
			exit 0
			;;
		*)
			echo "Please enter Y or N"
	esac
done
echo "mkdir ${ROOT}/echarts"
mkdir "${ROOT}/echarts"
echo "copying ${ROOT}/echarts.war to ${ROOT}/echarts/echarts.war"
cp ${ROOT}/echarts.war ${ROOT}/echarts/echarts.war
if [ $? != 0 ]; then
	echo "fatal: copy unsuccessful"
	exit 1
fi
echo "unzipping ${ROOT}/echarts/echarts.war to ${ROOT}/echarts/"
unzip -qq -d ${ROOT}/echarts ${ROOT}/echarts/echarts.war
if [ $? != 0 ]; then
	echo "error unzipping ${ROOT}/echarts.war to ${ROOT}/echarts/"
	exit 1
fi
echo "mkdir ${WEBAPPS}"
mkdir "${WEBAPPS}"
if [ $? != 0 ]
then
	echo "fatal: can't create ${WEBAPPS}"
	exit 1
fi
echo "unzipping ${ROOT}/echarts/echarts.war to ${WEBAPPS}"
unzip -qq -d ${WEBAPPS} ${ROOT}/echarts/echarts.war
if [ $? != 0 ]
then
	echo "fatal: unzip to ${WEBAPPS} unsuccessful"
	exit 1
fi
