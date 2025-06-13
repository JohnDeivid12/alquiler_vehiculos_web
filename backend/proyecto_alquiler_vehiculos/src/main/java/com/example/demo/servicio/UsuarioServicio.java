package com.example.demo.servicio;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.modelo.Usuario;
import com.example.demo.repositorio.RepositorioUsuario;

@Service
public class UsuarioServicio {

    @Autowired
    private RepositorioUsuario repositorio;

    public Usuario guardarUsuario(Usuario usuario) {
        return repositorio.save(usuario);
    }

    public List<Usuario> obtenerTodos() {
        return repositorio.findAll();
    }
}

