package com.artistas.api.services;

import java.util.ArrayList;

import com.artistas.api.Models.Venta;

public interface IVentaService {
	public ArrayList<Venta> findAll();
	public Venta saveVenta(Venta venta);
	public Venta findByVentaId(Long ventaId);
	public Venta updateVenta(Venta venta);
	public void deleteVenta(Long ventaId);
	public ArrayList<Venta> findByCestaId(Long cestaId);
	public ArrayList<Venta> saveVentas(ArrayList<Venta> ventas);
	public void deleteVentas(ArrayList<Venta> ventas);
}
