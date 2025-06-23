package com.example.demo.servicio;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
	    
	    @Autowired
		private GestionAlquilerServicio gestionAlquilerServicio;
	
	
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
	  @Transactional
		public Map<String, Object> procesarEntregaYCalcularCosto(Long idAlquiler, String fechaEntregaRealStr, Long idAdmin) throws ParseException {
		    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		    Date fechaEntregaReal = sdf.parse(fechaEntregaRealStr);

		    Optional<Alquiler> alquilerOpt = repositorio.findById(idAlquiler);
		    if (!alquilerOpt.isPresent()) {
		        throw new RuntimeException("No se encontró el alquiler con ID: " + idAlquiler);
		    }

		    Alquiler alquiler = alquilerOpt.get();
		    alquiler.setFecha_entrega_real(fechaEntregaReal);

		    Date fechaPactada = alquiler.getFecha_entrega();
		    long diffDias = (fechaEntregaReal.getTime() - fechaPactada.getTime()) / (1000 * 60 * 60 * 24);

		    double costoExtra = 0.0;
		    if (diffDias > 0) {
		        double valorDiario = alquiler.getVehiculo().getValor_alquiler_vehiculo();
		        costoExtra = valorDiario * diffDias;
		    }

		    alquiler.setCosto_extra(costoExtra);
		    alquiler.setValor_total_alquiler(alquiler.getValor_alquiler() + costoExtra);
		    alquiler.setEstado("devuelto");

		    repositorio.save(alquiler);
		    vehiculoServicio.actualizarEstadoVehiculo(alquiler.getVehiculo().getPlaca(), "Disponible");

		    // Registrar la gestión del alquiler
		    gestionAlquilerServicio.registrarGestion(idAdmin, idAlquiler, fechaEntregaReal);

		    // Armar la respuesta
		    Map<String, Object> respuesta = new HashMap<>();
		    respuesta.put("id_Alquiler", alquiler.getId_alquiler());
		    respuesta.put("vehiculo", alquiler.getVehiculo());
		    respuesta.put("costoExtra", costoExtra);
		    respuesta.put("valorAlquiler", alquiler.getValor_alquiler());
		    respuesta.put("valorTotalAlquiler", alquiler.getValor_total_alquiler());
		    respuesta.put("fechaEntregaReal", fechaEntregaReal);

		    return respuesta;
		}
	  
	// Servicio utilizado por Admin para buscar los vwhiculos no entregados
		public List<Vehiculo> buscarVehiculosPorEstadoAlquiler(String estado) {
		    return repositorio.findVehiculosByEstadoAlquiler(estado);
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
		
		public Optional<Alquiler> buscarAlquilerPendientePorPlaca(String placa) {
		    return repositorio.findByVehiculoPlacaAndEstado(placa, "pendiente de entrega");
		}



	}


