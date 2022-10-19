package timeplus.io;

import java.net.URI;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.java_websocket.client.WebSocketClient;
import org.java_websocket.drafts.Draft;
import org.java_websocket.handshake.ServerHandshake;

class MyWSClient extends WebSocketClient {
    static Logger logg = LoggerFactory.getLogger(MyWSClient.class);

    private List<Observer> observers;

    public MyWSClient(URI serverUri, Draft draft) {
        super(serverUri, draft);
        this.observers = new LinkedList<Observer>();
    }

    public MyWSClient(URI serverURI) {
        super(serverURI);
        this.observers = new LinkedList<Observer>();
    }

    public MyWSClient(URI serverUri, Map<String, String> httpHeaders) {
        super(serverUri, httpHeaders);
        this.observers = new LinkedList<Observer>();
    }

    @Override
    public void onOpen(ServerHandshake handshakedata) {
    }

    @Override
    public void onMessage(String message) {
        for (Observer observer : this.observers) {
            observer.handleMessage(message);
        }
    }

    @Override
    public void onClose(int code, String reason, boolean remote) {
        logg.debug(
                "Connection closed by " + (remote ? "remote peer" : "us") + " Code: " + code + " Reason: "
                        + reason);
    }

    @Override
    public void onError(Exception ex) {
        ex.printStackTrace();
    }

    public void addObserver(Observer ob) {
        this.observers.add(ob);
    }
}

public class QueryResultWatcher {
    static Logger logg = LoggerFactory.getLogger(QueryResultWatcher.class);
    private MyWSClient wsClient;

    public QueryResultWatcher(TimeplusClient client, String queryId, Observer messageHandler) {
        String baseUrl = client.baseUrl()
                .replace("http", "ws")
                .replace("/api/v1beta1", "");
        String wsUrl = baseUrl + "ws/queries/" + queryId;
        URI wsURI = URI.create(wsUrl);

        logg.debug("WS URI is " + wsURI);

        Map<String, String> httpHeaders = new HashMap<String, String>();
        httpHeaders.put("x-api-key", client.apiKey());
        this.wsClient = new MyWSClient(wsURI, httpHeaders);
        this.wsClient.addObserver(messageHandler);
    }

    public void watch() {
        this.wsClient.connect();
    }

    public synchronized void stop() {
        this.wsClient.close();
    }

}
