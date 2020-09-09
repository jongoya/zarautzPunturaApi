package com.artistas.api.services;

import java.util.ArrayList;

import com.artistas.api.Models.Cesta;

public interface ICestaService {
	public ArrayList<Cesta> findAll();
	public Cesta saveCesta(Cesta cesta);
	public Cesta findByCestaId(Long cestaId);
	public Cesta updateCesta(Cesta cesta);
	public void deleteCesta(Long cestaId);
	public void deleteCestas(ArrayList<Cesta> cestas);
}