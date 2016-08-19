package com.samuexx.lojaodopc.service;

import java.io.Serializable;

import javax.inject.Inject;

import com.samuexx.lojaodopc.model.Pedido;
import com.samuexx.lojaodopc.model.StatusPedido;
import com.samuexx.lojaodopc.repository.PedidoRepository;

public class EmissaoPedidoService implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private CadastroPedidoService cadastroPedidoService;

	@Inject
	private PedidoRepository pedidoRepository;

	@Inject
	private EstoqueService estoqueService;

	public Pedido emitir(Pedido pedido) {
		pedido = this.cadastroPedidoService.salvar(pedido);

		if (pedido.isNaoEmissivel()) {
			throw new NegocioException(
					"Pedido não não pode ser emitido com status "
							+ pedido.getStatus().getDescricao() + ".");
		}

		this.estoqueService.baixarItensEstoque(pedido);

		pedido.setStatus(StatusPedido.EMITIDO);
		pedido = this.pedidoRepository.guardar(pedido);

		return pedido;
	}

}
