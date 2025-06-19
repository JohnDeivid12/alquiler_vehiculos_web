package com.example.demo.servicio;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.demo.modelo.Admin;
import com.example.demo.modelo.Usuario;
import com.example.demo.repositorio.RepositorioAdmin;


@Service
public class AdminServicio {
	
	@Autowired
    private RepositorioAdmin repositorio;
	
	public Admin guardarAdmin(Admin admin) {
        return repositorio.save(admin);
    }

	 /*public List<Admin> obtenerDisponibles() {
	        return repositorio.findByDisponibleTrue();
	    }
	    */
	public Admin iniciarSesion(String usuarioAdmin, String contrasena_admin) {
        Admin admin = repositorio.findByUsuarioAdmin(usuarioAdmin);
        
        if (admin != null && admin.getContrasena_admin().equals(contrasena_admin)) {
            return admin;
        }
        
        return null;
    }

	
    }

