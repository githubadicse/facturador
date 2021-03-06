package com.adicse.facturador.component;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.FilenameUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.adicse.facturador.cpe.model.CamposDetalle;
import com.adicse.facturador.model.DocumentoCab;
import com.adicse.facturador.model.DocumentoDetalle;
import com.adicse.facturador.modelToJson.FacturaCab;
import com.adicse.facturador.modelToJson.FacturaDetalle;
import com.adicse.facturador.service.DocumentoCabService;
import com.adicse.facturador.service.ResumenBoletaService;

import freemarker.core.ParseException;
import freemarker.template.Configuration;
import freemarker.template.MalformedTemplateNameException;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import freemarker.template.TemplateExceptionHandler;
import freemarker.template.TemplateNotFoundException;
import freemarker.template.Version;

/*Con este procedimiento creamos y firmamos el archivo para enviar a la suna
a su vez verificamos si es factura para que su envio sea inmediato.*/

@Component
public class ServiciosCPE {

	private static final Logger log = LoggerFactory.getLogger(ServiciosCPE.class);

	private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");

	@Autowired
	SendSunat sendSunat;

	@Autowired
	private Signature signature;

	@Autowired
	private ProcesosRespuestaSunat procesosRespuestaSunat;
	
	@Autowired
	PropiedadesComponent propiedadesComponent;
	
	@Autowired
	DocumentoCabService documentoCabService;
	
	@Autowired
	ResumenBoletaService resumenBoletaService;

	public void crearXMLCPE21(FacturaCab facturaCab, String rutaArchivoXml, String rutaArchivoFtl,
			String nombreArchivoFtl, String rutaCertificado, String nombreArchivoCertificado, String passFirma,
			String UsuSol, String PassSol, String RutaRta)

			throws TemplateNotFoundException, MalformedTemplateNameException, ParseException, IOException,
			TemplateException {

		String ruc = facturaCab.getEmisorRuc();

		String codigoSunat = facturaCab.getTipoDocumento().getAbrDocumento().substring(0, 1).equals("F") ? "01" : "03";
		String td = facturaCab.getTipoDocumento().getAbrDocumento().substring(0, 1).equals("F") ? "F" : "B";
		String documento = td + String.format("%03d", facturaCab.getSerie()) + "-"
				+ String.format("%08d", facturaCab.getNumero());

		String archivo = ruc + "-" + codigoSunat + "-" + documento + ".xml";

		File fileXml = new File(rutaArchivoXml + "/" + archivo);
		if (!fileXml.exists()) {

			Configuration cfg = new Configuration(new Version("2.3.23"));

			try {
				cfg.setDirectoryForTemplateLoading(new File(rutaArchivoFtl));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			cfg.setDefaultEncoding("UTF-8");
			cfg.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);

			// Obetnemos los datos de la cabecera y del detalle
			Map<String, Object> map = new HashMap<>();
			map = this.setCabecera(facturaCab);

			// Instantiate template
			// Template template = cfg.getTemplate("xmlCPE.ftl");
			Template template = cfg.getTemplate(nombreArchivoFtl);

			// Console output
			// Writer console = new OutputStreamWriter(System.out);

			// template.process(map, console);
			// console.flush();

			// File output

			Writer file = new FileWriter(new File(rutaArchivoXml + "/" + archivo));
			template.process(map, file);
			file.flush();
			file.close();

			String archivoSinExtension = FilenameUtils.removeExtension(archivo);

			// Procedemos a firmar el archivo con el certificado de la sunat
			// Pause for 4 seconds
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			// AQUI FIRMAMOS EL ARCHIVO XML CREADO.
			signature.firmarArchivoXml(rutaArchivoXml + "/" + archivoSinExtension, rutaCertificado,
					nombreArchivoCertificado, passFirma);

			// AHORA ENVIAMOS A LA SUNAT

			sendSunat.EnviarSunat(ruc, UsuSol, PassSol, rutaArchivoXml + "/" + archivoSinExtension, archivoSinExtension,
					RutaRta + "/", archivoSinExtension);

			// AHORA ENVIAMOS VERIFICAMOS LA RESPUESTA DE LA SUNAT, EN LA CARPETA RESPUESTA
			// ESTARA EL ZIP QUE DEVUELVE LA SUNAT
			// PRIMERO VERIFICAMOS SI EL ARCHIVO SE ENCUENTRA
			String archivoZipRespuesta = "R-" + archivoSinExtension + ".zip";

			// Componente procesosRespuestaSunat
			String archivoDesempaquetadoXml = procesosRespuestaSunat.desempaquetarArchivoZip(RutaRta,
					archivoZipRespuesta);

			// REVISAMOS LA RESPUESTA DE LA SUNAT.
			Integer statusSunat = procesosRespuestaSunat.verificarXmlRespuestaSunat(archivoDesempaquetadoXml, RutaRta);
			System.out.println("Status Sunat : " + statusSunat);

			log.info("ARCHIVO GENERADO {}", archivo + "  fecha/hora : " + dateFormat.format(new Date()));

		} else {
			log.info("ARCHIVO EXISTE {}", archivo + "  fecha/hora : " + dateFormat.format(new Date()));
		}

	}

	/////// PROCEDIMIENTO PARA CREAR EL RESUMEN DE BOLETAS ///////////////

	public void crearXMLCPE21ResumenBoleta(List<DocumentoCab> lstDocumentoCab, String rutaArchivoXml,
			String rutaArchivoFtl, String nombreArchivoFtl, String rutaCertificado, String nombreArchivoCertificado,
			String passFirma, String UsuSol, String PassSol, String RutaRta)

			throws TemplateNotFoundException, MalformedTemplateNameException, ParseException, IOException,
			TemplateException {

		
		
		SimpleDateFormat fd = new SimpleDateFormat("yyyy-MM-dd");

		DocumentoCab facturaCab = lstDocumentoCab.get(0);
		String fechaEmision = fd.format(facturaCab.getFechaEmision()).replace("-", "");
		String ruc = facturaCab.getNumeroDocumentoCliente();

		String documento = "B" +  facturaCab.getComprobanteSerie() + "-"+ facturaCab.getComprobanteNumero();

		String nombreArchivo = "RC" + fechaEmision + "00001";

		File fileXml = new File(rutaArchivoXml + "/" + nombreArchivo + ".xml");

		
		// Definicion de la plantilla a usar para pasar el modelo de datos --
		
		Configuration cfg = new Configuration(new Version("2.3.23"));

		try {
			cfg.setDirectoryForTemplateLoading(new File(rutaArchivoFtl));
		} catch (IOException e) {
			e.printStackTrace();
		}
		cfg.setDefaultEncoding("UTF-8");
		cfg.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);

		// Instantiate template
		// Template template = cfg.getTemplate("resumenDiarioXmlUbl20.ftl");
		Template template = cfg.getTemplate(nombreArchivoFtl);



		//Writer file = new FileWriter(new File(rutaArchivoXml + "/" + nombreArchivo+ ".xml"));

		Map<String, Object> map = new HashMap<>();
		Integer item = 1;
		for (DocumentoCab rowDocumentoCab : lstDocumentoCab) {
			rowDocumentoCab.setItem(item);
			item ++;
			documentoCabService.save(rowDocumentoCab);

		}

		LocalDate localDate = LocalDate.now();
		LocalDateTime localDateTime = LocalDateTime.now();
		ZoneId zoneId = ZoneId.of("America/Lima");

		ZonedDateTime zonedDateTime = ZonedDateTime.of(localDateTime, zoneId);

		Integer dia = localDate.getDayOfMonth();
		Integer mes = localDate.getMonth().getValue();
		Integer anno = localDate.getYear();		
		
		String fechaCreacion = String.format("%04d",anno)+ String.format("%02d",mes) + String.format("%02d",dia);
		
		Integer nexId = resumenBoletaService.getMax();
		String identificadorArchivoEnvio =  "RC-" + fechaCreacion + "-"+String.format("%04d", nexId );
		map = this.setResumenBoleta(lstDocumentoCab, identificadorArchivoEnvio);
		
		
		// Console output
		Writer console = new OutputStreamWriter(System.out);

		 template.process(map, console);
		 console.flush();

		// File output

		Writer file = new FileWriter(new File(rutaArchivoXml + "/" + nombreArchivo + ".XML"));
		template.process(map, file);
		file.flush();
		file.close();
		 
				


	}


	
	
	
	
	
	
	
	
	public Map<String, Object> setResumenBoleta(List<DocumentoCab> lstDocumentoCab, String identificadorArchivoEnvio) {
		Map<String, Object> map = new HashMap<>();
		
		Map<String, Object> props = propiedadesComponent.getValoresDePropiedades();
		// Definimos la fecha de trabajo segun el reloj del servidor
		LocalDate localDate = LocalDate.now();
		
		SimpleDateFormat fd = new SimpleDateFormat("yyyy-MM-dd");

		Integer dia = localDate.getDayOfMonth();
		Integer mes = localDate.getMonth().getValue();
		Integer anno = localDate.getYear();
		String fechaGeneracionResumen = anno+"-"+mes+"-"+dia;
		
		DocumentoCab documentoCab = lstDocumentoCab.get(0);
		
		map.put("identificadorArchivoEnvio", identificadorArchivoEnvio);
		map.put("fechaeGeneracionResumen", fechaGeneracionResumen);
		map.put("fechaEmisionDocumentos", fd.format(documentoCab.getFechaEmision()));
		map.put("rucEmisor", props.get("rucEmisor") );
		
		map.put("rucEmisor", props.get("rucEmisor").toString() );
		map.put("emisor", props.get("nombreEmisor").toString() );
		
		
		
		
		map.put("detalleLineas", lstDocumentoCab );
		
		
		
		return map;
		
	}

	public Map<String, Object> setCabecera(FacturaCab facturaCab) {
		Map<String, Object> map = new HashMap<>();
		// Numero de documento de emision
		String td = facturaCab.getTipoDocumento().getAbrDocumento().substring(0, 1).equals("F") ? "F" : "B";
		String documento = td + String.format("%03d", facturaCab.getSerie()) + "-"
				+ String.format("%08d", facturaCab.getNumero());
		map.put("numeroDeDocumentoEmitido", documento);

		// Fecha de emision
		String formatoMes = "MM";
		SimpleDateFormat dateFormat = new SimpleDateFormat(formatoMes);
		String Mes = dateFormat.format(facturaCab.getFecha());

		String formatoDia = "dd";
		dateFormat = new SimpleDateFormat(formatoDia);
		String Dia = dateFormat.format(facturaCab.getFecha());

		String formatoAnno = "YYYY";
		dateFormat = new SimpleDateFormat(formatoAnno);
		String Anno = dateFormat.format(facturaCab.getFecha());

		String fecha = Anno + "-" + Mes + "-" + Dia;
		map.put("fechaEmision", fecha);

		// Hora de emision
		map.put("horaEmision", "00:00:00");

		// Fecha de vencimiento
		map.put("fechaVencimiento", fecha);

		// Codigo de tipo de documento sunat 01 factura 02 boleta
		String codigoSunat = facturaCab.getTipoDocumento().getAbrDocumento().substring(0, 1).equals("F") ? "01" : "03";
		map.put("codigoDocumentoSunat", codigoSunat);

		// Monto en letras
		map.put("importeEnLetras", "DIEZ MIL QUINIENTOS Y 00/100");

		// Tipo de moneda
		map.put("tipoDeMoneda", "PEN");

		// Cantidad de items en la factura
		Integer numeoItems = facturaCab.getFacturaDetalles().size();
		map.put("numeroDeItems", numeoItems);

		// Nombre comercial del emisor
		String emisor = facturaCab.getEmisorRazonsocial();
		emisor = emisor.replace("&", "Y");
		map.put("emisor", emisor);

		// ruc del emisor
		String rucEmisor = facturaCab.getEmisorRuc();
		map.put("rucEmisor", rucEmisor);

		// Tipo documento cliente 6 si tiene ruc 0 sin ruc 1 dni
		String tipoDocumentoCliente = facturaCab.getCliente().getTipoDocumentoCliente();
		map.put("tipoDocumentoCliente", tipoDocumentoCliente);

		// Numero documento cliente

		if (tipoDocumentoCliente.equals("0")) {
			map.put("numeroDocumentoCliente", 0);
		}
		if (tipoDocumentoCliente.equals("6")) {
			map.put("numeroDocumentoCliente", facturaCab.getCliente().getNumeroDocumentoCliente());
		}
		if (tipoDocumentoCliente.equals("1")) {
			map.put("numeroDocumentoCliente", facturaCab.getCliente().getNumeroDocumentoCliente());
		}

		// razon social del cliente
		String razonSocial = facturaCab.getCliente().getRazonSocial().trim().replace("&", "Y");
		map.put("razonSocialCliente", razonSocial);

		// DATOS PARA LOS TOTALES Y EL DETALLE
		Map<String, Object> datos = this.getDetalle(facturaCab);

		// suma Total Valor Venta
		map.put("sumaValorVenta", datos.get("sumaValorVenta"));

		// suma Total sumaTotalDescuento
		map.put("sumaTotalDescuento", datos.get("sumaTotalDescuento"));

		float sumaValorVentaGrabada = (float) datos.get("sumaValorVentaGrabada");
		map.put("sumaValorVentaGrabada", sumaValorVentaGrabada);

		float sumaValorVentaExonerado = (float) datos.get("sumaValorVentaExonerado");
		map.put("sumaValorVentaExonerado", sumaValorVentaExonerado);

		map.put("sumaTotalDeImpuestos", datos.get("sumaTotalDeImpuestos"));

		float sumaImporteTotalVenta = (float) datos.get("sumaImporteTotalVenta");
		map.put("sumaImporteTotalVenta", sumaImporteTotalVenta);

		// Id tipo de afectacion E exonerado S afecto
		map.put("tipoDeAfectacion", "E");

		// si es afecto a IVG o EXONERADO
		map.put("afecto", "EXO");

		@SuppressWarnings("unchecked")
		List<CamposDetalle> lstCamposDetalle = (List<CamposDetalle>) datos.get("detalle");

		map.put("detalleLineas", lstCamposDetalle);

		return map;
	}

	public Map<String, Object> getDetalle(FacturaCab facturaCab) {

		List<CamposDetalle> lstCamposDetalle = new ArrayList<>();

		Map<String, Object> mapTotales = new HashMap<>();

		float sumaTotalDeImpuestos = 0;
		float sumaValorVenta = 0;
		float sumaTotalDescuento = 0;
		float sumaValorVentaGrabada = 0;
		float sumaValorVentaExonerado = 0;
		float sumaImporteTotalVenta = 0;
		float porcentajeIgv = 0;

		porcentajeIgv = facturaCab.getIgvPorcentaje();
		CamposDetalle camposDetalle;
		Integer cnt = 1;

		for (FacturaDetalle rowDetalle : facturaCab.getFacturaDetalles()) {
			camposDetalle = new CamposDetalle();
			camposDetalle.setItem(cnt);
			cnt++;
			camposDetalle.setCantidad(rowDetalle.getCantidad());
			camposDetalle.setPorcentajeIgv(facturaCab.getIgvPorcentaje());

			// precio unitario incluido el igv
			float precioUnitarioIncluidoIgv = rowDetalle.getPrecioUnitarioIncluidoIgv();
			camposDetalle.setPrecioUnitarioIncluidoIgv(precioUnitarioIncluidoIgv);

			// precio unitario sin igv
			float precioUnitarioSinIgv = 0;
			if (facturaCab.getIgvPorcentaje() == 0) {
				precioUnitarioSinIgv = rowDetalle.getPrecioUnitarioIncluidoIgv();
				camposDetalle.setPrecioUnitarioSinIgv(precioUnitarioSinIgv);

			} else {
				float precioUnitarioConIgv = rowDetalle.getPrecioUnitarioIncluidoIgv();
				precioUnitarioSinIgv = precioUnitarioConIgv / (1 + (facturaCab.getIgvPorcentaje() / 100));
				camposDetalle.setPrecioUnitarioSinIgv(precioUnitarioSinIgv);
			}

			camposDetalle.setBaseImponible(rowDetalle.getCantidad() * precioUnitarioSinIgv);

			// debe estar en formato 0.10 ejem 10.00%
			float porcentajeDescuento = 0;
			camposDetalle.setPorcentajeDescuento(porcentajeDescuento);

			// baseImponible cantidad * precioUnitarioSinIgv -> 2000 * 83.050847 = 166101.69
			float baseImponible = rowDetalle.getCantidad() * precioUnitarioSinIgv;
			camposDetalle.setBaseImponible(baseImponible);

			// ejemplo 10% igual a 0.10
			camposDetalle.setPorcentajeDescuento(porcentajeDescuento);

			// precioUnitarioSinIgv * porcentajeDescuento -> 83.050847 * 0.10 = 8.3050847
			float descuentoUnitario = precioUnitarioSinIgv * porcentajeDescuento;
			camposDetalle.setDescuentoUnitario(descuentoUnitario);

			// totalDescuento = cantidad * descuentoUnitario -> 2000 * 8.3050847 = 16,610.17
			float totalDescuento = rowDetalle.getCantidad() * descuentoUnitario;
			camposDetalle.setTotalDescuento(totalDescuento);

			// pude ser valorVentaGravado o Exonerado
			// valorVenta = baseImponible - totalDescuento -> 166,101.69 - 16,610.17 =
			// 149,491.52
			float valorVentaGrabado = 0;
			float valorVentaExonerado = 0;
			float valorVenta = baseImponible - totalDescuento;
			if (porcentajeIgv > 0) {
				valorVentaGrabado = baseImponible - totalDescuento;
				camposDetalle.setValorVentaGrabado(valorVentaGrabado);
			} else {
				valorVentaExonerado = baseImponible - totalDescuento;
				camposDetalle.setValorVentaExonerado(valorVentaExonerado);
			}

			// impuesto total = valorVenta * (porcentajeIgv / 100) -> 149,491.52 * 0.18 =
			// 26,908.47
			float impuestoTotal = 0;
			if (porcentajeIgv > 0) {
				impuestoTotal = valorVenta * (porcentajeIgv / 100);
			}

			camposDetalle.setImpuestoTotal(impuestoTotal);

			camposDetalle.setProducto(rowDetalle.getProducto().getDscProducto().trim().replace("&", "Y"));

			// 01 con igv 0 2oneroso cuando se regala
			camposDetalle.setTipoPrecioVenta("01");

			// Catalogo Nro 7 Sunat 10 gravado-oneroso y 20 exonerado -oneroso
			if (porcentajeIgv > 0) {
				camposDetalle.setTipoAfectacionIgv("10");

				// 1000 si tiene impuesto 9997 exonerado
				camposDetalle.setTipoDeTributo("1000");

			} else {
				camposDetalle.setTipoAfectacionIgv("20");

				// 1000 si tiene impuesto 9997 exonerado
				camposDetalle.setTipoDeTributo("9997");

			}

			// valor venta es el total menos impuesto y menos descuento
			sumaValorVenta = sumaValorVenta + valorVenta;
			sumaTotalDescuento = sumaTotalDescuento + totalDescuento;

			sumaValorVentaGrabada = sumaValorVentaGrabada + valorVentaGrabado;
			sumaValorVentaExonerado = sumaValorVentaExonerado + valorVentaExonerado;

			sumaTotalDeImpuestos = sumaTotalDeImpuestos + impuestoTotal;

			sumaImporteTotalVenta = sumaImporteTotalVenta + (valorVentaGrabado + valorVentaExonerado + impuestoTotal);

			lstCamposDetalle.add(camposDetalle);

		}

		mapTotales.put("sumaValorVenta", sumaValorVenta);
		mapTotales.put("sumaTotalDescuento", sumaTotalDescuento);
		mapTotales.put("sumaValorVentaGrabada", sumaValorVentaGrabada);
		mapTotales.put("sumaValorVentaExonerado", sumaValorVentaExonerado);
		mapTotales.put("sumaTotalDeImpuestos", sumaTotalDeImpuestos);
		mapTotales.put("sumaImporteTotalVenta", sumaImporteTotalVenta);

		mapTotales.put("detalle", lstCamposDetalle);
		return mapTotales;
	}

}
