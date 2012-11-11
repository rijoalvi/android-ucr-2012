package com.moneyorganizer;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;

public class DetallesGasto extends Activity {

	int categoriaGasto;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_detalles_gasto);
		Bundle bundle = getIntent().getExtras();
		categoriaGasto = Integer.parseInt(bundle.getString("categoria"));
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.activity_detalles_gasto, menu);
		return true;
	}
	
	public void guardarGasto(View view){
		//guardar
		finish();
	}
}
