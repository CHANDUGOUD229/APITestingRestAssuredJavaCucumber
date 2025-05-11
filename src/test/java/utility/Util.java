package utility;
import apiBaseService.AuthService;
import io.restassured.response.Response;
import modelsRequest.LoginRequest;
import modelsResponse.LoginResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
public class Util {


    public static String secreteToken(){
        AuthService authService = new AuthService();
        Response response = authService.login(new LoginRequest("uday1234", "uday12345"));
        LoginResponse loginResponse = response.as(LoginResponse.class);
      return  loginResponse.getToken();

    }

    public static String jsonReader(String filePath) {
        try {
            return new String(Files.readAllBytes(Paths.get(filePath)));
        } catch (Exception e) {
            throw new RuntimeException("Failed to read JSON file", e);
        }
    }
    private static final ObjectMapper objectMapper = new ObjectMapper();
    public static void writeJsonToFile(Object data, String filePath) {
        try {
            objectMapper.writerWithDefaultPrettyPrinter().writeValue(new File(filePath), data);
        } catch (IOException e) {
            throw new RuntimeException("Failed to write JSON to file", e);
        }
    }

}
