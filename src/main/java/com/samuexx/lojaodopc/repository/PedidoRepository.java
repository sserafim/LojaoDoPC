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

import com.samuexx.lojaodopc.filter.PedidoFilter;
import com.samuexx.lojaodopc.model.Pedido;

public class PedidoRepository implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Inject
	private EntityManager manager;

	@SuppressWarnings("unchecked")
	public List<Pedido> filtrados(PedidoFilter filtro){
		
		Session session = this.manager.unwrap(Session.class);
		
		Criteria criteria = session.createCriteria(Pedido.class)
				// fazemos uma associação (join) com cliente e nomeamos como "c"
				.createAlias("cliente","c")
				// fazemos uma associação (join) com vendedor e nomeamos como "v"
				.createAlias("vendedor", "v");
		
		if(filtro.getNumeroDe() != null){
			// id deve ser maior ou igual (ge = greater or equals) a filtro.numeroDe
			criteria.add(Restrictions.ge("id", filtro.getNumeroDe()));
		}
		
		if(filtro.getNumeroAte() != null){
			// id deve ser menor ou igual (le = lower or equal) a filtro.numeroDe
			criteria.add(Restrictions.le("id",filtro.getNumeroAte()));
		}
		
		if(filtro.getDataCriacaoDe() != null){
			criteria.add(Restrictions.ge("dataCriacao", filtro.getDataCriacaoDe()));
		}
		
		if(filtro.getDataCriacaoAte() != null){
			criteria.add(Restrictions.le("dataCriacao", filtro.getDataCriacaoAte()));
		}
		
		if(StringUtils.isNotBlank(filtro.getNomeCliente())){
			criteria.add(Restrictions.ilike("c.nome", filtro.getNomeCliente(), MatchMode.ANYWHERE));
		}
		
		
		if(StringUtils.isNotBlank(filtro.getNomeVendedor())){
			criteria.add(Restrictions.ilike("v.nome", filtro.getNomeVendedor(),MatchMode.ANYWHERE));
		}
		
		if(filtro.getStatuses() != null && filtro.getStatuses().length > 0){
			criteria.add(Restrictions.in("status", filtro.getStatuses()));
		}
		
		
	
		return criteria.addOrder(Order.asc("id")).list();
	}

	public Pedido guardar(Pedido pedido) {
			pedido = this.manager.merge(pedido);
		return pedido;
	}
	
	public Pedido porId(Long id){
		return this.manager.find(Pedido.class,id);
	}

	




}
