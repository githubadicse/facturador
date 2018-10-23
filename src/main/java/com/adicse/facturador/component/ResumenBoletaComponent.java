package com.adicse.facturador.component;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.adicse.facturador.model.DocumentoCab;
import com.adicse.facturador.model.DocumentoDetalle;
import com.adicse.facturador.modelToJson.FacturaCab;
import com.adicse.facturador.modelToJson.FacturaDetalle;
import com.adicse.facturador.service.DocumentoCabService;

import freemarker.core.ParseException;
import freemarker.template.MalformedTemplateNameException;
import freemarker.template.TemplateException;
import freemarker.template.TemplateNotFoundException;

@Component
public class ResumenBoletaComponent {
	
	
	@Autowired
	private DocumentoCabService documentoCabService;
	
	@Autowired
	ServiciosCPE cerviciosCPE;
	
	@Autowired
	PropiedadesComponent propiedadesComponent;
	
	public void saveResumenBoletaToModel(List<FacturaCab> lstFacturaCab){
		
		
		
		DocumentoCab documentoCab = null;

		for(FacturaCab facturaCab : lstFacturaCab) {
			
			float igv = facturaCab.getIgvPorcentaje();
			
			String td = facturaCab.getTipoDocumento().getAbrDocumento().substring(0, 1).equals("F") ? "F" : "B";
			String documento = td + String.format("%03d", facturaCab.getSerie()) + "-"
					+ String.format("%08d", facturaCab.getNumero());
			
			
			documentoCab = new DocumentoCab();
			
			documentoCab.setIdDocumentoCab(documento);
			documentoCab.setFechaEmision(facturaCab.getFecha());
			documentoCab.setFechaVencimiento(facturaCab.getFecha() );
			documentoCab.setComprobanteEmitidoSunat(documento);
			
			//03 boleta
			documentoCab.setCodigoDocumentoSunat("03");
			
			//03 dsc boleta
			documentoCab.setDscDocumentoSunat("BOLETA ELECTRONICA");
			
			documentoCab.setComprobanteSerie( String.format("%03d", facturaCab.getSerie()) );
			documentoCab.setComprobanteNumero( String.format("%08d", facturaCab.getNumero()) );
			
			
			//Codigo tipo documento cliente sunat
			documentoCab.setCodigoTipoDocumentoCliente( Integer.parseInt( facturaCab.getCliente().getTipoDocumentoCliente()) );
			
			documentoCab.setNumeroDocumentoCliente(facturaCab.getCliente().getNumeroDocumentoCliente());
			
			documentoCab.setNombreCliente(facturaCab.getCliente().getRazonSocial());
			documentoCab.setDireccionCliente(facturaCab.getCliente().getDireccion() );
			documentoCab.setTipoMoneda(facturaCab.getTipoMoneda());
			
			
			/** Procesamos el detalle del documento **/
			List<DocumentoDetalle> lstDocumentoDetalle = new ArrayList<>();
			DocumentoDetalle documentoDetalle = null;
			float sumaValorVenta = 0;
			float sumaTotalDescuento = 0;
			float sumaValorVentaGrabada = 0;
			float sumaValorVentaExonerado = 0;
			float sumaTotalDeImpuestos = 0;
			float sumaImporteTotalVenta = 0;
			
			for(FacturaDetalle rowFacturaDetalle:facturaCab.getFacturaDetalles()) {
				String idDetalleFactura =  UUID.randomUUID().toString();
				documentoDetalle = new DocumentoDetalle();
				
				//Documento cabecera en el detalle
				documentoDetalle.setDocumentoCab(documentoCab);
				
				documentoDetalle.setIdFacturaDetalle(rowFacturaDetalle.getIdFacturaDetalle() );
				documentoDetalle.setDocumentoCab(documentoCab);
				documentoDetalle.setCantidad(rowFacturaDetalle.getCantidad());
				
				documentoDetalle.setPrecioUnitarioConIgv(rowFacturaDetalle.getPrecioUnitarioIncluidoIgv());
				documentoDetalle.setPrecioUnitarioSinIgv(rowFacturaDetalle.getPrecioUnitarioSinIgv());
				
				documentoDetalle.setTotalValorVentaBruto(rowFacturaDetalle.getBaseImponible());
				
				documentoDetalle.setPorcentajeDescuento(rowFacturaDetalle.getPorcentajeDescuento());
				documentoDetalle.setTotalDescuento(rowFacturaDetalle.getTotalDescuento());
				
				documentoDetalle.setTotalIgv(rowFacturaDetalle.getImpuestoTotal());
				
				documentoDetalle.setTotalValorVentaGrabada(rowFacturaDetalle.getValorVentaGrabado());
				documentoDetalle.setTotalValorVentaExonerado(rowFacturaDetalle.getValorVentaExonerado());
				documentoDetalle.setTotalVenta(rowFacturaDetalle.getImporteTotalVenta());
				
				
				documentoDetalle.setCodigoProducto( Long.toString( rowFacturaDetalle.getProducto().getIdProducto()) );
				documentoDetalle.setDscProductoServicio( rowFacturaDetalle.getProducto().getDscProducto());
				
				
				sumaValorVenta = sumaValorVenta + rowFacturaDetalle.getBaseImponible();
				sumaTotalDescuento = sumaTotalDescuento + rowFacturaDetalle.getTotalDescuento();

				sumaValorVentaGrabada = sumaValorVentaGrabada + rowFacturaDetalle.getValorVentaGrabado();
				sumaValorVentaExonerado = sumaValorVentaExonerado + rowFacturaDetalle.getValorVentaExonerado();

				sumaTotalDeImpuestos = sumaTotalDeImpuestos + rowFacturaDetalle.getImpuestoTotal();

				sumaImporteTotalVenta = sumaImporteTotalVenta + (rowFacturaDetalle.getValorVentaGrabado() + rowFacturaDetalle.getValorVentaExonerado() + rowFacturaDetalle.getImpuestoTotal());
				
				lstDocumentoDetalle.add(documentoDetalle);
				
			}
			
			documentoCab.setSumValorVentaBruto(sumaValorVenta);
			documentoCab.setSumTotalDescuento(sumaTotalDescuento);
			documentoCab.setSumValorVentaGrabada(sumaValorVentaGrabada);
			documentoCab.setSumValorVentaExonerada(sumaValorVentaExonerado);
			documentoCab.setSumIgv(sumaTotalDeImpuestos);
			documentoCab.setSumTotalVenta(sumaImporteTotalVenta);
			documentoCab.setEstadoRegistro("PEN");
			
			//documento detalle en la cabecera
			documentoCab.setDocumentoDetalles(lstDocumentoDetalle);
			
			documentoCabService.save(documentoCab);
			
			lstDocumentoDetalle = new ArrayList<>();
			
			
			
		}
		
		
		
		
	}

	/** Estado Registro PEN**/
	public void generaXmlResumenBoleta(String estadoRegistro,String fechaEmision) {
		
		//Obtenemos la relacion de Boletas con estado registro PEN funcion definida en el dao DocumentoDao
		List<DocumentoCab> lstDocumentoCab = documentoCabService.getDocumentoDaoByEstadoRegistro(estadoRegistro,fechaEmision);
		
		Map<String,Object> map = propiedadesComponent.getValoresDePropiedades();
		
		try {
			
			cerviciosCPE.crearXMLCPE21ResumenBoleta(lstDocumentoCab, (String)map.get("rutaArchivoXml")+"ResumenDeBoleta" , (String)map.get("rutaArchivoFtl") , (String)map.get("nombreArchivoResumenBoletaFtl"), 
					(String)map.get("rutaCertificado"), (String)map.get("nombreArchivoCertificado"), (String)map.get("passFirma"), 
					(String)map.get("UsuSol") ,(String) map.get("PassSol") , (String)map.get("RutaRta"));
			
			
		} catch (TemplateNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (MalformedTemplateNameException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (TemplateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	
	}
	

	
}
