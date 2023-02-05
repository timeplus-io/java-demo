package samples;

import java.util.*;
import java.util.concurrent.TimeUnit;

import com.timeplus.Observer;
import com.timeplus.QueryResultWatcher;
import com.timeplus.TimeplusClient;

import io.swagger.client.*;
import io.swagger.client.api.ApiKeysV1beta1Api;
import io.swagger.client.api.QueriesV1beta1Api;
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
            List<Query> result = queryApiInstance.v1beta1QueriesGet();
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
        QueriesV1beta1Api queryApiInstance = client.queryAPI();
        // Create a new query
        try {
            CreateQueryRequestV1Beta1 request = new CreateQueryRequestV1Beta1()
                    .description("sample query")
                    .sql("select * from iot");
            CreateQueryResponse result = queryApiInstance.v1beta1QueriesPost(request);
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
            queryApiInstance.v1beta1QueriesIdCancelPost(queryId);
            System.out.println("Query cancelled " + queryId);
        } catch (ApiException e) {
            System.err.println("Exception when calling QueriesApi#queriesPost");
            e.printStackTrace();
        }
    }
}
