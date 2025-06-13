package com.example.demo.controlador;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.servicio.AdminServicio;
import com.example.demo.servicio.UsuarioServicio;

@RestController
@RequestMapping("/admin")
public class AdminControlador {
	 @Autowired
	    private AdminServicio servicio;

}
