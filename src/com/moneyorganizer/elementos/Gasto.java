package com.moneyorganizer.elementos;

import com.moneyorganizer.db.ConstantesBD.ColumnaGastos;

import android.content.ContentValues;

public class Gasto {
	int id;
	int tipo;
	String fecha;
	int dia;
	int mes;
	int anio;
	int monto;
	String lugar;
	String detalle;
	
	public Gasto() {
		super();
	}
	
	public Gasto(int tipo, String fecha, int dia, int mes, int anio, int monto,
			String lugar, String detalle) {
		super();
		this.tipo = tipo;
		this.fecha = fecha;
		this.dia = dia;
		this.mes = mes;
		this.anio = anio;
		this.monto = monto;
		this.lugar = lugar;
		this.detalle = detalle;
	}
	
	public Gasto(int id, int tipo, String fecha, int dia, int mes, int anio,
			int monto, String lugar, String detalle) {
		super();
		this.id = id;
		this.tipo = tipo;
		this.fecha = fecha;
		this.dia = dia;
		this.mes = mes;
		this.anio = anio;
		this.monto = monto;
		this.lugar = lugar;
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

	public String getLugar() {
		return lugar;
	}

	public void setLugar(String lugar) {
		this.lugar = lugar;
	}

	public String getDetalle() {
		return detalle;
	}

	public void setDetalle(String detalle) {
		this.detalle = detalle;
	}

	@Override
	public String toString() {
		return "Gasto [id=" + id + ", tipo=" + tipo + ", fecha=" + fecha
				+ ", dia=" + dia + ", mes=" + mes + ", anio=" + anio
				+ ", monto=" + monto + ", lugar=" + lugar + ", detalle="
				+ detalle + "]";
	}
	public ContentValues getValues() {
		ContentValues values = new ContentValues();
		values.put(ColumnaGastos.ID, getId());
		values.put(ColumnaGastos.TIPO, getTipo());
		values.put(ColumnaGastos.FECHA, getFecha());
		values.put(ColumnaGastos.DIA, getDia());
		values.put(ColumnaGastos.MES, getMes());
		values.put(ColumnaGastos.ANIO, getAnio());
		values.put(ColumnaGastos.MONTO, getMonto());
		values.put(ColumnaGastos.LUGAR, getLugar());
		values.put(ColumnaGastos.DETALLE, getDetalle());
		return values;
	}
}
