package com.moneyorganizer;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.moneyorganizer.db.ControladorBD;
import com.moneyorganizer.elementos.Ingreso;

import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Activity;
import android.content.ContentValues;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class DetallesIngreso extends Activity {

	int categoriaIngreso;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_detalles_ingreso);
		Bundle bundle = getIntent().getExtras();
		categoriaIngreso = Integer.parseInt(bundle.getString("categoria"));
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.activity_detalles_ingreso, menu);
		return true;
	}

	public void guardarIngreso(View view) {
		guardarIngreso();
		finish();
	}

	private void guardarIngreso() {
		// Se obtienen los campos de la interfaz
		EditText monto = (EditText) findViewById(R.id.detalles_ingreso_monto);
		EditText fuente = (EditText) findViewById(R.id.detalles_ingreso_fuente);
		EditText detalle = (EditText) findViewById(R.id.detalles_ingreso_detalles);

		// Se obtiene la fecha
		SimpleDateFormat dateFormat = new SimpleDateFormat(
				"yyyy-MM-dd HH:mm:ss");
		Date date = new Date();
		String fecha = null;
		fecha = dateFormat.format(date);
		String temp[] = fecha.split(" ");
		String fechaDigitos[] = null;
		String horaDigitos[] = null;
		if (temp.length > 1) {
			fechaDigitos = temp[0].split("-");
			horaDigitos = temp[1].split(":");

			// Se crea el objeto Ingreso a guardar
			Ingreso ingreso = new Ingreso(categoriaIngreso,
					fecha,
					Integer.parseInt(fechaDigitos[2]),
					Integer.parseInt(fechaDigitos[1]),
					Integer.parseInt(fechaDigitos[0]), Integer.parseInt(monto
							.getText().toString()),
					fuente.getText().toString(), detalle.getText().toString());
			Log.d("", "Se va a guardar:" + categoriaIngreso + " "
					+ fecha + " "
					+ fechaDigitos[2] + " " + fechaDigitos[1] + " "
					+ fechaDigitos[0] + " " + monto.getText().toString() + " "
					+ fuente.getText().toString() + " "
					+ detalle.getText().toString());
			// guardarSong

			new GuardarIngresoTask().execute(ingreso);
		} else {
			String mensaje = "Error al guardar el gasto";
			Toast.makeText(DetallesIngreso.this, mensaje, Toast.LENGTH_SHORT)
					.show();
		}

	}

	private class GuardarIngresoTask extends AsyncTask<Ingreso, Void, Boolean> {

		public GuardarIngresoTask() {

		}

		@Override
		protected Boolean doInBackground(Ingreso... params) {
			ControladorBD control = new ControladorBD(DetallesIngreso.this);

			Ingreso ingreso = params[0];

			if (ingreso != null) {
				return control.guardarIngreso(ingreso);
			}
			return null;
		}

		@Override
		protected void onPostExecute(Boolean result) {
			if (result != null && result) {
				String mensaje = "Ingreso guardado con �xito";
				Toast.makeText(DetallesIngreso.this, mensaje,
						Toast.LENGTH_SHORT).show();
			}
			super.onPostExecute(result);
		}

		@Override
		protected void onPreExecute() {
		}

	}
}
