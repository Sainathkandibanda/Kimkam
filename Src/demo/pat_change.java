package demo;

import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
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

public class pat_change {
	
	public static String username1;
	public static String password1;
	public static String password2;
	int p = 0;
	int u = 0;

	private JFrame frmChangePassword;
	private JTextField username;
	private JPasswordField pass1;
	private JTextField pass2;
	private JButton submit;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					pat_change window = new pat_change();
					window.frmChangePassword.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public pat_change() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmChangePassword = new JFrame();
		frmChangePassword.setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\saina\\Documents\\Programsinjava\\demo_schedoc\\demo_schedoc\\Images\\stethoscope.png"));
		frmChangePassword.setTitle("Change Password");
		frmChangePassword.setBounds(100, 100, 650, 600);
		frmChangePassword.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmChangePassword.getContentPane().setLayout(null);
		frmChangePassword.getContentPane().setBackground( Color.decode("#e9f5dc") );
		
		JButton back = new JButton("Back");
		back.setFocusable(false);
		back.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				pat_edit pe = new pat_edit();
				frmChangePassword.dispose();
				pe.setVisible(true);
			}
		});
		back.setFont(new Font("Tahoma", Font.PLAIN, 20));
		back.setBounds(32, 39, 115, 33);
		frmChangePassword.getContentPane().add(back);
		
		JLabel lblChangePassword = new JLabel("Change Password");
		lblChangePassword.setFont(new Font("Tahoma", Font.PLAIN, 28));
		lblChangePassword.setBounds(154, 104, 342, 45);
		frmChangePassword.getContentPane().add(lblChangePassword);
		
		JButton logout = new JButton("LogOut");
		logout.setFocusable(false);
		logout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				home h = new home();
				frmChangePassword.dispose();
				h.setVisible(true);
			}
		});
		logout.setFont(new Font("Tahoma", Font.PLAIN, 20));
		logout.setBounds(480, 39, 115, 33);
		frmChangePassword.getContentPane().add(logout);
		
		JLabel lblNewLabel_1 = new JLabel("Username");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel_1.setBounds(91, 195, 103, 25);
		frmChangePassword.getContentPane().add(lblNewLabel_1);
		
		username = new JTextField();
		username.addKeyListener(new KeyAdapter() {
		    public void keyTyped(KeyEvent e) { 
		        if (username.getText().length() >= 20||e.getKeyChar()==32 ) // limit user_name to 20 characters
		            e.consume(); 
		    }  
		});
		username.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		username.setFont(new Font("Tahoma", Font.PLAIN, 20));
		username.setColumns(10);
		username.setBounds(307, 195, 169, 30);
		frmChangePassword.getContentPane().add(username);
		
		JLabel lblNewLabel_1_1 = new JLabel("Password");
		lblNewLabel_1_1.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel_1_1.setBounds(91, 288, 103, 25);
		frmChangePassword.getContentPane().add(lblNewLabel_1_1);
		
		JLabel lblNewLabel_1_2 = new JLabel("Confirm Password");
		lblNewLabel_1_2.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel_1_2.setBounds(91, 375, 169, 25);
		frmChangePassword.getContentPane().add(lblNewLabel_1_2);
		
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
			}
		});
		pass1.setBounds(307, 288, 169, 30);
		frmChangePassword.getContentPane().add(pass1);
		
		pass2 = new JTextField();
		pass2.addKeyListener(new KeyAdapter() {
		    public void keyTyped(KeyEvent e) { 
		        if (pass2.getText().length() >= 20||e.getKeyChar()==32 ) // limit user_name to 20 characters
		            e.consume(); 
		    }  
		});
		pass2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		pass2.setFont(new Font("Tahoma", Font.PLAIN, 20));
		pass2.setColumns(10);
		pass2.setBounds(307, 375, 169, 30);
		frmChangePassword.getContentPane().add(pass2);
		
		submit = new JButton("SUBMIT");
		submit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				submitActionPerformed(e);
			}
		});
		submit.setFont(new Font("Tahoma", Font.PLAIN, 20));
		submit.setBounds(307, 456, 169, 33);
		frmChangePassword.getContentPane().add(submit);
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
				JOptionPane.showMessageDialog(frmChangePassword,"Password updated successfully");
				pat_edit pe = new pat_edit();
				frmChangePassword.dispose();
				pe.setVisible(true);
			}
			else
			{
				JOptionPane.showMessageDialog(frmChangePassword,"Error");
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
				JOptionPane.showMessageDialog(frmChangePassword, "User does not exists");
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
			JOptionPane.showMessageDialog(frmChangePassword, "Passwords do not match");
		}
	}
	

	public void setVisible(boolean b) {
		frmChangePassword.setVisible(true);
	}


}