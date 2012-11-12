package com.moneyorganizer.elementos;

import android.content.ContentValues;

import com.moneyorganizer.db.ConstantesBD.ColumnaIngresos;

public class Ingreso {
	int id;
	int tipo;
	String fecha;
	int dia;
	int mes;
	int anio;
	int monto;
	String fuente;
	String detalle;
	
	public Ingreso() {
		super();
	}

	public Ingreso(int tipo, String fecha, int dia, int mes, int anio,
			int monto, String fuente, String detalle) {
		super();
		this.tipo = tipo;
		this.fecha = fecha;
		this.dia = dia;
		this.mes = mes;
		this.anio = anio;
		this.monto = monto;
		this.fuente = fuente;
		this.detalle = detalle;
	}

	public Ingreso(int id, int tipo, String fecha, int dia, int mes, int anio,
			int monto, String fuente, String detalle) {
		super();
		this.id = id;
		this.tipo = tipo;
		this.fecha = fecha;
		this.dia = dia;
		this.mes = mes;
		this.anio = anio;
		this.monto = monto;
		this.fuente = fuente;
		this.detalle = detalle;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getTipo() {
		return tipo;
	}

	public void setTipo(int tipo) {
		this.tipo = tipo;
	}

	public String getFecha() {
		return fecha;
	}

	public void setFecha(String fecha) {
		this.fecha = fecha;
	}

	public int getDia() {
		return dia;
	}

	public void setDia(int dia) {
		this.dia = dia;
	}

	public int getMes() {
		return mes;
	}

	public void setMes(int mes) {
		this.mes = mes;
	}

	public int getAnio() {
		return anio;
	}

	public void setAnio(int anio) {
		this.anio = anio;
	}

	public int getMonto() {
		return monto;
	}

	public void setMonto(int monto) {
		this.monto = monto;
	}

	public String getFuente() {
		return fuente;
	}

	public void setFuente(String fuente) {
		this.fuente = fuente;
	}

	public String getDetalle() {
		return detalle;
	}

	public void setDetalle(String detalle) {
		this.detalle = detalle;
	}

	@Override
	public String toString() {
		return "Ingreso [id=" + id + ", tipo=" + tipo + ", fecha=" + fecha
				+ ", dia=" + dia + ", mes=" + mes + ", anio=" + anio
				+ ", monto=" + monto + ", fuente=" + fuente + ", detalle="
				+ detalle + "]";
	}
	public ContentValues getValues() {
		ContentValues values = new ContentValues();
		//values.put(ColumnaIngresos.ID, getId());
		values.put(ColumnaIngresos.TIPO, getTipo());
		values.put(ColumnaIngresos.FECHA, getFecha());
		values.put(ColumnaIngresos.DIA, getDia());
		values.put(ColumnaIngresos.MES, getMes());
		values.put(ColumnaIngresos.ANIO, getAnio());
		values.put(ColumnaIngresos.MONTO, getMonto());
		values.put(ColumnaIngresos.FUENTE, getFuente());
		values.put(ColumnaIngresos.DETALLE, getDetalle());
		return values;
	}
}
