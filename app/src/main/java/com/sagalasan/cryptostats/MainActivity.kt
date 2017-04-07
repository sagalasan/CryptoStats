package com.sagalasan.cryptostats

import android.content.Intent
import android.content.res.Configuration
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.GridLayout
import com.sagalasan.cryptostats.bitfinex.Ticker
import com.sagalasan.cryptostats.bitfinex.TickerRequest
import com.sagalasan.cryptostats.websocket.WebSocketConnected
import com.sagalasan.cryptostats.websocket.WebSocketService
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe

class MainActivity : AppCompatActivity() {
    private val TAG = MainActivity::class.java.name

    private var isPortrait: Boolean = true

    private val pairs: Array<CurrencyPair> = CurrencyPair.getSix()

    private val eventBus = EventBus.getDefault()

    private val viewMap: MutableMap<String, CurrencyView> = HashMap()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val mainGrid = findViewById(R.id.main_grid) as GridLayout

        isPortrait = resources.configuration.orientation == Configuration.ORIENTATION_PORTRAIT

        mainGrid.columnCount = if (isPortrait) 2 else 3

        pairs.forEach { currencyPair ->
            val currencyView = CurrencyView(mainGrid.context)
            currencyView.setCurrency(currencyPair.left)

            val currencyLayoutParams = GridLayout.LayoutParams()
            currencyLayoutParams.columnSpec = GridLayout.spec(GridLayout.UNDEFINED, 1f)
            currencyLayoutParams.rowSpec = GridLayout.spec(GridLayout.UNDEFINED, 1f)
            currencyView.layoutParams = currencyLayoutParams

            mainGrid.addView(currencyView)

            viewMap.put(currencyPair.name, currencyView)

        }

        val intent = Intent(this, WebSocketService::class.java)
        startService(intent)

        eventBus.register(this)
    }

    @Subscribe
    fun onWebSocketConnected(webSocketConnected: WebSocketConnected) {
        val eventBus = EventBus.getDefault()
        pairs.map { TickerRequest(it.name) }.forEach { eventBus.post(it) }
    }

    @Subscribe
    fun onTickerReceived(ticker: Ticker) {
        Log.d(TAG, "Ticker: " + ticker)
        val pair = ticker.pair
        val price = ticker.ask
        runOnUiThread { viewMap[pair]?.updatePrice(price) }
    }
}
