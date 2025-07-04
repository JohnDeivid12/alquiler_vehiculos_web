package com.example.demo.modelo;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "admin")
public class Admin {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_admin")
    private Long id_admin;
    
    @Column(name = "usuarioadmin", length = 50, nullable = false, unique = true)
    private String usuarioAdmin;
    
    @Column(name = "primer_nombre_a", length = 50, nullable = false)
    private String primer_nombre_a;
    
    @Column(name = "segundo_nombre_a", length = 50)
    private String segundo_nombre_a;
    
    @Column(name = "primer_apellido_a", length = 50, nullable = false)
    private String primer_apellido_a;
    
    @Column(name = "segundo_apellido_a", length = 50)
    private String segundo_apellido_a;
    
    @Column(name = "contrasena_admin", length = 40, nullable = false)
    private String contrasena_admin;

	public Long getId_admin() {
		return id_admin;
	}

	public Admin() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Admin(Long id_admin, String usuarioAdmin, String primer_nombre_a, String segundo_nombre_a,
			String primer_apellido_a, String segundo_apellido_a, String contrasena_admin) {
		super();
		this.id_admin = id_admin;
		this.usuarioAdmin = usuarioAdmin;
		this.primer_nombre_a = primer_nombre_a;
		this.segundo_nombre_a = segundo_nombre_a;
		this.primer_apellido_a = primer_apellido_a;
		this.segundo_apellido_a = segundo_apellido_a; 
		this.contrasena_admin = contrasena_admin;
	}

	public void setId_admin(Long id_admin) {
		this.id_admin = id_admin;
	}

	public String getUsuarioAdmin() {
		return usuarioAdmin;
	}

	public void setUsuarioAdmin(String usuarioAdmin) {
		this.usuarioAdmin = usuarioAdmin;
	}

	public String getPrimer_nombre_a() {
		return primer_nombre_a;
	}

	public void setPrimer_nombre_a(String primer_nombre_a) {
		this.primer_nombre_a = primer_nombre_a;
	}

	public String getSegundo_nombre_a() {
		return segundo_nombre_a;
	}

	public void setSegundo_nombre_a(String segundo_nombre_a) {
		this.segundo_nombre_a = segundo_nombre_a;
	}

	public String getPrimer_apellido_a() {
		return primer_apellido_a;
	}

	public void setPrimer_apellido_a(String primer_apellido_a) {
		this.primer_apellido_a = primer_apellido_a;
	}

	public String getSegundo_apellido_a() {
		return segundo_apellido_a;
	}

	public void setSegundo_apellido_a(String segundo_apellido_a) {
		this.segundo_apellido_a = segundo_apellido_a;
	}

	public String getContrasena_admin() {
		return contrasena_admin;
	}

	public void setContrasena_admin(String contrasena_admin) {
		this.contrasena_admin = contrasena_admin;
	}
   
}