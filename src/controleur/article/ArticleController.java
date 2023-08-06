package controleur.article;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

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
 * Servlet implementation class ArticleController
 */
@WebServlet("/ArticleController")
public class ArticleController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private ArticleMetierInterface articleMetier;

    @Override
    public void init() throws ServletException {
        articleMetier = new ArticleMetierImpl();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");

        if (action != null) {
            switch (action) {
                case "view":
                    viewArticle(request, response);
                    break;
                case "delete":
                    deleteArticle(request, response);
                    break;
                case "update":
                    updateArticleForm(request, response);
                    break;
            }
        } else {
            List<Article> articles = articleMetier.listArticles();
            request.setAttribute("articles", articles);
            request.getRequestDispatcher("article.jsp").forward(request, response);
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");

        if (action != null) {
            switch (action) {
                case "add":
                    addArticle(request, response);
                    break;
                case "update":
                    updateArticle(request, response);
                    break;
            }
        } else {
            response.sendRedirect("ArticleController");
        }
    }

    private void viewArticle(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String id = request.getParameter("id");
        Article article = articleMetier.getArticleById(Integer.parseInt(id));

        if (article != null) {
            request.setAttribute("article", article);
            request.getRequestDispatcher("articleDetails.jsp").forward(request, response);
        } else {
            response.sendRedirect("ArticleController");
        }
    }

    private void deleteArticle(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));

        articleMetier.updateChildTableReferences(id);

        articleMetier.deleteArticle(id);

        HttpSession session = request.getSession();
        session.setAttribute("successMessage", "Article deleted successfully enfiiin");

        response.sendRedirect("ArticleController");

    }

    private void updateArticleForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String idStr = request.getParameter("id");
        if (idStr != null && !idStr.isEmpty()) {
            int id = Integer.parseInt(idStr);
            Article article = articleMetier.getArticleById(id);

            if (article != null) {
                request.setAttribute("article", article);
                request.getRequestDispatcher("EditArticle.jsp").forward(request, response);
                return; 
            }
        }

        response.sendRedirect("ArticleController");
    }


    private void addArticle(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ArrayList<String> erreurs = new ArrayList<>();

        String nom = request.getParameter("nom");
        String quantiteStr = request.getParameter("quantite");

        if (nom == null || nom.isEmpty()) {
            erreurs.add("Please fill in the name");
        }

        int quantite = 0;
        if (quantiteStr == null || quantiteStr.isEmpty()) {
            erreurs.add("Please fill in the quantity");
        } else {
            try {
                quantite = Integer.parseInt(quantiteStr);
            } catch (NumberFormatException e) {
                erreurs.add("Invalid quantity. Please enter a valid numero");
            }
        }

        if (!erreurs.isEmpty()) {
            request.setAttribute("errors", erreurs);
            request.setAttribute("nom", nom);
            request.setAttribute("quantite", quantiteStr);

            request.getRequestDispatcher("ArticleController").forward(request, response);
        } else {
            Article article = new Article();
            article.setNom(nom);
            article.setQuantite(quantite);

            articleMetier.addArticle(article);

            HttpSession session = request.getSession();
            session.setAttribute("successMessage", "Article added success");

            response.sendRedirect("ArticleController");
        }
    }

    private void updateArticle(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    	List<String> errors = new ArrayList<>();

        String idStr = request.getParameter("id");
        String nom = request.getParameter("nom");
        String quantiteStr = request.getParameter("quantite");

        System.out.println("ID: " + idStr);
        System.out.println("Nom: " + nom);
        System.out.println("Quantite: " + quantiteStr);

        if (idStr == null || idStr.isEmpty()) {
            errors.add("Article ID is missing");
        }

        if (nom == null || nom.isEmpty()) {
            errors.add("Please fill in the name");
        }

        if (quantiteStr == null || quantiteStr.isEmpty()) {
            errors.add("Please fill in the quantity");
        }

        int id = 0;
        int quantite = 0;

        if (idStr != null && !idStr.isEmpty()) {
            try {
                id = Integer.parseInt(idStr);
            } catch (NumberFormatException e) {
                errors.add("Invalid article ID. Please enter a valid number");
            }
        }

        if (quantiteStr != null && !quantiteStr.isEmpty()) {
            try {
                quantite = Integer.parseInt(quantiteStr);
            } catch (NumberFormatException e) {
                errors.add("Invalid quantity. Please enter a valid number");
            }
        }

        System.out.println("ID (after parsing): " + id);
        System.out.println("Quantite (after parsing): " + quantite);

        if (!errors.isEmpty()) {
            request.setAttribute("errors", errors);
            request.setAttribute("id", idStr);
            request.setAttribute("nom", nom);
            request.setAttribute("quantite", quantiteStr);

            request.getRequestDispatcher("EditArticle.jsp").forward(request, response);
        } else {
            Article article = new Article();
            article.setId(id);
            article.setNom(nom);
            article.setQuantite(quantite);

            articleMetier.updateArticle(article);

            HttpSession session = request.getSession();
            session.setAttribute("successMessage", "Article updated successfully");

            //response.sendRedirect("StockList.jsp ");
            response.sendRedirect("ArticleController");

        }
    }}