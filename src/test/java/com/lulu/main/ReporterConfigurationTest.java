package com.lulu.main;

import com.lulu.main.java.models.configurations.ReporterConfiguration;
import com.lulu.main.java.models.reporters.ReportType;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

@RunWith(Parameterized.class)
public class ReporterConfigurationTest {
    @Parameterized.Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][] {
                {"json",                ReportType.JSON},
                {"SQL",                 ReportType.SQL},
                {"sql strings",         ReportType.SQL_ALL_STRINGS},
                {"unsupported type",    ReportType.STRING}
        });
    }

    private String input;
    private ReportType expected;

    public ReporterConfigurationTest(String input, ReportType expected) {
        this.input = input;
        this.expected = expected;
    }

    @Test
    public void testTypes() {
        ReporterConfiguration jsonConfig = new ReporterConfiguration(input);

        assertThat(jsonConfig.reportType, is(expected));
    }
}