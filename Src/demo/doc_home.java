package demo;

import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.Font;
import javax.swing.JLabel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Toolkit;

public class doc_home {
	
	public static String doc_name;

	private JFrame frmDoctorHome;
	private JButton available;
	private JLabel welcome;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					doc_home window = new doc_home();
					window.frmDoctorHome.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public doc_home() {
		initialize();
		new doc_login();
		doc_name = doc_login.username1;
		welcome.setText("Welcome "+doc_name+",");
		
		
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmDoctorHome = new JFrame();
		frmDoctorHome.setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\saina\\Documents\\Programsinjava\\demo_schedoc\\demo_schedoc\\Images\\stethoscope.png"));
		frmDoctorHome.setTitle("Doctor Home");
		frmDoctorHome.setBounds(100, 100, 900, 687);
		frmDoctorHome.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmDoctorHome.getContentPane().setLayout(null);
		frmDoctorHome.getContentPane().setBackground( Color.decode("#f5e8e7") );
		
		JButton logout = new JButton("LogOut");
		logout.setBorderPainted(false);
		logout.setFocusable(false);
		logout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				home h = new home();
				frmDoctorHome.dispose();
				h.setVisible(true);
			}
		});
		logout.setFont(new Font("Tahoma", Font.PLAIN, 20));
		logout.setBounds(696, 47, 128, 33);
		frmDoctorHome.getContentPane().add(logout);
		
		welcome = new JLabel("Welcome");
		welcome.setFont(new Font("Tahoma", Font.PLAIN, 40));
		welcome.setBounds(20, 11, 626, 109);
		frmDoctorHome.getContentPane().add(welcome);
		
		JButton profile = new JButton("Manage Profile");
		profile.setBorderPainted(false);
		profile.setFocusable(false);
		profile.setFont(new Font("Tahoma", Font.PLAIN, 20));
		profile.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				doc_edit de = new doc_edit();
				frmDoctorHome.dispose();
				de.setVisible(true);
			}
		});
		profile.setBounds(325, 127, 283, 110);
		frmDoctorHome.getContentPane().add(profile);
		
		available = new JButton("Update Availability");
		available.setBorderPainted(false);
		available.setFocusable(false);
		available.setFont(new Font("Tahoma", Font.PLAIN, 20));
		available.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				availableActionPerformed(e);
			}
		});
		available.setBounds(325, 248, 283, 110);
		frmDoctorHome.getContentPane().add(available);
		
		JButton btnViewAppointments = new JButton("View or Cancel Upcoming");
		btnViewAppointments.setBorderPainted(false);
		btnViewAppointments.setFocusable(false);
		btnViewAppointments.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnViewAppointments.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				doc_view dv = new doc_view();
				frmDoctorHome.dispose();
				dv.setVisible(true);
			}
		});
		btnViewAppointments.setBounds(325, 369, 283, 110);
		frmDoctorHome.getContentPane().add(btnViewAppointments);
		
		JButton btnPreviousAppointments = new JButton("Previous Appointments");
		btnPreviousAppointments.setBorderPainted(false);
		btnPreviousAppointments.setFocusable(false);
		btnPreviousAppointments.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnPreviousAppointments.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				doc_done dd = new doc_done();
				frmDoctorHome.dispose();
				dd.setVisible(true);
			}
		});
		btnPreviousAppointments.setBounds(325, 493, 283, 110);
		frmDoctorHome.getContentPane().add(btnPreviousAppointments);
	}

	public void setVisible(boolean b) {
		frmDoctorHome.setVisible(b);
	}
	private void availableActionPerformed(ActionEvent e)
	{
		availability doca = new availability();
		frmDoctorHome.dispose();
		doca.setVisible(true);
	}

}