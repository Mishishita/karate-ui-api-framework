package utils;

import java.io.InputStream;
import java.util.Properties;

public class ConfigReader {

    public static final Properties properties = new Properties();

    static{
        try {
            String env = System.getProperty("env","qa");
            String filename = "config/"+env+".properties";
            InputStream input = ConfigReader.class.getClassLoader().getResourceAsStream(filename);
            properties.load(input);
        } catch (Exception e) {
            throw new RuntimeException("Error cargando archivo de configuración",e);
        }
    }

    public static String getBaseUrl(){
        return properties.getProperty("base.url");
    }

    public static String getEnviroment(){
        return properties.getProperty("enviroment");
    }

    
}
