package com.adicse.facturador.wintac.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="docum")
public class TipoDocumento  implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="coddoc")
	private Integer idDocumento;
	
	@Column(name="abrdoc")
	private String abrDocumento;
	
	
	//bi-directional many-to-one association to facturas
	@OneToMany(mappedBy="tipoDocumento")
	private List<FacturaCab> facturaCabs;
	
	

	public Integer getIdDocumento() {
		return idDocumento;
	}

	public void setIdDocumento(Integer idDocumento) {
		this.idDocumento = idDocumento;
	}

	public String getAbrDocumento() {
		return abrDocumento;
	}

	public void setAbrDocumento(String abrDocumento) {
		this.abrDocumento = abrDocumento;
	}

	public TipoDocumento() {
	}

	public List<FacturaCab> getFacturaCabs() {
		return facturaCabs;
	}

	public void setFacturaCabs(List<FacturaCab> facturaCabs) {
		this.facturaCabs = facturaCabs;
	}

	
	
	
	
	
}
