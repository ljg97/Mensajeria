package code;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Connection {

	java.sql.Connection cn = null;
	private String nom = "";

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}
	
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
		int aux = -1;

		if(!nom_u.equals("")) {
			
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
		}
		
		return aux;
	}
	
	public int loginUser(String nom_u, String pass) {
		int aux = -1;

		String sentenciaSql = "SELECT pass FROM usuario WHERE nom_u = ? ";
		PreparedStatement sentencia = null;
		ResultSet resultado = null;
		 
		try {
		  sentencia = cn.prepareStatement(sentenciaSql);
		  sentencia.setString(1, nom_u);
		  resultado = sentencia.executeQuery();
		  while (resultado.next()) {
			  if(pass.equals(resultado.getString(1))) {
				  setNom(nom_u);
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
	
	@SuppressWarnings("resource")
	public int itsFriend(String nom_u) {
		int aux = -1;

		String sentenciaSql = "SELECT idu FROM usuario WHERE nom_u = ? ";
		PreparedStatement sentencia = null;
		ResultSet resultado = null;
		 int id1=-1,id2=-1;
		try {
		  sentencia = cn.prepareStatement(sentenciaSql);
		  sentencia.setString(1, nom);
		  resultado = sentencia.executeQuery();
		  while (resultado.next())
			  id1 = resultado.getInt(1);
		  sentencia.setString(1, nom_u);
		  resultado = sentencia.executeQuery();
		  while (resultado.next())
			  id2 = resultado.getInt(1);
		  
		  sentenciaSql = "SELECT COUNT(*) FROM AMISTAD WHERE idu1=? AND idu2=? OR idu1=? AND idu2=?";
		  sentencia = cn.prepareStatement(sentenciaSql);
		  sentencia.setInt(1, id1);
		  sentencia.setInt(2, id2);
		  sentencia.setInt(3, id2);
		  sentencia.setInt(4, id1);
		  resultado = sentencia.executeQuery();
		  while(resultado.next()) {
			  if(resultado.getInt(1) > 0) {
				  sentenciaSql = "SELECT stat FROM AMISTAD WHERE idu1=? AND idu2=? OR idu1=? AND idu2=?";
				  sentencia = cn.prepareStatement(sentenciaSql);
				  sentencia.setInt(1, id1);
				  sentencia.setInt(2, id2);
				  sentencia.setInt(3, id2);
				  sentencia.setInt(4, id1);
				  resultado = sentencia.executeQuery();
				  if(resultado.next()) {
					  if(resultado.getBoolean(1))
						  aux=1;
					  else
						  aux=-1;
				  }
			  }
			  else
				  aux=-1;
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
	
	@SuppressWarnings("resource")
	public ArrayList<String> loadFriend() {
		ArrayList<String> aux = new ArrayList<String>();
		
		String sentenciaSql = "SELECT idu FROM usuario WHERE nom_u = ? ";
		PreparedStatement sentencia = null;
		ResultSet resultado = null;
		 int id1=-1;
		try {
		  sentencia = cn.prepareStatement(sentenciaSql);
		  sentencia.setString(1, nom);
		  resultado = sentencia.executeQuery();
		  while (resultado.next())
			  id1 = resultado.getInt(1);
		  
		  sentenciaSql = "SELECT idu2 FROM AMISTAD WHERE idu1=?";
		  sentencia = cn.prepareStatement(sentenciaSql);
		  sentencia.setInt(1, id1);
		  resultado = sentencia.executeQuery();
		  
		  while(resultado.next()) {
			  sentenciaSql = "SELECT nom_u FROM USUARIO WHERE idu=?";
			  sentencia = cn.prepareStatement(sentenciaSql);
			  sentencia.setInt(1, resultado.getInt(1));
			  resultado = sentencia.executeQuery();
			  if(resultado.next())
				  aux.add(resultado.getString(1));
			  }
		  
		  sentenciaSql = "SELECT idu1 FROM AMISTAD WHERE idu2=?";
		  sentencia = cn.prepareStatement(sentenciaSql);
		  sentencia.setInt(1, id1);
		  resultado = sentencia.executeQuery();
		  
		  while(resultado.next()) {
			  sentenciaSql = "SELECT nom_u FROM USUARIO WHERE idu=?";
			  sentencia = cn.prepareStatement(sentenciaSql);
			  sentencia.setInt(1, resultado.getInt(1));
			  resultado = sentencia.executeQuery();
			  if(resultado.next())
				  aux.add(resultado.getString(1));
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
