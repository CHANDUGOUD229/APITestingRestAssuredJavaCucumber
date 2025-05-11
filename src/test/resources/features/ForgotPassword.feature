Feature: Forgot Password

  @forgotPassword
  Scenario: User requests password reset with a valid registered email
    When the user requests a password reset for email "ankitha227@yahoo.com"
    Then the response message should be "If your email exists in our system, you will receive reset instructions."
    And the response status code should be 200

