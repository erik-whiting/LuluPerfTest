package com.lulu.main.java.models.reporters;

import org.json.simple.JSONObject;

import java.util.HashMap;

public class MonitorOutputDataAdapter {
    public HashMap<String, Object> transmissionData;

    public MonitorOutputDataAdapter(MonitorOutputSignal signal) {
        this.transmissionData = new HashMap<>() {
            {
                put("threadId", signal.threadId);
                put("monitorIteration", signal.monitorIteration);
                put("threadName", signal.threadName);
                put("data", signal.data);
            }
        };
    }

    public String renderString() { return this.transmissionData.toString(); }

    public JSONObject renderJSON() {
        JSONObject json = new JSONObject();
        for (String key : this.transmissionData.keySet()) {
            json.put(key, this.transmissionData.get(key));
        }
        return json;
    }

    public String renderSQLInsertValuesAllStrings(boolean castAllValuesToString) {
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

    public String renderSQLInsertValues() {
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
