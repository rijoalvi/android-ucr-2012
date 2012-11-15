package com.moneyorganizer;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.view.Menu;
import android.view.View;


/*
 * Lista de categorias:
 * 1.Salario
 * 2.Alquileres
 * 3.Inversiones
 * 4.Pensiones
 * 5.Cobros
 * 6.Bonos
 * 7.Reembolsos
 * 8.Regalos
 * 9.Otros
 */

public class CategoriaIngreso extends Activity {
	private float tipoDeCambio;
	private boolean estaEnDolares;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_categoria_ingreso);
        Bundle bundle = getIntent().getExtras();
		tipoDeCambio=bundle.getFloat("tipoDeCambio");
		estaEnDolares = bundle.getBoolean("dolares");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_categoria_ingreso, menu);
        return true;
    }
    
    public void ingresoSalario(View view){
    	Intent intento = new Intent(this, DetallesIngreso.class );
        intento.putExtra("categoria", "1");
        intento.putExtra("dolares", estaEnDolares);
		intento.putExtra("tipoDeCambio",tipoDeCambio);
        startActivity(intento);  
    	finish();
    }
    public void ingresoAlquileres(View view){
    	Intent intento = new Intent(this, DetallesIngreso.class );
        intento.putExtra("categoria", "2");
        intento.putExtra("dolares", estaEnDolares);
		intento.putExtra("tipoDeCambio",tipoDeCambio);
        startActivity(intento);  
    	finish();
    }
    public void ingresoInversiones(View view){
    	Intent intento = new Intent(this, DetallesIngreso.class );
        intento.putExtra("categoria", "3");
        intento.putExtra("dolares", estaEnDolares);
		intento.putExtra("tipoDeCambio",tipoDeCambio);
        startActivity(intento);  
    	finish();
    }
    public void ingresoPensiones(View view){
    	Intent intento = new Intent(this, DetallesIngreso.class );
        intento.putExtra("categoria", "4");
        intento.putExtra("dolares", estaEnDolares);
		intento.putExtra("tipoDeCambio",tipoDeCambio);
        startActivity(intento);  
    	finish();
    }
    public void ingresoCobros(View view){
    	Intent intento = new Intent(this, DetallesIngreso.class );
        intento.putExtra("categoria", "5");
        intento.putExtra("dolares", estaEnDolares);
		intento.putExtra("tipoDeCambio",tipoDeCambio);
        startActivity(intento);  
    	finish();
    }
    public void ingresoBonos(View view){
    	Intent intento = new Intent(this, DetallesIngreso.class );
        intento.putExtra("categoria", "6");
        intento.putExtra("dolares", estaEnDolares);
		intento.putExtra("tipoDeCambio",tipoDeCambio);
        startActivity(intento);  
    	finish();
    }
    public void ingresoReembolsos(View view){
    	Intent intento = new Intent(this, DetallesIngreso.class );
        intento.putExtra("categoria", "7");
        intento.putExtra("dolares", estaEnDolares);
		intento.putExtra("tipoDeCambio",tipoDeCambio);
        startActivity(intento);  
    	finish();
    }
    
    public void ingresoRegalos(View view){
    	Intent intento = new Intent(this, DetallesIngreso.class );
        intento.putExtra("categoria", "8");
        intento.putExtra("dolares", estaEnDolares);
		intento.putExtra("tipoDeCambio",tipoDeCambio);
        startActivity(intento);  
    	finish();
    }
    
    public void ingresoOtros(View view){
    	Intent intento = new Intent(this, DetallesIngreso.class );
        intento.putExtra("categoria", "9");
        intento.putExtra("dolares", estaEnDolares);
		intento.putExtra("tipoDeCambio",tipoDeCambio);
        startActivity(intento);  
    	finish();
    }
    
    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
    }
    
}
