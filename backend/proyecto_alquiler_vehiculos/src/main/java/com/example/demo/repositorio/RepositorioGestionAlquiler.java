package com.example.demo.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.demo.modelo.GestionAlquiler;

@Repository
public interface RepositorioGestionAlquiler extends JpaRepository<GestionAlquiler, Long> {
}