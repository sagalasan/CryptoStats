package com.sagalasan.cryptostats;

/**
 * Created by christiaan on 4/2/17.
 */

public enum Currency {
    BTC("Bitcoin"),
    ETH("Ethereum"),
    ETC("Ethereum Classic"),
    ZEC("Zcash"),
    LTC("Litecoin"),
    XRP("Ripple"),
    DSH("Dash"),
    XMR("Monero"),
    USD("United States Dollar");

    private String longName;

    Currency(String longName) {
        this.longName = longName;
    }

    public String getLongName() {
        return longName;
    }
}
