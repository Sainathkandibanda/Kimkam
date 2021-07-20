package demo;

import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.Calendar; 	

import javax.swing.JComboBox;
import com.toedter.calendar.JDateChooser;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.awt.event.ActionEvent;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.plaf.ColorUIResource;

import java.sql.*;
import javax.swing.SwingConstants;
import java.awt.Toolkit;

public class new_booking {
	
	public static String date1;
	public static String date2;
	public static String day1;
	public static String specialization;
	public static String doc_selected;
	public static String pat_name;
	int d = 0;  // to select doctor
	int s = 0;  // to select specialization
	int a = 0;  // to select day
	int full = 0;

	private JFrame frmNewAppointment;
	private JComboBox<String> Specialization;
	private JDateChooser dateChooser;
	private java.util.Date date;
	private JButton submit;
	private JButton book;
	private JComboBox<String> doc_list;
	private JPanel checkpanel;
	private JLabel lblBookAppointment;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		UIManager.put("ToggleButton.select", new ColorUIResource( Color.GREEN ));
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					new_booking window = new new_booking();
					window.frmNewAppointment.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public new_booking() {
		initialize();
	    new pat_login();
		pat_name = pat_login.username1;

	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmNewAppointment = new JFrame();
		frmNewAppointment.setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\saina\\Documents\\Programsinjava\\demo_schedoc\\demo_schedoc\\Images\\stethoscope.png"));
		frmNewAppointment.setTitle("New Appointment");
		frmNewAppointment.setBounds(100, 100, 862, 649);
		frmNewAppointment.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmNewAppointment.getContentPane().setLayout(null);
		frmNewAppointment.getContentPane().setBackground( Color.decode("#e9f5dc") );
		
		JLabel lblNewLabel = new JLabel("Specialization   :");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel.setBounds(117, 153, 156, 36);
		frmNewAppointment.getContentPane().add(lblNewLabel);
		
		Specialization = new JComboBox<>();
		Specialization.setFont(new Font("Tahoma", Font.PLAIN, 18));
		Specialization.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SpecializationActionPerformed(e);
			}
		});
		Specialization.setModel(new DefaultComboBoxModel<>(new String[] {"Cardiologist", "Gastroenterologist", "Neurologist", "Ophthalmologist", "Otolaryngologist", "Pulmonologist"}));
		Specialization.setBounds(353, 153, 203, 33);
		frmNewAppointment.getContentPane().add(Specialization);
		
		dateChooser = new JDateChooser();
		dateChooser.getCalendarButton().setFont(new Font("Tahoma", Font.PLAIN, 18));
		dateChooser.getCalendarButton().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dateChooserActionPerformed(e);
			}
		});
		dateChooser.setMinSelectableDate(new Date());
		dateChooser.setMaxSelectableDate(getSixDaysFromNow());
		dateChooser.setDateFormatString("dd-MM-yyyy");
		dateChooser.setDate(new Date());
				
		dateChooser.setBounds(353, 254, 203, 32);
		frmNewAppointment.getContentPane().add(dateChooser);
		
		
		
		JLabel lblDate = new JLabel("Date   :");
		lblDate.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblDate.setBounds(117, 254, 156, 36);
		frmNewAppointment.getContentPane().add(lblDate);
		
		submit = new JButton("Check");
		submit.setFocusable(false);
		submit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				submitActionPerformed(e);
			}
		});
		submit.setFont(new Font("Tahoma", Font.PLAIN, 20));
		submit.setBounds(256, 352, 132, 45);
		frmNewAppointment.getContentPane().add(submit);
		
		checkpanel = new JPanel();
		checkpanel.setBackground(Color.decode("#e9f5dc") );
		checkpanel.setBounds(28, 418, 791, 181);
		frmNewAppointment.getContentPane().add(checkpanel);
		checkpanel.setLayout(null);
		checkpanel.setVisible(false);
		
		JLabel lblAvailableDoctors = new JLabel("Available Doctors   :");
		lblAvailableDoctors.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblAvailableDoctors.setBounds(31, 51, 180, 36);
		checkpanel.add(lblAvailableDoctors);
		
		book = new JButton("Book");
		book.setFocusable(false);
		book.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				bookActionPerformed(e);
			}
		});
		book.setFont(new Font("Tahoma", Font.PLAIN, 20));
		book.setBounds(42, 112, 132, 45);
		checkpanel.add(book);
		
		doc_list = new JComboBox<String>();
		doc_list.setFont(new Font("Tahoma", Font.PLAIN, 17));
		doc_list.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				doc_listActionPerformed(e);
			}
		});
		doc_list.setBounds(256, 51, 203, 33);
		checkpanel.add(doc_list);
		
		JButton back = new JButton("Back");
		back.setFocusable(false);
		back.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				pat_home ph = new pat_home();
				frmNewAppointment.dispose();
				ph.setVisible(true);
			}
		});
		back.setFont(new Font("Tahoma", Font.PLAIN, 20));
		back.setBounds(46, 61, 97, 33);
		frmNewAppointment.getContentPane().add(back);
		
		lblBookAppointment = new JLabel("Schedule Appointment\r\n");
		lblBookAppointment.setHorizontalAlignment(SwingConstants.CENTER);
		lblBookAppointment.setFont(new Font("Tahoma", Font.PLAIN, 30));
		lblBookAppointment.setBounds(256, 47, 360, 53);
		frmNewAppointment.getContentPane().add(lblBookAppointment);
	}
	private java.sql.Date getSixDaysFromNow() {
		Calendar currentDate = Calendar.getInstance();
		currentDate.add(Calendar.DATE, 6);
		return new java.sql.Date(currentDate.getTimeInMillis());
	}
	private void submitActionPerformed(ActionEvent e)
	{
		SpecializationActionPerformed(e);
		checkpanel.setVisible(true);
		doc_list.removeAllItems();
		
		
		DateFormat df =  new SimpleDateFormat("E, dd MMM yyyy");
		DateFormat df2 = new SimpleDateFormat("YYYY-MM-dd");
		date1 = df.format(dateChooser.getDate());  // date is stored as String in "E, dd-MMM-yyyy" format
		date2 = df2.format(dateChooser.getDate());
	
		
		date = dateChooser.getDate();
		LocalDateTime ldt = date.toInstant()
		        .atZone(ZoneId.systemDefault())
		        .toLocalDateTime();
		DayOfWeek dow = ldt.getDayOfWeek();
		day1 = dow.name();                   // day is stored as String as ex."MONDAY" format
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/sys","root","Saigopi@12");
			Statement st = con.createStatement();
			if(day1=="MONDAY")
			{
				a = 3;
				ResultSet rs = st.executeQuery("select doc_avail.doc_id from doc_avail,doc where doc_avail.doc_id=doc.username and doc.field='"+specialization+"' and  mon=1 ");
				while(rs.next())
				{
					doc_list.addItem(rs.getString(1));
				}
			}
			if(day1=="TUESDAY")
			{
				a = 4;
				ResultSet rs = st.executeQuery("select doc_avail.doc_id from doc_avail,doc where doc_avail.doc_id=doc.username and doc.field='"+specialization+"' and tue=1 ");
				while(rs.next())
				{
					doc_list.addItem(rs.getString(1));
				}
			}
			if(day1=="WEDNESDAY")
			{
				a = 5;
				ResultSet rs = st.executeQuery("select doc_avail.doc_id from doc_avail,doc where doc_avail.doc_id=doc.username and doc.field='"+specialization+"' and wed=1 ");
				while(rs.next())
				{
					doc_list.addItem(rs.getString(1));
				}
			}
			if(day1=="THURSDAY")
			{
				a = 6;
				ResultSet rs = st.executeQuery("select doc_avail.doc_id from doc_avail,doc where doc_avail.doc_id=doc.username and doc.field='"+specialization+"' and thu=1 ");
				while(rs.next())
				{
					doc_list.addItem(rs.getString(1));
				}
			}
			if(day1=="FRIDAY")
			{
				a = 7;
				ResultSet rs = st.executeQuery("select doc_avail.doc_id from doc_avail,doc where doc_avail.doc_id=doc.username and doc.field='"+specialization+"' and fri=1 ");
				while(rs.next())
				{
					doc_list.addItem(rs.getString(1));
				}
			}
			if(day1=="SATURDAY")
			{
				a = 8;
				ResultSet rs = st.executeQuery("select doc_avail.doc_id from doc_avail,doc where doc_avail.doc_id=doc.username and doc.field='"+specialization+"' and sat=1 ");
				while(rs.next())
				{
					doc_list.addItem(rs.getString(1));
				}
			}
			if(day1=="SUNDAY")
			{
				a = 9;
				ResultSet rs = st.executeQuery("select doc_avail.doc_id from doc_avail,doc where doc_avail.doc_id=doc.username and doc.field='"+specialization+"' and sun=1 ");
				while(rs.next())
				{
					doc_list.addItem(rs.getString(1));
				}
			}
			st.close();
			con.close();
			//System.out.println(a);
			
		} catch (ClassNotFoundException | SQLException e1) {
			e1.printStackTrace();
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
	private void dateChooserActionPerformed(ActionEvent e)
	{
		checkpanel.setVisible(false);
	}
	private void doc_listActionPerformed(ActionEvent e)
	{
		doc_selected = (String)doc_list.getSelectedItem();
	
		if(doc_selected==null)
		{
			d = 0;  // Select doctor
		} 
		else
		{
			d = 1;   // valid
		}
	}
	private void bookActionPerformed(ActionEvent e)
	{
		doc_listActionPerformed(e);
		
			if(d==0)
			{
				JOptionPane.showMessageDialog(frmNewAppointment, "Sorry, Heroes are busy this day choose another day");
			}
			if(d==1&&full==0)
			{
				confirm_book cb = new confirm_book();
				frmNewAppointment.dispose();
				cb.setVisible(true);
			}
	
			
	}

	public void setVisible(boolean b) {
		frmNewAppointment.setVisible(b);
	}
}
