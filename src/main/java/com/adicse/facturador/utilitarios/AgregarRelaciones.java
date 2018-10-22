package com.adicse.facturador.utilitarios;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class AgregarRelaciones {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub

		String[][] entitys = {
				{ "DocumentoCab", "@OneToMany(mappedBy=\"documentoCab\")", "private List<DocumentoDetalle> documentoDetalles;",
						"\t@OneToMany(mappedBy=\"documentoCab\", cascade={CascadeType.ALL})" }

		};
		
		//String ruta = "/home/ubuntu/proyectos/spring boot/comercial/src/main/java/com/adicse/comercial/model/";

		String ruta = "D:\\proyectos\\facturador wintac\\facturador\\src\\main\\java\\com\\adicse\\facturador\\model\\";
		String archivoFuente = null;
		String archivoTemporal = null;
		String textoBuscar1 = null;
		String textoBuscar2 = null;
		String textoReemplazar = null;

		for (int i = 0; i < entitys.length; i++) {
			archivoFuente = ruta + entitys[i][0] + ".java";
			archivoTemporal = ruta + entitys[i][0] + ".txt";
			textoBuscar1 = entitys[i][1];
			textoBuscar2 = entitys[i][2];
			textoReemplazar = entitys[i][3];

			CrearArchivoTemporal(archivoFuente, archivoTemporal, textoBuscar1, textoBuscar2, textoReemplazar);
			CrearArchivoFuente(archivoTemporal, archivoFuente);
		}
		System.out.println("Fin procedimiento relaciones ...");
		return;

	}

	public static void CrearArchivoTemporal(String archivoRead, String archivoDestination, String textoBuscar1,
			String textoBuscar2, String textoReemplazar) throws IOException {
		File archivoWrite = new File(archivoDestination);
		File archivoLectura = new File(archivoRead);
		FileReader leerArchivo = null;
		leerArchivo = new FileReader(archivoLectura);

		BufferedWriter bw = null;
		if (archivoLectura.exists()) {
			// El fichero ya existe

			BufferedReader memoriaParaLectura = new BufferedReader(leerArchivo);
			try {

				bw = new BufferedWriter(new FileWriter(archivoWrite));
				bw.write("");

			} catch (IOException e) {
				e.printStackTrace();
			}

			String textoLinea = null;

			Integer cntLine = 1;
			List<TextoLine> lstLines = new ArrayList<TextoLine>();
			while ((textoLinea = memoriaParaLectura.readLine()) != null) {
				TextoLine textoLine = new TextoLine();
				textoLine.setNrolinea(cntLine);
				textoLine.setTexto(textoLinea);
				lstLines.add(textoLine);
				cntLine++;
			}
			String rowTexto = "", rowTexto2 = "";

			for (int i = 0; i < lstLines.size(); i++) {
				TextoLine textoLine = new TextoLine();
				textoLine = (TextoLine) lstLines.get(i);
				rowTexto = textoLine.getTexto();
				rowTexto = rowTexto.replace("\t", "").trim();
				if (rowTexto.equals(textoBuscar1)) {

					// buscamos la siguiente que sela la relacion con la lista.
					rowTexto2 = ((TextoLine) lstLines.get(i + 1)).getTexto();
					rowTexto2 = rowTexto2.replace("\t", "").trim();
					if (rowTexto2.equals(textoBuscar2)) {
						((TextoLine) lstLines.get(i)).setTexto(textoReemplazar);
					}
				}

			}
			for (TextoLine row : lstLines) {
				bw.write(row.getTexto());
				bw.newLine();
			}

			bw.close();
			memoriaParaLectura.close();

		} else {
			// El fichero no existe y hay que crearlo
			System.out.println("El archivo no existe...");
		}
		// archivoLectura.delete();
	}

	public static void CrearArchivoFuente(String archivoRead, String archivoDestination) throws IOException {
		// import org.hibernate.annotations.Type
		File archivoWrite = new File(archivoDestination);
		File archivoLectura = new File(archivoRead);
		FileReader leerArchivo = null;

		leerArchivo = new FileReader(archivoLectura);

		Integer nroLinea = 0;

		BufferedWriter bw = null;

		if (archivoLectura.exists()) {

			BufferedReader memoriaParaLectura = new BufferedReader(leerArchivo);
			try {

				bw = new BufferedWriter(new FileWriter(archivoWrite));
				bw.write("");

			} catch (IOException e) {
				e.printStackTrace();
			}
			String textoLinea = null;
			while ((textoLinea = memoriaParaLectura.readLine()) != null) {
				nroLinea++;
				bw.append(textoLinea);
				bw.newLine();

			}
			bw.close();
			memoriaParaLectura.close();

		} else {
			// El fichero no existe y hay que crearlo
			System.out.println("El archivo no existe...");
		}
		archivoLectura.delete();
	}

	public static int BuscarPalabra(String texto, String palabraBuscada) {
		Integer posicionInicial = 0;
		Integer longitud = 0;
		Integer cantidadPalabra = 0;

		for (int i = 0; i < texto.length(); i++) {
			longitud++;
			// if (cadenaPorCaracter[i] == ' ' || i == texto.length() - 1)
			if (i == texto.length() - 1) {
				if (texto.substring(posicionInicial, longitud).trim().toUpperCase()
						.equals(palabraBuscada.trim().toUpperCase())) {
					cantidadPalabra++;
				}
				posicionInicial = i;
			}
		}

		return cantidadPalabra;
	}

}
