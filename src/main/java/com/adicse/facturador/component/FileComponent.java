package com.adicse.facturador.component;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import java.util.TimeZone;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import com.adicse.facturador.dto.ControlProceso;
import com.adicse.facturador.modelToJson.FacturaCab;
import com.fasterxml.jackson.databind.ObjectMapper;

import freemarker.template.TemplateException;

@Component
public class FileComponent {

	@Autowired
	private ServiciosCPE serviciosCPE;

	@Autowired
	PropiedadesComponent propiedadesComponent;

	private static final Logger log = LoggerFactory.getLogger(FileComponent.class);

	private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");

	/**
	 * 
	 * Con este procedimiento se obtines los arhcivos json desde la ubicacion
	 * especificada en la ruta de configuracion, y se pasan al model este a su vez
	 * permite pasar a otro procedimiento para la generacion del archivo XML
	 * 
	 * Parametro : tipoProceso --> Puede ser FACTURA, BOLETA, NOTADECREDITO,
	 * RESUMENBOLETA
	 */

	public void setFileToModel(String tipoProceso) {

		if (ControlProceso.isProcesando()) {
			log.info("EL SISTEMA ESTA PROCESANDO INFORMACION ::: {}", dateFormat.format(new Date()));
			return;
		} else {
			log.info("EL SISTEMA INICIA PROCESO ::: {}", dateFormat.format(new Date()));
		}

		/*
		 * Obtenemos la propiedades del archivo config.properties para obtner las rutas
		 * de los archivos
		 */
		Properties prop = null;
		try {
			prop = propiedadesComponent.getPropValues();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		String _ruc = prop.getProperty("datos.ruc");

		// ruta donde se encuentran los archivos JSon
		String rutaFilesJson = prop.getProperty("datos." + _ruc + ".rutaArchivoJson");

		String rutaArchivoXml = prop.getProperty("datos." + _ruc + ".rutaArchivoXml");
		String rutaArchivoFtl = prop.getProperty("datos." + _ruc + ".rutaArchivoFtl");
		String nombreArchivoFtl = prop.getProperty("datos." + _ruc + ".nombreArchivoFtl");
		String rutaCertificado = prop.getProperty("datos." + _ruc + ".rutaCertificado");
		String nombreArchivoCertificado = prop.getProperty("datos." + _ruc + ".nombreArchivoCertificado");
		String passFirma = prop.getProperty("datos." + _ruc + ".passFirma");
		String UsuSol = prop.getProperty("datos." + _ruc + ".UsuSol");
		String PassSol = prop.getProperty("datos." + _ruc + ".PassSol");

		log.info("rutaFilesJson {}", rutaFilesJson + " Fecha/Hora" + dateFormat.format(new Date()));
		log.info("rutaArchivoXml {}", rutaArchivoXml + " Fecha/Hora" + dateFormat.format(new Date()));
		log.info("rutaArchivoFtl {}", rutaArchivoFtl + " Fecha/Hora" + dateFormat.format(new Date()));
		log.info("nombreArchivoFtl {}", nombreArchivoFtl + " Fecha/Hora" + dateFormat.format(new Date()));

		/* Obtenemos los archivos de la carpeta fileJSON, para su proceso */
		List<String> filesJson = this.readFolderFiles(rutaFilesJson);

		ObjectMapper mapper = new ObjectMapper();
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		df.setTimeZone(TimeZone.getTimeZone("America/Lima"));
		mapper.setDateFormat(df);

		ControlProceso.setProcesando(true);

		// RECORREMOS TODOS LOS ARCHIVO DE LA CARPETA JSON
		List<FacturaCab> lstFacturaCab = new ArrayList<>();
		for (String rowNombreArchivoJson : filesJson) {

			String archivo = rutaFilesJson + "\\" + rowNombreArchivoJson;

			// this.validUtf8(archivo);
			// System.out.println(archivo);

			// PROCESO FACTURA
			FacturaCab facturaCab = null;

			switch (tipoProceso) {
			case "FACTURA":
				for (String rowNombreArchivoJsonFactura : filesJson) {
					archivo = rutaFilesJson + "\\" + rowNombreArchivoJsonFactura;
					try {

						log.info("procesando {}", archivo + " Fecha/Hora" + dateFormat.format(new Date()));
						facturaCab = new FacturaCab();
						facturaCab = mapper.readValue(new File(archivo), FacturaCab.class);

						// filtramos solo los documento que inicion con F factura.
						if (facturaCab.getTipoDocumento().getAbrDocumento().substring(0, 1).equals("F")) {

							// Creamos el archivo XML

							// log.info("Archivo JSON a procesar : {}", archivo + " Fecha/Hora" +
							// dateFormat.format(new Date()));
							try {

								// CREAMOS FIRMAMOS Y ENVIAMOS LOS ARCHIVOS A LA SUNAT TIPO DE DOCUMENTOS
								// FACTURAS
								// RUTA AMPLIAMOS
								String rutaArchivoXmlFactura = rutaArchivoXml + "Factura";
								String rutaArchivoXmlRespuesta = rutaArchivoXml + "Respuesta";

								serviciosCPE.crearXMLCPE21(facturaCab, rutaArchivoXmlFactura, rutaArchivoFtl,
										nombreArchivoFtl, rutaCertificado, nombreArchivoCertificado, passFirma, UsuSol,
										PassSol, rutaArchivoXmlRespuesta);
							} catch (TemplateException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}

						}

					} catch (IOException e) {
						e.getMessage();
					}

				}
				break;

			case "BOLETA":
				for (String rowNombreArchivoJsonBoleta : filesJson) {
					archivo = rutaFilesJson + "\\" + rowNombreArchivoJsonBoleta;
					try {

						log.info("procesando {}", archivo + " Fecha/Hora" + dateFormat.format(new Date()));
						facturaCab = new FacturaCab();
						facturaCab = mapper.readValue(new File(archivo), FacturaCab.class);

						// filtramos solo los documento que inicion con F factura.
						if (facturaCab.getTipoDocumento().getAbrDocumento().substring(0, 1).equals("B")) {

							// Creamos el archivo XML

							// log.info("Archivo JSON a procesar : {}", archivo + " Fecha/Hora" +
							// dateFormat.format(new Date()));
							try {

								// CREAMOS FIRMAMOS Y ENVIAMOS LOS ARCHIVOS A LA SUNAT TIPO DE DOCUMENTOS
								// FACTURAS
								// RUTA AMPLIAMOS
								String rutaArchivoXmlBoleta = rutaArchivoXml + "Boleta";
								String rutaArchivoXmlRespuesta = rutaArchivoXml + "Respuesta";

								serviciosCPE.crearXMLCPE21(facturaCab, rutaArchivoXmlBoleta, rutaArchivoFtl,
										nombreArchivoFtl, rutaCertificado, nombreArchivoCertificado, passFirma, UsuSol,
										PassSol, rutaArchivoXmlRespuesta);
							} catch (TemplateException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}

						}
					} catch (IOException e) {
						e.getMessage();
					}
				}

				break;

			case "NOTADECREDITO":

				for (String rowNombreArchivoJsonNotaDeCredito : filesJson) {
					archivo = rutaFilesJson + "\\" + rowNombreArchivoJsonNotaDeCredito;
					try {

						log.info("procesando {}", archivo + " Fecha/Hora" + dateFormat.format(new Date()));
						facturaCab = new FacturaCab();
						facturaCab = mapper.readValue(new File(archivo), FacturaCab.class);

						// filtramos solo los documento que inicion con F factura.
						if (facturaCab.getTipoDocumento().getAbrDocumento().substring(0, 1).equals("N")) {

							// Creamos el archivo XML

							// log.info("Archivo JSON a procesar : {}", archivo + " Fecha/Hora" +
							// dateFormat.format(new Date()));
							try {

								// CREAMOS FIRMAMOS Y ENVIAMOS LOS ARCHIVOS A LA SUNAT TIPO DE DOCUMENTOS
								// FACTURAS
								// RUTA AMPLIAMOS
								String rutaArchivoXmlNotaDeCredito = rutaArchivoXml + "NotaDeCredito";
								String rutaArchivoXmlRespuesta = rutaArchivoXml + "Respuesta";

								serviciosCPE.crearXMLCPE21(facturaCab, rutaArchivoXmlNotaDeCredito, rutaArchivoFtl,
										nombreArchivoFtl, rutaCertificado, nombreArchivoCertificado, passFirma, UsuSol,
										PassSol, rutaArchivoXmlRespuesta);
							} catch (TemplateException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}

						}
					} catch (IOException e) {
						e.getMessage();
					}
				}

				break;

			case "RESUMENBOLETA":
				for (String rowNombreArchivoJsonResumenBoleta : filesJson) {
					archivo = rutaFilesJson + "\\" + rowNombreArchivoJsonResumenBoleta;

					try {

						log.info("procesando {}", archivo + " Fecha/Hora" + dateFormat.format(new Date()));
						facturaCab = new FacturaCab();
						facturaCab = mapper.readValue(new File(archivo), FacturaCab.class);

						// filtramos solo los documento que inicion con F factura.
						if (facturaCab.getTipoDocumento().getAbrDocumento().substring(0, 1).equals("B")) {

							// Creamos el archivo XML

							// log.info("Archivo JSON a procesar : {}", archivo + " Fecha/Hora" +
							// dateFormat.format(new Date()));
							try {

								// CREAMOS FIRMAMOS Y ENVIAMOS LOS ARCHIVOS A LA SUNAT TIPO DE DOCUMENTOS
								// FACTURAS
								// RUTA AMPLIAMOS
								String rutaArchivoXmlBoleta = rutaArchivoXml + "Boleta";
								String rutaArchivoXmlRespuesta = rutaArchivoXml + "Respuesta";

								serviciosCPE.crearXMLCPE21(facturaCab, rutaArchivoXmlBoleta, rutaArchivoFtl,
										nombreArchivoFtl, rutaCertificado, nombreArchivoCertificado, passFirma, UsuSol,
										PassSol, rutaArchivoXmlRespuesta);
							} catch (TemplateException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}

						}
					} catch (IOException e) {
						e.getMessage();
					}

				}

				break;

			default:
				break;
			}

		}

		ControlProceso.setProcesando(false);

	}

	public List<String> readFolderFiles(String folder) {

		List<String> lstFile = new ArrayList<>();
		File folderFile = new File(folder);

		if ((folderFile.exists())) {
			File[] files = folderFile.listFiles();
			for (File file : files) {
				// boolean isFolder = file.isDirectory();
				// System.out.println((isFolder ? "FOLDER: " : " FILE: ") + file.getName());
				lstFile.add(file.getName());
			}
		}

		return lstFile;
	}

	@SuppressWarnings("unused")
	public void validUtf8(String archivo) {

		File archivoLectura = new File(archivo);
		FileReader leerArchivo = null;
		BufferedReader memoriaParaLecturaAux = null;
		try {
			leerArchivo = new FileReader(archivoLectura);
			// memoriaParaLecturaAux = new BufferedReader(leerArchivo);
			memoriaParaLecturaAux = new BufferedReader(new InputStreamReader(new FileInputStream(archivo), "UTF-8"));

		} catch (FileNotFoundException | UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		String textoLinea = null;
		try {
			while ((textoLinea = memoriaParaLecturaAux.readLine()) != null) {
				String UTF8Str = new String(textoLinea.getBytes(), "UTF-8");
				System.out.println(UTF8Str);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
