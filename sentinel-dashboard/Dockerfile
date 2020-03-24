FROM openjdk:8-jdk-alpine

ARG SENTINEL_VERSION="1.7.1"

WORKDIR /home/sentinel

ENV JAR_FILE sentinel-dashboard.jar
ADD target/sentinel-dashboard.jar ${JAR_FILE}

EXPOSE 7070
EXPOSE 7071

CMD java -Dserver.port=7070 -Dcsp.sentinel.dashboard.server=localhost:7070 -Dproject.name=sentinel -jar ${JAR_FILE}