package com.example.demo.controlador;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.modelo.Usuario;
import com.example.demo.repositorio.RepositorioUsuario;
import com.example.demo.servicio.UsuarioServicio;

@RestController
@RequestMapping("/usuario")
@CrossOrigin(origins = "http://localhost:4200/")
public class UsuarioControlador {

    private final RepositorioUsuario repositorioUsuario;

    @Autowired
    private UsuarioServicio servicio;

    UsuarioControlador(RepositorioUsuario repositorioUsuario) {
        this.repositorioUsuario = repositorioUsuario;
    }

    @PostMapping("/guardarUsuario")
    public Usuario guardarUsuario(@RequestBody Usuario usuario) {
        return servicio.guardarUsuario(usuario);
    }

    @GetMapping("/listarUsuarios")
    public List<Usuario> listarUsuarios() {
        return servicio.obtenerTodos();
    }
    
    @PostMapping("/iniciarSesion")
    public ResponseEntity<?> iniciarSesion(@RequestParam String correo, @RequestParam String contrasena_usuario) {
        Usuario usuario = servicio.iniciarSesion(correo, contrasena_usuario);
        
        if (usuario != null) {
            return ResponseEntity.ok(usuario);
        } 	else {
            	return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body("Credenciales inválidas");
        	}
    }
} 


