FROM alpine:3.6
RUN apk --update add openjdk8-jre

COPY start-service.sh ./
COPY client-traductor-0.0.1-SNAPSHOT.jar ./

EXPOSE 8080
ENTRYPOINT ["./start-service.sh"]