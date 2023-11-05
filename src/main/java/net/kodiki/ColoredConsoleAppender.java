package net.kodiki;

import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.AppenderBase;

public class ColoredConsoleAppender extends AppenderBase<ILoggingEvent> {
    private boolean enableColor = true;

    public void setEnableColor(boolean enableColor) {
        this.enableColor = enableColor;
    }

    @Override
    protected void append(ILoggingEvent eventObject) {
        String coloredMessage = eventObject.getFormattedMessage();
        if (enableColor) {
            coloredMessage = "\u001B[31m" + coloredMessage + "\u001B[0m"; // Red color
        }
        System.out.print(coloredMessage);
    }
}
