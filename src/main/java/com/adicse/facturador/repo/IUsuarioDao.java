package com.adicse.facturador.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.adicse.facturador.model.Usuario;


@Repository
public interface IUsuarioDao extends CrudRepository<Usuario, Integer>, JpaRepository<Usuario,Integer>, PagingAndSortingRepository<Usuario, Integer>, JpaSpecificationExecutor<Usuario> {

	
	@Query("SELECT max(p.idusuario) FROM Usuario p")
	Integer getMax();
	
	
	public Usuario findAllByLogin(String login);
	
	@Query("select a from Usuario a where filial.idfilial=:idfilial order by idusuario")
	public List<Usuario> getByIdFilial(@Param("idfilial") Integer idfilial);
	

	
}
