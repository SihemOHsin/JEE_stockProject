package modele.domaine;

import java.util.Date;
import java.util.ArrayList;
import java.util.List;
public class CommandeAchat {
    private int id;
    private String nomFournisseur="";
    private Date dateCommande = new Date();
    private int articleId= 0;
    private int quantite=0;
    private List<Article> articles=new ArrayList<>();

    public CommandeAchat() {
    }

    public CommandeAchat(int id, String nomFournisseur, Date dateCommande, int articleId, int quantite) {
        this.id = id;
        this.nomFournisseur = nomFournisseur;
        this.dateCommande = dateCommande;
        this.articleId = articleId;
        this.quantite = quantite;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNomFournisseur() {
        return nomFournisseur;
    }

    public void setNomFournisseur(String nomFournisseur) {
        this.nomFournisseur = nomFournisseur;
    }

    public Date getDateCommande() {
        return dateCommande;
    }

    public void setDateCommande(Date dateCommande) {
        this.dateCommande = dateCommande;
    }

    public int getArticleId() {
        return articleId;
    }

    public void setArticleId(int articleId) {
        this.articleId = articleId;
    }

    public int getQuantite() {
        return quantite;
    }

    public void setQuantite(int quantite) {
        this.quantite = quantite;
    }

    public List<Article> getArticles() {
        return articles;
    }

    public void setArticles(List<Article> articles) {
        this.articles = articles;
    }

	@Override
	public String toString() {
		return "CommandeAchat [id=" + id + ", nomFournisseur=" + nomFournisseur + ", dateCommande=" + dateCommande
				+ ", articleId=" + articleId + ", quantite=" + quantite + ", articles=" + articles + "]";
	}
    
}


/*
 * package modele.domaine;
 * 
 * import java.util.List;
 * 
 * public class CommandeAchat { private int id; private List<Article> articles;
 * 
 * public CommandeAchat() { super(); }
 * 
 * public CommandeAchat(List<Article> articles) { super(); this.articles =
 * articles; }
 * 
 * public CommandeAchat(int id, List<Article> articles) { super(); this.id = id;
 * this.articles = articles; }
 * 
 * public int getId() { return id; }
 * 
 * public void setId(int id) { this.id = id; }
 * 
 * public List<Article> getArticles() { return articles; }
 * 
 * public void setArticles(List<Article> articles) { this.articles = articles; }
 * 
 * @Override public String toString() { return "CommandeAchat [id=" + id +
 * ", articles=" + articles + "]"; }
 * 
 * 
 * 
 * }
 */