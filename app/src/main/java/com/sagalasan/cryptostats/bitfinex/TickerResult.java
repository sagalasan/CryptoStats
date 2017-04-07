package com.sagalasan.cryptostats.bitfinex;

import com.sagalasan.cryptostats.Currency;

/**
 * Created by christiaan on 4/4/17.
 */

public class TickerResult {
    private Currency currency;
    private float bid;
    private float bidSize;
    private float ask;
    private float askSize;
    private float dailyChange;
    private float dailyChangePerc;
    private float lastPrice;
    private float volume;
    private float high;
    private float low;

    public Currency getCurrency() {
        return currency;
    }

    public void setCurrency(Currency currency) {
        this.currency = currency;
    }

    public float getBid() {
        return bid;
    }

    public void setBid(float bid) {
        this.bid = bid;
    }

    public float getBidSize() {
        return bidSize;
    }

    public void setBidSize(float bidSize) {
        this.bidSize = bidSize;
    }

    public float getAsk() {
        return ask;
    }

    public void setAsk(float ask) {
        this.ask = ask;
    }

    public float getAskSize() {
        return askSize;
    }

    public void setAskSize(float askSize) {
        this.askSize = askSize;
    }

    public float getDailyChange() {
        return dailyChange;
    }

    public void setDailyChange(float dailyChange) {
        this.dailyChange = dailyChange;
    }

    public float getDailyChangePerc() {
        return dailyChangePerc;
    }

    public void setDailyChangePerc(float dailyChangePerc) {
        this.dailyChangePerc = dailyChangePerc;
    }

    public float getLastPrice() {
        return lastPrice;
    }

    public void setLastPrice(float lastPrice) {
        this.lastPrice = lastPrice;
    }

    public float getVolume() {
        return volume;
    }

    public void setVolume(float volume) {
        this.volume = volume;
    }

    public float getHigh() {
        return high;
    }

    public void setHigh(float high) {
        this.high = high;
    }

    public float getLow() {
        return low;
    }

    public void setLow(float low) {
        this.low = low;
    }
}
