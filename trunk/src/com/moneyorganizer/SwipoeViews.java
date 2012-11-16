package com.moneyorganizer;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import com.moneyorganizer.conexion.DataParser;
import com.moneyorganizer.conexion.HTTPClientFactory;
import com.moneyorganizer.conexion.TipoCambio;
import com.moneyorganizer.conexion.XPPDataParser;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import com.moneyorganizer.R;

public class SwipoeViews extends FragmentActivity {
	public static final String TAG = MainActivity.class.getName();
	CollectionPagerAdapter mCollectionPagerAdapter;
	float tipoDeCambio = 0;
	boolean estaEnDolares = false;
	int mes;
	int anio;
	int posInicial;
	int posAnterior;
	int posActual;

	/**
	 * The {@link android.support.v4.view.ViewPager} that will display the
	 * object collection.
	 */
	ViewPager mViewPager;

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_swipoe_views);

		// Create an adapter that when requested, will return a fragment
		// representing an object in
		// the collection.
		//
		// ViewPager and its adapters use support library fragments, so we must
		// use
		// getSupportFragmentManager.
		mCollectionPagerAdapter = new CollectionPagerAdapter(
				getSupportFragmentManager());

		int tempFecha[] = null;
		tempFecha = getFechaActual();
		if (tempFecha != null) {
			mes = tempFecha[0];
			anio = tempFecha[1];
		}
		// Set up action bar.
		// Specify that the Home button should show an "Up" caret, indicating
		// that touching the
		// button will take the user one step up in the application's hierarchy.

		// Set up the ViewPager, attaching the adapter.
		mViewPager = (ViewPager) findViewById(R.id.pager);
		mViewPager.setAdapter(mCollectionPagerAdapter);
		posInicial = (mes + 107);
		posAnterior = posInicial;
		mViewPager.setCurrentItem(posInicial);
		Log.d("", "MES: " + String.valueOf(mes));
		// mViewPager.set

		ParseFileTask task = new ParseFileTask(new XPPDataParser());
		final Calendar c = Calendar.getInstance();

		String dia = String.valueOf(c.get(Calendar.DAY_OF_MONTH));
		String mes = String.valueOf(c.get(Calendar.MONTH) + 1);
		String anno = String.valueOf(c.get(Calendar.YEAR));
		task.execute("http://indicadoreseconomicos.bccr.fi.cr/indicadoreseconomicos/WebServices/wsIndicadoresEconomicos.asmx/ObtenerIndicadoresEconomicosXML?tcIndicador=317&tcFechaInicio="
				+ dia
				+ "%2F"
				+ mes
				+ "%2F"
				+ anno
				+ "&tcFechaFinal="
				+ dia
				+ "%2F"
				+ mes
				+ "%2F"
				+ anno
				+ "&tcNombre=moneyOrganizer&tnSubNiveles=n");
	}

	class ParseFileTask extends AsyncTask<String, Void, TipoCambio> {

		DataParser parser;

		public ParseFileTask(DataParser parser) {
			super();
			this.parser = parser;
		}

		protected TipoCambio doInBackground(String... params) {
			TipoCambio tc = new TipoCambio();
			HTTPClientFactory httpCF = new HTTPClientFactory();
			String url = params[0];
			try {
				tc = parser.parseUser(httpCF.getInputStreamFromHttpClient(url));
				return tc;
			} catch (IOException e) {
				Log.e(TAG, e.getMessage(), e);
			}
			return null;
		}

		protected void onPostExecute(TipoCambio result) {
			if (result != null) {
				tipoDeCambio = Float.valueOf(result.getValor());
				Log.d(TAG, "RESULTADO: " + tipoDeCambio);
			} else {
				Toast.makeText(SwipoeViews.this,
						"No se pudo realizar la conexión", Toast.LENGTH_SHORT)
						.show();
			}
			super.onPostExecute(result);
		}

		protected void onPreExecute() {
			super.onPreExecute();
		}
	}

	/**
	 * A {@link android.support.v4.app.FragmentStatePagerAdapter} that returns a
	 * fragment representing an object in the collection.
	 */
	public class CollectionPagerAdapter extends FragmentStatePagerAdapter {

		int cantItems = 132; // number of tabs
		int posActual = -1;

		public CollectionPagerAdapter(FragmentManager fm) {
			super(fm);
		}

		@Override
		public Fragment getItem(int i) {
			calcularFecha(i);
			// ("", "posActual: "+String.valueOf(i));
			Fragment fragment = new TabFragment();
			Bundle args = new Bundle();
			args.putInt(TabFragment.ARG_OBJECT, i);
			int ingresos = 500+i;
			int gastos = 320+i;
			int disponible = ingresos-gastos;
			
			args.putInt("ingresos", ingresos);
			args.putInt("ingresos", gastos);
			args.putInt("ingresos", disponible);
			fragment.setArguments(args);

			return fragment;
		}

		@Override
		public int getCount() {
			return cantItems;
		}

		@Override
		public CharSequence getPageTitle(int position) {
			String tabLabel = null;

			if (position > 118) {
				cantItems++;
			}
			switch (position % 12) {
			case 0:
				if (posAnterior < position) {
					tabLabel = getString(R.string.enero) + " - " + (anio + 1);
					Toast.makeText(SwipoeViews.this, "enerous", 3000).show();
				} else {
					tabLabel = getString(R.string.enero) + " - " + anio;
				}
				break;
			case 1:
				tabLabel = getString(R.string.febrero) + " - " + anio;
				break;
			case 2:
				tabLabel = getString(R.string.marzo) + " - " + anio;
				break;
			case 3:
				tabLabel = getString(R.string.abril) + " - " + anio;
				break;
			case 4:
				tabLabel = getString(R.string.mayo) + " - " + anio;
				break;
			case 5:
				tabLabel = getString(R.string.junio) + " - " + anio;
				break;
			case 6:
				tabLabel = getString(R.string.julio) + " - " + anio;
				break;
			case 7:
				tabLabel = getString(R.string.agosto) + " - " + anio;
				break;
			case 8:
				tabLabel = getString(R.string.setiembre) + " - " + anio;
				break;
			case 9:
				tabLabel = getString(R.string.octubre) + " - " + anio;
				break;
			case 10:
				tabLabel = getString(R.string.noviembre) + " - " + anio;
				break;
			case 11:
				if (posAnterior > position) {
					tabLabel = getString(R.string.diciembre) + " - "
							+ (anio - 1);
					Toast.makeText(SwipoeViews.this, "diciember", 3000).show();
				} else {
					tabLabel = getString(R.string.diciembre) + " - " + anio;
				}
				break;
			}

			return tabLabel;
		}
		private void calcularFecha(int i) {
			if (i > posAnterior) {
				mes++;
				if (mes == 13) {
					anio++;
					mes = 1;
				}
			}
			if (i < posAnterior) {
				mes--;
				if (mes == 0) {
					anio--;
					mes = 12;
				}
			}
			Log.d("", "posAnt " + String.valueOf(posAnterior));
			Log.d("", "posAct " + String.valueOf(i));
			Log.d("", "mes " + String.valueOf(mes));
			Log.d("", "anio " + String.valueOf(anio));
			posAnterior = i;
			posActual = i;
		}
	}

	/**
	 * A dummy fragment representing a section of the app, but that simply
	 * displays dummy text.
	 */
	public  class TabFragment extends Fragment {

		public  final static String ARG_OBJECT = "object";
		View rootView;

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {

			Bundle args = getArguments();
			int position = args.getInt(ARG_OBJECT);
			
			int tabLayout = 0;
			tabLayout = R.layout.activity_pantalla_principal;	
			rootView = inflater.inflate(tabLayout, container, false);
			TextView ingresos = (TextView) rootView.findViewById(R.id.montoIngresos);
			ingresos.setText(String.valueOf(args.getInt("ingresos")));
			TextView gastos = (TextView) rootView.findViewById(R.id.montoGastos);
			gastos.setText(String.valueOf(args.getInt("gastos")));
			TextView disponible = (TextView) rootView.findViewById(R.id.montoDisponible);
			disponible.setText(String.valueOf(args.getInt("disponible")));
			return rootView;
		}
	}
	
	public void SetMoneda(View view){
		if(estaEnDolares){			
			estaEnDolares = false;		
		}else{
			estaEnDolares = true;
		}
		SetTextMoneda();
	}
	
	
	public void SetTextMoneda(){
		Button button = (Button) findViewById(R.id.CMoneda);
		if(estaEnDolares){					
			button.setText(R.string.colones);			
		}else{
			button.setText(R.string.dolares);
		}
	}
	
	@SuppressWarnings("deprecation")
	public void cargarDetalles(View view) {
		AlertDialog alertDialog = new AlertDialog.Builder(this).create();
		alertDialog.setTitle("Detalles");
		alertDialog.setMessage("Seleccione una categoría de detalles:");
		// alertDialog.setIcon(R.drawable.search);
		alertDialog.setButton("Gasto", new DialogInterface.OnClickListener() {
			public void onClick(final DialogInterface dialog, final int which) {
				Intent intento = new Intent(getApplicationContext(),
						TotalDeGastos.class);
				intento.putExtra("mes", mes);
				intento.putExtra("anio", anio);
				intento.putExtra("dolares", estaEnDolares);
				intento.putExtra("tipoDeCambio", tipoDeCambio);

				startActivity(intento);
				return;
			}
		});
		alertDialog.setButton2("Ingreso",
				new DialogInterface.OnClickListener() {
					public void onClick(final DialogInterface dialog,
							final int which) {

						Intent intento = new Intent(getApplicationContext(),
								TotalDeIngresos.class);

						intento.putExtra("mes", mes);
						intento.putExtra("anio", anio);

						intento.putExtra("dolares", estaEnDolares);
						intento.putExtra("tipoDeCambio", tipoDeCambio);

						startActivity(intento);
					}
				});
		alertDialog.show();
	}

	public int[] getFechaActual() { // HAY QUE
		// ARREGLAARLO!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
		int respuesta[] = new int[2];
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
			respuesta[0] = Integer.parseInt(fechaDigitos[1]);
			respuesta[1] = Integer.parseInt(fechaDigitos[0]);
			return respuesta;
		}
		return null;
	}

	public void agregarIngreso(View view) {
		Intent intento = new Intent(getApplicationContext(),
				CategoriaIngreso.class);
		intento.putExtra("dolares", estaEnDolares);
		intento.putExtra("tipoDeCambio", tipoDeCambio);
		startActivity(intento);
	}

	public void agregarGasto(View view) {
		Intent intento = new Intent(getApplicationContext(),
				CategoriaGasto.class);
		intento.putExtra("dolares", estaEnDolares);
		intento.putExtra("tipoDeCambio", tipoDeCambio);
		startActivity(intento);
	}

	@Override
	public void onConfigurationChanged(Configuration newConfig) {
		super.onConfigurationChanged(newConfig);
		setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
	}
	
	

}
