package com.carpooling.util;

import jakarta.servlet.jsp.JspException;
import jakarta.servlet.jsp.tagext.SimpleTagSupport;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DateTimeFormatTag extends SimpleTagSupport {
    private LocalDateTime dateTime;
    private String pattern;

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public void setPattern(String pattern) {
        this.pattern = pattern;
    }

    @Override
    public void doTag() throws JspException, IOException {
        if (dateTime != null && pattern != null) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
            getJspContext().getOut().write(dateTime.format(formatter));
        }
    }
}
