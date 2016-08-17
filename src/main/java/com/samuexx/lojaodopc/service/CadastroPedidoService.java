package com.samuexx.lojaodopc.service;

import java.io.Serializable;
import java.util.Date;

import javax.inject.Inject;

import com.samuexx.lojaodopc.model.Pedido;
import com.samuexx.lojaodopc.model.StatusPedido;
import com.samuexx.lojaodopc.repository.PedidoRepository;
import com.samuexx.lojaodopc.util.jpa.Transactional;

public class CadastroPedidoService implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private PedidoRepository pedidoRepository;

	@Transactional
	public Pedido salvar(Pedido pedido) {

		if (pedido.isNovo()) {
			pedido.setDataCriacao(new Date());
			pedido.setStatus(StatusPedido.ORCAMENTO);
		}

		pedido.recalcularValorTotal();

		if(pedido.getItens().isEmpty()){
			throw new NegocioException("O Pedido deve possuir pelo menos um item.");
		}
		
		if(pedido.isValorTotalNegativo()){
			throw new NegocioException("Valor total do pedido não pode ser negativo.");
		}
		
		pedido = this.pedidoRepository.guardar(pedido);
		return pedido;
	}

}
