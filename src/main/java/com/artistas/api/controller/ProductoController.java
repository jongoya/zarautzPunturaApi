package com.artistas.api.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.artistas.api.Commons.CommonFunctions;
import com.artistas.api.Models.ProductoModel;
import com.artistas.api.security.JwtValidator;
import com.artistas.api.services.ILoginService;
import com.artistas.api.services.IProductoService;
import com.artistas.api.Commons.Constants;

@RestController
@RequestMapping("/api")
public class ProductoController {

	@Autowired
	private IProductoService productoService;
	
	@Autowired
	private ILoginService loginService;
	
	@Autowired
	private JwtValidator validator;
	
	@GetMapping("/get_productos")
	public ResponseEntity<ArrayList<ProductoModel>> getProductos(@RequestHeader(Constants.authorizationHeaderKey) String token) {
		if (!CommonFunctions.hasTokenAuthorization(token, validator,  loginService)) {
			return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
		}
		
		ArrayList<ProductoModel> productos = productoService.findAll();
		return new ResponseEntity<>(productos, HttpStatus.OK);
	}
	
	@PostMapping("/save_producto")
	public ResponseEntity<ProductoModel> saveProducto(@RequestHeader(Constants.authorizationHeaderKey) String token, @RequestBody ProductoModel producto) {
		if (!CommonFunctions.hasTokenAuthorization(token, validator,  loginService)) {
			return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
		}
		
		ProductoModel resultado = productoService.saveProducto(producto);
		return new ResponseEntity<>(resultado, HttpStatus.CREATED);
	}
	
	@PutMapping("/update_producto")
	public ResponseEntity<ProductoModel> updateProducto(@RequestHeader(Constants.authorizationHeaderKey) String token, @RequestBody ProductoModel producto) {
		if (!CommonFunctions.hasTokenAuthorization(token, validator,  loginService)) {
			return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
		}
		
		if (productoService.findByProductoId(producto.getProductoId()) == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		} else {
			ProductoModel resultado = productoService.updateProducto(producto);
			return new ResponseEntity<>(resultado, HttpStatus.OK);
		}
	}
	
	@PostMapping("/delete_producto")
	public ResponseEntity<Void> deleteProducto(@RequestHeader(Constants.authorizationHeaderKey) String token, @RequestBody ProductoModel producto) {
		if (!CommonFunctions.hasTokenAuthorization(token, validator,  loginService)) {
			return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
		}
		
		if (productoService.findByProductoId(producto.getProductoId()) != null) {
			productoService.deleteProducto(producto.getProductoId());
			return new ResponseEntity<>(HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
}
