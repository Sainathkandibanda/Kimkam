package demo;

import java.awt.EventQueue;
import java.sql.*;
import java.util.Date;

import javax.swing.JFrame;
import javax.swing.JTable;

import net.proteanit.sql.DbUtils;

import javax.swing.JScrollPane;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.ActionEvent;
import javax.swing.border.LineBorder;
import java.awt.Color;
import java.awt.Toolkit;

public class pat_view {
	
	public static String pat_name;
	int num;
	int id = 0;
	
	
	java.util.Date date;
	java.util.Date get_date;
	

	private JFrame frmViewOrCancel;
	private JTable table;
	private JTextField textId;
	private JButton cancel;
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					pat_view window = new pat_view();
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
	public pat_view() {
		initialize();
		new pat_login();
		pat_name = pat_login.username1;
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/sys","root","Saigopi@12");
			PreparedStatement st = con.prepareStatement("select id as Appointment_Id, doc_fk as Doctor,time as Time,date as Date, details as Reason from appointment where pat_fk = '"+pat_name+"' and full_time>NOW() order by full_time ASC");
			ResultSet rs = st.executeQuery();
			table.setModel(DbUtils.resultSetToTableModel(rs));
			
			JLabel lblNewLabel = new JLabel("Cancel Appointment");
			lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
			lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 30));
			lblNewLabel.setBounds(263, 336, 332, 39);
			frmViewOrCancel.getContentPane().add(lblNewLabel);
			
			JLabel lblNewLabel_1 = new JLabel("Enter Appointment ID");
			lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 20));
			lblNewLabel_1.setBounds(195, 425, 216, 33);
			frmViewOrCancel.getContentPane().add(lblNewLabel_1);
			
			textId = new JTextField();
			textId.addKeyListener(new KeyAdapter() {
			    public void keyTyped(KeyEvent e) { 
			        if (textId.getText().length() >= 3 || e.getKeyChar()<=47 || e.getKeyChar()>=58||e.getKeyChar()==32) // limit appId to 3 integers
			            e.consume(); 
			    }  
			});
			textId.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					textIdActionPerformed(e);
				}
			});
			textId.setBounds(421, 425, 146, 30);
			frmViewOrCancel.getContentPane().add(textId);
			textId.setColumns(10);
			
			cancel = new JButton("Cancel");
			cancel.setFocusable(false);
			cancel.setFont(new Font("Tahoma", Font.PLAIN, 15));
			cancel.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					cancelActionPerformed(e);
				}
			});
			cancel.setBounds(421, 493, 146, 30);
			frmViewOrCancel.getContentPane().add(cancel);
			
			JLabel lblUpcomingAppointment = new JLabel("Upcoming Appointments");
			lblUpcomingAppointment.setHorizontalAlignment(SwingConstants.CENTER);
			lblUpcomingAppointment.setFont(new Font("Tahoma", Font.PLAIN, 30));
			lblUpcomingAppointment.setBounds(235, 78, 360, 53);
			frmViewOrCancel.getContentPane().add(lblUpcomingAppointment);
			
			JButton back = new JButton("Back");
			back.setFocusable(false);
			back.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					pat_home ph = new pat_home();
					frmViewOrCancel.dispose();
					ph.setVisible(true);
				}
			});
			back.setFont(new Font("Tahoma", Font.PLAIN, 20));
			back.setBounds(28, 45, 97, 33);
			frmViewOrCancel.getContentPane().add(back);
			
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
		frmViewOrCancel.getContentPane().setBackground( Color.decode("#e9f5dc") );
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(28, 153, 829, 155);
		frmViewOrCancel.getContentPane().add(scrollPane);
		
		table = new JTable();
		table.setBackground(Color.decode("#f5fbf0"));
		table.setBorder(new LineBorder(new Color(0, 0, 0)));
		table.setFont(new Font("Tahoma", Font.PLAIN, 13));
		table.setEnabled(false);
		table.setRowSelectionAllowed(false);
		scrollPane.setViewportView(table);
		
	}
	private void textIdActionPerformed(ActionEvent e)
	{
		num = Integer.parseInt(textId.getText());
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/sys","root","Saigopi@12");
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery("select id from appointment");
			while(rs.next())
			{
				if (textId.getText().equals(rs.getString(1)))
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
	private void cancelActionPerformed(ActionEvent e)
	{
		textIdActionPerformed(e);
//		System.out.println(date);
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
		        	JOptionPane.showMessageDialog(frmViewOrCancel,"Your upcoming appointment is Successfully Canceled");
		        	pat_home ph = new pat_home();
		        	frmViewOrCancel.dispose();
		        	ph.setVisible(true);
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

	public void setVisible(boolean b) {
		frmViewOrCancel.setVisible(true);
	}
}