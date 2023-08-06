package modele.metier.bonDeLivraison;

import java.util.List;

import modele.domaine.BonLivraison;

public interface BonLivraisonMetierInterface {
    List<BonLivraison> listBonsLivraison();
    void addBonLivraison(BonLivraison bonLivraison);
    BonLivraison getBonLivraisonById(int id);
    void updateBonLivraison(BonLivraison bonLivraison);
    void deleteBonLivraison(int id);
}
