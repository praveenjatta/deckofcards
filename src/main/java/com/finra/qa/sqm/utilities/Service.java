package com.finra.qa.sqm.utilities;

import java.security.KeyStore;
import com.finra.qa.sqm.testrunner.TestContext;
import com.finra.qa.sqm.testrunner.MyContainer;
import com.finra.qa.sqm.testrunner.RunCukesByCompositionTest;
import com.relevantcodes.extentreports.LogStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.conn.ssl.*;
import org.apache.http.impl.client.CloseableHttpClient;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import javax.net.ssl.*;
import java.security.*;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.*;

/**
 * This file is Created by Praveen on 7/3/2020
 */

public class Service {

    public TestContext context;
    public int responseStatus;
    final static Logger logger = Logger.getLogger(Service.class);
    RunCukesByCompositionTest runCukesByCompositionTest;
    ConfigData configData;

    public Service() {
        context = (TestContext) MyContainer.getInstance(Thread.currentThread().getId());
        runCukesByCompositionTest = new RunCukesByCompositionTest();
        configData = new ConfigData();

    }



    public CloseableHttpClient setHttpClient() throws NoSuchAlgorithmException, KeyStoreException, KeyManagementException {
        SSLContext sslContext = this.setupSSLContext();
        CloseableHttpClient client = HttpClients.custom().setSSLContext(sslContext).setSSLHostnameVerifier(new NoopHostnameVerifier()).build();
        return client;
    }

    public SSLContext setupSSLContext() throws KeyStoreException, NoSuchAlgorithmException, KeyManagementException {
        SSLContext sslContext = (new SSLContextBuilder()).loadTrustMaterial((KeyStore) null, new TrustStrategy() {
            public boolean isTrusted(X509Certificate[] x509Certificates, String s) throws CertificateException {
                return true;
            }
        }).build();
        return sslContext;
    }


    /**
     * Using the method we can Create New Deck of Cards using GET
     * @author  Praveen
     * @since 03-July-2020
     */

    public HashMap<String,String> getResponse(String... app){
        String appVal="";
        if(app.length>0){
            appVal=app[0];
        }
        CloseableHttpClient client = null;
        CloseableHttpResponse response=null;
        String responseData="";
        HashMap<String,String> responseValues=new HashMap<String,String>();

        try {

            client = this.setHttpClient();

            String  url = configData.getconfigData(this.context.runAgainst);
            System.out.println(System.lineSeparator());
            logger.info(this.context.runAgainst + " : " + url+appVal + System.lineSeparator());
            context.test.log(LogStatus.INFO,this.context.runAgainst + " : " + url+appVal + System.lineSeparator());
            HttpGet httpGet = new HttpGet(url+appVal);

            response = client.execute(httpGet);
            this.responseStatus = response.getStatusLine().getStatusCode();
            logger.info(" I get RESPONSE Status as : " + this.responseStatus + System.lineSeparator());
            context.test.log(LogStatus.INFO," I get RESPONSE Status as : " + this.responseStatus + System.lineSeparator());
            responseData=EntityUtils.toString(response.getEntity());
            context.response=responseData;
            logger.info(" Response : " +responseData + System.lineSeparator());
            context.test.log(LogStatus.INFO," Response : " +responseData + System.lineSeparator());
            String responseDat=responseData.substring(1,responseData.length()-1);
            String[] responseArr=responseDat.split(",");
            for(int i=0;i<responseArr.length;i++){
                responseValues.put(responseArr[i].split(":")[0].replace("\"","").trim(),responseArr[i].split(":")[1].trim());
            }

        } catch (Exception var18) {
            logger.info("Response Failed" + System.lineSeparator());
            logger.info("Exception: " + var18 + System.lineSeparator());
            context.test.log(LogStatus.INFO," Response Failed" + System.lineSeparator());

        }


       return responseValues;
    }

    /**
     * Using the method we can Create New Deck of Cards using POST
     * @author  Praveen
     * @since 03-July-2020
     */

    public HashMap<String,String> postRequest(){

        CloseableHttpClient client = null;
        CloseableHttpResponse response=null;
        String responseData="";
        HashMap<String,String> responseValues=new HashMap<String,String>();

        try {

            client = HttpClients.createDefault();

            String  url = configData.getconfigData(this.context.runAgainst);
            System.out.println(System.lineSeparator());
            logger.info(this.context.runAgainst + " : " + url+ System.lineSeparator());
            context.test.log(LogStatus.INFO,this.context.runAgainst + " : " + url+ System.lineSeparator());
            HttpGet httpPost = new HttpGet(url);
            response = client.execute(httpPost);
            this.responseStatus = response.getStatusLine().getStatusCode();
            logger.info(" I get RESPONSE Status as : " + this.responseStatus + System.lineSeparator());
            context.test.log(LogStatus.INFO, " I get RESPONSE Status as : " + this.responseStatus + System.lineSeparator());
            responseData=EntityUtils.toString(response.getEntity());
            context.response=responseData;
            logger.info(" Response : " +responseData + System.lineSeparator());
            context.test.log(LogStatus.INFO," Response : " +responseData + System.lineSeparator());
            String responseDat=responseData.substring(1,responseData.length()-1);
            String[] responseArr=responseDat.split(",");
            for(int i=0;i<responseArr.length;i++){
                responseValues.put(responseArr[i].split(":")[0].replace("\"","").trim(),responseArr[i].split(":")[1].trim());
            }

        } catch (Exception var18) {
            logger.info("Response Failed" + System.lineSeparator());
            logger.info("Exception: " + var18 + System.lineSeparator());
            context.test.log(LogStatus.INFO,"Response Failed" + System.lineSeparator());
        }


        return responseValues;
    }


    /**
     * Using the method we can get JSON Values froom the response and stroing in Hashtable
     * @author  Praveen
     * @since 03-July-2020
     */

    public Hashtable<String,String> getJSONValue(String data){
        Hashtable<String,String> codeList=new Hashtable<String,String>();
        JSONParser parser = new JSONParser();
        try {
            JSONObject object= (JSONObject)parser.parse(data);
            JSONArray array=(JSONArray)object.get("cards");
            Iterator<JSONObject> objectList=array.iterator();
            while(objectList.hasNext()){
             JSONObject obj=   objectList.next();
             String code=(String)obj.get("code");
             String suit=(String)obj.get("suit");
             codeList.put(code,suit);
            }

        } catch (ParseException e) {
            e.printStackTrace();
        }
    return codeList;
    }
}
