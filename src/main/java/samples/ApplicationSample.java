package samples;

import java.io.IOException;
import java.util.*;

import org.json.JSONArray;
import org.json.JSONObject;

import com.timeplus.QueryObserver;
import com.timeplus.TimeplusClient;
import com.timeplus.Query;

import io.swagger.client.*;
import io.swagger.client.api.ApiKeysV1beta1Api;
import io.swagger.client.api.QueriesV1beta1Api;
import io.swagger.client.model.*;

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

        // initialize Timeplus Client with address, apikey and tenant
        String apiKey = System.getenv("TIMEPLUS_API_KEY");
        String address = System.getenv("TIMEPLUS_ADDRESS");
        String tenant = System.getenv("TIMEPLUS_TENANT");
        TimeplusClient client = new TimeplusClient(address, tenant, apiKey);

        // sample 1: list existing api keys
        listApiKeys(client);

        // sample 2: list all queries
        listQueries(client);

        AnalyzeSQL(client);

        // sample 3: run a query and handle query result
        runQuery(client);
    }

    public static void listApiKeys(TimeplusClient client) {
        // List current API Key
        ApiKeysV1beta1Api apiInstance = client.apikeysAPI();
        try {
            List<APIKey> result = apiInstance.v1beta1AuthApiKeysGet();
            System.out.println(result);
        } catch (ApiException e) {
            System.err.println("Exception when calling ApiKeysApi#authApiKeysGet");
            e.printStackTrace();
        }
    }

    public static void listQueries(TimeplusClient client) {
        QueriesV1beta1Api queryApiInstance = client.queryAPI();
        // List all current queries
        try {
            List<io.swagger.client.model.Query> result = queryApiInstance.v1beta1QueriesGet();
            System.out.println(result);
        } catch (ApiException e) {
            System.err.println("Exception when calling QueriesApi#queriesGet");
            e.printStackTrace();
        }
    }

    public static void AnalyzeSQL(TimeplusClient client) {
        try {
            AnalyzeSQLRequest request = new AnalyzeSQLRequest().sql("show streams");
            SQLAnalyzeResult result = client.queryAPI().v1beta1SqlanalyzePost(request);
            System.out.println(result.getQueryType());
        } catch (ApiException e) {
            System.err.println("Exception when calling AnalyzeSQL");
            e.printStackTrace();
        }
    }

    public static void runQuery(TimeplusClient client) {
        try {
            MyQueryResultHandler ob = new MyQueryResultHandler();
            Query query = new Query(client, "select * from car_live_data", "", "", ob);
            query.run();
        } catch (IOException e) {
            System.out.println("failed to run query " + e.getMessage());
        }
    }
}
