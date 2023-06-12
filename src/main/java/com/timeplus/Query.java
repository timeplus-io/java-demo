package com.timeplus;

import java.io.BufferedReader;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import org.json.JSONObject;

import com.squareup.okhttp.OkHttpClient;
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

	private BufferedReader br = null;
	private QueryResultIterator it = null;

	public Query(String baseURL, String apiKey, String sql, String name, String desciption)
			throws MalformedURLException {
		this.apiKey = apiKey;
		this.url = new URL(baseURL + "queries");

		this.sql = sql;
		this.name = name;
		this.desciption = desciption;
	}

	public JSONObject start() throws Exception {
		OkHttpClient client = new OkHttpClient();

		String json = getCreatePayload(sql, name, desciption);
		RequestBody body = RequestBody.create(MediaType.parse("application/json"), json);

		Request request = new Request.Builder().url(this.url).addHeader("X-API-KEY", this.apiKey)
				.addHeader("Accept", "text/event-stream").addHeader("Connection", "keep-alive").post(body).build();

		Response response = client.newCall(request).execute();
		
		if (response.code() > 299) {
			throw new Exception(response.body().string());
		}

		this.br = new BufferedReader(response.body().charStream());
		String line = this.br.readLine();

		if (line == null) {
			throw new Exception("failed to read response body");
		}

		// The first event MUST be query
		int colonIndex = line.indexOf(":");
		String eventField = line.substring(0, colonIndex).trim();
		String eventData = line.substring(colonIndex + 1).trim();

		if (!(eventField.equals("event") && eventData.equals("query"))) {
			throw new Exception("expected messsage");
		}

		String dataLine = br.readLine();
		int eventColonIndex = dataLine.indexOf(":");

		// TODO: Handle the case where the field is not data?
		String eventContentData = dataLine.substring(eventColonIndex + 1).trim();

		this.it = new QueryResultIterator(this.br);

		return new JSONObject(eventContentData);

	}

	public QueryResultIterator iterator() {
		return this.it;
	}

	private String getCreatePayload(String sql, String name, String description) {
		JSONObject payload = new JSONObject();
		payload.put("name", name);
		payload.put("sql", sql);
		payload.put("description", description);
		return payload.toString();
	}
	
	public void stop() throws IOException {
		this.br.close();
	}
}
