package gui;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import code.Connection;
import java.awt.Dimension;

import java.awt.Color;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.border.TitledBorder;

public class GUI extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final JPanel panelChats = new JPanel();
	private final JPanel panelMensajes = new JPanel();
	private final JPanel panelAmigos = new JPanel();
	private final JPanel panelPeticiones = new JPanel();
	/**
	 * Launch the application.
	 */

	/**
	 * Create the frame.
	 */
	public GUI(Login log, Connection cn) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 847, 589);
		getContentPane().setBackground(Color.DARK_GRAY);
		getContentPane().setLayout(null);
		
		panelChats.setBackground(Color.GRAY);
		panelChats.setBounds(0, 48, 200, 502);
		panelChats.setBorder(new EmptyBorder(5, 5, 5, 5));
		panelChats.setLayout(null);
		
		panelMensajes.setBackground(Color.DARK_GRAY);
		panelMensajes.setBounds(200, 48, 630, 470);
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
		buttonVolver.setEnabled(false);
		
		JButton buttonAnyadir = new JButton("");
		buttonAnyadir.setIcon(new ImageIcon(GUI.class.getResource("/img/add-user.png")));
		buttonAnyadir.setContentAreaFilled(false);
		buttonAnyadir.setBorderPainted(false);
		buttonAnyadir.setBounds(766, 4, 65, 41);
		panelBotones.add(buttonAnyadir);
		buttonAnyadir.setVisible(false);
		buttonAnyadir.setEnabled(false);

		JScrollPane scrollerChats = new JScrollPane();
		scrollerChats.setBounds(panelChats.getBounds());
		scrollerChats.getVerticalScrollBar().setUnitIncrement(7);
		scrollerChats.setViewportView(panelChats);
		getContentPane().add(scrollerChats);
		
		JScrollPane scrollerMensajes = new JScrollPane();
		scrollerMensajes.setBounds(panelMensajes.getBounds());
		scrollerMensajes.getVerticalScrollBar().setUnitIncrement(7);
		scrollerMensajes.setViewportView(panelMensajes);
		getContentPane().add(scrollerMensajes);
		
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
				buttonAmigos.setEnabled(true);
				buttonLogOut.setVisible(true);
				buttonLogOut.setEnabled(true);
				buttonVolver.setVisible(false);
				buttonVolver.setEnabled(false);
				buttonAnyadir.setVisible(false);
				buttonAnyadir.setEnabled(false);
				scrollerChats.setVisible(true);
				scrollerMensajes.setVisible(true);
				scrollerAmigos.setVisible(false);
				scrollerPeticiones.setVisible(false);
				cargarChats(panelChats, cn);
			}
		});
		
		buttonAmigos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				buttonAmigos.setVisible(false);
				buttonAmigos.setEnabled(false);
				buttonLogOut.setVisible(false);
				buttonLogOut.setEnabled(false);
				buttonVolver.setVisible(true);
				buttonVolver.setEnabled(true);
				buttonAnyadir.setVisible(true);
				buttonAnyadir.setEnabled(true);
				scrollerChats.setVisible(false);
				scrollerMensajes.setVisible(false);
				scrollerAmigos.setVisible(true);
				scrollerPeticiones.setVisible(true);
				cargarAmigos(panelAmigos, cn, buttonVolver.getActionListeners()[0]);
				cargarPeticiones(panelPeticiones, cn, buttonVolver.getActionListeners()[0]);
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
		ArrayList<Integer> chats = cn.loadChat();
		int pos = 10;
		int inc = 61;
		
		for (int i = 0; i < chats.size(); i++) {
			int id = chats.get(i);
			JPanel chat = new JPanel();			
			chat.setBounds(10, pos, 165, 50);
			chat.setBackground(Color.DARK_GRAY);
			chat.setLayout(null);
			
			JLabel nom = new JLabel();
			nom.setBounds(10, 10, 135, 15);
			nom.setForeground(Color.white);
			nom.setText(cn.getNameChat(id));
			chat.add(nom);
			
			chat.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					cargarMensajes(panelMensajes, cn, id);
					
					JTextField tfMensaje = new JTextField();
					tfMensaje.setColumns(10);
					tfMensaje.setBounds(200,518,630,32);
					getContentPane().add(tfMensaje);
					
					tfMensaje.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							cn.sendMensaje(id, tfMensaje.getText());
							cargarMensajes(panelMensajes, cn, id);
						}
					});
					tfMensaje.grabFocus();
				}
			});
			
			pos += inc;
			parent.add(chat);
		}
	}
	
	private void cargarMensajes (JPanel parent, Connection cn, int idc) {
		ArrayList<String> mensajes = cn.loadMensajes(idc);
		int pos = 10;
		int inc = 47;
		
		for (int i = 0; i < mensajes.size(); i+=2) {
			JPanel mensaje = new JPanel();
			mensaje.setBorder(new TitledBorder(null, mensajes.get(i), TitledBorder.LEADING, TitledBorder.TOP, null, null));
			mensaje.setBounds(10, pos, 608, 36);
			panelMensajes.add(mensaje);
			mensaje.setLayout(null);
			
			JLabel labelmensaje = new JLabel(mensajes.get(i+1));
			labelmensaje.setBounds(10, 14, 588, 14);
			mensaje.add(labelmensaje);
			
			pos += inc;
			parent.add(mensaje);
		}
		parent.revalidate();
		parent.repaint();
	}
	
	public void logOut(Login log) {
		log.setVisible(true);
		dispose();
	}
	
	public void verAmigos() {
		
	}
}
