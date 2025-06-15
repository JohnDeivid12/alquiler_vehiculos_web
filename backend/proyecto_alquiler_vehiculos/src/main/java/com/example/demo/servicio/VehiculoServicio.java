package com.example.demo.servicio;

import java.util.List;

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

	
	
}
