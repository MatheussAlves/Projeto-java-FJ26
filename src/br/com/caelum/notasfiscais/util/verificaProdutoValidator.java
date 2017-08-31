package br.com.caelum.notasfiscais.util;

import java.io.Serializable;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

public class verificaProdutoValidator implements Serializable, Validator{

	private static final long serialVersionUID = 1L;

	
	
	@Override
	public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
		// TODO Auto-generated method stub
		
	}

}
