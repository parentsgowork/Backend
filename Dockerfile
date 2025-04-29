# Base Image
FROM openjdk:17-jdk-slim

# jar 파일 복사
COPY build/libs/*.jar app.jar

# 8080 포트 열기
EXPOSE 8080

# 실행
ENTRYPOINT ["java","-jar","/app.jar"]
