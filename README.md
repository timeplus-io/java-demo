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

### Callback for Query Results

To handle stream query result, implement `com.timeplus.QueryObserver` , there are three callbacks:

1. `handleQuery` will notify a query result metadata
2. `handleData` will notify each query result row by row
3. `handleMetric` will notify some metric statistics of the current query.

```
// a sample query json
{
   "sinks":{

   },
   "end_time":0,
   "created_at":"2023-02-06 06:29:06",
   "description":"",
   "message":"",
   "analysis":{
      "original_query":"select * from car_live_data",
      "has_union":false,
      "is_streaming":true,
      "has_aggr":false,
      "has_table_join":false,
      "result_columns":[
         {
            "database":"",
            "is_view":false,
            "column":"in_use",
            "column_type":"bool",
            "table":""
         },
         {
            "database":"",
            "is_view":false,
            "column":"longitude",
            "column_type":"float32",
            "table":""
         },
         {
            "database":"",
            "is_view":false,
            "column":"latitude",
            "column_type":"float32",
            "table":""
         },
         {
            "database":"",
            "is_view":false,
            "column":"speed_kmh",
            "column_type":"uint32",
            "table":""
         },
         {
            "database":"",
            "is_view":false,
            "column":"gas_percent",
            "column_type":"decimal(10, 2)",
            "table":""
         },
         {
            "database":"",
            "is_view":false,
            "column":"total_km",
            "column_type":"float32",
            "table":""
         },
         {
            "database":"",
            "is_view":false,
            "column":"locked",
            "column_type":"bool",
            "table":""
         },
         {
            "database":"",
            "is_view":false,
            "column":"cid",
            "column_type":"string",
            "table":""
         },
         {
            "database":"",
            "is_view":false,
            "column":"time",
            "column_type":"datetime64(3)",
            "table":""
         },
         {
            "database":"",
            "is_view":false,
            "column":"_tp_time",
            "column_type":"datetime64(3)",
            "table":""
         },
         {
            "database":"",
            "is_view":false,
            "column":"_tp_index_time",
            "column_type":"datetime64(3, 'UTC')",
            "table":""
         }
      ],
      "query_type":"SELECT",
      "group_by_columns":[

      ],
      "has_subquery":false,
      "rewritten_query":"select * from car_live_data",
      "required_columns":[
         {
            "database":"default",
            "is_view":false,
            "column":"_tp_index_time",
            "column_type":"datetime64(3, 'UTC')",
            "table":"car_live_data"
         },
         {
            "database":"default",
            "is_view":false,
            "column":"_tp_time",
            "column_type":"datetime64(3)",
            "table":"car_live_data"
         },
         {
            "database":"default",
            "is_view":false,
            "column":"cid",
            "column_type":"string",
            "table":"car_live_data"
         },
         {
            "database":"default",
            "is_view":false,
            "column":"gas_percent",
            "column_type":"decimal(10, 2)",
            "table":"car_live_data"
         },
         {
            "database":"default",
            "is_view":false,
            "column":"in_use",
            "column_type":"bool",
            "table":"car_live_data"
         },
         {
            "database":"default",
            "is_view":false,
            "column":"latitude",
            "column_type":"float32",
            "table":"car_live_data"
         },
         {
            "database":"default",
            "is_view":false,
            "column":"locked",
            "column_type":"bool",
            "table":"car_live_data"
         },
         {
            "database":"default",
            "is_view":false,
            "column":"longitude",
            "column_type":"float32",
            "table":"car_live_data"
         },
         {
            "database":"default",
            "is_view":false,
            "column":"speed_kmh",
            "column_type":"uint32",
            "table":"car_live_data"
         },
         {
            "database":"default",
            "is_view":false,
            "column":"time",
            "column_type":"datetime64(3)",
            "table":"car_live_data"
         },
         {
            "database":"default",
            "is_view":false,
            "column":"total_km",
            "column_type":"float32",
            "table":"car_live_data"
         }
      ]
   },
   "created_by":{
      "name":"Mike",
      "id":"google-oauth2|112510942135182208123"
   },
   "sql":"select * from car_live_data",
   "tags":null,
   "duration":0,
   "result":{
      "data":null,
      "header":[
         {
            "name":"in_use",
            "type":"bool"
         },
         {
            "name":"longitude",
            "type":"float32"
         },
         {
            "name":"latitude",
            "type":"float32"
         },
         {
            "name":"speed_kmh",
            "type":"uint32"
         },
         {
            "name":"gas_percent",
            "type":"decimal(10, 2)"
         },
         {
            "name":"total_km",
            "type":"float32"
         },
         {
            "name":"locked",
            "type":"bool"
         },
         {
            "name":"cid",
            "type":"string"
         },
         {
            "name":"time",
            "type":"datetime64(3)"
         },
         {
            "name":"_tp_time",
            "type":"datetime64(3)"
         },
         {
            "name":"_tp_index_time",
            "type":"datetime64(3, 'UTC')"
         }
      ]
   },
   "start_time":1675664946,
   "last_updated_by":{
      "name":"Gang Tao",
      "id":"google-oauth2|112510942135182208745"
   },
   "last_updated_at":"2023-02-06 06:29:06",
   "name":"",
   "response_time":0,
   "id":"667f7ab4-a09e-4848-a58a-c84ac8db551e",
   "timeColumns":{
      "windowStart":-1,
      "eventTime":9,
      "windowEnd":-1
   },
   "status":"running"
}

// a sample data json array
[true,-177.59792,-71.88995,79,"70.04",12098.609,false,"c00623","2023-02-06T06:38:53.408Z","2023-02-06T06:38:53.408Z","1970-01-01T00:00:00Z"]


// a sample metric json
{
   "processing_time":1,
   "last_event_time":1675665533,
   "count":703,
   "eps":703,
   "response_time":0
}

```
