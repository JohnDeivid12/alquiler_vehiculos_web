package com.example.demo.servicio;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.repositorio.RepositorioAlquiler;


@Service
public class AlquilerServicio {
	@Autowired
    private RepositorioAlquiler repositorio;

}
