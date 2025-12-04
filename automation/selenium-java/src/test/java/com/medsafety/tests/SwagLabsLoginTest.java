package com.medsafety.tests;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.*;

public class SwagLabsLoginTest {

    private WebDriver driver;

    @BeforeClass
    public void setUpClass() {
        WebDriverManager.chromedriver().setup();
    }

    @BeforeMethod
    public void setUp() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://www.saucedemo.com/");
    }

    @Test
    public void validLogin_shouldNavigateToInventoryPage() {
        // Standard valid credentials from Swag Labs
        WebElement usernameInput = driver.findElement(By.id("user-name"));
        WebElement passwordInput = driver.findElement(By.id("password"));
        WebElement loginButton = driver.findElement(By.id("login-button"));

        usernameInput.sendKeys("standard_user");
        passwordInput.sendKeys("secret_sauce");
        loginButton.click();

        // After login, URL should contain /inventory.html
        String currentUrl = driver.getCurrentUrl();
        Assert.assertTrue(
                currentUrl.contains("inventory.html"),
                "Expected to be on inventory page but was: " + currentUrl
        );
    }

    @Test
    public void invalidLogin_shouldShowErrorMessage() {
        WebElement usernameInput = driver.findElement(By.id("user-name"));
        WebElement passwordInput = driver.findElement(By.id("password"));
        WebElement loginButton = driver.findElement(By.id("login-button"));

        usernameInput.sendKeys("standard_user");
        passwordInput.sendKeys("wrong_password");
        loginButton.click();

        WebElement errorMessage = driver.findElement(By.cssSelector("[data-test='error']"));
        String errorText = errorMessage.getText();

        Assert.assertTrue(
                errorText.toLowerCase().contains("username and password do not match"),
                "Unexpected error text: " + errorText
        );
    }

    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
