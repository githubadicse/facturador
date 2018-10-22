package com.adicse.facturador.wintac.repo;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.adicse.facturador.wintac.model.FacturaCab;

public interface IFacturaCabDao extends CrudRepository<FacturaCab, String> {
	
	
	@Query(value = "select p from FacturaCab p where extract(year from fecha)=2018 and extract(month from fecha)=10 and extract(day from fecha)=10 and swmov='S' and sw_ventas='V' and id='10102018030010291648'" ) 
	List<FacturaCab> getFactura();

	
}
