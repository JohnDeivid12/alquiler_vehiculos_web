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
    private Long idAlquiler;
	
	//Modificado por John Deivid de OneToOne a ManyToOne
	@ManyToOne
	@JoinColumn(name = "placa", referencedColumnName = "placa")
	private Vehiculo vehiculo;
	
	@ManyToOne()
	   @JoinColumn(name = "id_usuario", referencedColumnName = "id_usuario")
	    private Usuario usuario;

	@Column(name = "fecha_inicio")
    private Date fechaInicio;
	
	@Column(name = "fecha_entrega")
    private Date fechaEntrega;
	
	@Column(name = "valor_alquiler")
    private Double valorAlquiler;
	
	@Column(name = "estado")
    private String estado; 
	
	@Column(name = "fecha_entrega_real")
    private Date fechaEntregaReal; 
	
	@Column(name = "costo_extra")
    private Double costoExtra; 
	
	@Column(name = "valor_total_alquiler")
    private Double valorTotalAlquiler;

	public Alquiler(Long idAlquiler, Vehiculo vehiculo, Usuario usuario, Date fechaInicio, Date fechaEntrega,
			Double valorAlquiler, String estado, Date fechaEntregaReal, Double costoExtra,
			Double valorTotalAlquiler) {
		super();
		this.idAlquiler = idAlquiler;
		this.vehiculo = vehiculo;
		this.usuario = usuario;
		this.fechaInicio = fechaInicio;
		this.fechaEntrega = fechaEntrega;
		this.valorAlquiler = valorAlquiler;
		this.estado = estado;
		this.fechaEntregaReal = fechaEntregaReal;
		this.costoExtra = costoExtra;
		this.valorTotalAlquiler = valorTotalAlquiler;
	}

	public Long getIdAlquiler() {
		return idAlquiler;
	}

	public void setIdAlquiler(Long idAlquiler) {
		this.idAlquiler = idAlquiler;
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

	public Date getFechaInicio() {
		return fechaInicio;
	}

	public void setFechaInicio(Date fechaInicio) {
		this.fechaInicio = fechaInicio;
	}

	public Date getFechaEntrega() {
		return fechaEntrega;
	}

	public void setFechaEntrega(Date fecha_entrega) {
		this.fechaEntrega = fecha_entrega;
	}

	public Double getValorAlquiler() {
		return valorAlquiler;
	}

	public void setValorAlquiler(Double valorAlquiler) {
		this.valorAlquiler = valorAlquiler;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public Date getFechaEntregaReal() {
		return fechaEntregaReal;
	}

	public void setFechaEntregaReal(Date fechaEntregaReal) {
		this.fechaEntregaReal = fechaEntregaReal;
	}

	public Double getCostoExtra() {
		return costoExtra;
	}

	public void setCostoExtra(Double costoExtra) {
		this.costoExtra = costoExtra;
	}

	public Double getValorTotalAlquiler() {
		return valorTotalAlquiler;
	}

	public void setValorTotalAlquiler(Double valorTotalAlquiler) {
		this.valorTotalAlquiler = valorTotalAlquiler;
	}

	public Alquiler() {
		super();
		// TODO Auto-generated constructor stub
	} 
	
	
	
}
