package demo;

import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.*;
import java.awt.event.ActionEvent;
import javax.swing.JButton;
import java.awt.Toolkit;


public class pat_signup {
	
	
	public static String username1;
	public static String password1;
	public static String password2;
	public static String name;
	public static String email;
	public static String age;
	public static String gender;
	public static String bloodg1;
	public static String contact;
	public static String city;
	int u1 = 1;
	int u2 = 0;
	int p = 0;
	int c = 0;
	int g = 0;
	int b = 0;
	
	
	
	

	private JFrame frmPatientSignup;
	private JTextField username;
	private JPasswordField pass1;
	private JTextField Age;
	private JTextField Contact;
	private JTextField City;
	private JComboBox<String> Gender;
	private JComboBox<String> Bloodg;
	private JTextField pass2;
	private JButton back;
	private JTextField Name;
	private JTextField Email;
	private JLabel lblNewLabel_2;


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					pat_signup window = new pat_signup();
					window.frmPatientSignup.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public pat_signup() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	
	private void initialize() {
		frmPatientSignup = new JFrame();
		frmPatientSignup.setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\saina\\Documents\\Programsinjava\\demo_schedoc\\demo_schedoc\\Images\\stethoscope.png"));
		frmPatientSignup.setTitle("Patient Sign Up");
		frmPatientSignup.setBounds(100, 100, 757, 779);
		frmPatientSignup.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmPatientSignup.getContentPane().setLayout(null);
		frmPatientSignup.getContentPane().setBackground( Color.decode("#e6f5f3") );
		
		JLabel lblNewLabel = new JLabel("Patient Sign Up");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 28));
		lblNewLabel.setBounds(237, 42, 217, 60);
		frmPatientSignup.getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("First Name :");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel_1.setBounds(126, 141, 162, 25);
		frmPatientSignup.getContentPane().add(lblNewLabel_1);
		
		JLabel lblNewLabel_1_1 = new JLabel("Password :");
		lblNewLabel_1_1.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel_1_1.setBounds(126, 187, 162, 25);
		frmPatientSignup.getContentPane().add(lblNewLabel_1_1);
		
		JLabel lblNewLabel_1_2 = new JLabel("Confirm Password :");
		lblNewLabel_1_2.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel_1_2.setBounds(126, 240, 204, 25);
		frmPatientSignup.getContentPane().add(lblNewLabel_1_2);
		
		JLabel lblNewLabel_1_3 = new JLabel("Age :");
		lblNewLabel_1_3.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel_1_3.setBounds(126, 398, 162, 25);
		frmPatientSignup.getContentPane().add(lblNewLabel_1_3);
		
		JLabel lblNewLabel_1_4 = new JLabel("Gender :");
		lblNewLabel_1_4.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel_1_4.setBounds(126, 456, 162, 25);
		frmPatientSignup.getContentPane().add(lblNewLabel_1_4);
		
		JLabel lblNewLabel_1_5 = new JLabel("Blood Group :");
		lblNewLabel_1_5.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel_1_5.setBounds(126, 507, 162, 25);
		frmPatientSignup.getContentPane().add(lblNewLabel_1_5);
		
		JLabel lblNewLabel_1_6 = new JLabel("Contact :");
		lblNewLabel_1_6.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel_1_6.setBounds(126, 561, 162, 25);
		frmPatientSignup.getContentPane().add(lblNewLabel_1_6);
		
		JLabel lblNewLabel_1_7 = new JLabel("City :");
		lblNewLabel_1_7.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel_1_7.setBounds(126, 616, 162, 25);
		frmPatientSignup.getContentPane().add(lblNewLabel_1_7);
		
		JButton submit = new JButton("Submit");
		submit.setBackground(Color.decode("#f5f3e6"));
		submit.setFocusable(false);
		submit.setFont(new Font("Tahoma", Font.PLAIN, 20));
		submit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				submitActionPerformed(e);			
			}
		});
		submit.setBounds(370, 695, 217, 47);
		frmPatientSignup.getContentPane().add(submit);
		
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
		username.setBounds(370, 141, 217, 25);
		frmPatientSignup.getContentPane().add(username);
		username.setColumns(10);
		
		pass1 = new JPasswordField();
		pass1.addKeyListener(new KeyAdapter() {
		    @SuppressWarnings("deprecation")
			public void keyTyped(KeyEvent e) { 
		        if (pass1.getText().length() >= 20||e.getKeyChar()==32 ) // limit pass_1 to 20 characters
		            e.consume(); 
		    }  
		});
		pass1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				pass1ActionPerformed(e);
			}
		});
		pass1.setFont(new Font("Tahoma", Font.PLAIN, 20));
		pass1.setBounds(370, 187, 217, 25);
		frmPatientSignup.getContentPane().add(pass1);
		
		pass2 = new JTextField();
		pass2.addKeyListener(new KeyAdapter() {
		    public void keyTyped(KeyEvent e) { 
		        if (pass2.getText().length() >= 20||e.getKeyChar()==32 ) // limit pass_2 to 20 characters
		            e.consume(); 
		    }  
		});
		pass2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				pass2ActionPerformed(e);
			}
		});
		pass2.setFont(new Font("Tahoma", Font.PLAIN, 20));
		pass2.setColumns(10);
		pass2.setBounds(370, 238, 217, 25);
		frmPatientSignup.getContentPane().add(pass2);
		
		Age = new JTextField();
		Age.addKeyListener(new KeyAdapter() {
		    public void keyTyped(KeyEvent e) { 
		        if (Age.getText().length() >= 3 || e.getKeyChar()<=47 || e.getKeyChar()>=58||e.getKeyChar()==32) // limit Age to 2 integers
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
		Age.setBounds(370, 398, 217, 25);
		frmPatientSignup.getContentPane().add(Age);
		
		Gender = new JComboBox<>();
		Gender.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				GenderActionPerformed(e);
			}
		});
		Gender.setFont(new Font("Tahoma", Font.PLAIN, 20));
		Gender.setModel(new DefaultComboBoxModel<>(new String[] {"", "Male", "Female", "Other"}));
		Gender.setBounds(370, 456, 217, 25); 
		frmPatientSignup.getContentPane().add(Gender);
		
		Bloodg = new JComboBox<>();
		Bloodg.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				BloodgActionPerformed(e);
			}
		});
		Bloodg.setModel(new DefaultComboBoxModel<>(new String[] {"", "A+", "A-", "AB+", "AB-", "B+", "B-", "O+", "O-"}));
		Bloodg.setFont(new Font("Tahoma", Font.PLAIN, 20));
		Bloodg.setBounds(370, 507, 217, 25);
		frmPatientSignup.getContentPane().add(Bloodg);
		
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
		Contact.setBounds(370, 561, 217, 25);
		frmPatientSignup.getContentPane().add(Contact);
		
		City = new JTextField();
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
		City.setBounds(370, 616, 217, 25);
		frmPatientSignup.getContentPane().add(City);
		
		back = new JButton("Back");
		back.setBackground(Color.decode("#f5f3e6"));
		back.setFocusable(false);
		back.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				backActionPerformed(e);
			}
		});
		back.setFont(new Font("Tahoma", Font.PLAIN, 20));
		back.setBounds(46, 59, 97, 33);
		frmPatientSignup.getContentPane().add(back);
		
		JLabel lblNewLabel_1_8 = new JLabel("Last Name :");
		lblNewLabel_1_8.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel_1_8.setBounds(126, 294, 162, 25);
		frmPatientSignup.getContentPane().add(lblNewLabel_1_8);
		
		JLabel lblNewLabel_1_9 = new JLabel("Email ID :");
		lblNewLabel_1_9.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel_1_9.setBounds(126, 345, 162, 25);
		frmPatientSignup.getContentPane().add(lblNewLabel_1_9);
		
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
		Name.setFont(new Font("Tahoma", Font.PLAIN, 20));
		Name.setColumns(10);
		Name.setBounds(370, 294, 217, 25);
		frmPatientSignup.getContentPane().add(Name);
		
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
		Email.setFont(new Font("Tahoma", Font.PLAIN, 20));
		Email.setColumns(10);
		Email.setBounds(370, 345, 217, 25);
		frmPatientSignup.getContentPane().add(Email);
		
		lblNewLabel_2 = new JLabel("( This will be your Username )");
		lblNewLabel_2.setBounds(108, 161, 173, 16);
		frmPatientSignup.getContentPane().add(lblNewLabel_2);
		
		
	}
	 private void submitActionPerformed(ActionEvent e)
	 {
		 usernameActionPerformed(e);
		 pass1ActionPerformed(e);
		 pass2ActionPerformed(e);
		 NameActionPerformed(e);
		 EmailActionPerformed(e);
		 AgeActionPerformed(e);
		 BloodgActionPerformed(e);
		 GenderActionPerformed(e);
		 ContactActionPerformed(e);
		 CityActionPerformed(e);
		 
		 try {
				Class.forName("com.mysql.cj.jdbc.Driver");
				Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/sys","root","Saigopi@12");
				PreparedStatement st = con.prepareStatement("insert into pat(username,password,age,gender,bloodg,contact,city,name,email) values(?,?,?,?,?,?,?,?,?)");
				
				if(u1==0)
				{
					JOptionPane.showMessageDialog(frmPatientSignup, "Username already exists");
				} 
				if(u2==2)
				{
					JOptionPane.showMessageDialog(frmPatientSignup, "Enter Valid Username");
				}
				if(p==0)
				{
					JOptionPane.showMessageDialog(frmPatientSignup, "Entered password do not match");
				}
				if(g==0)
				{
					JOptionPane.showMessageDialog(frmPatientSignup, "Select gender");
				}
				if(b==0)
				{
					JOptionPane.showMessageDialog(frmPatientSignup, "Select blood group");
				}
				if(c==0)
				{
					JOptionPane.showMessageDialog(frmPatientSignup, "Invalid mobile number");
				}
				
				if(u1==1&&c==1&&p==1&&g==1&&b==1)
				{
					st.setString(1, username1);
					st.setString(2, password1);
					st.setString(3, age);
					st.setString(4, gender);
					st.setString(5, bloodg1);
					st.setString(6, contact);
					st.setString(7, city);
					st.setString(8, name);
					st.setString(9, email);
					st.executeUpdate();
					//JOptionPane.showMessageDialog(frame, username1+" Registered Successfully");
					pat_login pl = new pat_login();
					frmPatientSignup.dispose();
					pl.setVisible(true);
					
				}	
				else
				{
					JOptionPane.showMessageDialog(frmPatientSignup, username1+", Registration Unsuccessful");
				}
					st.close();
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
						u1 = 0;  // User_name Exists
						break;
					}
					else
					{
						u1 = 1;
					}
				}
				int k = username1.length();
				int l = 0;
				for(int i=0; i<k; i++)
				{
					if(username1.charAt(i)>=48&&username1.charAt(i)<=57)
					{
						l++;
					}

				}
				if (l==k)
				{
					u2 = 0;  // Enter a valid User_name	
				}
				else
				{
					u2 = 1;  // No errors
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
			if (password1.equals(password2))
			{
				p = 1;  // Passwords matches
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
	 private void GenderActionPerformed(ActionEvent e)
	 {
		 gender = (String)Gender.getSelectedItem();
			if(gender.length()!=0)
			{
				g = 1;  // Select gender
			} 
	 }
	 private void BloodgActionPerformed(ActionEvent e)
	 {
		 bloodg1 = (String)Bloodg.getSelectedItem();
			if(bloodg1.length()!=0)
			{
				b = 1;  // Select gender
			} 
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
	 private void backActionPerformed(ActionEvent e)
	 {
		dialog_signup ds = new dialog_signup();
		frmPatientSignup.dispose();
		ds.setVisible(true);
	 }

	public void setVisible(boolean d) {
		frmPatientSignup.setVisible(true);
	}
}
 
