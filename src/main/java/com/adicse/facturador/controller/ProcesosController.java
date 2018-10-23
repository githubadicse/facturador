package com.adicse.facturador.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.adicse.facturador.component.FileComponent;
import com.adicse.facturador.component.ResumenBoletaComponent;

@RestController
@RequestMapping("/procesos")
public class ProcesosController {

	
	@Autowired
	ResumenBoletaComponent resumenBoletaComponent;
	
	@Autowired
	FileComponent fileComponent;
	
	
	@RequestMapping("/cargaDocumentos")
	public void cargaDocumentos() {
		
		
		fileComponent.setFileToModel("RESUMENBOLETA");
		
	}	
	
	
	
	@RequestMapping("/generaXmlResumen")
	@ResponseBody
	public void generaResumendeBoleta(@RequestParam("fecha") String fecha) {
		
		System.out.println(fecha);
		resumenBoletaComponent.generaXmlResumenBoleta("PEN", fecha);
		
	}
}
