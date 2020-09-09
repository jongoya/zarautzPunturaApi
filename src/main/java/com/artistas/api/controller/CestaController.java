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

import com.artistas.api.Models.Cesta;
import com.artistas.api.Models.CestaMasVentas;
import com.artistas.api.Models.ProductoModel;
import com.artistas.api.Models.Venta;
import com.artistas.api.security.JwtValidator;
import com.artistas.api.services.ICestaService;
import com.artistas.api.services.ILoginService;
import com.artistas.api.services.IProductoService;
import com.artistas.api.services.IVentaService;
import com.artistas.api.Commons.CommonFunctions;
import com.artistas.api.Commons.Constants;

@RestController
@RequestMapping("/api")
public class CestaController {
	
	@Autowired
	private ICestaService cestaService;
	
	@Autowired
	private IVentaService ventaService;
	
	@Autowired
	private ILoginService loginService;
	
	@Autowired
	private JwtValidator validator;
	
	@Autowired
	private IProductoService productoService;
	
	@GetMapping("/get_cestas")
	public ResponseEntity<ArrayList<Cesta>> getCestas(@RequestHeader(Constants.authorizationHeaderKey) String token) {
		if (!CommonFunctions.hasTokenAuthorization(token, validator,  loginService)) {
			return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
		}
		
		ArrayList<Cesta> cestas = cestaService.findAll();
		return new ResponseEntity<>(cestas, HttpStatus.OK);
	}
	
	@GetMapping("/get_ventas")
	public ResponseEntity<ArrayList<Venta>> getVentas(@RequestHeader(Constants.authorizationHeaderKey) String token) {
		if (!CommonFunctions.hasTokenAuthorization(token, validator,  loginService)) {
			return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
		}
		
		ArrayList<Venta> ventas = ventaService.findAll();
		return new ResponseEntity<>(ventas, HttpStatus.OK);
	}
	
	@PostMapping("/save_cesta")
	public ResponseEntity<CestaMasVentas> saveCesta(@RequestHeader(Constants.authorizationHeaderKey) String token, @RequestBody CestaMasVentas model) {
		if (!CommonFunctions.hasTokenAuthorization(token, validator,  loginService)) {
			return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
		}
		
		Cesta cesta = cestaService.saveCesta(model.getCesta());
		
		for (Venta venta : model.getVentas()) {
			venta.setCestaId(cesta.getCestaId());
			ProductoModel producto = productoService.findByProductoId(venta.getProductoId());
			producto.setNumProductos(producto.getNumProductos() - venta.getCantidad());
			productoService.updateProducto(producto);
		}
		
		ArrayList<Venta> ventas = ventaService.saveVentas(model.getVentas());
		
		return new ResponseEntity<>(new CestaMasVentas(cesta, ventas), HttpStatus.CREATED);
	}
	
	@PutMapping("/update_cesta")
	public ResponseEntity<CestaMasVentas> updateCesta(@RequestHeader(Constants.authorizationHeaderKey) String token, @RequestBody CestaMasVentas model) {
		if (!CommonFunctions.hasTokenAuthorization(token, validator,  loginService)) {
			return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
		}
		
		if (cestaService.findByCestaId(model.getCesta().getCestaId()) == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		} else {
			Cesta cesta = cestaService.saveCesta(model.getCesta());
			for (Venta venta: model.getVentas()) {
				if (venta.getVentaId() == 0) {
					ProductoModel producto = productoService.findByProductoId(venta.getProductoId());
					producto.setNumProductos(producto.getNumProductos() - venta.getCantidad());
					productoService.updateProducto(producto);
				}
			}
			
			ArrayList<Venta> ventas = ventaService.saveVentas(model.getVentas());
			return new ResponseEntity<>(new CestaMasVentas(cesta, ventas), HttpStatus.OK);
		}
	}
	
	@PostMapping("/delete_Cesta")
	public ResponseEntity<Void> deleteProducto(@RequestHeader(Constants.authorizationHeaderKey) String token, @RequestBody Cesta cesta) {
		if (!CommonFunctions.hasTokenAuthorization(token, validator,  loginService)) {
			return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
		}
		
		if (cestaService.findByCestaId(cesta.getCestaId()) != null) {
			cestaService.deleteCesta(cesta.getCestaId());
			ArrayList<Venta> ventas = ventaService.findByCestaId(cesta.getCestaId());
			ventaService.deleteVentas(ventas);
			return new ResponseEntity<>(HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
}
