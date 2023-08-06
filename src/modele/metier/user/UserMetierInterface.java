package modele.metier.user;

import java.util.List;

import modele.domaine.User;

//liste des traitements métier sur un "User"
public interface UserMetierInterface 
{
	//Ajouter un objet "User"
	public void addUser(User u);
	//Retourner la liste de tous les objets "User"
	public List<User> listUsers();
	//Retourner la liste de tous les objets "User" par "nom" ou "prenom"
	public List<User> listUsersByNomOrPrenom(String motDeRecherche);
	//Retourner l'objet "User" ayant le login et le password passés en paramètres
	public User getUserByLoginAndPassword(String l, String p);
	//Mettre à jour un objet "User" déjà existant
	public void updateUser(User u);
	//Supprimer un objet "User" identifié par son "id"
	public void deleteUser(int id);
	//Retourner un objet "User" par id
	public User getUserById (int id);
	
}
