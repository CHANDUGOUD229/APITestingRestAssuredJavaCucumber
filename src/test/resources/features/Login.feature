Feature: Validate User Login API

  @login
  Scenario: Validate Successful login with valid credentials
    And a user logs in with valid username "uday1234" and password "uday12345"
    When I submit the login request
    Then the response status should be 200 OK

@login
  Scenario: Validate Login fails with invalid credentials
    And a user logs in with invalid username "wrong_user" and password "wrong_pass"
    When I submit the login request
    Then the response status should be 401 Unauthorized
    And the response should contains an error message "Invalid Credentials"
