FROM docker.phonepe.com:5000/pp-ops-xenial-ojdk8:0.8.3

ARG SENTINEL_VERSION="1.7.1"

WORKDIR /home/sentinel

ENV JAR_FILE foxtrot.jar
ADD target/sentinel-dashboard.jar ${JAR_FILE}

EXPOSE 7070
EXPOSE 7071

CMD java -Dserver.port=7070 -Dcsp.sentinel.dashboard.server=localhost:7070 -Dproject.name=sentinel -jar "/home/sentinel/sentinel-dashboard.jar"