package hcmus.zingmp3;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.opencsv.CSVWriter;
import hcmus.zingmp3.model.Artist;
import hcmus.zingmp3.model.Playlist;
import hcmus.zingmp3.model.Song;
import hcmus.zingmp3.model.Streaming;
import lombok.Locked;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.*;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static hcmus.zingmp3.Main.dir;

public class FileManager {

    public void saveFile(String fileUrl, String fileName, String directory) {
        if (fileName.length() > 255) {
            fileName = fileName.substring(0, 255);
        }

        File dir = new File(directory);

        if (!dir.exists()) {
            boolean created = dir.mkdirs();
            if (created) {
                System.out.println("Thư mục mới đã được tạo thành công!");
            } else {
                System.out.println("Không thể tạo thư mục mới!");
            }
        }

        // Save file to directory
        try {
            URL url = new URL(fileUrl);
            URLConnection connection = url.openConnection();
            InputStream inputStream = connection.getInputStream();

            // Tạo một FileOutputStream để ghi dữ liệu từ InputStream vào file
            FileOutputStream outputStream = new FileOutputStream(directory + fileName);

            // Đọc dữ liệu từ InputStream và ghi vào FileOutputStream
            byte[] buffer = new byte[1024];
            int bytesRead;
            while ((bytesRead = inputStream.read(buffer)) != -1) {
                outputStream.write(buffer, 0, bytesRead);
            }

            // Đóng InputStream và FileOutputStream sau khi hoàn thành
            outputStream.flush();
            inputStream.close();
            outputStream.close();
        } catch (IOException e) {
            System.out.println("Error save file: " + fileName + " || " + fileUrl);
        }
    }

    public void saveData(Set<String> data, String fileName) {
        // Save data to file
        try {
            FileOutputStream outputStream = new FileOutputStream(dir + fileName);
            for (String line : data) {
                outputStream.write((line + "\n").getBytes());
            }
            outputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void writeJson(Set<Playlist> playlistSet, String fileName) {
        try {
            Gson gson = new GsonBuilder()
                    .setPrettyPrinting()
                    .registerTypeAdapter(LocalDate.class, new LocalDateAdapter())
                    .create();
            Writer writer = new FileWriter(dir + fileName);
            gson.toJson(playlistSet, writer);
            writer.flush();
            writer.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
