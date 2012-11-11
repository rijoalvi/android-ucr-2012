package com.moneyorganizer;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;

/*
 * Lista de categorias:
 * 1.Transportes
 * 2.Servicios
 * 3.Alimentacion
 * 4.Recreacion
 * 5.Tecnologia
 * 6.Ropa
 * 7.Impuestos
 * 8.Regalos
 * 9.Otros
 */
public class CategoriaGasto extends Activity {

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_categoria_gasto);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.activity_categoria_gasto, menu);
		return true;
	}

	public void gastoTransporte(View view) {
		Intent intento = new Intent(this, DetallesGasto.class);
		intento.putExtra("categoria", "1");
		startActivity(intento);
		finish();
	}

	public void gastoServicios(View view) {
		Intent intento = new Intent(this, DetallesGasto.class);
		intento.putExtra("categoria", "2");
		startActivity(intento);
		finish();
	}

	public void gastoAlimentacion(View view) {
		Intent intento = new Intent(this, DetallesGasto.class);
		intento.putExtra("categoria", "3");
		startActivity(intento);
		finish();
	}

	public void gastoRecreacion(View view) {
		Intent intento = new Intent(this, DetallesGasto.class);
		intento.putExtra("categoria", "4");
		startActivity(intento);
		finish();
	}

	public void gastoTecnologia(View view) {
		Intent intento = new Intent(this, DetallesGasto.class);
		intento.putExtra("categoria", "5");
		startActivity(intento);
		finish();
	}

	public void gastoRopa(View view) {
		Intent intento = new Intent(this, DetallesGasto.class);
		intento.putExtra("categoria", "6");
		startActivity(intento);
		finish();
	}

	public void gastoImpuestos(View view) {
		Intent intento = new Intent(this, DetallesGasto.class);
		intento.putExtra("categoria", "7");
		startActivity(intento);
		finish();
	}

	public void gastoRegalos(View view) {
		Intent intento = new Intent(this, DetallesGasto.class);
		intento.putExtra("categoria", "8");
		startActivity(intento);
		finish();
	}

	public void gastoOtros(View view) {
		Intent intento = new Intent(this, DetallesGasto.class);
		intento.putExtra("categoria", "9");
		startActivity(intento);
		finish();
	}

}
