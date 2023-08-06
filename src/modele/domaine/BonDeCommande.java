package modele.domaine;

public class BonDeCommande {
    private int id;
    private String client ="";
    
	public BonDeCommande() {
		super();
	}
	public BonDeCommande(int id, String client) {
		super();
		this.id = id;
		this.client = client;
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
	@Override
	public String toString() {
		return "Bon de Commande [id=" + id + ", client=" + client + "]";
	}
    
    
    
}
    

    