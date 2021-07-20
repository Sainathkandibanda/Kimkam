package demo;

import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.awt.event.ActionEvent;
import java.awt.Toolkit;

public class doc_login {
	public static String username1;
	public static String password1;
	public static String user;
	public static String pass;
	int u = 0;

	private JFrame frmDoctorLogin;
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
					doc_login window = new doc_login();
					window.frmDoctorLogin.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public doc_login() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmDoctorLogin = new JFrame();
		frmDoctorLogin.setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\saina\\Documents\\Programsinjava\\demo_schedoc\\demo_schedoc\\Images\\stethoscope.png"));
		frmDoctorLogin.setTitle("Doctor Login");
		frmDoctorLogin.setBounds(100, 100, 650, 600);
		frmDoctorLogin.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmDoctorLogin.getContentPane().setLayout(null);
		frmDoctorLogin.getContentPane().setBackground( Color.decode("#f3e6f5") );
		
		JLabel lblNewLabel = new JLabel("Doctor Login");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 28));
		lblNewLabel.setBounds(208, 88, 165, 60);
		frmDoctorLogin.getContentPane().add(lblNewLabel);
		
		JLabel userLabel = new JLabel("Username");
		userLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
		userLabel.setBounds(129, 200, 153, 25);
		frmDoctorLogin.getContentPane().add(userLabel);
		
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
		username.setColumns(10);
		username.setBounds(302, 197, 179, 30);
		frmDoctorLogin.getContentPane().add(username);
		
		JLabel passLabel = new JLabel("Password");
		passLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
		passLabel.setBounds(129, 300, 153, 25);
		frmDoctorLogin.getContentPane().add(passLabel);
		
		password = new JPasswordField();
		password.addKeyListener(new KeyAdapter() {
		    @SuppressWarnings("deprecation")
			public void keyTyped(KeyEvent e) { 
		        if (password.getText().length() >= 20||e.getKeyChar()==32 ) // limit user_name to 20 characters
		            e.consume(); 
		    }  
		});
		password.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				passwordActionPerformed(e);
			}
		});
		password.setFont(new Font("Tahoma", Font.PLAIN, 20));
		password.setBounds(302, 300, 179, 30);
		frmDoctorLogin.getContentPane().add(password);
		
		forgot = new JButton("Forgot Password");
		forgot.setBackground(Color.decode("#f5f3e6"));
		forgot.setFocusable(false);
		forgot.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				forgotActionPerformed(e);
			}
		});
		forgot.setFont(new Font("Tahoma", Font.PLAIN, 20));
		forgot.setBounds(73, 400, 201, 38);
		frmDoctorLogin.getContentPane().add(forgot);
		
		submit = new JButton("Submit");
		submit.setBackground(Color.decode("#f5f3e6"));
		submit.setFocusable(false);
		

		submit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				submitActionPerformed(e);
			}
		});
		submit.setFont(new Font("Tahoma", Font.PLAIN, 20));
		submit.setBounds(302, 400, 201, 38);
		frmDoctorLogin.getContentPane().add(submit);
		
		back = new JButton("Back");
		back.setBackground(Color.decode("#f5f3e6"));
		back.setFocusable(false);
		

		back.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dialog_login dl = new dialog_login();
				frmDoctorLogin.dispose();
				dl.setVisible(true);
			}
		});
		back.setFont(new Font("Tahoma", Font.PLAIN, 20));
		back.setBounds(73, 48, 115, 33);
		frmDoctorLogin.getContentPane().add(back);
	}
	private void submitActionPerformed(ActionEvent e)
	{
		usernameActionPerformed(e);
		passwordActionPerformed(e);
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/sys","root","Saigopi@12");
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery("select * from doc");
			while (rs.next())
			{
				user = rs.getString(1);
				pass = rs.getString(2);
				if (user.equals(username1)&&pass.equals(password1))
				{
					JOptionPane.showMessageDialog(frmDoctorLogin,username1+", Logged In successfully");
					u = 1;
					doc_home doch = new doc_home();
					frmDoctorLogin.dispose();
					doch.setVisible(true);
				}
				
			}
			if(u==0)
			{
				JOptionPane.showMessageDialog(frmDoctorLogin, "Username or Password is Incorrect");
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
		doc_forgot df = new doc_forgot();
		frmDoctorLogin.dispose();
		df.setVisible(true);
	}
	public void setVisible(boolean b) {
		frmDoctorLogin.setVisible(true);
	}

}