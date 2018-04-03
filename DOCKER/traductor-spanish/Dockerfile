FROM alpine:3.6
RUN apk --update add openjdk8-jre

COPY start-service.sh ./
COPY traductor-spanish-0.0.1-SNAPSHOT.jar ./

EXPOSE 8101
ENTRYPOINT ["./start-service.sh"]