package com.moneyorganizer.db;

import java.util.ArrayList;
import java.util.List;

import com.moneyorganizer.elementos.Gasto;
import com.moneyorganizer.elementos.Ingreso;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

/**
 * @author Ricardo Alvarado
 * @author Marisabel Betancort
 * @author Raquel Rodriguez
 *
 */
public class ControladorBD implements ConstantesBD {
	OpenHelper openHelper;

	public ControladorBD(Context context) {
		openHelper = new OpenHelper(context);
	}

	//Ingresos:
	
	/**
	 * @description Retorna de la base de datos el ingreso correspondiente al id enviado como parametro 
	 * @param id
	 * @return Ingreso
	 */
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

	
	/**
	 * @description Retorna los ingresos de la fecha seleccionada (dia, mes anio) 
	 * @param int dia, int mes, int anio
	 * @return List<Ingreso>
	 */
	public List<Ingreso> getTodosLosIngresosDelDia(int dia, int mes, int anio) {
		List<Ingreso> ingresos = new ArrayList<Ingreso>();
		SQLiteDatabase db = openHelper.getReadableDatabase();
		Cursor cursor = db.query(TABLA_INGRESOS, new String[] {
				ColumnaIngresos.ID, ColumnaIngresos.TIPO, ColumnaIngresos.FECHA,
				ColumnaIngresos.DIA, ColumnaIngresos.MES,
				ColumnaIngresos.ANIO, ColumnaIngresos.MONTO,
				ColumnaIngresos.FUENTE, ColumnaIngresos.DETALLE }, ColumnaIngresos.DIA + " = ? AND "+ColumnaIngresos.MES + "= ? AND "+ ColumnaIngresos.ANIO + "= ?",
				new String[] { String.valueOf(dia), String.valueOf(mes), String.valueOf(anio) }, null, null, null);
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
	
	
	/**
	 * @description Retorna los ingresos del mes seleccionado (mes anio) 
	 * @param int mes, int anio
	 * @return List<Ingreso>
	 */
	public List<Ingreso> getTodosLosIngresosDelMes(int mes, int anio) {
		List<Ingreso> ingresos = new ArrayList<Ingreso>();
		SQLiteDatabase db = openHelper.getReadableDatabase();
		Cursor cursor = db.query(TABLA_INGRESOS, new String[] {
				ColumnaIngresos.ID, ColumnaIngresos.TIPO, ColumnaIngresos.FECHA,
				ColumnaIngresos.DIA, ColumnaIngresos.MES,
				ColumnaIngresos.ANIO, ColumnaIngresos.MONTO,
				ColumnaIngresos.FUENTE, ColumnaIngresos.DETALLE }, ColumnaIngresos.MES + "= ? AND "+ ColumnaIngresos.ANIO + "= ?",
				new String[] { String.valueOf(mes), String.valueOf(anio) }, null, null, null);
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
	
	

	/**
	 * @description Retorna todos los ingresos de la vida 
	 * @param 
	 * @return List<Ingreso>
	 */
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

	
	/**
	 * @description Guarda un ingreso en la base de datos 
	 * @param Ingreso
	 * @return boolean
	 */
	public boolean guardarIngreso(Ingreso ingreso) {
		SQLiteDatabase db = openHelper.getWritableDatabase();

		long id = db.insert(TABLA_INGRESOS, null, ingreso.getValues());
		db.close();
		if (id != -1) {
			Log.d("", "Datos insertados");
			//ingreso.setId(id);
			return true;
		}
		return false;
	}

//	public void actualizarIngreso(Ingreso ingreso) {
//		SQLiteDatabase db = openHelper.getWritableDatabase();
//		int affectedRows = db.update(TABLA_INGRESOS, ingreso.getValues(),
//				SongColumns.TRACKID + " = ?",
//				new String[] { String.valueOf(ingreso.getTrackId()) });
//		if (affectedRows > 0) {
//			Log.d("", "Datos actualizados");
//		}
//		db.close();
//	}

	
	/**
	 * @description Borra un ingreso de la base de datos 
	 * @param Ingreso
	 * @return boolean
	 */
	public boolean borrarIngreso(Ingreso ingreso) {
		SQLiteDatabase db = openHelper.getWritableDatabase();
		int affectedRows = db.delete(TABLA_INGRESOS, ColumnaIngresos.ID
				+ " = ?", new String[] { String.valueOf(ingreso.getId()) });
		db.close();
		if (affectedRows > 0) {
			Log.d("", "Datos borrados");
			return true;
		}
		return false;
	}
	
	
	//Gastos:
	
	/**
	 * @description Retorna de la base de datos el gasto correspondiente al id enviado como parametro 
	 * @param id
	 * @return Gasto
	 */
	public Gasto getGasto(int id) {
		Gasto gasto = null;
		SQLiteDatabase db = openHelper.getReadableDatabase();
		Cursor cursor = db.query(TABLA_GASTOS, new String[] {
				ColumnaGastos.ID, ColumnaGastos.TIPO, ColumnaGastos.FECHA,
				ColumnaGastos.DIA, ColumnaGastos.MES,
				ColumnaGastos.ANIO, ColumnaGastos.MONTO,
				ColumnaGastos.LUGAR, ColumnaGastos.DETALLE }, ColumnaGastos.ID + " = ?",
				new String[] { String.valueOf(id) }, null, null, null);

		if (cursor.moveToFirst()) {
			int idGasto = Integer.parseInt(cursor.getString(cursor
					.getColumnIndex(ColumnaGastos.ID)));
			int tipoGasto = Integer.parseInt(cursor.getString(cursor
					.getColumnIndex(ColumnaGastos.TIPO)));
			String fechaGasto = cursor.getString(cursor
					.getColumnIndex(ColumnaGastos.FECHA));
			int diaGasto = Integer.parseInt(cursor.getString(cursor
					.getColumnIndex(ColumnaGastos.DIA)));
			int mesGasto = Integer.parseInt(cursor.getString(cursor
					.getColumnIndex(ColumnaGastos.MES)));
			int anioGasto = Integer.parseInt(cursor.getString(cursor
					.getColumnIndex(ColumnaGastos.ANIO)));
			int montoGasto = Integer.parseInt(cursor.getString(cursor
					.getColumnIndex(ColumnaGastos.MONTO)));
			String lugarGasto = cursor.getString(cursor
					.getColumnIndex(ColumnaGastos.LUGAR));
			String detalleGasto = cursor.getString(cursor
					.getColumnIndex(ColumnaGastos.DETALLE));
			gasto = new Gasto(idGasto, tipoGasto, fechaGasto, diaGasto,
					mesGasto, anioGasto, montoGasto, lugarGasto, detalleGasto);
		}
		cursor.close();
		db.close();
		return gasto;
	}
	
	/**
	 * @description Retorna los gastos de la fecha seleccionada (dia, mes anio) 
	 * @param int dia, int mes, int anio
	 * @return List<Gasto>
	 */
	public List<Gasto> getTodosLosGastosDelDia(int dia, int mes, int anio) {
		List<Gasto> gastos = new ArrayList<Gasto>();
		SQLiteDatabase db = openHelper.getReadableDatabase();
		Cursor cursor = db.query(TABLA_GASTOS, new String[] {
				ColumnaGastos.ID, ColumnaGastos.TIPO, ColumnaGastos.FECHA,
				ColumnaGastos.DIA, ColumnaGastos.MES,
				ColumnaGastos.ANIO, ColumnaGastos.MONTO,
				ColumnaGastos.LUGAR, ColumnaGastos.DETALLE }, ColumnaGastos.DIA + " = ? AND "+ColumnaGastos.MES + "= ? AND "+ ColumnaGastos.ANIO + "= ?",
				new String[] { String.valueOf(dia), String.valueOf(mes), String.valueOf(anio) }, null, null, null);
		if (cursor.moveToFirst()) {
			do {
				int idGasto = Integer.parseInt(cursor.getString(cursor
						.getColumnIndex(ColumnaGastos.ID)));
				int tipoGasto = Integer.parseInt(cursor.getString(cursor
						.getColumnIndex(ColumnaGastos.TIPO)));
				String fechaGasto = cursor.getString(cursor
						.getColumnIndex(ColumnaGastos.FECHA));
				int diaGasto = Integer.parseInt(cursor.getString(cursor
						.getColumnIndex(ColumnaGastos.DIA)));
				int mesGasto = Integer.parseInt(cursor.getString(cursor
						.getColumnIndex(ColumnaGastos.MES)));
				int anioGasto = Integer.parseInt(cursor.getString(cursor
						.getColumnIndex(ColumnaGastos.ANIO)));
				int montoGasto = Integer.parseInt(cursor.getString(cursor
						.getColumnIndex(ColumnaGastos.MONTO)));
				String lugarGasto = cursor.getString(cursor
						.getColumnIndex(ColumnaGastos.LUGAR));
				String detalleGasto = cursor.getString(cursor
						.getColumnIndex(ColumnaGastos.DETALLE));
				Gasto gasto = new Gasto(idGasto, tipoGasto, fechaGasto, diaGasto,
						mesGasto, anioGasto, montoGasto, lugarGasto, detalleGasto);
				gastos.add(gasto);
			} while (cursor.moveToNext());
		}
		cursor.close();
		db.close();
		return gastos;
	}
	
	
	/**
	 * @description Retorna los gastos del mes seleccionado (mes anio) 
	 * @param int mes, int anio
	 * @return List<Gasto>
	 */
	public List<Gasto> getTodosLosGastosDelMes(int mes, int anio) {
		List<Gasto> gastos = new ArrayList<Gasto>();
		SQLiteDatabase db = openHelper.getReadableDatabase();
		Cursor cursor = db.query(TABLA_GASTOS, new String[] {
				ColumnaGastos.ID, ColumnaGastos.TIPO, ColumnaGastos.FECHA,
				ColumnaGastos.DIA, ColumnaGastos.MES,
				ColumnaGastos.ANIO, ColumnaGastos.MONTO,
				ColumnaGastos.LUGAR, ColumnaGastos.DETALLE }, ColumnaGastos.MES + "= ? AND "+ ColumnaGastos.ANIO + "= ?",
				new String[] { String.valueOf(mes), String.valueOf(anio) }, null, null, null);
		if (cursor.moveToFirst()) {
			do {
				int idGasto = Integer.parseInt(cursor.getString(cursor
						.getColumnIndex(ColumnaGastos.ID)));
				int tipoGasto = Integer.parseInt(cursor.getString(cursor
						.getColumnIndex(ColumnaGastos.TIPO)));
				String fechaGasto = cursor.getString(cursor
						.getColumnIndex(ColumnaGastos.FECHA));
				int diaGasto = Integer.parseInt(cursor.getString(cursor
						.getColumnIndex(ColumnaGastos.DIA)));
				int mesGasto = Integer.parseInt(cursor.getString(cursor
						.getColumnIndex(ColumnaGastos.MES)));
				int anioGasto = Integer.parseInt(cursor.getString(cursor
						.getColumnIndex(ColumnaGastos.ANIO)));
				int montoGasto = Integer.parseInt(cursor.getString(cursor
						.getColumnIndex(ColumnaGastos.MONTO)));
				String lugarGasto = cursor.getString(cursor
						.getColumnIndex(ColumnaGastos.LUGAR));
				String detalleGasto = cursor.getString(cursor
						.getColumnIndex(ColumnaGastos.DETALLE));
				Gasto gasto = new Gasto(idGasto, tipoGasto, fechaGasto, diaGasto,
						mesGasto, anioGasto, montoGasto, lugarGasto, detalleGasto);
				gastos.add(gasto);
			} while (cursor.moveToNext());
		}
		cursor.close();
		db.close();
		return gastos;
	}

	
	/**
	 * @description Retorna todos los gastos de la vida 
	 * @param 
	 * @return List<Gasto>
	 */
	public List<Gasto> getTodosLosGastos() {
		List<Gasto> gastos = new ArrayList<Gasto>();
		SQLiteDatabase db = openHelper.getReadableDatabase();
		Cursor cursor = db.query(TABLA_GASTOS, new String[] {
				ColumnaGastos.ID, ColumnaGastos.TIPO, ColumnaGastos.FECHA,
				ColumnaGastos.DIA, ColumnaGastos.MES,
				ColumnaGastos.ANIO, ColumnaGastos.MONTO,
				ColumnaGastos.LUGAR, ColumnaGastos.DETALLE }, null, null, null, null, null);
		if (cursor.moveToFirst()) {
			do {
				int idGasto = Integer.parseInt(cursor.getString(cursor
						.getColumnIndex(ColumnaGastos.ID)));
				int tipoGasto = Integer.parseInt(cursor.getString(cursor
						.getColumnIndex(ColumnaGastos.TIPO)));
				String fechaGasto = cursor.getString(cursor
						.getColumnIndex(ColumnaGastos.FECHA));
				int diaGasto = Integer.parseInt(cursor.getString(cursor
						.getColumnIndex(ColumnaGastos.DIA)));
				int mesGasto = Integer.parseInt(cursor.getString(cursor
						.getColumnIndex(ColumnaGastos.MES)));
				int anioGasto = Integer.parseInt(cursor.getString(cursor
						.getColumnIndex(ColumnaGastos.ANIO)));
				int montoGasto = Integer.parseInt(cursor.getString(cursor
						.getColumnIndex(ColumnaGastos.MONTO)));
				String lugarGasto = cursor.getString(cursor
						.getColumnIndex(ColumnaGastos.LUGAR));
				String detalleGasto = cursor.getString(cursor
						.getColumnIndex(ColumnaGastos.DETALLE));
				Gasto gasto = new Gasto(idGasto, tipoGasto, fechaGasto, diaGasto,
						mesGasto, anioGasto, montoGasto, lugarGasto, detalleGasto);
				gastos.add(gasto);
			} while (cursor.moveToNext());
		}
		cursor.close();
		db.close();
		return gastos;
	}

	/**
	 * @description Guarda un gasto en la base de datos 
	 * @param Ingreso
	 * @return boolean
	 */
	public boolean guardarGasto(Gasto gasto) {
		SQLiteDatabase db = openHelper.getWritableDatabase();
		long id = db.insert(TABLA_GASTOS, null, gasto.getValues());
		db.close();
		if (id != -1) {
			Log.d("", "Datos insertados");
			//gasto.setId(id);
			return true;
		}
		return false;
	}

//	public void actualizarGasto(Gasto gasto) {
//		SQLiteDatabase db = openHelper.getWritableDatabase();
//		int affectedRows = db.update(TABLA_GASTOS, gasto.getValues(),
//				SongColumns.TRACKID + " = ?",
//				new String[] { String.valueOf(gasto.getTrackId()) });
//		if (affectedRows > 0) {
//			Log.d("", "Datos actualizados");
//		}
//		db.close();
//	}

	/**
	 * @description Borra un gasto de la base de datos 
	 * @param Ingreso
	 * @return boolean
	 */
	public boolean borrarGasto(Gasto gasto) {
		SQLiteDatabase db = openHelper.getWritableDatabase();
		int affectedRows = db.delete(TABLA_GASTOS, ColumnaGastos.ID
				+ " = ?", new String[] { String.valueOf(gasto.getId()) });
		db.close();
		if (affectedRows > 0) {
			Log.d("", "Datos borrados");
			return true;
		}
		return false;
	}
}
