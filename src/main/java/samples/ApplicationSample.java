package samples;

import java.net.URL;

import org.json.JSONArray;
import org.json.JSONObject;

import com.timeplus.QueryResultIterator;
import com.timeplus.Query;

import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

public class ApplicationSample {
	public static final String API_VERSION = "v1beta2";

    public static void main(String[] args) {
        System.out.println("Hello Timeplus!");
        OkHttpClient client = new OkHttpClient();

        String apiKey = System.getenv("TIMEPLUS_API_KEY");
        String address = System.getenv("TIMEPLUS_ADDRESS");
        String tenant = System.getenv("TIMEPLUS_TENANT");

        String baseURL = address + "/" + tenant + "/api/" + API_VERSION + "/";

        // Sample 1: list all streams
        System.out.println("\nSample 1: list all streams");
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

        // Sample 2: list 10 queries
        System.out.println("\nSample 2: list 10 queries");
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

        // Sample 3: run a streaming query and get first 10 results
        System.out.println("\nSample 3: run a streaming query and get first 10 results");
        try {
            Query query = new Query(baseURL, apiKey, "select * from iot", "", "");
            
            // Exception will be throw if we fail to start query.
            // e.g. The stream doesn't exist
            JSONObject metadata = query.start();
            
            JSONObject result = (JSONObject)metadata.get("result");
            JSONArray header = (JSONArray)result.get("header");
            System.out.println("Query header " + header);
            
            QueryResultIterator it = query.iterator();
            
            // We will stop the streaming query after receiving 10 results.
            final int resultCount = 10;
            int current = 0;
            
            while (it.hasNext()) {
            	if (current >= resultCount) {
            		break;
            	}
            	current++;
            	
            	System.out.format("Received result #%d: %s\n", current, it.next());
            }
            
            query.stop();
            
        } catch (Exception e) {
            System.out.println("failed to run query " + e.getMessage());
        }
    }
}
