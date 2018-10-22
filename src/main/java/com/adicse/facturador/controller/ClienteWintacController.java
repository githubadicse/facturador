package com.adicse.facturador.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.adicse.facturador.wintac.model.Cliente;
import com.adicse.facturador.wintac.service.ClienteService;

@RestController
@RequestMapping("/cliente")
public class ClienteWintacController {
	
	
	@Autowired
	private ClienteService clienteService;
	
	
	@RequestMapping("/getall")
	public List<Cliente> getFac() {
		
		
		return clienteService.getAll();
	}	
	

}
