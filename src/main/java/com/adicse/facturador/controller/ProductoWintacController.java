package com.adicse.facturador.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.adicse.facturador.wintac.model.Producto;
import com.adicse.facturador.wintac.service.ProductoService;


@RestController
@RequestMapping("/producto")

public class ProductoWintacController {
	
	@Autowired
	private ProductoService productoService;
	
	
	@RequestMapping("/getall")
	public List<Producto> getProductos() {
		return productoService.getAll();
	}	
}
