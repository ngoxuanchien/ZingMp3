FROM jelastic/maven:3.9.5-openjdk-21

WORKDIR /song-service
COPY . .
RUN mvn clean compile install -DskipTests

CMD mvn spring-boot:run
