package com.jonathann.springboot.backend.apirest.controllers;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.jonathann.springboot.backend.apirest.models.entity.Cliente;
import com.jonathann.springboot.backend.apirest.models.entity.Region;
import com.jonathann.springboot.backend.apirest.models.service.IClienteService;
import com.jonathann.springboot.backend.apirest.models.service.IUploadFileService;

@RestController
@CrossOrigin(origins = { "http://localhost:4200","*" })
@RequestMapping("/api")
public class ClienteRestController {

	@Autowired
	private IClienteService clienteService;

	@Autowired
	private IUploadFileService uploadFileService;

	@GetMapping("/clientes")
	public List<Cliente> clientes() {
		return clienteService.getClientes();
	}

	@GetMapping("/clientes/page/{page}")
	public Page<Cliente> clientes(@PathVariable Integer page) {
		Pageable pageable = PageRequest.of(page, 4);
		return clienteService.getClientes(pageable);
	}
	
	@Secured({"ROLE_USER","ROLE_ADMIN"})
	@GetMapping("/clientes/{id}")
	public ResponseEntity<?> clienteById(@PathVariable Long id) {

		Cliente cliente = clienteService.getCliente(id);
		Map<String, Object> response = new HashMap<>();
		if (cliente == null) {
			response.put("mensaje", "El cliente con id: " + id.toString() + " no existe en la base de datos!");
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity<Cliente>(cliente, HttpStatus.OK);

	}
	@Secured("ROLE_ADMIN")
	@PostMapping("/clientes")
	public ResponseEntity<?> create(@Valid @RequestBody Cliente cliente, BindingResult result) {
		Cliente clienteNew = null;
		Map<String, Object> response = new HashMap<>();

		if (result.hasErrors()) {

			List<String> errors = result.getFieldErrors().stream()
					.map(e -> "El campo '" + e.getField() + "' " + e.getDefaultMessage()).collect(Collectors.toList());
			response.put("errors", errors);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
		}

		try {
			clienteNew = clienteService.create(cliente);
		} catch (DataAccessException e) {
			response.put("mensaje", "Error al intentar crear el cliente");
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		response.put("mensaje", "Cliente creado con exito!");
		response.put("cliente", clienteNew);

		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);

	}
	@Secured("ROLE_ADMIN")
	@PutMapping("/clientes/{id}")
	public ResponseEntity<?> update(@Valid @RequestBody Cliente cliente, BindingResult result, @PathVariable Long id) {
		Cliente clienteAct = clienteService.getCliente(id);
		Cliente clienteNew = null;
		Map<String, Object> response = new HashMap<>();

		if (result.hasErrors()) {

			List<String> errors = result.getFieldErrors().stream()
					.map(e -> "El campo '" + e.getField() + "' " + e.getDefaultMessage()).collect(Collectors.toList());
			response.put("errors", errors);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
		}

		if (clienteAct == null) {
			response.put("mensaje", "No se puede actualizar el cliente, El cliente con id: " + id.toString()
					+ " no existe en la base de datos!");
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}

		clienteAct.setNombre(cliente.getNombre());
		clienteAct.setApellido(cliente.getApellido());
		clienteAct.setEmail(cliente.getEmail());
		clienteAct.setCreateAt(cliente.getCreateAt());
		clienteAct.setRegion(cliente.getRegion());

		try {
			clienteNew = clienteService.update(clienteAct);
		} catch (DataAccessException e) {
			response.put("mensaje", "Error al intentar actualizar el cliente!");
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		response.put("mensaje", "El cliente ha sido actualizado con exito!");
		response.put("cliente", clienteNew);

		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
	}
	@Secured("ROLE_ADMIN")
	@DeleteMapping("/clientes/{id}")
	public ResponseEntity<?> delete(@PathVariable Long id) {
		Map<String, Object> response = new HashMap<>();
		try {
			Cliente cliente = clienteService.getCliente(id);
			String nombreFotoAnterior = cliente.getFoto();

			uploadFileService.eliminar(nombreFotoAnterior);
			clienteService.delete(id);
		} catch (DataAccessException e) {
			response.put("mensaje", "Error al intentar eliminar el cliente");
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		response.put("mensaje", "El cliente ha sido eliminado con exito!");
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
	}
	@Secured({"ROLE_USER","ROLE_ADMIN"})
	@PostMapping("/clientes/upload")
	public ResponseEntity<?> upload(@RequestParam("archivo") MultipartFile archivo, @RequestParam("id") Long id) {
		Map<String, Object> response = new HashMap<>();
		Cliente cliente = clienteService.getCliente(id);

		if (cliente == null) {
			response.put("mensaje", "No se puede actualizar el cliente, El cliente con id: " + id.toString()
					+ "no existe en la base de datos!");
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}

		if (!archivo.isEmpty()) {
			String nombreArchivo = null;
			try {
				nombreArchivo = uploadFileService.copiar(archivo);
			} catch (IOException e) {
				response.put("mensaje", "Error al subir la imagen del cliente ");
				return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
			}

			String nombreFotoAnterior = cliente.getFoto();

			uploadFileService.eliminar(nombreFotoAnterior);

			cliente.setFoto(nombreArchivo);

			clienteService.update(cliente);

			response.put("cliente", cliente);
			response.put("mensaje", "Has subido correctamente la imagen " + nombreArchivo);
		}

		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	}
	
	@GetMapping("/uploads/img/{nombreFoto:.+}")
	public ResponseEntity<Resource> verFoto(@PathVariable String nombreFoto) {
		Resource recurso = null;

		try {
			recurso = uploadFileService.cargar(nombreFoto);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}

		HttpHeaders header = new HttpHeaders();
		header.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename =\"" + recurso.getFilename() + "\"");

		return new ResponseEntity<Resource>(recurso, header, HttpStatus.OK);
	}
	@Secured("ROLE_ADMIN")
	@GetMapping("/clientes/regiones")
	public List<Region>listarRegiones(){
		return clienteService.findAllRegiones();
	}

}
