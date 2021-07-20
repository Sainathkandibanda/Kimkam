package demo;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.SwingConstants;

import net.proteanit.sql.DbUtils;

import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;
import java.awt.event.ActionEvent;
import javax.swing.border.LineBorder;
import java.awt.Color;
import java.awt.Toolkit;

public class doc_view {
	
	public static String doc_name;
	int num = 0;
	int id = 0;
	
	java.util.Date date;
	java.util.Date get_date;
	
	
	private JFrame frmViewOrCancel;
	private JTextField appId;
	private JTable table;
	private JButton cancel;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					doc_view window = new doc_view();
					window.frmViewOrCancel.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public doc_view() {
		initialize();
		new doc_login();
		doc_name = doc_login.username1;
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/sys","root","Saigopi@12");
			PreparedStatement st = con.prepareStatement("select id as Appointment_Id,pat_fk as Patient,time as Time,date as Date,details as Reason from appointment where doc_fk = '"+doc_name+"' and full_time>NOW() order by full_time ASC");
			ResultSet rs = st.executeQuery();
			table.setModel(DbUtils.resultSetToTableModel(rs));
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmViewOrCancel = new JFrame();
		frmViewOrCancel.setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\saina\\Documents\\Programsinjava\\demo_schedoc\\demo_schedoc\\Images\\stethoscope.png"));
		frmViewOrCancel.setTitle("View or Cancel");
		frmViewOrCancel.setBounds(100, 100, 900, 650);
		frmViewOrCancel.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmViewOrCancel.getContentPane().setLayout(null);
		frmViewOrCancel.getContentPane().setBackground( Color.decode("#f5e8e7") );
		
		JLabel lblUpcomingAppointment = new JLabel("Upcoming Appointments");
		lblUpcomingAppointment.setHorizontalAlignment(SwingConstants.CENTER);
		lblUpcomingAppointment.setFont(new Font("Tahoma", Font.PLAIN, 30));
		lblUpcomingAppointment.setBounds(235, 78, 360, 53);
		frmViewOrCancel.getContentPane().add(lblUpcomingAppointment);
		
		JButton back = new JButton("Back");
		back.setBorderPainted(false);
		back.setFocusable(false);
		back.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				doc_home dh = new doc_home();
				frmViewOrCancel.dispose();
				dh.setVisible(true);
			}
		});
		back.setFont(new Font("Tahoma", Font.PLAIN, 20));
		back.setBounds(28, 45, 97, 33);
		frmViewOrCancel.getContentPane().add(back);
		
		JLabel lblNewLabel = new JLabel("Cancel Appointment");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 30));
		lblNewLabel.setBounds(272, 374, 332, 39);
		frmViewOrCancel.getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Enter Appointment ID");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel_1.setBounds(176, 457, 216, 33);
		frmViewOrCancel.getContentPane().add(lblNewLabel_1);
		
		appId = new JTextField();
		appId.addKeyListener(new KeyAdapter() {
		    public void keyTyped(KeyEvent e) { 
		        if (appId.getText().length() >= 3 || e.getKeyChar()<=47 || e.getKeyChar()>=58||e.getKeyChar()==32) // limit appId to 3 integers
		            e.consume(); 
		    }  
		});
		appId.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				appIdActionPerformed(e);
			}
		});
		appId.setColumns(10);
		appId.setBounds(403, 457, 146, 30);
		frmViewOrCancel.getContentPane().add(appId);
		
		cancel = new JButton("Cancel ");
		cancel.setFocusable(false);
		cancel.setFont(new Font("Tahoma", Font.PLAIN, 15));
		cancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cancelActionPerformed(e);
			}
		});
		cancel.setBounds(403, 520, 146, 30);
		frmViewOrCancel.getContentPane().add(cancel);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(30, 150, 836, 185);
		frmViewOrCancel.getContentPane().add(scrollPane);
		
		table = new JTable();
		table.setBackground(Color.decode("#fdf9f9"));
		table.setBorder(new LineBorder(new Color(0, 0, 0)));
		table.setEnabled(false);
		table.setFont(new Font("Tahoma", Font.PLAIN, 13));
		scrollPane.setViewportView(table);
	
	}
	private void cancelActionPerformed(ActionEvent e)
	{
		appIdActionPerformed(e);
		if(id==0)
		{
			JOptionPane.showMessageDialog(frmViewOrCancel, "Appointment ID does not exists");
		} 
		else
		{
			try {
				Class.forName("com.mysql.cj.jdbc.Driver");
				Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/sys","root","Saigopi@12");
				Statement st = con.createStatement();
				ResultSet rs = st.executeQuery("select full_time from appointment where id = '"+num+"'");
				rs.next();
				Date today = new Date();   // creates a new variable to store today's date
				java.sql.Timestamp timestamp = rs.getTimestamp(1);   // gets the time-stamp of DateTime object
				get_date = new java.util.Date(timestamp.getTime());  // converts the time-stamp to date object
				if(today.before(get_date))						     // checks whether the given date of appointment is from future
				{
					st.executeUpdate("delete from appointment where id = '"+num+"'");
		        	JOptionPane.showMessageDialog(frmViewOrCancel,"Your upcoming appointment is Successfully Cancelled");
		        	doc_home dh = new doc_home();
		        	frmViewOrCancel.dispose();
		        	dh.setVisible(true);
				}
		        else
		        {
		        	JOptionPane.showMessageDialog(frmViewOrCancel, "ERROR: Cannot cancel the previous appointments");
		        }
		        st.close();
		        con.close();
				
				
			} catch (ClassNotFoundException | SQLException e1) {
				e1.printStackTrace();
			}
		}
		
		
	}
	
	private void appIdActionPerformed(ActionEvent e)
	{
		num = Integer.parseInt(appId.getText());
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/sys","root","Saigopi@12");
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery("select id from appointment");
			while(rs.next())
			{
				if (appId.getText().equals(rs.getString(1)))
				{
					id = 1;  // ID Exists
					break;
				}
				else
				{
					id = 0;
				}
			}
		} catch (ClassNotFoundException | SQLException e1) {
			e1.printStackTrace();
		}
	}
	public void setVisible(boolean b) {
		frmViewOrCancel.setVisible(b);
	}
}