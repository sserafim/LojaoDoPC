package com.samuexx.lojaodopc.service;

import javax.annotation.PostConstruct;

public class CalculadoraPreco {
	
	@PostConstruct
	public void init(){
		System.out.println("Init CalculadoraPreco");
	}
	
	public double calcularPreco(int quantidade, double precoUnitario){		
		return quantidade * precoUnitario;
	}
}
