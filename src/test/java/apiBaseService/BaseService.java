package apiBaseService;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.given;

public class BaseService {

    //Base Uri
    //Creating  the request
    // handling the response
    private static final String BASE_URL = "http://64.227.160.186:8080";
    private RequestSpecification requestSpecification;

    public BaseService() {
        requestSpecification = given().baseUri(BASE_URL);
    }

    protected void setAuthToken(String token) {
        requestSpecification.header("Authorization", "Bearer " + token);
    }

    protected Response postRequest(Object payLoad, String endpoint) {
        return requestSpecification.contentType(ContentType.JSON).body(payLoad).post(endpoint);
    }

    //get without path parameter
    protected Response getRequest(String endpoint) {
        return requestSpecification.get(endpoint);
    }

    protected Response getRequest(String accountNumber, int pages, int size, String endpoint) {
        return requestSpecification
                .queryParams("accountNumber", accountNumber)
                .queryParams("page", pages)
                .queryParams("size", size)
                .get(endpoint);
    }


    protected Response putRequest(Object payLoad, String endpoint) {
        return requestSpecification.contentType(ContentType.JSON).body(payLoad).put(endpoint);
    }


    protected Response postRequest(String baseUrl, Object payLoad, String endpoint) {
        return requestSpecification.given().baseUri(baseUrl).contentType(ContentType.JSON).body(payLoad).post(endpoint);
    }


}
