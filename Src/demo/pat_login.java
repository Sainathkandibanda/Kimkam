package demo;

import java.awt.Color;
import java.awt.EventQueue;
import java.sql.*;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.ActionEvent;
import javax.swing.JPasswordField;
import java.awt.Toolkit;

public class pat_login {
	
	public static String username1;
	public static String password1;
	public static String user;
	public static String pass;
	int u = 0;
	
	
	
	
	

	private JFrame frmPatientLogin;
	private JTextField username;
	private JPasswordField password;
	private JButton submit;
	private JButton forgot;
	private JButton back;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					pat_login window = new pat_login();
					window.frmPatientLogin.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public pat_login() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmPatientLogin = new JFrame();
		frmPatientLogin.setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\saina\\Documents\\Programsinjava\\demo_schedoc\\demo_schedoc\\Images\\stethoscope.png"));
		frmPatientLogin.setTitle("Patient Login");
		frmPatientLogin.setBounds(100, 100, 650, 600);
		frmPatientLogin.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmPatientLogin.getContentPane().setLayout(null);
		frmPatientLogin.getContentPane().setBackground( Color.decode("#f3e6f5") );
		
		JLabel lblNewLabel = new JLabel("Patient Login");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 28));
		lblNewLabel.setBounds(208, 88, 212, 60);
		frmPatientLogin.getContentPane().add(lblNewLabel);
		
		JLabel userLabel = new JLabel("Username");
		userLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
		userLabel.setBounds(118, 182, 153, 25);
		frmPatientLogin.getContentPane().add(userLabel);
		
		JLabel passLabel = new JLabel("Password");
		passLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
		passLabel.setBounds(118, 280, 153, 25);
		frmPatientLogin.getContentPane().add(passLabel);
		
		username = new JTextField();
		username.addKeyListener(new KeyAdapter() {
		    public void keyTyped(KeyEvent e) { 
		        if (username.getText().length() >= 20||e.getKeyChar()==32 ) // limit user_name to 20 characters
		            e.consume(); 
		    }  
		});
		username.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				usernameActionPerformed(e);
			}
		});
		username.setFont(new Font("Tahoma", Font.PLAIN, 20));
		username.setBounds(300, 179, 195, 30);
		frmPatientLogin.getContentPane().add(username);
		username.setColumns(10);
		
		password = new JPasswordField();
		password.addKeyListener(new KeyAdapter() {
		    @SuppressWarnings("deprecation")
			public void keyTyped(KeyEvent e) { 
		        if (password.getText().length() >= 20||e.getKeyChar()==32 ) // limit user_name to 20 characters
		            e.consume(); 
		    }  
		});
		password.setFont(new Font("Tahoma", Font.PLAIN, 20));
		password.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				passwordActionPerformed(e);
			}
		});
		password.setBounds(300, 280, 195, 30);
		frmPatientLogin.getContentPane().add(password);
		
		submit = new JButton("Submit");
		submit.setBackground(Color.decode("#f5f3e6"));
		submit.setFocusable(false);
		submit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				submitActionPerformed(e);
			}
		});
		submit.setFont(new Font("Tahoma", Font.PLAIN, 20));
		submit.setBounds(300, 380, 195, 38);
		frmPatientLogin.getContentPane().add(submit);
		
		forgot = new JButton("Forgot Password");
		forgot.setBackground(Color.decode("#f5f3e6"));
		forgot.setFocusable(false);
		forgot.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				forgotActionPerformed(e);
			}
		});
		forgot.setFont(new Font("Tahoma", Font.PLAIN, 20));
		forgot.setBounds(76, 380, 195, 38);
		frmPatientLogin.getContentPane().add(forgot);
		
		back = new JButton("Back");
		back.setBackground(Color.decode("#f5f3e6"));
		back.setFocusable(false);
		back.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dialog_login dl = new dialog_login();
				frmPatientLogin.dispose();
				dl.setVisible(true);
			}
		});
		back.setFont(new Font("Tahoma", Font.PLAIN, 20));
		back.setBounds(73, 48, 115, 33);
		frmPatientLogin.getContentPane().add(back);
		
		
	}
	private void submitActionPerformed(ActionEvent e)
	{
		usernameActionPerformed(e);
		passwordActionPerformed(e);
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/sys","root","Saigopi@12");
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery("select * from pat");
			while (rs.next())
			{
				user = rs.getString(1);
				pass = rs.getString(2);
				if (user.equals(username1)&&pass.equals(password1))
				{
					JOptionPane.showMessageDialog(frmPatientLogin,username1+", Logged In successfully");
					u = 1;
					pat_home ph = new pat_home();
					frmPatientLogin.dispose();
					ph.setVisible(true);
				}
				
			}
			if(u==0)
			{
				JOptionPane.showMessageDialog(frmPatientLogin, "Username or Password is Incorrect");
			}
		} catch (ClassNotFoundException | SQLException e1) {
			e1.printStackTrace();
		}
				
	}
	
	private void usernameActionPerformed(ActionEvent e)
	{
		username1 = username.getText();
	}
	@SuppressWarnings("deprecation")
	private void passwordActionPerformed(ActionEvent e)
	{
		password1 = password.getText();
	}
	private void forgotActionPerformed(ActionEvent e)
	{
		pat_forgot pf = new pat_forgot();
		frmPatientLogin.dispose();
		pf.setVisible(true);
	}
	public void setVisible(boolean b) {
		frmPatientLogin.setVisible(true);
	}
} 	
