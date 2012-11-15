package com.moneyorganizer;

import java.util.ArrayList;
import java.util.List;

import com.moneyorganizer.db.ControladorBD;
import com.moneyorganizer.elementos.Ingreso;

import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemLongClickListener;

public class TotalDeIngresos extends Activity {
	
	int mes;
	int anio;
	Ingreso listoParaEliminar;
	IngresoAdapter adapter;
	List<Ingreso> losIngresos; 
	ControladorBD controlador;
	private float tipoDeCambio;
	private boolean estaEnDolares;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_total_de_ingresos);
		ListView listaIngresos = (ListView) findViewById(R.id.lista_ingresos);
		listaIngresos.setAdapter(new IngresoAdapter(this,
				new ArrayList<Ingreso>()));
		listaIngresos.setOnItemLongClickListener(new OnItemLongClickListener() {

			@Override
			public boolean onItemLongClick(AdapterView<?> adapterView,
					View view, int position, long id) {
				listoParaEliminar = (Ingreso) adapterView.getAdapter().getItem(
						position);
				AlertDialog.Builder builder = new AlertDialog.Builder(
						TotalDeIngresos.this);
				builder.setTitle("¡Atención!");
				builder.setMessage("¿Está seguro de que desea borrar este ingreso?");
				builder.setPositiveButton("Si",
						new DialogInterface.OnClickListener() {
							@Override
							public void onClick(DialogInterface dialog, int which) {
								Log.d("", "Borrando Ingreso");
								new Borrador().execute(listoParaEliminar);
								listoParaEliminar= null;
							}
						});

				builder.setNegativeButton("No",
						new DialogInterface.OnClickListener() {
							@Override
							public void onClick(DialogInterface dialog, int which) {
								listoParaEliminar = null;
							}
						});

				builder.show();

				return true;
			}
		});
		
		Bundle bundle = getIntent().getExtras();
		mes = bundle.getInt("mes");
		anio = bundle.getInt("anio");
		tipoDeCambio=bundle.getFloat("tipoDeCambio");
		estaEnDolares = bundle.getBoolean("dolares");
		
		//AsyncTask que llena el adaptador
		new llenaLista().execute();
		
		
		
		
//		losIngresos = new ArrayList<Ingreso>();
//		controlador = new ControladorBD(this);
//		Bundle bundle = getIntent().getExtras();
//		mes = bundle.getInt("mes");
//		anio = bundle.getInt("anio");
//		losIngresos = controlador.getTodosLosIngresos();
//		adapter = (IngresoAdapter) listaIngresos.getAdapter();
//		adapter.addList(losIngresos);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.activity_total_de_ingresos, menu);
		return true;
	}

	public void agregarIngreso(View view) {
		Intent intento = new Intent(getApplicationContext(),
				CategoriaIngreso.class);
		intento.putExtra("dolares", estaEnDolares);
		intento.putExtra("tipoDeCambio",tipoDeCambio);
		startActivity(intento);	
		finish();
	}

	public void agregarGasto(View view) {
		Intent intento =new Intent(getApplicationContext(), CategoriaGasto.class); 
		intento.putExtra("dolares", estaEnDolares);
		intento.putExtra("tipoDeCambio",tipoDeCambio);
		startActivity(intento);
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

		public List<Ingreso> getIngresos() {
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
			IngresoViewHolder holder = null;
			if (convertView == null) {
				convertView = inflater.inflate(R.layout.list_view_layout,parent, false);
			
				holder = new IngresoViewHolder();
				convertView.setTag(holder);
				holder.fuente = (TextView) convertView.findViewById(R.id.lugar_fuente);
				holder.monto = (TextView) convertView.findViewById(R.id.monto);
				holder.fecha = (TextView) convertView.findViewById(R.id.fecha);
			} else {
				holder = (IngresoViewHolder) convertView.getTag();
			}
			
			float monto = ingreso.getMonto();
			if(estaEnDolares){
				monto = monto /tipoDeCambio;
			}

			holder.fuente.setText(ingreso.getFuente());
			holder.fuente.setTag(ingreso.getId());
			holder.monto.setText(Float.toString(monto));
			holder.monto.setTag(ingreso.getId());
			String fechaIngreso = String.valueOf(ingreso.getDia()) + "/"
					+ String.valueOf(ingreso.getMes()) + "/"
					+ String.valueOf(ingreso.getAnio());
			holder.fecha.setText(fechaIngreso);
			holder.fecha.setTag(ingreso.getId());
			return convertView;
		}
	}

	private static class IngresoViewHolder {
		public TextView fecha;
		public TextView monto;
		public TextView fuente;
		public ImageView imagenIngreso;
	}
	
	private class Borrador extends AsyncTask<Ingreso, Void, Boolean> {

		public Borrador() {
			
		}

		@Override
		protected Boolean doInBackground(Ingreso... params) {
			
			Ingreso gastoABorrar = null;
			gastoABorrar = params[0];
			

			if (gastoABorrar != null) {
				ControladorBD controlador = new ControladorBD(TotalDeIngresos.this);
					return controlador.borrarIngreso(gastoABorrar);
			}

			return null;
		}

		@Override
		protected void onPostExecute(Boolean result) {
			if (result != null && result) {
				String mensaje = "Ingreso eliminado";
				Toast.makeText(TotalDeIngresos.this, mensaje, Toast.LENGTH_SHORT)
						.show();
				ListView lista = (ListView) findViewById(R.id.lista_ingresos);
				IngresoAdapter adapter = (IngresoAdapter) lista.getAdapter();
				adapter.clear();
				new llenaLista().execute();
				adapter.refresh();
			}
			super.onPostExecute(result);
		}

		@Override
		protected void onPreExecute() {
		}

	}
	
	private class llenaLista extends AsyncTask<Void, Void, List<Ingreso>>{

		public llenaLista(){
			
		}
		
		@Override
		protected List<Ingreso> doInBackground(Void... params) {
			List<Ingreso> losIngresos = new ArrayList<Ingreso>();
			ControladorBD controlador = new ControladorBD(TotalDeIngresos.this);
			losIngresos = controlador.getTodosLosIngresosDelMes(mes, anio);
			return losIngresos;
		}

		@Override
		protected void onPostExecute(List<Ingreso> result) {
			ListView lista = (ListView) findViewById(R.id.lista_ingresos);
			IngresoAdapter adapter = (IngresoAdapter) lista.getAdapter();
			adapter.addList(result);
		}

	}
}
