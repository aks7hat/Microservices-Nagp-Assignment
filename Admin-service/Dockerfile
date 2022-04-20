FROM java:8-jdk
RUN mkdir /app
WORKDIR /app
COPY target/Admin-service-0.0.1-SNAPSHOT.jar /app
EXPOSE 8085
CMD ["--spring.profiles.active=gcp"]
ENTRYPOINT ["java", "-jar", "Admin-service-0.0.1-SNAPSHOT.jar"]
