package com.example.demo.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.modelo.Usuario;

	@Repository
	public interface RepositorioUsuario extends JpaRepository<Usuario, Long> { 
		//RepositorioUsuario
		Usuario findByCorreo(String correo);
			    boolean existsByCorreo(String correo);
		
	}

