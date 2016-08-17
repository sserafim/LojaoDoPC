package com.samuexx.lojaodopc.controller;

import java.io.Serializable;
import java.util.List;

import javax.enterprise.inject.Produces;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.apache.commons.lang3.StringUtils;

import com.samuexx.lojaodopc.model.Cliente;
import com.samuexx.lojaodopc.model.EnderecoEntrega;
import com.samuexx.lojaodopc.model.FormaPagamento;
import com.samuexx.lojaodopc.model.ItemPedido;
import com.samuexx.lojaodopc.model.Pedido;
import com.samuexx.lojaodopc.model.Produto;
import com.samuexx.lojaodopc.model.Usuario;
import com.samuexx.lojaodopc.repository.ClienteRepository;
import com.samuexx.lojaodopc.repository.ProdutoRepository;
import com.samuexx.lojaodopc.repository.UsuarioRepository;
import com.samuexx.lojaodopc.service.CadastroPedidoService;
import com.samuexx.lojaodopc.util.jsf.FacesUtil;
import com.samuexx.lojaodopc.validation.SKU;

@Named
@ViewScoped
public class CadastroPedidoBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private UsuarioRepository usuarioRespository;
	@Inject
	private ClienteRepository clienteRepository;
	@Inject
	private CadastroPedidoService cadatroPedidoService;
	@Inject
	private ProdutoRepository produtoRepository;

	private Produto produtoLinhaEditavel;
	
	@Produces
	@PedidoEdicao
	private Pedido pedido;
	private List<Usuario> vendedorList;
	private String sku;

	public CadastroPedidoBean() {
		limpar();
	}

	public void inicializar() {
		if (FacesUtil.isNotPostBack()) {
			this.vendedorList = this.usuarioRespository.vendedores();

			this.pedido.adicionarItemVazio();

			this.recalcularPedido();
		}
	}

	public List<Produto> completarProduto(String nome) {
		return this.produtoRepository.porNome(nome);
	}

	public void carregarProdutoLinhaEditavel() {
		ItemPedido item = this.pedido.getItens().get(0);

		if (this.produtoLinhaEditavel != null) {
			if (this.existeItemComProduto(this.produtoLinhaEditavel)) {
				FacesUtil
						.addErrorMessage("Já existe um item no pedido com o produto associado.");
			} else {
				item.setProduto(this.produtoLinhaEditavel);
				item.setValorUnitario(this.produtoLinhaEditavel
						.getValorUnitario());

				this.pedido.adicionarItemVazio();
				this.produtoLinhaEditavel = null;
				this.sku = null;

				this.pedido.recalcularValorTotal();
			}
		}

	}

	private boolean existeItemComProduto(Produto produto) {
		boolean existeItem = false;

		for (ItemPedido item : this.getPedido().getItens()) {
			if (produto.equals(item.getProduto())) {
				existeItem = true;
				break;
			}
		}

		return existeItem;
	}

	public FormaPagamento[] getFormasPagamento() {
		return FormaPagamento.values();
	}

	public List<Cliente> completarCliente(String nome) {
		return this.clienteRepository.porNome(nome);
	}

	public void limpar() {
		pedido = new Pedido();
		pedido.setEnderecoEntrega(new EnderecoEntrega());
	}

	public void salvar() {

		this.pedido.removerItemVazio();

		try {
			this.pedido = this.cadatroPedidoService.salvar(pedido);
			FacesUtil.addInfoMessage("Pedido Salva com sucesso!!");
		} finally {
			this.pedido.adicionarItemVazio();
		}
	}


	public void recalcularPedido() {
		if (this.pedido != null) {
			this.pedido.recalcularValorTotal();
		}
	}

	public void atualizarQuantidade(ItemPedido item, int linha) {
		if (item.getQuantidade() < 1) {
			if (linha == 0) {
				item.setQuantidade(1);
			} else {
				this.getPedido().getItens().remove(linha);
			}
		}

		this.pedido.recalcularValorTotal();
	}

	public void carregarProdutoPorSku() {
		if (StringUtils.isNotEmpty(this.sku)) {
			this.produtoLinhaEditavel = this.produtoRepository.porSku(this.sku);
			this.carregarProdutoLinhaEditavel();
		}

	}

	public Pedido getPedido() {
		return pedido;
	}

	public void setPedido(Pedido pedido) {
		this.pedido = pedido;
	}

	public List<Usuario> getVendedorList() {
		return vendedorList;
	}

	public boolean isEditando() {
		return this.pedido.getId() != null;
	}

	public Produto getProdutoLinhaEditavel() {
		return produtoLinhaEditavel;
	}

	public void setProdutoLinhaEditavel(Produto produtoLinhaEditavel) {
		this.produtoLinhaEditavel = produtoLinhaEditavel;
	}

	@SKU
	public String getSku() {
		return sku;
	}

	public void setSku(String sku) {
		this.sku = sku;
	}

}