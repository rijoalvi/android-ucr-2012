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
import android.widget.Toast;
import com.moneyorganizer.R;

public class SwipoeViews extends FragmentActivity {
	public static final String TAG = MainActivity.class.getName();
	CollectionPagerAdapter mCollectionPagerAdapter;
	boolean estaEnDolares = false;
	float tipoDeCambio =0;
	int mes;
	int anio;
	 
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
 
    // Set up action bar.
    // Specify that the Home button should show an "Up" caret, indicating
    // that touching the
    // button will take the user one step up in the application's hierarchy.
 
    // Set up the ViewPager, attaching the adapter.
    mViewPager = (ViewPager) findViewById(R.id.pager);
    mViewPager.setAdapter(mCollectionPagerAdapter);
    int tempFecha[] = null;
    tempFecha = getFecha(); 
    if(tempFecha!=null){
    	mes = tempFecha[0];
    	anio = tempFecha[1];
    }
    
    ParseFileTask task = new ParseFileTask(new XPPDataParser());
	final Calendar c = Calendar.getInstance();
	
	String dia = String.valueOf(c.get(Calendar.DAY_OF_MONTH));
	String mes =  String.valueOf(c.get(Calendar.MONTH)+1);
	String anno=  String.valueOf(c.get(Calendar.YEAR));
	task.execute("http://indicadoreseconomicos.bccr.fi.cr/indicadoreseconomicos/WebServices/wsIndicadoresEconomicos.asmx/ObtenerIndicadoresEconomicosXML?tcIndicador=317&tcFechaInicio="+dia+"%2F"+mes+"%2F"+anno+"&tcFechaFinal="+dia+"%2F"+mes+"%2F"+anno+"&tcNombre=moneyOrganizer&tnSubNiveles=n");
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
			String url=params[0];
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
				Toast.makeText(SwipoeViews.this, "No se pudo realizar la conexión",
						Toast.LENGTH_SHORT).show();
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
    	
    	int NUM_ITEMS = 14; // number of tabs
	 
	    public CollectionPagerAdapter(FragmentManager fm) {
	        super(fm);
	    }
	 
	    @Override
	    public Fragment getItem(int i) {
	        Fragment fragment = new TabFragment();
	        Bundle args = new Bundle();
	        args.putInt(TabFragment.ARG_OBJECT, i);
	        fragment.setArguments(args);
	        return fragment;
	    }
	 
	    @Override
	    public int getCount() {
	        return NUM_ITEMS;
	    }
	 
	    @Override
	    public CharSequence getPageTitle(int position) {
	    	String tabLabel = null;
	        switch (position%12) {
			case 0:
				tabLabel = getString(R.string.enero)+" - "+anio;
				break;
			case 1:
				tabLabel = getString(R.string.febrero)+" - "+anio;
				break;
			case 2:
				tabLabel = getString(R.string.marzo)+" - "+anio;
				break;
			case 3:
				tabLabel = getString(R.string.abril)+" - "+anio;
				break;
			case 4:
				tabLabel = getString(R.string.mayo)+" - "+anio;
				break;
			case 5:
				tabLabel = getString(R.string.junio)+" - "+anio;
				break;
			case 6:
				tabLabel = getString(R.string.julio)+" - "+anio;
				break;
			case 7:
				tabLabel = getString(R.string.agosto)+" - "+anio;
				break;
			case 8:
				tabLabel = getString(R.string.setiembre)+" - "+anio;
				break;
			case 9:
				tabLabel = getString(R.string.octubre)+" - "+anio;
				break;
			case 10:
				tabLabel = getString(R.string.noviembre)+" - "+anio;
				break;
			case 11:
				tabLabel = getString(R.string.diciembre)+" - "+anio;
				break;
	        }
	 
	        return tabLabel;
	    }
    }
 
    /**
     * A dummy fragment representing a section of the app, but that simply
     * displays dummy text.
     */
    public static class TabFragment extends Fragment {
 
    public static final String ARG_OBJECT = "object";
 
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
        Bundle savedInstanceState) {
 
        Bundle args = getArguments();
        int position = args.getInt(ARG_OBJECT);
 
        int tabLayout = 0;
//        switch (position) {
//			case 0:
				tabLayout = R.layout.activity_pantalla_principal;
//				break;
//			
//        }
        View rootView = inflater.inflate(tabLayout, container, false);
        //TextView rootView = new TextView(getActivity());//inflater.inflate(tabLayout, container, false);
        //rootView.setText("Prueba "+position);
        return rootView;
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
				intento.putExtra("dolares", estaEnDolares);
				intento.putExtra("tipoDeCambio",tipoDeCambio);
				int fecha[] = getFecha();
				if (fecha != null) {
					intento.putExtra("mes", fecha[0]);
					intento.putExtra("anio", fecha[1]);
				}
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
						intento.putExtra("dolares", estaEnDolares);
						intento.putExtra("tipoDeCambio",tipoDeCambio);
						int fecha[] = getFecha();
						if (fecha != null) {
							intento.putExtra("mes", fecha[0]);
							intento.putExtra("anio", fecha[1]);
							startActivity(intento);
						}
					}
				});
		alertDialog.show();
	}

	public int[] getFecha() {  //HAY QUE ARREGLAARLO!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
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
		Intent intento=new Intent(getApplicationContext(),
				CategoriaIngreso.class);
		intento.putExtra("dolares", estaEnDolares);
		intento.putExtra("tipoDeCambio",tipoDeCambio);
		startActivity(intento);
	}

	public void agregarGasto(View view) {
		Intent intento=new Intent(getApplicationContext(), CategoriaGasto.class);
		intento.putExtra("dolares", estaEnDolares);
		intento.putExtra("tipoDeCambio",tipoDeCambio);
		startActivity(intento);
	}
}
