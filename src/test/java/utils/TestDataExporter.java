package utils;

import java.io.File;
import java.io.IOException;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import ui.data.UserData;

public class TestDataExporter {
    private TestDataExporter() {

    }

    public static void exportUser(UserData userData) {

        try {

            File directory = new File("target/testdata");
            if (!directory.exists()) {
                directory.mkdirs();
            }
            File file = new File(directory, "user.json");

            ObjectMapper mapper = new ObjectMapper();
            mapper.enable(SerializationFeature.INDENT_OUTPUT);

            mapper.writeValue(file, userData);

        } catch (IOException e) {
            throw new RuntimeException("Error exporting test data", e);
        }
    }

    public static UserData readUser() {
        try {
            File file = new File("target/testdata/user.json");

            ObjectMapper mapper = new ObjectMapper();
            return mapper.readValue(file, UserData.class);
        } catch (Exception e) {
            throw new RuntimeException("Error reading test data", e);
        }
    }

    public static void updateAccountData(String customerId, String checkingAccountId, String savingsAccountId) {
        UserData userData = readUser();
        userData.setCustomerId(customerId);
        userData.setCheckingAccountId(checkingAccountId);
        userData.setSavingsAccountId(savingsAccountId);
        exportUser(userData);

    }

}
