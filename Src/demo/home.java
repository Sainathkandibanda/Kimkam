 package demo;

import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.plaf.ColorUIResource;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Toolkit;
import javax.swing.JTextField;
import java.awt.Panel;
import javax.swing.ImageIcon;
import java.awt.ScrollPane;
import javax.swing.SwingConstants;

public class home {

	private JFrame frmSchedoc;
	private JButton login;
	private JButton signup;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		UIManager.put("ToggleButton.select", new ColorUIResource( Color.GREEN ));
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					home window = new home();
					window.frmSchedoc.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public home() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmSchedoc = new JFrame();
		frmSchedoc.setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\\\Users\\\\saina\\\\Documents\\\\Programsinjava\\\\demo_schedoc\\\\demo_schedoc\\\\Images\\\\stethoscope.png"));
		frmSchedoc.setTitle("ScheDOC\r\n");
		frmSchedoc.setBounds(100, 100, 650, 600);
		frmSchedoc.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmSchedoc.getContentPane().setLayout(null);
		frmSchedoc.getContentPane().setBackground( Color.decode("#f5f3e6") );
		
		
		login = new JButton("Log in");
		login.setForeground(new Color(0, 128, 0));
		login.setBackground(new Color(255, 255, 224));
		login.setFocusable(false);
		
		login.setFont(new Font("Times New Roman", Font.BOLD, 24));
		login.setBounds(212, 355, 203, 50);
		frmSchedoc.getContentPane().add(login);
		
		JLabel lblNewLabel_1 = new JLabel("Sche");
		lblNewLabel_1.setForeground(new Color(0, 0, 139));
		lblNewLabel_1.setFont(new Font("Verdana", Font.BOLD, 50));
		lblNewLabel_1.setBounds(195, 133, 231, 97);
		frmSchedoc.getContentPane().add(lblNewLabel_1);
		
		signup = new JButton("SignUp");
		signup.setForeground(new Color(178, 34, 34));
		signup.setBackground(new Color(255, 255, 224));
		signup.setFocusable(false);
		
		signup.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				signupActionPerformed(e);
			}
		});
		signup.setFont(new Font("Tahoma", Font.PLAIN, 20));
		signup.setBounds(485, 46, 110, 40);
		frmSchedoc.getContentPane().add(signup);
		
		JLabel lblNewLabel = new JLabel("DOC");
		lblNewLabel.setForeground(new Color(139, 69, 19));
		lblNewLabel.setFont(new Font("Trebuchet MS", Font.BOLD | Font.ITALIC, 53));
		lblNewLabel.setBounds(330, 157, 216, 50);
		frmSchedoc.getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_2 = new JLabel("Don't have an account? Create one...");
		lblNewLabel_2.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		lblNewLabel_2.setBounds(410, 0, 216, 57);
		frmSchedoc.getContentPane().add(lblNewLabel_2);
		login.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				loginActionPerformed(e);
			}
		});
	}

	public void dispose() {
		frmSchedoc.dispose();
	}

	public void setVisible(boolean b)
	{
		frmSchedoc.setVisible(b);
	}
	private void signupActionPerformed(ActionEvent e)
	{
		dialog_signup ds = new dialog_signup();
		frmSchedoc.dispose();
		ds.setVisible(true);
	}
	private void loginActionPerformed(ActionEvent e)
	{
		dialog_login dl = new dialog_login();
		frmSchedoc.dispose();
		dl.setVisible(true);
	}
}