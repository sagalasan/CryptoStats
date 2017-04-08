package com.sagalasan.cryptostats;

import java.util.Arrays;
import java.util.List;

/**
 * Created by christiaan on 4/4/17.
 */

public enum CurrencyPair {
    BTCUSD(Currency.BTC, Currency.USD),
    ETHUSD(Currency.ETH, Currency.USD),
    LTCUSD(Currency.LTC, Currency.USD),
    ZECUSD(Currency.ZEC, Currency.USD),
    DSHUSD(Currency.DSH, Currency.USD),
    XMRUSD(Currency.XMR, Currency.USD);

    private Currency left;
    private Currency right;

    CurrencyPair(Currency left, Currency right) {
        this.left = left;
        this.right = right;
    }

    public Currency getLeft() {
        return left;
    }

    public Currency getRight() {
        return right;
    }

    public static List<CurrencyPair> getSix() {
        CurrencyPair[] pairs =  {BTCUSD, ETHUSD, LTCUSD, ZECUSD, DSHUSD, XMRUSD};
        return Arrays.asList(pairs);
    }

    @Override
    public String toString() {
        return name();
    }
}
