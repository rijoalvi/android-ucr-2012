package com.moneyorganizer.db;

import java.util.ArrayList;
import java.util.List;

import com.moneyorganizer.elementos.Ingreso;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

public class ControladorBD implements ConstantesBD {
	OpenHelper openHelper;

	public ControladorBD(Context context) {
		openHelper = new OpenHelper(context);
	}

	public Ingreso getIngreso(int id) {
		Ingreso ingreso = null;
		SQLiteDatabase db = openHelper.getReadableDatabase();
		Cursor cursor = db.query(TABLA_INGRESOS, new String[] {
				ColumnaIngresos.ID, ColumnaIngresos.TIPO, ColumnaIngresos.FECHA,
				ColumnaIngresos.DIA, ColumnaIngresos.MES,
				ColumnaIngresos.ANIO, ColumnaIngresos.MONTO,
				ColumnaIngresos.FUENTE, ColumnaIngresos.DETALLE }, ColumnaIngresos.ID + " = ?",
				new String[] { String.valueOf(id) }, null, null, null);

		if (cursor.moveToFirst()) {
			int idIngreso = Integer.parseInt(cursor.getString(cursor
					.getColumnIndex(ColumnaIngresos.ID)));
			int tipoIngreso = Integer.parseInt(cursor.getString(cursor
					.getColumnIndex(ColumnaIngresos.TIPO)));
			String fechaIngreso = cursor.getString(cursor
					.getColumnIndex(ColumnaIngresos.FECHA));
			int diaIngreso = Integer.parseInt(cursor.getString(cursor
					.getColumnIndex(ColumnaIngresos.DIA)));
			int mesIngreso = Integer.parseInt(cursor.getString(cursor
					.getColumnIndex(ColumnaIngresos.MES)));
			int anioIngreso = Integer.parseInt(cursor.getString(cursor
					.getColumnIndex(ColumnaIngresos.ANIO)));
			int montoIngreso = Integer.parseInt(cursor.getString(cursor
					.getColumnIndex(ColumnaIngresos.MONTO)));
			String fuenteIngreso = cursor.getString(cursor
					.getColumnIndex(ColumnaIngresos.FUENTE));
			String detalleIngreso = cursor.getString(cursor
					.getColumnIndex(ColumnaIngresos.DETALLE));
			ingreso = new Ingreso(idIngreso, tipoIngreso, fechaIngreso, diaIngreso,
					mesIngreso, anioIngreso, montoIngreso, fuenteIngreso, detalleIngreso);
		}
		cursor.close();
		db.close();
		return ingreso;
	}

//	public List<Ingreso> getTodosLosIngresosDelDia(int dia, int mes, int anio) {
//		List<Ingreso> ingresos = new ArrayList<Ingreso>();
//		SQLiteDatabase db = openHelper.getReadableDatabase();
//		Cursor cursor = db.query(TABLA_INGRESOS, new String[] {
//				ColumnaIngresos.ID, ColumnaIngresos.TIPO, ColumnaIngresos.FECHA,
//				ColumnaIngresos.DIA, ColumnaIngresos.MES,
//				ColumnaIngresos.ANIO, ColumnaIngresos.MONTO,
//				ColumnaIngresos.FUENTE, ColumnaIngresos.DETALLE }, ColumnaIngresos.DIA + " = ? AND "+ColumnaIngresos.MES + "= ? AND "+ ColumnaIngresos.ANIO + "= ?",
//				new String[] { String.valueOf(dia), String.valueOf(mes), String.valueOf(anio) }, null, null, null);
//		if (cursor.moveToFirst()) {
//			do {
//				int idIngreso = Integer.parseInt(cursor.getString(cursor
//						.getColumnIndex(ColumnaIngresos.ID)));
//				int tipoIngreso = Integer.parseInt(cursor.getString(cursor
//						.getColumnIndex(ColumnaIngresos.TIPO)));
//				String fechaIngreso = cursor.getString(cursor
//						.getColumnIndex(ColumnaIngresos.FECHA));
//				int diaIngreso = Integer.parseInt(cursor.getString(cursor
//						.getColumnIndex(ColumnaIngresos.DIA)));
//				int mesIngreso = Integer.parseInt(cursor.getString(cursor
//						.getColumnIndex(ColumnaIngresos.MES)));
//				int anioIngreso = Integer.parseInt(cursor.getString(cursor
//						.getColumnIndex(ColumnaIngresos.ANIO)));
//				int montoIngreso = Integer.parseInt(cursor.getString(cursor
//						.getColumnIndex(ColumnaIngresos.MONTO)));
//				String fuenteIngreso = cursor.getString(cursor
//						.getColumnIndex(ColumnaIngresos.FUENTE));
//				String detalleIngreso = cursor.getString(cursor
//						.getColumnIndex(ColumnaIngresos.DETALLE));
//				Ingreso ingreso = new Ingreso(idIngreso, tipoIngreso, fechaIngreso, diaIngreso,
//						mesIngreso, anioIngreso, montoIngreso, fuenteIngreso, detalleIngreso);
//				ingresos.add(ingreso);
//			} while (cursor.moveToNext());
//		}
//		cursor.close();
//		db.close();
//		return ingresos;
//	}
//	
//	public List<Ingreso> getTodosLosIngresosDelMes(int mes, int anio) {
//		List<Ingreso> ingresos = new ArrayList<Ingreso>();
//		SQLiteDatabase db = openHelper.getReadableDatabase();
//		Cursor cursor = db.query(TABLA_INGRESOS, new String[] {
//				ColumnaIngresos.ID, ColumnaIngresos.TIPO, ColumnaIngresos.FECHA,
//				ColumnaIngresos.DIA, ColumnaIngresos.MES,
//				ColumnaIngresos.ANIO, ColumnaIngresos.MONTO,
//				ColumnaIngresos.FUENTE, ColumnaIngresos.DETALLE }, ColumnaIngresos.MES + "= ? AND "+ ColumnaIngresos.ANIO + "= ?",
//				new String[] { String.valueOf(mes), String.valueOf(anio) }, null, null, null);
//		if (cursor.moveToFirst()) {
//			do {
//				int idIngreso = Integer.parseInt(cursor.getString(cursor
//						.getColumnIndex(ColumnaIngresos.ID)));
//				int tipoIngreso = Integer.parseInt(cursor.getString(cursor
//						.getColumnIndex(ColumnaIngresos.TIPO)));
//				String fechaIngreso = cursor.getString(cursor
//						.getColumnIndex(ColumnaIngresos.FECHA));
//				int diaIngreso = Integer.parseInt(cursor.getString(cursor
//						.getColumnIndex(ColumnaIngresos.DIA)));
//				int mesIngreso = Integer.parseInt(cursor.getString(cursor
//						.getColumnIndex(ColumnaIngresos.MES)));
//				int anioIngreso = Integer.parseInt(cursor.getString(cursor
//						.getColumnIndex(ColumnaIngresos.ANIO)));
//				int montoIngreso = Integer.parseInt(cursor.getString(cursor
//						.getColumnIndex(ColumnaIngresos.MONTO)));
//				String fuenteIngreso = cursor.getString(cursor
//						.getColumnIndex(ColumnaIngresos.FUENTE));
//				String detalleIngreso = cursor.getString(cursor
//						.getColumnIndex(ColumnaIngresos.DETALLE));
//				Ingreso ingreso = new Ingreso(idIngreso, tipoIngreso, fechaIngreso, diaIngreso,
//						mesIngreso, anioIngreso, montoIngreso, fuenteIngreso, detalleIngreso);
//				ingresos.add(ingreso);
//			} while (cursor.moveToNext());
//		}
//		cursor.close();
//		db.close();
//		return ingresos;
//	}

	public List<Ingreso> getTodosLosIngresos() {
		List<Ingreso> ingresos = new ArrayList<Ingreso>();
		SQLiteDatabase db = openHelper.getReadableDatabase();
		Cursor cursor = db.query(TABLA_INGRESOS, new String[] {
				ColumnaIngresos.ID, ColumnaIngresos.TIPO, ColumnaIngresos.FECHA,
				ColumnaIngresos.DIA, ColumnaIngresos.MES,
				ColumnaIngresos.ANIO, ColumnaIngresos.MONTO,
				ColumnaIngresos.FUENTE, ColumnaIngresos.DETALLE }, null, null, null, null, null);
		if (cursor.moveToFirst()) {
			do {
				int idIngreso = Integer.parseInt(cursor.getString(cursor
						.getColumnIndex(ColumnaIngresos.ID)));
				int tipoIngreso = Integer.parseInt(cursor.getString(cursor
						.getColumnIndex(ColumnaIngresos.TIPO)));
				String fechaIngreso = cursor.getString(cursor
						.getColumnIndex(ColumnaIngresos.FECHA));
				int diaIngreso = Integer.parseInt(cursor.getString(cursor
						.getColumnIndex(ColumnaIngresos.DIA)));
				int mesIngreso = Integer.parseInt(cursor.getString(cursor
						.getColumnIndex(ColumnaIngresos.MES)));
				int anioIngreso = Integer.parseInt(cursor.getString(cursor
						.getColumnIndex(ColumnaIngresos.ANIO)));
				int montoIngreso = Integer.parseInt(cursor.getString(cursor
						.getColumnIndex(ColumnaIngresos.MONTO)));
				String fuenteIngreso = cursor.getString(cursor
						.getColumnIndex(ColumnaIngresos.FUENTE));
				String detalleIngreso = cursor.getString(cursor
						.getColumnIndex(ColumnaIngresos.DETALLE));
				Ingreso ingreso = new Ingreso(idIngreso, tipoIngreso, fechaIngreso, diaIngreso,
						mesIngreso, anioIngreso, montoIngreso, fuenteIngreso, detalleIngreso);
				ingresos.add(ingreso);
			} while (cursor.moveToNext());
		}
		cursor.close();
		db.close();
		return ingresos;
	}

	public boolean salvarSong(Ingreso ingreso) {
		SQLiteDatabase db = openHelper.getWritableDatabase();

		long id = db.insert(TABLA_INGRESOS, null, ingreso.getValues());

		if (id != -1) {
			Log.d("", "Datos insertados");
			//ingreso.setId(id);
			return true;
		}
		db.close();
		return false;
	}

//	public void actualizarPersona(Ingreso ingreso) {
//		SQLiteDatabase db = openHelper.getWritableDatabase();
//		int affectedRows = db.update(TABLA_INGRESOS, ingreso.getValues(),
//				SongColumns.TRACKID + " = ?",
//				new String[] { String.valueOf(ingreso.getTrackId()) });
//		if (affectedRows > 0) {
//			Log.d("", "Datos actualizados");
//		}
//		db.close();
//	}

	public boolean removerSong(Ingreso ingreso) {
		SQLiteDatabase db = openHelper.getWritableDatabase();
		int affectedRows = db.delete(TABLA_INGRESOS, ColumnaIngresos.ID
				+ " = ?", new String[] { String.valueOf(ingreso.getId()) });
		if (affectedRows > 0) {
			Log.d("", "Datos borrados");
			return true;
		}
		return false;
	}
}
