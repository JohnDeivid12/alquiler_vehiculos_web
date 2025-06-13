package com.example.demo.modelo;


import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "gestion_alquiler")
public class GestionAlquiler {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_gestion")
    private Long id_gestion;
    
    @Column(name = "fecha_gestion")
    private Date fecha_gestion;
    
    @ManyToOne()
    @JoinColumn(name = "id_alquiler", referencedColumnName = "id_alquiler")
    private Alquiler alquiler;
    
    @ManyToOne()
    @JoinColumn(name = "id_admin", referencedColumnName = "id_admin")
    private Admin admin;

	public Long getId_gestion() {
		return id_gestion;
	}
 
	public void setId_gestion(Long id_gestion) {
		this.id_gestion = id_gestion;
	}

	public Date getFecha_gestion() {
		return fecha_gestion;
	}

	public void setFecha_gestion(Date fecha_gestion) {
		this.fecha_gestion = fecha_gestion;
	}

	public Alquiler getAlquiler() {
		return alquiler;
	}

	public void setAlquiler(Alquiler alquiler) {
		this.alquiler = alquiler;
	}
	
	public Alquiler getAdmin() {
		return alquiler;
	}

	public void setAdmin(Admin admin) {
		this.admin = admin;
	}

	public GestionAlquiler(Long id_gestion, Date fecha_gestion, Alquiler alquiler, Admin admin) {
		super();
		this.id_gestion = id_gestion;
		this.fecha_gestion = fecha_gestion;
		this.alquiler = alquiler;
		this.admin = admin;
	}

	public GestionAlquiler() {
		super();
		// TODO Auto-generated constructor stub
	}
}
