FROM gradle AS build
COPY --chown=gradle:gradle . /home/gradle/src/producer
WORKDIR /home/gradle/src/producer
RUN gradle build --no-daemon --stacktrace
FROM openjdk
ARG JAR_FILE=build/libs/*.jar
COPY --from=build /home/gradle/src/producer/build/libs/*.jar producer.jar
ENTRYPOINT ["java","-jar","/producer.jar"]