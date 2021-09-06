package com.lulu.main;

import com.lulu.main.java.models.configurations.ReporterConfiguration;
import com.lulu.main.java.models.reporters.MonitorOutputSignal;
import com.lulu.main.java.models.reporters.MonitorOutputTransceiver;
import org.hamcrest.CoreMatchers;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class MonitorOutputTransceiverTest {
    private static MonitorOutputTransceiver jsonTransceiver;
    private static MonitorOutputTransceiver sqlTransceiver;
    private static MonitorOutputTransceiver sqlStringTransceiver;
    private static MonitorOutputTransceiver stringTransceiver;
    private static ArrayList<MonitorOutputTransceiver> transceivers;
    private static MonitorOutputSignal mos1;
    private static MonitorOutputSignal mos2;
    private static MonitorOutputSignal mos3;
    private static MonitorOutputSignal mos4;

    @BeforeClass
    public static void setUp() {
        ReporterConfiguration jsonConfig = new ReporterConfiguration("json");
        ReporterConfiguration sqlConfig = new ReporterConfiguration("sql");
        ReporterConfiguration sqlStringsConfig = new ReporterConfiguration("Sql Strings");
        ReporterConfiguration stringConfig = new ReporterConfiguration("strings");

        jsonTransceiver = new MonitorOutputTransceiver(jsonConfig);
        sqlTransceiver = new MonitorOutputTransceiver(sqlConfig);
        sqlStringTransceiver = new MonitorOutputTransceiver(sqlStringsConfig);
        stringTransceiver = new MonitorOutputTransceiver(stringConfig);

        mos1 = new MonitorOutputSignal(100, 1, "threadName", 1.2);
        mos2 = new MonitorOutputSignal(100, 2, "threadName", 1.4);
        mos3 = new MonitorOutputSignal(200, 1, "otherThread", 81);
        mos4 = new MonitorOutputSignal(200, 2, "otherThread", 77);

        transceivers = new ArrayList<>(Arrays.asList(
                jsonTransceiver,
                sqlTransceiver,
                sqlStringTransceiver,
                stringTransceiver
        ));
    }

    @Test
    public void receiveSignal() {
        for (MonitorOutputTransceiver transceiver : transceivers) {
            transceiver.signals = new ArrayList<>(Arrays.asList(mos1, mos2, mos3, mos4));
            assertThat(transceiver.signals.size(), is(4));
            transceiver.receiveSignal(300, 1, "testThread", 8.4);
            assertThat(transceiver.signals.size(), is(5));
        }
    }

    @Test
    public void transmit() {
        
    }

    @Test
    public void translateSignals() {
    }

    @Test
    public void transmitStrings() {
    }

    @Test
    public void transmitToAPI() {
    }

    @Test
    public void transmitToDatabase() {
    }
}