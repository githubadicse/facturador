package com.adicse.facturador.wintac.repo;

import org.springframework.data.repository.CrudRepository;

import com.adicse.facturador.wintac.model.Cliente;

public interface IClienteDao extends CrudRepository<Cliente, Integer> {

}
