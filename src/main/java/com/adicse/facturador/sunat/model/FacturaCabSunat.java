package com.adicse.facturador.sunat.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.*;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="factura_cab")
public class FacturaCabSunat implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="id_factura_cab")
	private Long idFacturaCab;
	
	@Column(name="fecha_emision")
	private Date fechaEmision;	

	@Column(name="fecha_vencimiento")
	private Date fechaVencimiento;
	
	
	
	
	

	public FacturaCabSunat() {
	}

	public Long getIdFacturaCab() {
		return idFacturaCab;
	}

	public void setIdFacturaCab(Long idFacturaCab) {
		this.idFacturaCab = idFacturaCab;
	}

	public Date getFechaEmision() {
		return fechaEmision;
	}

	public void setFechaEmision(Date fechaEmision) {
		this.fechaEmision = fechaEmision;
	}

	public Date getFechaVencimiento() {
		return fechaVencimiento;
	}

	public void setFechaVencimiento(Date fechaVencimiento) {
		this.fechaVencimiento = fechaVencimiento;
	}		
	
	
	
	
	
}
