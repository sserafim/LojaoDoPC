package com.samuexx.lojaodopc.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.inject.Inject;

import com.samuexx.lojaodopc.model.Cliente;
import com.samuexx.lojaodopc.repository.ClienteRepository;


@FacesConverter(forClass = Cliente.class)
public class ClienteConverter implements Converter {

	@Inject
	private ClienteRepository clienteRepository;

	@Override
	public Object getAsObject(FacesContext context, UIComponent component,
			String value) {
		Cliente retorno = null;

		if (value != null && !"".equals(value)) {
			retorno = this.clienteRepository.porId(new Long(value));
		}

		return retorno;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component,
			Object value) {
		if (value != null) {
			Cliente cliente = ((Cliente) value);
			return cliente.getId() == null ? null : cliente.getId()
					.toString();
		}
		return null;
	}

}