import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.simple.JSONObject;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

public class JsonToJava {
    public static void main(String args[]) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();

        Path currentRelativePath = Paths.get("");
        String jsonPath = currentRelativePath.toAbsolutePath().toString() + "\\jsonfiles\\customerInfo.json";

        CustomerInfo customerInfo = objectMapper.readValue(new File(jsonPath), CustomerInfo.class);

        System.out.println(customerInfo.getCourseName());
    }
}
