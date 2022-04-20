FROM java:8-jdk
RUN mkdir /app
WORKDIR /app
COPY target/Api-Gateway-Service-0.0.1-SNAPSHOT.jar /app
EXPOSE 9000
CMD ["--spring.profiles.active=gcp"]
ENTRYPOINT ["java", "-jar", "Api-Gateway-Service-0.0.1-SNAPSHOT.jar"]
