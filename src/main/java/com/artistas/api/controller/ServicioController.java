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
import com.artistas.api.security.JwtValidator;
import com.artistas.api.services.ILoginService;
import com.artistas.api.services.IServicioService;
import com.artistas.api.Commons.Constants;

@RestController
@RequestMapping("/api")
public class ServicioController {
	
	@Autowired
	private IServicioService servicioService;
	
	@Autowired
	private ILoginService loginService;
	
	@Autowired
	private JwtValidator validator;
	
	@GetMapping("/get_servicios")
	public ResponseEntity<ArrayList<Servicio>> getServicios(@RequestHeader(Constants.authorizationHeaderKey) String token) {
		if (!CommonFunctions.hasTokenAuthorization(token, validator,  loginService)) {
			return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
		}
		
		ArrayList<Servicio> servicios = servicioService.findAll();
		return new ResponseEntity<>(servicios, HttpStatus.OK);
	}
	
	@PostMapping("/save_servicio")
	public ResponseEntity<Servicio> saveServicio(@RequestHeader(Constants.authorizationHeaderKey) String token, @RequestBody Servicio servicio) {
		if (!CommonFunctions.hasTokenAuthorization(token, validator,  loginService)) {
			return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
		}
		
		Servicio resultado = servicioService.saveServicio(servicio);
		return new ResponseEntity<>(resultado, HttpStatus.CREATED);
	}
	
	@PostMapping("/save_servicios")
	public ResponseEntity<ArrayList<Servicio>> saveServicios(@RequestHeader(Constants.authorizationHeaderKey) String token, @RequestBody ArrayList<Servicio> servicios) {
		if (!CommonFunctions.hasTokenAuthorization(token, validator,  loginService)) {
			return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
		}
		
		ArrayList<Servicio> resultados = servicioService.saveServicios(servicios);	
		return new ResponseEntity<>(resultados, HttpStatus.CREATED);
	}
	
	@GetMapping("/get_servicio/{id}")
	public ResponseEntity<?> getServicioByServicioId(@RequestHeader(Constants.authorizationHeaderKey) String token, @PathVariable(value = "id")Long id) {
		if (!CommonFunctions.hasTokenAuthorization(token, validator,  loginService)) {
			return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
		}
		
		Servicio servicio = servicioService.findByServicioId(id); 
		if (servicio == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		} else {
			return new ResponseEntity<>(servicio, HttpStatus.OK);
		}
	}
	
	@GetMapping("/get_servicios_client/{id}")
	public ResponseEntity<?> getServiciosByClientId(@RequestHeader(Constants.authorizationHeaderKey) String token, @PathVariable(value = "id")Long id) {
		if (!CommonFunctions.hasTokenAuthorization(token, validator,  loginService)) {
			return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
		}
		
		ArrayList<Servicio> servicios = servicioService.findByClienteId(id);
		return new ResponseEntity<>(servicios, HttpStatus.OK);
	}
	
	@GetMapping("/get_servicios_range/{fechaInicio}/{fechaFin}")
	public ResponseEntity<ArrayList<Servicio>> getServiciosByRange(@RequestHeader(Constants.authorizationHeaderKey) String token, 
			@PathVariable(value = "fechaInicio")Long fechaInicio, @PathVariable(value = "fechaFin")Long fechaFin) {
		if (!CommonFunctions.hasTokenAuthorization(token, validator,  loginService)) {
			return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
		}
		
		ArrayList<Servicio> servicios = servicioService.findByRange(fechaInicio, fechaFin);
		return new ResponseEntity<>(servicios, HttpStatus.OK);
	}
	
	@PutMapping("/update_servicio")
	public ResponseEntity<?> updateServicio(@RequestHeader(Constants.authorizationHeaderKey) String token, @RequestBody Servicio servicio) {
		if (!CommonFunctions.hasTokenAuthorization(token, validator,  loginService)) {
			return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
		}
		
		if (servicioService.findByServicioId(servicio.getServiceId()) == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		} else {
			Servicio resultado = servicioService.updateServicio(servicio);
			return new ResponseEntity<>(resultado, HttpStatus.OK);
		}
	}
	
	@PostMapping("/delete_servicio")
	public ResponseEntity<Void> deleteServicio(@RequestHeader(Constants.authorizationHeaderKey) String token, @RequestBody Servicio servicio) {
		if (!CommonFunctions.hasTokenAuthorization(token, validator,  loginService)) {
			return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
		}
		
		if (servicioService.findByServicioId(servicio.getServiceId()) != null) {
			servicioService.deleteServicio(servicio.getServiceId());
			return new ResponseEntity<>(HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
}
