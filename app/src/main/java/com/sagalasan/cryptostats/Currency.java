package com.sagalasan.cryptostats;

/**
 * Created by christiaan on 4/2/17.
 */

public enum Currency {
    BTC("Bitcoin"),
    ETH("Ethereum"),
    LTC("Litecoin"),
    XRP("Ripple"),
    DASH("Dash"),
    XMR("Monero");

    private String longName;

    Currency(String longName) {
        this.longName = longName;
    }

    public String getLongName() {
        return longName;
    }
}
