package test;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.List;

import modele.domaine.Article;
import modele.domaine.CommandeAchat;
import modele.domaine.User;
import modele.metier.achat.CommandeAchatMetierImpl;
import modele.metier.article.ArticleMetierImpl;
import modele.metier.article.ArticleMetierInterface;
import modele.metier.user.UserMetierImpl;
import modele.metier.user.UserMetierInterface;

public class TestMetier {

	public static void main(String[] args) {
		
		/***********
		// it must be commandeAchat id unique but article id already exist
		**********/
		
		//test commande d'achjat
        CommandeAchatMetierImpl commandeAchatMetier = new CommandeAchatMetierImpl();
        CommandeAchat commandeAchat = new CommandeAchat();
        commandeAchat.setId(39);
        commandeAchat.setNomFournisseur("Supplier sihem");
        //SimpleDateFormat SimpleDate = new SimpleDateFormat("yyyy-MM-dd");
        commandeAchat.setDateCommande(Date.valueOf("2022-01-01"));
        
        Article article1 = new Article();
        article1.setId(2);
        article1.setNom("test commande d'achat1");
        article1.setQuantite(1);
        
		
		//  Article article2 = new Article(); article2.setId(2);
		// article2.setNom(" test commande d'achat2"); article2.setQuantite(5);
		 
        
        System.out.println("add........");
        commandeAchat.getArticles().add(article1);
       // commandeAchat.getArticles().add(article2);
        
        System.out.println("traiter........");

        commandeAchatMetier.traiterCommandeAchat(commandeAchat);
        
        System.out.println("addArticleToCommandeAchat........");
     // Add the Article to the CommandeAchat
        commandeAchatMetier.addArticleToCommandeAchat(commandeAchat, article1);
        System.out.println(("list..."));
        commandeAchatMetier.removeArticleFromCommandeAchat(commandeAchat, article1);
        System.out.println(("remove..."));
        List<CommandeAchat> commandeAchats = commandeAchatMetier.getCommandeAchats();
        System.out.println(commandeAchats);
        commandeAchatMetier.getCommandeAchatById(1);
/*
        System.out.println("List :");
        for (CommandeAchat cmdAchat : commandeAchatMetier.getCommandeAchats()) {
            System.out.println("commandeAchat id: " + cmdAchat.getId());
            System.out.println("supplier: " + cmdAchat.getNomFournisseur());
            System.out.println("date: " + cmdAchat.getDateCommande());

            System.out.println("Articles:");
            for (Article article : cmdAchat.getArticles()) {
                System.out.println("Article id: " + article.getId());
                System.out.println("Article name: " + article.getNom());
                System.out.println("Article quantity: " + article.getQuantite());
            }
*/
            System.out.println("-----------------------------");
        }
	}
        
//		//Test Article done
//		
//		ArticleMetierInterface metierArticle= new ArticleMetierImpl();
//		
//		int articleId = 19; 
//		Article article = metierArticle.getArticleById(articleId);
//		System.out.println("artcile by id");
//		System.out.println(article);
//
//		Article articleToUpdate = metierArticle.getArticleById(articleId);
//		articleToUpdate.setNom("Updated Article");
//		articleToUpdate.setQuantite(15);
//		metierArticle.updateArticle(articleToUpdate);
//
//		int articleIdToDelete = 12; // ID of the article to delete
//		metierArticle.deleteArticle(articleIdToDelete);
//
//		metierArticle.addArticle(new Article("test final", 1));
//
//		List<Article> articles = metierArticle.listArticles();
//		for (Article a: articles)
//		{
//			System.out.println(a);
//		}
//		/*
//		 * article.setNom("Article test"); article.setQuantite(20);
//		 * metierArticle.addArticle(article);
//		 */
//		
//		System.out.println("------------------------------\n");
//		
//		
//		//test user
//		
//		UserMetierInterface metier= new UserMetierImpl();
//		//Test d'ajout
//		metier.addUser(new User ("Ben Saleh", "Mohamed","11","22"));
//		System.out.println("------------------------------\n");
//		//Test d'affichage de la liste totale des objets "User"
//		List<User> users = metier.listUsers();
//		for (User u: users)
//		{
//			System.out.println(u);
//		}
//		System.out.println("------------------------------\n");
//		//Test d'affichage d'un objet "User" en donnant le login et le password
//		User u = metier.getUserByLoginAndPassword("11", "22");
//		System.out.println(u);
//		System.out.println("------------------------------\n");
//		//Tester la mise à jour
//		u.setNom("Sallemi");
//		metier.updateUser(u);
//		List<User> users2 = metier.listUsers();
//		for (User u2: users2)
//		{
//			System.out.println(u2);
//		}
//		System.out.println("------------------------------\n");
//		
//		
//
//	}
        

