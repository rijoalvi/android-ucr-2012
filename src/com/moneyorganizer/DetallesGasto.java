package com.moneyorganizer;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

public class DetallesGasto extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalles_gasto);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_detalles_gasto, menu);
        return true;
    }
}
