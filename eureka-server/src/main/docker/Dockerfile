FROM alpine:3.6
RUN apk --update add openjdk8-jre

COPY start-service.sh ./
COPY eureka-server-0.0.1-SNAPSHOT.jar ./

EXPOSE 8761
EXPOSE 8762
EXPOSE 8763
ENTRYPOINT ["./start-service.sh"]