package com.artistas.api.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.artistas.api.Commons.CommonFunctions;
import com.artistas.api.Models.CierreCaja;
import com.artistas.api.security.JwtValidator;
import com.artistas.api.services.ICierreCajaService;
import com.artistas.api.services.ILoginService;
import com.artistas.api.Commons.Constants;

@RestController
@RequestMapping("/api")
public class CierreCajaController {

	@Autowired
	public ICierreCajaService cierreCajaService;
	
	@Autowired
	private ILoginService loginService;
	
	@Autowired
	private JwtValidator validator;
	
	@GetMapping("/get_cierre_cajas")
	public ResponseEntity<ArrayList<CierreCaja>> getCierreCajas(@RequestHeader(Constants.authorizationHeaderKey) String token) {
		if (!CommonFunctions.hasTokenAuthorization(token, validator,  loginService)) {
			return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
		}
		
		ArrayList<CierreCaja> cierreCajas = cierreCajaService.findAll();
		return new ResponseEntity<>(cierreCajas, HttpStatus.OK);
	}
	
	@PostMapping("/save_cierre_caja")
	public ResponseEntity<CierreCaja> saveCierreCaja(@RequestHeader(Constants.authorizationHeaderKey) String token, @RequestBody CierreCaja cierreCaja) {
		if (!CommonFunctions.hasTokenAuthorization(token, validator,  loginService)) {
			return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
		}
		
		CierreCaja resultado = cierreCajaService.saveCierreCaja(cierreCaja);
		return new ResponseEntity<>(resultado, HttpStatus.CREATED);
	}
	
	@PostMapping("/save_cierre_cajas")
	public ResponseEntity<ArrayList<CierreCaja>> saveCierreCajas(@RequestHeader(Constants.authorizationHeaderKey) String token, @RequestBody ArrayList<CierreCaja> cierreCajas) {
		if (!CommonFunctions.hasTokenAuthorization(token, validator,  loginService)) {
			return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
		}
		
		ArrayList<CierreCaja> resultado = cierreCajaService.saveCierreCajas(cierreCajas);
		return new ResponseEntity<>(resultado, HttpStatus.CREATED);
	}
}
