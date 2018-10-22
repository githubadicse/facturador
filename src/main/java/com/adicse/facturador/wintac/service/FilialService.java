package com.adicse.facturador.wintac.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.adicse.facturador.wintac.model.Filial;
import com.adicse.facturador.wintac.repo.IFilialDao;

@Service
@Transactional
public class FilialService {
	
	
	@Autowired
	private IFilialDao iFilialDao;
	
	
	public Filial getFilial(Integer id){
		return iFilialDao.findById(id).get();
	}

}
