package modele.metier.bonDeLivraison;

import java.util.ArrayList;
import java.util.List;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


import modele.dao.DaoImpl;
import modele.dao.DaoInterface;
import modele.domaine.Article;
import modele.domaine.MouvementStock;
import modele.domaine.TypeMouvement;
import modele.metier.article.ArticleMetierImpl;
import modele.metier.vente.CommandeVenteMetierImpl;
import modele.metier.vente.CommandeVenteMetierInterface;
import modele.domaine.BonLivraison;
import modele.domaine.CommandeVente;

public class BonLivraisonMetierImpl implements BonLivraisonMetierInterface {
    private DaoInterface dao;

    public BonLivraisonMetierImpl() {
        dao = new DaoImpl();
    }

    @Override
    public List<BonLivraison> listBonsLivraison() {
        List<BonLivraison> bonsLivraison = new ArrayList<>();
        try {
            PreparedStatement ps = dao.connection.prepareStatement("SELECT * FROM bon_livraison");
            ResultSet rs = dao.lire(ps);
            if (rs != null) {
                while (rs.next()) {
                    BonLivraison bonLivraison = new BonLivraison();
                    bonLivraison.setId(rs.getInt("id"));
                    bonLivraison.setCommandeVente(getCommandeVenteById(rs.getInt("commande_vente_id")));
                    bonLivraison.setDateLivraison(rs.getDate("date_livraison"));
                    bonsLivraison.add(bonLivraison);
                }
            }
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return bonsLivraison;
    }

    @Override
    public void addBonLivraison(BonLivraison bonLivraison) {
        try {
            PreparedStatement ps = dao.connection.prepareStatement("INSERT INTO bon_livraison (commande_vente_id, date_livraison) VALUES (?, ?)");
            ps.setInt(1, bonLivraison.getCommandeVente().getId());
            ps.setDate(2, new java.sql.Date(bonLivraison.getDateLivraison().getTime()));
            dao.ecrire(ps);
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public BonLivraison getBonLivraisonById(int id) {
        BonLivraison bonLivraison = null;
        try {
            PreparedStatement ps = dao.connection.prepareStatement("SELECT * FROM bon_livraison WHERE id = ?");
            ps.setInt(1, id);
            ResultSet rs = dao.lire(ps);
            if (rs != null && rs.next()) {
                bonLivraison = new BonLivraison();
                bonLivraison.setId(rs.getInt("id"));
                bonLivraison.setCommandeVente(getCommandeVenteById(rs.getInt("commande_vente_id")));
                bonLivraison.setDateLivraison(rs.getDate("date_livraison"));
            }
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return bonLivraison;
    }

    @Override
    public void updateBonLivraison(BonLivraison bonLivraison) {
        try {
            PreparedStatement ps = dao.connection.prepareStatement("UPDATE bon_livraison SET commande_vente_id = ?, date_livraison = ? WHERE id = ?");
            ps.setInt(1, bonLivraison.getCommandeVente().getId());
            ps.setDate(2, new java.sql.Date(bonLivraison.getDateLivraison().getTime()));
            ps.setInt(3, bonLivraison.getId());
            dao.ecrire(ps);
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteBonLivraison(int id) {
        try {
            PreparedStatement ps = dao.connection.prepareStatement("DELETE FROM bon_livraison WHERE id = ?");
            ps.setInt(1, id);
            dao.ecrire(ps);
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    private CommandeVente getCommandeVenteById(int id) {
        CommandeVenteMetierInterface commandeVenteMetier = new CommandeVenteMetierImpl();
        return commandeVenteMetier.getCommandeVenteById(id);
    }
}
