FROM alpine:3.6
RUN apk --update add openjdk8-jre

COPY start-service.sh ./
COPY configuration-server-0.0.1-SNAPSHOT.jar ./

EXPOSE 9000
ENTRYPOINT ["./start-service.sh"]