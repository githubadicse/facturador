package com.adicse.facturador.model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the filepath database table.
 * 
 */
@Entity
@NamedQuery(name="Filepath.findAll", query="SELECT f FROM Filepath f")
public class Filepath implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private Integer idruta;

	@Column(name="archivos_qaliwarma")
	private String archivosQaliwarma;

	private String rutadoc;

	private String rutaimages;

	private String rutapdf;

	private String rutaxls;

	public Filepath() {
	}

	public Integer getIdruta() {
		return this.idruta;
	}

	public void setIdruta(Integer idruta) {
		this.idruta = idruta;
	}

	public String getArchivosQaliwarma() {
		return this.archivosQaliwarma;
	}

	public void setArchivosQaliwarma(String archivosQaliwarma) {
		this.archivosQaliwarma = archivosQaliwarma;
	}

	public String getRutadoc() {
		return this.rutadoc;
	}

	public void setRutadoc(String rutadoc) {
		this.rutadoc = rutadoc;
	}

	public String getRutaimages() {
		return this.rutaimages;
	}

	public void setRutaimages(String rutaimages) {
		this.rutaimages = rutaimages;
	}

	public String getRutapdf() {
		return this.rutapdf;
	}

	public void setRutapdf(String rutapdf) {
		this.rutapdf = rutapdf;
	}

	public String getRutaxls() {
		return this.rutaxls;
	}

	public void setRutaxls(String rutaxls) {
		this.rutaxls = rutaxls;
	}

}