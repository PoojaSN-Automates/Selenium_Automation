package pages;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import base.BasePage;


public class ProductsSortPage extends BasePage {
	
	 private WebDriver driver;
	 
	 private By allsorting = By.xpath("//select[@class='product_sort_container']/option");
	 
	 // Sorting dropdown
	    private By sortingDropdown = By.className("product_sort_container");

	    // Product names
	    private By productNames = By.className("inventory_item_name");

	    // Product prices
	    private By productPrices = By.className("inventory_item_price");


	public ProductsSortPage(WebDriver driver) {
		
		super(driver);
		this.driver=driver;
	}
	
	

	public List<String> getallProductSorting() {
		
		List<WebElement> allsortingoptions= driver.findElements(allsorting);
		
		List<String> actualallsorting = new ArrayList<>();
		
		for(WebElement sortings:allsortingoptions ) {
			
			System.out.println(sortings.getText());
			actualallsorting.add(sortings.getText());
		}
		return actualallsorting;
	}
	
	
	 // Select sorting option
    public void selectSorting(
            String visibleText) {

        Select select =
                new Select(
                driver.findElement(
                        sortingDropdown));

        select.selectByVisibleText(
                visibleText);
    }



    // Get all product names
    public List<String> getAllProductNames() {

        List<WebElement> products =
                driver.findElements(
                        productNames);

        List<String> names =
                new ArrayList<>();

        for(WebElement product
                : products) {

            names.add(
                    product.getText());
        }

        return names;
    }



    // Get all product prices
    public List<Double> getAllProductPrices() {

        List<WebElement> prices =
                driver.findElements(
                        productPrices);

        List<Double> actualPrices =
                new ArrayList<>();

        for(WebElement price
                : prices) {

            String priceText =
                    price.getText()
                    .replace("$", "");

            actualPrices.add(
                    Double.parseDouble(
                            priceText));
        }

        return actualPrices;
    }



    // Verify A-Z sorting
    public boolean verifyNameAToZ() {

        List<String> actualProducts =
                getAllProductNames();

        List<String> expectedProducts =
                new ArrayList<>(
                        actualProducts);

        Collections.sort(
                expectedProducts);

        return actualProducts.equals(
                expectedProducts);
    }



    // Verify Z-A sorting
    public boolean verifyNameZToA() {

        List<String> actualProducts =
                getAllProductNames();

        List<String> expectedProducts =
                new ArrayList<>(
                        actualProducts);

        Collections.sort(
                expectedProducts,
                Collections.reverseOrder());

        return actualProducts.equals(
                expectedProducts);
    }



    // Verify Price Low to High
    public boolean verifyPriceLowToHigh() {

        List<Double> actualPrices =
                getAllProductPrices();

        List<Double> expectedPrices =
                new ArrayList<>(
                        actualPrices);

        Collections.sort(
                expectedPrices);

        return actualPrices.equals(
                expectedPrices);
    }



    // Verify Price High to Low
    public boolean verifyPriceHighToLow() {

        List<Double> actualPrices =
                getAllProductPrices();

        List<Double> expectedPrices =
                new ArrayList<>(
                        actualPrices);

        Collections.sort(
                expectedPrices,
                Collections.reverseOrder());

        return actualPrices.equals(
                expectedPrices);
    }
}
