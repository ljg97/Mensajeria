package code;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Connection {

	java.sql.Connection cn = null;
	private String nom = "";
	
	public java.sql.Connection Conectar() {

		try {
			  Class.forName("org.postgresql.Driver");
			  cn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/Mensajeria","postgres", "postgres");
			} catch (ClassNotFoundException cnfe) {
			  cnfe.printStackTrace();
			} catch (SQLException sqle) {
			  sqle.printStackTrace();
			}
			
        return cn;

    }
	
	public void disconnect() {
		try {
			  cn.close();
			  cn = null;
		} catch (SQLException sqle) {
			  sqle.printStackTrace();
		}
	}
	
	public int insertUser(String nom_u, String pass) {
		int aux = 0;

		String sentenciaSql = "INSERT INTO usuario (nom_u, pass) VALUES (?, ?)";
		PreparedStatement sentencia = null;
		 
		try {
		  sentencia = cn.prepareStatement(sentenciaSql);
		  sentencia.setString(1, nom_u);
		  sentencia.setString(2, pass);
		  sentencia.executeUpdate();
		  aux = 1;
		}  catch(org.postgresql.util.PSQLException psql) {
			aux = -1;
		} catch (SQLException sqle) {
		  sqle.printStackTrace();
		}finally {
		  if (sentencia != null)
		    try {
		    	sentencia.close();
		    } catch (SQLException sqle) {
		    	sqle.printStackTrace();
		    }
		}
		
		return aux;
	}
	
	public int loginUser(String nom_u, String pass) {
		int aux = 0;

		String sentenciaSql = "SELECT pass FROM usuario WHERE nom_u = ? ";
		PreparedStatement sentencia = null;
		ResultSet resultado = null;
		 
		try {
		  sentencia = cn.prepareStatement(sentenciaSql);
		  sentencia.setString(1, nom_u);
		  resultado = sentencia.executeQuery();
		  while (resultado.next()) {
			  if(pass.equals(resultado.getString(1))) {
				  nom = nom_u;
				  aux = 1;
			  } else {
				  aux = -1;
			  }
		  }
		} catch (SQLException sqle) {
		  sqle.printStackTrace();
		} finally {
		  if (sentencia != null)
		    try {
		      sentencia.close();
		    } catch (SQLException sqle) {
		      sqle.printStackTrace();
		    }
		}
		
		return aux;
	}
	
}
