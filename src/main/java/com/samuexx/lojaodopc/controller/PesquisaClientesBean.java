package com.samuexx.lojaodopc.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ViewScoped;
import javax.inject.Named;


@Named
@ViewScoped
public class PesquisaClientesBean implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	
	private List<Integer> clientesFiltrados;
	
	
	public PesquisaClientesBean(){
		
		clientesFiltrados = new ArrayList<>();
		
		for(int i=0; i<3; i++){
			clientesFiltrados.add(i);			
		}
	}


	public List<Integer> getClientesFiltrados() {
		return clientesFiltrados;
	}
	
	
	
}
