Feature: Deck of Cards API Validation


  Scenario: Create a New Deck of Cards and Validate
    Given I GET New Deck of Cards using open API
    When I Validate the response with Success as "true" and Shuffle Status
    Then I get the deckid and total cards count
    And I shuffle the cards and validate the Shuffle Status
    And I Validate all the Cards in the Deck


  Scenario: Create a New Deck of Cards with Jokers and Validate
    Given I POST New Deck of Cards with Jokers using open API
    When I Validate the response with Success as "true" and Shuffle Status
    Then I get the deckid and total cards count
    And I shuffle the cards and validate the Shuffle Status
    And I Validate all the Cards in the Deck


  Scenario: Draw a Card from the New Deck of Cards and Validate the remaining cards
    Given I GET New Deck of Cards using open API
    When I Validate the response with Success as "true" and Shuffle Status
    Then I get the deckid and total cards count
    And I shuffle the cards and validate the Shuffle Status
    And Draw "1" cards and validate the remaining cards
    And Draw "2" cards and validate the remaining cards
    And Draw "3" cards and validate the remaining cards









