package com.sagalasan.cryptostats;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.util.AttributeSet;
import android.view.View;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.util.Locale;

/**
 * Created by christiaan on 4/2/17.
 */

class CurrencyView(context: Context) : CardView(context) {
    private val UP_ARROW = " ▲"
    private val DOWN_ARROW = " ▼"
    private var arrowUp = true

    private var view: View? = null
    private var currencyLabel: TextView? = null
    private var currencyPrice: TextView? = null
    private var currencyArrow: TextView? = null

    private var currentPriceValue: Double = -1.0
    private var isPriceSet: Boolean = false

    init {
        view = inflate(context, R.layout.currency_card_view, this)
        currencyLabel = view?.findViewById(R.id.currency_label) as TextView
        currencyPrice = view?.findViewById(R.id.currency_price) as TextView
        currencyArrow = view?.findViewById(R.id.currency_arrow) as TextView
    }

    fun setCurrency(currency: Currency) {
        currencyLabel?.text = currency.longName
    }

    fun updatePrice(price: Double) {
        currencyPrice?.text = String.format(Locale.US, "$%s", price)
        arrowUp = if (price == currentPriceValue) arrowUp else price > currentPriceValue
        currentPriceValue = price
        isPriceSet = true

        updateArrow()
    }

    private fun updateArrow() {
        currencyArrow?.text = if (arrowUp) UP_ARROW else DOWN_ARROW
    }
}
