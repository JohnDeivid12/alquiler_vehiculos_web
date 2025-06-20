package com.example.demo.servicio;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.modelo.Usuario;
import com.example.demo.modelo.Vehiculo;
import com.example.demo.repositorio.RepositorioUsuario;

@Service
public class UsuarioServicio {

    @Autowired
    private RepositorioUsuario repositorio;
    
    @Autowired
    private VehiculoServicio vehiculoServicio;
    
    @Autowired
    private AlquilerServicio alquilerServicio;

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
    
    //Cancelar alquiler
    public boolean cancelarAlquiler(Long idAlquiler, Long idUsuario) {
    	return alquilerServicio.cancelarAlquilerPendiente(idAlquiler, idUsuario);
    }


}

