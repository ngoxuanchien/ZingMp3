package hcmus.zingmp3;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import hcmus.zingmp3.model.Artist;
import org.springframework.web.client.RestTemplate;

import java.io.File;
import java.io.IOException;
import java.util.HashSet;
import java.util.Scanner;

import static java.lang.Thread.sleep;

public class Main {
    public static String dir;
    public static String server;
    public static void main(String[] args) throws IOException {

        Scanner scanner = new Scanner(System.in);
        System.out.println("Nhập server: ");
        server = scanner.nextLine();

        // Lấy thư mục làm việc hiện tại
        File workingDir = new File(".");
        String absolutePath = workingDir.getAbsolutePath();

        // In ra đường dẫn tuyệt đối của thư mục làm việc hiện tại
        System.out.println("Thư mục làm việc hiện tại: " + absolutePath);
        dir = absolutePath;

        CrawData crawData = new CrawData();
        crawData.crawData("ZWZAC9BF");

//        Gson gson = new GsonBuilder()
//                .registerTypeAdapter(Artist.class, new ArtistTypeAdapter())
//                .create();
//
//        RestTemplate restTemplate = new RestTemplate();
//        String response = restTemplate.getForObject("http://localhost:3000/test/getArtist/Son-Tung-M-TP", String.class);
//        Artist artist = gson.fromJson(response, Artist.class);
//        System.out.println(artist);
//        FileManager fileManager = new FileManager();
//        fileManager.writeJson(artist, "/data/artist.json");



        System.out.println("Done");
    }
}