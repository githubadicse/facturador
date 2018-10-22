package com.adicse.facturador.wintac.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.adicse.facturador.wintac.model.Producto;
import com.adicse.facturador.wintac.repo.IProductoDao;

@Transactional
@Service
public class ProductoService {
	
	@Autowired
	private IProductoDao iProductoDao;
	
	
	public List<Producto> getAll(){
		return (List<Producto>) iProductoDao.findAll();
	}
	

}
