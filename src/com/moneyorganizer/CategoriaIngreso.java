package com.moneyorganizer;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

public class CategoriaIngreso extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_categoria_ingreso);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_categoria_ingreso, menu);
        return true;
    }
}
