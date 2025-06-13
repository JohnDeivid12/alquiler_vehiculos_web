package com.example.demo.controlador;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.servicio.AlquilerServicio;


@RestController
@RequestMapping("/alquiler")
public class AlquilerControlador {
	 @Autowired
	    private AlquilerServicio servicio;

}
