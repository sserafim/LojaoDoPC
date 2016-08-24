package com.samuexx.lojaodopc.controller;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.samuexx.lojaodopc.filter.ClienteFilter;
import com.samuexx.lojaodopc.model.Cliente;
import com.samuexx.lojaodopc.repository.ClienteRepository;

@Named
@ViewScoped
public class PesquisaClientesBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private ClienteRepository clienteRepository;

	private ClienteFilter clienteFilter;
	private List<Cliente> clientesFiltrados;
	private Cliente clienteSelecionado;

	public PesquisaClientesBean() {
		clienteFilter = new ClienteFilter();
	}

	public void pesquisar() {
		clientesFiltrados = clienteRepository
				.pesquisaCliFiltrados(clienteFilter);
	}

	public ClienteRepository getClienteRepository() {
		return clienteRepository;
	}

	public void setClienteRepository(ClienteRepository clienteRepository) {
		this.clienteRepository = clienteRepository;
	}

	public List<Cliente> getClientesFiltrados() {
		return clientesFiltrados;
	}

	public void setClientesFiltrados(List<Cliente> clientesFiltrados) {
		this.clientesFiltrados = clientesFiltrados;
	}

	public Cliente getClienteSelecionado() {
		return clienteSelecionado;
	}

	public void setClienteSelecionado(Cliente clienteSelecionado) {
		this.clienteSelecionado = clienteSelecionado;
	}

	public ClienteFilter getClienteFilter() {
		return clienteFilter;
	}

	public void setClienteFilter(ClienteFilter clienteFilter) {
		this.clienteFilter = clienteFilter;
	}

}
