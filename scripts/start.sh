#!/usr/bin/env bash

#환경변수 저장 sh 불러오기
source ~/.bash_profile

PROJECT_ROOT="/home/ec2-user/app/step1/OJeomMo" # 루트 경로
BUILD_DIR="/home/ec2-user/app/step2/zip/build/libs"
JAR_NAME=$BUILD_DIR/*.jar

java -jar JAR_NAME -DB_IP="${DB_IP}" -DB_PORT="${DB_PORT}" -DB_USERNAME="${DB_USERNAME}" -DB_PASSWORD="${DB_PASSWORD}" -DDL_MODE="${DDL_MODE}" -JWT_KEY="${JWT_KEY}"

# 환경변수 잘 넘어왔나 테스트
echo "DB_IP=$DB_IP" > $PROJECT_ROOT/environment_variables.txt
echo "DB_PASSWORD=$DB_PASSWORD" >> $PROJECT_ROOT/environment_variables.txt
echo "DB_PORT=$DB_PORT" >> $PROJECT_ROOT/environment_variables.txt
echo "DB_USERNAME=$DB_USERNAME" >> $PROJECT_ROOT/environment_variables.txt
echo "DDL_MODE=$DDL_MODE" >> $PROJECT_ROOT/environment_variables.txt
echo "JWT_KEY=$JWT_KEY" >> $PROJECT_ROOT/environment_variables.txt
echo "SPRING_PROFILES_ACTIVE=$SPRING_PROFILES_ACTIVE" >> $PROJECT_ROOT/environment_variables.txt



APP_LOG="$PROJECT_ROOT/application.log"
ERROR_LOG="$PROJECT_ROOT/error.log"
DEPLOY_LOG="$PROJECT_ROOT/deploy.log"

TIME_NOW=$(date +%c)

# build 파일 복사
echo "$TIME_NOW > $JAR_NAME 파일 복사" >> $DEPLOY_LOG
# cp $PROJECT_ROOT/build/libs/*.jar $JAR_FILE
# cp $JAR_FILE $PROJECT_ROOT/build/libs/*.jar
cp $BUILD_DIR/*.jar $PROJECT_ROOT/build/libs

# jar 파일 실행
echo "$TIME_NOW > $JAR_NAME 파일 실행" >> $DEPLOY_LOG
#nohup java -jar $JAR_FILE > $APP_LOG 2> $ERROR_LOG &
nohup java -jar $JAR_NAME > "$APP_LOG" 2> $ERROR_LOG &

#CURRENT_PID=$(pgrep -f $JAR_NAME)
CURRENT_PID=$(lsof -i :8080 -t)
echo "$TIME_NOW > 실행된 프로세스 아이디 $CURRENT_PID 입니다." >> $DEPLOY_LOG
