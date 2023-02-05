package com.timeplus;

import java.io.BufferedReader;
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
    private TimeplusClient client = null;

    private String host = null;
    private String scheme = null;

    private String sql = null;
    private String name = null;
    private String desciption = null;

    public Query(TimeplusClient client, String sql, String name, String desciption) throws MalformedURLException {
        this.client = client;
        URL url = new URL(this.client.address());
        this.host = url.getHost();
        this.scheme = url.getProtocol();

        this.sql = sql;
        this.name = name;
        this.desciption = desciption;
    }

    public void run() throws IOException {
        OkHttpClient client = new OkHttpClient();

        HttpUrl url = new HttpUrl.Builder()
                .scheme(this.scheme)
                .host(this.host)
                .addPathSegment(this.client.tenant() + "/api/v1beta2/queries")
                .build();

        String json = getCreatePayload(sql, name, desciption);
        RequestBody body = RequestBody.create(
                MediaType.parse("application/json"), json);

        Request request = new Request.Builder()
                .url(url)
                .addHeader("X-API-KEY", this.client.apiKey())
                .addHeader("Accept", "text/event-stream")
                .addHeader("Connection", "keep-alive")
                .post(body)
                .build();

        Response response = client.newCall(request).execute();

        SSEHandler handler = new SSEHandler(response);
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
