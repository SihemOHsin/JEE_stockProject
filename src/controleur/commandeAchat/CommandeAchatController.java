package controleur.commandeAchat;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import modele.domaine.Article;
import modele.domaine.CommandeAchat;
import modele.metier.achat.CommandeAchatMetierImpl;
import modele.metier.achat.CommandeAchatMetierInterface;
import modele.metier.article.ArticleMetierImpl;
import modele.metier.article.ArticleMetierInterface;

@WebServlet("/CommandeAchatController")
public class CommandeAchatController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    CommandeAchatMetierInterface commandeAchatMetier = null;
    ArticleMetierInterface articleMetier = null;

    @Override
    public void init() throws ServletException {
        commandeAchatMetier = new CommandeAchatMetierImpl();
        articleMetier = new ArticleMetierImpl();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String commandeAchatIdParam = request.getParameter("commandeAchatId");
        if (commandeAchatIdParam != null && !commandeAchatIdParam.isEmpty()) {
            int commandeAchatId = Integer.parseInt(commandeAchatIdParam);
            CommandeAchat commandeAchat = commandeAchatMetier.getCommandeAchatById(commandeAchatId);
            if (commandeAchat != null) {
                request.setAttribute("commandeAchat", commandeAchat);
                request.setAttribute("commandeAchatId", commandeAchatId);
                request.getRequestDispatcher("commandeAchatDetails.jsp").forward(request, response);
                return; // Exit the method after forwarding
            }
        }

        List<CommandeAchat> commandeAchats = commandeAchatMetier.getCommandeAchats();
        List<Article> articles = articleMetier.listArticles(); // Retrieve the list of articles
        request.setAttribute("commandeAchats", commandeAchats);
        request.setAttribute("articles", articles);
        request.getRequestDispatcher("AchatList.jsp").forward(request, response);
    }



	/*
	 * protected void doPost(HttpServletRequest request, HttpServletResponse
	 * response) throws ServletException, IOException { String action =
	 * request.getParameter("action");
	 * 
	 * if (action != null) { switch (action) { case "traiter":
	 * traiterCommandeAchat(request, response); break; case "addArticle":
	 * addArticleToCommandeAchat(request, response); break; case "removeArticle":
	 * removeArticleFromCommandeAchat(request, response); break; default:
	 * response.sendRedirect("CommandeAchatController"); break; } } else {
	 * response.sendRedirect("CommandeAchatController"); } }
	 * 
	 * private void traiterCommandeAchat(HttpServletRequest request,
	 * HttpServletResponse response) throws ServletException, IOException { int
	 * commandeAchatId = Integer.parseInt(request.getParameter("commandeAchatId"));
	 * 
	 * CommandeAchat commandeAchat = new CommandeAchat();
	 * commandeAchat.setId(commandeAchatId);
	 * 
	 * commandeAchatMetier.traiterCommandeAchat(commandeAchat);
	 * 
	 * response.sendRedirect("CommandeAchatController"); }
	 */

	/*
	 * private void addArticleToCommandeAchat(HttpServletRequest request,
	 * HttpServletResponse response) throws ServletException, IOException { int
	 * commandeAchatId = Integer.parseInt(request.getParameter("commandeAchatId"));
	 * int articleId = Integer.parseInt(request.getParameter("articleId"));
	 * 
	 * CommandeAchat commandeAchat = new CommandeAchat();
	 * commandeAchat.setId(commandeAchatId);
	 * 
	 * Article article = new Article(); article.setId(articleId);
	 * 
	 * commandeAchatMetier.addArticleToCommandeAchat(commandeAchat, article);
	 * 
	 * response.sendRedirect("CommandeAchatController"); }
	 * 
	 * private void removeArticleFromCommandeAchat(HttpServletRequest request,
	 * HttpServletResponse response) throws ServletException, IOException { int
	 * commandeAchatId = Integer.parseInt(request.getParameter("commandeAchatId"));
	 * int articleId = Integer.parseInt(request.getParameter("articleId"));
	 * 
	 * CommandeAchat commandeAchat = new CommandeAchat();
	 * commandeAchat.setId(commandeAchatId);
	 * 
	 * Article article = new Article(); article.setId(articleId);
	 * 
	 * commandeAchatMetier.removeArticleFromCommandeAchat(commandeAchat, article);
	 * 
	 * response.sendRedirect("CommandeAchatController"); }
	 */
}
