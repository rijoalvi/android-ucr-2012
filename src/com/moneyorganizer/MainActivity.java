package com.moneyorganizer;

import java.io.IOException;
import java.util.Calendar;

import com.moneyorganizer.conexion.DataParser;
import com.moneyorganizer.conexion.HTTPClientFactory;
import com.moneyorganizer.conexion.TipoCambio;
import com.moneyorganizer.conexion.XPPDataParser;
import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Activity;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends Activity {

	//public static final String TAG = MainActivity.class.getName();
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }
    
//    public void parseXPP(View view) {
//		ParseFileTask task = new ParseFileTask(new XPPDataParser());
//		final Calendar c = Calendar.getInstance();
//		
//		String dia = String.valueOf(c.get(Calendar.DAY_OF_MONTH));
//		
//		String mes =  String.valueOf(c.get(Calendar.MONTH)+1);
//		
//		String anno=  String.valueOf(c.get(Calendar.YEAR));
//		
//		task.execute("http://indicadoreseconomicos.bccr.fi.cr/indicadoreseconomicos/WebServices/wsIndicadoresEconomicos.asmx/ObtenerIndicadoresEconomicosXML?tcIndicador=317&tcFechaInicio="+dia+"%2F"+mes+"%2F"+anno+"&tcFechaFinal="+dia+"%2F"+mes+"%2F"+anno+"&tcNombre=moneyOrganizer&tnSubNiveles=n");
//	}
//
//	class ParseFileTask extends AsyncTask<String, Void, TipoCambio> {
//
//		DataParser parser;
//
//		public ParseFileTask(DataParser parser) {
//			super();
//			this.parser = parser;
//		}
//
//		@Override
//		protected TipoCambio doInBackground(String... params) {
//			TipoCambio tc = new TipoCambio();
//			HTTPClientFactory httpCF = new HTTPClientFactory();
//			String url=params[0];
//			try {
//				tc = parser.parseUser(httpCF.getInputStreamFromHttpClient(url));
//				return tc;
//			} catch (IOException e) {
//				Log.e(TAG, e.getMessage(), e);
//			} 
//			return null;
//		}
//
//		protected void onPostExecute(TipoCambio result) {
//			if (result != null) {
//				Log.d(TAG, "RESULTADO: " + result.toString());
//			} else {
//				Toast.makeText(MainActivity.this, "Unable to load user",
//						Toast.LENGTH_SHORT).show();
//			}
//			super.onPostExecute(result);
//		}
//
//		protected void onPreExecute() {
//			super.onPreExecute();
//		}
//	}

}
