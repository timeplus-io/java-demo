package com.timeplus;

import java.io.BufferedReader;
import java.io.IOException;

import org.json.JSONArray;
import org.json.JSONObject;

import com.squareup.okhttp.Response;

public class SSEHandler {

    private Response response = null;
    private QueryObserver ob;

    public SSEHandler(Response response, QueryObserver ob) {
        this.response = response;
        this.ob = ob;
    }

    public void handle() {
        try {
            BufferedReader br = new BufferedReader(response.body().charStream());
            String line;
            while ((line = br.readLine()) != null) {
                if (line.length() == 0) {
                    continue;
                }

                int colonIndex = line.indexOf(":");
                String eventField = line.substring(0, colonIndex).trim();
                String eventData = line.substring(colonIndex + 1).trim();

                if (eventField.equals("event")) {
                    String dataLine = br.readLine();
                    int eventColonIndex = dataLine.indexOf(":");
                    String eventContentData = dataLine.substring(eventColonIndex + 1).trim();

                    if (eventData.equals("query")) {
                        JSONObject query = new JSONObject(eventContentData);
                        ob.handleQuery(query);

                    } else if (eventData.equals("metrics")) {
                        JSONObject metric = new JSONObject(eventContentData);
                        ob.handleMetric(metric);
                    }

                } else if (eventField.equals("data")) {
                    JSONArray array = new JSONArray(eventData);
                    for (int i = 0; i < array.length(); i++) {
                        JSONArray row = array.getJSONArray(i);
                        ob.handleData(row);
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
