package cuki.gui;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import cuki.teste.PortLister;
import net.miginfocom.swing.MigLayout;

@SuppressWarnings("serial")
public class ConfConn extends JPanel implements ActionListener {

	private JComboBox<String> portCombo;
	private JComboBox<String> addrCombo;
	private JFrame m_home;

	public ConfConn(JFrame home, Font font) {

		m_home = home;

		String[] portas = PortLister.getPorts();

		if (portas.length == 0) {
			System.err.println("N�o foi encotrada nenhuma porta");
			System.exit(1);
		}

		setLayout(new MigLayout());

		JLabel lbl = new JLabel("Porta: ");
		lbl.setFont(font);
		add(lbl, "grow");

		portCombo = new JComboBox<String>(portas);
		portCombo.setFont(font);
		portCombo.addActionListener(this);
		add(portCombo, "wrap");

		lbl = new JLabel("Addr: ");
		lbl.setFont(font);
		add(lbl, "grow");

		String[] addrs = { "1", "2", "3", "4", "5" };

		addrCombo = new JComboBox<String>(addrs);
		addrCombo.setFont(font);
		addrCombo.addActionListener(this);
		add(addrCombo);
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() instanceof JComboBox) {

			@SuppressWarnings("rawtypes")
			JComboBox cp = (JComboBox) e.getSource();
			String aux = (String) cp.getSelectedItem();

			if (e.getSource() == portCombo)
				((Home) m_home).setPort(aux);
			else if (e.getSource() == addrCombo)
				((Home) m_home).setAddr(Integer.valueOf(aux));

			System.out.println("Escrito " + aux);
		}
	}

	public static void main(String[] args) {
		JFrame frame = new JFrame();

		ConfConn l = new ConfConn(null, new Font("Lucida Console", Font.PLAIN,
				18));

		frame.setContentPane(l);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.pack();
		frame.setVisible(true);
	}
}
