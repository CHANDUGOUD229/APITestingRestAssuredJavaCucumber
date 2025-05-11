package apiBaseService;


import io.restassured.response.Response;
import modelsRequest.LoginRequest;
import modelsRequest.SignupRequest;

import java.util.HashMap;

public class AuthService extends BaseService {

    private static final String BASE_PATH = "/api/auth/";

    public Response login(LoginRequest payLoad) {
        return postRequest(payLoad, BASE_PATH + "login");
    }

    public Response signup(SignupRequest payLoad) {
        return postRequest(payLoad, BASE_PATH + "signup");
    }

    public Response forgotPassword(String emailaddress) {
        HashMap<String, String> payLoad = new HashMap<>();
        payLoad.put("email", emailaddress);
        return postRequest(payLoad, BASE_PATH + "forgot-password");
    }


}
