package com.artistas.api.dao;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.artistas.api.Models.CierreCaja;

public interface ICierreCajaDao extends CrudRepository<CierreCaja, Long> {
	public Optional<CierreCaja> findByCajaId(Long cajaId);
}
