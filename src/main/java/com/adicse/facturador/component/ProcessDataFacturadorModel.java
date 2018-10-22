package com.adicse.facturador.component;

import org.springframework.stereotype.Component;

import com.adicse.facturador.model.DocumentoCab;
import com.adicse.facturador.modelToJson.FacturaCab;

@Component
public class ProcessDataFacturadorModel {
	
	
	public void crearDocumentoCab(FacturaCab facturaCab) {
		
	    String ruc = facturaCab.getEmisorRuc();
	    String codigoSunat = facturaCab.getTipoDocumento().getAbrDocumento().substring(0,1).equals("F")?"01":"03";
	    String td = facturaCab.getTipoDocumento().getAbrDocumento().substring(0, 1).equals("F")?"F":"B";
	    String documento = td + String.format("%03d",facturaCab.getSerie()) +"-"+ String.format("%08d",facturaCab.getNumero() );
		
		
		DocumentoCab documentoCab = new DocumentoCab();
		documentoCab.setIdDocumentoCab(ruc+"-"+ documento);
		documentoCab.setFechaEmision(facturaCab.getFecha());
		documentoCab.setFechaVencimiento(facturaCab.getFecha());
		documentoCab.setCodigoDocumentoSunat(codigoSunat);
		documentoCab.setDscDocumentoSunat(codigoSunat.equals("01")?"FACTURA":"BOLETA");
		//documentoCab.setNumeroDocumentoCliente(facturaCab.getCliente().get);
		
		
	}

}
