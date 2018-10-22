package com.adicse.facturador.utilitarios;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;



public class AgregarJsonFormatDateJSon {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		
		String[][] entitys = {				
				{"DocumentoCab"}
		};
		
		
		String ruta = "D:\\proyectos\\facturador wintac\\facturador\\src\\main\\java\\com\\adicse\\facturador\\model\\";
		//String ruta = "/home/ubuntu/proyectos/spring boot/comercial/src/main/java/com/adicse/comercial/model/";

		String archivoFuente = null;

	
		
		for(int i=0; i < entitys.length ; i++){
			archivoFuente = ruta + entitys[i][0] + ".java";

	
			List<TextoLine> list = createListLineasArchivo(archivoFuente);
			list = createListLineasArchivoActualizarImport(list,"import com.fasterxml.jackson.annotation.JsonFormat");
			list = createListLineasArchivoAgregaFormato(list, "@Temporal(TemporalType.DATE)", "A");
			EscribeArchivo(list,archivoFuente);
		}
		System.out.println("Fin procedimiento agregar formato TimeStamp ...");
		return ;		

	}
	
	
	public static List<TextoLine> createListLineasArchivoActualizarImport(List<TextoLine> list, String importString) {
		
		//veirificamos si esta importado 
		boolean existe = false;
		for(TextoLine row:list) {
			
			if( row.getTexto().trim().toLowerCase().contains(importString.toLowerCase() )) {
				existe = true;
			}
			
		}
		
		//si no existe lo agregamos en los imports;
		if(!existe) {
			Boolean registrado = false;
			Integer nroLinea = 0;
			for (int i = 0; i < list.size(); i++) {
				nroLinea++;

				if(  ((TextoLine)list.get(i)).getTexto().trim().length() == 0 && i > 2 && !registrado) {
					TextoLine textoLine = new TextoLine();
					textoLine.setNrolinea(nroLinea);
					textoLine.setTexto(importString+ ";");
					list.add(nroLinea,textoLine);
					
					//agregamos una linea en blanco
					nroLinea++;
					i++;
					textoLine = new TextoLine();
					textoLine.setNrolinea(nroLinea);
					textoLine.setTexto("");
					list.add(nroLinea,textoLine);	
					i++;
					registrado = true;
				}else {
					((TextoLine)list.get(i)).setNrolinea(nroLinea);
				}
			}
		}
		System.out.println("Fin Actualizacion de la lista ...........");
		return list;
		
		

		
	}
	
	public static List<TextoLine> createListLineasArchivoAgregaFormato(List<TextoLine> list, String cadenaABuscar, String InsertarAntesDespues) {
		
		// 
		//cadena a insertar
		String textoLinea = "\t@JsonFormat (pattern =\"dd/MM/yyyy\")";
		String textoContains = "@JsonFormat (pattern =\"dd/MM/yyyy\")";
		Integer nroLinea = 0;
		for (int i = 0; i < list.size(); i++) {
			nroLinea++;
			if( ((TextoLine)list.get(i)).getTexto().trim().contains(cadenaABuscar)) {
				
				if(InsertarAntesDespues.equals("A")) {
					
					if( ! ((TextoLine)list.get(i - 1)).getTexto().trim().contains(textoContains))
					{
						TextoLine textoLine = new TextoLine();
						textoLine.setNrolinea(nroLinea - 1);
						textoLine.setTexto(textoLinea);
						list.add(nroLinea -1,textoLine);
						

						
						i++;
						nroLinea++;
						((TextoLine)list.get(i)).setNrolinea(nroLinea);						
					}
					

					
				}

			}else {
				((TextoLine)list.get(i)).setNrolinea(nroLinea);
				
				
			}
			
		}

		System.out.println("Fin Actualizacion formato ...........");
		return list;
		
	}	
	

	public static void EscribeArchivo(List<TextoLine> lst,String archivoDestination) throws IOException {
		File archivoWrite = new File(archivoDestination);
		BufferedWriter bw = null;
		
		try {
			
			bw = new BufferedWriter(new FileWriter(archivoWrite));
			bw.write("");
			
		} catch (IOException e) {
			e.printStackTrace();
		}		
		
		for(TextoLine row:lst) {
			bw.append(row.getTexto().toString() );
			bw.newLine();							
		}
		
		bw.close();
		
		
		
	}
	
	
	public static List<TextoLine> createListLineasArchivo(String archivoRead) throws IOException{
		
		File archivoLectura = new File(archivoRead);
		FileReader leerArchivo = null;		
		
		leerArchivo = new FileReader(archivoLectura);
		
		@SuppressWarnings("resource")
		BufferedReader memoriaParaLecturaAux = new BufferedReader(leerArchivo);
		String textoLinea = null;

		TextoLine textoLine;
		Integer cntLine = 1;
		List<TextoLine> lstLines = new ArrayList<>();
		while ( (textoLinea = memoriaParaLecturaAux.readLine() ) != null  ) {
			cntLine++;
			textoLine = new TextoLine();
			textoLine.setNrolinea(cntLine);
			textoLine.setTexto(textoLinea);
			lstLines.add(textoLine);				

		}
		
		return lstLines;
			
		
	}


}



