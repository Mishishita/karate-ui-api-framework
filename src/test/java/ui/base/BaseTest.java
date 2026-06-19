package ui.base;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;

import utils.LogUtil;

public class BaseTest {

    protected Playwright playwright;
    protected Browser browser;
    protected Page page;

    // Preparar la prueba
    @BeforeEach
    protected void setUp() {

        playwright = Playwright.create();

        //browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
        boolean isCI = System.getenv("CI") != null;

        browser = playwright.chromium().launch(
                new BrowserType.LaunchOptions()
                        .setHeadless(isCI));

        page = browser.newPage();

    }

    // Desmontar el escenario
    @AfterEach
    protected void tearDown() {
        if (browser != null) {
            browser.close();
        }
        if (playwright != null) {
            playwright.close();
            
        }
        LogUtil.info("Navegador cerrado");
    }

}
