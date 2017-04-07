package com.sagalasan.cryptostats.websocket;

/**
 * Created by christiaan on 4/4/17.
 */

public class WebSocketConnected {
    private final boolean isStarted;

    public WebSocketConnected(boolean isStarted) {
        this.isStarted = isStarted;
    }

    public boolean isStarted() {
        return isStarted;
    }
}
