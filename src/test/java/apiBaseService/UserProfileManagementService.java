package apiBaseService;


import io.restassured.response.Response;
import modelsRequest.ProfileRequest;

public class UserProfileManagementService extends BaseService {

    private final String BASE_PATH = "/api/users/";

    public Response getProfile(String token) {
        setAuthToken(token);
        return getRequest(BASE_PATH + "profile");
    }

    public Response updateProfile(ProfileRequest payload, String token) {
        setAuthToken(token);
        return putRequest(payload, BASE_PATH + "profile");
    }


}
