package com.artistas.api.dao;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import com.artistas.api.Models.Servicio;

public interface IServicioDao extends CrudRepository<Servicio, Long> {
	public Optional<Servicio> findByServiceId(Long serviceId);
	public List<Servicio> findByClientId(Long clientId);
	public List<Servicio> findByEmpleadoId(Long empleadoId);
	
	@Query(value = "select s from Servicio s where s.fecha > :fechaInicio AND s.fecha < :fechaFin")
	public List<Servicio> findByFecha(@Param("fechaInicio")Long fechaInicio, @Param("fechaFin")Long fechaFin);
}