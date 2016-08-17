package com.samuexx.lojaodopc.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.inject.Inject;

import com.samuexx.lojaodopc.model.Categoria;
import com.samuexx.lojaodopc.repository.CategoriaRepository;

@FacesConverter(forClass = Categoria.class)
public class CategoriaConverter implements Converter {

	@Inject
	private CategoriaRepository categoriaRepository;

	@Override
	public Object getAsObject(FacesContext context, UIComponent component,
			String value) {
		Categoria retorno = null;

		if (value != null && !"".equals(value)) {
			retorno = this.categoriaRepository.porId(new Long(value));
		}

		return retorno;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component,
			Object value) {
		if (value != null) {
			Categoria categoria = ((Categoria) value);
			return categoria.getId() == null ? null : categoria.getId()
					.toString();
		}
		return null;
	}

}
