package samples;

import java.io.IOException;
import java.util.*;
import java.net.URL;

import org.json.JSONArray;
import org.json.JSONObject;

import com.timeplus.QueryObserver;
import com.timeplus.Query;

import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.HttpUrl;
import com.squareup.okhttp.MediaType;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;
import com.squareup.okhttp.RequestBody;

const API_VERSION = "v1beta2";

class MyQueryResultHandler implements QueryObserver {
    private JSONArray header = null;

    public MyQueryResultHandler() {
    }

    @Override
    public void handleQuery(JSONObject query) {
        JSONObject result = (JSONObject) query.get("result");
        JSONArray header = (JSONArray) result.get("header");
        System.out.println("In MyQueryResultHandler , query result header : " + header);
        this.header = header;
    }

    @Override
    public void handleData(JSONArray event) {
        System.out.println("In MyQueryResultHandler , handle data : " + event);
    }

    @Override
    public void handleMetric(JSONObject metric) {
        System.out.println("In MyQueryResultHandler , handle metric : " + metric);
    }
}

public class ApplicationSample {
    public static void main(String[] args) {
        System.out.println("Hello Timeplus!");
        OkHttpClient client = new OkHttpClient();

        String apiKey = System.getenv("TIMEPLUS_API_KEY");
        String address = System.getenv("TIMEPLUS_ADDRESS");
        String tenant = System.getenv("TIMEPLUS_TENANT");

        String baseURL = address + "/" + tenant + "/api/" + API_VERSION + "/"

        // sample 1: list all streams
        try {
            URL url = new URL(baseURL + "streams");
            Request request = new Request.Builder()
                .url(url)
                .addHeader("X-API-KEY", apiKey)
                .addHeader("Accept", "application/json")
                .build();

            Response response = client.newCall(request).execute();
            JSONArray streams = new JSONArray(response.body().string());
            for (int i = 0; i < streams.length(); i++) {
                JSONObject stream = streams.getJSONObject(i);

                // Please refer to the REST API documentation for more fields
                System.out.format("Stream #%d: %s\n", i + 1, stream.get("name"));
            }
        } catch (Exception e) {
            System.out.println("failed to list stream " + e.getMessage());
        }

        // sample 2: list 10 queries
        try {
            URL url = new URL(baseURL + "queries");
            Request request = new Request.Builder()
                .url(url)
                .addHeader("X-API-KEY", apiKey)
                .addHeader("Accept", "application/json")
                .build();

            Response response = client.newCall(request).execute();
            JSONArray queries = new JSONArray(response.body().string());
            for (int i = 0; i < queries.length() && i < 10; i++) {
                JSONObject query = queries.getJSONObject(i);

                // Please refer to the REST API documentation for more fields
                System.out.format("Query #%d: %s\n", i + 1, query.get("sql"));
            }
        } catch (Exception e) {
            System.out.println("failed to list queries " + e.getMessage());
        }

        // sample 3: run a query and handle query result
        MyQueryResultHandler ob = new MyQueryResultHandler();

        try {
            Query query = new Query(address, tenant, apiKey, "select * from iot", "", "", ob);
            query.run();
        } catch (IOException e) {
            System.out.println("failed to run query " + e.getMessage());
        }
    }
}
