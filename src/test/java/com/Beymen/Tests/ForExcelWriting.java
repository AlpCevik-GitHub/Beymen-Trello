package com.Beymen.Tests;

import com.Beymen.Pages.BasePage;
import com.Beymen.Utilities.BrowserUtils;
import com.Beymen.Utilities.Driver;
import com.Beymen.Utilities.ExcelUtil;
import com.Beymen.Utilities.TestBase;
import com.fasterxml.jackson.databind.ser.Serializers;
import org.junit.Test;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class ForExcelWriting extends BasePage {


    public  ForExcelWriting(){
        PageFactory.initElements(Driver.get(), this);
    }

    @FindBy(xpath = "(//tbody/tr)[1]")
    public WebElement firmaFirst;
    @FindBy(xpath = "//tbody//tr//td[@class='hidden-print']//a")
    public List<WebElement> showList;
    @FindBy(xpath = "(//tbody//tr//td[@class='hidden-print'])[1]")
    public WebElement showListFirst;

    @FindBy(xpath = "(//tbody//td)[1]")
    public WebElement firmaName;

    @FindBy(xpath = "((//tbody//td)[2]//a)[1]")
    public WebElement firmaTel;
    @FindBy(xpath = "((//tbody//td)[2]//a)[2]")
    public WebElement firmaWeb;
    @FindBy(xpath = "(//tbody//td)[3]")
    public WebElement firmaLocation;

    @Test
    public void test() {
        ExcelUtil qa3short = new ExcelUtil("/Users/alp/IdeaProjects/Beymen-Trello/searchBox.xlsx", "Sheet1");
        mainPage();
        //showListFirst.click();
        //System.out.println("firmaName.getText() = " + firmaName.getText());
        //System.out.println("firmaCom.getText() = " + firmaTel.getText());
        //System.out.println("firmaCom.getText() = " + firmaWeb.getText());
        //System.out.println("firmaLocation.getText() = " + firmaLocation.getText());
        int i = 1;
        for (int i1 = 0; i1 < 2; i1++) {
            showList.get(i1).click();
            BrowserUtils.waitFor(2);
            qa3short.setCellData(firmaName(firmaName.getText()), i,1);
            qa3short.setCellData(address(firmaName.getText()), i,2);
            qa3short.setCellData(tel(firmaTel.getText()), i,3);
            qa3short.setCellData(web(firmaWeb.getText()), i,4);
            i++;
            Driver.get().navigate().back();
            BrowserUtils.waitFor(2);
        }
    }

    public  String tel(String str){
        char[] charArray = str.toCharArray();

        String result = "";

        for (int i = 0; i < charArray.length; i++) {

            if (Character.isDigit(charArray[i]) ){
                result += charArray[i];
            }


        }
        result = result.trim();
        return result;
    }

    public  String web(String str){
        int firstIndex = str.indexOf("h");
        String result = str.substring(firstIndex, str.length()-1);
        return result;
    }
    public  String firmaName(String str){
        int firstIndex = str.indexOf("");
        int lastIndex = str.lastIndexOf("ŞTİ.");
        String result = str.substring(firstIndex, lastIndex);
        return result;
    }
    public  String address(String str){
        int firstIndex = str.indexOf("ŞTİ.");
        String result = str.substring(firstIndex, str.length()-1);
        return result;
    }
}
