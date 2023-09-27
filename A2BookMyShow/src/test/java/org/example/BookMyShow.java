package org.example;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class BookMyShow {

    public WebDriver driver;
    public String baseUrl = "https://in.bookmyshow.com/explore/home/chennai";

    @BeforeTest
    public void setup() throws InterruptedException {
        driver = new ChromeDriver();
        driver.get(baseUrl);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
    }

    @Test
    public void check()
    {
        List<WebElement> hyperlinks = driver.findElements(By.xpath("//a[@href]"));
        System.out.println("The count of hyper links present in the page- " + hyperlinks.size());
    }

    @AfterTest
    public void closeBrowser()
    {
        driver.close();
    }
}
