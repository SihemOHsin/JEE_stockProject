package modele.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public interface DaoInterface {
	static Connection connection = DBConnexion.getConnection();
	void ecrire(PreparedStatement ps) throws SQLException;
	ResultSet lire(PreparedStatement ps) throws SQLException;
}
