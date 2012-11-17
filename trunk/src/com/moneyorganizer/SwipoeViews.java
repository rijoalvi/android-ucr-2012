package com.moneyorganizer;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import com.moneyorganizer.conexion.DataParser;
import com.moneyorganizer.conexion.HTTPClientFactory;
import com.moneyorganizer.conexion.TipoCambio;
import com.moneyorganizer.conexion.XPPDataParser;
import com.moneyorganizer.db.ControladorBD;

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
import android.support.v4.view.PagerAdapter;
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
	
	public static final String TAG = SwipoeViews.class.getName();
	CollectionPagerAdapter mCollectionPagerAdapter;
	float tipoDeCambio = 500;
	boolean estaEnDolares = true;
	int mesInicial;
	int anioInicial;
	int mes;
	int anio;
	int posInicial;
	int posAnterior;
	int posActual;
	ViewPager mViewPager;

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_swipoe_views);
		mCollectionPagerAdapter = new CollectionPagerAdapter(getSupportFragmentManager());
		int tempFecha[] = null;
		tempFecha = getFechaActual();
		if (tempFecha != null) {
			mes = tempFecha[0];
			anio = tempFecha[1];
			mesInicial = mes;
			anioInicial = anio;
		}
		ControladorBD controlador = new ControladorBD(SwipoeViews.this);
		
		//NOTA IMPORTANTE:
		//Como el programa solo puede insertar gastos e ingresos en la fecha actual, se creo una clase que crea gastos en ingresos
		//en diversos meses para efectos de pruebas, que se llamaria a continuacion:
		
		//Insertar inserte = new Insertar(controlador);
		
		
		float f = controlador.getDisponible();		
		float resp = controlador.getTotalGastosDelMes(11, 2012);

		mViewPager = (ViewPager) findViewById(R.id.pager);
		mViewPager.setAdapter(mCollectionPagerAdapter);
		posInicial = (mes + 107);
		posAnterior = posInicial;
		mViewPager.setCurrentItem(posInicial);

		ParseFileTask task = new ParseFileTask(new XPPDataParser());
		final Calendar c = Calendar.getInstance();

		String dia = String.valueOf(c.get(Calendar.DAY_OF_MONTH));
		String mes = String.valueOf(c.get(Calendar.MONTH) + 1);
		String anno = String.valueOf(c.get(Calendar.YEAR));
		task.execute("http://indicadoreseconomicos.bccr.fi.cr/indicadoreseconomicos/WebServices/wsIndicadoresEconomicos.asmx/ObtenerIndicadoresEconomicosXML?tcIndicador=317&tcFechaInicio="
				+ dia + "%2F" + mes + "%2F" + anno + "&tcFechaFinal=" + dia + "%2F" + mes + "%2F" + anno + "&tcNombre=moneyOrganizer&tnSubNiveles=n");
	}

	
	/**
	 * Clase que llama al webService del Banco Central, y parsea su resultado.
	 */
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
			} catch (IOException e) {
				tc.setValor("500");
				Log.e(TAG, e.getMessage(), e);
			}
			return tc;
		}
		
		protected void onPostExecute(TipoCambio result) {
			if (result != null) {
				tipoDeCambio = Float.valueOf(result.getValor());
				Log.d(TAG, "RESULTADO: " + tipoDeCambio);
			} else {
				Toast.makeText(SwipoeViews.this,
						"No se pudo realizar la conexion", Toast.LENGTH_SHORT)
						.show();
			}
			super.onPostExecute(result);
		}

		protected void onPreExecute() {
			super.onPreExecute();
		}
	}

	/**
	 * Adaptador de fragmentos
	 */
	public class CollectionPagerAdapter extends FragmentStatePagerAdapter {

		int cantItems = 132; // numero de tabs inicial
		int posActual = -1;  //tab que esta en pantalla actualmente

		public CollectionPagerAdapter(FragmentManager fm) {
			super(fm);
		}
		
		public int getPosActual(){
			return posActual;
		}
		
		public void actualizar(){
			posAnterior = -1;
			getItem(posActual);
		}

		
		@Override
		public Fragment getItem(int i) {
			calcularFecha(i);
			posActual = i;
			TabFragment fragment = new TabFragment();
			Bundle args = new Bundle();
			args.putInt(TabFragment.ARG_OBJECT, i);
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
				if ((mes >= 11)&&((position%12)<mes)) {
					tabLabel = getString(R.string.enero) + " - " + (anio + 1);
				} else {
					tabLabel = getString(R.string.enero) + " - " + anio;
				}
				break;
			case 1:
				if ((mes >= 12)&&((position%12)<mes)) {
					tabLabel = getString(R.string.febrero) + " - " + (anio + 1);
				} else {
					tabLabel = getString(R.string.febrero) + " - " + anio;
				}
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
				if ((mes <= 1)&&((position%12)>mes)) {
					tabLabel = getString(R.string.noviembre) + " - " + (anio - 1);
				} else {
					tabLabel = getString(R.string.noviembre) + " - " + anio;
				}
				break;
			case 11:
				if ((mes <= 2)&&((position%12)>mes)) {
					tabLabel = getString(R.string.diciembre) + " - " + (anio - 1);
				} else {
					tabLabel = getString(R.string.diciembre) + " - " + anio;
				}
				break;
			}

			return tabLabel;
		}
		
		/**
		 * Metodo que calcula la fecha que debe mostrar en el fragemnto actual
		 * @param i
		 */
		private void calcularFecha(int i) {
			if (i != -1){
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
			}
			posAnterior = i;
			posActual = i;
		}
	}

	/**
	 * Clase fragmento
	 */
	public  class TabFragment extends Fragment {

		public  final static String ARG_OBJECT = "object";
		View viewRaiz;
		View rootView;
		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {

			Bundle args = getArguments();
			int position = args.getInt(ARG_OBJECT);
			
			int tabLayout = 0;
			tabLayout = R.layout.activity_pantalla_principal;	
			rootView = inflater.inflate(tabLayout, container, false);
			viewRaiz = rootView;
			Log.d("","## "+mes);
			Log.d("","## "+anio);
			asignarCampos();
			
			return rootView;
		}
		
		/**
		 * Metodo que llama a base de datos y llena los campos del fragmento creado.
		 */
		
		private void asignarCampos(){
			ControladorBD controlador = new ControladorBD(SwipoeViews.this);
			float ingresos1 = controlador.getTotalIngresosDelMes(mes, anio);
			float gastos1 = controlador.getTotalGastosDelMes(mes, anio);
			float disponible1 = controlador.getDisponible();
			TextView tipoCambio= (TextView) rootView.findViewById(R.id.tipoCambio);
			tipoCambio.setText("Tipo de Cambio: "+String.valueOf(tipoDeCambio));
			
			if(estaEnDolares){
				TextView ingresos = (TextView) rootView.findViewById(R.id.montoIngresos);
				ingresos.setText(String.valueOf(ingresos1/tipoDeCambio));
				TextView gastos = (TextView) rootView.findViewById(R.id.montoGastos);
				gastos.setText(String.valueOf(gastos1/tipoDeCambio));
				TextView disponible = (TextView) rootView.findViewById(R.id.montoDisponible);
				disponible.setText(String.valueOf(disponible1/tipoDeCambio));
				Button b = (Button) rootView.findViewById(R.id.CMoneda); 
				b.setText(R.string.dolares);
			}
			else{
				TextView ingresos = (TextView) rootView.findViewById(R.id.montoIngresos);
				ingresos.setText(String.valueOf(ingresos1));
				TextView gastos = (TextView) rootView.findViewById(R.id.montoGastos);
				gastos.setText(String.valueOf(gastos1));
				TextView disponible = (TextView) rootView.findViewById(R.id.montoDisponible);
				disponible.setText(String.valueOf(disponible1));
				Button b = (Button) rootView.findViewById(R.id.CMoneda); 
				b.setText(R.string.colones);
			}
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

	/**
	 * Metdo que retorna la fecha del dia actual
	 * @return
	 */
	public int[] getFechaActual() { 
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
		mes = mesInicial-2;
		anio = anioInicial;
		posAnterior =-1;
		mViewPager.setCurrentItem(posInicial);
	}

	public void agregarGasto(View view) {
		Intent intento = new Intent(getApplicationContext(),
				CategoriaGasto.class);
		intento.putExtra("dolares", estaEnDolares);
		intento.putExtra("tipoDeCambio", tipoDeCambio);
		startActivity(intento);
		mes = mesInicial;
		anio = anioInicial;
		posAnterior =-1;
		mViewPager.setCurrentItem(posInicial);
	}

	@Override
	public void onConfigurationChanged(Configuration newConfig) {
		super.onConfigurationChanged(newConfig);
		setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
	}
	
	
	/**
	 * Metodos que hacen el cambio entre dolares y colones
	 * @param view
	 */
	public void SetMoneda(View view){
		if(estaEnDolares){			
			estaEnDolares = false;		
		}else{
			estaEnDolares = true;
		}
		SetTextMoneda();
		mes = mesInicial-1;
		anio = anioInicial;
		posAnterior =-1;
		mViewPager.setCurrentItem(posInicial);
		
	}	
	
	public void SetTextMoneda(){
		Button button = (Button) findViewById(R.id.CMoneda);
		if(estaEnDolares){					
			button.setText(R.string.colones);			
		}else{
			button.setText(R.string.dolares);
		}
	}

}
