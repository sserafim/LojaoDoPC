package com.samuexx.lojaodopc.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.validation.constraints.NotNull;

import com.samuexx.lojaodopc.model.Categoria;
import com.samuexx.lojaodopc.model.Produto;
import com.samuexx.lojaodopc.repository.CategoriaRepository;
import com.samuexx.lojaodopc.service.CadastroProdutoService;
import com.samuexx.lojaodopc.util.jsf.FacesUtil;

@Named
@ViewScoped
public class CadastroProdutoBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private CategoriaRepository categoriasRaizesRepository;
	@Inject
	private CadastroProdutoService cadastroProdutoService;
	private Produto produto;
	private Categoria categoriaPai;
	private List<Categoria> categoriasRaizes;
	private List<Categoria> subCategorias;
	

	public CadastroProdutoBean() {
		limpar();
	}

	public void inicializar() {

		if (FacesUtil.isNotPostBack()) {
			categoriasRaizes = categoriasRaizesRepository
					.buscarCategoriasRaizes();
			
			if(this.categoriaPai != null){
				carregarSubcategorias();
			}
		}
	}

	public void salvar() {
		this.produto = cadastroProdutoService.salvar(this.produto);
		limpar();
		FacesUtil.addInfoMessage("Produto salvo com sucesso!!");
	}

	private void limpar() {
		produto = new Produto();
		categoriaPai = null;
		subCategorias = new ArrayList<>();

	}

	public void carregarSubcategorias() {
		subCategorias = categoriasRaizesRepository
				.subCategoriasDe(categoriaPai);
	}

	public Produto getProduto() {
		return produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
		
		if (this.produto != null) {
			this.categoriaPai = this.produto.getCategoria().getCategoriaPai();
		}
	}
	
	public boolean isEditando(){
		return this.produto.getId() != null;
	}

	public List<Categoria> getCategoriasRaizes() {
		return categoriasRaizes;
	}

	@NotNull
	public Categoria getCategoriaPai() {
		return categoriaPai;
	}

	public void setCategoriaPai(Categoria categoriaPai) {
		this.categoriaPai = categoriaPai;
	}

	public List<Categoria> getSubCategorias() {
		return subCategorias;
	}

}
