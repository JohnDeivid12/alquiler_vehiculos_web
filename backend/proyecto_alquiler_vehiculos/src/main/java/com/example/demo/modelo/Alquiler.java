package com.example.demo.modelo;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "alquiler")

public class Alquiler {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_alquiler")
    private Long id_alquiler;
	
	@ManyToOne()
	   @JoinColumn(name = "placa", referencedColumnName = "placa")
	    private Vehiculo vehiculo;
	
	@ManyToOne()
	   @JoinColumn(name = "id_usuario", referencedColumnName = "id_usuario")
	    private Usuario usuario;

	@Column(name = "fecha_inicio")
    private Date fecha_inicio;
	
	@Column(name = "fecha_entrega")
    private Date fecha_entrega;
	
	@Column(name = "valor_alquiler")
    private Double valor_alquiler;
	
	@Column(name = "estado")
    private String estado; 
	
	@Column(name = "fecha_entrega_real")
    private Date fecha_entrega_real; 
	
	@Column(name = "costo_extra")
    private Double costo_extra; 
	
	@Column(name = "valor_total_alquiler")
    private Double valor_total_alquiler;

	public Alquiler(Long id_alquiler, Vehiculo vehiculo, Usuario usuario, Date fecha_inicio, Date fecha_entrega,
			Double valor_alquiler, String estado, Date fecha_entrega_real, Double costo_extra,
			Double valor_total_alquiler) {
		super();
		this.id_alquiler = id_alquiler;
		this.vehiculo = vehiculo;
		this.usuario = usuario;
		this.fecha_inicio = fecha_inicio;
		this.fecha_entrega = fecha_entrega;
		this.valor_alquiler = valor_alquiler;
		this.estado = estado;
		this.fecha_entrega_real = fecha_entrega_real;
		this.costo_extra = costo_extra;
		this.valor_total_alquiler = valor_total_alquiler;
	}

	public Long getId_alquiler() {
		return id_alquiler;
	}

	public void setId_alquiler(Long id_alquiler) {
		this.id_alquiler = id_alquiler;
	}

	public Vehiculo getVehiculo() {
		return vehiculo;
	}

	public void setVehiculo(Vehiculo vehiculo) {
		this.vehiculo = vehiculo;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Date getFecha_inicio() {
		return fecha_inicio;
	}

	public void setFecha_inicio(Date fecha_inicio) {
		this.fecha_inicio = fecha_inicio;
	}

	public Date getFecha_entrega() {
		return fecha_entrega;
	}

	public void setFecha_entrega(Date fecha_entrega) {
		this.fecha_entrega = fecha_entrega;
	}

	public Double getValor_alquiler() {
		return valor_alquiler;
	}

	public void setValor_alquiler(Double valor_alquiler) {
		this.valor_alquiler = valor_alquiler;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public Date getFecha_entrega_real() {
		return fecha_entrega_real;
	}

	public void setFecha_entrega_real(Date fecha_entrega_real) {
		this.fecha_entrega_real = fecha_entrega_real;
	}

	public Double getCosto_extra() {
		return costo_extra;
	}

	public void setCosto_extra(Double costo_extra) {
		this.costo_extra = costo_extra;
	}

	public Double getValor_total_alquiler() {
		return valor_total_alquiler;
	}

	public void setValor_total_alquiler(Double valor_total_alquiler) {
		this.valor_total_alquiler = valor_total_alquiler;
	}

	public Alquiler() {
		super();
		// TODO Auto-generated constructor stub
	} 
	
	
	
}
