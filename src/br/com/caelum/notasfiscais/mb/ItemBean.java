package br.com.caelum.notasfiscais.mb;

import java.io.Serializable;
import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.caelum.notasfiscais.dao.ItemDao;
import br.com.caelum.notasfiscais.dao.NotaFiscalDao;
import br.com.caelum.notasfiscais.modelo.Item;
import br.com.caelum.notasfiscais.modelo.NotaFiscal;
@Named
@RequestScoped
public class ItemBean {

	
	private NotaFiscal notaFiscal = new NotaFiscal();
	private Item item = new Item();
	@Inject
	private NotaFiscalDao notaDao;
	
	@Inject
	private ItemDao iDao;
	
	private List<NotaFiscal> notas;
	
	private List<Item> itens;
	
	public List<NotaFiscal> getNotas() {
		if(notas==null){
			this.notas = notaDao.listaTodos();
		}
		return notas;
	}
	public Item getItem() {
		return item;
	}
	public void setItem(Item item) {
		this.item = item;
	}
	public NotaFiscal getNotaFiscal() {
		return notaFiscal;
	}
	public void setNotaFiscal(NotaFiscal notaFiscal) {
		this.notaFiscal = notaFiscal;
	}
	
	public void lista(){
		System.out.println("Procurando... nota fiscal = "+this.notaFiscal.getId());
		this.itens = iDao.listaItemsNota(notaFiscal);
		for(int i = 0;i<this.itens.size();i++){
			System.out.println(this.itens.get(i).getProduto().getNome());
			System.out.println(this.itens.get(i).getQuantidade());
			System.out.println(this.itens.get(i).getValorUnitario());
		}
		
	
		
	}
	public List<Item> getItens() {
	
		System.out.println("Listando itens....");
		return itens;
	}
	public NotaFiscalDao getNotaDao() {
		return notaDao;
	}
	
	
	public void setNotas(List<NotaFiscal> notas) {
		this.notas = notas;
	}
	
	
	
}
