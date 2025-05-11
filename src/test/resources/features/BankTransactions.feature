Feature: Bank Transactions

  @deposit
  Scenario: Deposit with valid credentials and valid deposit request
    Given the user logs in with username "uday1234" and password "uday12345"
    When the user submits a deposit request with account number "2042587477", amount 1000 and description "transeformed amount"
    Then the deposit response status should be "SUCCESS"
    And the deposited amount should be 1000
    And the source account number should be "2042587477"
