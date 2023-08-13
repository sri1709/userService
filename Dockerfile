FROM openjdk:8-jdk-alpine
VOLUME /tmp
EXPOSE 8300
ADD target/*.jar user-service.jar
ENV JAVA_OPTS=""
ENTRYPOINT [ "sh", "-c", "java $JAVA_OPTS -Djava.security.egd=file:/dev/./urandom -jar /user-service.jar" ]
