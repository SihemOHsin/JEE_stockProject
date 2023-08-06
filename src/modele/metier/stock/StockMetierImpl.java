package modele.metier.stock;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import modele.dao.DaoImpl;
import modele.dao.DaoInterface;
import modele.domaine.Article;
import modele.domaine.MouvementStock;
import modele.domaine.TypeMouvement;
import modele.metier.article.ArticleMetierImpl;

public class StockMetierImpl implements StockMetierInterface {
    private DaoInterface dao;
    private ArticleMetierImpl articleMetier;

    public StockMetierImpl() {
        dao = new DaoImpl();
        articleMetier = new ArticleMetierImpl();
    }

    @Override
    public void verifierNiveauxStock() {
        List<Article> articles = articleMetier.listArticles();

        for (Article article : articles) {
            int stockLevel = article.getQuantite();
            int threshold = 10; 

            if (stockLevel <= threshold) {
                System.out.println("Low stock for article: " + article.getNom());
                bloquerArticle(article);
            }
        }
    }

    @Override
    public List<MouvementStock> obtenirHistoriqueStock(Article article) {
        List<MouvementStock> mouvementStocks = new ArrayList<>();
        try {
            PreparedStatement ps = dao.connection.prepareStatement("SELECT * FROM mouvement_stock WHERE article_id = ?");
            ps.setInt(1, article.getId());
            ResultSet rs = dao.lire(ps);
            if (rs != null) {
                while (rs.next()) {
                    MouvementStock mouvementStock = new MouvementStock();
                    mouvementStock.setId(rs.getInt("id"));
                    mouvementStock.setArticle(article);
                    mouvementStock.setTypeMouvement(TypeMouvement.valueOf(rs.getString("type_mouvement")));
                    mouvementStocks.add(mouvementStock);
                }
            }
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return mouvementStocks;
    }


    @Override
    public void bloquerArticle(Article article) {
        try {
            PreparedStatement ps = dao.connection.prepareStatement("UPDATE article SET bloque = ? WHERE id = ?");
            ps.setBoolean(1, true);
            ps.setInt(2, article.getId());
            dao.ecrire(ps);
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
