package com.adicse.facturador.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the perfilesdetalle database table.
 * 
 */
@Entity
@NamedQuery(name="Perfilesdetalle.findAll", query="SELECT p FROM Perfilesdetalle p")
public class Perfilesdetalle implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private String idsysperfilesdetalle;

	private String idmenu;

	//bi-directional many-to-one association to Perfil
	@ManyToOne
	@JoinColumn(name="idperfil")
	private Perfil perfil;

	//bi-directional many-to-one association to Perfilesdetallemantenimiento
	@OneToMany(mappedBy="perfilesdetalle")
	private List<Perfilesdetallemantenimiento> perfilesdetallemantenimientos;

	public Perfilesdetalle() {
	}

	public String getIdsysperfilesdetalle() {
		return this.idsysperfilesdetalle;
	}

	public void setIdsysperfilesdetalle(String idsysperfilesdetalle) {
		this.idsysperfilesdetalle = idsysperfilesdetalle;
	}

	public String getIdmenu() {
		return this.idmenu;
	}

	public void setIdmenu(String idmenu) {
		this.idmenu = idmenu;
	}

	public Perfil getPerfil() {
		return this.perfil;
	}

	public void setPerfil(Perfil perfil) {
		this.perfil = perfil;
	}

	public List<Perfilesdetallemantenimiento> getPerfilesdetallemantenimientos() {
		return this.perfilesdetallemantenimientos;
	}

	public void setPerfilesdetallemantenimientos(List<Perfilesdetallemantenimiento> perfilesdetallemantenimientos) {
		this.perfilesdetallemantenimientos = perfilesdetallemantenimientos;
	}

	public Perfilesdetallemantenimiento addPerfilesdetallemantenimiento(Perfilesdetallemantenimiento perfilesdetallemantenimiento) {
		getPerfilesdetallemantenimientos().add(perfilesdetallemantenimiento);
		perfilesdetallemantenimiento.setPerfilesdetalle(this);

		return perfilesdetallemantenimiento;
	}

	public Perfilesdetallemantenimiento removePerfilesdetallemantenimiento(Perfilesdetallemantenimiento perfilesdetallemantenimiento) {
		getPerfilesdetallemantenimientos().remove(perfilesdetallemantenimiento);
		perfilesdetallemantenimiento.setPerfilesdetalle(null);

		return perfilesdetallemantenimiento;
	}

}