package utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LogUtil {

    private static final Logger logger =
            LoggerFactory.getLogger(LogUtil.class);

    public static void info(String mensaje) {
        logger.info(mensaje);
    }
}