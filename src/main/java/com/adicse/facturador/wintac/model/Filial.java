package com.adicse.facturador.wintac.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="filial")
public class Filial {
	
	@Id
	@Column(name="idFilial")
	private Integer idFilial;
	
	@Column(name="razonsocial")
	private String razonSocial;
	
	@Column(name="ruc")
	private String ruc;
	
	@Column(name="direccion")
	private String Direccion;
	
	
	public Integer getIdFilial() {
		return idFilial;
	}

	public void setIdFilial(Integer idFilial) {
		this.idFilial = idFilial;
	}

	public String getRazonSocial() {
		return razonSocial;
	}

	public void setRazonSocial(String razonSocial) {
		this.razonSocial = razonSocial;
	}

	public String getRuc() {
		return ruc;
	}

	public void setRuc(String ruc) {
		this.ruc = ruc;
	}

	public String getDireccion() {
		return Direccion;
	}

	public void setDireccion(String direccion) {
		Direccion = direccion;
	}
	
	
	
	

}
