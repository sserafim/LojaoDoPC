package com.samuexx.lojaodopc.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.inject.Inject;

import com.samuexx.lojaodopc.model.Usuario;
import com.samuexx.lojaodopc.repository.UsuarioRepository;

@FacesConverter(forClass = Usuario.class)
public class UsuarioConverter implements Converter {

	@Inject
	private UsuarioRepository usuarioRepository;

	@Override
	public Object getAsObject(FacesContext context, UIComponent component,
			String value) {
		Usuario retorno = null;

		if (value != null && !"".equals(value)) {
			retorno = this.usuarioRepository.porId(new Long(value));
		}

		return retorno;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component,
			Object value) {
		if (value != null) {
			Usuario usuario = ((Usuario) value);
			return usuario.getId() == null ? null : usuario.getId()
					.toString();
		}
		return null;
	}

}