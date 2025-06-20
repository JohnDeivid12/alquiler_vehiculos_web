package com.example.demo.repositorio;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.demo.modelo.Alquiler;
import com.example.demo.modelo.Usuario;
import com.example.demo.modelo.Vehiculo;

@Repository
public interface RepositorioAlquiler extends JpaRepository<Alquiler, Long> { 
	
	@Query("SELECT a.vehiculo FROM Alquiler a WHERE a.estado = :estado")
	List<Vehiculo> findVehiculosByEstadoAlquiler(@Param("estado") String estado);
	
	Optional<Alquiler> findByVehiculoPlacaAndEstado(String placa, String estado);
	
List<Alquiler> findByUsuario(Usuario usuario);
    
    /**
     * Busca alquileres por vehículo
     */
    List<Alquiler> findByVehiculo(Vehiculo vehiculo);
    
    /**
     * Busca alquileres activos por vehículo
     */
    List<Alquiler> findByVehiculoAndEstado(Vehiculo vehiculo, String estado);
    
    /**
     * Busca alquileres por estado
     */
    List<Alquiler> findByEstado(String estado);
    
    /**
     * Busca alquileres activos del usuario
     */
    List<Alquiler> findByUsuarioAndEstado(Usuario usuario, String estado);
    
    /**
     * Verifica si un vehículo tiene alquileres activos
     */
    boolean existsByVehiculoAndEstado(Vehiculo vehiculo, String estado);
	
} 