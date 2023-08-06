package modele.metier.article;

import modele.dao.DaoImpl;
import modele.dao.DaoInterface;
import modele.domaine.Article;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ArticleMetierImpl implements ArticleMetierInterface {
    private DaoInterface dao;

    public ArticleMetierImpl() {
    	dao = new DaoImpl();
    }

    @Override
    public List<Article> listArticles() {
        List<Article> articles = new ArrayList<>();
        try {
            PreparedStatement ps = dao.connection .prepareStatement("SELECT * FROM article");
            ResultSet rs = dao.lire(ps);
            if (rs != null) {
                while (rs.next()) {
                    Article a = new Article();
                    a.setId(rs.getInt("id"));
                    a.setNom(rs.getString("nom"));
                    a.setQuantite(rs.getInt("quantite"));
                    articles.add(a);
                }
            }
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return articles;
    }

    @Override
    public void addArticle(Article a) {
        try {
            PreparedStatement ps = dao.connection.prepareStatement("INSERT INTO article (nom, quantite) VALUES (?, ?)");
            ps.setString(1, a.getNom());
            ps.setInt(2, a.getQuantite());
            dao.ecrire(ps);
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Article getArticleById(int id) {
        Article article = null;
        try {
            PreparedStatement ps = dao.connection.prepareStatement("SELECT * FROM article WHERE id = ?");
            ps.setInt(1, id);
            ResultSet rs = dao.lire(ps);
            if (rs != null && rs.next()) {
                article = new Article();
                article.setId(rs.getInt("id"));
                article.setNom(rs.getString("nom"));
                article.setQuantite(rs.getInt("quantite"));
            }
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return article;
    }

    @Override
    public void updateArticle(Article a) {
        try {
            PreparedStatement ps = dao.connection.prepareStatement("UPDATE article SET nom = ?, quantite = ? WHERE id = ?");
            ps.setString(1, a.getNom());
            ps.setInt(2, a.getQuantite());
            ps.setInt(3, a.getId());
            dao.ecrire(ps);
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteArticle(int id) {
        try {
            PreparedStatement ps = dao.connection.prepareStatement("DELETE FROM article WHERE id = ?");
            ps.setInt(1, id);
            dao.ecrire(ps);
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    @Override
    public void updateChildTableReferences(int articleId) {
        try {
            String updateQuery = "UPDATE commandeachat_article SET articleId = NULL WHERE articleId = ?";
            PreparedStatement ps = dao.connection.prepareStatement(updateQuery);
            ps.setInt(1, articleId);

            dao.ecrire(ps);

            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


}
