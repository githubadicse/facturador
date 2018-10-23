package com.adicse.facturador.repo;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.adicse.facturador.model.ResumenBoleta;

public interface ResumenBoletaDao extends CrudRepository<ResumenBoleta, String> {
	
	
	@Query("SELECT max(p.numeroEnvio) FROM ResumenBoleta p")
	Integer getMax();

}
