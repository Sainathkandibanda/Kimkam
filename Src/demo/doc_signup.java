package demo;

import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.awt.event.ActionEvent;
import java.awt.Toolkit;

public class doc_signup {
	
	public static String username1;
	public static String password1;
	public static String password2;
	public static String gender;
	public static String specialization;
	public static String name;
	public static String email;
	public static String contact;
	public static String city;
	public static String hospital;
	
	int u1 = 1;
	int u2 = 0;
	int p = 0;
	int g = 0;
	int s = 0;
	int c = 0;
	
	
	

	private JFrame frmDoctorSignUp;
	private JTextField username;
	private JPasswordField pass1;
	private JTextField pass2;
	private JComboBox<String> Gender;
	private JComboBox<String> Specialization;
	private JButton submit;
	private JTextField Name;
	private JTextField Email;
	private JLabel lblContact;
	private JTextField Contact;
	private JTextField Hospital;
	private JTextField City;
	private JLabel labelName_2;
	private JLabel labelName_3;
	private JLabel lblNewLabel_2;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					doc_signup window = new doc_signup();
					window.frmDoctorSignUp.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public doc_signup() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmDoctorSignUp = new JFrame();
		frmDoctorSignUp.setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\saina\\Documents\\Programsinjava\\demo_schedoc\\demo_schedoc\\Images\\stethoscope.png"));
		frmDoctorSignUp.setTitle("Doctor Sign Up");
		frmDoctorSignUp.setBounds(100, 100, 655, 947);
		frmDoctorSignUp.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmDoctorSignUp.getContentPane().setLayout(null);
		frmDoctorSignUp.getContentPane().setBackground( Color.decode("#e6f5f3") );
		
		JLabel lblNewLabel = new JLabel("Doctor SignUp");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 30));
		lblNewLabel.setBounds(191, 49, 207, 42);
		frmDoctorSignUp.getContentPane().add(lblNewLabel);
		
		JButton back = new JButton("Back");
		back.setBackground(Color.decode("#f5f3e6"));
		back.setFocusable(false);
		back.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dialog_signup ds = new dialog_signup();
				frmDoctorSignUp.dispose();
				ds.setVisible(true);
			}
		});
		back.setFont(new Font("Tahoma", Font.PLAIN, 20));
		back.setBounds(30, 49, 97, 33);
		frmDoctorSignUp.getContentPane().add(back);
		
		JLabel lblNewLabel_1 = new JLabel("First Name :");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel_1.setBounds(87, 119, 162, 25);
		frmDoctorSignUp.getContentPane().add(lblNewLabel_1);
		
		username = new JTextField();
		username.addKeyListener(new KeyAdapter() {
		    public void keyTyped(KeyEvent e) { 
		        if (username.getText().length() >= 20||e.getKeyChar()==32 ) // limit username to 20 characters
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
		username.setBounds(321, 116, 217, 30);
		frmDoctorSignUp.getContentPane().add(username);
		
		JLabel lblNewLabel_1_1 = new JLabel("Password :");
		lblNewLabel_1_1.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel_1_1.setBounds(87, 177, 162, 25);
		frmDoctorSignUp.getContentPane().add(lblNewLabel_1_1);
		
		pass1 = new JPasswordField();
		pass1.addKeyListener(new KeyAdapter() {
		    @SuppressWarnings("deprecation")
			public void keyTyped(KeyEvent e) { 
		        if (pass1.getText().length() >= 20||e.getKeyChar()==32 ) // limit pass1 to 20 characters
		            e.consume(); 
		    }  
		});
		pass1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				pass1ActionPerformed(e);
			}
		});
		pass1.setFont(new Font("Tahoma", Font.PLAIN, 20));
		pass1.setBounds(321, 174, 217, 30);
		frmDoctorSignUp.getContentPane().add(pass1);
		
		JLabel lblNewLabel_1_2 = new JLabel("Confirm Password :");
		lblNewLabel_1_2.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel_1_2.setBounds(87, 224, 195, 25);
		frmDoctorSignUp.getContentPane().add(lblNewLabel_1_2);
		
		pass2 = new JTextField();
		pass2.addKeyListener(new KeyAdapter() {
		    public void keyTyped(KeyEvent e) { 
		        if (pass2.getText().length() >= 20 ||e.getKeyChar()==32) // limit pass2 to 20 characters
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
		pass2.setBounds(321, 221, 217, 30);
		frmDoctorSignUp.getContentPane().add(pass2);
		
		JLabel lblNewLabel_1_4 = new JLabel("Gender :");
		lblNewLabel_1_4.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel_1_4.setBounds(87, 391, 162, 25);
		frmDoctorSignUp.getContentPane().add(lblNewLabel_1_4);
		
		Gender = new JComboBox<String>();
		Gender.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				GenderActionPerformed(e);
			}
		});
		Gender.setModel(new DefaultComboBoxModel<>(new String[] {"", "Male", "Female"}));
		Gender.setFont(new Font("Tahoma", Font.PLAIN, 20));
		Gender.setBounds(321, 388, 217, 30);
		frmDoctorSignUp.getContentPane().add(Gender);
		
		JLabel lblNewLabel_1_3 = new JLabel("Specialization :");
		lblNewLabel_1_3.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel_1_3.setBounds(87, 450, 162, 25);
		frmDoctorSignUp.getContentPane().add(lblNewLabel_1_3);
		
		Specialization = new JComboBox<String>();
		Specialization.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SpecializationActionPerformed(e);
			}
		});
		Specialization.setModel(new DefaultComboBoxModel<>(new String[] {"Cardiologist", "Gastroenterologist", "Neurologist", "Ophthalmologist", "Otolaryngologist", "Pulmonologist"}));
		Specialization.setFont(new Font("Tahoma", Font.PLAIN, 20));
		Specialization.setBounds(321, 447, 217, 30);
		frmDoctorSignUp.getContentPane().add(Specialization);
		
		submit = new JButton("Submit");
		submit.setFont(new Font("Tahoma", Font.PLAIN, 20));
		submit.setBackground(Color.decode("#f5f3e6"));
		submit.setFocusable(false);
		submit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				submitActionPerformed(e);
			}
		});
		submit.setBounds(321, 689, 217, 47);
		frmDoctorSignUp.getContentPane().add(submit);
		
		JLabel labelName = new JLabel("Last Name :");
		labelName.setFont(new Font("Tahoma", Font.PLAIN, 20));
		labelName.setBounds(87, 280, 162, 25);
		frmDoctorSignUp.getContentPane().add(labelName);
		
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
		Name.setBounds(321, 277, 217, 30);
		frmDoctorSignUp.getContentPane().add(Name);
		
		Email = new JTextField();
		Email.addKeyListener(new KeyAdapter() {
		    public void keyTyped(KeyEvent e) { 
		        if (Email.getText().length() >= 30 ) // limit Email to 30 characters
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
		Email.setBounds(321, 331, 217, 30);
		frmDoctorSignUp.getContentPane().add(Email);
		
		JLabel lblNewLabel_1_5_1 = new JLabel("Email ID :");
		lblNewLabel_1_5_1.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel_1_5_1.setBounds(87, 334, 162, 25);
		frmDoctorSignUp.getContentPane().add(lblNewLabel_1_5_1);
		
		lblContact = new JLabel("Contact :");
		lblContact.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblContact.setBounds(87, 504, 162, 25);
		frmDoctorSignUp.getContentPane().add(lblContact);
		
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
		Contact.setBounds(321, 501, 217, 30);
		frmDoctorSignUp.getContentPane().add(Contact);
		
		Hospital = new JTextField();
		Hospital.addKeyListener(new KeyAdapter() {
		    public void keyTyped(KeyEvent e) { 
		        if (Hospital.getText().length() >= 20 ) // limit Hospital to 20 characters
		            e.consume(); 
		    }  
		});
		Hospital.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				HospitalActionPerformed(e);
			}
		});
		Hospital.setFont(new Font("Tahoma", Font.PLAIN, 20));
		Hospital.setColumns(10);
		Hospital.setBounds(321, 558, 217, 30);
		frmDoctorSignUp.getContentPane().add(Hospital);
		
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
		City.setBounds(321, 614, 217, 30);
		frmDoctorSignUp.getContentPane().add(City);
		
		labelName_2 = new JLabel("Hospital Name :");
		labelName_2.setFont(new Font("Tahoma", Font.PLAIN, 20));
		labelName_2.setBounds(87, 561, 162, 25);
		frmDoctorSignUp.getContentPane().add(labelName_2);
		
		labelName_3 = new JLabel("City :");
		labelName_3.setFont(new Font("Tahoma", Font.PLAIN, 20));
		labelName_3.setBounds(87, 617, 162, 25);
		frmDoctorSignUp.getContentPane().add(labelName_3);
		
		lblNewLabel_2 = new JLabel("( This will be your Username )");
		lblNewLabel_2.setBounds(64, 140, 185, 14);
		frmDoctorSignUp.getContentPane().add(lblNewLabel_2);
	}
	private void submitActionPerformed(ActionEvent e)
	 {
		 usernameActionPerformed(e);
		 pass1ActionPerformed(e);
		 pass2ActionPerformed(e);
		 NameActionPerformed(e);
		 EmailActionPerformed(e);
		 GenderActionPerformed(e);
		 SpecializationActionPerformed(e);
		 ContactActionPerformed(e);
		 CityActionPerformed(e);
		 HospitalActionPerformed(e);
		
		 
		 try {
				Class.forName("com.mysql.cj.jdbc.Driver");
				Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/sys","root","Saigopi@12");
				PreparedStatement st = con.prepareStatement("insert into doc(username,password,field,gender,name,email,contact,hospital,city) values(?,?,?,?,?,?,?,?,?)");
				
				if(u1==0)
				{
					JOptionPane.showMessageDialog(frmDoctorSignUp, "Username already exists");
				} 
				if(u2==2)
				{
					JOptionPane.showMessageDialog(frmDoctorSignUp, "Enter Valid Username");
				}
				if(p==0)
				{
					JOptionPane.showMessageDialog(frmDoctorSignUp, "Entered password do not match");
				}
				if(g==0)
				{
					JOptionPane.showMessageDialog(frmDoctorSignUp, "Select gender");
				}
				if(s==0)
				{
					JOptionPane.showMessageDialog(frmDoctorSignUp, "Select Specialization");
				}
				if(c==0)
				{
					JOptionPane.showMessageDialog(frmDoctorSignUp, "Invalid mobile number");
				}
				
				if(u1==1&&p==1&&g==1&&s==1&&c==1)
				{
					st.setString(1, username1);
					st.setString(2, password1);
					st.setString(3, specialization);
					st.setString(4, gender);
					st.setString(5, name);
					st.setString(6, email);
					st.setString(7, contact);
					st.setString(8, hospital);
					st.setString(9, city);
					
					st.executeUpdate();
					//JOptionPane.showMessageDialog(frame, username1+" Registered Successfully");
					doc_login docl = new doc_login();
					frmDoctorSignUp.dispose();
					docl.setVisible(true);
					
				}	
				else
				{
					JOptionPane.showMessageDialog(frmDoctorSignUp, username1+", Registration Unsuccessful");
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
				ResultSet rs = st.executeQuery("select * from doc");
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
	
	 private void GenderActionPerformed(ActionEvent e)
	 {
		 gender = (String)Gender.getSelectedItem();
			if(gender.length()!=0)
			{
				g = 1;  // Select gender
			} 
	 }
	 private void SpecializationActionPerformed(ActionEvent e)
	 {
		 specialization = (String)Specialization.getSelectedItem();
			if(specialization.length()!=0)
			{
				s = 1;  // Select specialization
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
	 private void HospitalActionPerformed(ActionEvent e)
	 {
		 hospital = Hospital.getText();
	 }

	public void setVisible(boolean b) {
		frmDoctorSignUp.setVisible(b);
		
	}
}