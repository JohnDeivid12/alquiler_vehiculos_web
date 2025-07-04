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

import com.example.demo.AlquilerVehiculosApplication;
import com.example.demo.modelo.Alquiler;
import com.example.demo.modelo.Usuario;
import com.example.demo.modelo.Vehiculo;
import com.example.demo.repositorio.RepositorioUsuario;
import com.example.demo.servicio.AlquilerServicio;
import com.example.demo.servicio.UsuarioServicio;
import com.example.demo.servicio.VehiculoServicio;

@RestController
@RequestMapping("/usuario")
@CrossOrigin(origins = "http://localhost:4200")
public class UsuarioControlador {

    @Autowired
    private UsuarioServicio servicio;
    
    @Autowired
    private VehiculoServicio vehiculoServicio;
    
    @Autowired
    private AlquilerServicio alquilerServicio;
    
    @Autowired
    private UsuarioServicio usuarioServicio;

  //Registro usuario
    @PostMapping("/guardarUsuario")
    public Usuario guardarUsuario(@RequestBody Usuario usuario) {
        return servicio.guardarUsuario(usuario);
    }

    @GetMapping("/listarUsuarios")
    public List<Usuario> listarUsuarios() {
        return servicio.obtenerTodos();
    }
    

  //UsuarioControlador
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
    
    //Ver vehiculos disponibles
    @GetMapping("/buscarVehiculosPorTipoYEstado")
    public ResponseEntity<List<Vehiculo>> buscarVehiculosPorTipoYEstado(@RequestParam String tipo, @RequestParam(defaultValue = "disponible") String estado) {

        List<Vehiculo> vehiculos = vehiculoServicio.buscarPorTipoYEstado(tipo, estado);

        if (vehiculos.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(vehiculos);
    }
    
    //By JOHN DEIVID buscar alquileres de un usuario por su correo
    @GetMapping("/verAlquileres")
    public ResponseEntity<?> verAlquileresPorCorreo(@RequestParam String correo) {
        try {
            List<Alquiler> alquileres = usuarioServicio.obtenerAlquileresPorCorreo(correo);
            return ResponseEntity.ok(alquileres);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("No se encontraron alquileres para el correo: " + correo);
        }
    }

    @PostMapping("/cancelarAlquiler")
    public ResponseEntity<String> cancelarAlquiler(
            @RequestParam Long idAlquiler,
            @RequestParam String correo) {

        boolean cancelado = usuarioServicio.cancelarAlquilerPorCorreo(idAlquiler, correo);

        if (cancelado) {
            return ResponseEntity.ok("El alquiler ha sido cancelado correctamente.");
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("No se pudo cancelar el alquiler. Verifica que esté pendiente y sea tuyo.");
        }
    }
}

