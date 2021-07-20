package demo;

import java.awt.Color;
import java.awt.EventQueue;
import java.sql.*;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.ActionEvent;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import java.awt.Toolkit;

public class pat_edit {
	
	public String user1;
	int u = 0;
	public static String name;
	public static String email;
	public static String age;
	public static String gender;
	public static String bloodg1;
	public static String contact;
	public static String city;
	int c = 0;
	int g = 0;
	int b = 0;
	
	

	private JFrame frmEditProfile;
	private JTextField username;
	private JTextField Age;
	private JTextField Contact;
	private JTextField City;
	private JComboBox<String> Gender;
	private JComboBox<String> Bloodg;
	private JButton submit;
	private JButton logout;
	private JButton back;
	private JButton password_change;
	private JLabel lblNewLabel_1_2;
	private JTextField Name;
	private JTextField Email;
	private JLabel lblNewLabel_1_3;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					pat_edit window = new pat_edit();
					window.frmEditProfile.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public pat_edit() {
		initialize();
		new pat_login();
		user1 = pat_login.username1;   
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/sys","root","Saigopi@12");
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery("select * from pat where username = '"+user1+"'");
			rs.next();                                     ///Need to implement while loop for rs.next(); check availability.java
			username.setText(rs.getString(1));
			Age.setText(rs.getString(3));
			Gender.setSelectedItem(rs.getString(4));
			Bloodg.setSelectedItem(rs.getString(5));
			Contact.setText(rs.getString(6));
			City.setText(rs.getString(7));
			Name.setText(rs.getString(8));
			Email.setText(rs.getString(9));
			
			
			logout = new JButton("LogOut");
			logout.setFocusable(false);
			logout.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					logoutActionPerformed(e);
				}
			});
			logout.setFont(new Font("Tahoma", Font.PLAIN, 20));
			logout.setBounds(569, 50, 115, 33);
			frmEditProfile.getContentPane().add(logout);
			
			back = new JButton("Back");
			back.setFocusable(false);
			back.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					backActionPerformed(e);
				}
			});
			back.setFont(new Font("Tahoma", Font.PLAIN, 20));
			back.setBounds(34, 50, 97, 33);
			frmEditProfile.getContentPane().add(back);
			
			password_change = new JButton("Change Password");
			password_change.setFocusable(false);
			password_change.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					password_changeActionPerformed(e);
				}
			});
			password_change.setFont(new Font("Tahoma", Font.PLAIN, 20));
			password_change.setBounds(110, 790, 229, 33);
			frmEditProfile.getContentPane().add(password_change);
			
			lblNewLabel_1_2 = new JLabel("Last Name");
			lblNewLabel_1_2.setFont(new Font("Tahoma", Font.PLAIN, 20));
			lblNewLabel_1_2.setBounds(110, 230, 139, 26);
			frmEditProfile.getContentPane().add(lblNewLabel_1_2);
			
			
			
			lblNewLabel_1_3 = new JLabel("Email ID");
			lblNewLabel_1_3.setFont(new Font("Tahoma", Font.PLAIN, 20));
			lblNewLabel_1_3.setBounds(110, 310, 139, 26);
			frmEditProfile.getContentPane().add(lblNewLabel_1_3);
			
			
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		
		
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmEditProfile = new JFrame();
		frmEditProfile.setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\saina\\Documents\\Programsinjava\\demo_schedoc\\demo_schedoc\\Images\\stethoscope.png"));
		frmEditProfile.setTitle("Profile");
		frmEditProfile.setBounds(100, 100, 739, 911);
		frmEditProfile.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmEditProfile.getContentPane().setLayout(null);
		frmEditProfile.getContentPane().setBackground( Color.decode("#e9f5dc") );
		
		JLabel lblNewLabel = new JLabel("Manage Profile");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 28));
		lblNewLabel.setBounds(225, 31, 216, 65);
		frmEditProfile.getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("First Name");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel_1.setBounds(110, 150, 139, 26);
		frmEditProfile.getContentPane().add(lblNewLabel_1);
		
		username = new JTextField();
		username.setEditable(false);
		username.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		username.setFont(new Font("Tahoma", Font.PLAIN, 20));
		username.setBounds(375, 150, 245, 34);
		frmEditProfile.getContentPane().add(username);
		username.setColumns(10);
		
		JLabel lblNewLabel_1_1 = new JLabel("Age");
		lblNewLabel_1_1.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel_1_1.setBounds(110, 390, 139, 26);
		frmEditProfile.getContentPane().add(lblNewLabel_1_1);
		
		Name = new JTextField();
		Name.addKeyListener(new KeyAdapter() {
		    public void keyTyped(KeyEvent e) { 
		        if (Name.getText().length() >= 20 ) // limit Name to 20 characters
		            e.consume(); 
		    }  
		});
		Name.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				NameActionPerformed(e);
			}
		});
		Name.setText((String) null);
		Name.setFont(new Font("Tahoma", Font.PLAIN, 20));
		Name.setColumns(10);
		Name.setBounds(375, 230, 245, 34);
		frmEditProfile.getContentPane().add(Name);
		
		Email = new JTextField();
		Email.addKeyListener(new KeyAdapter() {
		    public void keyTyped(KeyEvent e) { 
		        if (Email.getText().length() >= 20 ) // limit Email to 20 characters
		            e.consume(); 
		    }  
		});
		Email.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				EmailActionPerformed(e);
			}
		});
		Email.setText((String) null);
		Email.setFont(new Font("Tahoma", Font.PLAIN, 20));
		Email.setColumns(10);
		Email.setBounds(375, 310, 245, 34);
		frmEditProfile.getContentPane().add(Email);
		
		Age = new JTextField();
		Age.addKeyListener(new KeyAdapter() {
		    public void keyTyped(KeyEvent e) { 
		        if (Age.getText().length() >= 3 || e.getKeyChar()<=47 || e.getKeyChar()>=58||e.getKeyChar()==32) // limit Age to 3 integers
		            e.consume(); 
		    }  
		});
		Age.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AgeActionPerformed(e);
			}
		});
		Age.setFont(new Font("Tahoma", Font.PLAIN, 20));
		Age.setColumns(10);
		Age.setBounds(375, 385, 245, 34);
		frmEditProfile.getContentPane().add(Age);
		
		JLabel lblNewLabel_1_1_1 = new JLabel("Contact");
		lblNewLabel_1_1_1.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel_1_1_1.setBounds(110, 470, 139, 26);
		frmEditProfile.getContentPane().add(lblNewLabel_1_1_1);
		
		Contact = new JTextField();
		Contact.addKeyListener(new KeyAdapter() {
		    public void keyTyped(KeyEvent e) { 
		        if (Contact.getText().length() >= 10 || e.getKeyChar()<=47 || e.getKeyChar()>=58) // limit Age to 10 integers
		            e.consume(); 
		    } 
		});
		Contact.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ContactActionPerformed(e);
			}
		});
		Contact.setFont(new Font("Tahoma", Font.PLAIN, 20));
		Contact.setColumns(10);
		Contact.setBounds(375, 470, 245, 34);
		frmEditProfile.getContentPane().add(Contact);
		
		City = new JTextField();
		City.addKeyListener(new KeyAdapter() {
		    public void keyTyped(KeyEvent e) { 
		        if (City.getText().length() >= 20 ) // limit City to 20 characters
		            e.consume(); 
		    }  
		});
		City.addKeyListener(new KeyAdapter() {
		    public void keyTyped(KeyEvent e) { 
		        if (City.getText().length() >= 20 ) // limit City to 20 characters
		            e.consume(); 
		    }  
		});
		City.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CityActionPerformed(e);
			}
		});
		City.setFont(new Font("Tahoma", Font.PLAIN, 20));
		City.setColumns(10);
		City.setBounds(375, 550, 245, 34);
		frmEditProfile.getContentPane().add(City);
		
		JLabel lblNewLabel_1_1_1_1 = new JLabel("City");
		lblNewLabel_1_1_1_1.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel_1_1_1_1.setBounds(110, 550, 139, 26);
		frmEditProfile.getContentPane().add(lblNewLabel_1_1_1_1);
		
		JLabel lblNewLabel_1_1_1_1_1 = new JLabel("Gender");
		lblNewLabel_1_1_1_1_1.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel_1_1_1_1_1.setBounds(110, 630, 139, 26);
		frmEditProfile.getContentPane().add(lblNewLabel_1_1_1_1_1);
		
		JLabel lblNewLabel_1_1_1_1_2 = new JLabel("Blood Group");
		lblNewLabel_1_1_1_1_2.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel_1_1_1_1_2.setBounds(110, 710, 139, 26);
		frmEditProfile.getContentPane().add(lblNewLabel_1_1_1_1_2);
		
		Gender = new JComboBox<>();
		Gender.setFont(new Font("Tahoma", Font.PLAIN, 20));
		Gender.setModel(new DefaultComboBoxModel<>(new String[] {"", "Male", "Female"}));
		
		Gender.setBounds(375, 630, 245, 34);
		frmEditProfile.getContentPane().add(Gender);
		
		Bloodg = new JComboBox<>();
		Bloodg.setFont(new Font("Tahoma", Font.PLAIN, 20));
		Bloodg.setModel(new DefaultComboBoxModel<>(new String[] {"", "A+", "A-", "AB+", "AB-", "B+", "B-", "O+", "O-"}));
		
		Bloodg.setBounds(375, 710, 245, 34);
		frmEditProfile.getContentPane().add(Bloodg);
		
		submit = new JButton("Submit");
		submit.setFocusable(false);
		submit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				submitActionPerformed(e);
			}
		});
		submit.setFont(new Font("Tahoma", Font.PLAIN, 20));
		submit.setBounds(375, 789, 245, 34);
		frmEditProfile.getContentPane().add(submit);
	}
	private void submitActionPerformed(ActionEvent e)
	{
		 AgeActionPerformed(e);
		 NameActionPerformed(e);
		 EmailActionPerformed(e);
		 ContactActionPerformed(e);
		 CityActionPerformed(e);
		 try {
				Class.forName("com.mysql.cj.jdbc.Driver");
				Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/sys","root","Saigopi@12");
				Statement st = con.createStatement();
				
				
				if(c==0)
				{
					JOptionPane.showMessageDialog(frmEditProfile, "Invalid mobile number");
				}
				
				if(c==1)
				{
					
					st.executeUpdate("update pat set age = '"+age+"',contact='"+contact+"',city='"+city+"',name = '"+name+"',email='"+email+"' where username = '"+user1+"'");
					JOptionPane.showMessageDialog(frmEditProfile,"Changed Successfully");
					pat_home ph = new pat_home();
					frmEditProfile.dispose();
					ph.setVisible(true);
				}	
				else
				{
					JOptionPane.showMessageDialog(frmEditProfile, "Changes Unsuccessful");
				}
					st.close();
					con.close();
										
			} catch (ClassNotFoundException | SQLException e1) {
				e1.printStackTrace();
			}
	}
	private void NameActionPerformed(ActionEvent e)
	{
		name = Name.getText();
	}
	private void EmailActionPerformed(ActionEvent e)
	{
		email = Email.getText();
	}
	private void AgeActionPerformed(ActionEvent e)
	{
		age = Age.getText();
	}
	
	private void ContactActionPerformed(ActionEvent e)
	{
		contact = Contact.getText();
		if (contact.length()==10)
		{
			c = 1;   // Contact number valid
		}
	}
	private void CityActionPerformed(ActionEvent e)
	{
		city = City.getText();
	}
	private void password_changeActionPerformed(ActionEvent e)
	{
		pat_change pc = new pat_change();
		frmEditProfile.dispose();
		pc.setVisible(true);
	}
	private void logoutActionPerformed(ActionEvent e)
	{
		home h = new home();
		frmEditProfile.dispose();
		h.setVisible(true);
	}
	private void backActionPerformed(ActionEvent e)
	{
		pat_home ph = new pat_home();
		frmEditProfile.dispose();
		ph.setVisible(true);
	}

	public void setVisible(boolean d) {
		frmEditProfile.setVisible(true);
	}
	
}