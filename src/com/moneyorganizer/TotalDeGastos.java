package com.moneyorganizer;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;

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

	public void agregarIngreso(View view) {
		startActivity(new Intent(getApplicationContext(),
				CategoriaIngreso.class));
	}

	public void agregarGasto(View view) {
		startActivity(new Intent(getApplicationContext(), CategoriaGasto.class));
	}
}
