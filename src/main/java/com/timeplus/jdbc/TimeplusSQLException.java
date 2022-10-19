package com.timeplus.jdbc;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.sql.SQLException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TimeplusSQLException extends SQLException {
    private static final long serialVersionUID = -3669725541475950504L;
    Logger logger = LoggerFactory.getLogger(TimeplusSQLException.class);

    public TimeplusSQLException(String reason) {
        super(reason);
        StringWriter sw = new StringWriter();
        super.printStackTrace(new PrintWriter(sw));
        String stacktrace = sw.toString();
        this.logger.debug(reason + stacktrace);
    }
}
