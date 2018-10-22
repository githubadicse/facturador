package com.adicse.facturador.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.adicse.facturador.component.ServiciosCPE;
import com.adicse.facturador.wintac.model.FacturaCab;
import com.adicse.facturador.wintac.service.Ing001Service;

import freemarker.core.ParseException;
import freemarker.template.MalformedTemplateNameException;
import freemarker.template.TemplateException;
import freemarker.template.TemplateNotFoundException;

@RequestMapping("/factura")
@RestController
public class CrearXmlController {
	
	
	@Autowired
	private Ing001Service ing001Service;
	
	
	@Autowired
	private ServiciosCPE serviciosCPE;
	
	
	@RequestMapping("/xml")
	public void crearXmlCPE21() throws TemplateNotFoundException, MalformedTemplateNameException, ParseException, IOException, TemplateException{
		
	
		
		List<FacturaCab> lstFacturaCab = ing001Service.getFacturaWintac();
		
		//serviciosCPE.crearXMLCPE21(lstFacturaCab.get(0));
	}

}
