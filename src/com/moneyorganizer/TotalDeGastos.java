package com.moneyorganizer;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

public class TotalDeGastos extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_total_de_gastos);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_total_de_gastos, menu);
        return true;
    }
}
