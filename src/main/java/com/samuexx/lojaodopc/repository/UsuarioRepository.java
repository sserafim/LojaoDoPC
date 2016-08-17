package com.samuexx.lojaodopc.repository;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;

import com.samuexx.lojaodopc.model.Usuario;

public class UsuarioRepository implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Inject
	private EntityManager manager;
	
	public Usuario porId(Long id){
		return this.manager.find(Usuario.class, id);
	}

	public List<Usuario> vendedores(){
		return this.manager.createQuery("from Usuario", Usuario.class)
				.getResultList();
	}

}
