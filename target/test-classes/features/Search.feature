Feature: Vendor Support Center Homepage

  Background: 
    Given User is on the "Vendor Support Center" landing page

  @smokeTest
  Scenario: Verify the presence of security information
    Then The security information section is displayed when User clicks on Here’s how you know

  @smokeTest
  Scenario Outline: Validate navigation for each dropdown option in "I Want a Contract"
    When the user clicks on the "I Want a Contract" menu and selects "<dropdown>"
    Then the user should be redirected to the "<section title>" section

    Examples: 
      | dropdown                        | section title                   |
      | Types of Contracts              | Types of Contracts              |
      | Getting a GSA Schedule Contract | Getting a GSA Schedule Contract |
      | Additional Information          | Additional Information          |

  @smokeTest
  Scenario Outline: Validate search functionality
    When the user searches for "<item>" in the search field
    Then verify that the search results for "<results>" are displayed

    Examples: 
      | item                     | results                  |
      | Managing My GSA Contract | Managing My GSA Contract |
      | Contract Sales           | Contract Sales           |

   @smokeTest
  Scenario: Check Vendor File Status
    When User clicks on "Check File Status" button and then clicks on the login button
    Then Verify the user is redirected to the "General Services Administration - Sign In" page

  @testing
  Scenario Outline: Access eTools links
    When the user clicks on the resource "<link>"
    Then verify the link redirects to the correct "<page>"

    Examples: 
      | link          | page          |
      | GSA Advantage | GSA Advantage |
      | GSA eBuy      | GSA eBuy      |
      | eLibrary      | eLibrary      |
