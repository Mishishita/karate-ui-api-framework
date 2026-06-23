package ui.pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

import utils.ConfigReader;

public class ServicesPage {

    private final Page page;

    public ServicesPage(Page page){
        this.page = page;

    }
    

    public void openHomePage(){
        page.navigate(ConfigReader.getBaseUrl());
    }

    public void clickServices(){
        
       page.locator("#headerPanel a")
        .filter(new Locator.FilterOptions()
        .setHasText("Services"))
        .click();
    }

    public boolean isServicesPageDisplayed(){
        
        page.waitForSelector(
            "text=Available Bookstore SOAP services:"
        );

        return page.locator(
            "text=Available Bookstore SOAP services:"
        ).isVisible();

    }

    public String getCurrentUrl(){
        return page.url();
    }

}
