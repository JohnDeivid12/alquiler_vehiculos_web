package com.example.demo.servicio;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.demo.modelo.Admin;
import com.example.demo.modelo.Alquiler;
import com.example.demo.modelo.Usuario;
import com.example.demo.modelo.Vehiculo;
import com.example.demo.repositorio.RepositorioAdmin;


@Service
public class AdminServicio {
	
	@Autowired
    private RepositorioAdmin repositorio;
	
	 @Autowired
	    private AlquilerServicio alquilerServicio;
	
	public Admin guardarAdmin(Admin admin) {
        return repositorio.save(admin);
    }

	 public List<Vehiculo> obtenerVehiculosConAlquilerPendiente() {
	        return alquilerServicio.buscarVehiculosPorEstadoAlquiler("pendiente de entrega");
	    }
	 
	public Admin iniciarSesion(String usuarioAdmin, String contrasena_admin) {
        Admin admin = repositorio.findByUsuarioAdmin(usuarioAdmin);
        
        if (admin != null && admin.getContrasena_admin().equals(contrasena_admin)) {
            return admin;
        }
        
        return null;
    }
	
	//Cambiar estado de alquiler del vehiculo a entregado
    public boolean cambiarEstadoAlquilerComoAdminPorPlaca(String placa) {
        Optional<Alquiler> alquiler = alquilerServicio.buscarAlquilerPendientePorPlaca(placa);

        if (alquiler.isPresent()) {
            return alquilerServicio.actualizarEstadoAlquiler(alquiler.get().getId_alquiler(), "entregado");
        }

        return false;
    }
    
    

	
    }

