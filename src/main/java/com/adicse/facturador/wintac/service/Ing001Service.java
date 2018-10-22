package com.adicse.facturador.wintac.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.adicse.facturador.wintac.model.FacturaCab;
import com.adicse.facturador.wintac.repo.IFacturaCabDao;

@Transactional
@Service
public class Ing001Service {
	
	
	@Autowired
	private IFacturaCabDao iFacturaCabDao;
	
	
	public List<FacturaCab> getFacturaWintac(){
		
		return iFacturaCabDao.getFactura();
		
	}

}
