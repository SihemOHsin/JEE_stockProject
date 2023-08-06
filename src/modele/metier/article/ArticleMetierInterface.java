package modele.metier.article;

import java.util.List;

import modele.domaine.Article;

public interface ArticleMetierInterface {

	List<Article> listArticles();
    void addArticle(Article a);
    Article getArticleById(int id);
    void updateArticle(Article a);
    void deleteArticle(int id);
	void updateChildTableReferences(int articleId);
}
