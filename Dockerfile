FROM alpine:latest
EXPOSE 8080
COPY ./target/MSTestNeoris-0.0.1-SNAPSHOT.jar MSTestNeoris-0.0.1-SNAPSHOT.jar
RUN apk add openjdk8-jre
RUN apk update
RUN apk add busybox-extras
RUN apk add curl
ENTRYPOINT ["java","-jar","MSTestNeoris-0.0.1-SNAPSHOT.jar"]