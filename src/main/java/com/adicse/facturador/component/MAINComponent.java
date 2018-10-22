package com.adicse.facturador.component;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.adicse.facturador.modelToJson.FacturaCab;

@Component
public class MAINComponent {
	
	
	@Autowired
	private ResumenBoletaComponent resumenBoletaComponent;
	
	
	
	public void runFactura() {
		
	}
	
	public void runBoleta(List<FacturaCab> lstFacturaCab) {
		resumenBoletaComponent.saveResumenBoletaToModel(lstFacturaCab);
	}

	public void notaDeCredito() {
		
	}	
	
	public void resumenDeBoleta() {
		
	}	
}
