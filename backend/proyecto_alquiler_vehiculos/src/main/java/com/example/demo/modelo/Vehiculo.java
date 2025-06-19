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
    private Double valorAlquilerVehiculo;
	
	@Column(name = "estado_vehiculo", length = 30, nullable = false)
    private String estadoVehiculo;

	public Vehiculo() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Vehiculo(String placa, String tipo, String color, Double valorAlquilerVehiculo, String estadoVehiculo) {
		super();
		this.placa = placa;
		this.tipo = tipo;
		this.color = color;
		this.valorAlquilerVehiculo = valorAlquilerVehiculo;
		this.estadoVehiculo = estadoVehiculo;
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

	public Double getValorAlquilerVehiculo() {
		return valorAlquilerVehiculo;
	}

	public void setValorAlquilerVehiculo(Double valorAlquilerVehiculo) {
		this.valorAlquilerVehiculo = valorAlquilerVehiculo;
	}

	public String getEstadoVehiculo() {
		return estadoVehiculo;
	}

	public void setEstadoVehiculo(String estadoVehiculo) {
		this.estadoVehiculo = estadoVehiculo;
	}
	
}


