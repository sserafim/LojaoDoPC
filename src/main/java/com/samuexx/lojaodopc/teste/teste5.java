package com.samuexx.lojaodopc.teste;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class teste5 {

	public static void main(String[] args) {

		EntityManagerFactory factory = Persistence
				.createEntityManagerFactory("LojaoDoPCPU");
		EntityManager manager = factory.createEntityManager();

		EntityTransaction trx = manager.getTransaction();

		trx.begin();

		trx.commit();

	}

}
