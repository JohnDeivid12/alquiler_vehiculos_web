package com.example.demo.modelo;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "vehiculo")

public class Vehiculo {
	@Id
	@Column(name = "placa", length = 15, nullable = false)
    private String placa;
	
	@Column(name = "tipo", length = 40, nullable = false)
    private String tipo;
	
	@Column(name = "color", length = 20, nullable = false)
    private String color;
	
	@Column(name = "valor_alquiler_vehiculo", nullable = false)
    private Double valor_alquiler_vehiculo;
	
	@Column(name = "estado_vehiculo", length = 30, nullable = false)
    private String estado_vehiculo;

	public Vehiculo() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Vehiculo(String placa, String tipo, String color, Double valor_alquiler_vehiculo, String estado_vehiculo) {
		super();
		this.placa = placa;
		this.tipo = tipo;
		this.color = color;
		this.valor_alquiler_vehiculo = valor_alquiler_vehiculo;
		this.estado_vehiculo = estado_vehiculo;
	}

	public String getPlaca() {
		return placa;
	}

	public void setPlaca(String placa) {
		this.placa = placa;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public Double getValor_alquiler_vehiculo() {
		return valor_alquiler_vehiculo;
	}

	public void setValor_alquiler_vehiculo(Double valor_alquiler_vehiculo) {
		this.valor_alquiler_vehiculo = valor_alquiler_vehiculo;
	}

	public String getEstado_vehiculo() {
		return estado_vehiculo;
	}

	public void setEstado_vehiculo(String estado_vehiculo) {
		this.estado_vehiculo = estado_vehiculo;
	}
	
}


