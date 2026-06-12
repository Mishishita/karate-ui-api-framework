package utils;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import ui.data.RegisterUserData;

public class TestDataExporter {
    private TestDataExporter() {

    }

    public static void exportUser(RegisterUserData userData){

        try {

            File directory = new File("target/testdata");
            if (!directory.exists()) {
                directory.mkdirs();
            }
            File file = new File("target/testdata/user.json");

            String json = "{\n" + " \"username\": \"" +
                userData.getUsername() + 
                "\",\n" + 
                " \"password\": \"" +
                userData.getPassword() + 
                "\"\n" + "}";

             try (FileWriter writer= new FileWriter(file)) {
                 writer.write(json);
                
             }

        } catch (IOException e) {
            throw new RuntimeException("Error exporting test data",e);
        }
    }

}
