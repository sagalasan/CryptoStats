package com.sagalasan.cryptostats.bitfinex

/**
 * Created by christiaan on 4/5/17.
 */

data class TickerRequest(val pair: String) {
    val event: String = "subscribe"
    val channel: String = "ticker"
}

data class Ticker(val pair: String,
                  val channelId: Int,
                  val bid: Double,
                  val bidSize: Double,
                  val ask: Double,
                  val askSize: Double,
                  val dailyChange: Double,
                  val dailyChangePercent: Double,
                  val lastPrice: Double,
                  val volume: Double,
                  val high: Double,
                  val low: Double)