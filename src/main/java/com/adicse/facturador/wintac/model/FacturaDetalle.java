package com.adicse.facturador.wintac.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="ing002")
public class FacturaDetalle implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="idmov")
	private String idFactuaDetalle;
	
	
	//bi-directional many-to-one association to producto
	@ManyToOne
	@JoinColumn(name="codpro")
	private Producto producto;
	
	@Column(name="cantidad")
	private Integer cantidad;
	
	@Column(name="preunt")
	private float precioUnitario;
	
	
	// expresado en ejemplo: 10% - 0.10
	//private float porcentajeDescuento;
	
	
	public Integer getCantidad() {
		return cantidad;
	}

	public void setCantidad(Integer cantidad) {
		this.cantidad = cantidad;
	}

	public float getPrecioUnitario() {
		return precioUnitario;
	}

	public void setPrecioUnitario(float precioUnitario) {
		this.precioUnitario = precioUnitario;
	}

	//bi-directional many-to-one association to factua ca
	@ManyToOne
	@JoinColumn(name="id")
	private FacturaCab facturaCab;	
	

	
	public FacturaCab getFacturaCab() {
		return facturaCab;
	}

	public void setFacturaCab(FacturaCab facturaCab) {
		this.facturaCab = facturaCab;
	}

	public String getIdFactuaDetalle() {
		return idFactuaDetalle;
	}

	public void setIdFactuaDetalle(String idFactuaDetalle) {
		this.idFactuaDetalle = idFactuaDetalle;
	}

	public Producto getProducto() {
		return producto;
	}

	public void setProducto(Producto producto) {
		this.producto = producto;
	}

	public FacturaDetalle() {
	}


	
	
	
	
	

}
