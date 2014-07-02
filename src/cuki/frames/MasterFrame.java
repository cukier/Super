package cuki.frames;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import java.awt.Color;

import javax.swing.JMenuBar;
import javax.swing.JButton;
import javax.swing.ImageIcon;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import net.miginfocom.swing.MigLayout;

import javax.swing.JTextPane;

import cuki.utils.KModbus;

@SuppressWarnings("serial")
public class MasterFrame extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MasterFrame frame = new MasterFrame(null);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	private void createGUI(final JFrame frame, String pivoName) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);

		JMenuBar menuBar = new JMenuBar();
		menuBar.setBackground(Color.WHITE);
		setJMenuBar(menuBar);

		JButton btnHome = new JButton("");
		btnHome.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				dispose();
				if (frame != null)
					frame.setVisible(true);
			}
		});
		btnHome.setBackground(Color.WHITE);
		btnHome.setIcon(new ImageIcon(MasterFrame.class
				.getResource("/imgs/home.png")));
		menuBar.add(btnHome);

		JTextPane textPane = new JTextPane();
		textPane.setText(pivoName);
		menuBar.add(textPane);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new MigLayout("", "[]", "[]"));
	}

	public MasterFrame(final JFrame frame) {

		KModbus sync = KModbus.getReference("COM8", 1);

		createGUI(frame, sync.getPivoName());
	}

}
