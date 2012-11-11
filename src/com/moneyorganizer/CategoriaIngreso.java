package com.moneyorganizer;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
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

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_categoria_ingreso);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_categoria_ingreso, menu);
        return true;
    }
    
    public void ingresoSalario(View view){
    	Intent intento = new Intent(this, DetallesIngreso.class );
        intento.putExtra("categoria", "1");
        startActivity(intento);  
    	finish();
    }
    public void ingresoAlquileres(View view){
    	Intent intento = new Intent(this, DetallesIngreso.class );
        intento.putExtra("categoria", "2");
        startActivity(intento);  
    	finish();
    }
    public void ingresoInversiones(View view){
    	Intent intento = new Intent(this, DetallesIngreso.class );
        intento.putExtra("categoria", "3");
        startActivity(intento);  
    	finish();
    }
    public void ingresoPensiones(View view){
    	Intent intento = new Intent(this, DetallesIngreso.class );
        intento.putExtra("categoria", "4");
        startActivity(intento);  
    	finish();
    }
    public void ingresoCobros(View view){
    	Intent intento = new Intent(this, DetallesIngreso.class );
        intento.putExtra("categoria", "5");
        startActivity(intento);  
    	finish();
    }
    public void ingresoBonos(View view){
    	Intent intento = new Intent(this, DetallesIngreso.class );
        intento.putExtra("categoria", "6");
        startActivity(intento);  
    	finish();
    }
    public void ingresoReembolsos(View view){
    	Intent intento = new Intent(this, DetallesIngreso.class );
        intento.putExtra("categoria", "7");
        startActivity(intento);  
    	finish();
    }
    
    public void ingresoRegalos(View view){
    	Intent intento = new Intent(this, DetallesIngreso.class );
        intento.putExtra("categoria", "8");
        startActivity(intento);  
    	finish();
    }
    
    public void ingresoOtros(View view){
    	Intent intento = new Intent(this, DetallesIngreso.class );
        intento.putExtra("categoria", "9");
        startActivity(intento);  
    	finish();
    }
    
}
