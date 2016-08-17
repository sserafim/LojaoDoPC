package com.samuexx.lojaodopc.filter;

import java.io.Serializable;

import com.samuexx.lojaodopc.validation.SKU;

public class ProdutoFilter implements Serializable {

	public static final long serialVersionUID = 1L;

	private String sku;
	private String nome;

	@SKU
	public String getSku() {
		return sku;
	}

	public void setSku(String sku) {
		this.sku = sku == null ? null : sku.toUpperCase();
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

}
