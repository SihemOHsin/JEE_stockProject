package modele.domaine;

import java.util.List;

public class CommandeVente {
	private int id;
	private String client;
	private List<Article> articles;
	
	public CommandeVente() {
		super();
	}

	public CommandeVente(String client, List<Article> articles) {
		super();
		this.client = client;
		this.articles = articles;
	}


	public CommandeVente(int id, String client, List<Article> articles) {
		super();
		this.id = id;
		this.client = client;
		this.articles = articles;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getClient() {
		return client;
	}

	public void setClient(String client) {
		this.client = client;
	}

	public List<Article> getArticles() {
		return articles;
	}

	public void setArticles(List<Article> articles) {
		this.articles = articles;
	}

	@Override
	public String toString() {
		return "CommandeVente [id=" + id + ", client=" + client + ", articles=" + articles + "]";
	}
	
	

}
