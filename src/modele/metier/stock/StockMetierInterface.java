package modele.metier.stock;

import modele.domaine.Article;
import modele.domaine.MouvementStock;

import java.util.List;

public interface StockMetierInterface {

    void verifierNiveauxStock();
    List<MouvementStock> obtenirHistoriqueStock(Article article);
    void bloquerArticle(Article article);
}
