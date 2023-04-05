# Java-demo
Timeplus Java API demo

initialize Timeplus Client using address (for example `https://us.timeplus.cloud/` ) , workspace, apikeys

```java
String apiKey = System.getenv("TIMEPLUS_API_KEY");
String address = System.getenv("TIMEPLUS_ADDRESS");
String workspace = System.getenv("TIMEPLUS_WORKSPACE");
TimeplusClient client = new TimeplusClient(address, workspace, apiKey);
```

and then call API from packages `io.swagger.client.api`

- io.swagger.client.api.QueriesApi
- io.swagger.client.api.StreamsApi
- io.swagger.client.api.SourcesApi
- ... ...

refer to `Sample.java` for more detailed information.
