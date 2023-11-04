package com.Beymen.Tests;

import com.Beymen.Pages.BasePage;
import groovy.util.logging.Slf4j;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Test;
import org.openqa.selenium.Keys;


@Slf4j
public class BeymenTestCaseStudy extends BasePage {
    Logger logger = LogManager.getLogger(this.getClass());

    @Test
    public void senaryo_Beymen() {

        extentLogger = report.createTest("Beymen");

        extentLogger.info("www.beymen.com sitesi açılır");
        mainPage();
        logger.info("www.beymen.com sitesi açılır");
       // logger.error("www.beymen.com sitesi açılmadı.");

        extentLogger.info("Ana sayfanın açıldığı kontrol edilir.");
        checkMainPage();
        logger.info("Ana sayfanın açıldığı kontrol edildi.");
        //logger.error("Ana sayfanın açıldığı kontrol edilmedi.");

        extentLogger.info("Arama kutucuğuna şort kelimesi girilir.(Not = Şort kelimesi excel dosyası üzerinden 1 sütun 1 satırdan alınarak yazılmalıdır. )");
        inputSearchBox(readExcelFile()[0]);
        logger.info("Arama kutucuğuna şort kelimesi girilir.(Not = Şort kelimesi excel dosyası üzerinden 1 sütun 1 satırdan alınarak yazıldı. )");
       // logger.error("Arama kutucuğuna şort kelimesi girilir.(Not = Şort kelimesi excel dosyası üzerinden 1 sütun 1 satırdan alınarak yazılmadı. )");

        extentLogger.info("Arama kutucuğuna girilen “şort” kelimesi silinir.");
        deleteSearchBox();
        logger.info("Arama kutucuğuna girilen “şort” kelimesi silindi.");
        //logger.error("Arama kutucuğuna girilen “şort” kelimesi silinmedi.");

        extentLogger.info("Arama kutucuğuna “gömlek” kelimesi girilir.(Not = Gömlek kelimesi excel dosyası üzerinden 2 sütun 1 satırdan alınarak yazılmalıdır. )");
        inputSearchBox1(readExcelFile()[1]);
        logger.info("Arama kutucuğuna “gömlek” kelimesi girilir.(Not = Gömlek kelimesi excel dosyası üzerinden 2 sütun 1 satırdan alınarak yazıldı. )");
        //logger.error("Arama kutucuğuna “gömlek” kelimesi girilir.(Not = Gömlek kelimesi excel dosyası üzerinden 2 sütun 1 satırdan alınarak yazılmadı. )");

        extentLogger.info("Klavye üzerinden “enter” tuşuna bastırılır");
        searchBox.sendKeys(Keys.ENTER);
        logger.info("Klavye üzerinden “enter” tuşuna bastırıldı");
        //logger.error("Klavye üzerinden “enter” tuşuna bastırılmadı");

        extentLogger.info("Sonuca göre sergilenen ürünlerden rastgele bir ürün seçilir.");
        getRandomProduct();
        logger.info("Sonuca göre sergilenen ürünlerden rastgele bir ürün seçildi.");
        //logger.error("Sonuca göre sergilenen ürünlerden rastgele bir ürün seçilmedi.");

        extentLogger.info("Seçilen ürünün ürün bilgisi ve tutar bilgisi txt dosyasına yazılır.");
       productInformation();
        logger.info("Seçilen ürünün ürün bilgisi ve tutar bilgisi txt dosyasına yazıldı.");
        //logger.error("Seçilen ürünün ürün bilgisi ve tutar bilgisi txt dosyasına yazılmadı.");

        extentLogger.info("Seçilen ürün sepete eklenir.");
        addToCart();
        logger.info("Seçilen ürün sepete eklendi.");
        //logger.error("Seçilen ürün sepete eklenmedi.");

        extentLogger.info("Ürün sayfasındaki fiyat ile sepette yer alan ürün fiyatının doğruluğu karşılaştırılır.");
        matchPrice();
        logger.info("Ürün sayfasındaki fiyat ile sepette yer alan ürün fiyatının doğruluğu karşılaştırıldı.");
        //logger.error("Ürün sayfasındaki fiyat ile sepette yer alan ürün fiyatının doğruluğu karşılaştırılmadı.");

        extentLogger.info("Adet arttırılarak ürün adedinin 2 olduğu doğrulanır.");
        increaseNumberOfProduct("2 adet");
        logger.info("Adet arttırılarak ürün adedinin 2 olduğu doğrulandı.");
        //logger.error("Adet arttırılarak ürün adedinin 2 olduğu doğrulanmadı.");

        extentLogger.info("Ürün sepetten silinerek sepetin boş olduğu kontrol edilir.");
        deleteProduct();
        logger.info("Ürün sepetten silinerek sepetin boş olduğu kontrol edildi.");
        //logger.error("Ürün sepetten silinerek sepetin boş olduğu kontrol edilmedi.");







    }

}
