package com.adicse.facturador.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the perfil database table.
 * 
 */
@Entity
@NamedQuery(name="Perfil.findAll", query="SELECT p FROM Perfil p")
public class Perfil implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private Integer idperfil;

	private String dscperfil;

	private Integer idfilial;

	//bi-directional many-to-one association to Perfilesdetalle
	@OneToMany(mappedBy="perfil")
	private List<Perfilesdetalle> perfilesdetalles;

	//bi-directional many-to-one association to Usuario
	@OneToMany(mappedBy="perfil")
	private List<Usuario> usuarios;

	public Perfil() {
	}

	public Integer getIdperfil() {
		return this.idperfil;
	}

	public void setIdperfil(Integer idperfil) {
		this.idperfil = idperfil;
	}

	public String getDscperfil() {
		return this.dscperfil;
	}

	public void setDscperfil(String dscperfil) {
		this.dscperfil = dscperfil;
	}

	public Integer getIdfilial() {
		return this.idfilial;
	}

	public void setIdfilial(Integer idfilial) {
		this.idfilial = idfilial;
	}

	public List<Perfilesdetalle> getPerfilesdetalles() {
		return this.perfilesdetalles;
	}

	public void setPerfilesdetalles(List<Perfilesdetalle> perfilesdetalles) {
		this.perfilesdetalles = perfilesdetalles;
	}

	public Perfilesdetalle addPerfilesdetalle(Perfilesdetalle perfilesdetalle) {
		getPerfilesdetalles().add(perfilesdetalle);
		perfilesdetalle.setPerfil(this);

		return perfilesdetalle;
	}

	public Perfilesdetalle removePerfilesdetalle(Perfilesdetalle perfilesdetalle) {
		getPerfilesdetalles().remove(perfilesdetalle);
		perfilesdetalle.setPerfil(null);

		return perfilesdetalle;
	}

	public List<Usuario> getUsuarios() {
		return this.usuarios;
	}

	public void setUsuarios(List<Usuario> usuarios) {
		this.usuarios = usuarios;
	}

	public Usuario addUsuario(Usuario usuario) {
		getUsuarios().add(usuario);
		usuario.setPerfil(this);

		return usuario;
	}

	public Usuario removeUsuario(Usuario usuario) {
		getUsuarios().remove(usuario);
		usuario.setPerfil(null);

		return usuario;
	}

}