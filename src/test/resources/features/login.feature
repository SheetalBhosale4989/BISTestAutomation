Feature: Verify login


  Scenario Outline: Validate user is able to login with valid credentials
    Given user is on login page
    When user logs in with username "<username>" and password "<password>"
    Then user should see "<loginstatus>"

    Examples:
      | username | password | loginstatus      |
      | guest    | guest    | login successful |


  Scenario Outline: Validate user is able to login with invalid credentials
    Given user is on login page
    When user logs in with username "<username>" and password "<password>"
    Then user should see error message "<loginfailed>"

    Examples:
      | username | password | loginfailed    |
      | wrong    | wrong    | Not_Authorized |