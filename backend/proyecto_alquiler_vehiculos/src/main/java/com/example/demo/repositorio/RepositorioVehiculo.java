package com.example.demo.repositorio;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.modelo.Vehiculo;

@Repository
public interface RepositorioVehiculo extends JpaRepository<Vehiculo, String> { 
	
	public List<Vehiculo> findByTipoAndEstadoVehiculo(String tipo, String estadoVehiculo);
	public List<Vehiculo> findByEstadoVehiculo( String estadoVehiculo);
	
}