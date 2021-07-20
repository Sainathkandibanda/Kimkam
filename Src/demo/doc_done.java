package demo;

import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import net.proteanit.sql.DbUtils;

import javax.swing.JTable;
import javax.swing.JScrollPane;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import java.awt.Toolkit;

public class doc_done {
	public static String doc_name;

	private JFrame frmPreviousAppointments;
	private JTable table;


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					doc_done window = new doc_done();
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
	public doc_done() {
		initialize();
		new doc_login();
		doc_name = doc_login.username1;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/sys","root","Saigopi@12");
			PreparedStatement st = con.prepareStatement("select id as Appointment_Id,pat_fk as Patient,time as Time,date as Date,details as Reason from appointment where doc_fk = '"+doc_name+"' and full_time<NOW() order by full_time DESC");
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
		frmPreviousAppointments.getContentPane().setBackground( Color.decode("#f5e8e7") );
		
		JButton back = new JButton("Back");
		back.setBorderPainted(false);
		back.setFocusable(false);
		back.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				doc_home dh = new doc_home();
				frmPreviousAppointments.dispose();
				dh.setVisible(true);
			}
		});
		back.setFont(new Font("Tahoma", Font.PLAIN, 20));
		back.setBounds(28, 45, 97, 33);
		frmPreviousAppointments.getContentPane().add(back);
		
		JLabel lblAppointmentsHistory = new JLabel("Appointments History");
		lblAppointmentsHistory.setHorizontalAlignment(SwingConstants.CENTER);
		lblAppointmentsHistory.setFont(new Font("Tahoma", Font.PLAIN, 30));
		lblAppointmentsHistory.setBounds(237, 112, 360, 53);
		frmPreviousAppointments.getContentPane().add(lblAppointmentsHistory);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(25, 197, 832, 222);
		frmPreviousAppointments.getContentPane().add(scrollPane);
		
		table = new JTable();
		table.setBackground(Color.decode("#fdf9f9"));
		table.setEnabled(false);
		table.setFont(new Font("Tahoma", Font.PLAIN, 13));
		scrollPane.setViewportView(table);
		
		
		
	}

	public void setVisible(boolean b) {
		frmPreviousAppointments.setVisible(b);
	}

}