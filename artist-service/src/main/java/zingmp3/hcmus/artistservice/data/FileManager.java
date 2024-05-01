package zingmp3.hcmus.artistservice.data;

import ch.qos.logback.core.util.FileUtil;
import org.springframework.stereotype.Service;

import java.io.*;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;

@Service
public class FileManager {

    public void saveFile(String fileUrl, String fileName, String directory) {
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
            inputStream.close();
            outputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void saveData(List<String> data, String fileName) {
        // Save data to file
        try {
            FileOutputStream outputStream = new FileOutputStream(fileName, true);
            for (String line : data) {
                outputStream.write((line + "\n").getBytes());
            }
            outputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<String> readData(String fileName) {
        List<String> result = new ArrayList<>();
        try {
            FileInputStream inputStream = new FileInputStream(fileName);
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
            String line;
            while ((line = reader.readLine()) != null) {
                result.add(line);
            }
        } catch (FileNotFoundException e) {
            // Do nothing
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return result;
    }

    public boolean isExist(String content, String fileName) {
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.contains(content)) {
                   return true;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

}
