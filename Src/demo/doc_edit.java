package demo;

import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.awt.event.ActionEvent;
import javax.swing.DefaultComboBoxModel;
import java.awt.Toolkit;

public class doc_edit {
	
	public String user1;
	int u = 0;
	public static String gender;
	public static String field;
	public static String name;
	public static String email;
	public static String contact;
	public static String city;
	public static String hospital;

	int g = 0;
	int s = 0;
	int c = 0;


	private JComboBox<String> Gender;
	private JComboBox<String> specialization;
	private JButton submit;

	
	private JFrame frmProfile;
	private JTextField username;
	private JTextField Name;
	private JTextField Email;
	private JTextField Contact;
	private JTextField Hospital;
	private JTextField City;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					doc_edit window = new doc_edit();
					window.frmProfile.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public doc_edit() {
		initialize();
		new doc_login();
		user1 = doc_login.username1;   
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/sys","root","Saigopi@12");
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery("select * from doc where username = '"+user1+"'");
			rs.next();
				if(user1.equals(rs.getString(1)))
				{
					username.setText(rs.getString(1));
					Gender.setSelectedItem(rs.getString(4));
					specialization.setSelectedItem(rs.getString(3));
					Name.setText(rs.getString(5));
					Email.setText(rs.getString(6));
					Contact.setText(rs.getString(7));
					Hospital.setText(rs.getString(8));
					City.setText(rs.getString(9));
					
				}
			
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmProfile = new JFrame();
		frmProfile.setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\saina\\Documents\\Programsinjava\\demo_schedoc\\demo_schedoc\\Images\\stethoscope.png"));
		frmProfile.setTitle("Profile");
		frmProfile.setBounds(100, 100, 726, 847);
		frmProfile.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmProfile.getContentPane().setLayout(null);
		frmProfile.getContentPane().setBackground( Color.decode("#f5e8e7") );
		
		JLabel lblNewLabel = new JLabel("Manage Profile");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 28));
		lblNewLabel.setBounds(241, 21, 206, 65);
		frmProfile.getContentPane().add(lblNewLabel);
		
		JButton logout = new JButton("LogOut");
		logout.setBorderPainted(false);
		logout.setFocusable(false);
		logout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				home h = new home();
				frmProfile.dispose();
				h.setVisible(true);
			}
		});
		logout.setFont(new Font("Tahoma", Font.PLAIN, 20));
		logout.setBounds(538, 40, 115, 33);
		frmProfile.getContentPane().add(logout);
		
		JLabel lblNewLabel_1 = new JLabel("First Name");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel_1.setBounds(108, 150, 139, 26);
		frmProfile.getContentPane().add(lblNewLabel_1);
		
		username = new JTextField();
		username.addKeyListener(new KeyAdapter() {
		    public void keyTyped(KeyEvent e) { 
		        if (username.getText().length() >= 20||e.getKeyChar()==32 ) // limit username to 20 characters
		            e.consume(); 
		    }  
		});
		username.setText((String) null);
		username.setFont(new Font("Tahoma", Font.PLAIN, 20));
		username.setEditable(false);
		username.setColumns(10);
		username.setBounds(362, 150, 245, 30);
		frmProfile.getContentPane().add(username);
		
		JLabel lblNewLabel_1_1_1_1_1 = new JLabel("Gender");
		lblNewLabel_1_1_1_1_1.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel_1_1_1_1_1.setBounds(108, 360, 139, 26);
		frmProfile.getContentPane().add(lblNewLabel_1_1_1_1_1);
		
		Gender = new JComboBox<String>();
		Gender.setModel(new DefaultComboBoxModel<>(new String[] {"Male", "Female"}));
		Gender.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				GenderActionPerformed(e);
			}
		});
		Gender.setFont(new Font("Tahoma", Font.PLAIN, 20));
		Gender.setBounds(362, 360, 245, 30);
		frmProfile.getContentPane().add(Gender);
		
		JLabel lblNewLabel_1_1_1_1_2 = new JLabel("Specialization");
		lblNewLabel_1_1_1_1_2.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel_1_1_1_1_2.setBounds(108, 430, 139, 26);
		frmProfile.getContentPane().add(lblNewLabel_1_1_1_1_2);
		
		specialization = new JComboBox<String>();
		specialization.setModel(new DefaultComboBoxModel<>(new String[] {"Cardiologist", "Gastroenterologist", "Neurologist", "Ophthalmologist", "Otolaryngologist", "Pulmonologist"}));
		specialization.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				specializationActionPerformed(e);
			}
		});
		specialization.setFont(new Font("Tahoma", Font.PLAIN, 20));
		specialization.setEditable(true);
		specialization.setBounds(362, 430, 245, 30);
		frmProfile.getContentPane().add(specialization);
		
		submit = new JButton("Submit");
		submit.setFocusable(false);
		submit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				submitActionPerformed(e);
			}
		});
		submit.setFont(new Font("Tahoma", Font.PLAIN, 20));
		submit.setBounds(362, 710, 245, 34);
		frmProfile.getContentPane().add(submit);
		
		JButton password_change = new JButton("Change Password");
		password_change.setFocusable(false);
		password_change.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				doc_change dc = new doc_change();
				frmProfile.dispose();
				dc.setVisible(true);
			}
		});
		password_change.setFont(new Font("Tahoma", Font.PLAIN, 20));
		password_change.setBounds(55, 710, 229, 33);
		frmProfile.getContentPane().add(password_change);
		
		JButton back = new JButton("Back");
		back.setBorderPainted(false);
		back.setFocusable(false);
		back.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				doc_home dh = new doc_home();
				frmProfile.dispose();
				dh.setVisible(true);
			}
		});
		back.setFont(new Font("Tahoma", Font.PLAIN, 20));
		back.setBounds(64, 40, 97, 33);
		frmProfile.getContentPane().add(back);
		
		JLabel lblNewLabel_1_1 = new JLabel("Last Name");
		lblNewLabel_1_1.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel_1_1.setBounds(108, 220, 139, 26);
		frmProfile.getContentPane().add(lblNewLabel_1_1);
		
		JLabel lblNewLabel_1_2 = new JLabel("Email ID");
		lblNewLabel_1_2.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel_1_2.setBounds(108, 290, 139, 26);
		frmProfile.getContentPane().add(lblNewLabel_1_2);
		
		JLabel lblNewLabel_1_3 = new JLabel("Contact");
		lblNewLabel_1_3.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel_1_3.setBounds(108, 500, 139, 26);
		frmProfile.getContentPane().add(lblNewLabel_1_3);
		
		JLabel lblNewLabel_1_4 = new JLabel("Hospital");
		lblNewLabel_1_4.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel_1_4.setBounds(108, 570, 139, 26);
		frmProfile.getContentPane().add(lblNewLabel_1_4);
		
		JLabel lblNewLabel_1_4_1 = new JLabel("City");
		lblNewLabel_1_4_1.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel_1_4_1.setBounds(108, 640, 139, 26);
		frmProfile.getContentPane().add(lblNewLabel_1_4_1);
		
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
		Name.setBounds(362, 220, 245, 30);
		frmProfile.getContentPane().add(Name);
		
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
		Email.setText((String) null);
		Email.setFont(new Font("Tahoma", Font.PLAIN, 20));
		Email.setColumns(10);
		Email.setBounds(362, 290, 245, 30);
		frmProfile.getContentPane().add(Email);
		
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
		Contact.setText((String) null);
		Contact.setFont(new Font("Tahoma", Font.PLAIN, 20));
		Contact.setColumns(10);
		Contact.setBounds(362, 500, 245, 30);
		frmProfile.getContentPane().add(Contact);
		
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
		Hospital.setText((String) null);
		Hospital.setFont(new Font("Tahoma", Font.PLAIN, 20));
		Hospital.setColumns(10);
		Hospital.setBounds(362, 570, 245, 30);
		frmProfile.getContentPane().add(Hospital);
		
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
		City.setText((String) null);
		City.setFont(new Font("Tahoma", Font.PLAIN, 20));
		City.setColumns(10);
		City.setBounds(362, 640, 245, 30);
		frmProfile.getContentPane().add(City);
	}
	private void submitActionPerformed(ActionEvent e)
	{
		  
		 NameActionPerformed(e);
		 EmailActionPerformed(e);
		 GenderActionPerformed(e);
		 specializationActionPerformed(e);
		 ContactActionPerformed(e);
		 CityActionPerformed(e);
		 HospitalActionPerformed(e);
		 
		 		 try {
				Class.forName("com.mysql.cj.jdbc.Driver");
				Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/sys","root","Saigopi@12");
				Statement st = con.createStatement();
				
				if(g==0)
				{
					JOptionPane.showMessageDialog(frmProfile, "Select gender");
				}
				if(s==0)
				{
					JOptionPane.showMessageDialog(frmProfile, "Select specialization");
				}
				
				if(s==1&&g==1)
				{
					
					st.executeUpdate("update doc set field='"+field+"' ,gender='"+gender+"',name='"+name+"',email='"+email+"',contact='"+contact+"',hospital='"+hospital+"',city='"+city+"' where username = '"+user1+"'");
					JOptionPane.showMessageDialog(frmProfile,"Updated Successfully");
					doc_home dh = new doc_home();
					frmProfile.dispose();
					dh.setVisible(true);
				}	
				else
				{
					JOptionPane.showMessageDialog(frmProfile, "Update Unsuccessful");
				}
					st.close();
					con.close();
										
			} catch (ClassNotFoundException | SQLException e1) {
				e1.printStackTrace();
			}
	}
	private void GenderActionPerformed(ActionEvent e)
	{
		gender = (String)Gender.getSelectedItem();
		if(gender.length()!=0)
		{
			g = 1;  // Select gender
		} 
		else
		{
			g = 0;
		}
	}
	private void specializationActionPerformed(ActionEvent e)
	{
		field	= (String)specialization.getSelectedItem();
		if(field.length()!=0)
		{
			s = 1;  // Select gender
		} 
		else
		{
			s = 0;
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
		frmProfile.setVisible(true);
	}

}