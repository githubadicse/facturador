package com.adicse.facturador.dto;

public class ControlProceso {
	
	static boolean procesando;

	public static boolean isProcesando() {
		return procesando;
	}

	public static void setProcesando(boolean procesando) {
		ControlProceso.procesando = procesando;
	}
	
	
	

}
