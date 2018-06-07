package br.com.caelum.livraria.bean;

import java.io.Serializable;
import java.util.List;

import javax.faces.view.ViewScoped;
import javax.inject.Named;

import br.com.caelum.livraria.dao.DAO;
import br.com.caelum.livraria.modelo.Autor;
import br.com.caelum.livraria.util.RedirectView;;

@SuppressWarnings("serial")
@Named
@ViewScoped
public class AutorBean implements Serializable {

	private Autor autor = new Autor();
	private Integer autorId;
	

	public Integer getAutorId() {
		return autorId;
	}

	public void setAutorId(Integer autorId) {
		this.autorId = autorId;
	}

	public Autor getAutor() {
		return autor;
	}

	public List<Autor> getAutores() {
		return new DAO<Autor>(Autor.class).listaTodos();
	}

//	public void gravar() {
//	public String gravar() {
	public RedirectView gravar() {
		System.out.println("Gravando autor " + autor.getNome());
		
		if (this.autor.getId() == null) {
			new DAO<Autor>(Autor.class).adiciona(this.autor);
		} else {
			new DAO<Autor>(Autor.class).atualiza(this.autor);
		}
		
		this.autor = new Autor();
		
//		return "livro?faces-redirect=true";
		return new RedirectView("livro");
	}
	
	public void remover(Autor autor) {
		System.out.println("Removendo autor: " + autor.getNome());
		new DAO<Autor>(Autor.class).remove(autor);
	}
	
	public void carregar(Autor autor) {
		System.out.println("Carregando autor: " + autor.getNome());
		this.autor = autor;
	}
	
	public void carregarAutorPelaId() {
		this.autor = new DAO<Autor>(Autor.class).buscaPorId(this.autorId);
	}
}
