package com.sagalasan.cryptostats.websocket

import android.util.Log
import com.koushikdutta.async.http.AsyncHttpClient
import com.koushikdutta.async.http.Protocol
import com.koushikdutta.async.http.WebSocket

/**
 * Created by christiaan on 4/5/17.
 */
class WebSocketThread(val location: String,
                      val router: WebSocketRouter,
                      val listener: WebSocketConnectedListener) : Thread() {
    val TAG: String = WebSocketThread::class.java.simpleName
    var isRunning: Boolean = true
    var isConnected: Boolean = false

    var webSocket: WebSocket? = null

    override fun run() {
        Log.d(TAG, "Starting thread")
        AsyncHttpClient.getDefaultInstance().websocket(location, Protocol.HTTP_1_1.toString(),
                { ex, webSocket -> handleWebSocketCreated(webSocket)})
    }

    fun handleWebSocketCreated(webSocket: WebSocket) {
        this.webSocket = webSocket
        webSocket.setPongCallback { s -> Log.d(TAG, "Pong Received! " + s) }
        webSocket.setStringCallback {s -> router.response(s)}//{router::response}
        webSocket.ping("")

        isConnected = true

        listener.connected()
    }

    fun sendMessage(msg: String) {
        if (isConnected) webSocket?.send(msg)
        else Log.e(TAG, "Could not send message, not connected")
    }
}