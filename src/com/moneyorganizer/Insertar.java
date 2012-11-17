package com.moneyorganizer;

import com.moneyorganizer.db.ControladorBD;
import com.moneyorganizer.elementos.Gasto;
import com.moneyorganizer.elementos.Ingreso;

public class Insertar {

	public Insertar(ControladorBD controlador){
		InsertarIngresos(controlador);
		InsertarGastos(controlador);
		
	}
	
	public void InsertarGastos(ControladorBD controlador){
		Gasto unGasto = new Gasto();
		unGasto.setTipo(1);
		unGasto.setAnio(2012);
		unGasto.setDia(16);
		unGasto.setMes(10);
		unGasto.setMonto(640);
		unGasto.setDetalle("Bus");
		unGasto.setLugar("no me acuerdo");
		controlador.guardarGasto(unGasto);
		
		unGasto = new Gasto();
		unGasto.setTipo(3);
		unGasto.setAnio(2012);
		unGasto.setDia(16);
		unGasto.setMes(10);
		unGasto.setMonto(1240);
		unGasto.setDetalle("Nachos");
		unGasto.setLugar("no me acuerdo");
		controlador.guardarGasto(unGasto);
		
		unGasto = new Gasto();
		unGasto.setTipo(4);
		unGasto.setAnio(2012);
		unGasto.setDia(16);
		unGasto.setMes(10);
		unGasto.setMonto(1240);
		unGasto.setDetalle("Cine");
		unGasto.setLugar("Mall");
		controlador.guardarGasto(unGasto);
		
		
		unGasto = new Gasto();
		unGasto.setTipo(1);
		unGasto.setAnio(2012);
		unGasto.setDia(16);
		unGasto.setMes(10);
		unGasto.setMonto(260);
		unGasto.setDetalle("bus");
		unGasto.setLugar("Sabanilla");
		controlador.guardarGasto(unGasto);

		unGasto = new Gasto();
		unGasto.setTipo(6);
		unGasto.setAnio(2012);
		unGasto.setDia(16);
		unGasto.setMes(9);
		unGasto.setMonto(7240);
		unGasto.setDetalle("Blusa");
		unGasto.setLugar("Mall");
		controlador.guardarGasto(unGasto);
		
		unGasto = new Gasto();
		unGasto.setTipo(8);
		unGasto.setAnio(2012);
		unGasto.setDia(16);
		unGasto.setMes(9);
		unGasto.setMonto(7240);
		unGasto.setDetalle("Regalo Cumpleaños");
		unGasto.setLugar("Mall");
		controlador.guardarGasto(unGasto);
		
		unGasto = new Gasto();
		unGasto.setTipo(8);
		unGasto.setAnio(2012);
		unGasto.setDia(16);
		unGasto.setMes(11);
		unGasto.setMonto(7240);
		unGasto.setDetalle("Regalo Mámi");
		unGasto.setLugar("mall");
		controlador.guardarGasto(unGasto);
		
		unGasto = new Gasto();
		unGasto.setTipo(5);
		unGasto.setAnio(2012);
		unGasto.setDia(16);
		unGasto.setMes(11);
		unGasto.setMonto(740);
		unGasto.setDetalle("Memoria");
		unGasto.setLugar("Amazon");
		controlador.guardarGasto(unGasto);
		
		unGasto = new Gasto();
		unGasto.setTipo(8);
		unGasto.setAnio(2012);
		unGasto.setDia(16);
		unGasto.setMes(8);
		unGasto.setMonto(1500);
		unGasto.setDetalle("Cine");
		unGasto.setLugar("Multiplaza");
		controlador.guardarGasto(unGasto);
		
		unGasto = new Gasto();
		unGasto.setTipo(1);
		unGasto.setAnio(2012);
		unGasto.setDia(16);
		unGasto.setMes(8);
		unGasto.setMonto(250);
		unGasto.setDetalle("Bus");
		unGasto.setLugar("Multiplaza");
		controlador.guardarGasto(unGasto);
		
	}
	
	public void InsertarIngresos(ControladorBD controlador){
		Ingreso unIngreso = new Ingreso();
		unIngreso.setTipo(1);
		unIngreso.setAnio(2012);
		unIngreso.setDia(16);
		unIngreso.setMes(10);
		unIngreso.setMonto(1000);
		unIngreso.setDetalle("METICS");
		unIngreso.setFuente("no me acuerdo");
		controlador.guardarIngreso(unIngreso);
		
		unIngreso = new Ingreso();
		unIngreso.setTipo(8);
		unIngreso.setAnio(2012);
		unIngreso.setDia(20);
		unIngreso.setMes(9);
		unIngreso.setMonto(1300);
		unIngreso.setDetalle("Mámi");
		unIngreso.setFuente("Regalo");
		controlador.guardarIngreso(unIngreso);

		unIngreso = new Ingreso();
		unIngreso.setTipo(8);
		unIngreso.setAnio(2012);
		unIngreso.setDia(20);
		unIngreso.setMes(9);
		unIngreso.setMonto(1300);
		unIngreso.setDetalle("Mámi");
		unIngreso.setFuente("Regalo");
		controlador.guardarIngreso(unIngreso);
		
		unIngreso = new Ingreso();
		unIngreso.setTipo(5);
		unIngreso.setAnio(2012);
		unIngreso.setDia(20);
		unIngreso.setMes(11);
		unIngreso.setMonto(15000);
		unIngreso.setDetalle("Deuda Juan");
		unIngreso.setFuente("Juan");
		controlador.guardarIngreso(unIngreso);
		
		unIngreso = new Ingreso();
		unIngreso.setTipo(7);
		unIngreso.setAnio(2012);
		unIngreso.setDia(20);
		unIngreso.setMes(10);
		unIngreso.setMonto(7000);
		unIngreso.setDetalle("Cancelo Paseo");
		unIngreso.setFuente("UCR");
		controlador.guardarIngreso(unIngreso);
		
		unIngreso = new Ingreso();
		unIngreso.setTipo(3);
		unIngreso.setAnio(2012);
		unIngreso.setDia(20);
		unIngreso.setMes(7);
		unIngreso.setMonto(1600);
		unIngreso.setDetalle("");
		unIngreso.setFuente("Regalo");
		controlador.guardarIngreso(unIngreso);
		
		unIngreso = new Ingreso();
		unIngreso.setTipo(6);
		unIngreso.setAnio(2012);
		unIngreso.setDia(20);
		unIngreso.setMes(10);
		unIngreso.setMonto(1300);
		unIngreso.setDetalle("Loteria");
		unIngreso.setFuente("Loteria");
		controlador.guardarIngreso(unIngreso);
		
		unIngreso = new Ingreso();
		unIngreso.setTipo(1);
		unIngreso.setAnio(2012);
		unIngreso.setDia(20);
		unIngreso.setMes(6);
		unIngreso.setMonto(2200);
		unIngreso.setDetalle("lavar carro");
		unIngreso.setFuente("Mámi");
		controlador.guardarIngreso(unIngreso);
		
		
	}
}
