package ui.base;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

import org.junit.jupiter.api.extension.ExtendWith;
import ui.extensions.ScreenshotOnFailureExtension;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;

import utils.LogUtil;
import io.qameta.allure.Allure;

import java.io.ByteArrayInputStream;


@ExtendWith(ScreenshotOnFailureExtension.class)
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

    public void takeScreenshot(String testName) {

        String timestamp =
                java.time.LocalDateTime.now()
                        .format(
                                java.time.format.DateTimeFormatter
                                        .ofPattern("yyyyMMdd_HHmmss"));

        String fileName =
                testName + "_" + timestamp;

        page.screenshot(
                new Page.ScreenshotOptions()
                        .setPath(
                                java.nio.file.Paths.get(
                                        "target/screenshots/"
                                                + fileName
                                                + ".png")));

        LogUtil.info(
                "Screenshot guardado: "
                        + fileName);
    }

    // Desmontar el escenario
    @AfterEach
    public void tearDown() {
        if (browser != null) {
            browser.close();
        }
        if (playwright != null) {
            playwright.close();
            
        }
        LogUtil.info("Navegador cerrado");
    }


    public void attachScreenshotToAllure(String name) {

        byte[] screenshot = page.screenshot();

        Allure.addAttachment(name,new ByteArrayInputStream(screenshot));

        LogUtil.info("Screenshot adjuntado a Allure: "+ name);
   }
}
