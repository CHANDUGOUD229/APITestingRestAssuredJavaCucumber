package steps;

import apiBaseService.AuthService;
import com.github.javafaker.Faker;
import io.cucumber.java.en.*;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import modelsRequest.SignupRequest;
import org.testng.Assert;

public class UserRegistrationSteps {
    SignupRequest signupRequest;
    Response response;
    Faker faker;
    AuthService authService;
    String message;
    public UserRegistrationSteps(){

        authService = new AuthService();
    }


    @Given("the user registration endpoint is available")
    public void the_user_registration_endpoint_is_available() {
        faker=new Faker();
         signupRequest = new SignupRequest.Builder()
                .username(faker.name().username())
                .email(faker.internet().emailAddress())
                .firstName(faker.name().firstName())
                .password(faker.internet().password())
                .lastName(faker.name().lastName())
                .mobileNumber("7548564585").build();
    }

    @When("I submit a registration request with valid details")
    public void i_submit_a_registration_request_with_valid_details() {

        response = authService.signup(signupRequest);


    }

    @Then("the response status should be {int} Created")
    public void the_response_status_should_be_created(Integer statusCode) {
        Assert.assertEquals(response.getStatusCode(),statusCode,"status code is not matching");
    }

    @Then("the response should contain a user ID and success message")
    public void the_response_should_contain_a_user_id_and_success_message() {
        Assert.assertEquals(response.asPrettyString(), "User registered successfully!","response message is not matching");

    }

    @Given("a user already exists with username {string}")
    public void a_user_already_exists_with_username(String username) {
        signupRequest = new SignupRequest.Builder()
                .username(username)
                .email("jhan.deo@gmail.com")
                .firstName("jhan.deo")
                .password("faked1232")
                .lastName("faked")
                .mobileNumber("7548564585").build();
        response = authService.signup(signupRequest);

    }

    @Then("the response status should be {int} Conflict")
    public void the_response_status_should_be_conflict(Integer statusCode) {
        Assert.assertEquals(response.getStatusCode(),400,"status code is not matching");

    }
    @Then("the response should contain an error message {string}")
    public void the_response_should_contain_an_error_message(String exMessage) {

        String message=response.getBody().asString();
        String msg=message.substring(7,message.length());
        Assert.assertEquals(msg, "Username is already taken!","response message is not matching");

    }





}
