package com.samuexx.lojaodopc.repository;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;

import com.samuexx.lojaodopc.model.Categoria;

public class CategoriaRepository implements Serializable {

	public static final long serialVersionUID = 1L;
	
	@Inject
	private EntityManager manager;
	
	public List<Categoria> buscarCategoriasRaizes(){
		return manager.createQuery("from Categoria where categoriaPai is null",
				Categoria.class).getResultList();
	}
	
	public List<Categoria> subCategoriasDe(Categoria categoriaPai){
		return manager.createQuery("from Categoria where categoriaPai = :raiz",Categoria.class)
				.setParameter("raiz", categoriaPai)
				.getResultList();
	}
	
	public Categoria porId(Long id){
		return manager.find(Categoria.class,id);
	}
	
}
