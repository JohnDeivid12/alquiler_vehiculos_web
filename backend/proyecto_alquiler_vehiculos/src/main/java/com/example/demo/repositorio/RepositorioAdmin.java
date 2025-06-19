package com.example.demo.repositorio;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.modelo.Admin;
import com.example.demo.modelo.Usuario;




@Repository
public interface RepositorioAdmin extends JpaRepository<Admin, Long> { 
	
	// List<Admin> findByDisponibleTrue();
	Admin findByUsuarioAdmin(String usuarioAdmin);
    boolean existsByusuarioAdmin(String usuarioAdmin);
}
