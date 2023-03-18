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

public class NUDasmarinasReg {

	JFrame frmNUDasmarinasReg;
	static ArrayList<String[]> userList = new ArrayList<>();
	String emailValidate = "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$";
	private JPasswordField txtPassword;
	private JPasswordField txtConfirmPassword;
	private JFormattedTextField txtEmail;
	private JLabel lblRegister;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					NUDasmarinasReg window = new NUDasmarinasReg();
					window.frmNUDasmarinasReg.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public NUDasmarinasReg() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		try {
			BufferedReader readUsers = new BufferedReader(new FileReader("NUDasmarinasStudentCredentials.txt"));
			String currentLine;
			while((currentLine = readUsers.readLine()) != null) {
				userList.add(currentLine.split("\t"));
			}
			readUsers.close();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		frmNUDasmarinasReg = new JFrame();
		frmNUDasmarinasReg.setResizable(false);
		frmNUDasmarinasReg.setBounds(100, 100, 1110, 600);
		frmNUDasmarinasReg.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		

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
		frmNUDasmarinasReg.getContentPane().add(txtEmail);
		
		txtPassword = new JPasswordField();
		txtPassword.setBounds(718, 309, 223, 29);
		frmNUDasmarinasReg.getContentPane().add(txtPassword);
		
		txtConfirmPassword = new JPasswordField();
		txtConfirmPassword.setBounds(718, 358, 223, 29);
		frmNUDasmarinasReg.getContentPane().add(txtConfirmPassword);
		
		JButton btnRegister = new JButton("Register");
		btnRegister.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
        		if(txtConfirmPassword.getText().isBlank() || txtEmail.getText().isBlank() || txtPassword.getText().isEmpty())
        			JOptionPane.showMessageDialog(frmNUDasmarinasReg, "Please fill in the necessary information", "Empty Fields Detected", JOptionPane.ERROR_MESSAGE);
        		else if(!(txtPassword.getText().equals(txtConfirmPassword.getText())))
               		JOptionPane.showMessageDialog(frmNUDasmarinasReg, "Passwords do not match", "Password Mismatch", JOptionPane.ERROR_MESSAGE);
               	else {
               		boolean isExisting = false;
               		for(String[] user : userList) {
    					if(txtEmail.getText().equals(user[0])) {
    						isExisting = true;
    					}
    				}
               		if(isExisting) {
               			JOptionPane.showMessageDialog(frmNUDasmarinasReg, "Email already exists", "Invalid email", JOptionPane.ERROR_MESSAGE);
               			txtEmail.grabFocus();
               		}else {
	               		try {
	    					BufferedWriter updateUsers = new BufferedWriter(new FileWriter("NUDasmarinasStudentCredentials.txt", true));
	    					updateUsers.newLine();
	    					updateUsers.write(txtEmail.getText() + "\t" + txtPassword.getText());
	    					updateUsers.close();
	    					JOptionPane.showMessageDialog(frmNUDasmarinasReg, "Registered Successfully!", "Succesful Registration", JOptionPane.INFORMATION_MESSAGE);
	    					NUDasmarinas window = new NUDasmarinas();
	    					window.frmNUDasmarinas.setVisible(true);
	    					frmNUDasmarinasReg.dispose();
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
		frmNUDasmarinasReg.getContentPane().add(btnRegister);
		
		JLabel lblLogin = new JLabel("Login");
		lblLogin.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				NUDasmarinas window = new NUDasmarinas();
				window.frmNUDasmarinas.setVisible(true);
				frmNUDasmarinasReg.dispose();
			}
		});
		lblLogin.setForeground(Color.WHITE);
		lblLogin.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblLogin.setBounds(686, 180, 45, 13);
		frmNUDasmarinasReg.getContentPane().add(lblLogin);
		
		lblRegister = new JLabel("Register");
		lblRegister.setForeground(Color.WHITE);
		lblRegister.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblRegister.setBounds(736, 180, 60, 13);
		frmNUDasmarinasReg.getContentPane().add(lblRegister);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon("C:\\Users\\ROG\\Downloads\\NURegPage\\NNUDASMARINASREG.png"));
		lblNewLabel.setBounds(0, 0, 1096, 563);
		frmNUDasmarinasReg.getContentPane().add(lblNewLabel);
	}

}
