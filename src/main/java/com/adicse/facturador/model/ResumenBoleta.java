package com.adicse.facturador.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the resumen_boleta database table.
 * 
 */
@Entity
@Table(name="resumen_boleta")
@NamedQuery(name="ResumenBoleta.findAll", query="SELECT r FROM ResumenBoleta r")
public class ResumenBoleta implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="id_resumen_boleta")
	private String idResumenBoleta;

	@Temporal(TemporalType.DATE)
	@Column(name="fecha_generacion")
	private Date fechaGeneracion;

	//bi-directional many-to-one association to DocumentoCab
	@OneToMany(mappedBy="resumenBoleta")
	private List<DocumentoCab> documentoCabs;

	public ResumenBoleta() {
	}

	public String getIdResumenBoleta() {
		return this.idResumenBoleta;
	}

	public void setIdResumenBoleta(String idResumenBoleta) {
		this.idResumenBoleta = idResumenBoleta;
	}

	public Date getFechaGeneracion() {
		return this.fechaGeneracion;
	}

	public void setFechaGeneracion(Date fechaGeneracion) {
		this.fechaGeneracion = fechaGeneracion;
	}

	public List<DocumentoCab> getDocumentoCabs() {
		return this.documentoCabs;
	}

	public void setDocumentoCabs(List<DocumentoCab> documentoCabs) {
		this.documentoCabs = documentoCabs;
	}

	public DocumentoCab addDocumentoCab(DocumentoCab documentoCab) {
		getDocumentoCabs().add(documentoCab);
		documentoCab.setResumenBoleta(this);

		return documentoCab;
	}

	public DocumentoCab removeDocumentoCab(DocumentoCab documentoCab) {
		getDocumentoCabs().remove(documentoCab);
		documentoCab.setResumenBoleta(null);

		return documentoCab;
	}

}