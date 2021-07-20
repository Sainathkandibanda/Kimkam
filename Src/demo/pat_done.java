package demo;

import java.awt.Color;
import java.awt.EventQueue;
import java.sql.*;

import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.SwingConstants;

import net.proteanit.sql.DbUtils;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Toolkit;

public class pat_done {
	
	public static String pat_name;

	private JFrame frmPreviousAppointments;
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					pat_done window = new pat_done();
					window.frmPreviousAppointments.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public pat_done() {
		initialize();
		new pat_login();
		pat_name = pat_login.username1;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/alien","root","pavanitej");
			PreparedStatement st = con.prepareStatement("select id as Appointment_Id,doc_fk as Doctor,time as Time,date as Date,details as Reason from appointment where pat_fk = '"+pat_name+"' and full_time<NOW() order by full_time DESC");
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
		frmPreviousAppointments = new JFrame();
		frmPreviousAppointments.setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\saina\\Documents\\Programsinjava\\demo_schedoc\\demo_schedoc\\Images\\stethoscope.png"));
		frmPreviousAppointments.setTitle("Previous Appointments");
		frmPreviousAppointments.setBounds(100, 100, 900, 650);
		frmPreviousAppointments.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmPreviousAppointments.getContentPane().setLayout(null);
		frmPreviousAppointments.getContentPane().setBackground( Color.decode("#e9f5dc") );
		
		JButton back = new JButton("Back");
		back.setFocusable(false);
		back.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				pat_home ph = new pat_home();
				frmPreviousAppointments.dispose();
				ph.setVisible(true);
			}
		});
		back.setFont(new Font("Tahoma", Font.PLAIN, 20));
		back.setBounds(28, 45, 97, 33);
		frmPreviousAppointments.getContentPane().add(back);
		
		JLabel lblHistoryAppointments = new JLabel("Appointments History");
		lblHistoryAppointments.setHorizontalAlignment(SwingConstants.CENTER);
		lblHistoryAppointments.setFont(new Font("Tahoma", Font.PLAIN, 30));
		lblHistoryAppointments.setBounds(237, 112, 360, 53);
		frmPreviousAppointments.getContentPane().add(lblHistoryAppointments);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(25, 197, 832, 222);
		frmPreviousAppointments.getContentPane().add(scrollPane);
		
		table = new JTable();
		table.setBackground(Color.decode("#f5fbf0"));
		table.setEnabled(false);
		table.setFont(new Font("Tahoma", Font.PLAIN, 13));
		scrollPane.setViewportView(table);
	}

	public void setVisible(boolean b) {
		frmPreviousAppointments.setVisible(b);
	}
}