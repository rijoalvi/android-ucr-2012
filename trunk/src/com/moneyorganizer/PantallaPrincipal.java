package com.moneyorganizer;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

public class PantallaPrincipal extends Activity {

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_pantalla_principal);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.activity_pantalla_principal, menu);
		return true;
	}

}
