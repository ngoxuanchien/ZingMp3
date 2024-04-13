package hcmus.zingmp3.playservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@SpringBootApplication
public class StreamServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(StreamServiceApplication.class, args);
//        try {
//            Path path = Paths.get("../newFolder");
//            Files.createDirectory(path);
//            System.out.println("Directory is created!");
//            System.out.println(path.toAbsolutePath());
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
    }

}
