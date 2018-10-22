package com.adicse.facturador.modelToJson;

import java.io.Serializable;
import java.util.List;


public class Cliente implements Serializable {
	

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer idCliente;
	
	private String razonSocial;
	
	private String direccion;

	private String numeroDocumentoCliente;
	
	private String tipoDocumentoCliente;
	
	

	private List<FacturaCab> facturaCabs;
	
	
	public Integer getIdCliente() {
		return idCliente;
	}

	public void setIdCliente(Integer idCliente) {
		this.idCliente = idCliente;
	}

	public String getRazonSocial() {
		return razonSocial;
	}

	public void setRazonSocial(String razonSocial) {
		this.razonSocial = razonSocial;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public Cliente() {
	}



	public List<FacturaCab> getFacturaCabs() {
		return facturaCabs;
	}

	public void setFacturaCabs(List<FacturaCab> facturaCabs) {
		this.facturaCabs = facturaCabs;
	}

	public String getNumeroDocumentoCliente() {
		return numeroDocumentoCliente;
	}

	public void setNumeroDocumentoCliente(String numeroDocumentoCliente) {
		this.numeroDocumentoCliente = numeroDocumentoCliente;
	}

	public String getTipoDocumentoCliente() {
		return tipoDocumentoCliente;
	}

	public void setTipoDocumentoCliente(String tipoDocumentoCliente) {
		this.tipoDocumentoCliente = tipoDocumentoCliente;
	}

	
	
	
	
	
	
	

}
