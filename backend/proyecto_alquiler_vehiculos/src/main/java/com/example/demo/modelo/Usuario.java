package com.example.demo.modelo;

import java.sql.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;

import jakarta.persistence.Id;
import jakarta.persistence.Table;


@Entity
@Table(name = "usuario")

public class Usuario {
 
	    @Id
	    @Column(name = "id_usuario", nullable = false, unique = true)
	    private Long idUsuario;

	    @Column(name = "primer_nombre_u", nullable = false)
	    private String primer_nombre_u;

	    @Column(name = "segundo_nombre_u")
	    private String segundo_nombre_u;

	    @Column(name = "primer_apellido_u", nullable = false)
	    private String primer_apellido_u;

	    @Column(name = "segundo_apellido_u")
	    private String segundo_apellido_u;

	    @Column(name = "fecha_expedicion_licencia", nullable = false)
	    private Date fecha_expedicion_licencia;

	    @Column(name = "categoria_licencia", nullable = false)
	    private String categoria_licencia;

	    @Column(name = "vigencia_licencia", nullable = false)
	    private Date vigencia_licencia;

	    @Column(name = "correo", nullable = false, unique = true)
	    private String correo;

	    @Column(name = "telefono", nullable = false)
	    private String telefono;

	    @Column(name = "contrasena_usuario", nullable = false)
	    private String contrasena_usuario;

	
	public Usuario() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Usuario(Long idUsuario, String primer_nombre_u, String segundo_nombre_u, String primer_apellido_u,
			String segundo_apellido_u, Date fecha_expedicion_licencia, String categoria_licencia,
			Date vigencia_licencia, String correo, String telefono, String contrasena_usuario) {
		super();
		this.idUsuario = idUsuario;
		this.primer_nombre_u = primer_nombre_u;
		this.segundo_nombre_u = segundo_nombre_u;
		this.primer_apellido_u = primer_apellido_u;
		this.segundo_apellido_u = segundo_apellido_u;
		this.fecha_expedicion_licencia = fecha_expedicion_licencia;
		this.categoria_licencia = categoria_licencia;
		this.vigencia_licencia = vigencia_licencia;
		this.correo = correo;
		this.telefono = telefono;
		this.contrasena_usuario = contrasena_usuario;
	}

	public Long getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(Long idUsuario) {
		this.idUsuario = idUsuario;
	}

	public String getPrimer_nombre_u() {
		return primer_nombre_u;
	}

	public void setPrimer_nombre_u(String primer_nombre_u) {
		this.primer_nombre_u = primer_nombre_u;
	}

	public String getSegundo_nombre_u() {
		return segundo_nombre_u;
	}

	public void setSegundo_nombre_u(String segundo_nombre_u) {
		this.segundo_nombre_u = segundo_nombre_u;
	}

	public String getPrimer_apellido_u() {
		return primer_apellido_u;
	}

	public void setPrimer_apellido_u(String primer_apellido_u) {
		this.primer_apellido_u = primer_apellido_u;
	}

	public String getSegundo_apellido_u() {
		return segundo_apellido_u;
	}

	public void setSegundo_apellido_u(String segundo_apellido_u) {
		this.segundo_apellido_u = segundo_apellido_u;
	}

	public Date getFecha_expedicion_licencia() {
		return fecha_expedicion_licencia;
	}

	public void setFecha_expedicion_licencia(Date fecha_expedicion_licencia) {
		this.fecha_expedicion_licencia = fecha_expedicion_licencia;
	}

	public String getCategoria_licencia() {
		return categoria_licencia;
	}

	public void setCategoria_licencia(String categoria_licencia) {
		this.categoria_licencia = categoria_licencia;
	}

	public Date getVigencia_licencia() {
		return vigencia_licencia;
	}

	public void setVigencia_licencia(Date vigencia_licencia) {
		this.vigencia_licencia = vigencia_licencia;
	}

	public String getCorreo() {
		return correo;
	}

	public void setCorreo(String correo) {
		this.correo = correo;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public String getContrasena_usuario() {
		return contrasena_usuario;
	}

	public void setContraseña_usuario(String contrasena_usuario) {
		this.contrasena_usuario = contrasena_usuario;
	}
	
}

