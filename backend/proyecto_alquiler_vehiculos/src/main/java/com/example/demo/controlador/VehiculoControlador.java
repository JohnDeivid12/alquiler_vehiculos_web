package com.example.demo.controlador;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.modelo.Vehiculo;
import com.example.demo.servicio.VehiculoServicio;

@RestController
@RequestMapping("/vehiculo")

public class VehiculoControlador {
	 @Autowired
	    private VehiculoServicio servicio;
	 
	 @PostMapping("/guardarVehiculo")
	    public Vehiculo guardarVehiculo(@RequestBody Vehiculo vehiculo) {
	        return servicio.guardarVehiculo(vehiculo);
	    }
	 
	 @GetMapping("/buscarDisponiblesPorTipo")
	    public List<Vehiculo> buscarDisponiblesPorTipo(@RequestParam String tipo) {
	        return servicio.buscarPorTipoYEstado(tipo, "disponible");
	    }

}
