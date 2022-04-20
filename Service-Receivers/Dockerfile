FROM java:8-jdk
RUN mkdir /app
WORKDIR /app
COPY target/Service-Receivers-0.0.1-SNAPSHOT.jar /app
EXPOSE 8081
CMD ["--spring.profiles.active=gcp"]
ENTRYPOINT ["java", "-jar", "Service-Receivers-0.0.1-SNAPSHOT.jar"]
