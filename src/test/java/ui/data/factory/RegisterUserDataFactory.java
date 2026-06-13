package ui.data.factory;

import ui.data.UserData;

public class RegisterUserDataFactory {

    private RegisterUserDataFactory() {

    }

    public static UserData createDefaultUser() {

        UserData userData = new UserData();

        userData.setFirstName("Maria");
        userData.setLastName("Tinoco");
        userData.setStreet("Lima");
        userData.setCity("ATE");
        userData.setState("PE");
        userData.setZipCode("054");
        userData.setPhoneNumber("123456789");
        userData.setSsn("123456");

        long timestamp = System.currentTimeMillis();
        userData.setUsername("test" + timestamp);
        userData.setPassword("test123");
        userData.setRepeatedPassword("test123");

        return userData;
    }

}
