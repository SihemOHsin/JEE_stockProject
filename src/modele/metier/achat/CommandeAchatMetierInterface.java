package modele.metier.achat;

import modele.domaine.Article;
import modele.domaine.CommandeAchat;
import java.util.List;

public interface CommandeAchatMetierInterface {
    void traiterCommandeAchat(CommandeAchat commandeAchat);
    void addArticleToCommandeAchat(CommandeAchat commandeAchat, Article article);
    void removeArticleFromCommandeAchat(CommandeAchat commandeAchat, Article article);
    List<CommandeAchat> getCommandeAchats();
    CommandeAchat getCommandeAchatById(int commandeAchatId);
    
}
