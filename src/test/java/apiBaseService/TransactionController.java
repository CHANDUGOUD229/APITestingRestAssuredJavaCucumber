package apiBaseService;

import io.restassured.response.Response;

public class TransactionController extends BaseService {

    private final String BASE_PATH = "/api/transactions/";

    public Response deposit(Object payLoad, String token) {
        setAuthToken(token);
        return postRequest(payLoad, BASE_PATH + "deposit");

    }

    public Response history(String token) {
        setAuthToken(token);
        return getRequest("2042587477", 0, 5, BASE_PATH + "history");
    }

    public Response transfer(Object payLoad, String token) {
        setAuthToken(token);
        return postRequest(payLoad, BASE_PATH + "transfer");
    }

    public Response withDraw(Object payLoad, String token) {
        setAuthToken(token);
        return postRequest(payLoad, BASE_PATH + "withdraw");
    }


}
