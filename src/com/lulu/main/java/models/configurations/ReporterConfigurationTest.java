package com.lulu.main.java.models.configurations;

import com.lulu.main.java.models.reporters.ReportType;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class ReporterConfigurationTest {
    @Test
    public void testTypes() {
        ReporterConfiguration jsonConfig = new ReporterConfiguration("json");
        ReporterConfiguration sqlConfig = new ReporterConfiguration("SQL");
        ReporterConfiguration sqlStringConfig = new ReporterConfiguration("sql strings");
        ReporterConfiguration unexpectedConfig = new ReporterConfiguration("unspoorted type");

        assertThat(jsonConfig.reportType, is(ReportType.JSON));
        assertThat(sqlConfig.reportType, is(ReportType.SQL));
        assertThat(sqlStringConfig.reportType, is(ReportType.SQL_ALL_STRINGS));
        assertThat(unexpectedConfig.reportType, is(ReportType.STRING));
    }
}