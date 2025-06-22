package com.example.demo.controlador;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

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

import com.example.demo.modelo.Alquiler;
import com.example.demo.servicio.AlquilerServicio;


@RestController
@RequestMapping("/alquiler")
@CrossOrigin(origins = "http://localhost:4200")

public class AlquilerControlador {
	 @Autowired
	    private AlquilerServicio servicio;
	 
	 @PostMapping("/alquilar")
	    public ResponseEntity<?> alquilarVehiculo(@RequestBody Map<String, Object> solicitudAlquiler) {
	        try {
	            String correo = (String) solicitudAlquiler.get("correoUsuario");
	            String placa = (String) solicitudAlquiler.get("placaVehiculo");
	            String fecha_inicio = (String) solicitudAlquiler.get("fechaInicio"); // formato: "yyyy-MM-dd"
	            String fechaEntregaStr = (String) solicitudAlquiler.get("fechaEntrega"); // formato: "yyyy-MM-dd"
	            
	            // Validar datos requeridos
	            if (correo == null || placa == null || 
	                fecha_inicio == null || fechaEntregaStr == null) {
	                return ResponseEntity.badRequest()
	                        .body("Todos los campos son requeridos: correoUsuario, placaVehiculo, fechaInicio, fechaEntrega");
	            }
	            
	            // Convertir fechas
	            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	            Date fechaInicio = sdf.parse(fecha_inicio);
	            Date fechaEntrega = sdf.parse(fechaEntregaStr);
	            
	            // Crear el alquiler
	            Alquiler alquiler = servicio.crearAlquiler(correo, placa, fechaInicio, fechaEntrega);
	            
	            return ResponseEntity.ok(alquiler);
	            
	        } catch (RuntimeException e) {
	            return ResponseEntity.badRequest().body("Error: " + e.getMessage());
	        } catch (Exception e) {
	            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
	                    .body("Error interno del servidor: " + e.getMessage());
	        }
	    }
	 
     //Actualizar estado de alquiler
	 @PostMapping("/actualizarEstado")
	    public ResponseEntity<String> actualizarEstadoAlquiler(@RequestParam Long idAlquiler, @RequestParam String nuevoEstado) {

	        boolean actualizado = servicio.actualizarEstadoAlquiler(idAlquiler, nuevoEstado);

	        if (actualizado) {
	            return ResponseEntity.ok("El estado del alquiler se actualizó correctamente.");
	        } else {
	            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
	                    .body("No fue posible actualizar el estado. Verifique el ID o si la transición es válida.");
	        }
	    }
	 
	 //BY JOHN DEIVID: CALCULAR COSTO EXTRA Y ACTUALIZAR ESTADO DEL VEHICULO A DISPONIBLE
	 @PostMapping("/devolucion")
	 public ResponseEntity<?> registrarDevolucion(@RequestParam Long idAlquiler, 
	                                              @RequestParam String fechaEntregaReal,
	                                              @RequestParam Long idAdmin) {
	     try {
	         Map<String, Object> respuesta = servicio.procesarEntregaYCalcularCosto(idAlquiler, fechaEntregaReal, idAdmin);
	         return ResponseEntity.ok(respuesta);
	     } catch (RuntimeException e) {
	         return ResponseEntity.badRequest().body("Error: " + e.getMessage());
	     } catch (Exception e) {
	         return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error interno: " + e.getMessage());
	     }
	 }
}
