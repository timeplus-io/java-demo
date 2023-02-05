package com.timeplus;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.swagger.client.ApiClient;
import io.swagger.client.Configuration;
import io.swagger.client.auth.ApiKeyAuth;

import io.swagger.client.api.QueriesV1beta1Api;
import io.swagger.client.api.ApiKeysV1beta1Api;

public class TimeplusClient {
    static Logger logg = LoggerFactory.getLogger(TimeplusClient.class);

    private String address;
    private String tenant;
    private String apiKey;

    private ApiClient client;

    public TimeplusClient(String address, String tenant, String apiKey) {
        this.address = address;
        this.tenant = tenant;
        this.apiKey = apiKey;
        logg.debug("address " + this.address);
        logg.debug("tenant " + this.tenant);

        client = Configuration.getDefaultApiClient();
        client.setBasePath(this.baseUrl() + "api");

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

    public QueriesV1beta1Api queryAPI() {
        return new QueriesV1beta1Api();
    }

    public ApiKeysV1beta1Api apikeysAPI() {
        return new ApiKeysV1beta1Api();
    }

    public String baseUrl() {
        if (this.tenant.length() > 0) {
            return this.address + this.tenant + "/";
        }
        return this.address;
    }
}
