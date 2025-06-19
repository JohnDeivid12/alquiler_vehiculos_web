package com.example.demo.controlador;

import java.util.HashMap;
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
import com.example.demo.modelo.Admin;
import com.example.demo.modelo.Usuario;
import com.example.demo.modelo.Vehiculo;
import com.example.demo.servicio.AdminServicio;
import com.example.demo.servicio.VehiculoServicio;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/admin")

public class AdminControlador {
	 @Autowired
	    private AdminServicio adminServicio;
	 
	 @Autowired
	 private VehiculoServicio servicioV;
	 
	 

	@PostMapping("/guardarAdmin")
	    public Admin guardarAdmin(@RequestBody Admin admin) {
	        return adminServicio.guardarAdmin(admin);
	    
	}
	
	@GetMapping("/vehiculos-disponibles")
	public List<Vehiculo> obtenerDisponibles() {
	    return servicioV.obtenerVehiculosDisponibles(); // <-- reutilizando el mismo método del servicio
	}
	
	 @PostMapping("/actualizarEstadoDesdeAdmin")
	    public ResponseEntity<String> actualizarEstadoVehiculo(@RequestParam String placa, @RequestParam String nuevoEstado) {
	        boolean actualizado = servicioV.actualizarEstadoVehiculo(placa, nuevoEstado);

	        if (actualizado) {
	            return ResponseEntity.ok("Estado del vehículo actualizado correctamente.");
	        } else {
	            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Vehículo no encontrado con la placa: " + placa);
	        }
	    }

	//Ver vehiculos con alquiler pendiente 
		/* @GetMapping("/vehiculosPendientes")
		 public ResponseEntity<List<Vehiculo>> vehiculosConAlquilerPendiente() {
		     List<Vehiculo> vehiculos = adminServicio.obtenerVehiculosConAlquilerPendiente();

		     if (vehiculos.isEmpty()) {
		         return ResponseEntity.noContent().build(); // 204 No Content
		     }

		     return ResponseEntity.ok(vehiculos);
		 }
	
	//@GetMapping("/disponibles")
    //public List<Admin> obtenerDisponibles() {
      //  return servicio.obtenerDisponibles();
    }
	*/
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
	 /* @PostMapping("/iniciarSesion")
    public ResponseEntity<?> iniciarSesion(@RequestParam String correo, @RequestParam String contrasena_usuario) {
        Usuario usuario = servicio.iniciarSesion(correo, contrasena_usuario);
        
        if (usuario != null) {
            return ResponseEntity.ok(usuario);
        } 	else {
            	return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body("Credenciales inválidas");
        	}
    }  */
	 }
	


