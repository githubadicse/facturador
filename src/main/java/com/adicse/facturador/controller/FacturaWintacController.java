package com.adicse.facturador.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.adicse.facturador.wintac.model.FacturaCab;
import com.adicse.facturador.wintac.model.FacturaDetalle;
import com.adicse.facturador.wintac.service.Ing001Service;


@RestController
@RequestMapping("/factura")

public class FacturaWintacController {

	
	@Autowired
	private Ing001Service  ing001Service;
	
	
	@RequestMapping("/getall")
	@ResponseBody
	public List<FacturaCab> getFac() {
		
		List<FacturaCab> lst = ing001Service.getFacturaWintac();
		
		for(FacturaCab row:lst ) {
			

			for(FacturaDetalle rowDetalle:row.getFacturaDetalles()) {
				//rowDetalle.setFacturaCab(null);
				System.out.println(rowDetalle.getProducto().getDscProducto() +  " " + "Punt : " + rowDetalle.getPrecioUnitario() + " Cantidad : " + rowDetalle.getCantidad() );
			}
		}
		return null;
	}
}
