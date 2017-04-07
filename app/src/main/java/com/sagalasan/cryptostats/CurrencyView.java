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

public class CurrencyView extends CardView {
    private static final String UP_ARROW= "▲";
    private static final String DOWN_ARROW = "▼";

    private View rootView;
    private TextView currencyLabel;
    private TextView currencyPrice;

    private double currentPriceValue;
    private boolean isPriceSet = false;

    public CurrencyView(Context context) {
        super(context);
        init(context);
    }

    public CurrencyView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CurrencyView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public CurrencyView setCurrency(Currency currency) {
        currencyLabel.setText(currency.getLongName());
        return this;
    }

    public void updatePrice(double price) {
        String arrow = isPriceSet ? ((price < currentPriceValue) ? DOWN_ARROW : UP_ARROW) : "";
        currencyPrice.setText(String.format(Locale.US, "$%s %s", price, arrow));
        currentPriceValue = price;
        isPriceSet = true;
    }

    private void init(Context context) {
        rootView = inflate(context, R.layout.currency_card_view, this);
        currencyLabel = (TextView) rootView.findViewById(R.id.currency_label);
        currencyPrice = (TextView) rootView.findViewById(R.id.currency_price);
    }

}
