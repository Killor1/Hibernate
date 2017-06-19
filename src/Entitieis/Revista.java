package Entitieis;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Revista implements Serializable {
	private int id_revista;
	private String titol;
	private Date data_publicacio;

	private Set<Article> articles = new HashSet<Article>();

	public Revista(int id_revista, String titol, Date data_publicacio) {
		super();
		this.titol = titol;
		this.data_publicacio = data_publicacio;
		this.id_revista = id_revista;
	}

	public Revista() {
		super();
	}

	public String getTitol() {
		return titol;
	}

	public void setTitol(String titol) {
		this.titol = titol;
	}

	public Date getData_publicacio() {
		return data_publicacio;
	}

	public void setData_publicacio(Date data_publicacio) {
		this.data_publicacio = data_publicacio;
	}

	public int getId_revista() {
		return id_revista;
	}

	public void setId_revista(int id_revista) {
		this.id_revista = id_revista;
	}

	public void addArticle(Article art) {
		articles.add(art);
	}

	public Article getArticle(int i) {
		Article ars=null;
		for(Article art:articles){
			if(art.getId_article()==i){
				ars=art;
			}
		}
		return ars;
	}

	public Set<Article> getArticles() {
		return articles;
	}

	public void setArticles(Set<Article> articles) {
		this.articles = articles;
	}

	@Override
	public String toString() {
		return "Revista [id_revista=" + id_revista + ",titol=" + titol + ", data_publicacio="
				+ data_publicacio.toString() + "]";
	}

}