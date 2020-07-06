package com.finra.qa.sqm.utilities;


import com.finra.qa.sqm.testrunner.TestContext;
import com.finra.qa.sqm.testrunner.MyContainer;
import com.finra.qa.sqm.testrunner.RunCukesByCompositionTest;
import com.finra.qa.sqm.testrunner.TestContext;
import org.apache.log4j.Logger;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.streaming.SXSSFCell;
import org.apache.poi.xssf.streaming.SXSSFRow;
import org.apache.poi.xssf.streaming.SXSSFSheet;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.*;


/**
 * This file is Created by Praveen on 7/3/2020
 */

public class ExcelData {


    public FileInputStream fileIS = null;
    public XSSFWorkbook workbook = null;

    public TestContext context;
    public XSSFSheet sheet = null;
    public XSSFRow row = null;
    public XSSFCell cell = null;

    public XSSFRow rowHeader = null;
    public FileOutputStream output_file = null;
    public File excelPath = null;
    final static Logger logger = Logger.getLogger(ExcelData.class);
    public int responseStatus;
    public ConfigData configData;
    RunCukesByCompositionTest runCukesByCompositionTest;


    public ExcelData() {

        context = (TestContext) MyContainer.getInstance(Thread.currentThread().getId());
        configData = new ConfigData();
        runCukesByCompositionTest = new RunCukesByCompositionTest();
    }



    public void readDataFromExcel() {
        Hashtable<String, ArrayList<String>>  cardsData= new Hashtable<String,ArrayList<String>>();
        ArrayList<String> cardSet=null;
        String cardType=null;
        try {

            String fileName =  "TestData.xlsx";
            ClassLoader classLoader = this.getClass().getClassLoader();
            File path = new File(classLoader.getResource("InputData/" + fileName).getFile());
            boolean flag = path.exists();
            if (!flag) {
                path = new File(classLoader.getResource("InputData/" + fileName).getFile());
            }

            this.fileIS = new FileInputStream(path);
            this.workbook = new XSSFWorkbook(this.fileIS);
            this.sheet = this.workbook.getSheetAt(0);
            int rowCount=this.sheet.getPhysicalNumberOfRows();
            int columnCount=this.sheet.getRow(0).getPhysicalNumberOfCells();
            for(int i=1;i<columnCount;i++){
                 cardType=this.sheet.getRow(0).getCell(i).getStringCellValue();
                 cardSet=new ArrayList<String>();
                for(int j=1;j<rowCount;j++){
                  cardSet.add(this.sheet.getRow(j).getCell(i).getStringCellValue());
                }
                cardsData.put(cardType,cardSet);
            }
            context.cardsData=cardsData;

        } catch (Exception var7) {
            logger.info("Exception: " + var7);
        }

    }



}
