package demo;

import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.ButtonGroup;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.GridLayout;
import java.sql.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import javax.swing.JToggleButton;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.ActionEvent;
import javax.swing.JButton;
import javax.swing.UIManager;
import javax.swing.plaf.ColorUIResource;
import javax.swing.JTextArea;
import java.awt.Toolkit;
import java.util.Date;

public class confirm_book {
	
	public static String doc_name;       // to store doctor name
	public static String pat_name;       // to store patient name
	public static String slot;   		 // to store time in data base
	public static java.util.Date slot1;  // to perform the calculations for enabling buttons
	public static java.util.Date start;  // to store start time of doctor
	public static java.util.Date end;    // to store end time of doctor
	public static java.util.Date now;    // to store present time
	public static int num = 0;           // to store slot number
	public static String date;           // to store the date from new_booking.java in data base
	public static String full_date;
	public static String date2;
	public static String details;        // to store the description in database 
	public static String today;
	
	int present = 0;  					//check whether username exists in doc and doc_avail
	int start_time = 0;
	int end_time = 0;
	int select = 0;   // check for slot selection
	int type = 1;
	int slot_num = 0;
	int full = 0;
	ArrayList<Integer> al = new ArrayList<>();
	
	
	
	private DateFormat dateFormat;
	private DateFormat dateFormat1;
	private JFrame frmChooseSlot;
	private JLabel username;
	private JLabel Specialization;
	private JLabel Gender;
	private JLabel time;
	private JButton submit;
	private ButtonGroup buttonGroup;
	public static JToggleButton buttonArr[];
	private JTextArea desc;
	private JLabel Contact;
	private JLabel Hospital;
	private JLabel City;
	private JLabel lblAfternoon_1;
	private JButton back;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		UIManager.put("ToggleButton.select", new ColorUIResource( Color.GREEN ));
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					confirm_book window = new confirm_book();
					window.frmChooseSlot.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	@SuppressWarnings("deprecation")
	public confirm_book() {
		initialize();
		new pat_login();
		pat_name = pat_login.username1;
		new new_booking();
		date = new_booking.date1;
		date2 = new_booking.date2;
		doc_name = new_booking.doc_selected;
		
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/sys","root","Saigopi@12");
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery("select doc.*,doc_avail.* from doc,doc_avail where doc_avail.doc_id = '"+doc_name+"'");
			while(rs.next())
			{
				if(rs.getString(1).equals(doc_name))
				{
					present = 1;
					break;
				}
			}
			if(present == 1)
			{
//				System.out.println("true1");
				username.setText("Doctor   : "+"Dr."+rs.getString("username")+" "+rs.getString("name"));
				Specialization.setText("Role     : "+rs.getString("field"));
				Gender.setText("Gender   : "+rs.getString("gender"));
				Contact.setText("Contact  : "+rs.getString("contact"));
				City.setText("City     : "+rs.getString("city"));
				Hospital.setText("Hospital : "+rs.getString("hospital"));
				start_time = Integer.valueOf(rs.getString("start"));
				end_time = Integer.valueOf(rs.getString("end"));				
				time.setText("Timings  : "+rs.getString("start")+":00 to "+rs.getString("end")+":00");
			}
		
			ResultSet rs1 = st.executeQuery("select * from appointment where doc_fk = '"+doc_name+"' and date = '"+date+"'");
			while(rs1.next())
			{
//				System.out.println("true2");
//				System.out.println(rs1.getString(2));
				if(rs1.getString(2).equals(doc_name))
				{
					al.add(rs1.getInt("slotnum"));
				}
			}
			

			st.close();
			con.close();
			
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		
		try {
			start = dateFormat1.parse(String.valueOf(start_time));
		} catch (ParseException e1) {
			e1.printStackTrace();
		}
		
		try {
			end = dateFormat1.parse(String.valueOf(end_time));
			end.setMinutes(end.getMinutes() - 30);
		} catch (ParseException e1) {
			e1.printStackTrace();
		}
		
		for(int i = 0; i<25; i++) 
		{ 
			buttonArr[i].setEnabled(false);
		}

		 
		now = new Date();                    // stores today time in date object
		today = dateFormat.format(now);		// parse the date object into String in the format HH:mm	
		try {
			now = dateFormat.parse(today);   // again converting String object into Date object to compare with the slot1 object
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		for(int i = 0; i<25; i++)
		{
			try {
				slot1 = dateFormat.parse(buttonArr[i].getText());
			} catch (ParseException e1) {
				e1.printStackTrace();
			}
		
			boolean a = slot1.after(start);   // desired is true
			boolean b = slot1.equals(start);  // desired is true
			boolean c = slot1.equals(end);    // desired is true
			boolean d = slot1.before(end);    // desired is true
			
			if((a||b)&&(c||d))
			{
				buttonArr[i].setEnabled(true);
			}
			
		
			boolean e = slot1.before(now);
			if(e)
			{
				buttonArr[i].setEnabled(false);
			}
	
		}
		
		for(int i = 0; i<al.size(); i++)
		{
			buttonArr[al.get(i)].setEnabled(false);
			buttonArr[al.get(i)].setBackground(Color.red);
		}
	
//		slot_num = 5;
//		if(slot_num == 5)
//		{
//			buttonArr[slot_num].setEnabled(false);
//			buttonArr[slot_num].setBackground(Color.red);
//		}
//		/*
//		 * slot_num = rs.getInt(index); buttonArr[slot_num].setEnabled(false);   code in while(rs.next())
//		 * buttonArr[slot_num].setBackground(Color.red);
//		 */
		/* System.out.print(buttonArr[24].getText()); */
			
	}
	

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmChooseSlot = new JFrame();
		frmChooseSlot.setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\saina\\Documents\\Programsinjava\\demo_schedoc\\demo_schedoc\\Images\\stethoscope.png"));
		frmChooseSlot.setTitle("Choose Slot\r\n");
		frmChooseSlot.setBounds(100, 100, 1248, 816);
		frmChooseSlot.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmChooseSlot.getContentPane().setLayout(null);
		frmChooseSlot.getContentPane().setBackground( Color.decode("#e9f5dc") );
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.decode("#e9f5dc"));
		panel.setBounds(10, 11, 1218, 250);
		frmChooseSlot.getContentPane().add(panel);
		panel.setLayout(null);
		
		username = new JLabel("");
		username.setFont(new Font("Tahoma", Font.PLAIN, 20));
		username.setBounds(55, 25, 271, 31);
		panel.add(username);
		
		Specialization = new JLabel("");
		Specialization.setFont(new Font("Tahoma", Font.PLAIN, 20));
		Specialization.setBounds(55, 75, 295, 31);
		panel.add(Specialization);
		
		Gender = new JLabel("");
		Gender.setFont(new Font("Tahoma", Font.PLAIN, 20));
		Gender.setBounds(55, 125, 295, 31);
		panel.add(Gender);
		
		time = new JLabel("");
		time.setFont(new Font("Tahoma", Font.PLAIN, 20));
		time.setBounds(55, 175, 295, 31);
		panel.add(time);
		
		submit = new JButton("Submit");
		submit.setFocusable(false);
		submit.setBounds(981, 189, 165, 31);
		panel.add(submit);
		submit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				submitActionPerformed(e);
			}
		});
		submit.setFont(new Font("Tahoma", Font.PLAIN, 21));
		
		JLabel lblNewLabel_1 = new JLabel("Reason :");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel_1.setBounds(743, 17, 183, 30);
		panel.add(lblNewLabel_1);
		
		desc = new JTextArea();
		desc.addKeyListener(new KeyAdapter() {
		    public void keyTyped(KeyEvent e) { 
		        if (desc.getText().length() >= 200 ) // limit Name to 200 characters
		            e.consume(); 
		    }  
		});
		desc.setBackground(Color.decode("#f5fbf0"));
		desc.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				if (desc.getText().length() >= 200 ) // limit user_name to 200 characters
		            e.consume();
			}
		});
		
		desc.setWrapStyleWord(true);
		desc.setFont(new Font("Monospaced", Font.PLAIN, 17));
		desc.setLineWrap(true);
		desc.setBounds(743, 52, 403, 127);
		panel.add(desc);
		
		Contact = new JLabel("");
		Contact.setFont(new Font("Tahoma", Font.PLAIN, 20));
		Contact.setBounds(352, 25, 358, 31);
		panel.add(Contact);
		
		Hospital = new JLabel("");
		Hospital.setFont(new Font("Tahoma", Font.PLAIN, 20));
		Hospital.setBounds(352, 75, 358, 31);
		panel.add(Hospital);
		
		City = new JLabel("");
		City.setFont(new Font("Tahoma", Font.PLAIN, 20));
		City.setBounds(352, 125, 358, 31);
		panel.add(City);
		
		back = new JButton("Back");
		back.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new_booking nw = new new_booking();
				frmChooseSlot.dispose();
				nw.setVisible(true);
			}
		});
		back.setFont(new Font("Tahoma", Font.PLAIN, 21));
		back.setFocusable(false);
		back.setBounds(743, 189, 165, 31);
		panel.add(back);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(Color.decode("#e9f5dc"));
		panel_1.setBounds(244, 267, 978, 499);
		frmChooseSlot.getContentPane().add(panel_1);
		panel_1.setLayout(null);
		GridLayout gl =new GridLayout(5,3,5,20);
        panel_1.setLayout(gl);
        
        JLabel lblMorning = new JLabel("Morning   :");
        lblMorning.setFont(new Font("Tahoma", Font.PLAIN, 20));
        lblMorning.setBounds(78, 300, 111, 31);
        frmChooseSlot.getContentPane().add(lblMorning);
        
        JLabel lblAfternoon = new JLabel("Midday   :");
        lblAfternoon.setFont(new Font("Tahoma", Font.PLAIN, 20));
        lblAfternoon.setBounds(76, 400, 99, 31);
        frmChooseSlot.getContentPane().add(lblAfternoon);
        
        JLabel lblEvening = new JLabel("Evening   :");
        lblEvening.setFont(new Font("Tahoma", Font.PLAIN, 20));
        lblEvening.setBounds(78, 600, 99, 31);
        frmChooseSlot.getContentPane().add(lblEvening);
        
        JLabel lblNight = new JLabel("Night   :");
        lblNight.setFont(new Font("Tahoma", Font.PLAIN, 20));
        lblNight.setBounds(78, 701, 83, 31);
        frmChooseSlot.getContentPane().add(lblNight);
        
        lblAfternoon_1 = new JLabel("Afternoon   :");
        lblAfternoon_1.setFont(new Font("Tahoma", Font.PLAIN, 20));
        lblAfternoon_1.setBounds(78, 500, 125, 31);
        frmChooseSlot.getContentPane().add(lblAfternoon_1);
		
		
		dateFormat = new SimpleDateFormat("HH:mm");   // HH will look for 0-23
		dateFormat1 = new SimpleDateFormat("HH");	  // hh will look for 1-12
		//newFormat = new SimpleDateFormat("hh:mm");
		//String formatedDate = newFormat.format(date);
		
		buttonArr = new JToggleButton[25];
		buttonArr[0] = new JToggleButton("9:00");
		buttonArr[1] = new JToggleButton("9:30");
		buttonArr[2] = new JToggleButton("10:00");
		buttonArr[3] = new JToggleButton("10:30");
		buttonArr[4] = new JToggleButton("11:00");
		buttonArr[5] = new JToggleButton("11:30");
		buttonArr[6] = new JToggleButton("12:00");
		buttonArr[7] = new JToggleButton("12:30");
		buttonArr[8] = new JToggleButton("13:00");
		buttonArr[9] = new JToggleButton("13:30");
		buttonArr[10] = new JToggleButton("14:00");
		buttonArr[11] = new JToggleButton("14:30");
		buttonArr[12] = new JToggleButton("15:00");
		buttonArr[13] = new JToggleButton("15:30");
		buttonArr[14] = new JToggleButton("16:00");
		buttonArr[15] = new JToggleButton("16:30");
		buttonArr[16] = new JToggleButton("17:00");
		buttonArr[17] = new JToggleButton("17:30");
		buttonArr[18] = new JToggleButton("18:00");
		buttonArr[19] = new JToggleButton("18:30");
		buttonArr[20] = new JToggleButton("19:00");
		buttonArr[21] = new JToggleButton("19:30");
		buttonArr[22] = new JToggleButton("20:00");
		buttonArr[23] = new JToggleButton("20:30");
		buttonArr[24] = new JToggleButton("21:00");
		
		for(int i = 0; i < 25; i++) {
            panel_1.add(buttonArr[i]);
		}
		
		buttonGroup = new ButtonGroup();
		for (int i=0; i<25; i++)
		{
			buttonGroup.add(buttonArr[i]);
		}
		
			
	}
	private void submitActionPerformed(ActionEvent e)
	{
		/* System.out.println(slot); */
		for(int i = 0; i<25; i++)
		{
			if(buttonArr[i].isSelected())
			{			
				num = i;
				slot = (buttonArr[i].getText());
				select = 1;
			}
		}
		details = desc.getText();
		
		/*
		 * System.out.println(num); System.out.println(start);
		 * end.setMinutes(end.getMinutes() - 30); to perform arithmetic operations
		 * System.out.println(end);
		 */
//		System.out.println(slot);
//		System.out.println(num);
//		System.out.println(date);
//		System.out.println(pat_name);
//		System.out.println(details);
		full_date = date2+" "+slot+":00";
//		System.out.println(full_date);
		
		
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/sys","root","Saigopi@12");
			Statement st1 = con.createStatement();
			ResultSet rs = st1.executeQuery("select doc_fk,pat_fk,time,date from appointment");
			while(rs.next())
			{
//				System.out.println("true");
				if(doc_name.equals(rs.getString(1))&&pat_name.equals(rs.getString(2))&&date.equals(rs.getString(4)))
				{
					full = 1;
					break;
				}
			}
			if(full==1)
			{
				JOptionPane.showMessageDialog(frmChooseSlot, pat_name+", you already booked appointment with "+doc_name+" at "+rs.getString(3)+" on "+rs.getString(4));
				pat_view pv = new pat_view();
				frmChooseSlot.dispose();
				pv.setVisible(true);
			}
			else
			{
				PreparedStatement st = con.prepareStatement("insert into appointment(doc_fk,pat_fk,slotnum,time,date,details,full_time) values(?,?,?,?,?,?,?)");
				  if(select == 0)
				  { 
					  JOptionPane.showMessageDialog(frmChooseSlot,"Select your slot before proceeding");
				  } 
				  if(details.length()==0)
				  {
					  JOptionPane.showMessageDialog(frmChooseSlot, "Enter the details");
					  type = 0;
					  
				  }
				  else
				  {
					  type = 1;
				  }
				  if(select ==1 && type ==1)
				  {
					  st.setString(1, doc_name);
					  st.setString(2, pat_name);
					  st.setInt(3, num);
					  st.setString(4, slot);
					  st.setString(5, date);
					  st.setString(6, details);
					  st.setString(7, full_date);
					  st.executeUpdate();
//					  JOptionPane.showMessageDialog(frame,"Success");
					  success s = new success();
					  frmChooseSlot.dispose();
					  s.setVisible(true);
					  
				  }
			}
			  
			  
			  con.close();
			 
		} catch (ClassNotFoundException | SQLException e1) {
			e1.printStackTrace();
		}
			
	}

	public void setVisible(boolean b) {
		frmChooseSlot.setVisible(b);
	}
}