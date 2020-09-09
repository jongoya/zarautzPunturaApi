package com.artistas.api.services;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.artistas.api.Models.ProductoModel;
import com.artistas.api.dao.IProductoDao;

@Service
public class ProductoServiceImpl implements IProductoService {

	@Autowired
	private IProductoDao productoDao;
	
	@Override
	public ArrayList<ProductoModel> findAll() {
		return (ArrayList<ProductoModel>) productoDao.findAll();
	}

	@Override
	public ProductoModel saveProducto(ProductoModel producto) {
		return productoDao.save(producto);
	}

	@Override
	public ProductoModel findByProductoId(Long productoId) {
		return productoDao.findByProductoId(productoId).orElse(null);
	}

	@Override
	public ProductoModel findByCodigoBarras(String codigoBarras) {
		return productoDao.findByCodigoBarras(codigoBarras).orElse(null);
	}

	@Override
	public ProductoModel updateProducto(ProductoModel producto) {
		return productoDao.save(producto);
	}

	@Override
	public void deleteProducto(Long productoId) {
		productoDao.deleteById(productoId);
	}

	@Override
	public void deleteProductos(ArrayList<ProductoModel> productos) {
		productoDao.deleteAll(productos);
	}

}
