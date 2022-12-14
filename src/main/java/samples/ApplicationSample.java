package samples;

import java.util.*;
import java.util.concurrent.TimeUnit;

import com.timeplus.Observer;
import com.timeplus.QueryResultWatcher;
import com.timeplus.TimeplusClient;

import io.swagger.client.*;
import io.swagger.client.api.ApiKeysApi;
import io.swagger.client.api.QueriesApi;
import io.swagger.client.model.*;

class MyQueryResultHandler implements Observer {
    public MyQueryResultHandler() {
    }

    public void handleMessage(String message) {
        System.out.println("In MyQueryResultHandler , handle message" + message);
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
        ApiKeysApi apiInstance = client.apikeysAPI();
        try {
            List<APIKey> result = apiInstance.authApiKeysGet();
            System.out.println(result);
        } catch (ApiException e) {
            System.err.println("Exception when calling ApiKeysApi#authApiKeysGet");
            e.printStackTrace();
        }
    }

    public static void listQueries(TimeplusClient client) {
        QueriesApi queryApiInstance = client.queryAPI();
        // List all current queries
        try {
            List<QueryWithMetrics> result = queryApiInstance.queriesGet();
            System.out.println(result);
        } catch (ApiException e) {
            System.err.println("Exception when calling QueriesApi#queriesGet");
            e.printStackTrace();
        }
    }

    public static void AnalyzeSQL(TimeplusClient client) {
        try {
            AnalyzeSQLRequest request = new AnalyzeSQLRequest().sql("show streams");
            SQLAnalyzeResult result = client.queryAPI().sqlanalyzePost(request);
            System.out.println(result.getQueryType());
        } catch (ApiException e) {
            System.err.println("Exception when calling AnalyzeSQL");
            e.printStackTrace();
        }
    }

    public static void runQuery(TimeplusClient client) {
        QueriesApi queryApiInstance = client.queryAPI();
        // Create a new query
        try {
            CreateQueryRequest request = new CreateQueryRequest()
                    .description("sample query")
                    .sql("select * from iot");
            Query result = queryApiInstance.queriesPost(request);
            String queryId = result.getId();
            List<Column> header = result.getResult().getHeader();

            System.out.println("Query created with id " + queryId);
            System.out.println("Query header is " + header);

            // monitor query result for 10 second and stop watch
            MyQueryResultHandler queryResultHandler = new MyQueryResultHandler();
            QueryResultWatcher watcher = new QueryResultWatcher(client, queryId, queryResultHandler);
            watcher.watch();
            try {
                TimeUnit.SECONDS.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            watcher.stop(); // query is still running but not been consumed

            // cancel query using id
            queryApiInstance.queriesIdCancelPost(queryId);
            System.out.println("Query cancelled " + queryId);
        } catch (ApiException e) {
            System.err.println("Exception when calling QueriesApi#queriesPost");
            e.printStackTrace();
        }
    }
}
