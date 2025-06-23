package com.example.demo.servicio;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.example.demo.modelo.Alquiler;
import com.example.demo.modelo.Usuario;
import com.example.demo.modelo.Vehiculo;
import com.example.demo.repositorio.RepositorioAlquiler;
import com.example.demo.repositorio.RepositorioUsuario;

@Service
public class UsuarioServicio {

    @Autowired
    private RepositorioUsuario repositorio;
    
    @Autowired
    private VehiculoServicio vehiculoServicio;
    
    @Autowired
    private AlquilerServicio alquilerServicio;
    
    @Autowired
    private RepositorioAlquiler repositorioAlquiler;

    //Registro usuario
    public Usuario guardarUsuario(Usuario usuario) {
        return repositorio.save(usuario);
    }

    public List<Usuario> obtenerTodos() {
        return repositorio.findAll();
    }
    
  //servicioUsuario
    public Usuario iniciarSesion(String correo, String contrasena_usuario) {
            Usuario usuario = repositorio.findByCorreo(correo);
            
            if (usuario != null && usuario.getContrasena_usuario().equals(contrasena_usuario)) {
                return usuario;
            }
            
            return null;
        }
    
    //Ver vehiculos disponibles
    public List<Vehiculo> seleccionarVehiculo(String tipo, String estadoVehiculo) {
        return vehiculoServicio.buscarPorTipoYEstado(tipo, estadoVehiculo);
    }

    //By JOHN DEIVID buscar alquileres de un usuario por su correo
    public List<Alquiler> obtenerAlquileresPorCorreo(String correo) {
        Usuario usuario = repositorio.findByCorreo(correo);

        if (usuario == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuario no encontrado");
        }

        return repositorioAlquiler.findByUsuario(usuario);
    }

    public boolean cancelarAlquilerPorCorreo(Long idAlquiler, String correo) {
        Usuario usuario = repositorio.findByCorreo(correo);

        if (usuario == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuario no encontrado");
        }

        return alquilerServicio.cancelarAlquilerPendiente(idAlquiler, usuario.getIdUsuario());
    }
}

