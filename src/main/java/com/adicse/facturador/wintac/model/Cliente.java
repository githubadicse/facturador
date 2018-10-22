package com.adicse.facturador.wintac.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;


@Entity
@Table(name="cli_pro")
public class Cliente implements Serializable {
	

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@Column(name="codigo")
	private Integer idCliente;
	
	
	
	@Column(name="razonsocial")
	private String razonSocial;
	
	@Column(name="direccion1")
	private String direccion;

	@Column(name="ruc")
	private String ruc;

	
	
	//bi-directional many-to-one association to facturas
	@OneToMany(mappedBy="cliente")
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

	public String getRuc() {
		return ruc;
	}

	public void setRuc(String ruc) {
		this.ruc = ruc;
	}

	public List<FacturaCab> getFacturaCabs() {
		return facturaCabs;
	}

	public void setFacturaCabs(List<FacturaCab> facturaCabs) {
		this.facturaCabs = facturaCabs;
	}
	
	
	
	
	
	
	

}
