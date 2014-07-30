package cuki.gui;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import net.miginfocom.swing.MigLayout;

@SuppressWarnings("serial")
public class Setores extends JPanel implements ActionListener {

	private String m_port;
	private int m_addr;
	private int[] m_angulo;

	public Setores(String port, int addr, Font font) {

		m_port = port;
		m_addr = addr;

		setLayout(new MigLayout());

		JLabel lbl = new JLabel("Setor");
		lbl.setFont(font);
		add(lbl, "center");

		lbl = new JLabel("Angulo");
		lbl.setFont(font);
		add(lbl, "center,wrap");

		for (int i = 0; i < 12; ++i) {
			if (i % 2 == 0) {
				String str = "Setor " + i;
				lbl = new JLabel(str);
				lbl.setFont(font);
				add(lbl, "center");
			} else {
				JTextField tf = new JTextField();
				tf.setFont(font);
				tf.setPreferredSize(new Dimension(60, 20));
				tf.addActionListener(this);
				add(tf, "center,wrap");
			}

		}

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub

	}

	public static void main(String[] args) {
		JFrame frame = new JFrame();

		Setores s = new Setores("COM8", 1, new Font("Lucida Console",
				Font.PLAIN, 18));

		frame.setContentPane(s);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.pack();
		frame.setVisible(true);
	}
}
