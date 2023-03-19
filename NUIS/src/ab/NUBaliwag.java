package ab;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class NUBaliwag {

	JFrame frmNUBaliwag;
	JPasswordField passwordField;
	static ArrayList<String[]> userList = new ArrayList<>();
	private JLabel lblSeePass, lblUnSeePass;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					NUBaliwag window = new NUBaliwag();
					window.frmNUBaliwag.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public NUBaliwag() {
		String[] predefinedUser = {"admin@gmail.com", "password"};
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {try {
		userList.clear();
		BufferedWriter writePredefinedUser = new BufferedWriter(new FileWriter("NUBaliwagStudentCredentials.txt", true));
		BufferedReader readUsers = new BufferedReader(new FileReader("NUBaliwagStudentCredentials.txt"));
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
		frmNUBaliwag = new JFrame();
		frmNUBaliwag.setResizable(false);
		frmNUBaliwag.setBounds(100, 100, 1110, 600);
		frmNUBaliwag.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmNUBaliwag.getContentPane().setLayout(null);
		
		JLabel lblLogin = new JLabel("Login");
		lblLogin.setForeground(Color.WHITE);
		lblLogin.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblLogin.setBounds(686, 180, 45, 13);
		frmNUBaliwag.getContentPane().add(lblLogin);
		
		JLabel lblNewLabel_1 = new JLabel("Register");
		lblNewLabel_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				NUBaliwagReg window = new NUBaliwagReg();
				window.frmNUBaliwagReg.setVisible(true);
				frmNUBaliwag.dispose();
			}
		});
		lblNewLabel_1.setForeground(Color.WHITE);
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblNewLabel_1.setBounds(734, 181, 64, 13);
		frmNUBaliwag.getContentPane().add(lblNewLabel_1);
		
		JComboBox cmBoxBranch = new JComboBox();
		cmBoxBranch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(cmBoxBranch.getSelectedIndex()==2) {
					NUManila window = new NUManila();
					window.frmNUManila.setVisible(true);
					frmNUBaliwag.dispose();
				}
				else if (cmBoxBranch.getSelectedIndex()==3) {
					NUMoa window = new NUMoa();
					window.frmNUMoa.setVisible(true);
					frmNUBaliwag.dispose();
					
				}
				else if (cmBoxBranch.getSelectedIndex()==4) {
					NUDasmarinas window = new NUDasmarinas();
					window.frmNUDasmarinas.setVisible(true);
					frmNUBaliwag.dispose();
				}
				else if (cmBoxBranch.getSelectedIndex()==5) {
					NULipa window = new NULipa();
					window.frmNULipa.setVisible(true);
					frmNUBaliwag.dispose();
				}
				
			}
		});
		cmBoxBranch.setFont(new Font("Tahoma", Font.PLAIN, 15));
		cmBoxBranch.setModel(new DefaultComboBoxModel(new String[] {"NU Baliwag", "-Select Branch-", "NU Manila", "NU MOA", "NU Dasmarinas", "NU Lipa"}));
		cmBoxBranch.setToolTipText("");
		cmBoxBranch.setBounds(716, 224, 219, 34);
		 frmNUBaliwag.getContentPane().add(cmBoxBranch);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(716, 317, 219, 29);
		frmNUBaliwag.getContentPane().add(passwordField);
		
		JFormattedTextField txtEmail = new JFormattedTextField();
		txtEmail.setBounds(716, 273, 219, 29);
		frmNUBaliwag.getContentPane().add(txtEmail);
		
		JButton btnNewButton = new JButton("Login");
		btnNewButton.setBackground(new Color(240, 230, 140));
		btnNewButton.setForeground(Color.ORANGE);
		btnNewButton.setBounds(686, 368, 71, 21);
		frmNUBaliwag.getContentPane().add(btnNewButton);
		
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
		frmNUBaliwag.getContentPane().add(lblSeePass);
		
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
		frmNUBaliwag.getContentPane().add(lblUnSeePass);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon("C:\\Users\\ROG\\Downloads\\NUBaliwag.png"));
		lblNewLabel.setBounds(0, 0, 1096, 563);
		frmNUBaliwag.getContentPane().add(lblNewLabel);
	}

}
