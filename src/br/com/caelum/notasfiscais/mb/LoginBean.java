package br.com.caelum.notasfiscais.mb;



import java.io.Serializable;

import javax.enterprise.context.RequestScoped;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.caelum.notasfiscais.dao.UsuarioDao;
import br.com.caelum.notasfiscais.modelo.Usuario;
import sun.security.validator.ValidatorException;



@Named
@RequestScoped
public class LoginBean implements Serializable{
	private static final long serialVersionUID = 1L;
	private Usuario usuario = new Usuario();
	@Inject
	private UsuarioDao dao;
	@Inject
	private UsuarioLogadoBean usuarioLogado;
	
	
	public String efetuaLogin(){
		
		boolean loginValido = dao.existe(this.usuario);
		if(loginValido){
			usuarioLogado.logar(usuario);
			return "produto?faces-redirect=true";
		}else{
			usuarioLogado.deslogar();
			this.usuario = new Usuario();
			FacesContext.getCurrentInstance().addMessage(
					null, new FacesMessage(FacesMessage.SEVERITY_WARN,"Login invalido", "Watch out for PrimeFaces!"));
			return "login";
		}
		
	
		
	}
	public String logout(){
		usuarioLogado.deslogar();
		this.usuario = new Usuario();
		return "login";
	}
	
	
	public void grava(){
		if(usuario.getId()==null){
			dao.adiciona(usuario);
		}
		this.usuario = new Usuario();
		System.out.println("Usuario gravado ");
	}
	
	public String efetuaCadastro(){
		return "cadastro";
		
	}
	
	
	public Usuario getUsuario(){
		return this.usuario;
	}
	
	
}
