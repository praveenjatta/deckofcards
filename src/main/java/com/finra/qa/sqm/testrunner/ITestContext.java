package com.finra.qa.sqm.testrunner;

import com.relevantcodes.extentreports.ExtentTest;


import java.util.Properties;

/**
 * This file is Created by Praveen on 7/3/2020
 */

public interface ITestContext {
    public String runAgainst = null;
    public String SuiteRunId = null;
    public String featureName = null;
    public Properties config = null;
    public ExtentTest test = null;
}
