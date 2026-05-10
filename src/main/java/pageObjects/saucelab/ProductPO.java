package pageObjects.saucelab;

import core.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import pageUIs.saucelab.ProductPageUI;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ProductPO extends BasePage {
    private WebDriver driver;
    public ProductPO(WebDriver driver){
        this.driver =driver;
    }

    public void sortBy(String sortCriteria) {
        waitElementClickable(driver, ProductPageUI.SORT_DROPDOWN);
        selectItemInDropDown(driver,ProductPageUI.SORT_DROPDOWN,sortCriteria);
    }

    public String getSortItemSelected(){
        waitElementVisible(driver,ProductPageUI.SORT_DROPDOWN);
        return getSelectedItemInDropdown(driver,ProductPageUI.SORT_DROPDOWN);
    }

    public boolean isProductNameSortAscending() {

        List<WebElement> productName=getListElement(driver, ProductPageUI.PRODUCT_NAME_TEXT);

        //Mảng A
        ArrayList<String> productList = new ArrayList<String>();

        for (WebElement product : productName){
            System.out.println(product.getText());
            productList.add(product.getText());
        }

        //Mảng B
        ArrayList<String> sortedList = new ArrayList<String>();
        for (String product: productList){
            sortedList.add(product);
        }

        Collections.sort(sortedList);

        return  productList.equals(sortedList);
    }

    public boolean isProductNameSortDescending() {
        List<WebElement> productName = getListElement(driver,ProductPageUI.PRODUCT_NAME_TEXT);

        ArrayList<String> productList = new ArrayList<String>();

        for (WebElement product : productName){
            System.out.println(product.getText());
            productList.add(product.getText());
        }

       ArrayList<String> sortedList = new ArrayList<String>();
        for (String product: productList){
            sortedList.add(product);
        }

        Collections.sort(sortedList);
        Collections.reverse(sortedList);

        return  productList.equals(sortedList);
    }

    public boolean isProductPriceSortAscending() {
        //Lấy ra các element
        List<WebElement> productPrice = getListElement(driver,ProductPageUI.PRODUCT_PRICE_TEXT);

        //Mảng A
        ArrayList<Float> productList = new ArrayList<Float>();

        //Vong lao lay productName lưu vào A
        for (WebElement product : productPrice){
            System.out.println(product.getText());
            productList.add(Float.parseFloat(product.getText().replace("$","")));
        }

        //Mảng B
        ArrayList<Float> sortedList = new ArrayList<Float>();
        for (Float product: productList){
            sortedList.add(product);
        }

        //Sort
        Collections.sort(sortedList);
        return  productList.equals(sortedList);
    }

    public boolean isProductPriceSortDescending() {
       List<WebElement> productPrice = getListElement(driver,ProductPageUI.PRODUCT_PRICE_TEXT);

       ArrayList<Float> productList = new ArrayList<Float>();

       for (WebElement product : productPrice){
           System.out.println(product.getText());
            productList.add(Float.parseFloat(product.getText().replace("$","")));
        }

        ArrayList<Float> sortedList = new ArrayList<Float>();
        for (Float product: productList){
            sortedList.add(product);
        }

        Collections.sort(sortedList);

        Collections.reverse(sortedList);

        return  productList.equals(sortedList);
    }
}