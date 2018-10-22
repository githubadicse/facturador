package com.adicse.facturador.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.adicse.facturador.sunat.model.FacturaCabSunat;
import com.adicse.facturador.sunat.service.FacturaCabService;


@RestController
@RequestMapping("/facturador")
public class FacturaSunatController {

	@Autowired
	private FacturaCabService  facturaCabService;
	
	
	@RequestMapping("/demo")
	public FacturaCabSunat getFac() {
		return facturaCabService.getFacturaSunat(1l);
	}
	
}
