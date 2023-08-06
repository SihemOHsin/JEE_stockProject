package modele.metier.donneesArticle;
import java.util.List;
import modele.domaine.DonneesArticle;

public interface DonneesArticleMetierInterface {
    List<DonneesArticle> listDonneesArticles();
    void addDonneesArticle(DonneesArticle donneesArticle);
    DonneesArticle getDonneesArticleById(int id);
    void updateDonneesArticle(DonneesArticle donneesArticle);
    void deleteDonneesArticle(int id);
}
