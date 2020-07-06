package com.finra.qa.sqm.testrunner;

import com.google.inject.AbstractModule;

/**
 * This file is Created by Praveen on 7/3/2020
 */

public class  ExecutionBinding extends AbstractModule {

    @Override
    public void configure() {
        bind(ITestContext.class).to(TestContext.class);
    }
}