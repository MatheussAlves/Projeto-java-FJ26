package br.com.caelum.notasfiscais.dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import br.com.caelum.notasfiscais.modelo.Item;
import br.com.caelum.notasfiscais.modelo.NotaFiscal;


public class ItemDao {
	@Inject
	EntityManager manager;
	
	
	public List<Item> listaItemsNota(NotaFiscal notaFiscal){
		String jpql = "Select i from Item i "+
						"Where i.notaFiscal = :notaFiscal";
		Query query = this.manager.createQuery(jpql);
		query.setParameter("notaFiscal", notaFiscal);
		return query.getResultList();
		
		
	}
	
	
}
