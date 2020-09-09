package com.artistas.api.dao;

import java.util.Optional;
import org.springframework.data.repository.CrudRepository;
import com.artistas.api.Models.ProductoModel;

public interface IProductoDao extends CrudRepository<ProductoModel, Long>  {
	public Optional<ProductoModel> findByProductoId(Long productoId);
	public Optional<ProductoModel> findByCodigoBarras(String codigoBarras);
}
