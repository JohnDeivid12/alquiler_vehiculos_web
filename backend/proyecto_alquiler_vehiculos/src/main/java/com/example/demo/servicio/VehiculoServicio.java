package com.example.demo.servicio;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.modelo.Vehiculo;
import com.example.demo.repositorio.RepositorioVehiculo;

@Service
public class VehiculoServicio {
	@Autowired
    private RepositorioVehiculo repositorio;
	
	
	public Vehiculo guardarVehiculo(Vehiculo vehiculo) {
        return repositorio.save(vehiculo);
    }	
	
	//BALLEST
	public List<Vehiculo> obtenerTodos() {
	    return repositorio.findAll();
	}
	
	//Buscar vehiculos disponibles
	public List<Vehiculo> buscarPorEstado(String estadoVehiculo) {
        return repositorio.findByEstadoVehiculo(estadoVehiculo);
	}
	
	//Buscar vehiculos de un tipo disponibles
	public List<Vehiculo> buscarPorTipoYEstado(String tipo, String estadoVehiculo) {
        return repositorio.findByTipoAndEstadoVehiculo(tipo, estadoVehiculo);
    }
	

	//Actualizar estado de vehiculo
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

	
}
