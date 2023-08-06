package modele.metier.vente;

import modele.dao.DaoImpl;
import modele.dao.DaoInterface;
import modele.domaine.CommandeVente;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import modele.domaine.Article;
import modele.domaine.BonLivraison;


public class CommandeVenteMetierImpl implements CommandeVenteMetierInterface {
    private DaoInterface dao;

    public CommandeVenteMetierImpl() {
        dao = new DaoImpl();
    }

    @Override
    public void traiterCommandeVente(CommandeVente commandeVente) {
        try {
            PreparedStatement ps = dao.connection.prepareStatement("INSERT INTO commande_vente (client) VALUES (?)");
            ps.setString(1, commandeVente.getClient());
            dao.ecrire(ps);
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void addArticleToCommandeVente(CommandeVente commandeVente, Article article) {
        int commandeVenteId = commandeVente.getId();
        addArticleToCommandeVente(commandeVenteId, article);
    }

    private void addArticleToCommandeVente(int commandeVenteId, Article article) {
        try {
            PreparedStatement ps = dao.connection.prepareStatement("INSERT INTO articles_commande_vente (commande_vente_id, article_id) VALUES (?, ?)");
            ps.setInt(1, commandeVenteId);
            ps.setInt(2, article.getId());
            dao.ecrire(ps);
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void removeArticleFromCommandeVente(CommandeVente commandeVente, Article article) {
        int commandeVenteId = commandeVente.getId();
        removeArticleFromCommandeVente(commandeVenteId, article);
    }

    private void removeArticleFromCommandeVente(int commandeVenteId, Article article) {
        try {
            PreparedStatement ps = dao.connection.prepareStatement("DELETE FROM articles_commande_vente WHERE commande_vente_id = ? AND article_id = ?");
            ps.setInt(1, commandeVenteId);
            ps.setInt(2, article.getId());
            dao.ecrire(ps);
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<CommandeVente> getCommandeVentes() {
        List<CommandeVente> commandeVentes = new ArrayList<>();
        try {
            PreparedStatement ps = dao.connection.prepareStatement("SELECT * FROM commande_vente");
            ResultSet rs = dao.lire(ps);
            if (rs != null) {
                while (rs.next()) {
                    CommandeVente commandeVente = new CommandeVente();
                    commandeVente.setId(rs.getInt("id"));
                    commandeVente.setClient(rs.getString("client"));
                    commandeVentes.add(commandeVente);
                }
            }
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return commandeVentes;
    }

    @Override
    public BonLivraison genererBonLivraison(CommandeVente commande) {
        // Creatation nouveau bn livraison
        BonLivraison bonLivraison = new BonLivraison();
        bonLivraison.setCommandeVente(commande);
        bonLivraison.setDateLivraison(new Date()); 

        //enregistrer dans db
        try {
        	// utilisation Statement.RETURN_GENERATED_KEYS
            PreparedStatement ps = dao.connection.prepareStatement("INSERT INTO bon_livraison (commande_vente_id, date_livraison) VALUES (?, ?)", Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, commande.getId());
            ps.setDate(2, new java.sql.Date(bonLivraison.getDateLivraison().getTime()));
            dao.ecrire(ps);
            
            // par generated key on peut retrieve le bn livraison id
            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
                int bonLivraisonId = rs.getInt(1);
                bonLivraison.setId(bonLivraisonId);
            }

            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return bonLivraison;
    }

    @Override
    public CommandeVente getCommandeVenteById(int id) {
        CommandeVente commandeVente = null;
        try {
            PreparedStatement ps = dao.connection.prepareStatement("SELECT * FROM commande_vente WHERE id = ?");
            ps.setInt(1, id);
            ResultSet rs = dao.lire(ps);
            if (rs != null && rs.next()) {
                commandeVente = new CommandeVente();
                commandeVente.setId(rs.getInt("id"));
                commandeVente.setClient(rs.getString("client"));
            }
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return commandeVente;
    }


}

