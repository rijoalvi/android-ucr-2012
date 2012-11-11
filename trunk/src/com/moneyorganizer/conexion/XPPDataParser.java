package com.moneyorganizer.conexion;
import com.moneyorganizer.conexion.DataParser;

public class XPPDataParser implements DataParser {
	public TipoCambio parseUser(char[] in) {
		TipoCambio tipoCambio = new TipoCambio();
        String s=new String(in);
        String []a= s.split("NUM_VALOR");
        s=a[1].substring(4,16);
		tipoCambio.setValor(s);
		return tipoCambio;
	}
}
