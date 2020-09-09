package com.artistas.api.dao;

import java.util.Optional;
import org.springframework.data.repository.CrudRepository;
import com.artistas.api.Models.TipoServicio;

public interface ITipoServicioDao extends CrudRepository<TipoServicio, Long> {
	public Optional<TipoServicio> findByServicioId(Long servicioId);
}
