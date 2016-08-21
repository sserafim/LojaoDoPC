package com.samuexx.lojaodopc.service;

import java.io.Serializable;

import javax.inject.Inject;

import com.samuexx.lojaodopc.model.Pedido;
import com.samuexx.lojaodopc.model.StatusPedido;
import com.samuexx.lojaodopc.repository.PedidoRepository;
import com.samuexx.lojaodopc.util.jpa.Transactional;

public class CancelamentoPedidoService implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private PedidoRepository pedidoRepository;

	@Inject
	private EstoqueService estoqueService;

	@Transactional
	public Pedido cancelar(Pedido pedido) {
		pedido = this.pedidoRepository.porId(pedido.getId());

		if (pedido.isNaoCancelavel()) {
			throw new NegocioException(
					"Pedido não pode ser cancelado no status "
							+ pedido.getStatus().getDescricao() + ".");
		}
		
		if(pedido.isEmitido()){
			this.estoqueService.retornarItensEstoque(pedido);
				
		}
		
		pedido.setStatus(StatusPedido.CANCELADO);
		
		pedido = this.pedidoRepository.guardar(pedido);

		return pedido;
	}

}
