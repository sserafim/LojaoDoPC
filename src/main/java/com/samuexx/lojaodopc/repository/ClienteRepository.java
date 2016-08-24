package com.samuexx.lojaodopc.repository;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import com.samuexx.lojaodopc.filter.ClienteFilter;
import com.samuexx.lojaodopc.model.Cliente;

public class ClienteRepository implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private EntityManager manager;

	public Cliente porId(Long id) {
		return this.manager.find(Cliente.class, id);
	}

	public List<Cliente> porNome(String nome) {
		return this.manager
				.createQuery("from Cliente " + "where upper(nome) like :nome",
						Cliente.class)
				.setParameter("nome", nome.toUpperCase() + "%").getResultList();
	}

	public List<Cliente> porCpfRg(String cpfRg) {
		return this.manager
				.createQuery(
						"from Cliente where documentoReceitaFederal like :cpfRg  ",
						Cliente.class).setParameter("cpfRg", cpfRg + "%")
				.getResultList();
	}

	public Cliente guardar(Cliente cliente) {
		return manager.merge(cliente);
	}

	@SuppressWarnings("unchecked")
	public List<Cliente> pesquisaCliFiltrados(ClienteFilter clienteFilter) {
		Session session = manager.unwrap(Session.class);
		Criteria criteria = session.createCriteria(Cliente.class);

		if (StringUtils.isNotBlank(clienteFilter.getNome())) {
			criteria.add(Restrictions.ilike("nome", clienteFilter.getNome(),
					MatchMode.ANYWHERE));
		}

		if (StringUtils.isNotBlank(clienteFilter.getDocumentoReceitaFederal())) {
			criteria.add(Restrictions.ilike("documentoReceitaFederal",
					clienteFilter.getDocumentoReceitaFederal(),
					MatchMode.ANYWHERE));
		}

		return criteria.addOrder(Order.desc("nome")).list();

	}
}
