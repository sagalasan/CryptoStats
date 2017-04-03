package com.sagalasan.cryptostats;

import android.content.res.Configuration;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.GridLayout;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = MainActivity.class.getName();

    private boolean isPortrait;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        GridLayout mainGrid = (GridLayout) findViewById(R.id.main_grid);

        isPortrait = getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT;

        mainGrid.setColumnCount(isPortrait ? 2 : 3);

        Currency[] currencies = Currency.values();

        for (int i = 0; i < currencies.length; i++) {
            CurrencyView currencyView = new CurrencyView(mainGrid.getContext());
            currencyView.setCurrency(currencies[i]);

            GridLayout.LayoutParams currencyLayoutParams = new GridLayout.LayoutParams();
            currencyLayoutParams.columnSpec = GridLayout.spec(GridLayout.UNDEFINED, 1f);
            currencyLayoutParams.rowSpec = GridLayout.spec(GridLayout.UNDEFINED, 1f);
            currencyView.setLayoutParams(currencyLayoutParams);

            mainGrid.addView(currencyView);
        }
    }
}
