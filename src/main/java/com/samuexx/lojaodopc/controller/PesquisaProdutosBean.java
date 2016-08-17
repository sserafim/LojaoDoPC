package com.samuexx.lojaodopc.controller;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.samuexx.lojaodopc.filter.ProdutoFilter;
import com.samuexx.lojaodopc.model.Produto;
import com.samuexx.lojaodopc.repository.ProdutoRepository;
import com.samuexx.lojaodopc.util.jsf.FacesUtil;

@Named
@ViewScoped
public class PesquisaProdutosBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private ProdutoRepository produtoRepository;

	private ProdutoFilter produtoFilter;
	private List<Produto> produtosFiltrados;
	private Produto produtoSelecionado;

	public PesquisaProdutosBean() {
		produtoFilter = new ProdutoFilter();
	}

	public void pesquisar() {
		produtosFiltrados = produtoRepository.pesquisaFiltrados(produtoFilter);
	}

	public void excluir() {
		produtoRepository.remover(produtoSelecionado);
		produtosFiltrados.remove(produtoSelecionado);

		FacesUtil.addInfoMessage("Produto " + produtoSelecionado.getSku()
				+ " excluído com sucesso!");

	}

	public ProdutoRepository getProdutoRepository() {
		return produtoRepository;
	}

	public List<Produto> getProdutosFiltrados() {
		return produtosFiltrados;
	}

	public ProdutoFilter getProdutoFilter() {
		return produtoFilter;
	}

	public void setProdutoFilter(ProdutoFilter produtoFilter) {
		this.produtoFilter = produtoFilter;
	}

	public Produto getProdutoSelecionado() {
		return produtoSelecionado;
	}

	public void setProdutoSelecionado(Produto produtoSelecionado) {
		this.produtoSelecionado = produtoSelecionado;
	}

}
