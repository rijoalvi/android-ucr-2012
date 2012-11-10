package com.moneyorganizer.conexion;

public class XPPDataParser implements DataParser {
	@Override
	public TipoCambio parseUser(char[] in) {
		TipoCambio tipoCambio = new TipoCambio();
        String s=new String(in);
        String []a= s.split("NUM_VALOR");
        s=a[1].substring(4,16);
		tipoCambio.setValor(s);
		return tipoCambio;
	}
}
