package net.kodiki;

import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.AppenderBase;

import static ch.qos.logback.classic.Level.ERROR_INTEGER;
import static ch.qos.logback.classic.Level.WARN_INTEGER;

public class ColoredTypeConsoleAppender extends AppenderBase<ILoggingEvent> {
    private boolean enableTypeColoring = false;
    private ColloredTypeConsoleAppenderMode mode = ColloredTypeConsoleAppenderMode.COLORED_TEXT;

    private static final String TEXT_RED = "\u001B[31m";
    private static final String TEXT_YELLOW = "\u001B[33m";

    private static final String HIGHLIGHT_RED = "\u001B[41m\u001B[37m";
    private static final String HIGHLIGHT_YELLOW = "\u001B[43m\u001B[30m";
    private static final String CLEAR = "\u001B[0m";

    @Override
    protected void append(ILoggingEvent event) {
        if (enableTypeColoring) {
            String coloredMessage = getColoredMessage(event);
            System.out.println(coloredMessage);
        } else {
            System.out.println(event.getFormattedMessage());
        }
    }

    private String getColoredMessage(ILoggingEvent event) {
        String message = event.getFormattedMessage();

        if (ColloredTypeConsoleAppenderMode.COLORED_TEXT.equals(mode)){
            if (event.getLevel().levelInt == ERROR_INTEGER) {
                return TEXT_RED + message + CLEAR; // Red background, white text
            } else if (event.getLevel().levelInt == WARN_INTEGER) {
                return TEXT_YELLOW + message + CLEAR;
            }
        } else {
            if (event.getLevel().levelInt == ERROR_INTEGER) {
                return HIGHLIGHT_RED + message + CLEAR; // Red background, white text
            } else if (event.getLevel().levelInt == WARN_INTEGER) {
                return HIGHLIGHT_YELLOW + message + CLEAR;
            }
        }
        return message;
    }

    public void setEnableTypeColoring(boolean enableTypeColoring) {
        this.enableTypeColoring = enableTypeColoring;
    }

    public void setMode(String mode) {
        this.mode = ColloredTypeConsoleAppenderMode.valueOf(mode);
    }
}
