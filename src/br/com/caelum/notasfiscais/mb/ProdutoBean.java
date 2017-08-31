package br.com.caelum.notasfiscais.mb;

import java.io.Serializable;
import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.caelum.notasfiscais.dao.ProdutoDao;
import br.com.caelum.notasfiscais.modelo.Produto;

@Named
@RequestScoped
public class ProdutoBean implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private Produto produto = new Produto();
	private List<Produto> produtos;
	@Inject
	private ProdutoDao dao;
	
	public void grava(){
	
		
		if(produto.getId()==null){
			dao.adiciona(produto);
		}else{
			dao.atualiza(produto);
		}
		this.produto = new Produto();
		this.produtos = dao.listaTodos();
	}
	 public void cancelar(){
		this.produto = new Produto();
		 
	 }
	public void remove(Produto produto){
		
		dao.remove(produto);
		this.produtos = dao.listaTodos();
		System.out.println("Produto removido");
	}
	
	public List<Produto> getProdutos(){
		if(produtos == null){
			System.out.println("Carregando produtos....");
			produtos = dao.listaTodos();
		}
		return produtos;
	}
	
	public Produto getProduto(){
		return this.produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}
	public boolean existeProdutoListado(Produto produto){
		boolean verifica = dao.existeProdutoCadastrado(produto);
		
		return verifica;
	}
	public boolean podeRemover(Produto produto){
		boolean verifica = !existeProdutoListado(produto);
		return verifica;
	}
}
