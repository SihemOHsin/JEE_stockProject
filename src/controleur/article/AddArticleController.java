package controleur.article;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import modele.domaine.Article;
import modele.metier.article.ArticleMetierImpl;
import modele.metier.article.ArticleMetierInterface;

/**
 * Servlet implementation class AddArticleController
 */
@WebServlet("/AddArticleController")
public class AddArticleController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    ArticleMetierInterface articleMetier = null;

    public void init() throws ServletException {
        articleMetier = new ArticleMetierImpl();
    }
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddArticleController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		    ArrayList<String> erreurs = new ArrayList<>();

		    PrintWriter out = response.getWriter();

		    String nom = request.getParameter("nom");
		    String quantiteStr = request.getParameter("quantite");

		    //test
		    out.println("La valeur du nom est:"+nom);	
		    out.println("La valeur du nom est:"+quantiteStr);	

		    if (nom == null || nom.isEmpty()) {
		        erreurs.add("Please fill in the Name field.");
		    }

		    int quantite = 0;
		    if (quantiteStr == null || quantiteStr.isEmpty()) {
		        erreurs.add("Please fill in the Quantity field.");
		    } else {
		        try {
		            quantite = Integer.parseInt(quantiteStr);
		        } catch (NumberFormatException e) {
		            erreurs.add("Invalid quantity, why?  Please enter a valid number now!");
		        }
		    }

		    if (erreurs.size() != 0) {
		        request.setAttribute("erreurs", erreurs);
		        request.setAttribute("nom", nom);
		        request.setAttribute("quantite", quantiteStr);

		        request.getRequestDispatcher("addEditArticle.jsp").forward(request, response);
		    } else {
		        // correct
		        Article article = new Article();
		        article.setNom(nom);
		        article.setQuantite(quantite);

		        articleMetier.addArticle(article);

		        HttpSession session = request.getSession();
		        session.setAttribute("successMessage", "gooooood , Article added successfully.");

		        response.sendRedirect("ListArticleController");
		    }
		}
}
