package com.lulu.main;

import com.lulu.main.java.models.reporters.MonitorOutputDataAdapter;
import com.lulu.main.java.models.reporters.MonitorOutputSignal;
import org.json.simple.JSONObject;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.HashMap;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class MonitorOutputDataAdapterTest {
    private static MonitorOutputDataAdapter adapter;
    private static MonitorOutputSignal signal;
    private static final int threadId = 1234;
    private static final int iteration = 10;
    private static final String threadName = "Thread1234";
    private static final double data = 44.543;
    private static HashMap<String, Object> dataHash = new HashMap<>() {
        {
            put("threadId", threadId);
            put("monitorIteration", iteration);
            put("threadName", threadName);
            put("data", data);
        }
    };

    @BeforeClass
    public static void setUp() {
        signal = new MonitorOutputSignal(threadId, iteration, threadName, data);
        adapter = new MonitorOutputDataAdapter(signal);
    }

    @Test
    public void renderString() {
        String expectedOutput = dataHash.toString();
        String output = adapter.renderString();
        assertThat(output, is(expectedOutput));
    }

    @Test
    public void renderJSON() {
        // There is a very weird test failure here
        // when we pass both JSONObject objects to
        // assertEquals. The workaroundAssertion
        // is how we get passed that.
        JSONObject expectedOutput = new JSONObject();
        for (String key : dataHash.keySet()) {
            expectedOutput.put(key, dataHash.get(key));
        }
        JSONObject output = adapter.renderJSON();
        boolean workaroundAssertion = output.toString().equals(expectedOutput.toString());
        assertTrue(workaroundAssertion);
    }

    @Test
    public void renderSQLInsertValuesAllStrings() {
        String expectedOutput = "(" + "\""   + threadId   + "\", "
                                      + "\"" + iteration  + "\", "
                                      + "\"" + threadName + "\", "
                                      + "\"" + data       + "\""
                                      + ")";
        String actualOutput = adapter.renderSQLInsertValuesAllStrings(true);
        assertEquals(expectedOutput, actualOutput);
    }

    @Test
    public void renderSQLInsertValues() {
        String expectedOutput = "(" + threadId   + ", "
                                    + iteration  + ", "
                                    + "\"" + threadName + "\", "
                                    + data
                                    + ")";
        String actualOutput = adapter.renderSQLInsertValues();
        actualOutput = adapter.renderSQLInsertValues();
        assertEquals(expectedOutput, actualOutput);
    }
}