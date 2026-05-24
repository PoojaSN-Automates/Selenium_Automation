package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import base.BasePage;

public class HomePage extends BasePage {

    WebDriver driver;

    private By title = By.className("title");

    private By badgecount = By.className("shopping_cart_badge");

    private By clickcart = By.className("shopping_cart_link");

    
    public HomePage(WebDriver driver) {

        super(driver);

        this.driver = driver;
    }



    public boolean isLoginSuccessfull() {

        return driver.getCurrentUrl()
                .contains("inventory.html");
    }



    public boolean isProductPageDisplayed() {

        return driver.getCurrentUrl()
                .contains("inventory.html") && isDisplayed(title);
    }



    public void addToCart(String productName) {

        By addtocart = By.xpath(

        "//div[text()='"
        + productName +
        "']"

        + "/ancestor::div[@class='inventory_item']"

        + "//button");

        click(addtocart);
    }



    public String getCartCount() {

        try {

            return getText(badgecount);

        } catch(Exception e) {

            return "0";
        }
    }



    public void addMultipleProducts(
            String products[]) {

        for(String product : products) {

            addToCart(product);
        }
    }



    public void clickOncart() {

        click(clickcart);
    }



    public void removeProduct(
            String productName) {

        By removeBtn = By.xpath(

        "//div[text()='"
        + productName +
        "']"

        + "/ancestor::div[@class='inventory_item']"

        + "//button");

        click(removeBtn);
    }
}
//to display homepage or any page title check here
