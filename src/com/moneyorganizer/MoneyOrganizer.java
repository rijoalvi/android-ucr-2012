package com.moneyorganizer;

import java.text.SimpleDateFormat;
import java.util.Date;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class MoneyOrganizer extends FragmentActivity {

	CollectionPagerAdapter mCollectionPagerAdapter;

	/**
	 * The {@link android.support.v4.view.ViewPager} that will display the
	 * object collection.
	 */
	ViewPager mViewPager;

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_money_organizer);

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
	}

	/**
	 * A {@link android.support.v4.app.FragmentStatePagerAdapter} that returns a
	 * fragment representing an object in the collection.
	 */
	public class CollectionPagerAdapter extends FragmentStatePagerAdapter {

		final int NUM_ITEMS = 3; // number of tabs

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
			switch (position) {
			case 0:
				tabLabel = getString(R.string.title_section1);
				break;
			case 1:
				tabLabel = getString(R.string.title_section2);
				break;
			case 2:
				tabLabel = getString(R.string.title_section3);
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
			switch (position) {
			case 0:
				tabLayout = R.layout.activity_pantalla_principal;
				break;
			case 1:
				tabLayout = R.layout.activity_total_de_gastos;
				break;
			case 2:
				tabLayout = R.layout.activity_pantalla_principal;
				break;
			}

			View rootView = inflater.inflate(tabLayout, container, false);

			return rootView;
		}
	}

	// public void cargarListaIngresos(View view) {
	// startActivity(new Intent(getApplicationContext(),
	// TotalDeIngresos.class));
	// // finish();
	// }
	//
	// public void cargarListaGastos(View view) {
	// startActivity(new Intent(getApplicationContext(), TotalDeGastos.class));
	// // finish();
	// }

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
		startActivity(new Intent(getApplicationContext(),
				CategoriaIngreso.class));
	}

	public void agregarGasto(View view) {
		startActivity(new Intent(getApplicationContext(), CategoriaGasto.class));
	}

}
