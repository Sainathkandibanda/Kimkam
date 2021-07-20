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

public class dialog_signup {

	private JFrame frame;
	private JButton pat;
	private JButton doc;
	private JButton back;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					dialog_signup window = new dialog_signup();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public dialog_signup() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\saina\\Documents\\Programsinjava\\demo_schedoc\\demo_schedoc\\Images\\stethoscope.png"));
		frame.setBounds(100, 100, 650, 600);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.getContentPane().setBackground( Color.decode("#e6f5f3") );

		
		doc = new JButton("Doctor");
		doc.setBackground(Color.decode("#f5f3e6"));
		doc.setFocusable(false);
		doc.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				docActionPerformed(e);
			}
		});
		doc.setFont(new Font("Tahoma", Font.PLAIN, 20));
		doc.setBounds(73, 400, 200, 50);
		frame.getContentPane().add(doc);
		
		pat = new JButton("Patient");
		pat.setBackground(Color.decode("#f5f3e6"));
		pat.setFocusable(false);
		pat.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				patActionPerformed(e);
			}
		});
		pat.setFont(new Font("Tahoma", Font.PLAIN, 20));
		pat.setBounds(357, 400, 200, 50);
		frame.getContentPane().add(pat);
		
		JLabel lblNewLabel = new JLabel("Are you?");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 40));
		lblNewLabel.setBounds(230, 147, 160, 105);
		frame.getContentPane().add(lblNewLabel);
		
		back = new JButton("Back");
		back.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				home h = new home();
				frame.dispose();
				h.setVisible(true);
			}
		});
		back.setFont(new Font("Tahoma", Font.PLAIN, 20));
		back.setFocusable(false);
		back.setBackground(Color.decode("#f5f3e6"));
		back.setBounds(73, 48, 115, 33);
		frame.getContentPane().add(back);
	}
	private void patActionPerformed(ActionEvent e)
	{
		pat_signup ps = new pat_signup();
		//home h = new home();
		//h.dispose();
		frame.dispose();		
		ps.setVisible(true);
	}
	private void docActionPerformed(ActionEvent e)
	{
		doc_signup docs = new doc_signup();
		//home h = new home();
		//h.dispose();
		frame.dispose();		
		docs.setVisible(true);
	}

	public void setVisible(boolean b) {
		frame.setVisible(true);
		
	}
}
