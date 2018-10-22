package com.adicse.facturador.model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the control_archivos database table.
 * 
 */
@Entity
@Table(name="control_archivos")
@NamedQuery(name="ControlArchivo.findAll", query="SELECT c FROM ControlArchivo c")
public class ControlArchivo implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="id_control_archivo")
	private String idControlArchivo;

	@Column(name="nombre_archivo_xml")
	private String nombreArchivoXml;

	private String situacion;

	public ControlArchivo() {
	}

	public String getIdControlArchivo() {
		return this.idControlArchivo;
	}

	public void setIdControlArchivo(String idControlArchivo) {
		this.idControlArchivo = idControlArchivo;
	}

	public String getNombreArchivoXml() {
		return this.nombreArchivoXml;
	}

	public void setNombreArchivoXml(String nombreArchivoXml) {
		this.nombreArchivoXml = nombreArchivoXml;
	}

	public String getSituacion() {
		return this.situacion;
	}

	public void setSituacion(String situacion) {
		this.situacion = situacion;
	}

}