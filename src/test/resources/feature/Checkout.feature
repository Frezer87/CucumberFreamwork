Feature: place the order for Products
@placeOrder
  Scenario: Search Experiance for product search in both home and offers page
    Given User is on GreenCart Landing page
    When User searched with shortname <Name> and extracted actual name of product
    And Added iterms of the selected product to cart
    Then User proceeds to Checkout and validate the <Name> items in checkout page
    And verify user has abilty to place the order
    
    Examples:
    | Name |
    | Tom  |
  