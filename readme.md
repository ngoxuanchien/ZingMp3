# ZingMp3 Backend
## 1. Introduction
## 2. Requirements
To run this project, you need to have the following software installed:
- Docker: [Docker Installation Guide](https://docs.docker.com/get-docker/)
- Docker Compose: [Docker Compose Installation Guide](https://docs.docker.com/compose/install/)
- Maven: [Maven Installation Guide](https://maven.apache.org/install.html)
- Java Development Kit (JDK) 21 or higher: [JDK Installation Guide](https://www.oracle.com/java/technologies/javase/jdk21-archive-downloads.html)

## Installation

### 1. Clone the repository
```bash
git clone https://github.com/ngoxuanchien/ZingMp3.git
cd ZingMp3
```

### 2. Build the project
#### 2.1. Init the project
```bash
cd init
docker-compose up -d
cd ..
```

#### 2.2 Build the project
```bash
mvn clean install
```

### 3. Run the project
#### 3.1. Run the discovery service
```bash
cd discovery-service
mvn spring-boot:run
```

#### 3.2. Run the user service
```bash
cd user-service
mvn spring-boot:run
```

#### 3.3. Run the image service
```bash
cd image-service
mvn spring-boot:run
```

#### 3.4. Run the media service
```bash
cd media-service
mvn spring-boot:run
```

#### 3.5. Run the artist service
##### 3.5.1. Run the artist core
```bash
cd artist-service/artist-core
mvn spring-boot:run
```

##### 3.5.2. Run the artist event handler
```bash
cd artist-service/artist-event-handler
mvn spring-boot:run
```

#### 3.6. Run the song service
##### 3.6.1. Run the song core
```bash
cd song-service/song-core
mvn spring-boot:run
```

##### 3.6.2. Run the song event handler
```bash
cd song-service/song-event-handler
mvn spring-boot:run
```

#### 3.7. Run the playlist service
##### 3.7.1. Run the playlist core
```bash
cd playlist-service/playlist-core
mvn spring-boot:run
```

##### 3.7.2. Run the playlist event handler
```bash
cd playlist-service/playlist-event-handler
mvn spring-boot:run
```

#### 3.8. Run the notification service
After running the notification service, you must change the env 'MAIL_USERNAME' and 'MAIL_PASSWORD' in the file 'notification-service/notification-core/src/main/resources/application.properties' to your email and password to send email notifications.
```bash
cd notification-service/notification-core
mvn spring-boot:run
```
### 4. Run the project with docker
```bash
docker-compose up -d
```

### 5. Application URLs
- Discovery Service: http://localhost:8761
- Api Gateway: http://localhost:8081
- Keycloak: http://localhost:8080