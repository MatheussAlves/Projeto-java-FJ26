package br.com.caelum.notasfiscais.mb;

import java.io.Serializable;

import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.caelum.notasfiscais.dao.UsuarioDao;
import br.com.caelum.notasfiscais.modelo.Usuario;


@Named
@RequestScoped
public class CadastraLoginBean implements Serializable{

	private static final long serialVersionUID = 1L;
	
	
	private Usuario usuario = new Usuario();
	@Inject
	private UsuarioDao dao;
	
	private String confirmaSenha;
	
	
	
	public String getConfirmaSenha() {
		return confirmaSenha;
	}

	public void setConfirmaSenha(String confirmaSenha) {
		this.confirmaSenha = confirmaSenha;
	
	}
	
	public boolean verificaSenha(){
		if(this.confirmaSenha.equals(usuario.getSenha())){
			return true;
		}else{
			return false;
		}
	}

	public void grava(){
		if(usuario.getId()==null && verificaSenha()){
			dao.adiciona(usuario);
		}else{
			FacesContext.getCurrentInstance()
			.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN,"Senhas diferentes", "Watch out for PrimeFaces!"));
			this.confirmaSenha = null;
		}
		//this.usuario = new Usuario();
		System.out.println("Usuario gravado ");
		
		
	}
	
	public String confirmaCadastro(){
		return "login?faces-redirect=true";
		
	}
	public Usuario getUsuario(){
		return this.usuario;
	}
	public String retornar(){
		this.confirmaSenha=null;
		return "cadastro";
	}
}
