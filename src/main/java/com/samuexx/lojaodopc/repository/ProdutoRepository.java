package com.samuexx.lojaodopc.repository;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceException;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import com.samuexx.lojaodopc.filter.ProdutoFilter;
import com.samuexx.lojaodopc.model.Produto;
import com.samuexx.lojaodopc.service.NegocioException;
import com.samuexx.lojaodopc.util.jpa.Transactional;

public class ProdutoRepository implements Serializable {

	public static final long serialVersionUID = 1L;

	@Inject
	private EntityManager manager;

	public Produto porId(Long id) {
		return manager.find(Produto.class, id);
	}

	public Produto guardar(Produto produto) {
		return manager.merge(produto);
	}

	@Transactional
	public void remover(Produto produto) {
		try {
			produto = porId(produto.getId());
			manager.remove(produto);
			manager.flush();
		} catch (PersistenceException e) {
			throw new NegocioException("Produto não pode ser exluído.");
		}

	}

	public Produto porSku(String sku) {
		try {
			return manager
					.createQuery("from Produto where upper(sku) = :sku",
							Produto.class)
					.setParameter("sku", sku.toUpperCase()).getSingleResult();
		} catch (NoResultException e) {
			return null;
		}

	}

	@SuppressWarnings("unchecked")
	public List<Produto> pesquisaFiltrados(ProdutoFilter produtoFilter) {
		Session session = manager.unwrap(Session.class);
		Criteria criteria = session.createCriteria(Produto.class);

		if (StringUtils.isNotBlank(produtoFilter.getSku())) {
			criteria.add(Restrictions.eq("sku", produtoFilter.getSku()));
		}

		if (StringUtils.isNotBlank(produtoFilter.getNome())) {
			criteria.add(Restrictions.ilike("nome", produtoFilter.getNome(),
					MatchMode.ANYWHERE));
		}
		return criteria.addOrder(Order.asc("nome")).list();
	}

	public List<Produto> porNome(String nome) {
		return this.manager
				.createQuery("from Produto where upper(nome) like :nome",
						Produto.class).setParameter("nome", nome.toUpperCase() + "%")
				.getResultList();
	}

}
