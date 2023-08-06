package modele.metier.achat;

import java.util.ArrayList;
import java.util.List;
import modele.dao.DaoInterface;
import modele.dao.DaoImpl;
import modele.domaine.Article;
import modele.domaine.CommandeAchat;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CommandeAchatMetierImpl implements CommandeAchatMetierInterface {
    private DaoInterface dao;

    public CommandeAchatMetierImpl() {
        dao = new DaoImpl();
    }

    @Override
    public void traiterCommandeAchat(CommandeAchat commandeAchat) {
        try {
            int commandeAchatId = commandeAchat.getId();
            List<Article> articles = commandeAchat.getArticles();

            saveCommandeAchat(commandeAchat);

            for (Article article : articles) {
                addArticleToCommandeAchat(commandeAchatId, article);
            }
        } catch (IllegalArgumentException e) {
            System.out.println("Erreur traiterCommandeAchat: commande achat with the same id already exists");
            e.printStackTrace();
        }
    }


    private void saveCommandeAchat(CommandeAchat commandeAchat) {
    	try {
    		// check if dao  null
            if (dao == null) {
                dao = new DaoImpl(); 
            }
            // Check if an existing record with the same id already exists
            CommandeAchat existingCommandeAchat = getCommandeAchatById(commandeAchat.getId());
            if (existingCommandeAchat != null) {
                // Existing record found, perform an update operation instead of insert
                PreparedStatement updatePs = dao.connection.prepareStatement("UPDATE commandeachat SET nomFournisseur=?, dateCommande=? WHERE id=?");
                updatePs.setString(1, commandeAchat.getNomFournisseur());
                updatePs.setDate(2, new java.sql.Date(commandeAchat.getDateCommande().getTime()));
                updatePs.setInt(3, commandeAchat.getId());
                dao.ecrire(updatePs);
                updatePs.close();
                
                throw new IllegalArgumentException("commandeAchat with the same id already exists");
            } else {
                // New record, perform insert operation
                PreparedStatement insertPs = dao.connection.prepareStatement("INSERT INTO commandeachat (id, nomFournisseur, dateCommande) VALUES (?, ?, ?)");
                insertPs.setInt(1, commandeAchat.getId());
                insertPs.setString(2, commandeAchat.getNomFournisseur());
                insertPs.setDate(3, new java.sql.Date(commandeAchat.getDateCommande().getTime()));
                dao.ecrire(insertPs);
                insertPs.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
/*            PreparedStatement ps = dao.connection.prepareStatement("INSERT INTO commandeachat (id, nomFournisseur, dateCommande) VALUES (?, ?, ?)");
            ps.setInt(1, commandeAchat.getId());
            ps.setString(2, commandeAchat.getNomFournisseur());
            ps.setDate(3, new java.sql.Date(commandeAchat.getDateCommande().getTime()));
            dao.ecrire(ps);
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }*/
    }

    private void addArticleToCommandeAchat(int commandeAchatId, Article article) {
        try {
            PreparedStatement ps = dao.connection.prepareStatement("INSERT INTO commandeachat_article (commandeAchatId, articleId) VALUES (?, ?)");
            ps.setInt(1, commandeAchatId);
            ps.setInt(2, article.getId());
            dao.ecrire(ps);
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void addArticleToCommandeAchat(CommandeAchat commandeAchat, Article article) {
        int commandeAchatId = commandeAchat.getId();
        
        try {
            addArticleToCommandeAchat(commandeAchatId, article);
        } catch (Exception e) {
        	System.out.println("erreur in addArticleToCommandeAchat");
            e.printStackTrace();
        }
    }


    @Override
    public void removeArticleFromCommandeAchat(CommandeAchat commandeAchat, Article article) {
        int commandeAchatId = commandeAchat.getId();
        int articleId = article.getId();

        try {
            PreparedStatement ps = dao.connection.prepareStatement("DELETE FROM commandeachat_article WHERE commandeAchatId = ? AND articleId = ?");
            ps.setInt(1, commandeAchatId);
            ps.setInt(2, articleId);
            int rowsAffected = ps.executeUpdate();
            ps.close();
//if there is no article associated with that command prevent the system from crashing
            if (rowsAffected == 0) {
                throw new IllegalArgumentException("the specified article is not associated with the provided commandeAchat");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //******************** calculer quantite 
    @Override
    public List<CommandeAchat> getCommandeAchats() {
        List<CommandeAchat> commandeAchats = new ArrayList<>();
        try {
            PreparedStatement ps = dao.connection.prepareStatement("SELECT c.id, c.nomFournisseur, c.dateCommande, COUNT(a.id) AS quantite, a.id AS articleId, a.nom AS articleNom, a.quantite AS articleQuantite FROM commandeachat c LEFT JOIN commandeachat_article ca ON c.id = ca.commandeAchatId LEFT JOIN article a ON ca.articleId = a.id GROUP BY c.id, c.nomFournisseur, c.dateCommande");
            ResultSet rs = dao.lire(ps);
            if (rs != null) {
                CommandeAchat commandeAchat = null;
                while (rs.next()) {
                    int commandeAchatId = rs.getInt("id");
                    if (commandeAchat == null || commandeAchat.getId() != commandeAchatId) {
                        commandeAchat = new CommandeAchat();
                        commandeAchat.setId(commandeAchatId);
                        commandeAchat.setNomFournisseur(rs.getString("nomFournisseur"));
                        commandeAchat.setDateCommande(rs.getDate("dateCommande"));
                        commandeAchat.setQuantite(rs.getInt("quantite"));
                        commandeAchat.setArticles(new ArrayList<>());
                        commandeAchats.add(commandeAchat);
                    }
                    Article article = new Article();
                    article.setId(rs.getInt("articleId"));
                    article.setNom(rs.getString("articleNom"));
                    article.setQuantite(rs.getInt("articleQuantite"));
                    commandeAchat.getArticles().add(article);
                }
            }
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return commandeAchats;
    }

    //********************* add jointure
	/*
	 * @Override public List<CommandeAchat> getCommandeAchats() {
	 * List<CommandeAchat> commandeAchats = new ArrayList<>(); try {
	 * PreparedStatement ps = dao.connection.
	 * prepareStatement("SELECT c.id, c.nomFournisseur, c.dateCommande, a.id, a.nom, a.quantite FROM commandeachat c LEFT JOIN commandeachat_article ca ON c.id = ca.commandeAchatId LEFT JOIN article a ON ca.articleId = a.id"
	 * ); ResultSet rs = dao.lire(ps); if (rs != null) { int previousCommandeAchatId
	 * = -1; CommandeAchat commandeAchat = null; while (rs.next()) { int
	 * currentCommandeAchatId = rs.getInt("c.id"); if (currentCommandeAchatId !=
	 * previousCommandeAchatId) { // Create a new CommandeAchat object commandeAchat
	 * = new CommandeAchat(); commandeAchat.setId(currentCommandeAchatId);
	 * commandeAchat.setNomFournisseur(rs.getString("c.nomFournisseur"));
	 * commandeAchat.setDateCommande(rs.getDate("c.dateCommande"));
	 * commandeAchat.setArticles(new ArrayList<>());
	 * commandeAchats.add(commandeAchat); } if (commandeAchat != null) { // Create
	 * an Article object and add it to the CommandeAchat's articles list Article
	 * article = new Article(); article.setId(rs.getInt("a.id"));
	 * article.setNom(rs.getString("a.nom"));
	 * article.setQuantite(rs.getInt("a.quantite"));
	 * commandeAchat.getArticles().add(article); } previousCommandeAchatId =
	 * currentCommandeAchatId; } } ps.close(); } catch (SQLException e) {
	 * e.printStackTrace(); } return commandeAchats; }
	 */

//***************original one
	/*
	 * @Override public List<CommandeAchat> getCommandeAchats() {
	 * List<CommandeAchat> commandeAchats = new ArrayList<>(); try {
	 * PreparedStatement ps =
	 * dao.connection.prepareStatement("SELECT * FROM commandeachat"); ResultSet rs
	 * = dao.lire(ps); if (rs != null) { while (rs.next()) { CommandeAchat
	 * commandeAchat = new CommandeAchat(); commandeAchat.setId(rs.getInt("id"));
	 * commandeAchat.setNomFournisseur(rs.getString("nomFournisseur"));
	 * commandeAchat.setDateCommande(rs.getDate("dateCommande"));
	 * commandeAchat.setArticles(getArticlesForCommandeAchat(commandeAchat));
	 * 
	 * commandeAchats.add(commandeAchat); } } ps.close(); } catch (SQLException e) {
	 * e.printStackTrace(); } return commandeAchats; }
	 */
    private List<Article> getArticlesForCommandeAchat(CommandeAchat commandeAchat) {
        List<Article> articles = new ArrayList<>();
        try {
            PreparedStatement ps = dao.connection.prepareStatement("SELECT * FROM commandeachat_article WHERE commandeAchatId = ?");
            ps.setInt(1, commandeAchat.getId());
            ResultSet rs = dao.lire(ps);
            if (rs != null) {
                while (rs.next()) {
                    Article article = new Article();
                    article.setId(rs.getInt("articleId"));

                    articles.add(article);
                }
            }
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return articles;
    }

    @Override
    public CommandeAchat getCommandeAchatById(int commandeAchatId) {
        CommandeAchat commandeAchat = null;
        try {
            // Join CommandeAchat => associated Article 
            PreparedStatement ps = dao.connection.prepareStatement("SELECT c.id, c.nomFournisseur, c.dateCommande, a.id, a.nom, a.quantite FROM commandeachat c LEFT JOIN commandeachat_article ca ON c.id = ca.commandeAchatId LEFT JOIN article a ON ca.articleId = a.id WHERE c.id = ?");

            ps.setInt(1, commandeAchatId);
            ResultSet rs = dao.lire(ps);
            if (rs != null && rs.next()) {
                commandeAchat = new CommandeAchat();
                commandeAchat.setId(rs.getInt("id"));
                commandeAchat.setNomFournisseur(rs.getString("nomFournisseur"));
                commandeAchat.setDateCommande(rs.getDate("dateCommande"));

                List<Article> articles = new ArrayList<>();
                do {
                    Article article = new Article();
                    article.setId(rs.getInt("id"));
                    article.setNom(rs.getString("nom"));
                    article.setQuantite(rs.getInt("quantite"));

                    articles.add(article);
                } while (rs.next());

                commandeAchat.setArticles(articles);
            }
            ps.close();
        } catch (SQLException e) {
        	System.out.println("erreur getCommandeAchatById ");
            e.printStackTrace();
        }
        return commandeAchat;
    }
}

/*
 * List<Product> productList = new ArrayList<Product>(); try { // Préparer la
 * requête SQL String query =
 * "SELECT p.id AS id, p.code AS code, p.designation AS designation, " +
 * "p.prix AS prix, p.categorie_id AS categorie_id, c.libelle AS libelle, " +
 * "p.actif AS actif, p.dateAchat AS dateAchat, p.dateExpiration AS dateExpiration "
 * + "FROM Produit p, Categorie c " + "WHERE p.categorie_id = c.id " +
 * "AND p.user_id = ?";
 * 
 * PreparedStatement ps = dao.connection.prepareStatement(query); ps.setInt(1,
 * userId); ResultSet rs = dao.lire(ps); while (rs != null && rs.next()) {
 * Product product = new Product(); product.setId(rs.getInt("id"));
 * product.setCode(rs.getString("code"));
 * product.setDesignation(rs.getString("designation"));
 * product.setPrix(rs.getDouble("prix"));
 * product.setActif(rs.getBoolean("actif"));
 * product.setCategorie_id(rs.getInt("categorie_id"));
 * product.setLibelle(rs.getString("libelle"));
 * product.setDateAchat(rs.getDate("dateAchat"));
 * product.setDateExpiration(rs.getDate("dateExpiration"));
 * 
 * productList.add(product); } ps.close(); } catch (SQLException e) {
 * e.printStackTrace(); } return productList; }
 */
