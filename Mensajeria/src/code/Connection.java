package code;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

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
		 int id1=getID(nom),id2=-1;
		try {
		  sentencia = cn.prepareStatement(sentenciaSql);
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
		
		String sentenciaSql = "SELECT * FROM AMISTAD WHERE (idu1 = ? OR idu2 = ?)";
		PreparedStatement sentencia = null;
		ResultSet resultado = null;
		 int id1=getID(nom);
		try {		  
		  sentencia = cn.prepareStatement(sentenciaSql);
		  sentencia.setInt(1, id1);
		  sentencia.setInt(2, id1);
		  resultado = sentencia.executeQuery();
		  
		  while(resultado.next()) {
			  if(resultado.getInt("idu1") == id1) {
				  if(resultado.getBoolean("stat")) {
					  String sent = "SELECT nom_u FROM USUARIO WHERE idu=?";
					  sentencia = cn.prepareStatement(sent);
					  sentencia.setInt(1, resultado.getInt("idu2"));
					  ResultSet res = sentencia.executeQuery();
					  if(res.next())
						  aux.add(res.getString(1));
				  }
			  }
			  else {
				  String sent = "SELECT nom_u FROM USUARIO WHERE idu=?";
				  sentencia = cn.prepareStatement(sent);
				  sentencia.setInt(1, resultado.getInt("idu1"));
				  ResultSet res = sentencia.executeQuery();
				  if(res.next())
					  aux.add(res.getString(1));
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
	public void deleteFriend(String nom_u) {
		String sentenciaSql = "SELECT idu FROM usuario WHERE nom_u = ? ";
		PreparedStatement sentencia = null;
		ResultSet resultado = null;
		 int id1=getID(nom),id2=-1;
		try {
		  sentencia = cn.prepareStatement(sentenciaSql);
		  sentencia.setString(1, nom_u);
		  resultado = sentencia.executeQuery();
		  while (resultado.next())
			  id2 = resultado.getInt(1);
		  
		  System.out.println("id1: " + id1 + "\nid2: " + id2);
		  
		  sentenciaSql = "DELETE FROM AMISTAD WHERE idu1=? AND idu2=? OR idu1=? AND idu2=?";
		  sentencia = cn.prepareStatement(sentenciaSql);
		  sentencia.setInt(1, id1);
		  sentencia.setInt(2, id2);
		  sentencia.setInt(3, id2);
		  sentencia.setInt(4, id1);
		  sentencia.executeUpdate();
		  
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
	}
	
	@SuppressWarnings("resource")
	public void acceptFriend(String nom_u) {
		String sentenciaSql = "SELECT idu FROM usuario WHERE nom_u = ? ";
		PreparedStatement sentencia = null;
		ResultSet resultado = null;
		 int id1=getID(nom),id2=-1;
		try {
		  sentencia = cn.prepareStatement(sentenciaSql);
		  sentencia.setString(1, nom_u);
		  resultado = sentencia.executeQuery();
		  while (resultado.next())
			  id2 = resultado.getInt(1);
		  
		  sentenciaSql = "UPDATE AMISTAD SET stat=? WHERE idu1=? AND idu2=? OR idu1=? AND idu2=?";
		  sentencia = cn.prepareStatement(sentenciaSql);
		  sentencia.setBoolean(1, true);
		  sentencia.setInt(2, id1);
		  sentencia.setInt(3, id2);
		  sentencia.setInt(4, id2);
		  sentencia.setInt(5, id1);
		  sentencia.executeUpdate();
		  
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
	}
	
	@SuppressWarnings("resource")
	public int addFriend(String nom_u) {
		int aux = -1;
		
		String sentenciaSql = "SELECT idu FROM usuario WHERE nom_u = ? ";
		PreparedStatement sentencia = null;
		ResultSet resultado = null;
		 int id1=getID(nom),id2=-1;
		try {
		  sentencia = cn.prepareStatement(sentenciaSql);
		  sentencia.setString(1, nom_u);
		  resultado = sentencia.executeQuery();
		  while (resultado.next()) {
			  id2 = resultado.getInt(1);
			  aux = 1;
		  }
		  if(aux == 1) {

			  System.out.println("id1: " + id1 + "\nid2: " + id2);
			  
			  sentenciaSql = "INSERT INTO amistad (idu1, idu2, stat) VALUES (?, ?, ?)";
			  sentencia = cn.prepareStatement(sentenciaSql);
			  sentencia.setInt(1, id1);
			  sentencia.setInt(2, id2);
			  sentencia.setBoolean(3, false);
			  sentencia.executeUpdate();
			  
			}
		  }catch (SQLException sqle) {
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
	
	public void newConver(String nom_u) {
		try {
			String sentenciaSql = "BEGIN;INSERT INTO chat (idc) VALUES (DEFAULT);INSERT INTO dialogo (idc, idu1, idu2) SELECT currval('idc_seq'),?,?;END;";
			PreparedStatement sentencia = null;			
			sentencia = cn.prepareStatement(sentenciaSql);
			sentencia.setInt(1, getID(nom));
			sentencia.setInt(2, getID(nom_u));
			sentencia.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void newGroup(String nomg) {
		try {
			String sentenciaSql = "BEGIN;INSERT INTO chat (idc) VALUES (DEFAULT);INSERT INTO grupo (idc, nom_g) SELECT currval('idc_seq'),?;INSERT INTO participa (idc, idu, administra) SELECT currval('idc_seq'),?,?;END;";
			PreparedStatement sentencia = null;			
			sentencia = cn.prepareStatement(sentenciaSql);
			sentencia.setString(1, nomg);
			sentencia.setInt(2, getID(nom));
			sentencia.setBoolean(3, false);
			sentencia.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	@SuppressWarnings("resource")
	public ArrayList<Integer> loadChat() {
		ArrayList<Integer> aux = new ArrayList<Integer>();
		String sentenciaSql1 = "SELECT idc FROM dialogo WHERE idu1 = ? OR idu2 = ?";
		String sentenciaSql2 = "SELECT idc FROM participa WHERE idu = ?";
		PreparedStatement sentencia = null;
		ResultSet resultado = null;
		int idu = getID(nom), idc=-1;
		
		try {
		  
		  sentencia = cn.prepareStatement(sentenciaSql1);
		  sentencia.setInt(1, idu);
		  sentencia.setInt(2, idu);
		  resultado = sentencia.executeQuery();
		  while (resultado.next())
			  aux.add(resultado.getInt(1));
		  

		  sentencia = cn.prepareStatement(sentenciaSql2);
		  sentencia.setInt(1, idu);
		  resultado = sentencia.executeQuery();
		  while (resultado.next())
			  aux.add(resultado.getInt(1));
		  
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
	public ArrayList<String> loadMensajes(int idc) {
		ArrayList<String> aux = new ArrayList<String>();
		String sentenciaSql = "SELECT idu, datos FROM mensaje WHERE idc=?";
		PreparedStatement sentencia = null;
		ResultSet resultado = null;
		
		try {
		  sentencia = cn.prepareStatement(sentenciaSql);
		  sentencia.setInt(1, idc);
		  resultado = sentencia.executeQuery();
		  while (resultado.next()) {
			  aux.add(getName(resultado.getInt("idu")));
			  aux.add(resultado.getString("datos"));
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
	public void sendMensaje(int idc, String data) {
		String sentenciaSql = "INSERT INTO mensaje(idm,idu,idc,datos,fecha) VALUES (default,?,?,?, now())";
		PreparedStatement sentencia = null;
		
		try {
		  sentencia = cn.prepareStatement(sentenciaSql);
		  sentencia.setInt(1, getID(nom));
		  sentencia.setInt(2, idc);
		  sentencia.setString(3, data);
		  sentencia.executeUpdate();
		  
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
	}
	
	public String getNameChat(int idc) {
		String aux = "";
		String sentenciaSql1 = "SELECT COUNT(*) FROM dialogo WHERE idc = ?";
		String sentenciaSql2 = "SELECT * FROM dialogo WHERE idc = ?";
		String sentenciaSql3 = "SELECT nom_u FROM usuario WHERE idu = ? ";
		String sentenciaSql4 = "SELECT nom_g FROM grupo WHERE idc = ?";
		PreparedStatement sentencia = null;
		ResultSet resultado = null;
		
		try {
		  
		  sentencia = cn.prepareStatement(sentenciaSql1);
		  sentencia.setInt(1, idc);
		  resultado = sentencia.executeQuery();
		  if (resultado.next()) {
			  if(resultado.getInt(1) > 0) {
				  sentencia = cn.prepareStatement(sentenciaSql2);
				  sentencia.setInt(1, idc);
				  resultado = sentencia.executeQuery();
				  if(resultado.next()) {
					  if(resultado.getInt("idu1") == getID(nom)) {
						  sentencia = cn.prepareStatement(sentenciaSql3);
						  sentencia.setInt(1, resultado.getInt("idu2"));
						  resultado = sentencia.executeQuery();
						  if(resultado.next())
							  aux = resultado.getString("nom_u");
					  }else {
						  sentencia = cn.prepareStatement(sentenciaSql3);
						  sentencia.setInt(1, resultado.getInt("idu1"));
						  resultado = sentencia.executeQuery();
						  if(resultado.next())
							  aux = resultado.getString("nom_u");					  
					  }
				  }
			  }else {
				  System.out.println("entro");
				  sentencia = cn.prepareStatement(sentenciaSql4);
				  sentencia.setInt(1, idc);
				  resultado = sentencia.executeQuery();
				  if(resultado.next())
					  aux = resultado.getString("nom_g");				  
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
	
	private int getID(String nom) {
		String sentenciaSql = "SELECT idu FROM usuario WHERE nom_u = ? ";
		PreparedStatement sentencia = null;
		ResultSet resultado = null;
		
		int aux = 0;
		try {
			sentencia = cn.prepareStatement(sentenciaSql);
			sentencia.setString(1, nom);
			resultado = sentencia.executeQuery();
			  while (resultado.next())
				  aux = resultado.getInt(1);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return aux;
	}
	
	private String getName(int idu) {
		String sentenciaSql = "SELECT nom_u FROM usuario WHERE idu = ? ";
		PreparedStatement sentencia = null;
		ResultSet resultado = null;
		
		String aux = "";
		try {
			sentencia = cn.prepareStatement(sentenciaSql);
			sentencia.setInt(1, idu);
			resultado = sentencia.executeQuery();
			  while (resultado.next())
				  aux = resultado.getString(1);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return aux;
	}
}
