package com.adicse.facturador.model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the perfilesdetallemantenimiento database table.
 * 
 */
@Entity
@NamedQuery(name="Perfilesdetallemantenimiento.findAll", query="SELECT p FROM Perfilesdetallemantenimiento p")
public class Perfilesdetallemantenimiento implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private Integer perfilesdetallemantenimiento;

	private Integer editar;

	private Integer eliminar;

	private Integer imprimir;

	private Integer nuevo;

	//bi-directional many-to-one association to Perfilesdetalle
	@ManyToOne
	@JoinColumn(name="idsysperfilesdetalle")
	private Perfilesdetalle perfilesdetalle;

	public Perfilesdetallemantenimiento() {
	}

	public Integer getPerfilesdetallemantenimiento() {
		return this.perfilesdetallemantenimiento;
	}

	public void setPerfilesdetallemantenimiento(Integer perfilesdetallemantenimiento) {
		this.perfilesdetallemantenimiento = perfilesdetallemantenimiento;
	}

	public Integer getEditar() {
		return this.editar;
	}

	public void setEditar(Integer editar) {
		this.editar = editar;
	}

	public Integer getEliminar() {
		return this.eliminar;
	}

	public void setEliminar(Integer eliminar) {
		this.eliminar = eliminar;
	}

	public Integer getImprimir() {
		return this.imprimir;
	}

	public void setImprimir(Integer imprimir) {
		this.imprimir = imprimir;
	}

	public Integer getNuevo() {
		return this.nuevo;
	}

	public void setNuevo(Integer nuevo) {
		this.nuevo = nuevo;
	}

	public Perfilesdetalle getPerfilesdetalle() {
		return this.perfilesdetalle;
	}

	public void setPerfilesdetalle(Perfilesdetalle perfilesdetalle) {
		this.perfilesdetalle = perfilesdetalle;
	}

}