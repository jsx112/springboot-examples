#!/bin/bash
#defined
SERVER_NAME="$1"
DEPLOY_DIR="$2"
VERSIN_DIR='/opt/pkg/'

#param validate
if [ $# -lt 1 ] then
  echo "you must use like this : ./publish.sh <projectname>  [ home dir]"
  exit
fi

if [ "$2" != "" ]; then
   DEPLOY_DIR="$2"
fi

warpkg=`ls -lah|grep $VERSIN_DIR|grep war|wc -l`
echo $warpkg
if ["$warpkg" != "0" ]; then
	#shutdown tomcat
	"$DEPLOY_DIR"/bin/shutdown.sh
	echo "tomcat shutdown"
	#check tomcat process
	tomcat_pid=`ps -ef| grep java | grep "$SERVER_NAME" |awk '{print $2}'`
	echo "current :" $tomcat_pid
        for PID in $tomcat_pid; do
        "$DEPLOY_DIR"/bin/shutdown.sh >/dev/null 2>&1
        sleep 3s
        tomcat_pid2=`ps -ef |grep java | grep $SERVER_NAME  | awk '{print $2}' `
        if [ $PID2 != "0" ];then
		kill -9 $PID2 >/dev/null 2>&1
		RETVAL=$?
	  else
		RETVAL=0
	  fi
      sleep 1
      echo -e "\033[32mOK:$SERVER_NAME kill 服务停止完成 \033[0m"
 done
	#bak SERVER_NAME
	BAK_DIR=/opt/bakJAVA/$SERVER_NAME/`date +%Y%m%d`
	mkdir -p "$BAK_DIR"
	mv "$DEPLOY_DIR"/webapps/$SERVER_NAME.war "$BAK_DIR"/"$SERVER_NAME"_`date +%H%M%S`.war
	#publish SERVER_NAME
	echo "scan no tomcat pid,$SERVER_NAME publishing"
	mv /$VERSIN_DIR/$SERVER_NAME*.war "$DEPLOY_DIR"/webapps/$SERVER_NAME.war
	#start tomcat
	"$DEPLOY_DIR"/bin/startup.sh
    sleep 5

    tomcat_pid3=`ps aux | grep java | grep "$SERVER_NAME" |awk '{print $2}'`
    if [ ! -n "$tomcat_pid3" ]; then
    echo -e "\033[31mERROR: hhly-${SERVER_NAME}-tomcat 发布后启动失败 \033[0m"
    echo "hhly-${SERVER_NAME}-tomcat 自动发布失败FAIL $DATA_TAG"
    exit 1
    else
    echo -e "\033[32mSUCCESS:  $SERVER_NAME 发布完成\033[0m"
    echo "hhly-${SERVER_NAME}-tomcat 自动发布SUCCESS:$DATA_TAG"
	echo "tomcat is starting,please try to access $SERVER_NAME conslone url"
    fi
else
    mv $DEPLOY_DIR$SERVER_NAME  $BAK_DIR$SERVER_NAME_$DATA_TAG
	mv $DEPLOY_DIR$SERVER_NAME.zip $BAK_DIR$SERVER_NAME.zip_$DATA_TAG
    mv $VERSIN_DIR$SERVER_NAME.zip  $DEPLOY_DIR
    cd $DEPLOY_DIR
    unzip $SERVER_NAME.zip
    cd $BIN_DIR
	dos2unix *.properties *.conf *.xml
    chmod +x $SERVER_NAME.jar
    ./$SERVER_NAME.jar start > /dev/null 2>&1
	sleep 5

    ZPID=`ps -ef | grep java | grep "$SERVER_NAME" |awk '{print $2}'`
    if [ ! -n "$ZPID" ]; then
    echo -e "\\033[1mERROR: The $SERVER_NAME  started fail \033[0m"
	exit 1
    else
    echo -e "\\033[1mOK: The $SERVER_NAME  started ok\033[0m"
	echo -e "\\033[0mBye"

fi
