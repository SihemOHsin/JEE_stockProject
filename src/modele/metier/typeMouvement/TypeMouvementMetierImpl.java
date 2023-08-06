package modele.metier.typeMouvement;

import java.util.ArrayList;
import java.util.List;
import modele.domaine.TypeMouvement;

public class TypeMouvementMetierImpl implements TypeMouvementMetierInterface {

    @Override
    public List<TypeMouvement> listTypesMouvement() {
        List<TypeMouvement> typesMouvement = new ArrayList<>();
        
        typesMouvement.add(TypeMouvement.ENTREE);
        typesMouvement.add(TypeMouvement.SORTIE);
        return typesMouvement;
    }

    @Override
    public TypeMouvement getTypeMouvementById(int id) {
        
        if (id == 1) {
            return TypeMouvement.ENTREE;
        } else if (id == 2) {
            return TypeMouvement.SORTIE;
        }
        return null;
    }
}
