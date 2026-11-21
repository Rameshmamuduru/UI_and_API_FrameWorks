Feature: User Login feature
	
    
    
  # TC017 - Login with valid credentials
  Scenario: TC017 - Login with valid credentials

    Given the user is on the home page and clicks on Signup Login
    When the user enters a valid email address as "jhonwalker@gmail.com"
    And the user enters a valid password as "Password@1234"
    And the user clicks the Login button
    Then the user should see "Logged in as"


  # TC018 - Login with invalid credentials
  Scenario: TC018 - Login with invalid credentials

    Given the user is on the home page and clicks on Signup Login
    When the user enters invalid email address and password as below
      | email    | rames@gmail.com |
      | password | ramesh@123      |
    And the user clicks the Login button
    Then the user should see "incorrect!"
    
      
  @datadriven
  Scenario Outline: TC019 - Login data driven testing
    Given the user is on the home page and clicks on Signup Login
    When the user enters a valid email address as "<email>"
    And the user enters a valid password as "<password>"
    And the user clicks the Login button
    Then the user should see "<validationMessage>"
    
    Examples:
    	| email                | password       | validationMessage |
      	| jhonwalker@gmail.com | Password@1234  |	Logged in as	|
      	| invalid@gmail.com    | invalid@123    |	incorrect!		|
      	| demo@gmail.com       | demo@123       |	incorrect!		|
		