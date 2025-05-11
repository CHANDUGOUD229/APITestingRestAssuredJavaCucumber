Feature: validate User Registration functionality

  Scenario: Successful User Registration
    Given the user registration endpoint is available
    When I submit a registration request with valid details
    Then the response status should be 200 Created
    And the response should contain a user ID and success message
@test
  Scenario: Registration with Existing Username
    Given a user already exists with username "john_doe"
    Then the response status should be 400 Conflict
    And the response should contain an error message "Username already exists"


