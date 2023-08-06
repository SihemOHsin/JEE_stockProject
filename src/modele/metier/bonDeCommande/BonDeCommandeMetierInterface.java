package modele.metier.bonDeCommande;
import java.util.List;
import modele.domaine.BonDeCommande;

public interface BonDeCommandeMetierInterface {
    List<BonDeCommande> listBonDeCommande();
    void addBonDeCommande(BonDeCommande bonDeCommande);
    BonDeCommande getBonDeCommandeById(int id);
    void updateBonDeCommande(BonDeCommande bonDeCommande);
    void deleteBonDeCommande(int id);
}
