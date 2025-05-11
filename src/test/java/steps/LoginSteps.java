package steps;

import apiBaseService.AuthService;
import io.cucumber.java.en.*;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import modelsRequest.LoginRequest;
import modelsResponse.LoginResponse;
import org.testng.Assert;

public class LoginSteps {

    LoginResponse loginResponse;
    LoginRequest loginApiTest;
    AuthService authService;
    Response response;

    @Given("a user logs in with valid username {string} and password {string}")
    public void a_user_logs_in_with_valid_username_and_password(String username, String password) {
        loginApiTest = new LoginRequest(username, password);
    }

    @When("I submit the login request")
    public void i_submit_the_login_request() {

        authService = new AuthService();
    }

    @Then("the response status should be {int} OK")
    public void the_response_status_should_be_ok(Integer statusCode) {
        response = authService.login(loginApiTest);
        Assert.assertEquals(response.getStatusCode(), statusCode, "status code is  not matching");
        loginResponse = response.as(LoginResponse.class);
    }

    @Given("a user logs in with invalid username {string} and password {string}")
    public void a_user_logs_in_with_invalid_username_and_password(String username, String password) {
        loginApiTest = new LoginRequest(username, password);

    }

    @Then("the response status should be {int} Unauthorized")
    public void the_response_status_should_be_unauthorized(Integer statusCode) {
        response = authService.login(loginApiTest);
        Assert.assertEquals(response.getStatusCode(), statusCode, "status code is  not matching for negative test");

    }

    @Then("the response should contains an error message {string}")
    public void the_response_should_contains_an_error_message(String exMessage) {
        String rawBody = response.getBody().asString();
        String header=response.getHeader("Content-Type");
        System.err.println(header);
        JsonPath jsonPath = new JsonPath(rawBody); // Forcing parsing
        String errorMessage = jsonPath.getString("error");
        Assert.assertEquals(errorMessage, "Invalid Credentials", "response message is not matching");

    }


}
