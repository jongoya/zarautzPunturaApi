package com.artistas.api.services;

import java.util.ArrayList;

import com.artistas.api.Models.TipoServicio;

public interface ITipoServicioService {
	public ArrayList<TipoServicio> findAll();
	public TipoServicio saveTipoServicio(TipoServicio servicio);
	public ArrayList<TipoServicio> saveTipoServicios(ArrayList<TipoServicio> tipoServicios);
	public TipoServicio findByTipoServicioId(Long id);
	public void deleteTipoServicios(ArrayList<TipoServicio> tipoServicios);
}
