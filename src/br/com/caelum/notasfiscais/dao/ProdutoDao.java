package br.com.caelum.notasfiscais.dao;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaQuery;

import com.sun.xml.internal.ws.policy.privateutil.PolicyUtils.Collections;

import br.com.caelum.notasfiscais.modelo.Produto;


public class ProdutoDao implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Inject
	private EntityManager manager;
	
	
	public void adiciona(Produto produto) {
		
		this.manager.getTransaction().begin();
		this.manager.persist(produto);
		this.manager.getTransaction().commit();
		//this.manager.close();
		
	}


	public void remove(Produto produto) {
		
		this.manager.getTransaction().begin();

		this.manager.remove(manager.merge(produto));

		this.manager.getTransaction().commit();
		//manager.close();
	}

	public void atualiza(Produto produto) {
		
		this.manager.getTransaction().begin();

		this.manager.merge(produto);
		
		this.manager.getTransaction().commit();
		//manager.close();
	}

	public List<Produto> buscaPorNome(String nome) {

	

		String jpql = "select p from Produto p where "
				+ " lower(p.nome) like :nome order by p.nome";

		List<Produto> lista = manager.createQuery(jpql, Produto.class)
				.setParameter("nome", nome + "%").getResultList();

		//manager.close();
		
		return lista; 
	}

	public List<Produto> listaTodos() {
		
		
		CriteriaQuery<Produto> query = manager.getCriteriaBuilder().createQuery(Produto.class);
		query.select(query.from(Produto.class));

		List<Produto> lista = manager.createQuery(query).getResultList();

		//manager.close();
		lista.sort(null);
		return lista; 
	}
	
	public Produto buscaPorId(Long id) {
		

		Produto produto = manager.find(Produto.class, id);

		//manager.close();

		return produto;
	}
	public boolean existeProdutoCadastrado(Produto produto){
		Query query = this.manager.createQuery("Select i from Item i "
							+"Where i.produto = :produto")
				.setParameter("produto",produto);
		boolean encontrado = !query.getResultList().isEmpty();
		
		return encontrado;
		
		
		
	}
}