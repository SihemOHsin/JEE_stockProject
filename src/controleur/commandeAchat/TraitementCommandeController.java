package controleur.commandeAchat;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import modele.domaine.CommandeAchat;
import modele.domaine.Article;
import modele.metier.achat.CommandeAchatMetierImpl;
import modele.metier.achat.CommandeAchatMetierInterface;

/**
 * Servlet implementation class TraitementCommandeController
 */
@WebServlet("/TraitementCommandeController")
public class TraitementCommandeController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    CommandeAchatMetierInterface commandeAchatMetier=null;

    @Override
    public void init() throws ServletException {
        super.init();
        commandeAchatMetier = new CommandeAchatMetierImpl();
    }
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TraitementCommandeController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		List<CommandeAchat> commandeAchats = commandeAchatMetier.getCommandeAchats();

        request.setAttribute("commandeAchats", commandeAchats);

        request.getRequestDispatcher("AchatList.jsp").forward(request, response);
    	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		List<String> erreurs = new ArrayList<>();

        String commandeAchatIdStr = request.getParameter("commandeAchatId");
        String nomFournisseur = request.getParameter("nomFournisseur");
        String dateCommande = request.getParameter("dateCommande");


        if (commandeAchatIdStr == null || commandeAchatIdStr.isEmpty()) {
            erreurs.add("Veuillez remplir le champ ID de la commande");
        }

        int commandeAchatId = 0;
        try {
            commandeAchatId = Integer.parseInt(commandeAchatIdStr);
        } catch (NumberFormatException e) {
            erreurs.add("ID de commande invalide, veuillez entrer un nombre valide");
        }

        if (nomFournisseur == null || nomFournisseur.isEmpty()) {
            erreurs.add("Veuillez remplir le champ Nom du fournisseur");
        }
        if (dateCommande == null || dateCommande.isEmpty()) {
            erreurs.add("Veuillez remplir le champ Date de commande");
        }
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");

        Date parsedDate = null;
        try {
            parsedDate = simpleDateFormat.parse(dateCommande);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        if (!erreurs.isEmpty()) {
            request.setAttribute("erreurs", erreurs);
            request.getRequestDispatcher("creerCommande.jsp").forward(request, response);
        } else {
            CommandeAchat commandeAchat = new CommandeAchat();
            commandeAchat.setId(commandeAchatId);
            commandeAchat.setNomFournisseur(nomFournisseur);
            commandeAchat.setDateCommande(parsedDate);

            List<Article> articles = new ArrayList<>();
            int articleCount = 1;
            String articleParam = "article" + articleCount;
            String quantiteParam = "quantite" + articleCount;

            while (request.getParameter(articleParam) != null) {
                String articleName = request.getParameter(articleParam);
                String quantiteStr = request.getParameter(quantiteParam);

                Article article = new Article();
                article.setNom(articleName);
                article.setQuantite(Integer.parseInt(quantiteStr));
                commandeAchatMetier.addArticleToCommandeAchat(commandeAchat, article);

                //articles.add(article);

                articleCount++;
                articleParam = "article" + articleCount;
                quantiteParam = "quantite" + articleCount;
            }

            commandeAchat.setArticles(articles);

            commandeAchatMetier.traiterCommandeAchat(commandeAchat);

            HttpSession session = request.getSession();
            session.setAttribute("successMessage", "CommandeAchat créée avec succès");

            response.sendRedirect("AchatList.jsp");
        }
    	}

}
