package modele.metier.donneesArticle;

import java.util.ArrayList;
import java.util.List;
import modele.dao.DaoInterface;
import modele.dao.DaoImpl;
import modele.domaine.DonneesArticle;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DonneesArticleMetierImpl implements DonneesArticleMetierInterface {
    private DaoInterface dao;

    public DonneesArticleMetierImpl() {
        dao = new DaoImpl();
    }

    @Override
    public List<DonneesArticle> listDonneesArticles() {
        List<DonneesArticle> donneesArticles = new ArrayList<>();
        try {
            PreparedStatement ps = dao.connection.prepareStatement("SELECT * FROM donnees_article");
            ResultSet rs = dao.lire(ps);
            if (rs != null) {
                while (rs.next()) {
                    DonneesArticle donneesArticle = new DonneesArticle();
                    donneesArticle.setId(rs.getInt("id"));
                    donneesArticle.setNom(rs.getString("nom"));
                    donneesArticle.setQuantite(rs.getInt("quantite"));
                    donneesArticles.add(donneesArticle);
                }
            }
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return donneesArticles;
    }

    @Override
    public void addDonneesArticle(DonneesArticle donneesArticle) {
        try {
            PreparedStatement ps = dao.connection.prepareStatement("INSERT INTO donnees_article (nom, quantite) VALUES (?, ?)");
            ps.setString(1, donneesArticle.getNom());
            ps.setInt(2, donneesArticle.getQuantite());
            dao.ecrire(ps);
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public DonneesArticle getDonneesArticleById(int id) {
        DonneesArticle donneesArticle = null;
        try {
            PreparedStatement ps = dao.connection.prepareStatement("SELECT * FROM donnees_article WHERE id = ?");
            ps.setInt(1, id);
            ResultSet rs = dao.lire(ps);
            if (rs != null && rs.next()) {
                donneesArticle = new DonneesArticle();
                donneesArticle.setId(rs.getInt("id"));
                donneesArticle.setNom(rs.getString("nom"));
                donneesArticle.setQuantite(rs.getInt("quantite"));
            }
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return donneesArticle;
    }

    @Override
    public void updateDonneesArticle(DonneesArticle donneesArticle) {
        try {
            PreparedStatement ps = dao.connection.prepareStatement("UPDATE donnees_article SET nom = ?, quantite = ? WHERE id = ?");
            ps.setString(1, donneesArticle.getNom());
            ps.setInt(2, donneesArticle.getQuantite());
            ps.setInt(3, donneesArticle.getId());
            dao.ecrire(ps);
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteDonneesArticle(int id) {
        try {
            PreparedStatement ps = dao.connection.prepareStatement("DELETE FROM donnees_article WHERE id = ?");
            ps.setInt(1, id);
            dao.ecrire(ps);
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
