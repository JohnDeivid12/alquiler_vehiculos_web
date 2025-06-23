package com.example.demo.servicio;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.example.demo.modelo.Vehiculo;
import com.example.demo.repositorio.RepositorioVehiculo;

@Service
public class VehiculoServicio {
	@Autowired
    private RepositorioVehiculo repositorio;
	
	public Vehiculo guardarVehiculo(Vehiculo vehiculo) {
        return repositorio.save(vehiculo);
    }	
	
	public List<Vehiculo> buscarPorTipoYEstado(String tipo, String estadoVehiculo) {
        return repositorio.findByTipoAndEstadoVehiculo(tipo, estadoVehiculo);
    }

	public boolean actualizarEstadoVehiculo(String placa, String nuevoEstado) {
	    Optional<Vehiculo> vehiculoOptional = repositorio.findById(placa);

	    if (vehiculoOptional.isPresent()) {
	        Vehiculo vehiculo = vehiculoOptional.get();
	        vehiculo.setEstadoVehiculo(nuevoEstado);
	        repositorio.save(vehiculo);
	        return true;
	    } else {
	        return false;
	    }
	}
	
	public List<Vehiculo> buscarPorEstado(String estadoVehiculo) {
        return repositorio.findByEstadoVehiculo(estadoVehiculo);
	}
	
	public List<Vehiculo> obtenerTodos() {
	    return repositorio.findAll();
	}
	
	
}
