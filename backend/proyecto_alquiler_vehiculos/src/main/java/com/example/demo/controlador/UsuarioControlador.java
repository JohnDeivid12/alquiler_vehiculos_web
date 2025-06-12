package com.example.demo.controlador;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.AlquilerVehiculosApplication;
import com.example.demo.modelo.Usuario;
import com.example.demo.repositorio.RepositorioUsuario;

@RestController

@RequestMapping("/usuario")

public class UsuarioControlador {
 
	    private final AlquilerVehiculosApplication alquilerVehiculosApplication;
        	
		@Autowired
		private RepositorioUsuario repositorio;
		
		UsuarioControlador(AlquilerVehiculosApplication alquilerVehiculosApplication) {
	        this.alquilerVehiculosApplication = alquilerVehiculosApplication;
	    }
		
		@PostMapping("/guardarUsuario")
		public Usuario guardarUsuario(@RequestBody Usuario u){
		    return this.repositorio.save(u);
		}

}
