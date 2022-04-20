FROM java:8-jdk
RUN mkdir /app
WORKDIR /app
COPY target/Notification-service-0.0.1-SNAPSHOT.jar /app
EXPOSE 8092
CMD ["--spring.profiles.active=gcp"]
ENTRYPOINT ["java", "-jar", "Notification-service-0.0.1-SNAPSHOT.jar"]
