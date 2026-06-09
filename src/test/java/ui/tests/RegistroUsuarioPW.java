package ui.tests;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
import ui.pages.RegisterPage;

import utils.LogUtil;

public class RegistroUsuarioPW {

    public static void main(String[] args) {

        try (Playwright playwright = Playwright.create()) {

            Browser browser = playwright.chromium().launch(
                    new BrowserType.LaunchOptions().setHeadless(false));

            Page page = browser.newPage();

            RegisterPage registerPage = new RegisterPage(page);

            page.navigate("https://parabank.parasoft.com/parabank/index.htm");
            LogUtil.info("Página abierta correctamente");

            registerPage.openRegistrationForm();
            LogUtil.info("Click en Register");

            registerPage.enterFirstName("Maria");
            registerPage.enterLastName("Tinoco");
            registerPage.enterStreet("Lima");
            registerPage.enterCity("Ate");

            registerPage.enterState("PE");
            registerPage.enterZipCode("054");
            registerPage.enterPhoneNumber("123456789");
            registerPage.enterSSn("123456");

            long timestamp = System.currentTimeMillis();
            String username = "test" + timestamp;

            registerPage.enterUsername(username);
            registerPage.enterPassword("test123");
            registerPage.enterRepeatedPassword("test123");

            LogUtil.info("Datos ingresados");
            registerPage.clickRegister();
            LogUtil.info("Registro exitoso");

            if (!registerPage.isUserRegistered(username)) {
                throw new RuntimeException(
                        "Usuario no encontrado en pantalla");
            }

            String welcomeMessage = registerPage.getWelcomeMessage();
            LogUtil.info("Mensaje: " + welcomeMessage);

            LogUtil.info("Nombre: Maria");
            LogUtil.info("Apellido: Tinoco");
            LogUtil.info("Usuario generado: " + username);
            LogUtil.info("Validación OK. Usuario encontrado: " + username);

            page.waitForTimeout(5000);

            browser.close();
            LogUtil.info("Navegador cerrado");

        } catch (Exception e) {

            LogUtil.info("Error: " + e.getMessage());
            throw new RuntimeException(e);
        }
    }
}
