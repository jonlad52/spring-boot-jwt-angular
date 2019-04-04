package com.jonathann.springboot.backend.apirest.models.dao;

import java.util.List;
import org.springframework.data.repository.CrudRepository;

import com.jonathann.springboot.backend.apirest.models.entity.Producto;

public interface IProductoDao extends CrudRepository<Producto,Long>{
	
	/*@Query("SELECT p FROM Producto p WHERE p.nombre LIKE %?1% ")
	public List<Producto>findByNombre(String termino);*/
	public List<Producto>findByNombreContainingIgnoreCase(String term);
}
