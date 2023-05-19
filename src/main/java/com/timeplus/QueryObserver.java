package com.timeplus;

import org.json.JSONArray;
import org.json.JSONObject;

public interface QueryObserver {
    void handleData(JSONArray event);

    void handleQuery(JSONObject query);

    void handleMetric(JSONObject metric);
}
