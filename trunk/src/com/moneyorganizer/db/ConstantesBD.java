package com.moneyorganizer.db;

public interface ConstantesBD {
	String TABLA_INGRESOS = "ingresos";
	String TABLA_GASTOS = "gastos";

	static interface ColumnaIngresos {

		String ID = "id";
		String TIPO = "tipo";
		String FECHA = "fecha";
		String DIA = "dia";
		String MES = "mes";
		String ANIO = "anio";
		String MONTO = "monto";
		String FUENTE = "fuente";
		String DETALLE = "detalle";
	}
	
	static interface ColumnaGastos {

		String ID = "id";
		String TIPO = "tipo";
		String FECHA = "fecha";
		String DIA = "dia";
		String MES = "mes";
		String ANIO = "anio";
		String MONTO = "monto";
		String LUGAR = "lugar";
		String DETALLE = "detalle";
	}
	
	String[] COLUMNA_INGRESOS = new String[] { ColumnaIngresos.ID,
			ColumnaIngresos.TIPO, ColumnaIngresos.FECHA,
			ColumnaIngresos.DIA, ColumnaIngresos.MES, ColumnaIngresos.ANIO,
			ColumnaIngresos.MONTO, ColumnaIngresos.FUENTE,
			ColumnaIngresos.DETALLE };

	String[] COLUMNA_GASTOS = new String[] { ColumnaGastos.ID,
			ColumnaGastos.TIPO, ColumnaGastos.FECHA,
			ColumnaGastos.DIA, ColumnaGastos.MES, ColumnaGastos.ANIO,
			ColumnaGastos.MONTO, ColumnaGastos.LUGAR,
			ColumnaGastos.DETALLE };
}
