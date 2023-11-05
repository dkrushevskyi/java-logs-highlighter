package net.kodiki;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Main {
    private static final Logger logger
            = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) {
        System.out.println("Hello world!");
        logger.info("Example log from {}", Main.class.getSimpleName());
        logger.warn("Warning {}", Main.class.getSimpleName());
        logger.error("Error {}", Main.class.getSimpleName());
    }
}