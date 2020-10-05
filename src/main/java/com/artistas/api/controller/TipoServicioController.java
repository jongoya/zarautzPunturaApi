package com.artistas.api.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.artistas.api.Commons.CommonFunctions;
import com.artistas.api.Models.Servicio;
import com.artistas.api.Models.TipoServicio;
import com.artistas.api.security.JwtValidator;
import com.artistas.api.services.ILoginService;
import com.artistas.api.services.ITipoServicioService;
import com.artistas.api.Commons.Constants;

@RestController
@RequestMapping("/api")
public class TipoServicioController {
	
	@Autowired
	public ITipoServicioService tipoServicioService;
	
	@Autowired
	private ILoginService loginService;
	
	@Autowired
	private JwtValidator validator;
	
	@GetMapping("/get_tipo_servicios")
	public ResponseEntity<ArrayList<TipoServicio>> getTipoServicios(@RequestHeader(Constants.authorizationHeaderKey) String token) {
		if (!CommonFunctions.hasTokenAuthorization(token, validator,  loginService)) {
			return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
		}
		
		ArrayList<TipoServicio> servicios = tipoServicioService.findAll();
		return new ResponseEntity<>(servicios, HttpStatus.OK);
	}
	
	@GetMapping("/get_tipo_servicio/{id}")
	public ResponseEntity<?> getTipoServicio(@RequestHeader(Constants.authorizationHeaderKey) String token, @PathVariable(value = "id")Long id) {
		if (!CommonFunctions.hasTokenAuthorization(token, validator,  loginService)) {
			return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
		}
		
		TipoServicio servicio = tipoServicioService.findByTipoServicioId(id);
		
		if (servicio == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		} else {
			return new ResponseEntity<>(servicio, HttpStatus.OK);
		}
	}
	
	@PostMapping("/save_tipo_servicio")
	public ResponseEntity<TipoServicio> saveTipoServicio(@RequestHeader(Constants.authorizationHeaderKey) String token, @RequestBody TipoServicio tipoServicio) {
		if (!CommonFunctions.hasTokenAuthorization(token, validator,  loginService)) {
			return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
		}
		
		TipoServicio resultado = tipoServicioService.saveTipoServicio(tipoServicio);
		return new ResponseEntity<>(resultado, HttpStatus.CREATED);
	}
	
	@PostMapping("/save_tipo_servicios")
	public ResponseEntity<ArrayList<TipoServicio>> saveTipoServicios(@RequestHeader(Constants.authorizationHeaderKey) String token, @RequestBody ArrayList<TipoServicio> tipoServicios) {
		if (!CommonFunctions.hasTokenAuthorization(token, validator,  loginService)) {
			return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
		}
		
		ArrayList<TipoServicio> resultado = tipoServicioService.saveTipoServicios(tipoServicios);
		return new ResponseEntity<>(resultado, HttpStatus.CREATED);
	}
	
	@PutMapping("/update_tipo_servicio")
	public ResponseEntity<TipoServicio> updateServicio(@RequestHeader(Constants.authorizationHeaderKey) String token, @RequestBody TipoServicio servicio) {
		if (!CommonFunctions.hasTokenAuthorization(token, validator,  loginService)) {
			return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
		}
		
		if (tipoServicioService.findByTipoServicioId(servicio.getServicioId()) == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		} else {
			TipoServicio resultado = tipoServicioService.updateTipoServicio(servicio);
			return new ResponseEntity<>(resultado, HttpStatus.OK);
		}
	}
}
