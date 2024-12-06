package stepDefinitions;

import io.cucumber.java.en.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.Arrays;
import java.util.List;


import static hooks.TestHooks.driver;
import static org.junit.Assert.assertEquals;
import static org.openqa.selenium.By.xpath;
import hooks.TestHooks;

public class HepsiburadaStepDefinitions {
  //  WebDriver driver;
    String productPrice;

    @Given("Kullanıcı {string} adresine gider")
    public void kullanıcı_adresine_gider(String url) {
        driver.get(url);
        driver.manage().window().maximize();
    }


    @When("Kullanıcı {string} kategorisine gider")
    public void kullanıcı_kategorisine_gider(String kategoriYolu) {
        String[] kategoriler = kategoriYolu.split(" -> ");
        System.out.println(Arrays.toString(kategoriler));

        WebDriverWait wait = new WebDriverWait(driver, 10);
        for (String kategori : kategoriler) {
                 WebElement element = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[contains(text(), '" + kategori + "')]")));
                ((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);

        }

    }

    @When("Kullanıcı {string} ve {string} filtrelerini seçer")
    public void kullanıcı_ve_filtrelerini_seçer(String marka, String ekranBoyutu) {
        driver.findElement(xpath("//label[contains(text(), '" + marka + "')]")).click();
        driver.findElement(xpath("//label[contains(text(), '" + ekranBoyutu + "')]")).click();
    }

    @When("Kullanıcı çıkan sonuçlardan en yüksek fiyatlı ürüne tıklar")
    public void kullanıcı_çıkan_sonuçlardan_en_yüksek_fiyatlı_ürüne_tıklar() {
        List<WebElement> ürünler = driver.findElements(By.cssSelector(".product-list .product-detail"));
        WebElement enPahalıÜrün = ürünler.get(ürünler.size() - 1);
        enPahalıÜrün.click();


        WebElement fiyatElementi = driver.findElement(By.cssSelector(".price"));
        productPrice = fiyatElementi.getText();
    }

    @When("Kullanıcı {string} butonuna tıklar")
    public void kullanıcı_butonuna_tıklar(String butonMetni) {
        driver.findElement(xpath("//button[contains(text(), '" + butonMetni + "')]")).click();
    }

    @Then("Ürün sepete eklenir ve fiyatının ürün detay sayfasıyla aynı olduğunu doğrular")
    public void ürün_sepete_eklenir_ve_fiyatının_ürün_detay_sayfasıyla_aynı_olduğunu_doğrular() {
        driver.findElement(By.id("shoppingCart")).click();
        WebElement sepetFiyatıElementi = driver.findElement(By.cssSelector(".total-price"));
        String sepetFiyatı = sepetFiyatıElementi.getText();
        assertEquals("Ürün fiyatı sepette yanlış!", productPrice, sepetFiyatı);
        driver.quit();
    }
}
