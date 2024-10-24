
@tag
Feature: Purchase the order from Ecommerse website

Background: 
Given I landed on Ecommerse page

  @tag2
  Scenario Outline: Positive test of submitting the order
    Given Logged in with  username <uname> and passwoord  <pass>
    When I add product <productName> to cart
    And Check out the product <productName> and submit the order
    Then "THANKYOU FOR THE ORDER" message is displayed on confirmation page

    Examples: 
      | uname                 | pass        | productName  |
      | sonali@mailinator.com | ABCabbc@123 | ZARA COAT 3 |
      
