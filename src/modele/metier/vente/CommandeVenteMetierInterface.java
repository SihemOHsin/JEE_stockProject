package modele.metier.vente;

import java.util.List;

import modele.domaine.Article;
import modele.domaine.BonLivraison;
import modele.domaine.CommandeVente;

public interface CommandeVenteMetierInterface {
    void traiterCommandeVente(CommandeVente commandeVente);
    void addArticleToCommandeVente(CommandeVente commandeVente, Article article);
    void removeArticleFromCommandeVente(CommandeVente commandeVente, Article article);
    List<CommandeVente> getCommandeVentes();
    BonLivraison genererBonLivraison(CommandeVente commande);
	CommandeVente getCommandeVenteById(int id);
}
