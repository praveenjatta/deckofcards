package com.finra.qa.sqm.testrunner;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.NetworkMode;
import org.testng.ITestContext;
import org.testng.annotations.*;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * This file is Created by Praveen on 7/3/2020
 */

public class RunCukesByCompositionTest extends RunCukes {

    public TestContext context;


    @BeforeSuite
    public void beforeSuite(ITestContext istx) throws Exception {
        try {

            TestContext.report = new ExtentReports(System.getProperty("user.dir") + "/target/report.html", NetworkMode.ONLINE);
            istx.getSuite().getParallel();
        } catch (Exception e) {

        }
    }

    @Parameters({"FeatureName"})
    @BeforeTest
    public void beforeTest(ITestContext ctx, String FeatureName) throws Exception {
        Injector injector = Guice.createInjector(new ExecutionBinding());
        this.context = injector.getInstance(TestContext.class);
        MyContainer.putInstance(Thread.currentThread().getId(), this.context);
        this.context = (TestContext) MyContainer.getInstance(Thread.currentThread().getId());

        this.context.featureName = ctx.getCurrentXmlTest().getParameter("FeatureName");
        String path = System.getProperty("user.dir") + "/src/test/resources/features/" + this.context.featureName;
        if (path.contains("*")) {
            this.context.isFolder = true;
        } else {
            File dir = new File(path);
            if (dir.isDirectory()) {
                this.context.isFolder = true;
            }
        }

        if (!this.context.isFolder) {
            if (this.context.featureName.contains(".feature"))
                this.context.testName = this.context.featureName.substring(this.context.featureName.lastIndexOf("/") + 1, this.context.featureName.length());
            else
                this.context.testName = ctx.getCurrentXmlTest().getName();


        }else{
            this.context.testName=ctx.getCurrentXmlTest().getName();
        }

    }

    @BeforeMethod
    public void beforeMethod(){
        this.context.testName=this.context.testName.replace(".feature","");
        this.context.test=TestContext.report.startTest(this.context.testName);
    }
    /********************************
     * Cucumber Test Method
     ****************************/
    public String[] OptionsSpecification(ITestContext istx) {

        String tags = "";
        List<String> runParams = new ArrayList<String>();
        String[] Options_Runtime = new String[7];
        Options_Runtime[0] = System.getProperty("user.dir") + "/src/test/resources/features/" + this.context.featureName;
        Options_Runtime[1] = "--glue";
        Options_Runtime[2] = "stepdefinition";
        Options_Runtime[3] = "--glue";
        Options_Runtime[4] = "com/finra/qa/sqm";
        Options_Runtime[5] = "--plugin";
        Options_Runtime[6] = "json:target/cucumberReport.json";
        for (int i = 0; i < Options_Runtime.length; i++)
            runParams.add(Options_Runtime[i]);
        if (System.getProperty("tagsparam") != null) {
            runParams.add("--tags");
            runParams.add(System.getProperty("tagsparam"));
        } else {
            tags = istx.getCurrentXmlTest().getParameter("tags");
            if (!(tags == null)) {

                if (!tags.equals("")) {
                    runParams.add("--tags");
                    runParams.add(tags);
                }
            }
        }
        return runParams.toArray(new String[runParams.size()]);
    }


    @SuppressWarnings("resource")
    @Test
    public void runCuke(ITestContext istx) throws Exception  {
        super.RunningOfCukes(OptionsSpecification(istx));
    }


    @AfterMethod
    public void afterMethod(){

        TestContext.report.endTest(this.context.test);

    }

    @AfterSuite(alwaysRun = true)
    public void quit() throws IOException, InterruptedException {

        TestContext.report.flush();
        TestContext.report.close();

    }



}
