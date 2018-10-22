package com.adicse.facturador.model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the documento_detalle database table.
 * 
 */
@Entity
@Table(name="documento_detalle")
@NamedQuery(name="DocumentoDetalle.findAll", query="SELECT d FROM DocumentoDetalle d")
public class DocumentoDetalle implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="id_factura_detalle")
	private String idFacturaDetalle;

	private double cantidad;

	@Column(name="codigo_producto")
	private String codigoProducto;

	@Column(name="dsc_producto_servicio")
	private String dscProductoServicio;

	@Column(name="porcentaje_descuento")
	private double porcentajeDescuento;

	@Column(name="porcentaje_igv")
	private double porcentajeIgv;

	@Column(name="precio_unitario_con_igv")
	private double precioUnitarioConIgv;

	@Column(name="precio_unitario_sin_igv")
	private double precioUnitarioSinIgv;

	@Column(name="total_descuento")
	private double totalDescuento;

	@Column(name="total_igv")
	private double totalIgv;

	@Column(name="total_valor_venta_bruto")
	private double totalValorVentaBruto;

	@Column(name="total_valor_venta_exonerado")
	private double totalValorVentaExonerado;

	@Column(name="total_valor_venta_grabada")
	private double totalValorVentaGrabada;

	@Column(name="total_venta")
	private double totalVenta;

	//bi-directional many-to-one association to DocumentoCab
	@ManyToOne
	@JoinColumn(name="id_documento_cab")
	private DocumentoCab documentoCab;

	public DocumentoDetalle() {
	}

	public String getIdFacturaDetalle() {
		return this.idFacturaDetalle;
	}

	public void setIdFacturaDetalle(String idFacturaDetalle) {
		this.idFacturaDetalle = idFacturaDetalle;
	}

	public double getCantidad() {
		return this.cantidad;
	}

	public void setCantidad(double cantidad) {
		this.cantidad = cantidad;
	}

	public String getCodigoProducto() {
		return this.codigoProducto;
	}

	public void setCodigoProducto(String codigoProducto) {
		this.codigoProducto = codigoProducto;
	}

	public String getDscProductoServicio() {
		return this.dscProductoServicio;
	}

	public void setDscProductoServicio(String dscProductoServicio) {
		this.dscProductoServicio = dscProductoServicio;
	}

	public double getPorcentajeDescuento() {
		return this.porcentajeDescuento;
	}

	public void setPorcentajeDescuento(double porcentajeDescuento) {
		this.porcentajeDescuento = porcentajeDescuento;
	}

	public double getPorcentajeIgv() {
		return this.porcentajeIgv;
	}

	public void setPorcentajeIgv(double porcentajeIgv) {
		this.porcentajeIgv = porcentajeIgv;
	}

	public double getPrecioUnitarioConIgv() {
		return this.precioUnitarioConIgv;
	}

	public void setPrecioUnitarioConIgv(double precioUnitarioConIgv) {
		this.precioUnitarioConIgv = precioUnitarioConIgv;
	}

	public double getPrecioUnitarioSinIgv() {
		return this.precioUnitarioSinIgv;
	}

	public void setPrecioUnitarioSinIgv(double precioUnitarioSinIgv) {
		this.precioUnitarioSinIgv = precioUnitarioSinIgv;
	}

	public double getTotalDescuento() {
		return this.totalDescuento;
	}

	public void setTotalDescuento(double totalDescuento) {
		this.totalDescuento = totalDescuento;
	}

	public double getTotalIgv() {
		return this.totalIgv;
	}

	public void setTotalIgv(double totalIgv) {
		this.totalIgv = totalIgv;
	}

	public double getTotalValorVentaBruto() {
		return this.totalValorVentaBruto;
	}

	public void setTotalValorVentaBruto(double totalValorVentaBruto) {
		this.totalValorVentaBruto = totalValorVentaBruto;
	}

	public double getTotalValorVentaExonerado() {
		return this.totalValorVentaExonerado;
	}

	public void setTotalValorVentaExonerado(double totalValorVentaExonerado) {
		this.totalValorVentaExonerado = totalValorVentaExonerado;
	}

	public double getTotalValorVentaGrabada() {
		return this.totalValorVentaGrabada;
	}

	public void setTotalValorVentaGrabada(double totalValorVentaGrabada) {
		this.totalValorVentaGrabada = totalValorVentaGrabada;
	}

	public double getTotalVenta() {
		return this.totalVenta;
	}

	public void setTotalVenta(double totalVenta) {
		this.totalVenta = totalVenta;
	}

	public DocumentoCab getDocumentoCab() {
		return this.documentoCab;
	}

	public void setDocumentoCab(DocumentoCab documentoCab) {
		this.documentoCab = documentoCab;
	}

}