package com.moneyorganizer;

import java.util.ArrayList;
import java.util.List;

import com.moneyorganizer.db.ControladorBD;
import com.moneyorganizer.elementos.Gasto;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.HeaderViewListAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.CompoundButton.OnCheckedChangeListener;

public class TotalDeGastos extends Activity {
	int mes;
	int anio;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_total_de_gastos);
		ListView listaGastos = (ListView) findViewById(R.id.lista_gastos);
		listaGastos.setAdapter(new GastoAdapter(this, new ArrayList<Gasto>()));
		List<Gasto> losGastos = new ArrayList<Gasto>();
		ControladorBD controlador = new ControladorBD(this);
		
		Bundle bundle = getIntent().getExtras();
		mes = bundle.getInt("mes");
		anio = bundle.getInt("anio");
		
		
		losGastos = controlador.getTodosLosGastosDelMes(mes, anio);
		GastoAdapter adapter = (GastoAdapter) listaGastos.getAdapter();
		adapter.addList(losGastos);
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

	private class GastoAdapter extends BaseAdapter {
		List<Gasto> gastos;
		LayoutInflater inflater;

		public GastoAdapter(Context context, List<Gasto> gastos) {
			this.gastos = gastos;
			inflater = (LayoutInflater) context
					.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		}

		public void addList(List<Gasto> gastos) {
			this.gastos.addAll(gastos);
			notifyDataSetChanged();// refresh
		}

		public void clear() {
			this.gastos.clear();
			notifyDataSetChanged();// refresh
		}

		public int getCount() {
			return gastos.size();
		}

		public List<Gasto> getSongs() {
			return gastos;
		}

		public Object getItem(int position) {
			return gastos.get(position);
		}

		public void refresh() {
			this.gastos = this.gastos;
			notifyDataSetChanged();// refresh
		}

		public long getItemId(int position) {
			return position;
		}

		public View getView(int position, View convertView, ViewGroup parent) {
			Gasto gastos = (Gasto) getItem(position);

			GastoViewHolder holder = null;
			if (convertView == null) {

				convertView = inflater.inflate(R.layout.list_view_layout,
						parent, false);

				holder = new GastoViewHolder();
				convertView.setTag(holder);

				holder.lugar = (TextView) convertView
						.findViewById(R.id.lugar_fuente);
				holder.monto = (TextView) convertView.findViewById(R.id.monto);
				holder.fecha = (TextView) convertView.findViewById(R.id.fecha);
			} else {
				holder = (GastoViewHolder) convertView.getTag();
			}

			holder.lugar.setText(gastos.getLugar());
			holder.lugar.setTag(gastos.getId());

			holder.monto.setText(Integer.toString(gastos.getMonto()));
			holder.monto.setTag(gastos.getId());
			String fechaGasto = String.valueOf(gastos.getDia()) + "/"
					+ String.valueOf(gastos.getMes()) + "/"
					+ String.valueOf(gastos.getAnio());
			holder.fecha.setText(fechaGasto);
			holder.fecha.setTag(gastos.getId());

			// holder.favorite.setOnCheckedChangeListener(null);
			// holder.favorite.setChecked(true);
			// holder.favorite
			// .setOnCheckedChangeListener(new OnCheckedChangeListener() {
			// public void onCheckedChanged(CompoundButton buttonView,
			// boolean isChecked) {
			// String id = buttonView.getTag().toString();
			// if (id != null) {
			//
			// final int songID = Integer.parseInt(id);
			// AlertDialog.Builder dialog = new AlertDialog.Builder(
			// Favorites.this);
			// dialog.setTitle("Confirmation");
			// dialog.setMessage("Do you really want to remove this favorite?");
			// dialog.setPositiveButton("Si",
			// new DialogInterface.OnClickListener() {
			// public void onClick(
			// DialogInterface dialog,
			// int id) {
			// Log.d("", "el ID es: " + id);
			// removeSong(songID);
			// }
			// });
			// dialog.setNegativeButton("No",
			// new DialogInterface.OnClickListener() {
			// public void onClick(
			// DialogInterface dialog,
			// int id) {
			// MusicAdapter adapter = (MusicAdapter) ((HeaderViewListAdapter)
			// mSongsList
			// .getAdapter())
			// .getWrappedAdapter();
			// adapter.refresh();
			//
			// }
			// });
			// dialog.show();
			// }
			//
			// }
			// });
			return convertView;
		}
	}

	private static class GastoViewHolder {
		public TextView fecha;
		public TextView monto;
		public TextView lugar;
		public ImageView imagenGasto;
	}
}
