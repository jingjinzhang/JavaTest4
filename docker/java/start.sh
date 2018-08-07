#!/bin/bash
echo "搜索端口"
while ! nc -z ${MYSQL_IP} ${MYSQL_PORT}; do sleep 3; done

java -jar Exam1.jar