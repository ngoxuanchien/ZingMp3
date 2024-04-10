FROM jelastic/maven:3.9.5-openjdk-21

WORKDIR /user-service
COPY . .
RUN mvn clean compile install -DskipTests

CMD mvn spring-boot:run

#FROM openjdk:21
#
#COPY target/song-service-0.0.1-SNAPSHOT.jar app.jar
#
#ENTRYPOINT ["java", "-jar", "/app.jar"]
