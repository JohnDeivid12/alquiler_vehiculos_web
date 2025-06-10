package com.example.demo.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.modelo.Vehiculo;

@Repository
public interface RepositorioVehiculo extends JpaRepository<Vehiculo, String> { 
	
}
