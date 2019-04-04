package com.jonathann.springboot.backend.apirest.models.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.jonathann.springboot.backend.apirest.models.entity.Cliente;
import com.jonathann.springboot.backend.apirest.models.entity.Factura;
import com.jonathann.springboot.backend.apirest.models.entity.Producto;
import com.jonathann.springboot.backend.apirest.models.entity.Region;

public interface IClienteService {
	
	public List<Cliente> getClientes();
	public Page<Cliente> getClientes(Pageable pageable);
	public Cliente getCliente(Long id);
	public Cliente update(Cliente cliente);
	public Cliente create(Cliente cliente);
	public void delete(Long id);
	public List<Region>findAllRegiones();
	
	public Factura findFacturaById(Long id);
	public Factura saveFactura(Factura factura);
	public void deleteFacturaById(Long id);
	
	public List<Producto> findProductoByNombre(String term);
}
