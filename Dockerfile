FROM maven:3.6-jdk-11
COPY ./. /
RUN mvn test