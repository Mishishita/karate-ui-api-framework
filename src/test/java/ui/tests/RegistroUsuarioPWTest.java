package ui.tests;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import ui.base.BaseTest;
import ui.data.UserData;
import ui.data.factory.RegisterUserDataFactory;
import ui.pages.RegisterPage;
import utils.ConfigReader;
import utils.LogUtil;
import utils.TestDataExporter;

import io.qameta.allure.Description;


@Epic("Parabank")
@Feature("Registro de Usuarios")
public class RegistroUsuarioPWTest extends BaseTest {

    @Tag("ui")
    @Tag("smoke")
    @Story("Alta de usuario")
    @Description("Valida el registro exitoso de un nuevo usuario en Parabank")
    @DisplayName("Registro exitoso de usuario")
    @Test
    public void shouldRegisterUser() {


        RegisterPage registerPage = new RegisterPage(page);

        page.navigate(ConfigReader.getBaseUrl());
        LogUtil.info("Página abierta correctamente");

        registerPage.openRegistrationForm();
        LogUtil.info("Click en Register");

        UserData userData = RegisterUserDataFactory.createDefaultUser();
        registerPage.registerUser(userData);
        LogUtil.info("Datos ingresados");
        registerPage.clickRegister();

        LogUtil.info("Registro exitoso");

        if (!registerPage.isUserRegistered(userData.getUsername())) {
            throw new RuntimeException(
                    "Usuario no encontrado en pantalla");
        }
        TestDataExporter.exportUser(userData);
        LogUtil.info("Credenciales exportadas correctamente");

        String welcomeMessage = registerPage.getWelcomeMessage();
        LogUtil.info("Mensaje: " + welcomeMessage);

        LogUtil.info("Nombre: " + userData.getFirstName());
        LogUtil.info("Apellido: " + userData.getLastName());
        LogUtil.info("Usuario generado: " + userData.getUsername());

        page.waitForTimeout(5000);

        

    }
}
