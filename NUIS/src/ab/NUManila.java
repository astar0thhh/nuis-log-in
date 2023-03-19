package ab;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import java.awt.Font;
import java.awt.Color;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JFormattedTextField;
import javax.swing.JPasswordField;



import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class NUManila {

	JFrame frmNUManila;
	private JPasswordField passwordField;
	static ArrayList<String[]> userList = new ArrayList<>();
	private JButton btnNewButton;
	private JFormattedTextField txtEmail;
	private JLabel lblUnSeePass, lblSeePass;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					NUManila window = new NUManila();
					window.frmNUManila.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public NUManila() {
		String[] predefinedUser = {"admin@gmail.com", "password"};
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		try {
			userList.clear();
			BufferedWriter writePredefinedUser = new BufferedWriter(new FileWriter("NUMnlStudentCredentials.txt", true));
			BufferedReader readUsers = new BufferedReader(new FileReader("NUMnlStudentCredentials.txt"));
			String currentLine;
			if((currentLine = readUsers.readLine()) == null) {
				writePredefinedUser.write("admin@gmail.com\tpassword"); //predefined user credentials
				writePredefinedUser.close();
			}else {
				userList.add(currentLine.split("\t"));
			}
			while((currentLine = readUsers.readLine()) != null) {
				userList.add(currentLine.split("\t"));
			}
			readUsers.close();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		frmNUManila = new JFrame();
		frmNUManila.setResizable(false);
		frmNUManila.setBounds(100, 100, 1110, 600);
		frmNUManila.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmNUManila.getContentPane().setLayout(null);
		
		JLabel lblLogin = new JLabel("Login");
		lblLogin.setForeground(Color.WHITE);
		lblLogin.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblLogin.setBounds(686, 180, 45, 13);
		frmNUManila.getContentPane().add(lblLogin);
		
		JLabel lblNewLabel_1 = new JLabel("Register");
		lblNewLabel_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {			
				NUManilaReg window = new NUManilaReg();
				window.frmManilaReg.setVisible(true);
				frmNUManila.dispose();
			}
		});
		lblNewLabel_1.setForeground(Color.WHITE);
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblNewLabel_1.setBounds(734, 181, 64, 13);
		frmNUManila.getContentPane().add(lblNewLabel_1);
		
		JComboBox cmBoxBranch = new JComboBox();
		cmBoxBranch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(cmBoxBranch.getSelectedIndex()==2) {
					NUMoa window = new NUMoa();
					window.frmNUMoa.setVisible(true);
					frmNUManila.dispose();
				}
				else if (cmBoxBranch.getSelectedIndex()==3) {
					NUDasmarinas window = new NUDasmarinas();
					window.frmNUDasmarinas.setVisible(true);
					frmNUManila.dispose();
					
				}
				else if (cmBoxBranch.getSelectedIndex()==4) {
					NULipa windowLipa = new NULipa();
					windowLipa.frmNULipa.setVisible(true);
					frmNUManila.dispose();
					
				}
				else if (cmBoxBranch.getSelectedIndex()==5) {
					NUBaliwag windowBaliwag = new NUBaliwag();
					windowBaliwag.frmNUBaliwag.setVisible(true);
					frmNUManila.dispose();				
					
				}
				
				
			}
		});
		cmBoxBranch.setFont(new Font("Tahoma", Font.PLAIN, 15));
		cmBoxBranch.setModel(new DefaultComboBoxModel(new String[] {"NU Manila", "-Select Branch-", "NU MOA", "NU Dasmarinas", "NU Lipa", "NU Baliwag"}));
		cmBoxBranch.setToolTipText("");
		cmBoxBranch.setBounds(716, 224, 219, 34);
		frmNUManila.getContentPane().add(cmBoxBranch);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(716, 317, 219, 29);
		frmNUManila.getContentPane().add(passwordField);
		
		JFormattedTextField txtEmail = new JFormattedTextField();
		txtEmail.setBounds(716, 273, 219, 29);
		frmNUManila.getContentPane().add(txtEmail);
		
		btnNewButton = new JButton("Login");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				boolean isExisting = false;
				for(String[] NUMnlStudentCredentials: userList) {
					if(txtEmail.getText().equals(NUMnlStudentCredentials[0])) {
						isExisting = true;
						if(passwordField.getText().equals(NUMnlStudentCredentials[1])) {
							
							NUMnl window = new NUMnl();
							window.frmNUMnl.setVisible(true);
							frmNUManila.dispose();
							
						}
						else
							JOptionPane.showMessageDialog(frmNUManila, "Incorrect Password. Please try again.", "Login Unsuccessful", JOptionPane.ERROR_MESSAGE);
					}
				}
				
				if(!isExisting) {
					JOptionPane.showMessageDialog(frmNUManila, "Email does not Exist!", "Invalid Email", JOptionPane.WARNING_MESSAGE);
					txtEmail.grabFocus();
				}
			}
		});
		btnNewButton.setBackground(new Color(240, 230, 140));
		btnNewButton.setForeground(Color.ORANGE);
		btnNewButton.setBounds(686, 368, 71, 21);
		frmNUManila.getContentPane().add(btnNewButton);
		
		lblSeePass = new JLabel("");
		lblSeePass.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				passwordField.setEchoChar((char) 0);
				passwordField.setText(String.valueOf(passwordField.getPassword()));
				lblUnSeePass.setVisible(true);
				lblUnSeePass.setEnabled(true);
				lblSeePass.setVisible(false);
				lblSeePass.disable();
			}
		});
		lblSeePass.setIcon(new ImageIcon("C:\\Users\\ROG\\Downloads\\Untitled design (2)\\1.png"));
		lblSeePass.setBounds(934, 317, 39, 29);
		frmNUManila.getContentPane().add(lblSeePass);
		
		lblUnSeePass = new JLabel("");
		lblUnSeePass.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				passwordField.setEchoChar((char) '●');
				lblSeePass.setVisible(true);
				lblSeePass.setEnabled(true);
				lblUnSeePass.setVisible(false);
				lblUnSeePass.disable();
			}
		});
		lblUnSeePass.setIcon(new ImageIcon("C:\\Users\\ROG\\Downloads\\Untitled design (2)\\2.png"));
		lblUnSeePass.setBounds(934, 317, 39, 29);
		lblUnSeePass.setVisible(false);
		frmNUManila.getContentPane().add(lblUnSeePass);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon("C:\\Users\\ROG\\Downloads\\NUManila.png"));
		lblNewLabel.setBounds(0, 0, 1096, 563);
		frmNUManila.getContentPane().add(lblNewLabel);
	}
}
