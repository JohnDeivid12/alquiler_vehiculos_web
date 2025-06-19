package com.example.demo.servicio;


import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.modelo.Alquiler;
import com.example.demo.modelo.Usuario;
import com.example.demo.modelo.Vehiculo;
import com.example.demo.repositorio.RepositorioAlquiler;
import com.example.demo.repositorio.RepositorioUsuario;
import com.example.demo.repositorio.RepositorioVehiculo;


@Service
public class AlquilerServicio {
		@Autowired
    	private RepositorioAlquiler repositorio;
	
	    
	    @Autowired
	    private RepositorioVehiculo repositorioVehiculo;
	    
	    @Autowired
	    private RepositorioUsuario repositorioUsuario;
	    
	    @Autowired
	    private VehiculoServicio vehiculoServicio;
	
	
	
	  public Alquiler crearAlquiler(String correo, String placaVehiculo, 
              java.util.Date fechaInicio, java.util.Date fechaEntrega) {


		  Usuario usuario = repositorioUsuario.findByCorreo(correo);
		  if (usuario == null) {
			  throw new RuntimeException("Usuario no encontrado con correo: " + correo);
		  }


		  Optional<Vehiculo> vehiculoOpt = repositorioVehiculo.findById(placaVehiculo);
		  if (!vehiculoOpt.isPresent()) {
			  throw new RuntimeException("Vehículo no encontrado con placa: " + placaVehiculo);
		  }

		  Vehiculo vehiculo = vehiculoOpt.get();


		  if (!"disponible".equalsIgnoreCase(vehiculo.getEstadoVehiculo())) {
			  throw new RuntimeException("El vehículo no está disponible para alquiler");
		  }


		  if (repositorio.existsByVehiculoAndEstado(vehiculo, "activo")) {
			  throw new RuntimeException("El vehículo ya tiene un alquiler activo");
		  }


		  long diffInMillies = fechaEntrega.getTime() - fechaInicio.getTime();
		  long dias = diffInMillies / (1000 * 60 * 60 * 24);
		  if (dias <= 0) {
			  throw new RuntimeException("La fecha de entrega debe ser posterior a la fecha de inicio");
		  }


		  Double valorDiario = vehiculo.getValor_alquiler_vehiculo();
		  Double valorTotal = valorDiario * dias;


		  Alquiler alquiler = new Alquiler();
		  alquiler.setUsuario(usuario);
		  alquiler.setVehiculo(vehiculo);
		  alquiler.setFecha_inicio(fechaInicio);
		  alquiler.setFecha_entrega(fechaEntrega);
		  alquiler.setValor_alquiler(valorTotal);
		  alquiler.setEstado("pendiente de entrega");
		  alquiler.setCosto_extra(0.0);
		  alquiler.setValor_total_alquiler(valorTotal);


		  Alquiler alquilerGuardado = repositorio.save(alquiler);


		  vehiculoServicio.actualizarEstadoVehiculo(placaVehiculo, "no disponible");

		  return alquilerGuardado;
}

}
