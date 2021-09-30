FROM openjdk:8-jdk-alpine
LABEL maintainer="tataelxsi.co.in"
WORKDIR /myapp
COPY ./target/RMG_Report-0.0.1-SNAPSHOT.jar /myapp/RMG_Report-0.0.1-SNAPSHOT.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","RMG_Report-0.0.1-SNAPSHOT.jar"]