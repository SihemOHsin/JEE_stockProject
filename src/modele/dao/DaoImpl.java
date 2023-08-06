package modele.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DaoImpl implements DaoInterface {	
	@Override
	public void ecrire(PreparedStatement ps) throws SQLException {
		ps.executeUpdate();		
	}
	@Override
	public ResultSet lire(PreparedStatement ps) throws SQLException {
		// TODO Auto-generated method stub
		return ps.executeQuery();
	}
	
}
