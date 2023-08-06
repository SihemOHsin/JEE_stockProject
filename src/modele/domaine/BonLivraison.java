package modele.domaine;

import java.util.Date;

public class BonLivraison {
	private int id;
	private CommandeVente commandeVente;
	private Date dateLivraison;
	
	public BonLivraison() {
		super();
	}

	public BonLivraison(CommandeVente commandeVente, Date dateLivraison) {
		super();
		this.commandeVente = commandeVente;
		this.dateLivraison = dateLivraison;
	}

	public BonLivraison(int id, CommandeVente commandeVente, Date dateLivraison) {
		super();
		this.id = id;
		this.commandeVente = commandeVente;
		this.dateLivraison = dateLivraison;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public CommandeVente getCommandeVente() {
		return commandeVente;
	}

	public void setCommandeVente(CommandeVente commandeVente) {
		this.commandeVente = commandeVente;
	}

	public Date getDateLivraison() {
		return dateLivraison;
	}

	public void setDateLivraison(Date dateLivraison) {
		this.dateLivraison = dateLivraison;
	}

	@Override
	public String toString() {
		return "BonLivraison [id=" + id + ", commandeVente=" + commandeVente + ", dateLivraison=" + dateLivraison + "]";
	}
	
	

}
