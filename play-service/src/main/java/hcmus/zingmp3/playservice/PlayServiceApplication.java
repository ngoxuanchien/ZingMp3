package hcmus.zingmp3.playservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class PlayServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(PlayServiceApplication.class, args);
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
