package ab;

import java.awt.EventQueue;

import javax.swing.JFrame;

public class NUMnl {

	JFrame frmNUMnl;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					NUMnl window = new NUMnl();
					window.frmNUMnl.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public NUMnl() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmNUMnl = new JFrame();
		frmNUMnl.setBounds(100, 100, 454, 300);
		frmNUMnl.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

}
