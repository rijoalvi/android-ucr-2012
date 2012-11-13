package com.moneyorganizer;

import java.util.ArrayList;
import java.util.List;
import com.moneyorganizer.db.ControladorBD;
import com.moneyorganizer.elementos.Ingreso;
import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

public class TotalDeIngresos extends Activity {
	
	int mes;
	int anio;
	IngresoAdapter adapter;
	List<Ingreso> losIngresos; 
	ControladorBD controlador;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_total_de_ingresos);
		ListView listaIngresos = (ListView) findViewById(R.id.lista_ingresos);
		listaIngresos.setAdapter(new IngresoAdapter(this,
				new ArrayList<Ingreso>()));
		losIngresos = new ArrayList<Ingreso>();
		controlador = new ControladorBD(this);
		Bundle bundle = getIntent().getExtras();
		mes = bundle.getInt("mes");
		anio = bundle.getInt("anio");
		losIngresos = controlador.getTodosLosIngresos();
		adapter = (IngresoAdapter) listaIngresos.getAdapter();
		adapter.addList(losIngresos);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.activity_total_de_ingresos, menu);
		return true;
	}

	public void agregarIngreso(View view) {
		startActivity(new Intent(getApplicationContext(),
				CategoriaIngreso.class));	
		finish();
	}

	public void agregarGasto(View view) {
		startActivity(new Intent(getApplicationContext(), CategoriaGasto.class));
		finish();
	}

	private class IngresoAdapter extends BaseAdapter {
		List<Ingreso> ingreso;
		LayoutInflater inflater;

		public IngresoAdapter(Context context, List<Ingreso> ingresos) {
			this.ingreso = ingresos;
			inflater = (LayoutInflater) context
					.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		}

		public void addList(List<Ingreso> gastos) {
			this.ingreso.addAll(gastos);
			notifyDataSetChanged();// refresh
		}

		public void clear() {
			this.ingreso.clear();
			notifyDataSetChanged();// refresh
		}

		public int getCount() {
			return ingreso.size();
		}

		public List<Ingreso> getSongs() {
			return ingreso;
		}

		public Object getItem(int position) {
			return ingreso.get(position);
		}

		public void refresh() {
			this.ingreso = this.ingreso;
			notifyDataSetChanged();// refresh
		}

		public long getItemId(int position) {
			return position;
		}

		public View getView(int position, View convertView, ViewGroup parent) {
			Ingreso ingreso = (Ingreso) getItem(position);
			GastoViewHolder holder = null;
			if (convertView == null) {
				convertView = inflater.inflate(R.layout.list_view_layout,parent, false);
			/*	convertView.setOnLongClickListener(new OnLongClickListener() {
					
					@Override
					public boolean onLongClick(AdapterView<Adapter> adapter, View v) {
						
						 v.
						return false;
					}
				})*/
				holder = new GastoViewHolder();
				convertView.setTag(holder);
				holder.fuente = (TextView) convertView.findViewById(R.id.lugar_fuente);
				holder.monto = (TextView) convertView.findViewById(R.id.monto);
				holder.fecha = (TextView) convertView.findViewById(R.id.fecha);
			} else {
				holder = (GastoViewHolder) convertView.getTag();
			}

			holder.fuente.setText(ingreso.getFuente());
			holder.fuente.setTag(ingreso.getId());
			holder.monto.setText(Integer.toString(ingreso.getMonto()));
			holder.monto.setTag(ingreso.getId());
			String fechaGasto = String.valueOf(ingreso.getDia()) + "/"
					+ String.valueOf(ingreso.getMes()) + "/"
					+ String.valueOf(ingreso.getAnio());
			holder.fecha.setText(fechaGasto);
			holder.fecha.setTag(ingreso.getId());
			return convertView;
		}
	}

	private static class GastoViewHolder {
		public TextView fecha;
		public TextView monto;
		public TextView fuente;
		public ImageView imagenGasto;
	}
}
