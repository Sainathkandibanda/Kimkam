package demo;

import java.awt.Color;
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JSlider;
import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.awt.event.ActionEvent;
import javax.swing.JCheckBox;
import java.awt.Toolkit;

public class availability {
	
	public static String user1;
	int present = 0; //if username present toggle this
	
	int a=0;
	int b = 0;
	int c = 0;
	int d = 0;
	int ee = 0;  // since variable e cannot be used
	int f = 0;
	int g = 0;
	int slider_1 = 0;
	int slider_2 = 0;
	int ss = 1;  // slider value to check end time greater than start time, ss==1 is for valid
	int tt = 1;  // to make user select atleast one day, tt==1 is for valid
	
	private JFrame frmAvailability;
	private JLabel start;
	private JLabel end;
	private JSlider slider1;
	private JSlider slider2;
	private JButton submit;
	private JCheckBox mon;
	private JCheckBox tue;
	private JCheckBox wed;
	private JCheckBox thu;
	private JCheckBox fri;
	private JCheckBox sat;
	private JCheckBox sun;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					availability window = new availability();
					window.frmAvailability.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public availability() {
		initialize();
		new doc_login();
		user1 = doc_login.username1;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/sys","root","Saigopi@12");
			Statement st = con.createStatement();
			ResultSet rs1 = st.executeQuery("select * from doc_avail");
			while(rs1.next())
			{
				if(rs1.getString(2).equals(user1))
				{
					present = 1;
				}
			}
			if(present == 1)
			{
				System.out.println("true");
				ResultSet rs = st.executeQuery("select * from doc_avail where id in (select max(id) as id from doc_avail where doc_id = '"+user1+"')");
				rs.next();
				mon.setSelected(rs.getBoolean(3));
				tue.setSelected(rs.getBoolean(4));
				wed.setSelected(rs.getBoolean(5));
				thu.setSelected(rs.getBoolean(6));
				fri.setSelected(rs.getBoolean(7));
				sat.setSelected(rs.getBoolean(8));
				sun.setSelected(rs.getBoolean(9));
				slider1.setValue(rs.getInt(10));
				slider2.setValue(rs.getInt(11));
			}
			st.close();
			con.close();

		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmAvailability = new JFrame();
		frmAvailability.setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\saina\\Documents\\Programsinjava\\demo_schedoc\\demo_schedoc\\Images\\stethoscope.png"));
		frmAvailability.setTitle("Availability");
		frmAvailability.setBounds(100, 100, 900, 650);
		frmAvailability.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmAvailability.getContentPane().setLayout(null);
		frmAvailability.getContentPane().setBackground( Color.decode("#f5e8e7") );
		
		JLabel lblNewLabel = new JLabel("Availability");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 35));
		lblNewLabel.setBounds(331, 26, 171, 47);
		frmAvailability.getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Available Days:");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel_1.setBounds(66, 144, 141, 25);
		frmAvailability.getContentPane().add(lblNewLabel_1);
		
		JLabel lblNewLabel_1_1 = new JLabel("Available Time:");
		lblNewLabel_1_1.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel_1_1.setBounds(66, 270, 141, 25);
		frmAvailability.getContentPane().add(lblNewLabel_1_1);
		
		start = new JLabel("Start Time");
		start.setFont(new Font("Tahoma", Font.PLAIN, 15));
		start.setBounds(132, 306, 185, 25);
		frmAvailability.getContentPane().add(start);
		
		end = new JLabel("End Time");
		end.setFont(new Font("Tahoma", Font.PLAIN, 15));
		end.setBounds(132, 417, 185, 25);
		frmAvailability.getContentPane().add(end);
		
		slider1 = new JSlider();
		slider1.setBackground(Color.decode("#f5e8e7") );
		slider1.setValue(9);
		slider1.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				start.setText("Start Time = "+slider1.getValue()+":00");
				slider_1 = slider1.getValue();
			}
		});
		slider1.setMajorTickSpacing(1);
		slider1.setMaximum(21);
		slider1.setMinimum(9);
		slider1.setPaintTicks(true);
		slider1.setPaintLabels(true);
		slider1.setBounds(132, 342, 584, 66);
		frmAvailability.getContentPane().add(slider1);
		
		slider2 = new JSlider();
		slider2.setBackground(Color.decode("#f5e8e7") );
		slider2.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				end.setText("End Time = "+slider2.getValue()+":00");
				slider_2 = slider2.getValue();
			}
		});
		slider2.setValue(10);
		slider2.setPaintTicks(true);
		slider2.setPaintLabels(true);
		slider2.setMinimum(10);
		slider2.setMaximum(21);
		slider2.setMajorTickSpacing(1);
		slider2.setBounds(131, 450, 584, 66);
		frmAvailability.getContentPane().add(slider2);
		
		submit = new JButton("Submit");
		submit.setFocusable(false);
		submit.setFocusable(false);
		submit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				submitActionPerformed(e);
			}
		});
		submit.setFont(new Font("Tahoma", Font.PLAIN, 15));
		submit.setBounds(132, 555, 109, 32);
		frmAvailability.getContentPane().add(submit);
		
		mon = new JCheckBox("Monday");
		mon.setFocusable(false);
		mon.setBackground(Color.decode("#f5e8e7") );
		mon.setFont(new Font("Tahoma", Font.PLAIN, 15));
		mon.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				monActionPerformed(e);
		
			}
		});
		mon.setBounds(296, 144, 97, 23);
		frmAvailability.getContentPane().add(mon);
		
		tue = new JCheckBox("Tuesday");
		tue.setFocusable(false);
		tue.setBackground(Color.decode("#f5e8e7") );
		tue.setFont(new Font("Tahoma", Font.PLAIN, 15));
		tue.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tueActionPerformed(e);
			}
		});
		tue.setBounds(296, 179, 97, 23);
		frmAvailability.getContentPane().add(tue);
		
		wed = new JCheckBox("Wednesday");
		wed.setFocusable(false);
		wed.setBackground(Color.decode("#f5e8e7") );
		wed.setFont(new Font("Tahoma", Font.PLAIN, 15));
		wed.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				wedActionPerformed(e);
			}
		});
		wed.setBounds(296, 218, 118, 23);
		frmAvailability.getContentPane().add(wed);
		
		thu = new JCheckBox("Thursday");
		thu.setFocusable(false);
		thu.setBackground(Color.decode("#f5e8e7") );
		thu.setFont(new Font("Tahoma", Font.PLAIN, 15));
		thu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				thuActionPerformed(e);
			}
		});
		thu.setBounds(469, 144, 97, 23);
		frmAvailability.getContentPane().add(thu);
		
		fri = new JCheckBox("Friday");
		fri.setFocusable(false);
		fri.setBackground(Color.decode("#f5e8e7") );
		fri.setFont(new Font("Tahoma", Font.PLAIN, 15));
		fri.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				friActionPerformed(e);
			}
		});
		fri.setBounds(469, 179, 97, 23);
		frmAvailability.getContentPane().add(fri);
		
		sat = new JCheckBox("Saturday");
		sat.setFocusable(false);
		sat.setBackground(Color.decode("#f5e8e7") );
		sat.setFont(new Font("Tahoma", Font.PLAIN, 15));
		sat.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				satActionPerformed(e);
			}
		});
		sat.setBounds(469, 218, 97, 23);
		frmAvailability.getContentPane().add(sat);
		
		sun = new JCheckBox("Sunday");
		sun.setFocusable(false);
		sun.setBackground(Color.decode("#f5e8e7") );
		sun.setFont(new Font("Tahoma", Font.PLAIN, 15));
		sun.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				sunActionPerformed(e);			}
		});
		sun.setBounds(619, 144, 97, 23);
		frmAvailability.getContentPane().add(sun);
		
		JLabel lblNewLabel_2 = new JLabel("(Select your schedule for next 7 days)");
		lblNewLabel_2.setBounds(43, 163, 272, 25);
		frmAvailability.getContentPane().add(lblNewLabel_2);
		
		JButton logout = new JButton("LogOut");
		logout.setBorderPainted(false);
		logout.setFocusable(false);
		logout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				home h = new home();
				frmAvailability.dispose();
				h.setVisible(true);
			}
		});
		logout.setFont(new Font("Tahoma", Font.PLAIN, 20));
		logout.setBounds(714, 48, 128, 33);
		frmAvailability.getContentPane().add(logout);
		
		JButton back = new JButton("Back");
		back.setBorderPainted(false);
		back.setFocusable(false);
		back.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				doc_home dh = new doc_home();
				frmAvailability.dispose();
				dh.setVisible(true);
			}
		});
		back.setFont(new Font("Tahoma", Font.PLAIN, 20));
		back.setBounds(43, 48, 128, 33);
		frmAvailability.getContentPane().add(back);
	}
	private void submitActionPerformed(ActionEvent e)
	{
		monActionPerformed(e);
		tueActionPerformed(e);
		wedActionPerformed(e);
		thuActionPerformed(e);
		friActionPerformed(e);
		satActionPerformed(e);
		sunActionPerformed(e);
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/sys","root","Saigopi@12");
			Statement st2 = con.createStatement();

			if(present==1)
			{
				if(ss==1&&tt==1)
				{
					st2.executeUpdate("update doc_avail set mon='"+a+"',tue='"+b+"',wed='"+c+"',thu='"+d+"',fri='"+ee+"',sat='"+f+"',sun='"+g+"',start='"+slider_1+"',end='"+slider_2+"' where doc_id ='"+user1+"'");
					JOptionPane.showMessageDialog(frmAvailability,"Success");
				}
						
			}
			else
			{
				PreparedStatement st1 = con.prepareStatement("insert into doc_avail(doc_id,mon,tue,wed,thu,fri,sat,sun,start,end) values(?,?,?,?,?,?,?,?,?,?)");
				if(ss==1&&tt==1)
				{
					st1.setString(1,user1);
					
					st1.setInt(2, a);
					st1.setInt(3, b);
					st1.setInt(4, c);
					st1.setInt(5, d);
					st1.setInt(6, ee);
					st1.setInt(7, f);
					st1.setInt(8, g);
					st1.setInt(9, slider_1);
					st1.setInt(10, slider_2);
					st1.executeUpdate();
					JOptionPane.showMessageDialog(frmAvailability,"Successfully updated");
					doc_home dh = new doc_home();
					frmAvailability.dispose();
					dh.setVisible(true);
				}
			}
	
			if(slider_1>slider_2)
			{
				JOptionPane.showMessageDialog(frmAvailability,"Start time cannot be greater than End time");
				ss = 0;
			} 
			if(a==0&&b==0&&c==0&&d==0&&ee==00&&f==0&&g==0)
			{
				JOptionPane.showMessageDialog(frmAvailability,"Select atleast one day");
				tt = 0;
			}
			
			
			
		} catch (ClassNotFoundException | SQLException e1) {
			e1.printStackTrace();
		}
		
		
	}
	private void monActionPerformed(ActionEvent e)
	{
		if(mon.isSelected())
		{
			a = 1;
		}
		else
		{
			a = 0;
		}
	}
	private void tueActionPerformed(ActionEvent e)
	{
		if(tue.isSelected())
		{
			b = 1;
		}
		else
		{
			b = 0;
		}
	}
	private void wedActionPerformed(ActionEvent e)
	{
		if(wed.isSelected())
		{
			c = 1;
		}
		else
		{
			c = 0;
		}
	}
	private void thuActionPerformed(ActionEvent e)
	{
		if(thu.isSelected())
		{
			d = 1;
		}
		else
		{
			d = 0;
		}
	}
	private void friActionPerformed(ActionEvent e)
	{
		if(fri.isSelected())
		{
			ee = 1;
		}
		else
		{
			ee = 0;
		}
	}
	private void satActionPerformed(ActionEvent e)
	{
		if(sat.isSelected())
		{
			f = 1;
		}
		else
		{
			f = 0;
		}
	}
	private void sunActionPerformed(ActionEvent e)
	{
		if(sun.isSelected())
		{
			g = 1;
		}
		else
		{
			g = 0;
		}
	}

	public void setVisible(boolean e) {
		frmAvailability.setVisible(e);
	}
}