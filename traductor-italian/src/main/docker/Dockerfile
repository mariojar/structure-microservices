FROM alpine:3.6
RUN apk --update add openjdk8-jre

COPY start-service.sh ./
COPY traductor-italian-0.0.1-SNAPSHOT.jar ./

EXPOSE 8102
ENTRYPOINT ["./start-service.sh"]