package ui.tests;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;

import ui.data.RegisterUserData;
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

            RegisterUserData userData = new RegisterUserData();
            userData.setFirstName("Maria");
            userData.setLastName("Tinoco");
            userData.setStreet("Lima");
            userData.setCity("ATE");

            userData.setState("PE");
            userData.setZipCode("054");
            userData.setPhoneNumber("123456789");
            userData.setSsn("123456");

            userData.setUsername("test" + System.currentTimeMillis());
            userData.setPassword("test123");
            userData.setRepeatedPassword("test123");

            registerPage.registerUser(userData);

            LogUtil.info("Datos ingresados");
            registerPage.clickRegister();
            LogUtil.info("Registro exitoso");

            if (!registerPage.isUserRegistered(userData.getUsername())) {
                throw new RuntimeException(
                        "Usuario no encontrado en pantalla");
            }

            String welcomeMessage = registerPage.getWelcomeMessage();
            LogUtil.info("Mensaje: " + welcomeMessage);

            LogUtil.info("Nombre: " + userData.getFirstName());
            LogUtil.info("Apellido: " + userData.getLastName());
            LogUtil.info("Usuario generado: " + userData.getUsername());

            page.waitForTimeout(5000);

            browser.close();
            LogUtil.info("Navegador cerrado");

        } catch (Exception e) {

            LogUtil.info("Error: " + e.getMessage());
            throw new RuntimeException(e);
        }
    }
}
