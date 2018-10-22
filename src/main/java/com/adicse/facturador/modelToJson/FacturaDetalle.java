package com.adicse.facturador.modelToJson;

import java.io.Serializable;

import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;


public class FacturaDetalle implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	private String idFacturaDetalle;
	
	private Integer item;
	
	// <cbc:InvoiceQuantity>
	// ejemplo 2000
	private float cantidad;

	// <cbc:Percent>
	// ejemplo 18% igual 18.00
	private float porcentajeIgv;

	// <cbc:PriceAmount>
	// 98.00
	private float precioUnitarioIncluidoIgv;

	// ejemplo 98/1.18 = 83.050847
	private float precioUnitarioSinIgv;

	// <cbc:BaseAmount>
	// baseImponible cantidad * precioUnitarioSinIgv -> 2000 * 83.050847 = 166101.69
	private float baseImponible;

	// <cbc:MultiplierFactorNumeric>
	// ejemplo 10% igual a 0.10
	private float porcentajeDescuento;

	// precioUnitarioSinIgv * porcentajeDescuento -> 83.050847 * 0.10 = 8.3050847
	private float descuentoUnitario;

	// <cac:AllowanceCharge>
	// <cbc:Amount>
	// cantidad * descuentoUnitario -> 2000 * 8.3050847 = 16,610.17
	private float totalDescuento;

	// <cac:TaxSubTotal>
	// <cac:TaxableAmount>
	// valorVenta = baseImponible - totalDescuento -> 166,101.69 - 16,610.17 =
	// 149,491.52
	private float valorVentaGrabado;

	private float valorVentaExonerado;

	private float importeTotalVenta;

	// <cac:TaxTotal>
	// <cbc:TaxAmount>
	// valorVenta * (porcentajeIgv / 100) -> 149,491.52 * 0.18 = 26,908.47
	private float impuestoTotal;

	// 01 con igv 0 2oneroso cuando se regala
	private String tipoPrecioVenta;

	// uso para emision de nota de credito
	private String facturaReferencia;

	// en caso de nota de credito se explica por que la devolucion del producto.
	private String glosa;	
	
	private Producto producto;
	
	
	// expresado en ejemplo: 10% - 0.10
	// private float porcentajeDescuento;
	
	//Catalogo Nro 7 Sunat 10 gravado-oneroso  y 20 exonerado -oneroso
	private String tipoAfectacionIgv;
	
	//1000 si tiene impuesto 9997 exonerado
	private String tipoDeTributo;
	
	

	

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

	public String getIdFacturaDetalle() {
		return idFacturaDetalle;
	}

	public void setIdFacturaDetalle(String idFacturaDetalle) {
		this.idFacturaDetalle = idFacturaDetalle;
	}

	public Producto getProducto() {
		return producto;
	}

	public void setProducto(Producto producto) {
		this.producto = producto;
	}

	public FacturaDetalle() {
	}

	public Integer getItem() {
		return item;
	}

	public void setItem(Integer item) {
		this.item = item;
	}

	public float getCantidad() {
		return cantidad;
	}

	public void setCantidad(float cantidad) {
		this.cantidad = cantidad;
	}

	public float getPorcentajeIgv() {
		return porcentajeIgv;
	}

	public void setPorcentajeIgv(float porcentajeIgv) {
		this.porcentajeIgv = porcentajeIgv;
	}

	public float getPrecioUnitarioIncluidoIgv() {
		return precioUnitarioIncluidoIgv;
	}

	public void setPrecioUnitarioIncluidoIgv(float precioUnitarioIncluidoIgv) {
		this.precioUnitarioIncluidoIgv = precioUnitarioIncluidoIgv;
	}

	public float getPrecioUnitarioSinIgv() {
		return precioUnitarioSinIgv;
	}

	public void setPrecioUnitarioSinIgv(float precioUnitarioSinIgv) {
		this.precioUnitarioSinIgv = precioUnitarioSinIgv;
	}

	public float getBaseImponible() {
		return baseImponible;
	}

	public void setBaseImponible(float baseImponible) {
		this.baseImponible = baseImponible;
	}

	public float getPorcentajeDescuento() {
		return porcentajeDescuento;
	}

	public void setPorcentajeDescuento(float porcentajeDescuento) {
		this.porcentajeDescuento = porcentajeDescuento;
	}

	public float getDescuentoUnitario() {
		return descuentoUnitario;
	}

	public void setDescuentoUnitario(float descuentoUnitario) {
		this.descuentoUnitario = descuentoUnitario;
	}

	public float getTotalDescuento() {
		return totalDescuento;
	}

	public void setTotalDescuento(float totalDescuento) {
		this.totalDescuento = totalDescuento;
	}

	public float getValorVentaGrabado() {
		return valorVentaGrabado;
	}

	public void setValorVentaGrabado(float valorVentaGrabado) {
		this.valorVentaGrabado = valorVentaGrabado;
	}

	public float getValorVentaExonerado() {
		return valorVentaExonerado;
	}

	public void setValorVentaExonerado(float valorVentaExonerado) {
		this.valorVentaExonerado = valorVentaExonerado;
	}

	public float getImporteTotalVenta() {
		return importeTotalVenta;
	}

	public void setImporteTotalVenta(float importeTotalVenta) {
		this.importeTotalVenta = importeTotalVenta;
	}

	public float getImpuestoTotal() {
		return impuestoTotal;
	}

	public void setImpuestoTotal(float impuestoTotal) {
		this.impuestoTotal = impuestoTotal;
	}

	public String getTipoPrecioVenta() {
		return tipoPrecioVenta;
	}

	public void setTipoPrecioVenta(String tipoPrecioVenta) {
		this.tipoPrecioVenta = tipoPrecioVenta;
	}

	public String getFacturaReferencia() {
		return facturaReferencia;
	}

	public void setFacturaReferencia(String facturaReferencia) {
		this.facturaReferencia = facturaReferencia;
	}

	public String getGlosa() {
		return glosa;
	}

	public void setGlosa(String glosa) {
		this.glosa = glosa;
	}

	public String getTipoAfectacionIgv() {
		return tipoAfectacionIgv;
	}

	public void setTipoAfectacionIgv(String tipoAfectacionIgv) {
		this.tipoAfectacionIgv = tipoAfectacionIgv;
	}

	public String getTipoDeTributo() {
		return tipoDeTributo;
	}

	public void setTipoDeTributo(String tipoDeTributo) {
		this.tipoDeTributo = tipoDeTributo;
	}


	
	
	
	
	

}
