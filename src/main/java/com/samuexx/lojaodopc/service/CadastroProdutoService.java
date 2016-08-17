package com.samuexx.lojaodopc.service;

import java.io.Serializable;

import javax.inject.Inject;

import com.samuexx.lojaodopc.model.Produto;
import com.samuexx.lojaodopc.repository.ProdutoRepository;
import com.samuexx.lojaodopc.util.jpa.Transactional;

public class CadastroProdutoService implements Serializable{
	
	public static final long serialVersionUID = 1L;
	
	@Inject
	private ProdutoRepository produtoRepository;
	
	@Transactional
	public Produto salvar(Produto produto){
		
		Produto produtoExistente = produtoRepository.porSku(produto.getSku());
		
		if(produtoExistente != null && !produtoExistente.equals(produto)){
			throw new NegocioException("Já existe um produto com o SKU informado.");
		}
		
		return produtoRepository.guardar(produto);
	}

}
