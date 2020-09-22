package com.artistas.api.dao;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.artistas.api.Models.Servicio;
import com.artistas.api.Models.SistemasEquilibrio;


public interface ISistemaDao extends CrudRepository<SistemasEquilibrio, Long> {
	public Optional<Servicio> findBySistemaId(Long serviceId);

}
