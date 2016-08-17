package com.samuexx.lojaodopc.controller;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.inject.Named;

import com.samuexx.lojaodopc.service.CalculadoraPreco;

@Named("meuBean")
public class PrecoProdutoBean {

	@Inject
	private CalculadoraPreco calculadora;
	
	@PostConstruct
	public void init(){
		System.out.println("init PrecoProduto");
	}

	public double getPreco() {
		return calculadora.calcularPreco(10, 10.45);
	}

}
