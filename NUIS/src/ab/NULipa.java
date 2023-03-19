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

public class NULipa {

	JFrame frmNULipa;
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
					NULipa window = new NULipa();
					window.frmNULipa.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public NULipa() {
		String[] predefinedUser = {"admin@gmail.com", "password"};
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		try {
			userList.clear();
			BufferedWriter writePredefinedUser = new BufferedWriter(new FileWriter("NULipaStudentCredentials.txt", true));
			BufferedReader readUsers = new BufferedReader(new FileReader("NULipaStudentCredentials.txt"));
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
		frmNULipa = new JFrame();
		frmNULipa.setResizable(false);
		frmNULipa.setBounds(100, 100, 1110, 600);
		frmNULipa.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmNULipa.getContentPane().setLayout(null);
		
		JLabel lblLogin = new JLabel("Login");
		lblLogin.setForeground(Color.YELLOW);
		lblLogin.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblLogin.setBounds(686, 180, 45, 13);
		frmNULipa.getContentPane().add(lblLogin);
		
		JLabel lblNewLabel_1 = new JLabel("Register");
		lblNewLabel_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				NULipaReg window = new NULipaReg();
				window.frmNULipaReg.setVisible(true);
				frmNULipa.dispose();
			}
		});
		lblNewLabel_1.setForeground(Color.WHITE);
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblNewLabel_1.setBounds(734, 181, 64, 13);
		frmNULipa.getContentPane().add(lblNewLabel_1);
		
		JComboBox cmBoxBranch = new JComboBox();
		cmBoxBranch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(cmBoxBranch.getSelectedIndex()==2) {
					NUManila window = new NUManila();
					window.frmNUManila.setVisible(true);
					frmNULipa.dispose();
				}
				else if (cmBoxBranch.getSelectedIndex()==3) {
					NUMoa window = new NUMoa();
					window.frmNUMoa.setVisible(true);
					frmNULipa.dispose();
					
				}
				else if (cmBoxBranch.getSelectedIndex()==4) {
					NUDasmarinas window = new NUDasmarinas();
					window.frmNUDasmarinas.setVisible(true);
					frmNULipa.dispose();
				}
				else if (cmBoxBranch.getSelectedIndex()==5) {
					NUBaliwag window = new NUBaliwag();
					window.frmNUBaliwag.setVisible(true);
					frmNULipa.dispose();
				}
				
			}
		});
		cmBoxBranch.setFont(new Font("Tahoma", Font.PLAIN, 15));
		cmBoxBranch.setModel(new DefaultComboBoxModel(new String[] {"NU Lipa", "-Select Branch-", "NU Manila", "NU MOA", "NU Dasmarinas", "NU Baliwag"}));
		cmBoxBranch.setToolTipText("");
		cmBoxBranch.setBounds(716, 224, 219, 34);
		frmNULipa.getContentPane().add(cmBoxBranch);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(716, 317, 219, 29);
		frmNULipa.getContentPane().add(passwordField);
		
		JFormattedTextField txtEmail = new JFormattedTextField();
		txtEmail.setBounds(716, 273, 219, 29);
		frmNULipa.getContentPane().add(txtEmail);
		
		JButton btnNewButton = new JButton("Login");
		btnNewButton.setBackground(new Color(240, 230, 140));
		btnNewButton.setForeground(Color.ORANGE);
		btnNewButton.setBounds(686, 368, 71, 21);
		frmNULipa.getContentPane().add(btnNewButton);
		
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
		frmNULipa.getContentPane().add(lblSeePass);
		
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
		frmNULipa.getContentPane().add(lblUnSeePass);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon("C:\\Users\\ROG\\Downloads\\NULipa.png"));
		lblNewLabel.setBounds(0, 0, 1096, 563);
		frmNULipa.getContentPane().add(lblNewLabel);
	}

}
