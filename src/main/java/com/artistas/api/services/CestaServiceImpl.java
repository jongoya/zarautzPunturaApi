package com.artistas.api.services;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.artistas.api.Models.Cesta;
import com.artistas.api.dao.ICestaDao;

@Service
public class CestaServiceImpl implements ICestaService {
	
	@Autowired
	private ICestaDao cestaDao;

	@Override
	public ArrayList<Cesta> findAll() {
		return (ArrayList<Cesta>) cestaDao.findAll();
	}

	@Override
	public Cesta saveCesta(Cesta cesta) {
		return cestaDao.save(cesta);
	}

	@Override
	public Cesta findByCestaId(Long cestaId) {
		return cestaDao.findByCestaId(cestaId).orElse(null);
	}

	@Override
	public Cesta updateCesta(Cesta cesta) {
		return cestaDao.save(cesta);
	}

	@Override
	public void deleteCesta(Long cestaId) {
		cestaDao.deleteById(cestaId);
	}

	@Override
	public void deleteCestas(ArrayList<Cesta> cestas) {
		cestaDao.deleteAll(cestas);
	}
}
