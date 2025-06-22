package com.example.demo.servicio;

import com.example.demo.modelo.Admin;
import com.example.demo.modelo.Alquiler;
import com.example.demo.modelo.GestionAlquiler;

import java.util.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.repositorio.RepositorioAdmin;
import com.example.demo.repositorio.RepositorioAlquiler;
import com.example.demo.repositorio.RepositorioGestionAlquiler;

@Service
public class GestionAlquilerServicio {

    @Autowired
    private RepositorioGestionAlquiler repositorioGestion;

    @Autowired
    private RepositorioAlquiler repositorioAlquiler;

    @Autowired
    private RepositorioAdmin repositorioAdmin;
    
    

    public void registrarGestion(Long idAdmin, Long idAlquiler, Date fechaGestion) {
        Optional<Alquiler> alquilerOpt = repositorioAlquiler.findById(idAlquiler);
        Optional<Admin> adminOpt = repositorioAdmin.findById(idAdmin);

        if (!alquilerOpt.isPresent()) {
            throw new RuntimeException("No se encontró el alquiler con ID: " + idAlquiler);
        }

        if (!adminOpt.isPresent()) {
            throw new RuntimeException("No se encontró el admin con ID: " + idAdmin);
        }

        GestionAlquiler gestion = new GestionAlquiler();
        gestion.setFecha_gestion(fechaGestion);
        gestion.setAlquiler(alquilerOpt.get());
        gestion.setAdmin(adminOpt.get());

        repositorioGestion.save(gestion);
    }
}

