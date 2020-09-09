package com.artistas.api.dao;

import java.util.Optional;
import org.springframework.data.repository.CrudRepository;
import com.artistas.api.Models.Empleado;

public interface IEmpleadoDao extends CrudRepository<Empleado, Long> {
	public Optional<Empleado> findByEmpleadoId(Long empleadoId);
}
