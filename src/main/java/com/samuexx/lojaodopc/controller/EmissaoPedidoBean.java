package com.samuexx.lojaodopc.controller;

import java.io.Serializable;

import javax.enterprise.event.Event;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.samuexx.lojaodopc.model.Pedido;
import com.samuexx.lojaodopc.service.EmissaoPedidoService;
import com.samuexx.lojaodopc.util.jsf.FacesUtil;


@Named
@ViewScoped
public class EmissaoPedidoBean implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Inject
	private EmissaoPedidoService emissaoPedidoService;
	
	@Inject
	@PedidoEdicao
	private Pedido pedido;
	
	@Inject
	private Event<PedidoAlteradoEvent> pedidoAlteradoEvent;
	
	public void emitirPedido(){
		
		this.pedido.removerItemVazio();
		
		try{
			this.pedido = this.emissaoPedidoService.emitir(this.pedido);
			
			this.pedidoAlteradoEvent.fire(new PedidoAlteradoEvent(this.pedido));
			
			FacesUtil.addInfoMessage("Pedido emitido com sucesso!");
		}finally{
			this.pedido.adicionarItemVazio();
		}
		
	}

}
