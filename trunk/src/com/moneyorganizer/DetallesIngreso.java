package com.moneyorganizer;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

public class DetallesIngreso extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalles_ingreso);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_detalles_ingreso, menu);
        return true;
    }
}
