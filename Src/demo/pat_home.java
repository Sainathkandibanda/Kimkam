package demo;

import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Toolkit;

public class pat_home {
	private String pat_name;

	private JFrame frmPatientHome;
	private JButton profile;
	private JButton new_appointment;
	private JButton logout;
	private JButton viewApp;
	private JButton viewApp_1;
	private JLabel welcome;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					pat_home window = new pat_home();
					window.frmPatientHome.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public pat_home() {
		initialize();
		new pat_login();
		pat_name = pat_login.username1;
		welcome.setText("Welcome "+pat_name+",");
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmPatientHome = new JFrame();
		frmPatientHome.setTitle("Patient Home");
		frmPatientHome.setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\saina\\Documents\\Programsinjava\\demo_schedoc\\demo_schedoc\\Images\\stethoscope.png"));
		frmPatientHome.setBounds(100, 100, 900, 701);
		frmPatientHome.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmPatientHome.getContentPane().setLayout(null);
		frmPatientHome.getContentPane().setBackground( Color.decode("#e9f5dc") );
		
		welcome = new JLabel("Welcome ");
		welcome.setFont(new Font("Tahoma", Font.PLAIN, 40));
		welcome.setBounds(20, 11, 626, 109);
		frmPatientHome.getContentPane().add(welcome);
		
		logout = new JButton("LogOut");
		logout.setFocusable(false);
		logout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				logoutActionPerformed(e);
			}
		});
		logout.setFont(new Font("Tahoma", Font.PLAIN, 20));
		logout.setBounds(696, 47, 128, 33);
		frmPatientHome.getContentPane().add(logout);
		
		profile = new JButton("Manage Profile");
		profile.setFocusable(false);
		profile.setFont(new Font("Tahoma", Font.PLAIN, 20));
		profile.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				profileActionPerformed(e);
			}
		});
		profile.setBounds(325, 127, 270, 110);
		frmPatientHome.getContentPane().add(profile);
		
		new_appointment = new JButton("New Appointment");
		new_appointment.setFocusable(false);
		new_appointment.setFont(new Font("Tahoma", Font.PLAIN, 20));
		new_appointment.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new_appointmentActionPerformed(e);
			}
		});
		new_appointment.setBounds(325, 248, 270, 110);
		frmPatientHome.getContentPane().add(new_appointment);
		
		viewApp = new JButton("View or Cancel Upcoming");
		viewApp.setFocusable(false);
		viewApp.setFont(new Font("Tahoma", Font.PLAIN, 20));
		viewApp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				viewAppActionPerformed(e);
			}
		});
		viewApp.setBounds(325, 369, 270, 110);
		frmPatientHome.getContentPane().add(viewApp);
		
		viewApp_1 = new JButton("Previous Appointments");
		viewApp_1.setFocusable(false);
		viewApp_1.setFont(new Font("Tahoma", Font.PLAIN, 20));
		viewApp_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				pat_done pd = new pat_done();
				frmPatientHome.dispose();
				pd.setVisible(true);
			}
		});
		viewApp_1.setBounds(325, 490, 270, 110);
		frmPatientHome.getContentPane().add(viewApp_1);
	}

	private void profileActionPerformed(ActionEvent e)
	{
		pat_edit pe = new pat_edit();
		frmPatientHome.dispose();
		pe.setVisible(true);
	}
	private void logoutActionPerformed(ActionEvent e)
	{
		home h = new home();
		frmPatientHome.dispose();
		h.setVisible(true);
	}
	private void new_appointmentActionPerformed(ActionEvent e)
	{
		new_booking nb = new new_booking();
		frmPatientHome.dispose();
		nb.setVisible(true);
	}
	private void viewAppActionPerformed(ActionEvent e)
	{
		pat_view pv = new pat_view();
		frmPatientHome.dispose();
		pv.setVisible(true);
	}
	public void setVisible(boolean b) {
		frmPatientHome.setVisible(true);
		
	}

}