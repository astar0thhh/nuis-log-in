package ab;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;

public class NUBaliwagReg {

	JFrame frmNUBaliwagReg;
	static ArrayList<String[]> userList = new ArrayList<>();
	String emailValidate = "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$";
	private JPasswordField txtPassword;
	private JPasswordField txtConfirmPassword;
	private JFormattedTextField txtEmail;
	private JLabel lblRegister, lblSeePass, lblUnSeePass;;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					NUBaliwagReg window = new NUBaliwagReg();
					window.frmNUBaliwagReg.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public NUBaliwagReg() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		try {
			BufferedReader readUsers = new BufferedReader(new FileReader("NUBaliwagStudentCredentials.txt"));
			String currentLine;
			while((currentLine = readUsers.readLine()) != null) {
				userList.add(currentLine.split("\t"));
			}
			readUsers.close();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		frmNUBaliwagReg = new JFrame();
		frmNUBaliwagReg.setResizable(false);
		frmNUBaliwagReg.setBounds(100, 100, 1110, 600);
		frmNUBaliwagReg.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		

		txtEmail = new JFormattedTextField();
		txtEmail.addFocusListener(new FocusAdapter() {
	        	@Override
	        	public void focusLost(FocusEvent e) {
				if(!(txtEmail.getText().matches(emailValidate))) {
               		txtEmail.setText("Please Enter a Valid Email");               		
               		txtPassword.setEditable(false);
               		txtConfirmPassword.setEditable(false);
               		txtPassword.setBackground(Color.gray);
               		txtConfirmPassword.setBackground(Color.gray);
               		
               	}else {
               		
               		txtPassword.setEditable(true);
               		txtConfirmPassword.setEditable(true);
               		txtPassword.setBackground(Color.white);
               		txtConfirmPassword.setBackground(Color.white);
               	}
        	}
			
			@Override
			public void focusGained(FocusEvent e) {
				txtEmail.setText("");
			}
		});
		txtEmail.setBounds(718, 255, 223, 29);
		frmNUBaliwagReg.getContentPane().add(txtEmail);
		
		txtPassword = new JPasswordField();
		txtPassword.setBounds(718, 309, 223, 29);
		frmNUBaliwagReg.getContentPane().add(txtPassword);
		
		txtConfirmPassword = new JPasswordField();
		txtConfirmPassword.setBounds(718, 358, 223, 29);
		frmNUBaliwagReg.getContentPane().add(txtConfirmPassword);
		
		JButton btnRegister = new JButton("Register");
		btnRegister.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
        		if(txtConfirmPassword.getText().isBlank() || txtEmail.getText().isBlank() || txtPassword.getText().isEmpty())
        			JOptionPane.showMessageDialog(frmNUBaliwagReg, "Please fill in the necessary information", "Empty Fields Detected", JOptionPane.ERROR_MESSAGE);
        		else if(!(txtPassword.getText().equals(txtConfirmPassword.getText())))
               		JOptionPane.showMessageDialog(frmNUBaliwagReg, "Passwords do not match", "Password Mismatch", JOptionPane.ERROR_MESSAGE);
               	else {
               		boolean isExisting = false;
               		for(String[] user : userList) {
    					if(txtEmail.getText().equals(user[0])) {
    						isExisting = true;
    					}
    				}
               		if(isExisting) {
               			JOptionPane.showMessageDialog(frmNUBaliwagReg, "Email already exists", "Invalid email", JOptionPane.ERROR_MESSAGE);
               			txtEmail.grabFocus();
               		}else {
	               		try {
	    					BufferedWriter updateUsers = new BufferedWriter(new FileWriter("NUBaliwagStudentCredentials.txt", true));
	    					updateUsers.newLine();
	    					updateUsers.write(txtEmail.getText() + "\t" + txtPassword.getText());
	    					updateUsers.close();
	    					JOptionPane.showMessageDialog(frmNUBaliwagReg, "Registered Successfully!", "Succesful Registration", JOptionPane.INFORMATION_MESSAGE);
	    					NUBaliwag loginWindow = new NUBaliwag();
	    					loginWindow.frmNUBaliwag.setVisible(true);
	    					frmNUBaliwagReg.dispose();
	    				} catch (IOException e1) {
	    					e1.printStackTrace();
	    				}
               		}
               	}
               	
        	}
        });
		btnRegister.setBackground(new Color(255, 215, 0));
		btnRegister.setForeground(new Color(248, 248, 255));
		btnRegister.setBounds(689, 408, 85, 21);
		frmNUBaliwagReg.getContentPane().add(btnRegister);
		
		JLabel lblLogin = new JLabel("Login");
		lblLogin.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				NUBaliwag window = new NUBaliwag();
				window.frmNUBaliwag.setVisible(true);
				frmNUBaliwagReg.dispose();
			}
		});
		lblLogin.setForeground(Color.WHITE);
		lblLogin.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblLogin.setBounds(686, 180, 45, 13);
		frmNUBaliwagReg.getContentPane().add(lblLogin);
		
		lblRegister = new JLabel("Register");
		lblRegister.setForeground(Color.YELLOW);
		lblRegister.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblRegister.setBounds(736, 180, 60, 13);
		frmNUBaliwagReg.getContentPane().add(lblRegister);
		
		lblSeePass = new JLabel("");
		lblSeePass.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				txtPassword.setEchoChar((char) 0);
				txtPassword.setText(String.valueOf(txtPassword.getPassword()));
				txtConfirmPassword.setEchoChar((char) 0);
				txtConfirmPassword.setText(String.valueOf(txtConfirmPassword.getPassword()));
				lblUnSeePass.setVisible(true);
				lblUnSeePass.setEnabled(true);
				lblSeePass.setVisible(false);
				lblSeePass.disable();
			}
		});
		lblSeePass.setIcon(new ImageIcon("C:\\Users\\ROG\\Downloads\\Untitled design (2)\\1.png"));
		lblSeePass.setBounds(902, 400, 39, 29);
		frmNUBaliwagReg.getContentPane().add(lblSeePass);
		
		lblUnSeePass = new JLabel("");
		lblUnSeePass.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				txtPassword.setEchoChar((char) '●');
				txtConfirmPassword.setEchoChar((char) '●');
				lblSeePass.setVisible(true);
				lblSeePass.setEnabled(true);
				lblUnSeePass.setVisible(false);
				lblUnSeePass.disable();
			}
		});
		lblUnSeePass.setIcon(new ImageIcon("C:\\Users\\ROG\\Downloads\\Untitled design (2)\\2.png"));
		lblUnSeePass.setBounds(902, 397, 39, 29);
		lblUnSeePass.setVisible(false);
		frmNUBaliwagReg.getContentPane().add(lblUnSeePass);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon("C:\\Users\\ROG\\Downloads\\NURegPage\\NUBALIWAGREG.png"));
		lblNewLabel.setBounds(0, 0, 1096, 563);
		frmNUBaliwagReg.getContentPane().add(lblNewLabel);
	}

}
