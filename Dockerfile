FROM openjdk:8-jdk-alpine

ARG SENTINEL_VERSION="1.3.0"

WORKDIR /home/sentinel

RUN adduser -S sentinel && \
    apk add --no-cache tzdata && \
    cp /usr/share/zoneinfo/Asia/Kolkata /etc/localtime && \
    echo "Asia/Kolkata" >  /etc/timezone && \
    rm -rf /var/cache/apk/* && \
    sed -i "s/dl-cdn.alpinelinux.org/mirrors.aliyun.com/g" /etc/apk/repositories && \
    wget -qO sentinel-dashboard.jar "https://github.com/alibaba/Sentinel/releases/download/1.3.0/sentinel-dashboard-1.3.0.jar"

USER sentinel

EXPOSE 7070

CMD java -Dproject.name=sentinel -Djava.security.egd=file:/dev/./urandom -jar "/home/sentinel/sentinel-dashboard.jar"