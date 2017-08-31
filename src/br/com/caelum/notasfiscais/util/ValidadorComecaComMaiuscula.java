package br.com.caelum.notasfiscais.util;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

@FacesValidator("comecaComMaiuscula")
public class ValidadorComecaComMaiuscula implements Validator{

	@Override
	public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
		// TODO Auto-generated method stub
		String valor = value.toString();
		if(!valor.matches("[A-Z].*")){
			throw new ValidatorException(
					new FacesMessage("Deve começar com maiuscula"));
		}
	}
}
