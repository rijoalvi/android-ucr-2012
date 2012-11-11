package com.moneyorganizer;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;

public class DetallesIngreso extends Activity {
	
	int categoriaIngreso;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalles_ingreso);
        Bundle bundle=getIntent().getExtras();
        categoriaIngreso= Integer.parseInt(bundle.getString("categoria"));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_detalles_ingreso, menu);
        return true;
    }
    
    public void guardarIngreso(View view){
		//guardar
		finish();
	}
}
