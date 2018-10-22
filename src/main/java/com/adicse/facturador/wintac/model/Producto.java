package com.adicse.facturador.wintac.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="productos")
public class Producto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name="codpro")
	private long idProducto;
	
	@Column(name="nompro")
	private String dscProducto;
	
	
	//bi-directional many-to-one association to facturas
	@OneToMany(mappedBy="producto")
	private List<FacturaDetalle> facturaDetalles;
	

	public long getIdProducto() {
		return idProducto;
	}

	public void setIdProducto(long idProducto) {
		this.idProducto = idProducto;
	}

	public String getDscProducto() {
		return dscProducto;
	}

	public void setDscProducto(String dscProducto) {
		this.dscProducto = dscProducto;
	}

	public Producto() {
	}
	
	
	

}
