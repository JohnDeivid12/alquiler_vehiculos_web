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

import com.example.demo.modelo.Admin;
import com.example.demo.modelo.Vehiculo;
import com.example.demo.servicio.AdminServicio;

@RestController
@RequestMapping("/admin")
@CrossOrigin(origins = "http://localhost:4200/")
public class AdminControlador {
	
	 @Autowired
	    private AdminServicio adminServicio;
	 
	 //Guardar Admin
	 @PostMapping("/guardarAdmin")
	    public Admin guardarAdmin(@RequestBody Admin admin) {
	        return adminServicio.guardarAdmin(admin);
	}
	 
	 @PostMapping("/iniciarSesion")
	    public ResponseEntity<?> iniciarSesion(@RequestParam String usuarioAdmin, @RequestParam String contrasena_admin) {
	        Admin a = adminServicio.iniciarSesion(usuarioAdmin, contrasena_admin);
	        
	        if (a != null) {
	            return ResponseEntity.ok(a);
	        } 	else {
	            	return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
	                    .body("Credenciales inválidas");
	        	}
	    }
	 
	 //Ver vehiculos con alquiler pendiente 
	 @GetMapping("/vehiculosPendientes")
	 public ResponseEntity<List<Vehiculo>> vehiculosConAlquilerPendiente() {
	     List<Vehiculo> vehiculos = adminServicio.obtenerVehiculosConAlquilerPendiente();

	     if (vehiculos.isEmpty()) {
	         return ResponseEntity.noContent().build(); // 204 No Content
	     }

	     return ResponseEntity.ok(vehiculos);
	 }

	 //Cambiar estado de alquiler del vehiculo a entregado
	 @PostMapping("/entregarVehiculo")
	 public ResponseEntity<String> entregarVehiculo(@RequestParam String placa) {
	     boolean entregado = adminServicio.cambiarEstadoAlquilerComoAdminPorPlaca(placa);

	     if (entregado) {
	         return ResponseEntity.ok("El alquiler fue marcado como entregado correctamente.");
	     } else {
	         return ResponseEntity.status(HttpStatus.NOT_FOUND)
	                 .body("No se encontró un alquiler pendiente con la placa proporcionada.");
	     }
	 }
     
	 //Valor total del alquiler si hay costo extra
	 @PostMapping("/calcularRecargo")
	 public ResponseEntity<String> calcularRecargo(
	         @RequestParam Long idAlquiler,
	         @RequestParam int diasRetraso,
	         @RequestParam double tarifaDiaria) {

	     boolean actualizado = adminServicio.calcularRecargoPorRetraso(idAlquiler, diasRetraso, tarifaDiaria);

	     if (actualizado) {
	         return ResponseEntity.ok("Recargo calculado y valor total actualizado correctamente.");
	     } else {
	         return ResponseEntity.status(HttpStatus.BAD_REQUEST)
	                 .body("No se pudo calcular el recargo. Verifica si la fecha de entrega real es posterior.");
	     }
	 }

	 
}
