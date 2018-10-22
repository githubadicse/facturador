package com.adicse.facturador.model;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;


/**
 * The persistent class for the usuario database table.
 * 
 */
@Entity
@NamedQuery(name="Usuario.findAll", query="SELECT u FROM Usuario u")
public class Usuario implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private Integer idusuario;

	private Boolean activo;

	private String clave;

	@Column(name="cnt_cambio_clave")
	private Integer cntCambioClave;

	private String dni;

	@Column(name="fecha_registro_systema")
	private Timestamp fechaRegistroSystema;

	@Column(name="fecha_registro_systema_modifica")
	private Timestamp fechaRegistroSystemaModifica;

	@Column(name="idusuario_crea")
	private Integer idusuarioCrea;

	@Column(name="idusuario_modifica")
	private Integer idusuarioModifica;

	private String login;

	private String nomusuario;

	//bi-directional many-to-one association to Filial
	@ManyToOne
	@JoinColumn(name="idfilial")
	private Filial filial;

	//bi-directional many-to-one association to Perfil
	@ManyToOne
	@JoinColumn(name="idperfil")
	private Perfil perfil;

	public Usuario() {
	}

	public Integer getIdusuario() {
		return this.idusuario;
	}

	public void setIdusuario(Integer idusuario) {
		this.idusuario = idusuario;
	}

	public Boolean getActivo() {
		return this.activo;
	}

	public void setActivo(Boolean activo) {
		this.activo = activo;
	}

	public String getClave() {
		return this.clave;
	}

	public void setClave(String clave) {
		this.clave = clave;
	}

	public Integer getCntCambioClave() {
		return this.cntCambioClave;
	}

	public void setCntCambioClave(Integer cntCambioClave) {
		this.cntCambioClave = cntCambioClave;
	}

	public String getDni() {
		return this.dni;
	}

	public void setDni(String dni) {
		this.dni = dni;
	}

	public Timestamp getFechaRegistroSystema() {
		return this.fechaRegistroSystema;
	}

	public void setFechaRegistroSystema(Timestamp fechaRegistroSystema) {
		this.fechaRegistroSystema = fechaRegistroSystema;
	}

	public Timestamp getFechaRegistroSystemaModifica() {
		return this.fechaRegistroSystemaModifica;
	}

	public void setFechaRegistroSystemaModifica(Timestamp fechaRegistroSystemaModifica) {
		this.fechaRegistroSystemaModifica = fechaRegistroSystemaModifica;
	}

	public Integer getIdusuarioCrea() {
		return this.idusuarioCrea;
	}

	public void setIdusuarioCrea(Integer idusuarioCrea) {
		this.idusuarioCrea = idusuarioCrea;
	}

	public Integer getIdusuarioModifica() {
		return this.idusuarioModifica;
	}

	public void setIdusuarioModifica(Integer idusuarioModifica) {
		this.idusuarioModifica = idusuarioModifica;
	}

	public String getLogin() {
		return this.login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getNomusuario() {
		return this.nomusuario;
	}

	public void setNomusuario(String nomusuario) {
		this.nomusuario = nomusuario;
	}

	public Filial getFilial() {
		return this.filial;
	}

	public void setFilial(Filial filial) {
		this.filial = filial;
	}

	public Perfil getPerfil() {
		return this.perfil;
	}

	public void setPerfil(Perfil perfil) {
		this.perfil = perfil;
	}

}