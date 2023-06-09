# Timeplus Java API demo

`ApplicationSample.java` contains three basic samples to demonstrate how to interacting with Timeplus Rest API.

1. List streams ([doc](https://docs.timeplus.com/rest.html#tag/Streams-v1beta2/paths/~1v1beta2~1streams/get))
2. List 10 queries ([doc](https://docs.timeplus.com/rest.html#tag/Queries-v1beta2/paths/~1v1beta2~1queries/get))
3. Create query and get its results ([doc](https://docs.timeplus.com/rest.html#tag/Queries-v1beta2/paths/~1v1beta2~1queries/post))

You will need to setup the following environment variables:

```bash
export TIMEPLUS_API_KEY="<YOUR API KEY>"

# Make sure there is no trailing `/`
export TIMEPLUS_ADDRESS="https://us.timeplus.cloud" 

# This is the ID (not friendly name) of your workspace
export TIMEPLUS_WORKSPACE="<YOUR WORKSPACE ID>"
```

Then you can simply run `make run` to start the sample application.