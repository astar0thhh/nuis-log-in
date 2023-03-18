package ab;

import java.awt.EventQueue;

import javax.swing.JFrame;

public class NUMoaEnter {

	JFrame frmNUMoaEnter;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					NUMoaEnter window = new NUMoaEnter();
					window.frmNUMoaEnter.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public NUMoaEnter() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmNUMoaEnter = new JFrame();
		frmNUMoaEnter.setBounds(100, 100, 450, 300);
		frmNUMoaEnter.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

}
