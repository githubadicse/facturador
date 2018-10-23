package com.adicse.facturador.repo;



import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.adicse.facturador.model.DocumentoCab;

public interface IDocumentoCabDao extends CrudRepository<DocumentoCab, String> {

	
	@Query("Select p from DocumentoCab p where p.estadoRegistro = :estadoRegistro and p.fechaEmision = :fechaEmision")
	List<DocumentoCab> getDocumentoDaoByEstadoRegistro(@Param("estadoRegistro") String estadoRegistro, @Param("fechaEmision") Date fechaEmision );
}
