package gui;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import code.Connection;

import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;
import javax.swing.ImageIcon;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

public class Amigos extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private final JPanel panelAmigos = new JPanel();

	//private int amigo = 0;
	
	/**
	 * Create the dialog.
	 */
	public Amigos(Connection cn, JFrame parent) {
		getContentPane().setBackground(Color.LIGHT_GRAY);
		setBounds(100, 100, 450, 423);
		getContentPane().setLayout(null);
		panelAmigos.setBackground(Color.DARK_GRAY);
		panelAmigos.setBounds(0, 47, 434, 337);
		panelAmigos.setBorder(new EmptyBorder(5, 5, 5, 5));
		panelAmigos.setLayout(null);		
		{
			JPanel panel = new JPanel();
			panel.setBackground(Color.LIGHT_GRAY);
			panel.setBounds(0, 0, 434, 47);
			getContentPane().add(panel);
			panel.setLayout(null);
			{
				JButton buttonVolver = new JButton("");
				buttonVolver.setBounds(0, 3, 65, 41);
				panel.add(buttonVolver);
				buttonVolver.setContentAreaFilled(false);
				buttonVolver.setBorderPainted(false);
				buttonVolver.setIcon(new ImageIcon(Amigos.class.getResource("/img/return.png")));
				{
					JButton buttonAnyadir = new JButton("");
					buttonAnyadir.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							String nom_u = JOptionPane.showInputDialog(null, "Introduce el nombre del usuario a añadir.", "Agregar usuario", JOptionPane.DEFAULT_OPTION);
							cn.addFriend(nom_u);
						}
					});
					buttonAnyadir.setIcon(new ImageIcon(Amigos.class.getResource("/img/add-user.png")));
					buttonAnyadir.setContentAreaFilled(false);
					buttonAnyadir.setBorderPainted(false);
					buttonAnyadir.setBounds(369, 3, 65, 41);
					panel.add(buttonAnyadir);
				}
				buttonVolver.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						dispose();
						parent.setVisible(true);						
					}
				});
			}
		}
		
		JScrollPane scroller = new JScrollPane();
		scroller.setBounds(panelAmigos.getBounds());
		scroller.getVerticalScrollBar().setUnitIncrement(7);
		scroller.setViewportView(panelAmigos);
		getContentPane().add(scroller);
		cargarAmigos(panelAmigos, cn);
	}
	
	private void cargarAmigos (JPanel parent, Connection cn) {
		ArrayList<String> amigos = cn.loadFriend();
		System.out.println(amigos.size());
		int pos = 10;
		int inc = 61;
		
		for (int i = 0; i < amigos.size(); i++) {
			JPanel amigo = new JPanel();
			
			amigo.setBounds(10, pos, 394, 50);
			amigo.setBackground(Color.LIGHT_GRAY);
			amigo.setLayout(null);
			
			JLabel nom = new JLabel();
			nom.setBounds(10, 18, 160, 15);
			nom.setForeground(Color.white);
			nom.setText(amigos.get(i));
			amigo.add(nom);
			

			JButton but1 = new JButton();
			but1.setBounds(184, 13, 89, 23);

			JButton but2 = new JButton();
			but2.setBounds(283, 13, 89, 23);	
			
			if(sonAmigos(cn, amigos.get(i)) > 0) {
				but1.setText("Hablar");
				but1.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						
					}
				});
				amigo.add(but1);
				
				but2.setText("Borrar");
				but2.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						cn.deleteFriend(nom.getText());
						vaciarPanel(parent);
						cargarAmigos(parent, cn);
					}
				});
				amigo.add(but2);
			}else {
				but1.setText("Aceptar");
				but1.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						cn.acceptFriend(nom.getText());
						vaciarPanel(parent);
						cargarAmigos(parent, cn);						
					}
				});
				amigo.add(but1);
				
				but2.setText("Denegar");
				but2.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						cn.deleteFriend(nom.getText());
						vaciarPanel(parent);
						cargarAmigos(parent, cn);
					}
				});
				amigo.add(but2);
			}

			pos += inc;
			parent.add(amigo);
		}

		parent.setPreferredSize(new Dimension(150,pos));
		parent.revalidate();
		parent.repaint();
	}
	
	private int sonAmigos(Connection cn, String nom_u) {
		return cn.itsFriend(nom_u);
	}
	
	private void vaciarPanel(JPanel parent) {
		parent.removeAll();
	}
	
}
