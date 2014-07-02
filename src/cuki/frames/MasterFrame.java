package cuki.frames;

import java.awt.BorderLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Color;
import javax.swing.JButton;
import javax.swing.ImageIcon;

@SuppressWarnings("serial")
public class MasterFrame extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {

		MasterFrame frame = new MasterFrame();
		frame.setVisible(true);

	}

	/**
	 * Create the frame.
	 */
	public MasterFrame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(null);
		contentPane.setLayout(new BorderLayout(10, 10));
		setContentPane(contentPane);

		Fundo barr = new Fundo(getSize());
		contentPane.add(barr, BorderLayout.NORTH);
		barr.setLayout(null);
		
		JButton btnHome = new JButton("");
		btnHome.setIcon(new ImageIcon(MasterFrame.class.getResource("/imgs/bt-home.png")));
		btnHome.setToolTipText("Go Home");
		btnHome.setBounds(10, 11, 90, 40);
		barr.add(btnHome);
	}
}
