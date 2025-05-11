package steps;

import apiBaseService.AuthService;
import io.cucumber.java.en.*;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.Assert;

public class ForgotPasswordSteps {

    Response response;
    JsonPath jsonPath;

    @When("the user requests a password reset for email {string}")
    public void the_user_requests_a_password_reset(String email) {
        AuthService authService = new AuthService();
        response = authService.forgotPassword(email);
        jsonPath = response.jsonPath();
    }


    @Then("the response message should be {string}")
    public void the_response_message_should_be(String expectedMessage) {
        jsonPath = response.jsonPath();
        String actualMessage = jsonPath.getString("message");
        Assert.assertEquals(actualMessage, expectedMessage, "message is not matching");
    }




    @Then("the response status code should be {int}")
    public void the_response_status_code_should_be(Integer expectedStatusCode) {
        Assert.assertEquals((Integer) response.getStatusCode(), expectedStatusCode, "status code is not matching");
    }

}
