package com.moneyorganizer.conexion;

public class TipoCambio {
	private String num_valor;

    public String getValor() {
        return num_valor;
    }

    public void setValor(String valor) {
        this.num_valor = valor;
    }
    @Override
    public String toString() {
        return "TipoCambio =" + num_valor;
    }
}
