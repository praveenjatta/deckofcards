package com.finra.qa.sqm.testrunner;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Properties;

/**
 * This file is Created by Praveen on 7/3/2020
 */

public class TestContext  implements  ITestContext{

    public String runAgainst=null;
    public String featureName;
    public boolean isFolder=false;
    public String testName;
    public Properties config = null;
    public static ExtentReports report=null;
    public ExtentTest test = null;
    public String xmlInString="";
    public HashMap<String,String> responseValues=null;
    public Hashtable<String, ArrayList<String>> cardsData= null;
    public String response="";

}
