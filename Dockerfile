FROM openjdk:8u171-jre-alpine
COPY build/libs/todo-0.0.1-SNAPSHOT.jar app.jar
ENTRYPOINT ["java"]
CMD ["-jar","app.jar"]
