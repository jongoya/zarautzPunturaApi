package com.artistas.api.services;

import java.util.ArrayList;

import com.artistas.api.Models.ProductoModel;


public interface IProductoService {
	public ArrayList<ProductoModel> findAll();
	public ProductoModel saveProducto(ProductoModel producto);
	public ProductoModel findByProductoId(Long productoId);
	public ProductoModel findByCodigoBarras(String codigoBarras);
	public ProductoModel updateProducto(ProductoModel producto);
	public void deleteProducto(Long productoId);
	public void deleteProductos(ArrayList<ProductoModel> productos);
}
