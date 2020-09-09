package com.artistas.api.dao;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.artistas.api.Models.Cesta;

public interface ICestaDao extends CrudRepository<Cesta, Long> {
	public Optional<Cesta> findByCestaId(Long cestaId);
}
