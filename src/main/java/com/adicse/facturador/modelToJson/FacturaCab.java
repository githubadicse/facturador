package com.adicse.facturador.modelToJson;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonFormat;

public class FacturaCab implements Serializable {

	private static final long serialVersionUID = 1L;

	private String id;
	
	@JsonFormat (pattern ="dd/MM/yyyy", timezone="America/Lima")
	@Temporal(TemporalType.DATE)
	private Date fecha;

	
	private Cliente cliente;
 

	private TipoDocumento tipoDocumento;
	
	
	private Integer serie;
	
	
	private Integer numero;
	
	
	
	private float igvPorcentaje;	
	

	private List<FacturaDetalle> facturaDetalles;	

	
	private Integer flagArchivoFacturador;
	
	private String emisorRazonsocial;
	private String emisorRuc;
	private String emisorDireccion;	
	
	private String tipoMoneda;
	

	public Integer getSerie() {
		return serie;
	}

	public void setSerie(Integer serie) {
		this.serie = serie;
	}

	public Integer getNumero() {
		return numero;
	}

	public void setNumero(Integer numero) {
		this.numero = numero;
	}

	public List<FacturaDetalle> getFacturaDetalles() {
		return facturaDetalles;
	}

	public void setFacturaDetalles(List<FacturaDetalle> facturaDetalles) {
		this.facturaDetalles = facturaDetalles;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public FacturaCab() {
	}

	public TipoDocumento getTipoDocumento() {
		return tipoDocumento;
	}

	public void setTipoDocumento(TipoDocumento tipoDocumento) {
		this.tipoDocumento = tipoDocumento;
	}

	public float getIgvPorcentaje() {
		return igvPorcentaje;
	}

	public void setIgvPorcentaje(float igvPorcentaje) {
		this.igvPorcentaje = igvPorcentaje;
	}

	public Integer getFlagArchivoFacturador() {
		return flagArchivoFacturador;
	}

	public void setFlagArchivoFacturador(Integer flagArchivoFacturador) {
		this.flagArchivoFacturador = flagArchivoFacturador;
	}

	public String getEmisorRazonsocial() {
		return emisorRazonsocial;
	}

	public void setEmisorRazonsocial(String emisorRazonsocial) {
		this.emisorRazonsocial = emisorRazonsocial;
	}

	public String getEmisorRuc() {
		return emisorRuc;
	}

	public void setEmisorRuc(String emisorRuc) {
		this.emisorRuc = emisorRuc;
	}

	public String getEmisorDireccion() {
		return emisorDireccion;
	}

	public void setEmisorDireccion(String emisorDireccion) {
		this.emisorDireccion = emisorDireccion;
	}

	public String getTipoMoneda() {
		return tipoMoneda;
	}

	public void setTipoMoneda(String tipoMoneda) {
		this.tipoMoneda = tipoMoneda;
	}

	
	
	
	
}
