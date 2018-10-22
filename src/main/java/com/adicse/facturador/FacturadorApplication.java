package com.adicse.facturador;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;

import com.adicse.facturador.component.FileComponent;
import com.adicse.facturador.dto.ControlProceso;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.hibernate5.Hibernate5Module;
import com.fasterxml.jackson.datatype.hibernate5.Hibernate5Module.Feature;

/**
 * Url - Informacion para trabajar con fechas a partir de java 8
 * https://www.baeldung.com/java-8-date-time-intro  

**/


@SpringBootApplication
public class FacturadorApplication implements CommandLineRunner {

	@Bean
	@Primary
	public ObjectMapper jsonMapper() {
	
		
		DateFormat df = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		//TimeZone.setDefault(TimeZone.getTimeZone("UTC"));
		//TimeZone.setDefault(TimeZone.getTimeZone("GMT"));
		
	    ObjectMapper mapper = new ObjectMapper();
	    Hibernate5Module hm = new Hibernate5Module();
	    //hm.configure(Hibernate5Module.Feature.FORCE_LAZY_LOADING, false);
	    hm.enable(Feature.SERIALIZE_IDENTIFIER_FOR_LAZY_NOT_LOADED_OBJECTS);
	    
	    mapper.registerModule(hm);
	    mapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
	    
	    // This doesn't work with Hibernate4Module :-(
	    //mapper.setSerializationInclusion(Include.NON_NULL);
	    mapper.setSerializationInclusion(Include.NON_EMPTY);
	    //df.setTimeZone(TimeZone.getTimeZone("UTC"));
	    mapper.setDateFormat(df);
	   
	    return mapper;
	}	
	
	
	@Autowired
	FileComponent fileComponent; 
	
	
	public static void main(String[] args) {
		SpringApplication.run(FacturadorApplication.class, args);
		
		ControlProceso cp = new ControlProceso();
		cp.setProcesando(false);
	}


	@Override
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub
		
		//recoge los archivos json para convertilos a xml ubl21 
		fileComponent.setFileToModel("FACTURA");
		
		
//		String archivoXml = "D:\\proyectos\\generador archivos para facturador\\empresas\\20209098114\\fileXmlRespuesta\\R-20209098114-01-F003-00073618.XML";
//		verificaXmlRespuestaSunat.verificarXmlRespuestaSunat(archivoXml);
		

//		String directorioZip = "D:\\proyectos\\generador archivos para facturador\\empresas\\20209098114\\fileXmlRespuesta";
//		String archivoZip = "R-20209098114-01-F003-00073618.ZIP";
		//verificaXmlRespuestaSunat.desempaquetarArchivoZip(directorioZip, archivoZip);
		
		
	}
	
	
}
