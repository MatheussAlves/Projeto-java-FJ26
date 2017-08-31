package br.com.caelum.notasfiscais.util;

import java.io.Serializable;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;

@FacesValidator("nomeJaExistente")
public class NomeJaExistenteValidator implements Validator, Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Inject
	private EntityManager manager;
		
	
	
	
	@Override
	public void validate(FacesContext context, 
			UIComponent component, Object value) throws ValidatorException {
		
	Query q = manager.createQuery
			("Select Count(u) from Usuario u where u.login like :login");
	q.setParameter("login",String.valueOf(value));
	Long count = (Long) q.getSingleResult();
	if(count !=0 ){
		throw new ValidatorException(new FacesMessage("Nome de login já existente"));
	}
	
		
	}

}
