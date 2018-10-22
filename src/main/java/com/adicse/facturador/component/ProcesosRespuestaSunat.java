package com.adicse.facturador.component;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathFactory;

import org.springframework.stereotype.Component;
import org.w3c.dom.Document;
import org.w3c.dom.Node;

@Component
public class ProcesosRespuestaSunat {

	public Integer verificarXmlRespuestaSunat(String archivoRespuestaXml, String rutaArchivoRespuestaXml) {

		Integer valorRetorno=null; 
		//String archivoXml = "D:\\proyectos\\generador archivos para facturador\\empresas\\20209098114\\fileXmlRespuesta\\R-20209098114-01-F003-00073618.XML";

		String archivoXml = rutaArchivoRespuestaXml + "/" + archivoRespuestaXml;
	    DocumentBuilderFactory domFactory = DocumentBuilderFactory.newInstance();
        try {
            DocumentBuilder builder = domFactory.newDocumentBuilder();
            
            Document dDoc = builder.parse(archivoXml);

            XPath xPath = XPathFactory.newInstance().newXPath();
            Node node = (Node) xPath.evaluate("/ApplicationResponse/DocumentResponse/Response/ResponseCode", dDoc, XPathConstants.NODE);
            valorRetorno = Integer.parseInt(node.getTextContent());
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        return valorRetorno;

	}
	
	
	
	public String desempaquetarArchivoZip(String directorioZip, String archivoZip) {
		String nombreArchivo = null;
		try {
			//crea un buffer temporal para el archivo que se va descomprimir
			ZipInputStream zis = new ZipInputStream(new FileInputStream(directorioZip + "/"+archivoZip));

			ZipEntry salida;
			//recorre todo el buffer extrayendo uno a uno cada archivo.zip y creándolos de nuevo en su archivo original 
			while (null != (salida = zis.getNextEntry())) {
				System.out.println("Nombre del Archivo: "+salida.getName());
				nombreArchivo = salida.getName();
					FileOutputStream fos = new FileOutputStream(directorioZip + "/" +salida.getName());
					int leer;
					byte[] buffer = new byte[1024];
					while (0 < (leer = zis.read(buffer))) {
						fos.write(buffer, 0, leer);
					}
					fos.close();
					zis.closeEntry();
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}catch(IOException e){
			e.printStackTrace();
		}	
		System.out.println("Nombre de archivo desempaquetado");
		return nombreArchivo;
	}

}
