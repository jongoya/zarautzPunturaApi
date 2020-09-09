package com.artistas.api.dao;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.artistas.api.Models.Venta;

public interface IVentaDao extends CrudRepository<Venta, Long> {
	public Optional<Venta> findByVentaId(Long ventaId);
	public ArrayList<Venta> findByCestaId(Long cestaId);
}
