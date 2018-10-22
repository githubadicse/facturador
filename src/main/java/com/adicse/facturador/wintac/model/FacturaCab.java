package com.adicse.facturador.wintac.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Table(name="ing001")
public class FacturaCab implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="id")
	private String id;
	
	
	
	@Column(name="fecha")
	@JsonFormat (pattern ="dd/MM/yyyy")
	@Temporal(TemporalType.DATE)
	private Date fecha;

	
	//bi-directional many-to-one association to cliente
	@ManyToOne
	@JoinColumn(name="codpro")
	private Cliente cliente;
 
	//bi-directional many-to-one association to tipoDocumento
	@ManyToOne
	@JoinColumn(name="coddoc")
	private TipoDocumento tipoDocumento;
	
	@Column(name="serie")
	private Integer serie;
	
	@Column(name="nrodoc")
	private Integer numero;
	
	
	@Column(name="igv")
	private float igvPorcentaje;	
	
	//bi-directional many-to-one association to facturas Detalle
	@OneToMany(mappedBy="facturaCab",  cascade={CascadeType.ALL})
	private List<FacturaDetalle> facturaDetalles;	

	
	
	
	


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
	
	
	
	
	
}
