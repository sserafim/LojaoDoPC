package com.samuexx.lojaodopc.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.inject.Inject;

import com.samuexx.lojaodopc.model.Pedido;
import com.samuexx.lojaodopc.repository.PedidoRepository;


@FacesConverter(forClass = Pedido.class)
public class PedidoConverter implements Converter {

	@Inject
	private PedidoRepository pedidoRepository;

	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		Pedido retorno = null;
		
		if (value != null && !value.isEmpty()) {
			retorno = this.pedidoRepository.porId(Long.parseLong(value));
		}
		
		return retorno;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		if (value != null) {
			Pedido pedido = (Pedido) value;
			return pedido.getId() == null ? null : pedido.getId().toString();
		}
		
		return "";
	}

}