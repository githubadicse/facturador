package com.adicse.facturador.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the documento_cab database table.
 * 
 */
@Entity
@Table(name="documento_cab")
@NamedQuery(name="DocumentoCab.findAll", query="SELECT d FROM DocumentoCab d")
public class DocumentoCab implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="id_documento_cab")
	private String idDocumentoCab;

	@Column(name="codigo_documento_sunat")
	private String codigoDocumentoSunat;

	@Column(name="codigo_tipo_documento_cliente")
	private Integer codigoTipoDocumentoCliente;

	@Column(name="codigo_tipo_tributo_cat05")
	private String codigoTipoTributoCat05;

	@Column(name="comprobante_emitido_sunat")
	private String comprobanteEmitidoSunat;

	@Column(name="comprobante_numero")
	private String comprobanteNumero;

	@Column(name="comprobante_serie")
	private String comprobanteSerie;

	@Column(name="direccion_cliente")
	private String direccionCliente;

	@Column(name="dsc_documento_sunat")
	private String dscDocumentoSunat;

	@Column(name="dsc_tipo_documento_cliente")
	private String dscTipoDocumentoCliente;

	@Column(name="estado_registro")
	private String estadoRegistro;

	@Column(name="estado_respuesta_sunat")
	private Integer estadoRespuestaSunat;

	@Temporal(TemporalType.DATE)
	@Column(name="fecha_emision")
	private Date fechaEmision;

	@Temporal(TemporalType.DATE)
	@Column(name="fecha_vencimiento")
	private Date fechaVencimiento;

	private Integer item;

	@Column(name="nombre_cliente")
	private String nombreCliente;

	@Column(name="nombre_tributo_cat05")
	private String nombreTributoCat05;

	@Column(name="numero_documento_cliente")
	private String numeroDocumentoCliente;

	@Column(name="pocentaje_igv")
	private double pocentajeIgv;

	@Column(name="sum_igv")
	private double sumIgv;

	@Column(name="sum_total_descuento")
	private double sumTotalDescuento;

	@Column(name="sum_total_venta")
	private double sumTotalVenta;

	@Column(name="sum_valor_venta_bruto")
	private double sumValorVentaBruto;

	@Column(name="sum_valor_venta_exonerada")
	private double sumValorVentaExonerada;

	@Column(name="sum_valor_venta_grabada")
	private double sumValorVentaGrabada;

	@Column(name="tipo_moneda")
	private String tipoMoneda;

	//bi-directional many-to-one association to ResumenBoleta
	@ManyToOne
	@JoinColumn(name="id_resumen_boleta")
	private ResumenBoleta resumenBoleta;

	//bi-directional many-to-one association to DocumentoDetalle
	@OneToMany(mappedBy="documentoCab")
	private List<DocumentoDetalle> documentoDetalles;

	public DocumentoCab() {
	}

	public String getIdDocumentoCab() {
		return this.idDocumentoCab;
	}

	public void setIdDocumentoCab(String idDocumentoCab) {
		this.idDocumentoCab = idDocumentoCab;
	}

	public String getCodigoDocumentoSunat() {
		return this.codigoDocumentoSunat;
	}

	public void setCodigoDocumentoSunat(String codigoDocumentoSunat) {
		this.codigoDocumentoSunat = codigoDocumentoSunat;
	}

	public Integer getCodigoTipoDocumentoCliente() {
		return this.codigoTipoDocumentoCliente;
	}

	public void setCodigoTipoDocumentoCliente(Integer codigoTipoDocumentoCliente) {
		this.codigoTipoDocumentoCliente = codigoTipoDocumentoCliente;
	}

	public String getCodigoTipoTributoCat05() {
		return this.codigoTipoTributoCat05;
	}

	public void setCodigoTipoTributoCat05(String codigoTipoTributoCat05) {
		this.codigoTipoTributoCat05 = codigoTipoTributoCat05;
	}

	public String getComprobanteEmitidoSunat() {
		return this.comprobanteEmitidoSunat;
	}

	public void setComprobanteEmitidoSunat(String comprobanteEmitidoSunat) {
		this.comprobanteEmitidoSunat = comprobanteEmitidoSunat;
	}

	public String getComprobanteNumero() {
		return this.comprobanteNumero;
	}

	public void setComprobanteNumero(String comprobanteNumero) {
		this.comprobanteNumero = comprobanteNumero;
	}

	public String getComprobanteSerie() {
		return this.comprobanteSerie;
	}

	public void setComprobanteSerie(String comprobanteSerie) {
		this.comprobanteSerie = comprobanteSerie;
	}

	public String getDireccionCliente() {
		return this.direccionCliente;
	}

	public void setDireccionCliente(String direccionCliente) {
		this.direccionCliente = direccionCliente;
	}

	public String getDscDocumentoSunat() {
		return this.dscDocumentoSunat;
	}

	public void setDscDocumentoSunat(String dscDocumentoSunat) {
		this.dscDocumentoSunat = dscDocumentoSunat;
	}

	public String getDscTipoDocumentoCliente() {
		return this.dscTipoDocumentoCliente;
	}

	public void setDscTipoDocumentoCliente(String dscTipoDocumentoCliente) {
		this.dscTipoDocumentoCliente = dscTipoDocumentoCliente;
	}

	public String getEstadoRegistro() {
		return this.estadoRegistro;
	}

	public void setEstadoRegistro(String estadoRegistro) {
		this.estadoRegistro = estadoRegistro;
	}

	public Integer getEstadoRespuestaSunat() {
		return this.estadoRespuestaSunat;
	}

	public void setEstadoRespuestaSunat(Integer estadoRespuestaSunat) {
		this.estadoRespuestaSunat = estadoRespuestaSunat;
	}

	public Date getFechaEmision() {
		return this.fechaEmision;
	}

	public void setFechaEmision(Date fechaEmision) {
		this.fechaEmision = fechaEmision;
	}

	public Date getFechaVencimiento() {
		return this.fechaVencimiento;
	}

	public void setFechaVencimiento(Date fechaVencimiento) {
		this.fechaVencimiento = fechaVencimiento;
	}

	public Integer getItem() {
		return this.item;
	}

	public void setItem(Integer item) {
		this.item = item;
	}

	public String getNombreCliente() {
		return this.nombreCliente;
	}

	public void setNombreCliente(String nombreCliente) {
		this.nombreCliente = nombreCliente;
	}

	public String getNombreTributoCat05() {
		return this.nombreTributoCat05;
	}

	public void setNombreTributoCat05(String nombreTributoCat05) {
		this.nombreTributoCat05 = nombreTributoCat05;
	}

	public String getNumeroDocumentoCliente() {
		return this.numeroDocumentoCliente;
	}

	public void setNumeroDocumentoCliente(String numeroDocumentoCliente) {
		this.numeroDocumentoCliente = numeroDocumentoCliente;
	}

	public double getPocentajeIgv() {
		return this.pocentajeIgv;
	}

	public void setPocentajeIgv(double pocentajeIgv) {
		this.pocentajeIgv = pocentajeIgv;
	}

	public double getSumIgv() {
		return this.sumIgv;
	}

	public void setSumIgv(double sumIgv) {
		this.sumIgv = sumIgv;
	}

	public double getSumTotalDescuento() {
		return this.sumTotalDescuento;
	}

	public void setSumTotalDescuento(double sumTotalDescuento) {
		this.sumTotalDescuento = sumTotalDescuento;
	}

	public double getSumTotalVenta() {
		return this.sumTotalVenta;
	}

	public void setSumTotalVenta(double sumTotalVenta) {
		this.sumTotalVenta = sumTotalVenta;
	}

	public double getSumValorVentaBruto() {
		return this.sumValorVentaBruto;
	}

	public void setSumValorVentaBruto(double sumValorVentaBruto) {
		this.sumValorVentaBruto = sumValorVentaBruto;
	}

	public double getSumValorVentaExonerada() {
		return this.sumValorVentaExonerada;
	}

	public void setSumValorVentaExonerada(double sumValorVentaExonerada) {
		this.sumValorVentaExonerada = sumValorVentaExonerada;
	}

	public double getSumValorVentaGrabada() {
		return this.sumValorVentaGrabada;
	}

	public void setSumValorVentaGrabada(double sumValorVentaGrabada) {
		this.sumValorVentaGrabada = sumValorVentaGrabada;
	}

	public String getTipoMoneda() {
		return this.tipoMoneda;
	}

	public void setTipoMoneda(String tipoMoneda) {
		this.tipoMoneda = tipoMoneda;
	}

	public ResumenBoleta getResumenBoleta() {
		return this.resumenBoleta;
	}

	public void setResumenBoleta(ResumenBoleta resumenBoleta) {
		this.resumenBoleta = resumenBoleta;
	}

	public List<DocumentoDetalle> getDocumentoDetalles() {
		return this.documentoDetalles;
	}

	public void setDocumentoDetalles(List<DocumentoDetalle> documentoDetalles) {
		this.documentoDetalles = documentoDetalles;
	}

	public DocumentoDetalle addDocumentoDetalle(DocumentoDetalle documentoDetalle) {
		getDocumentoDetalles().add(documentoDetalle);
		documentoDetalle.setDocumentoCab(this);

		return documentoDetalle;
	}

	public DocumentoDetalle removeDocumentoDetalle(DocumentoDetalle documentoDetalle) {
		getDocumentoDetalles().remove(documentoDetalle);
		documentoDetalle.setDocumentoCab(null);

		return documentoDetalle;
	}

}