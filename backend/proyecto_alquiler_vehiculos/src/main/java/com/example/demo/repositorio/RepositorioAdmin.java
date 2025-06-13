package com.example.demo.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.modelo.Admin;
import com.example.demo.modelo.Usuario;

@Repository
public interface RepositorioAdmin extends JpaRepository<Admin, Long> { 
	
}
