package com.adicse.facturador.modelToJson;

import java.io.Serializable;
import java.util.List;

public class TipoDocumento  implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Integer idDocumento;
	
	private String abrDocumento;
	
	
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
