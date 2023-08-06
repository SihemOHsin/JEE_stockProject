package modele.metier.user;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import modele.dao.DaoImpl;
import modele.dao.DaoInterface;
import modele.domaine.User;

public class UserMetierImpl implements UserMetierInterface{
	
	private DaoInterface dao = null;	
	
	public UserMetierImpl() {
		 dao = new DaoImpl();
	}
	@Override
	public void addUser(User u) {
		
		
		
		try {
			// pr�parer la requ�te SQL
			PreparedStatement ps = dao.connection.prepareStatement(" insert into user values (0,?,?,?,?)");
			// passer les param�tres
			ps.setString(1, u.getNom());
			ps.setString(2, u.getPrenom());
			ps.setString(3, u.getLogin());
			ps.setString(4, u.getPassword());
			// executer la requ�te
			dao.ecrire(ps);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	@Override
	public List<User> listUsers() {
		//D�finir une liste vide pour stocker les objets "User"
		List<User> users= new ArrayList<User>();
	
		try {
			// pr�parer la requ�te SQL
			PreparedStatement ps = dao.connection.prepareStatement(" select * from User");
			// R�cup�rer le r�sultat de la requ�te
			ResultSet rs= dao.lire(ps);
			if (rs!=null)
			{
				//parcourir le r�sultat 
				while(rs.next())
				{
					//Construire un objet "User" puis lui affecter les attributs
					// et enfin l'ajouter dans la liste
					User u = new User();
					u.setId(rs.getInt("id"));
					u.setNom(rs.getString("nom"));
					u.setPrenom(rs.getString("prenom"));
					u.setLogin(rs.getString("login"));
					u.setPassword(rs.getString("password"));
					// ajouter l'objet "User" dans la liste
					users.add(u);
				}
			}
			ps.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		//retoturner la liste
		return users;
	}
	@Override
	public User getUserByLoginAndPassword(String l, String p) {

		User u= null;
		try {
			// pr�parer la requ�te SQL
			PreparedStatement ps = dao.connection.prepareStatement(" select * from User where login =? and password = ?");
			ps.setString(1, l);
			ps.setString(2, p);
			ResultSet rs= dao.lire(ps);
			if (rs!=null)
			{
				while(rs.next())
				{
					u = new User();
					u.setId(rs.getInt("id"));
					u.setNom(rs.getString("nom"));
					u.setPrenom(rs.getString("prenom"));
					u.setLogin(rs.getString("login"));
					u.setPassword(rs.getString("password"));
					
				}
			}
			ps.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return u;
	}
	@Override
	public void updateUser(User u) {

		try {
			// pr�parer la requ�te SQL
			PreparedStatement ps = dao.connection.prepareStatement(" update user set nom= ?, prenom=?, login=?, password =? where id=? ");
			ps.setString(1, u.getNom());
			ps.setString(2, u.getPrenom());
			ps.setString(3, u.getLogin());
			ps.setString(4, u.getPassword());
			ps.setInt(5, u.getId());

			dao.ecrire(ps);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	@Override
	public void deleteUser(int id) {

		try {
			// pr�parer la requ�te SQL
			PreparedStatement ps = dao.connection.prepareStatement(" delete from user  where id=? ");
			ps.setInt(1, id);
			dao.ecrire(ps);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	@Override
	public User getUserById(int id) {
		
				User u= null;
				try {
					// pr�parer la requ�te SQL
					PreparedStatement ps = dao.connection.prepareStatement(" select * from User where id = ?");
					ps.setInt(1,id);
					
					ResultSet rs= dao.lire(ps);
					if (rs!=null)
					{
						while(rs.next())
						{
							u = new User();
							u.setId(rs.getInt("id"));
							u.setNom(rs.getString("nom"));
							u.setPrenom(rs.getString("prenom"));
							u.setLogin(rs.getString("login"));
							u.setPassword(rs.getString("password"));
							
						}
					}
					ps.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
				return u;
	}
	@Override
	public List<User> listUsersByNomOrPrenom(String motDeRecherche) {
		//D�finir une liste vide pour stocker les objets "User"
				List<User> users= new ArrayList<User>();

				try {
					// pr�parer la requ�te SQL
					String sql= "select * from User where nom like ? or prenom like ?";
					System.out.println(sql);
					System.out.println("mr:"+motDeRecherche);
					PreparedStatement ps = dao.connection.prepareStatement(sql);
					//Affecter la valeur du param�tre
					ps.setString(1,"%"+motDeRecherche+"%");
					ps.setString(2,"%"+motDeRecherche+"%");

					//ps.setString(2,motDeRecherche);
					// R�cup�rer le r�sultat de la requ�te
					ResultSet rs= dao.lire(ps);
					if (rs!=null)
					{
						//parcourir le r�sultat 
						while(rs.next())
						{
							//Construire un objet "User" puis lui affecter les attributs
							// et enfin l'ajouter dans la liste
							User u = new User();
							u.setId(rs.getInt("id"));
							u.setNom(rs.getString("nom"));
							u.setPrenom(rs.getString("prenom"));
							u.setLogin(rs.getString("login"));
							u.setPassword(rs.getString("password"));
							// ajouter l'objet "User" dans la liste
							users.add(u);
						}
					}
					ps.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
				//retoturner la liste
				return users;
	}
}
