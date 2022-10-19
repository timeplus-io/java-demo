package timeplus.io;

import io.swagger.client.ApiClient;
import io.swagger.client.Configuration;
import io.swagger.client.auth.ApiKeyAuth;

import io.swagger.client.api.QueriesApi;
import io.swagger.client.api.ApiKeysApi;

public class TimeplusClient {
    private String address;
    private String tenant;
    private String apiKey;

    private ApiClient client;

    public TimeplusClient(String address, String tenant, String apiKey) {
        this.address = address;
        this.tenant = tenant;
        this.apiKey = apiKey;
        System.out.println("address " + this.address);
        System.out.println("tenant " + this.tenant);

        client = Configuration.getDefaultApiClient();
        client.setBasePath(this.baseUrl() + "api/v1beta1");

        // Configure API key authorization: ApiKeyAuth
        ApiKeyAuth ApiKeyAuth = (ApiKeyAuth) client.getAuthentication("ApiKeyAuth");
        ApiKeyAuth.setApiKey(this.apiKey);
    }

    public String address() {
        return this.address;
    }

    public String apiKey() {
        return this.apiKey;
    }

    public String tenant() {
        return this.tenant;
    }

    public QueriesApi queryAPI() {
        return new QueriesApi();
    }

    public ApiKeysApi apikeysAPI() {
        return new ApiKeysApi();
    }

    public String baseUrl() {
        if (this.tenant.length() > 0) {
            return this.address + this.tenant + "/";
        }
        return this.address;
    }
}
