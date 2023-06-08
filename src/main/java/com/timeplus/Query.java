package com.timeplus;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import org.json.JSONObject;

import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.HttpUrl;
import com.squareup.okhttp.MediaType;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;
import com.squareup.okhttp.RequestBody;

public class Query {
    private String apiKey = null;
    private URL url = null;

    private String sql = null;
    private String name = null;
    private String desciption = null;

    private QueryObserver ob = null;

    public Query(String host, String tenant, String apiKey, String sql, String name, String desciption, QueryObserver ob)
            throws MalformedURLException {
        this.apiKey = apiKey;
        this.url = new URL(host + "/" + tenant + "/api/v1beta2/queries");

        this.sql = sql;
        this.name = name;
        this.desciption = desciption;

        this.ob = ob;
    }

    public void run() throws IOException {
        OkHttpClient client = new OkHttpClient();

        String json = getCreatePayload(sql, name, desciption);
        RequestBody body = RequestBody.create(
                MediaType.parse("application/json"), json);

        Request request = new Request.Builder()
                .url(this.url)
                .addHeader("X-API-KEY", this.apiKey)
                .addHeader("Accept", "text/event-stream")
                .addHeader("Connection", "keep-alive")
                .post(body)
                .build();

        Response response = client.newCall(request).execute();

        SSEHandler handler = new SSEHandler(response, this.ob);
        handler.handle();
    }

    private String getCreatePayload(String sql, String name, String description) {
        JSONObject payload = new JSONObject();
        payload.put("name", name);
        payload.put("sql", sql);
        payload.put("description", description);
        return payload.toString();
    }

}
