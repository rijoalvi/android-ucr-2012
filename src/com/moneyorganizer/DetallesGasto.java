package com.moneyorganizer;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.moneyorganizer.db.ControladorBD;
import com.moneyorganizer.elementos.Gasto;

import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Activity;
import android.content.ContentValues;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

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

	public void guardarGasto(View view) {
		guardarGasto();
		finish();
	}

	private void guardarGasto() {
		// Se obtienen los campos de la interfaz
		EditText monto = (EditText) findViewById(R.id.detalles_gasto_monto);
		EditText lugar = (EditText) findViewById(R.id.detalles_gasto_lugar);
		EditText detalle = (EditText) findViewById(R.id.detalles_gasto_detalles);

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

			// Se crea el objeto Gasto a guardar
			Gasto gasto = new Gasto(categoriaGasto, fecha,
					Integer.parseInt(fechaDigitos[2]),
					Integer.parseInt(fechaDigitos[1]),
					Integer.parseInt(fechaDigitos[0]), Integer.parseInt(monto
							.getText().toString()), lugar.getText().toString(),
					detalle.getText().toString());
			Log.d("", "Se va a guardar:" + categoriaGasto + " "
					+ fecha + " "
					+ fechaDigitos[2] + " " + fechaDigitos[1] + " "
					+ fechaDigitos[0] + " " + monto.getText().toString() + " "
					+ lugar.getText().toString() + " "
					+ detalle.getText().toString());
			// guardarSong

			new GuardarGastoTask().execute(gasto);
		} else {
			String mensaje = "Error al guardar el gasto";
			Toast.makeText(DetallesGasto.this, mensaje, Toast.LENGTH_SHORT)
					.show();
		}
	}

	private class GuardarGastoTask extends AsyncTask<Gasto, Void, Boolean> {

		public GuardarGastoTask() {

		}

		@Override
		protected Boolean doInBackground(Gasto... params) {
			ControladorBD control = new ControladorBD(DetallesGasto.this);

			Gasto gasto = params[0];

			if (gasto != null) {
				return control.guardarGasto(gasto);
			}
			return null;
		}

		@Override
		protected void onPostExecute(Boolean result) {
			if (result != null && result) {
				String mensaje = "Gasto guardado con ƒxito";
				Toast.makeText(DetallesGasto.this, mensaje, Toast.LENGTH_SHORT)
						.show();
			}
			super.onPostExecute(result);
		}

		@Override
		protected void onPreExecute() {
		}

	}
}
