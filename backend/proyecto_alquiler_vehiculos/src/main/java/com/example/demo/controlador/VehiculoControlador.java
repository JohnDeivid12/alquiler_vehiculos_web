package com.example.demo.controlador;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
	 
	 @PostMapping("/actualizarEstado")
	 public ResponseEntity<String> actualizarEstadoVehiculo(@RequestParam String placa, @RequestParam String nuevoEstado) {

	     boolean actualizado = servicio.actualizarEstadoVehiculo(placa, nuevoEstado);

	     if (actualizado) {
	         return ResponseEntity.ok("Estado del vehículo actualizado correctamente.");
	     } else {
	         return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Vehículo no encontrado con la placa: " + placa);
	     }
	 }

}
