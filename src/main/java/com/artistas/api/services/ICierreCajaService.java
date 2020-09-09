package com.artistas.api.services;

import java.util.ArrayList;

import com.artistas.api.Models.CierreCaja;

public interface ICierreCajaService {
	public ArrayList<CierreCaja> findAll();
	public ArrayList<CierreCaja> saveCierreCajas(ArrayList<CierreCaja> cierreCajas);
	public CierreCaja saveCierreCaja(CierreCaja cierreCaja);
	public void deleteCierreCaja(CierreCaja cierreCaja);
	public void deleteCierreCajas(ArrayList<CierreCaja> cierreCajas);
}
