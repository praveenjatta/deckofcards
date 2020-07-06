package com.finra.qa.sqm.api;

import com.finra.qa.sqm.testrunner.MyContainer;
import com.finra.qa.sqm.testrunner.TestContext;
import com.finra.qa.sqm.utilities.ExcelData;
import com.finra.qa.sqm.utilities.Service;
import com.relevantcodes.extentreports.LogStatus;
import org.apache.log4j.Logger;

import java.util.HashMap;
import java.util.Hashtable;
import java.util.Set;

/**
 * This file is Created by Praveen on 7/3/2020
 */

public class DeckofCardsPage {

    final static Logger logger = Logger.getLogger(DeckofCardsPage.class);
    public TestContext context;
    Service service;


    public DeckofCardsPage() {
        context = (TestContext) MyContainer.getInstance(Thread.currentThread().getId());
        service = new Service();
    }



    /**
     * Using the method we can Create New Deck of Cards
     * @author  Praveen
     * @since 03-July-2020
     */


    public void createDeck(){
        context.runAgainst="NewDeck";
      context.responseValues= service.getResponse();

    }



    /**
     * Using the method we can Create New Deck of Cards with Jokers
     * @author  Praveen
     * @since 03-July-2020
     */

    public void postDeck(){
        context.runAgainst="NewDeckWithJokers";
        context.responseValues= service.postRequest();

    }


    /**
     * Using the method we can get the Success Status of the API and Shuffle Status
     * @author  Praveen
     * @since 03-July-2020
     */

    public void validate_success_status(String status){
      if(context.responseValues.get("success").equalsIgnoreCase(status)){
          logger.info("Success status is matching : "+status);
          context.test.log(LogStatus.INFO,"Success status is matching : "+status);
        }else{
          logger.info("Success status is not matching");
          context.test.log(LogStatus.INFO,"Success status is not matching "+status);
        }
      logger.info("Shuffled status is "+context.responseValues.get("shuffled"));
      context.test.log(LogStatus.INFO,"Shuffled status is "+context.responseValues.get("shuffled")+ System.lineSeparator());

    }


    /**
     * Using the method we can get the Deckid & Cards Count
     * @author  Praveen
     * @since 03-July-2020
     */

    public void getDeckID_deckCount(){
        logger.info("Deck Id "+context.responseValues.get("deck_id"));
        context.test.log(LogStatus.INFO,"Deck Id "+context.responseValues.get("deck_id"));
        logger.info("Total cards count " + context.responseValues.get("remaining"));
        context.test.log(LogStatus.INFO, "Total cards count " + context.responseValues.get("remaining"));
        if(context.responseValues.get("remaining").equalsIgnoreCase("54")) {
            System.out.println(System.lineSeparator());
            System.out.println("Jokers added to the Deck");
            System.out.println(" JOKER :: Code :: X1 suit :: BLACK " );
            System.out.println(" JOKER :: Code :: X2 suit :: RED " );
        }
     }



    /**
     * Using the method we can Shuffle the Cards
     * @author  Praveen
     * @since 03-July-2020
     */

    public void shuffle_cards(){
        if(context.responseValues.get("shuffled").equals(true)){
            logger.info("Cards are already shuffled" + System.lineSeparator());
            context.test.log(LogStatus.INFO,"Cards are already shuffled" + System.lineSeparator());

        }else{
            String deckid=context.responseValues.get("deck_id").replace("\"","");
            context.runAgainst="Shuffle";
            context.responseValues= service.getResponse(deckid+"/shuffle/");
            logger.info("Cards are shuffled and Shuffled status is "+context.responseValues.get("shuffled"));

        }
    }


    /**
     * Using the method we can Validate all the Cards getting Expected Values from the ExcelSheet
     * @author  Praveen
     * @since 03-July-2020
     */

    public void validate_All_Cards(){
        ExcelData excelData=new ExcelData();
        excelData.readDataFromExcel();
        String deckid=context.responseValues.get("deck_id").replace("\"","");
        context.runAgainst="DrawACard";
        context.responseValues= service.getResponse(deckid+"/draw/?count=52");
        Hashtable<String,String> actualData=service.getJSONValue(context.response);
        if(actualData.size() == 52) {
            System.out.println("Total cards count = " + actualData.size() + System.lineSeparator());
            context.test.log(LogStatus.INFO," Total cards count = " + actualData.size());
            Set<String> suit = context.cardsData.keySet();
            for (String suitName : suit) {
                for (int i = 0; i < 13; i++) {
                    if (suitName.equalsIgnoreCase(actualData.get(context.cardsData.get(suitName).get(i)))) {
                        System.out.println(" Code :: "  + context.cardsData.get(suitName).get(i) + "  suit :: " + suitName);
                        context.test.log(LogStatus.INFO,"  Code :: "  + context.cardsData.get(suitName).get(i) + "  suit :: " + suitName);
                    } else {
                        System.out.println("Duplicate cards found");
                        context.test.log(LogStatus.INFO," Duplicate cards found ");
                    }
                }
            }
        }else if(actualData.size() == 54){
            System.out.println("Total cards count = " + actualData.size() + System.lineSeparator());
            context.test.log(LogStatus.INFO," Total cards count = " + actualData.size());
            Set<String> suit = context.cardsData.keySet();
            for (String suitName : suit) {
                for (int i = 0; i < 13; i++) {
                    if (suitName.equalsIgnoreCase(actualData.get(context.cardsData.get(suitName).get(i)))) {
                        System.out.println(" Code :: " + context.cardsData.get(suitName).get(i) + "  suit :: " + suitName);
                        context.test.log(LogStatus.INFO,"  Code :: " + context.cardsData.get(suitName).get(i) + "  suit :: " + suitName);
                    } else {
                        System.out.println("Duplicate cards found");
                        context.test.log(LogStatus.INFO," Duplicate cards found ");
                    }
                }
            }
            System.out.println(" Code :: X1 suit :: " + actualData.get("X1"));
            System.out.println(" Code :: X2 suit :: " + actualData.get("X2"));
        }

    }

    /**
     * Using the method we can Draw a Card from the Deck
     * @author  Praveen
     * @since 03-July-2020
     */

    public void drawACard(String cardNum){
        String deckid=context.responseValues.get("deck_id").replace("\"","");
        context.runAgainst="DrawACard";
        context.responseValues= service.getResponse(deckid+"/draw/?count="+cardNum);
        Hashtable<String,String> actualData=service.getJSONValue(context.response);
        int cards=Integer.parseInt(cardNum);
        if(actualData.size()==cards){
            logger.info("Number of cards drawn "+cardNum + System.lineSeparator());
            context.test.log(LogStatus.INFO,"Number of cards drawn " +cardNum + System.lineSeparator());
           for(String suit: actualData.keySet()){
               logger.info( " Code :: " + actualData.get(suit) + "  Suit :: " + suit + System.lineSeparator());
               context.test.log(LogStatus.INFO," Code :: " + actualData.get(suit) + "  Suit :: " + suit + System.lineSeparator());
           }
        }
      logger.info(" remaining cards " + context.responseValues.get("remaining") + System.lineSeparator());
      context.test.log(LogStatus.INFO," remaining cards " + context.responseValues.get("remaining") + System.lineSeparator());
    }



}
