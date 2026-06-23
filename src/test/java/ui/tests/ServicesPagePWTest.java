package ui.tests;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import com.microsoft.playwright.Page;

import ui.base.BaseTest;
import ui.pages.ServicesPage;
import utils.LogUtil;
import java.nio.file.Paths;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;

@Epic("Parabank")
@Feature("Navegación")
public class ServicesPagePWTest  extends BaseTest{

    @Tag("ui")
    @Tag("smoke")
    @Story("Acceso a Services")
    @Description("Valida la navegación hacia la página Services")
    @DisplayName("Navegación a página Services")
    @Test
    public void shouldOpenServicesPage(){

        ServicesPage servicesPage  = new ServicesPage(page);
        servicesPage.openHomePage();
        page.screenshot(new Page.ScreenshotOptions()
        .setPath(Paths.get("target/homepage.png")));

        LogUtil.info("Página principal abierta");

        servicesPage .clickServices();
        LogUtil.info("Click en services");
       
        if (!servicesPage.getCurrentUrl().contains("services.htm")){
            throw new RuntimeException("NO se abrio la página services");
        }

        LogUtil.info("URL correcta:" + servicesPage.getCurrentUrl());

        if (!servicesPage.isServicesPageDisplayed()){
            
            throw new RuntimeException("NO se encontró el título de servicios");
        }

        page.waitForTimeout(5000);


    }
    
    
}
