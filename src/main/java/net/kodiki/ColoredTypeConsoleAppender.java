package net.kodiki;

import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.AppenderBase;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static ch.qos.logback.classic.Level.ERROR_INTEGER;
import static ch.qos.logback.classic.Level.WARN_INTEGER;
import static net.kodiki.AnsiColors.*;

public class ColoredTypeConsoleAppender extends AppenderBase<ILoggingEvent> {
    private boolean enableTypeColoring = false;
    private ColloredTypeConsoleAppenderMode mode = ColloredTypeConsoleAppenderMode.COLORED_TEXT;

    private static final String DEFAULT_WRAPPER_TEMPLATE = "[ %s ]";
    private String wrapperTemplate = DEFAULT_WRAPPER_TEMPLATE;

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
        String log = event.getFormattedMessage();
        String message = format(log);

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
                return HIGHLIGHT_CYAN + message + CLEAR;
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

    public void setWrapperTemplate(String template) {
        this.wrapperTemplate = template;
    }

    private String format(String message) {
        if (!wrapperTemplate.contains("%s")) {
            setWrapperTemplate(DEFAULT_WRAPPER_TEMPLATE);
            System.out.println(HIGHLIGHT_RED + "WRAPPER TEMPLATE IS NOT CORRECT PLEASE USE %%s, IT DEFINE WHERE TO PUT LOGS INSIDE TEMPLATE. USIGN DEFAULT WRAPPER TEMPLATE" + CLEAR);
        }
        return wrapperTemplate.formatted(message);
    }
}
