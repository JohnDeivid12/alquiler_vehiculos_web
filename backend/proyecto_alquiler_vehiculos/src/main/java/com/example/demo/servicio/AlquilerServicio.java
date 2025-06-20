package com.example.demo.servicio;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.modelo.Alquiler;
import com.example.demo.modelo.Usuario;
import com.example.demo.modelo.Vehiculo;

import com.example.demo.repositorio.RepositorioAlquiler;
import com.example.demo.repositorio.RepositorioUsuario;
import com.example.demo.repositorio.RepositorioVehiculo;

import jakarta.transaction.Transactional;


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
	
	//Crear alquiler
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


		  if (repositorio.existsByVehiculoAndEstado(vehiculo, "pendiente de entrega")) {
			  throw new RuntimeException("El vehículo ya tiene un alquiler activo");
		  }


		  long diffInMillies = fechaEntrega.getTime() - fechaInicio.getTime();
		  long dias = diffInMillies / (1000 * 60 * 60 * 24);
		  if (dias <= 0) {
			  throw new RuntimeException("La fecha de entrega debe ser posterior a la fecha de inicio");
		  }


		  Double valorDiario = vehiculo.getValorAlquilerVehiculo();
		  Double valorTotal = valorDiario * dias;


		  Alquiler alquiler = new Alquiler();
		  alquiler.setUsuario(usuario);
		  alquiler.setVehiculo(vehiculo);
		  alquiler.setFechaInicio(fechaInicio);
		  alquiler.setFechaEntrega(fechaEntrega);
		  alquiler.setValorAlquiler(valorTotal);
		  alquiler.setEstado("pendiente de entrega");
		  alquiler.setCostoExtra(0.0);
		  alquiler.setValorTotalAlquiler(valorTotal);


		  Alquiler alquilerGuardado = repositorio.save(alquiler);


		  vehiculoServicio.actualizarEstadoVehiculo(placaVehiculo, "no disponible");

		  return alquilerGuardado;
}
	
	//Servicio utilizado por Usuario para cancelar alquiler
	public boolean cancelarAlquilerPendiente(Long idAlquiler, Long idUsuario) {
	    Optional<Alquiler> alquilerOptional = repositorio.findById(idAlquiler);

	    if (alquilerOptional.isPresent()) {
	        Alquiler alquiler = alquilerOptional.get();

	        if (alquiler.getUsuario().getIdUsuario().equals(idUsuario) &&
	            "pendiente de entrega".equalsIgnoreCase(alquiler.getEstado())) {

	            return actualizarEstadoAlquiler(idAlquiler, "cancelado");
	        }
	    }
	    return false;
	}
    // Servicio utilizado por Admin para buscar los vwhiculos no entregados
	public List<Vehiculo> buscarVehiculosPorEstadoAlquiler(String estado) {
	    return repositorio.findVehiculosByEstadoAlquiler(estado);
	}
	
	public Optional<Alquiler> buscarAlquilerPendientePorPlaca(String placa) {
	    return repositorio.findByVehiculoPlacaAndEstado(placa, "pendiente de entrega");
	}

	//Actualizar estado de alquiler
	public boolean actualizarEstadoAlquiler(Long idAlquiler, String nuevoEstado) {
	    Optional<Alquiler> alquilerOptional = repositorio.findById(idAlquiler);

	    if (alquilerOptional.isPresent()) {
	        Alquiler alquiler = alquilerOptional.get();
	        alquiler.setEstado(nuevoEstado);
	        repositorio.save(alquiler);
	        return true;
	    } else {
	        return false;
	    }

    }
}
