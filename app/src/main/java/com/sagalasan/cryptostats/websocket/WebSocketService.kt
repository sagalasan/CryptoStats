package com.sagalasan.cryptostats.websocket

import android.app.Service
import android.content.Context
import android.content.Intent
import android.os.IBinder
import android.util.Log
import android.widget.Toast
import com.fasterxml.jackson.annotation.JsonAutoDetect
import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.databind.DeserializationFeature
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.introspect.VisibilityChecker
import com.sagalasan.cryptostats.Currency
import com.sagalasan.cryptostats.CurrencyPair
import com.sagalasan.cryptostats.bitfinex.Ticker
import com.sagalasan.cryptostats.bitfinex.TickerRequest
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe

/**
 * Created by christiaan on 4/5/17.
 */
class WebSocketService : Service(), WebSocketConnectedListener {
    private val TAG: String = this.javaClass.name
    private val URL: String = "wss://api.bitfinex.com/ws"

    private val webSocketThread: WebSocketThread = WebSocketThread(URL, WebSocketRouter(this), this)

    private val eventBus: EventBus = EventBus.getDefault()

    private val subscribedPairs: MutableSet<String> = HashSet()

    private val objectMapper: ObjectMapper = ObjectMapper()

    override fun onCreate() {
        Toast.makeText(this, "onCreate()", Toast.LENGTH_SHORT).show()
        webSocketThread.start()

        objectMapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES)
                .setSerializationInclusion(JsonInclude.Include.NON_NULL)
                .setVisibilityChecker(VisibilityChecker.Std.defaultInstance()
                        .withFieldVisibility(JsonAutoDetect.Visibility.ANY))

        eventBus.register(this)
    }

    override fun onStartCommand(intent: Intent, flags: Int, startId: Int): Int {
        Toast.makeText(this, "Service starting", Toast.LENGTH_SHORT).show()
        return START_NOT_STICKY
    }

    override fun onBind(intent: Intent): IBinder? {
        return null
    }

    fun onHeartBeatReceived(chanId: Int) {
        Log.d(TAG, "HEART: " + chanId)
    }

    fun onTickerReceived(ticker: Ticker) {
        eventBus.post(ticker)
    }

    @Subscribe
    fun onTickerRequestReceived(tickerRequest: TickerRequest) {
        subscribedPairs.add(tickerRequest.pair)
        webSocketThread.sendMessage(objectMapper.writeValueAsString(tickerRequest))
    }

    // TODO Notify activity
    // TODO send subscribe requests for the coins
    override fun connected() {
        Log.d(TAG, "WE ARE CONNECTED")
        eventBus.post(WebSocketConnected(true))
    }
}

interface WebSocketConnectedListener { fun connected() }