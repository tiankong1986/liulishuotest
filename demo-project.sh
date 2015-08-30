#!/bin/bash
PID=$(ps -ef | grep coins-txn-api-ws | grep -v grep | awk '{ print $2 }')
if [ $1 = "start" ];then
  if [ -z "$PID" ];then
     echo "do start"
     java -jar coins-txn-api-ws*.jar
  else
     echo "Application is starting"
  fi
elif [ $1 = "stop" ];then
  echo "do stop"
  if [ -z "$PID" ];then
     echo "Application is already stopped"
  else
     kill $PID
  fi
elif [ $1 = "restart" ];then
  if [ -z "$PID" ];then
     echo "start soon"
  else
     echo "stop server"
     kill $PID
     echo "start soon"
  fi
  java -jar coins-txn-api-ws*.jar
elif [ $1 = "status" ];then
  if [ -z "$PID" ];then
     echo "Application is already stopped"
  else
     echo "Application is starting"
  fi
else
  echo "please make sure the position variable is right"
fi
