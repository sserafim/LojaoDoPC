package com.samuexx.lojaodopc.controller;

import java.io.Serializable;

import javax.faces.bean.ViewScoped;
import javax.inject.Named;

import com.samuexx.lojaodopc.model.Usuario;

@Named
@ViewScoped
public class CadastroUsuarioBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private Usuario usuario;

	public CadastroUsuarioBean() {
		usuario = new Usuario();
	}
	
	public void salvar(){
		
	}

	public Usuario getUsuario() {
		return usuario;
	}

}
