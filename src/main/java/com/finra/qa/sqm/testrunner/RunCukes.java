package com.finra.qa.sqm.testrunner;

import cucumber.runtime.ClassFinder;
import cucumber.runtime.RuntimeOptions;
import cucumber.runtime.io.MultiLoader;
import cucumber.runtime.io.ResourceLoader;
import cucumber.runtime.io.ResourceLoaderClassFinder;
import cucumber.runtime.Runtime;

import java.util.ArrayList;

import static java.util.Arrays.asList;

/**
 * This file is Created by Praveen on 7/3/2020
 */

public class RunCukes {

    @SuppressWarnings("unused")
    public void RunningOfCukes(String[] argv)  throws Exception{
        byte existatus = run(argv,Thread.currentThread().getContextClassLoader());
    }


    public static byte run(String[] argv, ClassLoader classLoader) throws Exception {
        RuntimeOptions runtimeOptions = new RuntimeOptions(new ArrayList<String>(asList(argv)));
        ResourceLoader resourceLoader= new MultiLoader(classLoader);
        ClassFinder classFinder = new ResourceLoaderClassFinder(resourceLoader,classLoader);
        Runtime runtime = new Runtime(resourceLoader,classFinder,classLoader,runtimeOptions);
        runtime.run();
        return runtime.exitStatus();


    }
}
