package demo;

import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.ActionEvent;
import java.sql.*;
import javax.swing.JButton;
import java.awt.Toolkit;
public class pat_forgot {
	
	public static String username1;
	public static String password1;
	public static String password2;
	int p = 0;
	int u = 0;
	
	
	

	private JFrame frmForgotPassword;
	private JTextField pass2;
	private JTextField username;
	private JPasswordField pass1;
	private JButton submit;
	private JButton back;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					pat_forgot window = new pat_forgot();
					window.frmForgotPassword.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public pat_forgot() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmForgotPassword = new JFrame();
		frmForgotPassword.setTitle("Forgot Password");
		frmForgotPassword.setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\saina\\Documents\\Programsinjava\\demo_schedoc\\demo_schedoc\\Images\\stethoscope.png"));
		frmForgotPassword.setBounds(100, 100, 650, 600);
		frmForgotPassword.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmForgotPassword.getContentPane().setLayout(null);
		frmForgotPassword.getContentPane().setBackground( Color.decode("#e6f5f3") );
		
		JLabel lblNewLabel = new JLabel("Forgot Password");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 28));
		lblNewLabel.setBounds(203, 84, 342, 45);
		frmForgotPassword.getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Username");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel_1.setBounds(113, 165, 103, 25);
		frmForgotPassword.getContentPane().add(lblNewLabel_1);
		
		JLabel lblNewLabel_1_1 = new JLabel("Password");
		lblNewLabel_1_1.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel_1_1.setBounds(113, 246, 103, 25);
		frmForgotPassword.getContentPane().add(lblNewLabel_1_1);
		
		JLabel lblNewLabel_1_2 = new JLabel("Confirm Password");
		lblNewLabel_1_2.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel_1_2.setBounds(113, 322, 169, 25);
		frmForgotPassword.getContentPane().add(lblNewLabel_1_2);
		
		pass2 = new JTextField();
		pass2.addKeyListener(new KeyAdapter() {
		    public void keyTyped(KeyEvent e) { 
		        if (username.getText().length() >= 20||e.getKeyChar()==32 ) // limit user_name to 20 characters
		            e.consume(); 
		    }  
		});
		pass2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				pass2ActionPerformed(e);
			}
		});
		pass2.setFont(new Font("Tahoma", Font.PLAIN, 20));
		pass2.setBounds(351, 322, 169, 30);
		frmForgotPassword.getContentPane().add(pass2);
		pass2.setColumns(10);
		
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
		username.setBounds(351, 165, 169, 30);
		frmForgotPassword.getContentPane().add(username);
		
		pass1 = new JPasswordField();
		pass1.addKeyListener(new KeyAdapter() {
		    @SuppressWarnings("deprecation")
			public void keyTyped(KeyEvent e) { 
		        if (pass1.getText().length() >= 20||e.getKeyChar()==32 ) // limit user_name to 20 characters
		            e.consume(); 
		    }  
		});
		pass1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				pass1ActionPerformed(e);
			}
		});
		pass1.setBounds(351, 241, 169, 30);
		frmForgotPassword.getContentPane().add(pass1);
		
		submit = new JButton("Submit");
		submit.setBackground(Color.decode("#f5f3e6"));
		submit.setFocusable(false);
		submit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				submitActionPerformed(e);
			}
		});
		submit.setFont(new Font("Tahoma", Font.PLAIN, 20));
		submit.setBounds(351, 410, 169, 33);
		frmForgotPassword.getContentPane().add(submit);
		
		back = new JButton("Back");
		back.setBackground(Color.decode("#f5f3e6"));
		back.setFocusable(false);
		back.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				backActionPerformed(e);
			}
		});
		back.setFont(new Font("Tahoma", Font.PLAIN, 20));
		back.setBounds(28, 34, 115, 33);
		frmForgotPassword.getContentPane().add(back);
	}
	private void submitActionPerformed(ActionEvent e)
	{
		usernameActionPerformed(e);
		pass1ActionPerformed(e);
		pass2ActionPerformed(e);
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/sys","root","Saigopi@12");
			Statement st = con.createStatement();
			
			if(u==1 && p==1)
			{
				st.executeUpdate("update pat set password='"+password2+"' where username='"+username1+"'");
				JOptionPane.showMessageDialog(frmForgotPassword,"Password updated successfully");
				pat_login pl = new pat_login();
				frmForgotPassword.dispose();
				pl.setVisible(true);
			}
			else
			{
				JOptionPane.showMessageDialog(frmForgotPassword,"Error");
			}
			con.close();
			
		} catch (ClassNotFoundException | SQLException e1) {
			e1.printStackTrace();
		}		
	}
	private void usernameActionPerformed(ActionEvent e)
	{
		username1 = username.getText();
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/sys","root","Saigopi@12");
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery("select * from pat");
			while(rs.next())
			{
				if (username1.equals(rs.getString(1)))
				{
					u = 1;  // User existed
				}
			}
			if(u==0)  // User does not exist
			{
				JOptionPane.showMessageDialog(frmForgotPassword, "User does not exists");
			}
			
		} catch (ClassNotFoundException | SQLException e1) {
			e1.printStackTrace();
		}
		
	}
	@SuppressWarnings("deprecation")
	private void pass1ActionPerformed(ActionEvent e)
	{
		password1 = pass1.getText();
	}
	private void pass2ActionPerformed(ActionEvent e)
	{
		password2 = pass2.getText();
		if(password2.equals(password1))
		{
			p = 1; // passwords matches
		}
		else
		{
			JOptionPane.showMessageDialog(frmForgotPassword, "Passwords do not match");
		}
	}
	private void backActionPerformed(ActionEvent e)
	{
		pat_login pl = new pat_login();
		frmForgotPassword.dispose();
		pl.setVisible(true);
	}

	public void setVisible(boolean b) {
		frmForgotPassword.setVisible(true);
	}

}