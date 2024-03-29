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
	float monto;
	String lugar;
	String detalle;
	
	public Gasto() {
		super();
	}
	
	public Gasto(int tipo, String fecha, int dia, int mes, int anio, float monto,
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
			float monto, String lugar, String detalle) {
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

	public float getMonto() {
		return monto;
	}

	public void setMonto(float monto) {
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
		//values.put(ColumnaGastos.ID, this.getId());
		values.put(ColumnaGastos.TIPO, this.getTipo());
		values.put(ColumnaGastos.FECHA, this.getFecha());
		values.put(ColumnaGastos.DIA, this.getDia());
		values.put(ColumnaGastos.MES, this.getMes());
		values.put(ColumnaGastos.ANIO, this.getAnio());
		values.put(ColumnaGastos.MONTO,this. getMonto());
		values.put(ColumnaGastos.LUGAR, this.getLugar());
		values.put(ColumnaGastos.DETALLE, this.getDetalle());
		return values;
	}
}
