package gui;

import java.awt.BorderLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import code.Connection;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;

public class Login extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JPasswordField passwordField;
	private JTextField textField;
	private JButton btnLogin;
	
	/**
	 * Create the dialog.
	 */
	public Login(Connection cn, JFrame parent) {
		setBounds(100, 100, 450, 190);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(Color.DARK_GRAY);
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		
		JLabel labelUser = new JLabel("Usuario:");
		labelUser.setForeground(Color.WHITE);
		
		JLabel labelPassword = new JLabel("Contrase\u00F1a:");
		labelPassword.setForeground(Color.WHITE);
		
		passwordField = new JPasswordField();
		
		textField = new JTextField();
		textField.setColumns(10);
		
		GroupLayout gl_contentPanel = new GroupLayout(contentPanel);
		gl_contentPanel.setHorizontalGroup(
			gl_contentPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPanel.createSequentialGroup()
					.addGap(30)
					.addComponent(labelUser)
					.addGap(10)
					.addComponent(textField, GroupLayout.PREFERRED_SIZE, 319, GroupLayout.PREFERRED_SIZE))
				.addGroup(gl_contentPanel.createSequentialGroup()
					.addGap(10)
					.addComponent(labelPassword)
					.addGap(10)
					.addComponent(passwordField, GroupLayout.PREFERRED_SIZE, 319, GroupLayout.PREFERRED_SIZE))
		);
		gl_contentPanel.setVerticalGroup(
			gl_contentPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPanel.createSequentialGroup()
					.addGap(11)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPanel.createSequentialGroup()
							.addGap(3)
							.addComponent(labelUser))
						.addComponent(textField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPanel.createSequentialGroup()
							.addGap(3)
							.addComponent(labelPassword))
						.addComponent(passwordField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
		);
		contentPanel.setLayout(gl_contentPanel);
		{
			JLabel labelError = new JLabel("");
			labelError.setHorizontalAlignment(SwingConstants.CENTER);
			labelError.setRequestFocusEnabled(false);
			labelError.setFocusTraversalKeysEnabled(false);
			labelError.setFocusable(false);
			labelError.setHorizontalTextPosition(SwingConstants.CENTER);
			labelError.setFont(new Font("Tahoma", Font.PLAIN, 14));
			labelError.setForeground(Color.RED);
			
			JPanel buttonPane = new JPanel();
			buttonPane.setBackground(Color.LIGHT_GRAY);
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			
			JButton btnRegistrar = new JButton("");
			btnRegistrar.setContentAreaFilled(false);
			btnRegistrar.setBorderPainted(false);
			btnRegistrar.setIcon(new ImageIcon(Login.class.getResource("/img/register.png")));
			btnRegistrar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					int result = cn.insertUser(textField.getText(), String.valueOf(passwordField.getPassword()));
					if (result == 1) {
						dispose();
						parent.setVisible(true);
					} else {
						labelError.setText("Error al registrar el usuario.");
					}
				}
			});
			{
				btnLogin = new JButton("");
				btnLogin.setBorderPainted(false);
				btnLogin.setContentAreaFilled(false);
				btnLogin.setIcon(new ImageIcon(Login.class.getResource("/img/log-in.png")));
				btnLogin.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						int result = cn.loginUser(textField.getText(), String.valueOf(passwordField.getPassword()));
						if (result == 1) {
							dispose();
							parent.setVisible(true);
						} else {
							labelError.setText("Error al iniciar el usuario.");
						}
					}
				});
				btnLogin.setActionCommand("OK");
				getRootPane().setDefaultButton(btnLogin);
			}
			
			GroupLayout gl_buttonPane = new GroupLayout(buttonPane);
			gl_buttonPane.setHorizontalGroup(
				gl_buttonPane.createParallelGroup(Alignment.LEADING)
					.addGroup(gl_buttonPane.createSequentialGroup()
						.addContainerGap()
						.addComponent(btnRegistrar)
						.addGap(18)
						.addComponent(labelError, GroupLayout.DEFAULT_SIZE, 248, Short.MAX_VALUE)
						.addGap(18)
						.addComponent(btnLogin)
						.addContainerGap())
			);
			gl_buttonPane.setVerticalGroup(
				gl_buttonPane.createParallelGroup(Alignment.LEADING)
					.addGroup(gl_buttonPane.createSequentialGroup()
						.addGroup(gl_buttonPane.createParallelGroup(Alignment.LEADING)
							.addGroup(gl_buttonPane.createSequentialGroup()
								.addGap(3)
								.addGroup(gl_buttonPane.createParallelGroup(Alignment.BASELINE)
									.addComponent(btnRegistrar)
									.addComponent(btnLogin)))
							.addGroup(gl_buttonPane.createSequentialGroup()
								.addGap(16)
								.addComponent(labelError, GroupLayout.PREFERRED_SIZE, 22, GroupLayout.PREFERRED_SIZE)))
						.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
			);
			buttonPane.setLayout(gl_buttonPane);
		}
	}
}
