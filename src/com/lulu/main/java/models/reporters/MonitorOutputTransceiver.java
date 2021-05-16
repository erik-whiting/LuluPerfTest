package com.lulu.main.java.models.reporters;

import java.util.ArrayList;
import java.util.ListIterator;

public class MonitorOutputTransceiver {
    public ArrayList<MonitorOutputSignal> signals;
    public ArrayList<MonitorOutputDataAdapter> adapters = new ArrayList<>();

    public MonitorOutputTransceiver() {
         this.signals = new ArrayList<>();
    }

    public void receiveSignal(long threadId, long monitorIteration, String threadName, double data) {
        MonitorOutputSignal signal = new MonitorOutputSignal(
                threadId,
                monitorIteration,
                threadName,
                data
        );
        this.signals.add(signal);
    }

    public void transmit() {
        this.translateSignals();
        for (MonitorOutputDataAdapter adapter : this.adapters) {
            System.out.println(adapter.renderString());
        }
    }

    public void translateSignals() {
        ListIterator<MonitorOutputSignal> signalIterator = this.signals.listIterator();
        while (signalIterator.hasNext()) {
            this.adapters.add(
                    new MonitorOutputDataAdapter(signalIterator.next())
            );
            signalIterator.remove();
        }
    }
}
