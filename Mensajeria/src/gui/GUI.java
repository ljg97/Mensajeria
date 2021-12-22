package gui;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import code.Connection;

import java.awt.Dimension;
import java.awt.Point;
import java.awt.Color;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Objects;
import java.awt.event.ActionEvent;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.border.TitledBorder;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JPasswordField;
import javax.swing.border.BevelBorder;
import javax.swing.border.EtchedBorder;

public class GUI extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final JPanel panelChats = new JPanel();
	private final JPanel panelMensajes = new JPanel();
	private final JPanel panelAmigos = new JPanel();
	private final JPanel panelPeticiones = new JPanel();
	private JScrollPane scrollerMensajes;
	private JTextField tfNombre;
	private JPasswordField pfNew;
	private JPasswordField pfNew2;
	private JPasswordField pfOld;
	private String nom;
	private JButton buttonSettingsGroup;
	private JTextField tfMensaje;
	private JPanel panelSettingsGroup;
	/**
	 * Launch the application.
	 */

	/**
	 * Create the frame.
	 */
	public GUI(Login log, Connection cn, String n) {
		nom = n;
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 847, 589);
		getContentPane().setBackground(Color.DARK_GRAY);
		getContentPane().setLayout(null);
		
		panelChats.setBackground(Color.GRAY);
		panelChats.setBounds(0, 48, 200, 502);
		panelChats.setBorder(new EmptyBorder(5, 5, 5, 5));
		panelChats.setLayout(null);
		
		panelMensajes.setBackground(Color.DARK_GRAY);
		panelMensajes.setBounds(200, 48, 630, 469);
		panelMensajes.setBorder(new EmptyBorder(5, 0, 5, 5));
		panelMensajes.setLayout(null);
		
		panelAmigos.setBackground(Color.DARK_GRAY);
		panelAmigos.setBounds(0, 48, 415, 502);
		panelAmigos.setBorder(new EmptyBorder(5, 5, 5, 5));
		panelAmigos.setLayout(null);
		

		panelPeticiones.setBackground(Color.DARK_GRAY);
		panelPeticiones.setBounds(415, 48, 415, 502);
		panelPeticiones.setBorder(new EmptyBorder(5, 0, 5, 5));
		panelPeticiones.setLayout(null);	
		
		panelSettingsGroup = new JPanel();
		panelSettingsGroup.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		panelSettingsGroup.setBackground(Color.GRAY);
		panelSettingsGroup.setLayout(null);
		panelSettingsGroup.setBounds(472, 0, 359, 517);
		panelSettingsGroup.setVisible(false);
		getContentPane().add(panelSettingsGroup);
		
		JPanel panelSettings = new JPanel();
		panelSettings.setBackground(Color.GRAY);
		panelSettings.setBounds(0, 48, 831, 502);
		getContentPane().add(panelSettings);
		panelSettings.setLayout(null);
		panelSettings.setVisible(false);
		
		JLabel Icon = new JLabel("");
		Icon.setIcon(new ImageIcon(GUI.class.getResource("/img/user.png")));
		Icon.setBounds(180, 130, 128, 128);
		panelSettings.add(Icon);
		
		JLabel lblNom = new JLabel(nom);
		lblNom.setHorizontalAlignment(SwingConstants.CENTER);
		lblNom.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblNom.setBounds(318, 180, 300, 36);
		lblNom.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
		panelSettings.add(lblNom);
		
		JButton btnNom = new JButton("Cambiar nombre");
		btnNom.setBounds(318, 227, 140, 23);
		panelSettings.add(btnNom);
		
		JButton btnPas = new JButton("Cambiar contrase\u00F1a");
		btnPas.setBounds(478, 227, 140, 23);
		panelSettings.add(btnPas);
		
		tfNombre = new JTextField();
		tfNombre.setBounds(318, 298, 300, 28);
		panelSettings.add(tfNombre);
		tfNombre.setVisible(false);
		tfNombre.setColumns(10);
		
		JLabel lblCambio = new JLabel("");
		lblCambio.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblCambio.setHorizontalAlignment(SwingConstants.RIGHT);
		lblCambio.setBounds(10, 300, 298, 21);
		lblCambio.setVisible(false);
		panelSettings.add(lblCambio);
		
		pfNew = new JPasswordField();
		pfNew.setBounds(318, 337, 300, 28);
		pfNew.setVisible(false);
		panelSettings.add(pfNew);
		
		pfNew2 = new JPasswordField();
		pfNew2.setBounds(318, 376, 300, 28);
		pfNew2.setVisible(false);
		panelSettings.add(pfNew2);
		
		JLabel lblCambioCon = new JLabel("Introduce la nueva contrase\u00F1a");
		lblCambioCon.setHorizontalAlignment(SwingConstants.RIGHT);
		lblCambioCon.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblCambioCon.setBounds(10, 339, 298, 21);
		lblCambioCon.setVisible(false);
		panelSettings.add(lblCambioCon);
		
		JLabel lblCambioCon2 = new JLabel("Repite la nueva contrase\u00F1a");
		lblCambioCon2.setHorizontalAlignment(SwingConstants.RIGHT);
		lblCambioCon2.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblCambioCon2.setBounds(10, 378, 298, 21);
		lblCambioCon2.setVisible(false);
		panelSettings.add(lblCambioCon2);
		
		pfOld = new JPasswordField();
		pfOld.setBounds(318, 298, 300, 28);
		pfOld.setVisible(false);
		panelSettings.add(pfOld);
		
		JButton btnOkNom = new JButton("Aceptar");
		btnOkNom.setBounds(628, 301, 140, 23);
		btnOkNom.setVisible(false);
		panelSettings.add(btnOkNom);
		
		JButton btnCancel = new JButton("Cancelar");
		btnCancel.setBounds(628, 340, 140, 23);
		btnCancel.setVisible(false);
		panelSettings.add(btnCancel);
		
		JLabel lblError = new JLabel("");
		lblError.setHorizontalAlignment(SwingConstants.CENTER);
		lblError.setForeground(Color.RED);
		lblError.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblError.setBounds(180, 261, 588, 26);
		panelSettings.add(lblError);
		
		JButton btnOkPas = new JButton("Aceptar");
		btnOkPas.setBounds(628, 301, 140, 23);
		btnOkPas.setVisible(false);
		panelSettings.add(btnOkPas);
		
		JButton btnDelete = new JButton("BORRAR USUARIO");
		btnDelete.setBounds(628, 227, 140, 23);
		panelSettings.add(btnDelete);
		
		JButton btnOkDelete = new JButton("Aceptar");
		btnOkDelete.setBounds(628, 301, 140, 23);
		btnOkDelete.setVisible(false);
		panelSettings.add(btnOkDelete);
		
		JPanel panelBotones = new JPanel();
		panelBotones.setBackground(Color.LIGHT_GRAY);
		panelBotones.setBounds(0, 0, 831, 49);
		getContentPane().add(panelBotones);
		panelBotones.setLayout(null);
		
		JButton buttonAmigos = new JButton("");
		buttonAmigos.setBounds(0, 4, 65, 41);
		panelBotones.add(buttonAmigos);
		buttonAmigos.setContentAreaFilled(false);
		buttonAmigos.setBorderPainted(false);
		buttonAmigos.setIcon(new ImageIcon(GUI.class.getResource("/img/friends.png")));
		
		JButton buttonLogOut = new JButton("");
		buttonLogOut.setIcon(new ImageIcon(GUI.class.getResource("/img/log-out.png")));
		buttonLogOut.setContentAreaFilled(false);
		buttonLogOut.setBorderPainted(false);
		buttonLogOut.setBounds(766, 4, 65, 41);
		panelBotones.add(buttonLogOut);
		
		JButton buttonVolver = new JButton("");
		buttonVolver.setIcon(new ImageIcon(GUI.class.getResource("/img/return.png")));
		buttonVolver.setContentAreaFilled(false);
		buttonVolver.setBorderPainted(false);
		buttonVolver.setBounds(0, 4, 65, 41);
		panelBotones.add(buttonVolver);
		buttonVolver.setVisible(false);
		
		JButton buttonAnyadir = new JButton("");
		buttonAnyadir.setIcon(new ImageIcon(GUI.class.getResource("/img/add-user.png")));
		buttonAnyadir.setContentAreaFilled(false);
		buttonAnyadir.setBorderPainted(false);
		buttonAnyadir.setBounds(766, 4, 65, 41);
		panelBotones.add(buttonAnyadir);
		buttonAnyadir.setVisible(false);
		
		JButton buttonSettings = new JButton("");
		buttonSettings.setIcon(new ImageIcon(GUI.class.getResource("/img/settings.png")));
		buttonSettings.setContentAreaFilled(false);
		buttonSettings.setBorderPainted(false);
		buttonSettings.setBounds(75, 4, 65, 41);
		panelBotones.add(buttonSettings);
		
		buttonSettingsGroup = new JButton("");
		buttonSettingsGroup.setIcon(new ImageIcon(GUI.class.getResource("/img/settingsGroup.png")));
		buttonSettingsGroup.setContentAreaFilled(false);
		buttonSettingsGroup.setBorderPainted(false);
		buttonSettingsGroup.setBounds(691, 4, 65, 41);
		buttonSettingsGroup.setVisible(false);
		panelBotones.add(buttonSettingsGroup);

		JScrollPane scrollerChats = new JScrollPane();
		scrollerChats.setBounds(panelChats.getBounds());
		scrollerChats.getVerticalScrollBar().setUnitIncrement(7);
		scrollerChats.setViewportView(panelChats);
		getContentPane().add(scrollerChats);
		
		scrollerMensajes = new JScrollPane();
		scrollerMensajes.setBounds(panelMensajes.getBounds());
		scrollerMensajes.getVerticalScrollBar().setUnitIncrement(7);
		scrollerMensajes.setViewportView(panelMensajes);
		getContentPane().add(scrollerMensajes);
				
		tfMensaje = new JTextField();
		tfMensaje.setColumns(10);
		tfMensaje.setBounds(200,518,630,32);
		getContentPane().add(tfMensaje);
		
		JScrollPane scrollerAmigos = new JScrollPane();
		scrollerAmigos.setBounds(panelAmigos.getBounds());
		scrollerAmigos.getVerticalScrollBar().setUnitIncrement(7);
		scrollerAmigos.setViewportView(panelAmigos);
		getContentPane().add(scrollerAmigos);
		scrollerAmigos.setVisible(false);
		

		JScrollPane scrollerPeticiones = new JScrollPane();
		scrollerPeticiones.setBounds(panelPeticiones.getBounds());
		scrollerPeticiones.getVerticalScrollBar().setUnitIncrement(7);
		scrollerPeticiones.setViewportView(panelPeticiones);
		getContentPane().add(scrollerPeticiones);
		scrollerPeticiones.setVisible(false);
		
		buttonLogOut.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				logOut(log);
			}
		});

		buttonVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				buttonAmigos.setVisible(true);
				buttonLogOut.setVisible(true);
				buttonSettings.setVisible(true);
				buttonVolver.setVisible(false);
				buttonAnyadir.setVisible(false);
				scrollerChats.setVisible(true);
				scrollerMensajes.setVisible(true);
				if(Objects.isNull(tfMensaje)==false)
					tfMensaje.setVisible(false);
				scrollerAmigos.setVisible(false);
				scrollerPeticiones.setVisible(false);
				panelSettings.setVisible(false);
				cargarChats(panelChats, cn);
				vaciarPanel(panelMensajes);
			}
		});
		
		buttonAmigos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				buttonAmigos.setVisible(false);
				buttonLogOut.setVisible(false);
				buttonSettings.setVisible(false);
				buttonSettingsGroup.setVisible(false);
				buttonVolver.setVisible(true);
				buttonAnyadir.setVisible(true);
				scrollerChats.setVisible(false);
				scrollerMensajes.setVisible(false);
				if(Objects.isNull(tfMensaje)==false)
					tfMensaje.setVisible(false);
				scrollerAmigos.setVisible(true);
				scrollerPeticiones.setVisible(true);
				cargarAmigos(panelAmigos, cn, buttonVolver.getActionListeners()[0]);
				cargarPeticiones(panelPeticiones, cn, buttonVolver.getActionListeners()[0]);
			}
		});
		
		buttonSettings.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				buttonAmigos.setVisible(false);
				buttonLogOut.setVisible(false);
				buttonSettings.setVisible(false);
				buttonSettingsGroup.setVisible(false);
				buttonAnyadir.setVisible(false);
				buttonVolver.setVisible(true);
				scrollerChats.setVisible(false);
				scrollerMensajes.setVisible(false);
				if(Objects.isNull(tfMensaje)==false)
					tfMensaje.setVisible(false);
				scrollerAmigos.setVisible(false);
				scrollerPeticiones.setVisible(false);
				panelSettings.setVisible(true);
			}
		});
		

		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnNom.setEnabled(true);
				btnPas.setEnabled(true);
				btnDelete.setEnabled(true);
				lblCambio.setVisible(false);
				btnOkNom.setVisible(false);
				lblCambio.setVisible(false);
				lblCambioCon.setVisible(false);
				lblCambioCon2.setVisible(false);
				btnOkPas.setVisible(false);
				btnCancel.setVisible(false);
				btnOkDelete.setVisible(false);
				tfNombre.setVisible(false);
				pfOld.setVisible(false);
				pfNew.setVisible(false);
				pfNew2.setVisible(false);
				tfNombre.setText("");
				pfOld.setText("");
				pfNew.setText("");
				pfNew2.setText("");
				lblError.setText("");
			}
		});
		
		btnNom.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnNom.setEnabled(false);
				btnPas.setEnabled(false);
				btnDelete.setEnabled(false);
				lblCambio.setVisible(true);
				lblCambio.setText("Introduce el nuevo nombre de usuario");
				btnOkNom.setVisible(true);
				btnCancel.setVisible(true);
				tfNombre.setVisible(true);
			}
		});
		
		btnOkNom.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String nom_u = tfNombre.getText();
				boolean vacio = true;
				if(nom_u.length()>0) {
					for (int i=0;i<nom_u.length();i++){
						if(nom_u.charAt(i)!= ' ')
							vacio = false;
					}
				}
				if (!vacio) {
					if(nom_u.equals(nom)) {
						lblError.setText("El nombre actual y el nuevo no pueden ser iguales");
						tfNombre.setText("");
					}else {
						if(cn.changeNom(nom_u)!=-1) {
							nom = nom_u;
							lblNom.setText(nom_u);
							lblError.setText("");		
							tfNombre.setText("");
							btnCancel.getActionListeners()[0].actionPerformed(e);
							JOptionPane.showMessageDialog(null, "Se ha cambiado el nombre de usuario");							
						}else {
							lblError.setText("Nombre de usuario en uso");	
							tfNombre.setText("");						
						}				
					}
				}else {
					lblError.setText("Nuevo nombre no puede estar vacio");
				}
			}
		});
		
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int op = JOptionPane.showConfirmDialog(null, "¿Realmente quieres eliminar tu cuenta?", "Eliminar cuenta", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
				if(op == 0) {
					btnNom.setEnabled(false);
					btnPas.setEnabled(false);
					btnDelete.setEnabled(false);
					lblCambio.setVisible(true);
					lblCambio.setText("Introduce tu contraseña");
					btnOkDelete.setVisible(true);					
					btnCancel.setVisible(true);
					pfOld.setVisible(true);				
				}
			}
		});
		
		btnOkDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				boolean vacio = true;
				if(pfOld.getPassword().length>0) {
					for (int i=0;i<pfOld.getPassword().length;i++){
						if(String.valueOf(pfOld.getPassword()).charAt(i)!= ' ')
							vacio = false;
					}
				}
				
				if (!vacio) {
					int op = cn.deleteUser(String.valueOf(pfOld.getPassword()));
					if(op == -1) {
						pfOld.setText("");
						lblError.setText("La contraseña es incorrecta");
					}else if (op == 1) {
						logOut(log);					
					}else
						lblError.setText("No puedes dejar grupos sin administrador");	
				}else {
					lblError.setText("El campo constraseña no puede estar vacio");
				}
			}
		});
		
		btnPas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnNom.setEnabled(false);
				btnPas.setEnabled(false);
				btnDelete.setEnabled(false);
				lblCambio.setVisible(true);
				lblCambio.setText("Introduce tu contraseña actual");
				lblCambioCon.setVisible(true);
				lblCambioCon2.setVisible(true);
				btnOkPas.setVisible(true);
				btnCancel.setVisible(true);
				pfOld.setVisible(true);
				pfNew.setVisible(true);
				pfNew2.setVisible(true);
			}
		});
		
		btnOkPas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String newPas = String.valueOf(pfNew.getPassword());
				String newPas2 = String.valueOf(pfNew2.getPassword());
				boolean vacio = true;
				if(newPas.length()>0) {
					for (int i=0;i<newPas.length();i++){
						if(newPas.charAt(i)!= ' ')
							vacio = false;
					}
				}
				if(newPas2.length()>0) {
					for (int i=0;i<newPas2.length();i++){
						if(newPas2.charAt(i)!= ' ')
							vacio = false;
					}
				}
				if (!vacio) {
					if(newPas.equals(newPas2)) {
						int op = cn.changePas(String.valueOf(pfOld.getPassword()),newPas);
						if(op == 1) {
							lblError.setText("");
							pfOld.setText("");
							pfNew.setText("");
							pfNew2.setText("");
							btnCancel.getActionListeners()[0].actionPerformed(e);
							JOptionPane.showMessageDialog(null, "Se ha cambiado la contraseña");
						}else if (op == -1){
							pfOld.setText("");
							pfNew.setText("");
							pfNew2.setText("");
							lblError.setText("La contraseña introducida es incorrecta");
						}else {
							pfOld.setText("");
							pfNew.setText("");
							pfNew2.setText("");
							lblError.setText("La contraseña nueva es igual a la antigua");							
						}
					}else {
						lblError.setText("Las contraseñas introducidas no son iguales");
					}
				}else {
					lblError.setText("Nueva constraseña no puede estar vacia");
				}
			}
		});

		buttonSettingsGroup.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panelSettingsGroup.setVisible(true);
				buttonSettingsGroup.setVisible(false);
			}
		});
		
		panelSettingsGroup.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseExited(MouseEvent e) {
				panelSettingsGroup.setVisible(false);
				buttonSettingsGroup.setVisible(true);
			}
		});
		
		buttonAnyadir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String nom_u = JOptionPane.showInputDialog(null, "Introduce el nombre del usuario a añadir.", "Agregar usuario", JOptionPane.DEFAULT_OPTION);
				cn.addFriend(nom_u);
			}
		});
		
		cargarChats(panelChats, cn);
	}
	
	private void cargarAmigos (JPanel parent, Connection cn, ActionListener ac) {
		ArrayList<String> amigos = cn.loadFriend();
		int pos = 10;
		int inc = 61;
		
		for (int i = 0; i < amigos.size(); i++) {			
			if(sonAmigos(amigos.get(i), cn) > 0) {
				JPanel amigo = new JPanel();
				
				amigo.setBounds(10, pos, 395, 50);
				amigo.setBackground(Color.LIGHT_GRAY);
				amigo.setLayout(null);
				
				JLabel nom = new JLabel();
				nom.setBounds(10, 18, 160, 15);
				nom.setForeground(Color.white);
				nom.setText(amigos.get(i));
				amigo.add(nom);
				

				JButton but1 = new JButton();
				but1.setBounds(195, 13, 89, 23);

				JButton but2 = new JButton();
				but2.setBounds(295, 13, 89, 23);
				but1.setText("Hablar");
				but1.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						cn.newConver(nom.getText());
						ac.actionPerformed(e);
					}
				});
				amigo.add(but1);
				
				but2.setText("Borrar");
				but2.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						cn.deleteFriend(nom.getText());
						vaciarPanel(parent);
						cargarAmigos(parent, cn, ac);
						ac.actionPerformed(e);
					}
				});
				amigo.add(but2);
				
				pos += inc;
				parent.add(amigo);
			}
		}

		parent.setPreferredSize(new Dimension(150,pos));
		parent.revalidate();
		parent.repaint();
	}
	
	private void cargarPeticiones (JPanel parent, Connection cn, ActionListener ac) {
		ArrayList<String> amigos = cn.loadFriend();
		int pos = 10;
		int inc = 61;
		
		for (int i = 0; i < amigos.size(); i++) {			
			if(sonAmigos(amigos.get(i), cn) < 0) {
				JPanel amigo = new JPanel();
				
				amigo.setBounds(10, pos, 395, 50);
				amigo.setBackground(Color.LIGHT_GRAY);
				amigo.setLayout(null);
				
				JLabel nom = new JLabel();
				nom.setBounds(10, 18, 160, 15);
				nom.setForeground(Color.white);
				nom.setText(amigos.get(i));
				amigo.add(nom);
				

				JButton but1 = new JButton();
				but1.setBounds(195, 13, 89, 23);

				JButton but2 = new JButton();
				but2.setBounds(295, 13, 89, 23);
				
				but1.setText("Aceptar");
				but1.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						cn.acceptFriend(nom.getText());
						vaciarPanel(parent);
						cargarAmigos(panelAmigos, cn, ac);
						cargarPeticiones(panelPeticiones, cn, ac);			
					}
				});
				amigo.add(but1);
				
				but2.setText("Denegar");
				but2.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						cn.deleteFriend(nom.getText());
						vaciarPanel(parent);
						cargarAmigos(panelAmigos, cn, ac);
						cargarPeticiones(panelPeticiones, cn, ac);
					}
				});
				amigo.add(but2);
				
				pos += inc;
				parent.add(amigo);
			}			
		}

		parent.setPreferredSize(new Dimension(150,pos));
		parent.revalidate();
		parent.repaint();
	}
	
	private int sonAmigos(String nom_u, Connection cn) {
		return cn.itsFriend(nom_u);
	}
	
	private void vaciarPanel(JPanel parent) {
		parent.removeAll();
	}
	
	private void cargarChats (JPanel parent, Connection cn) {
		vaciarPanel(parent);
		ArrayList<Integer> chats = cn.loadChat();
		int pos = 10;
		int inc = 61;
		
		for (int i = 0; i < chats.size(); i++) {
			int id = chats.get(i);
			JPanel chat = new JPanel();			
			chat.setBounds(10, pos, 165, 50);
			chat.setBackground(Color.DARK_GRAY);
			chat.setLayout(null);
			
			JLabel nom_c = new JLabel();
			nom_c.setBounds(10, 10, 135, 15);
			nom_c.setForeground(Color.white);
			nom_c.setText(cn.getNameChat(id));
			chat.add(nom_c);
			
			chat.addMouseListener(new MouseAdapter() {

				@Override
				public void mouseClicked(MouseEvent e) {
					vaciarPanel(panelMensajes);
					cargarMensajes(panelMensajes, cn, id);
					tfMensaje.setVisible(true);

					if(tfMensaje.getActionListeners().length > 0)
						tfMensaje.removeActionListener(tfMensaje.getActionListeners()[0]);
					
					tfMensaje.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							char[] msj = tfMensaje.getText().toCharArray();
							boolean ok = false;
							int i = 0;
							while(i < msj.length) {
								if(msj[i] != ' ') 
									ok = true;
								i++;
							}
							if (ok == true) {
								cn.sendMensaje(id, tfMensaje.getText());
								cargarMensajes(panelMensajes, cn, id);
								tfMensaje.setText("");									
							}
						}
					});
					tfMensaje.grabFocus();

					if(cn.isGroup(id)) {
						panelSettingsGroup.removeAll();
						buttonSettingsGroup.setVisible(true);
						
						boolean admin = cn.isAdmin(id, nom);
						
						MouseAdapter dntClose = new MouseAdapter() {public void mouseEntered(MouseEvent e) {
							panelSettingsGroup.setVisible(true);
						}};
						
						JLabel lblGroupName = new JLabel("Nombre Grupo");
						lblGroupName.setFont(new Font("Tahoma", Font.BOLD, 14));
						lblGroupName.setBounds(10, 11, 339, 34);
						lblGroupName.setBorder(BorderFactory.createLineBorder(Color.black, 1));
						panelSettingsGroup.add(lblGroupName);
						
						JButton btnGroupExit = new JButton("Salir");
						btnGroupExit.setBounds(260, 56, 89, 23);
						panelSettingsGroup.add(btnGroupExit);
						
						JButton btnGroupDelete = new JButton("Borrar");
						btnGroupDelete.setBounds(260, 483, 89, 23);
						panelSettingsGroup.add(btnGroupDelete);
						
						JButton btnGroupAdd = new JButton("A\u00F1adir");
						btnGroupAdd.setBounds(10, 56, 89, 23);
						panelSettingsGroup.add(btnGroupAdd);
						
						JPanel panelGroupUsers = new JPanel();
						panelGroupUsers.setBackground(Color.LIGHT_GRAY);
						panelGroupUsers.setBounds(10, 90, 339, 382);
						
						JScrollPane scrollerGroupUsers = new JScrollPane();
						scrollerGroupUsers.setBounds(panelGroupUsers.getBounds());
						scrollerGroupUsers.getVerticalScrollBar().setUnitIncrement(7);
						scrollerGroupUsers.setViewportView(panelGroupUsers);
						panelGroupUsers.setLayout(null);
						panelSettingsGroup.add(scrollerGroupUsers);
						scrollerGroupUsers.setVisible(true);
						
						JPanel panelGroupUser = new JPanel();
						panelGroupUser.setBounds(10, 10, 295, 30);
						panelGroupUsers.add(panelGroupUser);
						panelGroupUser.setLayout(null);
						
						JLabel lblGroupUserName = new JLabel("Nombre Usuario");
						lblGroupUserName.setBounds(10, 8, 138, 14);
						panelGroupUser.add(lblGroupUserName);
						
						JButton btnKick = new JButton("");
						btnKick.setIcon(new ImageIcon(GUI.class.getResource("/img/kick-off.png")));
						btnKick.setBounds(245, 2, 40, 25);
						panelGroupUser.add(btnKick);
						
						JButton btnAdmin = new JButton("");
						btnAdmin.setIcon(new ImageIcon(GUI.class.getResource("/img/crown.png")));
						btnAdmin.setBounds(195, 2, 40, 25);
						panelGroupUser.add(btnAdmin);
						btnAdmin.setBackground(Color.red);	
						

						btnGroupExit.addMouseListener(dntClose);						
						btnGroupAdd.addMouseListener(dntClose);						
						btnGroupDelete.addMouseListener(dntClose);
						scrollerGroupUsers.addMouseListener(dntClose);
						btnKick.addMouseListener(dntClose);
						btnAdmin.addMouseListener(dntClose);
						
					}else {
						buttonSettingsGroup.setVisible(false);						
					}
				}
			});

			pos += inc;
			parent.add(chat);
		}
		parent.setPreferredSize(new Dimension(parent.getWidth()-20,pos));
	}
	
	private void cargarMensajes (JPanel parent, Connection cn, int idc) {
		ArrayList<String> mensajes = cn.loadMensajes(idc);
		int pos = 10;
		int inc = 47;
		
		for (int i = 0; i < mensajes.size(); i+=2) {
			if(mensajes.get(i) == "") {
				JPanel mensaje = new JPanel();
				mensaje.setBorder(new TitledBorder(null, "Eliminado", TitledBorder.LEADING, TitledBorder.TOP, null, null));
				mensaje.setBounds(10, pos, 588, 36);
				panelMensajes.add(mensaje);
				mensaje.setLayout(null);
				
				JLabel labelmensaje = new JLabel("Este mensaje ha sido eliminado");
				labelmensaje.setBounds(10, 14, 588, 14);
				mensaje.add(labelmensaje);	
				pos += inc;
				parent.add(mensaje);			
			}else {
				JPanel mensaje = new JPanel();
				mensaje.setBorder(new TitledBorder(null, mensajes.get(i), TitledBorder.LEADING, TitledBorder.TOP, null, null));
				mensaje.setBounds(10, pos, 588, 36);
				panelMensajes.add(mensaje);
				mensaje.setLayout(null);
				
				JLabel labelmensaje = new JLabel(mensajes.get(i+1));
				labelmensaje.setBounds(10, 14, 588, 14);
				mensaje.add(labelmensaje);		
				pos += inc;
				parent.add(mensaje);		
			}
		}
		scrollerMensajes.getViewport().setViewPosition(new Point(1,pos));
		
		parent.setPreferredSize(new Dimension(parent.getWidth()-20,pos));
		parent.revalidate();
		parent.repaint();
	}
	
	
	
	public void logOut(Login log) {
		log.setVisible(true);
		dispose();
	}
}
