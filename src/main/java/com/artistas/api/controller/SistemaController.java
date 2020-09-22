package com.artistas.api.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.artistas.api.Commons.CommonFunctions;
import com.artistas.api.Commons.Constants;
import com.artistas.api.Models.SistemasEquilibrio;
import com.artistas.api.security.JwtValidator;
import com.artistas.api.services.ILoginService;
import com.artistas.api.services.ISistemaService;

@RestController
@RequestMapping("/api")
public class SistemaController {
	
	@Autowired
	private JwtValidator validator;
	
	@Autowired
	private ILoginService loginService;
	
	@Autowired
	private ISistemaService sistemaService;

	@GetMapping("/get_sistemas")
	public ResponseEntity<ArrayList<SistemasEquilibrio>> getCestas(@RequestHeader(Constants.authorizationHeaderKey) String token) {
		if (!CommonFunctions.hasTokenAuthorization(token, validator,  loginService)) {
			return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
		}
		
		ArrayList<SistemasEquilibrio> sistemas = sistemaService.findAll();
		return new ResponseEntity<>(sistemas, HttpStatus.OK);
	}
}
