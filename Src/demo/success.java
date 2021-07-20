package demo;

import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JPanel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Toolkit;
import javax.swing.ImageIcon;

public class success {
	
	public static String pat;
	public static String doc;
	public static String time;
	public static String date1;

	private JFrame frmSuccess;
	private JLabel lab_1;
	private JLabel lab_2;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					success window = new success();
					window.frmSuccess.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public success() {
		initialize();
		
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		
		new confirm_book();
		pat = confirm_book.pat_name;
		doc = confirm_book.doc_name;
		time = confirm_book.slot;
		date1 = confirm_book.date;
		
		
		
		frmSuccess = new JFrame();
		frmSuccess.setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\saina\\Documents\\Programsinjava\\demo_schedoc\\demo_schedoc\\Images\\stethoscope.png"));
		frmSuccess.setTitle("Appointment Scheduled");
		frmSuccess.setBounds(100, 100, 900, 650);
		frmSuccess.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmSuccess.getContentPane().setLayout(null);
		frmSuccess.getContentPane().setBackground( Color.decode("#e9f5dc") );
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.decode("#e9f5dc"));
		panel.setBounds(36, 350, 838, 156);
		frmSuccess.getContentPane().add(panel);
		panel.setLayout(null);
		
		lab_1 = new JLabel("Hello, "+pat);
		lab_1.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lab_1.setBounds(10, 39, 175, 25);
		panel.add(lab_1);
		
		lab_2 = new JLabel("Your appointment with "+doc+" is scheduled on "+date1+" at "+time);
		lab_2.setHorizontalAlignment(SwingConstants.LEFT);
		lab_2.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lab_2.setVerticalAlignment(SwingConstants.TOP);
		lab_2.setBounds(10, 90, 839, 91);
		panel.add(lab_2);
		
		JButton btnNewButton = new JButton("OK");
		btnNewButton.setFocusable(false);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				pat_home ph = new pat_home();
				frmSuccess.dispose();
				ph.setVisible(true);
			}
		});
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnNewButton.setBounds(358, 517, 105, 66);
		frmSuccess.getContentPane().add(btnNewButton);
		
		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 5));
		lblNewLabel_1.setIcon(new ImageIcon("C:\\Users\\saina\\Documents\\Programsinjava\\demo_schedoc\\demo_schedoc\\Images\\success.png"));
		lblNewLabel_1.setBounds(267, 11, 328, 328);
		frmSuccess.getContentPane().add(lblNewLabel_1);
	}

	public void setVisible(boolean b) {
		frmSuccess.setVisible(b);
		
	}
}