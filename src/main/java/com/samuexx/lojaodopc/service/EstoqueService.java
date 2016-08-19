package com.samuexx.lojaodopc.service;

import java.io.Serializable;

import javax.inject.Inject;

import com.samuexx.lojaodopc.model.ItemPedido;
import com.samuexx.lojaodopc.model.Pedido;
import com.samuexx.lojaodopc.repository.PedidoRepository;
import com.samuexx.lojaodopc.util.jpa.Transactional;

public class EstoqueService implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Inject
	private PedidoRepository pedidoRepository;
	
	
	@Transactional
	public void baixarItensEstoque(Pedido pedido){
		pedido = this.pedidoRepository.porId(pedido.getId());
		
		for(ItemPedido item : pedido.getItens()){
			item.getProduto().baixarEstoque(item.getQuantidade());
		}
		
		
	}
	

}
