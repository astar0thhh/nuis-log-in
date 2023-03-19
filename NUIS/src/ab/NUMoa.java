package ab;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;

import java.awt.event.ActionListener;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;



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

public class NUMoa {

	JFrame frmNUMoa;
	private JPasswordField passwordField;
	static ArrayList<String[]> userList = new ArrayList<>();
	private JLabel lblSeePass, lblUnseePass;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					NUMoa window = new NUMoa();
					window.frmNUMoa.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public NUMoa() {
		String[] predefinedUser = {"admin@gmail.com", "password"};
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		try {
			userList.clear();
			BufferedWriter writePredefinedUser = new BufferedWriter(new FileWriter("NUMoaStudentCredentials.txt", true));
			BufferedReader readUsers = new BufferedReader(new FileReader("NUMoaStudentCredentials.txt"));
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
		frmNUMoa = new JFrame();
		frmNUMoa.setResizable(false);
		frmNUMoa.setBounds(100, 100, 1110, 600);
		frmNUMoa.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmNUMoa.getContentPane().setLayout(null);
		
		JLabel lblLogin = new JLabel("Login");
		lblLogin.setForeground(Color.WHITE);
		lblLogin.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblLogin.setBounds(686, 180, 45, 13);
		frmNUMoa.getContentPane().add(lblLogin);
		
		JLabel lblNewLabel_1 = new JLabel("Register");
		lblNewLabel_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				NUMoaReg window = new NUMoaReg();
				window.frmNUMoaReg.setVisible(true);
				frmNUMoa.dispose();
			}
		});
		lblNewLabel_1.setForeground(Color.WHITE);
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblNewLabel_1.setBounds(734, 181, 64, 13);
		frmNUMoa.getContentPane().add(lblNewLabel_1);
		
		JComboBox cmBoxBranch = new JComboBox();
		cmBoxBranch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (cmBoxBranch.getSelectedIndex()== 2) {
					NUManila window = new NUManila();
					window.frmNUManila.setVisible(true);
					frmNUMoa.dispose();					
				}
				else if(cmBoxBranch.getSelectedIndex()== 3) {
					NUDasmarinas window = new NUDasmarinas();
					window.frmNUDasmarinas.setVisible(true);
					frmNUMoa.dispose();
					
				}
				else if (cmBoxBranch.getSelectedIndex()==4) {
					NULipa window = new NULipa();
					window.frmNULipa.setVisible(true);
					frmNUMoa.dispose();
					
				}
				else if (cmBoxBranch.getSelectedIndex()==5) {
					NUBaliwag window = new NUBaliwag();
					window.frmNUBaliwag.setVisible(true);
					frmNUMoa.dispose();
					
				}
			}
		});
		cmBoxBranch.setFont(new Font("Tahoma", Font.PLAIN, 15));
		cmBoxBranch.setModel(new DefaultComboBoxModel(new String[] {"NU MOA", "-Select Branch-", "NU Manila", "NU Dasmarinas", "NU Lipa", "NU Baliwag"}));
		cmBoxBranch.setToolTipText("");
		cmBoxBranch.setBounds(716, 224, 219, 34);
		frmNUMoa.getContentPane().add(cmBoxBranch);
		
		
		
		JFormattedTextField txtEmail = new JFormattedTextField();
		txtEmail.setBounds(716, 273, 219, 29);
		frmNUMoa.getContentPane().add(txtEmail);
		
		JButton btnNewButton = new JButton("Login");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				boolean isExisting = false;
				for(String[] userCredentials: userList) {
					if(txtEmail.getText().equals(userCredentials[0])) {
						isExisting = true;
						if(passwordField.getText().equals(userCredentials[1])) {
							
							NUMoaEnter window = new NUMoaEnter();
							window.frmNUMoaEnter.setVisible(true);
							frmNUMoa.dispose();
							
						}
						else
							JOptionPane.showMessageDialog(frmNUMoa, "Incorrect Password. Please try again.", "Login Unsuccessful", JOptionPane.ERROR_MESSAGE);
					}
				}
				
				if(!isExisting) {
					JOptionPane.showMessageDialog(frmNUMoa, "Email does not Exist!", "Invalid Email", JOptionPane.WARNING_MESSAGE);
					txtEmail.grabFocus();
				}
			}
		});
		btnNewButton.setBackground(new Color(240, 230, 140));
		btnNewButton.setForeground(Color.ORANGE);
		btnNewButton.setBounds(686, 368, 71, 21);
		frmNUMoa.getContentPane().add(btnNewButton);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(716, 317, 219, 29);
		frmNUMoa.getContentPane().add(passwordField);
		
		lblSeePass = new JLabel("");
		lblSeePass.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				passwordField.setEchoChar((char) 0);
				passwordField.setText(String.valueOf(passwordField.getPassword()));
				lblUnseePass.setVisible(true);
				lblUnseePass.setEnabled(true);
				lblSeePass.setVisible(false);
				lblSeePass.disable();
			}
		});
		lblSeePass.setIcon(new ImageIcon("C:\\Users\\ROG\\Downloads\\Untitled design (2)\\1.png"));
		lblSeePass.setBounds(934, 317, 39, 29);
		frmNUMoa.getContentPane().add(lblSeePass);
		
		lblUnseePass = new JLabel("");
		lblUnseePass.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				passwordField.setEchoChar((char) '●');
				lblSeePass.setVisible(true);
				lblSeePass.setEnabled(true);
				lblUnseePass.setVisible(false);
				lblUnseePass.disable();
			}
		});
		lblUnseePass.setIcon(new ImageIcon("C:\\Users\\ROG\\Downloads\\Untitled design (2)\\2.png"));
		lblUnseePass.setBounds(934, 317, 39, 29);
		lblUnseePass.setVisible(false);
		frmNUMoa.getContentPane().add(lblUnseePass);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon("C:\\Users\\ROG\\Downloads\\NUMOA.png"));
		lblNewLabel.setBounds(0, 0, 1096, 563);
		frmNUMoa.getContentPane().add(lblNewLabel);
	}

}
