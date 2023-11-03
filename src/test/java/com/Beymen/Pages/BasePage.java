package com.Beymen.Pages;

import com.Beymen.Utilities.*;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.Assert;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import java.io.*;
import java.util.List;
import java.util.Random;

public class BasePage extends TestBase {

    public BasePage() {
        PageFactory.initElements(Driver.get(), this);
    }

    String prodPrice;


   // @FindBy(xpath = "//input[@class='default-input o-header__search--input']")
    //public WebElement searchBox;

    @FindBy(xpath = "//input[@class='o-header__search--input']")
    public WebElement searchBox;

    @FindBy(xpath = "//input[@id='o-searchSuggestion__input']")
    public WebElement nextSearchBox;

    @FindBy(xpath = "//button[@class='o-header__search--close -hasButton']")
    public WebElement clearSearchBox;
    @FindBy(css = "button#onetrust-accept-btn-handler")
    public WebElement cookies_Loc;

    @FindBy(xpath = "//*[@id=\"genderManButton\"]")
    public  WebElement genderSelection;

    @FindBy(xpath = "(//a[@class='o-productCard__link'])[1]")
    public WebElement randomProduct;

    @FindBy(xpath = "//*[@class='o-productDetail__description']")
    public WebElement productName;

    @FindBy(css = "#priceNew")
    public WebElement productPrice;

    @FindBy(xpath = "//*[contains(@class, 'm-variation__item')]")
    public List<WebElement> sizeOfBody;

    @FindBy(css = "#addBasket")
    public WebElement addBasketButton;

    @FindBy(xpath = "//*[@class='m-notification__button btn']")
    public WebElement goToBasketButton;

    @FindBy(css = ".m-productPrice__salePrice")
    public WebElement basketPrice;

    @FindBy(xpath = "//*[@class='a-selectControl -small']/.")
    public WebElement quantityDropDown;

    @FindBy(xpath = "//*[@class='a-selectControl -small']/./option[@value=\"2\"]")
    public WebElement dropdownElement;

    @FindBy(xpath = "//button[@id='removeCartItemBtn0-key-0']")
    public WebElement deleteButton;

    @FindBy(xpath = "//*[text()='Sepetinizde Ürün Bulunmamaktadır']")
    public WebElement getMessage;

    public void mainPage() {
        Driver.get().get(ConfigurationReader.get("url"));

        //Açılıştaki cookies kapatılır
        BrowserUtils.waitForClickablility(cookies_Loc, 5);
        cookies_Loc.click();
        genderSelection.click();
    }

    public void checkMainPage() {
        String actualTitle = Driver.get().getTitle();
        String expectedTitle = "Beymen.com – Türkiye’nin Tek Dijital Lüks Platformu";
        //Anasayfada olduğumuzu assert ediyoruz
        Assert.assertEquals(expectedTitle, actualTitle);
    }

    public void inputSearchBox(String str) {

//        searchBox.click();
        searchBox.sendKeys(str);

        BrowserUtils.waitFor(2);
    }

    public void inputSearchBox1(String str) {

//        searchBox.click();
        nextSearchBox.sendKeys(str);

        BrowserUtils.waitFor(2);
    }

    public void deleteSearchBox() {
        clearSearchBox.click();
        BrowserUtils.waitFor(2);
    }

    public String[] readExcelFile() {

        ExcelUtil qa3short = new ExcelUtil("/Users/alp/IdeaProjects/BeymenTest/searchBox.xlsx", "Sheet1");

        String[][] dataArray = qa3short.getDataArray();
        String[] allURL = new String[qa3short.columnCount()];

        for (int i = 0; i < allURL.length; i++) {
            allURL[i] = dataArray[0][i].trim();
        }
        return allURL;
    }

        public void getRandomProduct () {

            Random rn = new Random();
            //int a = rn.nextInt(randomProduct.size());
            BrowserUtils.waitFor(1);
           // a = 1; //Random olarak productları seçiyor ancak beden ölçüsü kalmayan procut varsa fail oluyor o yüzden beden sayısı olan bir product verdim
            randomProduct.click();
            BrowserUtils.waitFor(2);
        }

        public void productInformation () {

            try {
                FileWriter fileWriter = new FileWriter("prod.txt");
                PrintWriter printWriter = new PrintWriter(fileWriter);
                printWriter.println("Ürün bilgisi : " + productName.getText());
                printWriter.println("Fiyat : " + productPrice.getText());
                printWriter.close();

            } catch (IOException e) {
                e.printStackTrace();
            }
        }


        public void addToCart () {
            BrowserUtils.waitForVisibility(addBasketButton, 5);
            for (int i = 0; i < sizeOfBody.size(); i++) {
                sizeOfBody.get(i).click();

                if (sizeOfBody.get(i).getAttribute("class").contains("active")) {
                    break;
                }
            }
            prodPrice = productPrice.getText().substring(0,productPrice.getText().length()-3);
            addBasketButton.click();
            BrowserUtils.waitForClickablility(goToBasketButton, 5);
            goToBasketButton.click();
            BrowserUtils.waitFor(1);
        }

        public void matchPrice () {
            BrowserUtils.waitForVisibility(basketPrice, 5);
            String cartPrice = basketPrice.getText().substring(0,basketPrice.getText().length()-6);

            Assert.assertEquals(prodPrice, cartPrice);
        }

        public void increaseNumberOfProduct (String numberOfProduct){

//            Actions builder = new Actions(driver);
//            builder.clickAndHold(quantityDropDown).perform();
//            BrowserUtils.waitFor(2);
//            builder.sendKeys(Keys.ARROW_DOWN ).perform();
//            BrowserUtils.waitFor(2);
//            builder.sendKeys(Keys.ARROW_DOWN ).perform();
//            BrowserUtils.waitFor(2);
//            builder.sendKeys(Keys.ENTER ).perform();

            //JavascriptExecutor js = (JavascriptExecutor) driver;
            //js.executeScript("arguments[0].click();", quantityDropDown);
            //BrowserUtils.waitFor(1);
            //js.executeScript("arguments[0].click();", dropdownElement);
            Select select = new Select(quantityDropDown);
             select.selectByVisibleText(numberOfProduct);



            String actualOption = select.getFirstSelectedOption().getText();
            System.out.println(actualOption);
            System.out.println(numberOfProduct);

            Assert.assertEquals(numberOfProduct, actualOption);

        }

        public void deleteProduct () {
            BrowserUtils.waitForClickablility(deleteButton, 5);
            deleteButton.click();

            String expectedMessage = "SEPETINIZDE ÜRÜN BULUNMAMAKTADIR";
            String actualMessage = getMessage.getText();

            Assert.assertEquals(expectedMessage, actualMessage);
        }

    }



