package modele.domaine;

public class DonneesArticle {
    private String nom= null;
    private int quantite= 0;
    private int id= 0;

    public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public DonneesArticle() {
		super();
	}

	public DonneesArticle(String nom, int quantite) {
        this.nom = nom;
        this.quantite = quantite;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public int getQuantite() {
        return quantite;
    }

    public void setQuantite(int quantite) {
        this.quantite = quantite;
    }
}
