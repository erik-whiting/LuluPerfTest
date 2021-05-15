package com.lulu.main.java.models.reporters;

import org.json.simple.JSONObject;

import java.util.HashMap;

public class MonitorOutputTransmitter {
    public HashMap<String, Object> transmissionData;

    public MonitorOutputTransmitter(MonitorOutputReceiver receiver) {
        this.transmissionData = new HashMap<>() {
            {
                put("threadId", receiver.threadId);
                put("monitorIteration", receiver.monitorIteration);
                put("threadName", receiver.threadName);
                put("data", receiver.data);
            }
        };
    }

    public String transmitAsString() { return this.transmissionData.toString(); }

    public JSONObject transmitAsJSON() {
        JSONObject json = new JSONObject();
        for (String key : this.transmissionData.keySet()) {
            json.put(key, this.transmissionData.get(key));
        }
        return json;
    }

    public String transmitAsSQLInsertValues(boolean castAllValuesToString) {
        String insertValues = "";
        insertValues += "\"" + this.transmissionData.get("threadId") + "\"";
        insertValues += ", ";
        insertValues += "\"" + this.transmissionData.get("monitorIteration") + "\"";
        insertValues += ", ";
        insertValues += "\"" + this.transmissionData.get("threadName") + "\"";
        insertValues += ", ";
        insertValues += "\"" + this.transmissionData.get("data") + "\"";
        insertValues = "(" + insertValues + ")";
        return insertValues;
    }

    public String transmitAsSQLInsertValues() {
        String insertValues = "";
        insertValues += this.transmissionData.get("threadId");
        insertValues += ", ";
        insertValues += this.transmissionData.get("monitorIteration");
        insertValues += ", ";
        insertValues += "\"" + this.transmissionData.get("threadName") + "\"";
        insertValues += ", ";
        insertValues += this.transmissionData.get("data");
        insertValues = "(" + insertValues + ")";
        return insertValues;
    }
}
