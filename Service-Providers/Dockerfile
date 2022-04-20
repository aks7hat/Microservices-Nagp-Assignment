FROM java:8-jdk
RUN mkdir /app
WORKDIR /app
COPY target/Service-Providers-0.0.1-SNAPSHOT.jar /app
EXPOSE 8088
CMD ["--spring.profiles.active=gcp"]
ENTRYPOINT ["java", "-jar", "Service-Providers-0.0.1-SNAPSHOT.jar"]
