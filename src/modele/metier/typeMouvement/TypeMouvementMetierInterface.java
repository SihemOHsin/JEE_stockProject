package modele.metier.typeMouvement;

import java.util.List;
import modele.domaine.TypeMouvement;

public interface TypeMouvementMetierInterface {
    List<TypeMouvement> listTypesMouvement();
    TypeMouvement getTypeMouvementById(int id);
}
