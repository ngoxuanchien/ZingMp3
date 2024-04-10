FROM jelastic/maven:3.9.5-openjdk-21

WORKDIR /discovery-service
COPY . .
RUN mvn clean install -DskipTests

CMD mvn spring-boot:run