package com.example.demo.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.modelo.Admin;

@Repository
public interface RepositorioAdmin extends JpaRepository<Admin, Long> { 
	    
		Admin findByUsuarioAdmin(String usuarioAdmin);
	    boolean existsByusuarioAdmin(String usuarioAdmin);
	}	
