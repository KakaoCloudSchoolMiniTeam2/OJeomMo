#!/usr/bin/env bash

#환경변수 저장 sh 불러오기
source ~/.bash_profile

PROJECT_ROOT="/home/ec2-user/app/step1/OJeomMo" # 루트 경로
BUILD_DIR="/home/ec2-user/app/step2/zip/build/libs"
JAR_NAME="*.jar"

APP_LOG="$PROJECT_ROOT/application.log"
ERROR_LOG="$PROJECT_ROOT/error.log"
DEPLOY_LOG="$PROJECT_ROOT/deploy.log"

TIME_NOW=$(date +%c)

# build 파일 복사
echo "$TIME_NOW > $JAR_NAME 파일 복사" >> $DEPLOY_LOG
# cp $PROJECT_ROOT/build/libs/*.jar $JAR_FILE
# cp $JAR_FILE $PROJECT_ROOT/build/libs/*.jar
cp "$BUILD_DIR/$JAR_NAME" "$PROJECT_ROOT/build/libs/$JAR_NAME"

# jar 파일 실행
echo "$TIME_NOW > $JAR_NAME 파일 실행" >> $DEPLOY_LOG
#nohup java -jar $JAR_FILE > $APP_LOG 2> $ERROR_LOG &
nohup java -jar "$BUILD_DIR/$JAR_NAME" > "$APP_LOG" 2> $ERROR_LOG &

CURRENT_PID=$(pgrep -f $JAR_NAME)
echo "$TIME_NOW > 실행된 프로세스 아이디 $CURRENT_PID 입니다." >> $DEPLOY_LOG