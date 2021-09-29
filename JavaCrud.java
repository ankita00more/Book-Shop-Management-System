import java.awt.EventQueue;
import java.sql.*;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JPanel;
import javax.swing.border.BevelBorder;
import javax.swing.border.TitledBorder;

import net.proteanit.sql.DbUtils;

import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.Color;

public class JavaCrud {

	private JFrame frame;
	private JTextField textBname;
	private JTextField textEdition;
	private JTextField textPrice;
	private JTable table;
	private JTextField textBid;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					JavaCrud window = new JavaCrud();
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
	public JavaCrud() {
		initialize();
		Connect();
		table_load();
	}
	
	Connection con;
	PreparedStatement pst;
	ResultSet rs;
	
public void Connect()
{
	try {
		Class.forName("com.mysql.jdbc.Driver");
		con = DriverManager.getConnection("jdbc:mysql://localhost/javacrud", "root", "");
	}
	catch(ClassNotFoundException ex)
	{
		
	}
	catch(SQLException ex)
	{
	
	}
	
}

public void table_load()
{
	try {
		pst = con.prepareStatement("select * from book");
		rs = pst.executeQuery();
		table.setModel(DbUtils.resultSetToTableModel(rs));
	
	}
	catch(SQLException e)
	{
		e.printStackTrace();
	}
}
	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setBackground(Color.WHITE);
		frame.setBounds(100, 100, 907, 580);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("BOOK SHOP");
		lblNewLabel.setFont(new Font("Times New Roman", Font.BOLD, 30));
		lblNewLabel.setBounds(310, 11, 210, 61);
		frame.getContentPane().add(lblNewLabel);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "Registration", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(37, 69, 325, 239);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("Book Name: ");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_1.setBounds(20, 47, 94, 25);
		panel.add(lblNewLabel_1);
		
		JLabel lblNewLabel_1_1 = new JLabel("Edition:");
		lblNewLabel_1_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_1_1.setBounds(20, 104, 74, 22);
		panel.add(lblNewLabel_1_1);
		
		JLabel lblNewLabel_1_2 = new JLabel("Price:");
		lblNewLabel_1_2.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_1_2.setBounds(20, 166, 80, 22);
		panel.add(lblNewLabel_1_2);
		
		textBname = new JTextField();
		textBname.setBounds(126, 50, 161, 22);
		panel.add(textBname);
		textBname.setColumns(10);
		
		textEdition = new JTextField();
		textEdition.setColumns(10);
		textEdition.setBounds(126, 106, 161, 22);
		panel.add(textEdition);
		
		textPrice = new JTextField();
		textPrice.setColumns(10);
		textPrice.setBounds(126, 168, 161, 22);
		panel.add(textPrice);
		
		JButton btnSave = new JButton("SAVE");
		btnSave.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String bname,edition,price;
			
				bname = textBname.getText();
				edition = textEdition.getText();
				price = textPrice.getText();
					
				try {
					pst = con.prepareStatement("insert into book(Name,Edition,Price)values(?,?,?)");
					pst.setString(1, bname);
					pst.setString(2, edition);
					pst.setString(3, price);
					pst.executeUpdate();
					JOptionPane.showMessageDialog(null, "Record Added Successfully!!!");
					table_load();
					textBname.setText("");
					textEdition.setText("");
					textPrice.setText("");
					textBname.requestFocus();
				}
				catch (SQLException el) {
					el.printStackTrace();
					
				}
				
				}
		});
		btnSave.setBounds(42, 348, 89, 42);
		frame.getContentPane().add(btnSave);
		
		JButton btnExit = new JButton("EXIT");
		btnExit.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		btnExit.setBounds(152, 348, 89, 42);
		frame.getContentPane().add(btnExit);
		
		JButton btnClear = new JButton("CLEAR");
		btnClear.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textBname.setText("");
				textEdition.setText("");
				textPrice.setText("");
				textBname.requestFocus();
				
			}
		});
		btnClear.setBounds(261, 348, 89, 42);
		frame.getContentPane().add(btnClear);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(397, 68, 462, 322);
		frame.getContentPane().add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(null, "Search", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_1.setBounds(37, 441, 315, 74);
		frame.getContentPane().add(panel_1);
		panel_1.setLayout(null);
		
		JLabel lblNewLabel_1_2_1 = new JLabel("Book ID:");
		lblNewLabel_1_2_1.setBounds(10, 30, 80, 22);
		panel_1.add(lblNewLabel_1_2_1);
		lblNewLabel_1_2_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		
		textBid = new JTextField();
		textBid.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				try {
					String id= textBid.getText();
					
						pst = con.prepareStatement("select Name,Edition,Price from book where id= ?");
						pst.setString(1, id);
						ResultSet rs = pst.executeQuery();
					if(rs.next()==true)
					{
						String name = rs.getString(1);
						String edition = rs.getString(2);
						String price = rs.getString(3);
						
						textBname.setText(name);
						textEdition.setText(edition);
						textPrice.setText(price);
					}
					else
					{
						textBname.setText("");
						textEdition.setText("");
						textPrice.setText("");
					}
				}
					catch(SQLException ex)
					{
				
					}
		}
		
		});
		textBid.setBounds(111, 33, 160, 20);
		panel_1.add(textBid);
		textBid.setColumns(10);
		
		JButton btnUpdate = new JButton("UPDATE");
		btnUpdate.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			
				String bname,edition,price,bid;
				
				bname = textBname.getText();
				edition = textEdition.getText();
				price = textPrice.getText();
				bid= textBid.getText();
					
				try {
					pst = con.prepareStatement("update book set name=?,edition=?,price=? where id=?");
					pst.setString(1, bname);
					pst.setString(2, edition);
					pst.setString(3, price);
					pst.setString(4, bid);
					pst.executeUpdate();
					JOptionPane.showMessageDialog(null, "Record Updated Successfully!!!");
					table_load();
					textBname.setText("");
					textEdition.setText("");
					textPrice.setText("");
					textBname.requestFocus();
				}
				catch (SQLException el) {
					el.printStackTrace();
				}
					
				
			}
		});
		btnUpdate.setBounds(431, 415, 89, 42);
		frame.getContentPane().add(btnUpdate);
		
		JButton btnDelete = new JButton("DELETE");
		btnDelete.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String bid;
				
				bid= textBid.getText();
					
				try {
					pst = con.prepareStatement("delete from book where id=?");
					pst.setString(1, bid);
					pst.executeUpdate();
					JOptionPane.showMessageDialog(null, "Record Deleted Successfully!!!");
					table_load();
					textBname.setText("");
					textEdition.setText("");
					textPrice.setText("");
					textBname.requestFocus();
				}
				catch (SQLException el) {
					el.printStackTrace();
				}
					
			}
		});
		btnDelete.setBounds(547, 415, 89, 42);
		frame.getContentPane().add(btnDelete);
	}
}
