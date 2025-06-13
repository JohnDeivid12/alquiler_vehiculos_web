package com.example.demo.servicio;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.repositorio.RepositorioAdmin;
import com.example.demo.repositorio.RepositorioUsuario;

@Service
public class AdminServicio {
	
	@Autowired
    private RepositorioAdmin repositorio;

}
