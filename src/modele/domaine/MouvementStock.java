package modele.domaine;

public class MouvementStock {
	private int id;
    private Article article=null;
    private TypeMouvement typeMouvement=null;
    
	public MouvementStock() {
		super();
	}
	public MouvementStock(int id, Article article, TypeMouvement typeMouvement) {
		super();
		this.id = id;
		this.article = article;
		this.typeMouvement = typeMouvement;
	}
	public MouvementStock(Article article, TypeMouvement typeMouvement) {
		super();
		this.article = article;
		this.typeMouvement = typeMouvement;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Article getArticle() {
		return article;
	}
	public void setArticle(Article article) {
		this.article = article;
	}
	public TypeMouvement getTypeMouvement() {
		return typeMouvement;
	}
	public void setTypeMouvement(TypeMouvement typeMouvement) {
		this.typeMouvement = typeMouvement;
	}
	@Override
	public String toString() {
		return "MouvementStock [id=" + id + ", article=" + article + ", typeMouvement=" + typeMouvement + "]";
	}

}
