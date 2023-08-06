package controleur.article;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import modele.domaine.Article;
import modele.domaine.User;
import modele.metier.article.ArticleMetierImpl;
import modele.metier.article.ArticleMetierInterface;

/**
 * Servlet implementation class ListArticleController
 */
@WebServlet("/ListArticleController")
public class ListArticleController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	ArticleMetierInterface articleMetier = null;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ListArticleController() {
        super();
        // TODO Auto-generated constructor stub
    }
    public void init(ServletConfig config) throws ServletException {
		articleMetier = new ArticleMetierImpl();
	}
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(true);
        String id = request.getParameter("id");
        String nom = request.getParameter("nom");
        String quantite = request.getParameter("quantite");
        if (id == null) {
            id = "";
        }
        if (nom == null) {
            nom = "";
        }
        if (quantite == null) {
            quantite = "";
        }
        List<Article> articles = articleMetier.listArticles();
        session.setAttribute("articles", articles);

        request.getRequestDispatcher("StockList.jsp").forward(request, response);
    }



	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
    

}
