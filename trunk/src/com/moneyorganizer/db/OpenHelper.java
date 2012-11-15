package com.moneyorganizer.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class OpenHelper extends SQLiteOpenHelper implements ConstantesBD {
	private static final int DATABASE_VERSION = 1;
	private static final String DATABASE_NAME = "moneyorganizer.db";

	public OpenHelper(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL("CREATE TABLE " + TABLA_INGRESOS + " ( "
				+ ColumnaIngresos.ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
				+ ColumnaIngresos.TIPO + " INTEGER, " 
				+ ColumnaIngresos.FECHA + " TEXT, " 
				+ ColumnaIngresos.DIA + " INTEGER, "
				+ ColumnaIngresos.MES + " INTEGER, " 
				+ ColumnaIngresos.ANIO + " INTEGER, "
				+ ColumnaIngresos.MONTO + " FLOAT, "
				+ ColumnaIngresos.FUENTE + " TEXT, "
				+ ColumnaIngresos.DETALLE + " TEXT" + ")");
		Log.d("", "Columna de ingresos creada");
		
		db.execSQL("CREATE TABLE " + TABLA_GASTOS + " ( "
				+ ColumnaGastos.ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
				+ ColumnaGastos.TIPO + " INTEGER, " 
				+ ColumnaGastos.FECHA + " TEXT, " 
				+ ColumnaGastos.DIA + " INTEGER, "
				+ ColumnaGastos.MES + " INTEGER, " 
				+ ColumnaGastos.ANIO + " INTEGER, "
				+ ColumnaGastos.MONTO + " FLOAT, "
				+ ColumnaGastos.LUGAR + " TEXT, "
				+ ColumnaGastos.DETALLE + " TEXT" + ")");
		Log.d("", "Columna de gastos creada");
		Log.d("", "Base de datos creada");
		

		// db.execSQL("INSERT INTO " + PERSON_TABLE_NAME + "("
		// + SongColumns.FIRSTNAME + "," + SongColumns.LASTNAME + ")"
		// + " VALUES(\"John\",\"Doe\")");

	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

		int i = oldVersion;
		if (newVersion > i) {
			updateDBVersion1(db);
		}
		i++;
		if (newVersion > i) {
			updateDBVersion2(db);
		}
	}

	private void updateDBVersion1(SQLiteDatabase db) {
		// db.execSQL("ALTER TABLE " + PERSON_TABLE_NAME + " ADD COLUMN "
		// + SongColumns.EMAIL + " TEXT");
	}

	private void updateDBVersion2(SQLiteDatabase db) {
		// db.execSQL("ALTER TABLE " + PERSON_TABLE_NAME + " ADD COLUMN "
		// + PersonColumns.PHONE + " TEXT");
	}

}
