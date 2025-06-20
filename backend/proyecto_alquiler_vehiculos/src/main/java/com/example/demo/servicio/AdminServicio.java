package com.example.demo.servicio;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.modelo.Admin;
import com.example.demo.modelo.Alquiler;
import com.example.demo.modelo.Vehiculo;
import com.example.demo.repositorio.RepositorioAdmin;
import com.example.demo.repositorio.RepositorioAlquiler;


@Service
public class AdminServicio {
	
	 @Autowired
	    private AlquilerServicio alquilerServicio;
	 
	 @Autowired
	    private RepositorioAlquiler repositorioAlquiler;
	 
	 @Autowired
	    private RepositorioAdmin repositorio;
	 
	 
	 //Guardar Admin
	 public Admin guardarAdmin(Admin admin) {
	         return repositorio.save(admin);
	     }
	 
	 public Admin iniciarSesion(String usuarioAdmin, String contrasena_admin) {
	        Admin admin = repositorio.findByUsuarioAdmin(usuarioAdmin);
	        
	        if (admin != null && admin.getContrasena_admin().equals(contrasena_admin)) {
	            return admin;
	        }
	        
	        return null;
	    }

	    //Ver vehiculos con alquiler pendiente 
	    public List<Vehiculo> obtenerVehiculosConAlquilerPendiente() {
	        return alquilerServicio.buscarVehiculosPorEstadoAlquiler("pendiente de entrega");
	    }
	    
	    //Cambiar estado de alquiler del vehiculo a entregado
	    public boolean cambiarEstadoAlquilerComoAdminPorPlaca(String placa) {
	        Optional<Alquiler> alquiler = alquilerServicio.buscarAlquilerPendientePorPlaca(placa);

	        if (alquiler.isPresent()) {
	            return alquilerServicio.actualizarEstadoAlquiler(alquiler.get().getIdAlquiler(), "entregado");
	        }

	        return false;
	    }
	    
		 //Valor total del alquiler si hay costo extra
	    public boolean calcularRecargoPorRetraso(Long idAlquiler, int diasRetraso, double tarifaDiaria) {
	        Optional<Alquiler> alquilerOptional = repositorioAlquiler.findById(idAlquiler);

	        if (alquilerOptional.isPresent()) {
	            Alquiler alquiler = alquilerOptional.get();

	            Date fechaEntrega = alquiler.getFechaEntrega();
	            Date fechaEntregaReal = alquiler.getFechaEntregaReal();

	            if (fechaEntregaReal != null && fechaEntregaReal.after(fechaEntrega)) {

	                double costoExtra = diasRetraso * tarifaDiaria;
	                alquiler.setCostoExtra(costoExtra);

	                // Se suma al valor total del alquiler
	                double totalFinal = alquiler.getValorAlquiler() + costoExtra;
	                alquiler.setValorTotalAlquiler(totalFinal);

	                repositorioAlquiler.save(alquiler);
	                return true;
	            }
	        }
	        return false;
	    }


}
