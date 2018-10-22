package com.adicse.facturador.modelToJson;

import java.io.Serializable;
import java.util.List;

public class Producto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private long idProducto;
	
	private String dscProducto;
	
	
	private List<FacturaDetalle> facturaDetalles;
	

	public List<FacturaDetalle> getFacturaDetalles() {
		return facturaDetalles;
	}

	public void setFacturaDetalles(List<FacturaDetalle> facturaDetalles) {
		this.facturaDetalles = facturaDetalles;
	}

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
