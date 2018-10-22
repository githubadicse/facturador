package com.adicse.facturador.wintac.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.adicse.facturador.wintac.model.Cliente;
import com.adicse.facturador.wintac.repo.IClienteDao;

@Transactional
@Service
public class ClienteService {

	@Autowired
	private IClienteDao iClienteDao;
	
	
	public List<Cliente> getAll(){
		return (List<Cliente>) iClienteDao.findAll();
	}
	
}
