package com.samuexx.lojaodopc.teste;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import com.samuexx.lojaodopc.model.Grupo;
import com.samuexx.lojaodopc.model.Usuario;

public class Teste2 {
	
	public static void main (String[] args){
	
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("LojaoDoPCPU");
		EntityManager manager = factory.createEntityManager();
		
		EntityTransaction trx = manager.getTransaction();
		
		trx.begin();
		
		Usuario usuario = new Usuario();
		usuario.setNome("Maria");
		usuario.setEmail("maria@abadia.com");
		usuario.setSenha("123");
		
		Grupo grupo = new Grupo();
		grupo.setNome("Vendedores");
		grupo.setDescricao("Vendedores interno");
		
		usuario.getGrupos().add(grupo);
		
		manager.persist(usuario);
		
		trx.commit();
		
		
	}

}
