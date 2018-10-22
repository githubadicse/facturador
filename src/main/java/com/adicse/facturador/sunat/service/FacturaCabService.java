package com.adicse.facturador.sunat.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.adicse.facturador.sunat.model.FacturaCabSunat;
import com.adicse.facturador.sunat.repo.IFacturaCabSunat;

@Transactional
@Service
public class FacturaCabService {
	
	
	@Autowired
	private IFacturaCabSunat iFacturaCabSunat;
	
	
	public FacturaCabSunat getFacturaSunat(Long id) {
		return iFacturaCabSunat.findById(id).get();
	}

}
