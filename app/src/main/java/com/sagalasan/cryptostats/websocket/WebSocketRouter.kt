package com.sagalasan.cryptostats.websocket

import android.util.Log
import com.sagalasan.cryptostats.bitfinex.Ticker
import org.json.JSONArray
import org.json.JSONObject

/**
 * Created by christiaan on 4/6/17.
 */
class WebSocketRouter(val webSocketService: WebSocketService) {
    private val TAG = WebSocketRouter::class.java.name
    private val EVENT = "event"
    private val CHANNEL = "channel"
    private val CHANNEL_ID = "chanId"
    private val PAIR = "pair"

    private val TICKER = "ticker"

    // Map channelId -> Pair
    private val channelPairMap: MutableMap<Int, String> = HashMap()

    // Map channelId -> channelType
    private val channelTypeMap: MutableMap<Int, String> = HashMap()

    /**
     * Method called by whoever is receiving the websocket messages
     */
    fun response(msg: String) {
        if (msg[0] == '{') handleJsonObject(msg)
        else if (msg[0] == '[') handleJsonArray(msg)
        else Log.e(TAG, "Received message that was not an object or an array")
    }

    private fun handleJsonObject(msg: String) {
        val obj = JSONObject(msg)
        Log.d(TAG, "Object: " + obj)

        if (obj.getString(EVENT) == "subscribed") handleSubscription(obj)
    }

    private fun handleJsonArray(msg: String) {
        val array = JSONArray(msg)
        Log.d(TAG, "Array: " + array)

        val channelId = array.getInt(0)
        val channelType = channelTypeMap.getValue(channelId)

        if (array[1] == ("hb")) webSocketService.onHeartBeatReceived(channelId)
        else if (channelType == TICKER) {
            handleTicker(array)
        } else {
            Log.d(TAG, "Unknown channel type")
        }
    }

    private fun handleSubscription(obj: JSONObject) {
        val channelType = obj.getString(CHANNEL)
        if (channelType == TICKER) handleTickerSubscribe(obj)
    }

    private fun handleTickerSubscribe(obj: JSONObject) {
        val channelId: Int = obj.getInt(CHANNEL_ID)
        val channeltype: String = obj.getString(CHANNEL)
        val pair: String = obj.getString(PAIR)

        channelPairMap.put(channelId, pair)
        channelTypeMap.put(channelId, channeltype)
    }

    private fun handleTicker(array: JSONArray) {
        val channelId          = array.getInt(0)
        val bid                = array.getDouble(1)
        val bidSize            = array.getDouble(2)
        val ask                = array.getDouble(3)
        val askSize            = array.getDouble(4)
        val dailyChange        = array.getDouble(5)
        val dailyChangePercent = array.getDouble(6)
        val lastPrice          = array.getDouble(7)
        val volume             = array.getDouble(8)
        val high               = array.getDouble(9)
        val low                = array.getDouble(10)
        val pair               = channelPairMap.getValue(channelId)

        val ticker = Ticker(pair, channelId, bid, bidSize, ask, askSize, dailyChange, dailyChangePercent,
                lastPrice, volume, high, low)

        webSocketService.onTickerReceived(ticker)
    }

}