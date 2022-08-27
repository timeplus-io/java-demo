package timeplus.io;

import io.swagger.client.ApiClient;
import io.swagger.client.Configuration;
import io.swagger.client.auth.ApiKeyAuth;

public class TimeplusClient {
    private String address;
    private String tenant;
    private String apiKey;

    public TimeplusClient(String address, String tenant, String apiKey) {
        this.address = address;
        this.tenant = tenant;
        this.apiKey = apiKey;

        System.out.println("address " + this.address);
        System.out.println("tenant " + this.tenant);
        System.out.println("apiKey " + this.apiKey);

        ApiClient client = Configuration.getDefaultApiClient();

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

    public String baseUrl() {
        if (this.tenant.length() > 0) {
            return this.address + this.tenant + "/";
        }
        return this.address;
    }
}
