package net.kodiki;

import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.AppenderBase;

import static ch.qos.logback.classic.Level.ERROR_INTEGER;
import static ch.qos.logback.classic.Level.WARN_INTEGER;

public class ColoredTypeConsoleAppender extends AppenderBase<ILoggingEvent> {
    private boolean enableColor = true;

    @Override
    protected void append(ILoggingEvent event) {
        if (enableColor) {
            String coloredMessage = getColoredMessage(event);
            System.out.println(coloredMessage);
        } else {
            System.out.println(event.getFormattedMessage());
        }
    }

    private String getColoredMessage(ILoggingEvent event) {
        String message = event.getFormattedMessage();
        if (event.getLevel().levelInt == ERROR_INTEGER) {
            return "\u001B[41m\u001B[37m" + message + "\u001B[0m"; // Red background, white text
        } else if (event.getLevel().levelInt == WARN_INTEGER) {
            return "\u001B[33m" + message + "\u001B[0m"; // Yellow color for warnings
        }
        return message;
    }

    public void setEnableColor(boolean enableColor) {
        this.enableColor = enableColor;
    }
}
