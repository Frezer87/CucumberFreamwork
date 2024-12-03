Feature: Search and Place the Order for Products

@offersPage
  Scenario: Search Experiance for product search home and offers page
    Given User is on GreenCart Landing page
    When User searched with shortname <Name> and extracted actual name of product
    Then user searched for <Name> shorname in offers page
    And validate product name in offers page matches with Landing Page
    
    Examples:
    | Name |
    | Tom  |
    | Beet |