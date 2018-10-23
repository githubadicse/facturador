package com.adicse.facturador.component;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.springframework.stereotype.Component;


@Component
public class PropiedadesComponent {
	
	
	
	public Properties getPropValues() throws IOException  {
		InputStream inputStream = null;
		Properties prop = new Properties();
		try {
			
			String propFileName = "config.properties";
 
			inputStream = getClass().getClassLoader().getResourceAsStream(propFileName);
 
			if (inputStream != null) {
				prop.load(inputStream);
			} else {
				throw new FileNotFoundException("property file '" + propFileName + "' not found in the classpath");
			}
 
 
		} catch (Exception e) {
			System.out.println("Exception: " + e);
		} finally {
			inputStream.close();
		}
		return prop;
	}	
	
	public Map<String,Object> getValoresDePropiedades(){
		Map<String,Object> map = new HashMap<>();
		
		Properties prop = null;
		try {
			prop = this.getPropValues();
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
		
		map.put("rutaFilesJson", rutaFilesJson);
		map.put("rutaArchivoXml", rutaArchivoXml);
		map.put("rutaArchivoFtl", rutaArchivoFtl);
		map.put("nombreArchivoFtl", nombreArchivoFtl);
		map.put("rutaCertificado", rutaCertificado);
		map.put("nombreArchivoCertificado", nombreArchivoCertificado);
		map.put("passFirma", passFirma);
		
		map.put("UsuSol", UsuSol);
		map.put("PassSol", PassSol);		
		
		return map;
	}

}
