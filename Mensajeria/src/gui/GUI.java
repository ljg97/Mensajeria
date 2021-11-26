package gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import code.Connection;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import java.awt.Dimension;

import java.awt.Color;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JLabel;

public class GUI extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	static Connection cn = new Connection();
	private JTextField textField;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		cn.Conectar();
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
				| UnsupportedLookAndFeelException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GUI frame = new GUI();
					Login log = new Login(cn, frame);
					log.setVisible(true);
					frame.setVisible(false);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public GUI() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 847, 589);
		contentPane = new JPanel();
		contentPane.setBackground(Color.DARK_GRAY);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panelBotones = new JPanel();
		panelBotones.setBackground(Color.LIGHT_GRAY);
		panelBotones.setBounds(0, 0, 831, 49);
		contentPane.add(panelBotones);
		panelBotones.setLayout(null);
		
		JButton buttonAmigos = new JButton("");
		buttonAmigos.setBounds(10, 4, 65, 41);
		panelBotones.add(buttonAmigos);
		buttonAmigos.setContentAreaFilled(false);
		buttonAmigos.setBorderPainted(false);
		buttonAmigos.setIcon(new ImageIcon(GUI.class.getResource("/img/friends.png")));
		
		JButton buttonLogOut = new JButton("");
		buttonLogOut.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				logOut();
			}
		});
		buttonLogOut.setIcon(new ImageIcon(GUI.class.getResource("/img/log-out.png")));
		buttonLogOut.setContentAreaFilled(false);
		buttonLogOut.setBorderPainted(false);
		buttonLogOut.setBounds(766, 4, 65, 41);
		panelBotones.add(buttonLogOut);
		
		JPanel panelChats = new JPanel();
		panelChats.setBackground(Color.LIGHT_GRAY);
		panelChats.setBounds(0, 50, 194, 500);
		panelChats.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.DARK_GRAY);
		panel.setBounds(193, 50, 638, 500);
		contentPane.add(panel);
		panel.setLayout(null);
		
		textField = new JTextField();
		textField.setBounds(10, 459, 618, 30);
		panel.add(textField);
		textField.setColumns(10);
		
		JPanel panelMensajes = new JPanel();
		panelMensajes.setBackground(Color.LIGHT_GRAY);
		panelMensajes.setBounds(10, 11, 618, 437);
		panel.add(panelMensajes);
		panelMensajes.setLayout(null);
		buttonAmigos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				verAmigos();
			}
		});
		

		JScrollPane scroller = new JScrollPane();
		scroller.setBounds(panelChats.getBounds());
		scroller.getVerticalScrollBar().setUnitIncrement(7);
		scroller.setViewportView(panelChats);
		getContentPane().add(scroller);
		cargarChats(panelChats);
	}
	
	private void cargarChats (JPanel parent) {
		int pos = 10;
		int inc = 61;
		
		for (int i = 0; i < 20; i++) {
			JPanel chat = new JPanel();			
			chat.setBounds(10, pos, 155, 50);
			chat.setBackground(Color.DARK_GRAY);
			chat.setLayout(null);
			
			JLabel nom = new JLabel();
			nom.setBounds(10, 10, 135, 15);
			nom.setForeground(Color.white);
			nom.setText("Nombre Prueba");
			chat.add(nom);
			
			pos += inc;
			parent.add(chat);
		}

		parent.setPreferredSize(new Dimension(150,pos));
		parent.revalidate();
		parent.repaint();
	}
	
	public void logOut() {
		Login log = new Login(cn, this);
		log.setVisible(true);
		setVisible(false);		
	}
	
	public void verAmigos() {
		Amigos log = new Amigos(cn, this);
		log.setVisible(true);
		setVisible(false);						
	}
}
