package modele.metier.bonDeCommande;

import java.util.List;
import modele.domaine.BonDeCommande;
import modele.dao.DaoInterface;
import modele.dao.DaoImpl;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class BonDeCommandeMetierImpl implements BonDeCommandeMetierInterface {
    private DaoInterface dao;

    public BonDeCommandeMetierImpl() {
        dao = new DaoImpl();
    }

    @Override
    public List<BonDeCommande> listBonDeCommande() {
        List<BonDeCommande> bonDeCommandes = new ArrayList<>();
        try {
            PreparedStatement ps = dao.connection.prepareStatement("SELECT * FROM bon_de_commande");
            ResultSet rs = dao.lire(ps);
            if (rs != null) {
                while (rs.next()) {
                    BonDeCommande bonDeCommande = new BonDeCommande();
                    bonDeCommande.setId(rs.getInt("id"));
                    bonDeCommande.setClient(rs.getString("client"));
                    bonDeCommandes.add(bonDeCommande);
                }
            }
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return bonDeCommandes;
    }

    @Override
    public void addBonDeCommande(BonDeCommande bonDeCommande) {
        try {
            PreparedStatement ps = dao.connection.prepareStatement("INSERT INTO bon_de_commande (order_data) VALUES (?)");
            ps.setString(1, bonDeCommande.getClient());
            dao.ecrire(ps);
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public BonDeCommande getBonDeCommandeById(int id) {
        BonDeCommande bonDeCommande = null;
        try {
            PreparedStatement ps = dao.connection.prepareStatement("SELECT * FROM bon_de_commande WHERE id = ?");
            ps.setInt(1, id);
            ResultSet rs = dao.lire(ps);
            if (rs != null && rs.next()) {
                bonDeCommande = new BonDeCommande();
                bonDeCommande.setId(rs.getInt("id"));
                bonDeCommande.setClient(rs.getString("Client"));
            }
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return bonDeCommande;
    }

    @Override
    public void updateBonDeCommande(BonDeCommande bonDeCommande) {
        try {
            PreparedStatement ps = dao.connection.prepareStatement("UPDATE bon_de_commande SET order_data = ? WHERE id = ?");
            ps.setString(1, bonDeCommande.getClient());
            ps.setInt(2, bonDeCommande.getId());
            dao.ecrire(ps);
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteBonDeCommande(int id) {
        try {
            PreparedStatement ps = dao.connection.prepareStatement("DELETE FROM bon_de_commande WHERE id = ?");
            ps.setInt(1, id);
            dao.ecrire(ps);
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
