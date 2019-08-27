FROM docker.phonepe.com:5000/pp-ops-xenial-ojdk8:0.8.3

ARG SENTINEL_VERSION="1.3.0"

WORKDIR /home/sentinel

RUN adduser -S sentinel && \
    apk add --no-cache tzdata && \
    cp /usr/share/zoneinfo/Asia/Kolkata /etc/localtime && \
    echo "Asia/Kolkata" >  /etc/timezone && \
    rm -rf /var/cache/apk/* && \
    sed -i "s/dl-cdn.alpinelinux.org/mirrors.aliyun.com/g" /etc/apk/repositories && \
    wget -qO sentinel-dashboard.jar "https://github.com/alibaba/Sentinel/releases/download/${SENTINEL_VERSION}/sentinel-dashboard-${SENTINEL_VERSION}.jar"

USER sentinel

EXPOSE 7070
EXPOSE 7071

CMD java  -Dproject.name=sentinel -jar "/home/sentinel/sentinel-dashboard.jar"