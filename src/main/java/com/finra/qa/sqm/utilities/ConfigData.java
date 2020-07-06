package com.finra.qa.sqm.utilities;

import com.finra.qa.sqm.testrunner.MyContainer;
import com.finra.qa.sqm.testrunner.TestContext;
import java.io.FileInputStream;
import java.util.Properties;

import org.apache.log4j.Logger;

/**
 * This file is Created by Praveen on 7/3/2020
 */

public class ConfigData {

    public TestContext context = (TestContext) MyContainer.getInstance(Thread.currentThread().getId());
    static final Logger logger = Logger.getLogger(ConfigData.class);

    public ConfigData() {
    }


    public String getconfigData(String key) {
        FileInputStream ip = null;

        try {
            this.context.config = new Properties();
            String path = System.getProperty("user.dir") + "/src/main/resources/config/config" + ".properties";
            ip = new FileInputStream(path);
            this.context.config.load(ip);
            String var5 = this.context.config.getProperty(key);
            return var5;
        } catch (Exception var15) {
            logger.info("Exception: " + var15);
        } finally {
            try {
                if (ip != null) {
                    ip.close();
                }
            } catch (Exception var14) {
                logger.info(var14.getMessage());
            }

        }

        return null;
    }

}
