package stepdefinition;

import com.finra.qa.sqm.api.DeckofCardsPage;
import com.finra.qa.sqm.testrunner.MyContainer;
import com.finra.qa.sqm.testrunner.TestContext;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.apache.log4j.Logger;

import java.util.HashMap;

public class DeckOfCardsStep {

    final static Logger logger = Logger.getLogger(DeckOfCardsStep.class);
    public TestContext context;
    DeckofCardsPage deckofCards;

    public DeckOfCardsStep() {
        context = (TestContext) MyContainer.getInstance(Thread.currentThread().getId());
        deckofCards = new DeckofCardsPage();
    }


    @Given("^I GET New Deck of Cards using open API$")
    public void create_deck_of_cards(){
      deckofCards.createDeck();
    }

    @When("^I Validate the response with Success as \"([^\"]*)\" and Shuffle Status$")
    public void validate_response_as_success(String status){
        deckofCards.validate_success_status(status);
    }

    @Then("^I get the deckid and total cards count$")
    public void get_deckid_cardscount(){
        deckofCards.getDeckID_deckCount();
    }

    @And("^I shuffle the cards and validate the Shuffle Status$")
    public void shuffle_the_cards(){
        deckofCards.shuffle_cards();
    }

    @Given("^I POST New Deck of Cards with Jokers using open API$")
    public void create_deck_of_cards_with_jokers(){
        deckofCards.postDeck();
    }

    @And("^I Validate all the Cards in the Deck$")
    public void validate_all_cards(){
        deckofCards.validate_All_Cards();
    }

    @And("^Draw \"([^\"]*)\" cards and validate the remaining cards$")
    public void draw_card_and_validate_remainaingCards(String num){
        deckofCards.drawACard(num);
    }



}
