package com.jonathann.springboot.backend.apirest.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jonathann.springboot.backend.apirest.models.entity.Factura;
import com.jonathann.springboot.backend.apirest.models.entity.Producto;
import com.jonathann.springboot.backend.apirest.models.service.IClienteService;

@CrossOrigin(origins= {"http://localhost:4200","*"})
@RestController
@RequestMapping("/api")
public class FacturaRestController {
	
	@Autowired
	private IClienteService clienteService;
	
	@Secured({"ROLE_USER","ROLE_ADMIN"})
	@GetMapping("/facturas/{id}")
	public ResponseEntity<?> detalleFactura(@PathVariable Long id) {
		Map<String, Object>response = new HashMap<>();
		Factura factura = clienteService.findFacturaById(id);
		if(factura==null) {
			response.put("mensaje","La factura no existe en la base de datos.");
			return new ResponseEntity<Map<String,Object>>(response,HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Factura>(factura,HttpStatus.OK);
	}
	
	@Secured({"ROLE_ADMIN"})
	@DeleteMapping("/facturas/{id}")
	public ResponseEntity<?> deleteFactura(@PathVariable Long id){
		Map<String,Object>response= new HashMap<>();
		try {
			clienteService.deleteFacturaById(id);
		} catch (DataAccessException e) {
			response.put("mensaje","Error al intentar eliminar la factura.");
			return new ResponseEntity<Map<String,Object>>(response,HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		response.put("mensaje","La factura se ha eliminado con exito.");
		return new ResponseEntity<Map<String,Object>>(response,HttpStatus.OK);
		
	}
	@Secured({"ROLE_ADMIN"})
	@PostMapping("/facturas")
	public ResponseEntity<?> saveFactura(@RequestBody Factura factura){
		Map<String,Object>response = new HashMap<>();
		Factura newFactura = null;
		try {
			newFactura = clienteService.saveFactura(factura);
		} catch (DataAccessException e) {
			response.put("mensaje","Error al intentar crear la factura");
			return new ResponseEntity<Map<String,Object>>(response,HttpStatus.INTERNAL_SERVER_ERROR);
		}
		response.put("mensaje","Factura creada con exito");
		response.put("factura",newFactura);
		return new ResponseEntity<Map<String,Object>>(response,HttpStatus.CREATED);
	}
	@Secured({"ROLE_ADMIN"})
	@PutMapping("/facturas")
	public ResponseEntity<?> updateFactura(@RequestBody Factura factura){
		Map<String,Object>response = new HashMap<>();
		Factura facturanew = clienteService.findFacturaById(factura.getId());
		Factura facturasave=null;
		if(facturanew == null) {
			response.put("mensaje","La factura no se encuentra en la base de datos");
			return new ResponseEntity<Map<String,Object>>(response,HttpStatus.NOT_FOUND);
		}
		facturanew.setDescripcion(factura.getDescripcion());
		facturanew.setItems(factura.getItems());
		facturanew.setObservacion(factura.getObservacion());
		try {
			facturasave = clienteService.saveFactura(facturanew);
		} catch (DataAccessException e) {
			response.put("mensaje","Error al intentar editar la factura.");
			return new ResponseEntity<Map<String,Object>>(response,HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		response.put("mensaje","La factura se ha actualizado con exito.");
		response.put("factura", facturasave);
		return new ResponseEntity<Map<String,Object>>(response,HttpStatus.OK);
		
	}
	@Secured({"ROLE_ADMIN"})
	@GetMapping("/facturas/filtrar-producto/{term}")
	public List<Producto> filtrarProductos(@PathVariable String term){
		return clienteService.findProductoByNombre(term);
	}
	
	
	
}
