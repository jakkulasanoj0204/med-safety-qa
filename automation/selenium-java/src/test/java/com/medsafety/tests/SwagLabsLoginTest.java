package com.medsafety.tests;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.*;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

public class SwagLabsLoginTest {

    private WebDriver driver;
    private WebDriverWait wait;

    @BeforeClass
    public void setUpClass() {
        WebDriverManager.chromedriver().setup();
    }

    @BeforeMethod
    public void setUp() throws InterruptedException {
        // Disable Chrome password manager popups
        ChromeOptions options = new ChromeOptions();
        Map<String, Object> prefs = new HashMap<>();
        prefs.put("credentials_enable_service", false);
        prefs.put("profile.password_manager_enabled", false);
        options.setExperimentalOption("prefs", prefs);

        driver = new ChromeDriver(options);
        driver.manage().window().maximize();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        driver.get("https://www.saucedemo.com/");
        Thread.sleep(2000); // pause to view login page
    }

    @Test
    public void validLogin_shouldNavigateToInventoryPage() throws InterruptedException {
        WebElement usernameInput = driver.findElement(By.id("user-name"));
        WebElement passwordInput = driver.findElement(By.id("password"));
        WebElement loginButton = driver.findElement(By.id("login-button"));

        usernameInput.sendKeys("standard_user");
        Thread.sleep(1000);

        passwordInput.sendKeys("secret_sauce");
        Thread.sleep(1000);

        loginButton.click();

        // Wait for inventory page to load
        wait.until(ExpectedConditions.urlContains("inventory.html"));
        Thread.sleep(2000); // so you can see it

        String currentUrl = driver.getCurrentUrl();
        Assert.assertTrue(
                currentUrl.contains("inventory.html"),
                "Expected to be on inventory page but was: " + currentUrl
        );
    }

    @Test
    public void invalidLogin_shouldShowErrorMessage() throws InterruptedException {
        WebElement usernameInput = driver.findElement(By.id("user-name"));
        WebElement passwordInput = driver.findElement(By.id("password"));
        WebElement loginButton = driver.findElement(By.id("login-button"));

        usernameInput.sendKeys("standard_user");
        Thread.sleep(1000);

        passwordInput.sendKeys("wrong_password");
        Thread.sleep(1000);

        loginButton.click();
        Thread.sleep(1500);

        WebElement errorMessage = driver.findElement(By.cssSelector("[data-test='error']"));
        String errorText = errorMessage.getText();

        Assert.assertTrue(
                errorText.toLowerCase().contains("username and password do not match"),
                "Unexpected error text: " + errorText
        );
    }

    @Test(enabled = false)
    public void validLogin_addItemToCart_andCheckout_shouldCompleteOrder() throws InterruptedException {
        // Step 1: Login with valid credentials
        WebElement usernameInput = driver.findElement(By.id("user-name"));
        WebElement passwordInput = driver.findElement(By.id("password"));
        WebElement loginButton = driver.findElement(By.id("login-button"));

        usernameInput.sendKeys("standard_user");
        Thread.sleep(800);

        passwordInput.sendKeys("secret_sauce");
        Thread.sleep(800);

        loginButton.click();

        // Wait until inventory page is loaded
        wait.until(ExpectedConditions.urlContains("inventory.html"));
        Thread.sleep(1500);

        // Step 2: Add an item to the cart (Sauce Labs Backpack)
        WebElement addToCartButton = driver.findElement(By.id("add-to-cart-sauce-labs-backpack"));
        addToCartButton.click();
        Thread.sleep(1500);

        // Step 3: Open the cart (click on the cart link)
        WebElement cartIcon = driver.findElement(By.className("shopping_cart_link"));
        cartIcon.click();

        // Wait for cart page & checkout button to be visible
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("checkout")));
        Thread.sleep(1500);

        // Step 4: Click Checkout
        WebElement checkoutButton = driver.findElement(By.id("checkout"));
        checkoutButton.click();
        Thread.sleep(1500);

        // Step 5: Fill in checkout information
        WebElement firstNameInput = driver.findElement(By.id("first-name"));
        WebElement lastNameInput = driver.findElement(By.id("last-name"));
        WebElement postalCodeInput = driver.findElement(By.id("postal-code"));

        firstNameInput.sendKeys("Sanoj");
        Thread.sleep(600);

        lastNameInput.sendKeys("Jakkula");
        Thread.sleep(600);

        postalCodeInput.sendKeys("01609");
        Thread.sleep(600);

        WebElement continueButton = driver.findElement(By.id("continue"));
        continueButton.click();

        // Wait for overview page
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("finish")));
        Thread.sleep(1500);

        // Step 6: Finish the order
        WebElement finishButton = driver.findElement(By.id("finish"));
        finishButton.click();

        // Wait for confirmation
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".complete-header")));
        Thread.sleep(1500);

        // Step 7: Assert that order is successful
        WebElement confirmationHeader = driver.findElement(By.cssSelector(".complete-header"));
        String confirmationText = confirmationHeader.getText();

        Assert.assertTrue(
                confirmationText.toLowerCase().contains("thank you for your order"),
                "Expected order confirmation, but got: " + confirmationText
        );
    }

    @AfterMethod
    public void tearDown() throws InterruptedException {
        Thread.sleep(2000); // pause to see final state
        if (driver != null) {
            driver.quit();
        }
    }
}
