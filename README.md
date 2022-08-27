# java-demo
timeplus java API demo

initialize Timeplus Client using address (for example `https://dev.timeplus.cloud/` ) , tenant, apikeys

```java
String apiKey = System.getenv("TIMEPLUS_API_KEY");
String address = System.getenv("TIMEPLUS_ADDRESS");
String tenant = System.getenv("TIMEPLUS_TENANT");
TimeplusClient client = new TimeplusClient(address, tenant, apiKey);
```

and then call API from packages `io.swagger.client.api`

- io.swagger.client.api.QueriesApi
- io.swagger.client.api.StreamsApi
- io.swagger.client.api.SourcesApi
- ... ...

refer to `Sample.java` for more detailed information.
