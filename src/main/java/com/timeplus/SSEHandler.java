package com.timeplus;

import java.io.BufferedReader;
import java.io.IOException;

import com.squareup.okhttp.Response;

public class SSEHandler {

    private Response response = null;

    public SSEHandler(Response response) {
        this.response = response;
    }

    public void handle() {
        try {
            BufferedReader br = new BufferedReader(response.body().charStream());
            String line;
            while ((line = br.readLine()) != null) {
                System.out.println(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
