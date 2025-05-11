package steps;

import apiBaseService.AuthService;
import apiBaseService.TransactionController;
import io.cucumber.java.en.*;
import io.qameta.allure.Description;
import io.restassured.response.Response;
import modelsRequest.DepositRequest;
import modelsRequest.LoginRequest;
import modelsResponse.DepositResponse;
import modelsResponse.LoginResponse;
import org.testng.Assert;
import utility.Util;

import java.io.IOException;

public class BankTransactionsSteps {

    LoginResponse loginResponse;
    Response response;
    DepositResponse depositResponse;
    @Description("this step enter the login details")
    @Given("the user logs in with username {string} and password {string}")
    public void the_user_logs_in(String username, String password) {
        AuthService authService = new AuthService();
        response = authService.login(new LoginRequest(username, password));
        loginResponse = response.as(LoginResponse.class);
        Assert.assertNotNull(loginResponse.getToken(), "Login token should not be null");
    }

    @Description("this step verifies the login response")
    @When("the user submits a deposit request with account number {string}, amount {int} and description {string}")
    public void the_user_submits_a_deposit_request(String accountNumber, int amount, String description) throws IOException {
        DepositRequest depositRequest = new DepositRequest.Builder()
                .accountNumber(accountNumber)
                .amount(amount)
                .description(description)
                .build();

        TransactionController transactionController = new TransactionController();
        response = transactionController.deposit(Util.jsonReader("./src/test/java/TestDataFromJsonFile/depositRequest.json"), loginResponse.getToken());
        depositResponse = response.as(DepositResponse.class);
    }

    @Description("this step verifies the deposit response")
    @Then("the deposit response status should be {string}")
    public void the_deposit_response_status_should_be(String expectedStatus) {
        Assert.assertEquals(depositResponse.getStatus(), expectedStatus, "Status mismatch");
    }

    @Description("this step verifies the deposit amount")
    @Then("the deposited amount should be {int}")
    public void the_deposited_amount_should_be(int expectedAmount) {
        Assert.assertEquals(depositResponse.getAmount(), expectedAmount, "Amount mismatch");
    }

    @Description("this step verifies the deposit description")
    @Then("the source account number should be {string}")
    public void the_source_account_number_should_be(String expectedAccountNumber) {
        Assert.assertEquals(depositResponse.getSourceAccount(), expectedAccountNumber, "Account number mismatch");
    }



}
