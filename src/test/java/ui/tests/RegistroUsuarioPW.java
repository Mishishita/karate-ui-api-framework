package ui.tests;

import ui.base.BaseTest;
import ui.data.RegisterUserData;
import ui.data.factory.RegisterUserDataFactory;
import ui.pages.RegisterPage;

import utils.LogUtil;

public class RegistroUsuarioPW extends BaseTest {

    public static void main(String[] args) {

        RegistroUsuarioPW test = new RegistroUsuarioPW();
        test.setUp();

        RegisterPage registerPage = new RegisterPage(test.page);

        test.page.navigate("https://parabank.parasoft.com/parabank/index.htm");
        LogUtil.info("Página abierta correctamente");

        registerPage.openRegistrationForm();
        LogUtil.info("Click en Register");

        RegisterUserData userData = RegisterUserDataFactory.createDefaultUser();
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

        test.page.waitForTimeout(5000);

        test.tearDown();

        LogUtil.info("Navegador cerrado");

    }
}
