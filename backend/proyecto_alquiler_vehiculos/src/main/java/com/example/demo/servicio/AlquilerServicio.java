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
	
	//By JOHN DEIVID Servicio utilizado por Usuario para cancelar alquiler
	/*public boolean cancelarAlquilerPendiente(Long idAlquiler, Long idUsuario) {
        Optional<Alquiler> alquilerOptional = repositorio.findById(idAlquiler);

        if (alquilerOptional.isPresent()) {
            Alquiler alquiler = alquilerOptional.get();

            if (alquiler.getUsuario().getIdUsuario().equals(idUsuario)
                    && "pendiente de entrega".equalsIgnoreCase(alquiler.getEstado())) {

                // Reutilizamos tu método existente
                return actualizarEstadoAlquiler(idAlquiler, "cancelado");
            }
        }

        return false;
    }*/
	
	public boolean cancelarAlquilerPendiente(Long idAlquiler, Long idUsuario) {
	    Optional<Alquiler> alquilerOptional = repositorio.findById(idAlquiler);

	    if (alquilerOptional.isPresent()) {
	        Alquiler alquiler = alquilerOptional.get();

	        if (alquiler.getUsuario().getIdUsuario().equals(idUsuario)
	                && "pendiente de entrega".equalsIgnoreCase(alquiler.getEstado())) {

	            // Cambiar estado del vehículo a "disponible"
	            Vehiculo vehiculo = alquiler.getVehiculo();
	            vehiculo.setEstadoVehiculo("disponible");
	            repositorioVehiculo.save(vehiculo);

	            // Reutilizar tu método para actualizar el estado del alquiler
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
	
	//BY JOHN DEIVID: CALCULAR COSTO EXTRA Y ACTUALIZAR ESTADO DEL VEHICULO A DISPONIBLE
	@Transactional
	public Map<String, Object> procesarEntregaYCalcularCosto(Long idAlquiler, String fechaEntregaRealStr, Long idAdmin) throws ParseException {
	    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	    Date fechaEntregaReal = sdf.parse(fechaEntregaRealStr);

	    Optional<Alquiler> alquilerOpt = repositorio.findById(idAlquiler);
	    if (!alquilerOpt.isPresent()) {
	        throw new RuntimeException("No se encontró el alquiler con ID: " + idAlquiler);
	    }

	    Alquiler alquiler = alquilerOpt.get();
	    alquiler.setFechaEntregaReal(fechaEntregaReal);

	    Date fechaPactada = alquiler.getFechaEntrega();
	    long diffDias = (fechaEntregaReal.getTime() - fechaPactada.getTime()) / (1000 * 60 * 60 * 24);

	    double costoExtra = 0.0;
	    if (diffDias > 0) {
	        double valorDiario = alquiler.getVehiculo().getValorAlquilerVehiculo();
	        costoExtra = valorDiario * diffDias;
	    }

	    alquiler.setCostoExtra(costoExtra);
	    alquiler.setValorTotalAlquiler(alquiler.getValorAlquiler() + costoExtra);
	    alquiler.setEstado("devuelto");

	    repositorio.save(alquiler);
	    vehiculoServicio.actualizarEstadoVehiculo(alquiler.getVehiculo().getPlaca(), "disponible");

	    // Registrar la gestión del alquiler
	    gestionAlquilerServicio.registrarGestion(idAdmin, idAlquiler, fechaEntregaReal);

	    // Armar la respuesta
	    Map<String, Object> respuesta = new HashMap<>();
	    respuesta.put("idAlquiler", alquiler.getIdAlquiler());
	    respuesta.put("vehiculo", alquiler.getVehiculo());
	    respuesta.put("costoExtra", costoExtra);
	    respuesta.put("valorAlquiler", alquiler.getValorAlquiler());
	    respuesta.put("valorTotalAlquiler", alquiler.getValorTotalAlquiler());
	    respuesta.put("fechaEntregaReal", fechaEntregaReal);

	    return respuesta;
	}




}
