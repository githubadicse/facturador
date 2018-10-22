package com.adicse.facturador.wintac.repo;

import org.springframework.data.repository.CrudRepository;

import com.adicse.facturador.wintac.model.Producto;

public interface IProductoDao extends CrudRepository<Producto, Long> {

}
