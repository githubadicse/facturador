package com.adicse.facturador.sunat.repo;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.adicse.facturador.sunat.model.FacturaCabSunat;

@Repository
public interface IFacturaCabSunat extends CrudRepository<FacturaCabSunat, Long> {

}
