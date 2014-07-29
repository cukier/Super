package cuki.gui;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;

import net.miginfocom.swing.MigLayout;

import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;

import cuki.proc.KModbus;
import cuki.proc.Mapa;

@SuppressWarnings("serial")
public class Laminas extends JPanel implements ActionListener {

	private KModbus m_k = null;

	private JComboBox<String> comboBoxNrSetores = null;
	@SuppressWarnings("rawtypes")
	private JComboBox[] comboSetor = null;

	private int novoNrSetores = 0;
	private int m_addr;

	private String m_port;

	private String[] nrSetoresStr = { "1", "2", "3", "4", "5", "6" };
	private String[] porcentagemStr = { "10", "20", "30", "40", "50", "60",
			"70", "80", "90", "100" };

	public Laminas(String port, int addr, Font font) {

		m_port = port;
		m_addr = addr;

		setLayout(new MigLayout("",
				"[grow,center][grow,center][grow,center][grow,center]",
				"[grow,center][grow,center][grow,center][grow,center][grow,center]"));

		JLabel lblNrsetores = new JLabel("nrSetores");
		add(lblNrsetores);

		comboBoxNrSetores = new JComboBox<String>(nrSetoresStr);
		comboBoxNrSetores.addActionListener(this);
		add(comboBoxNrSetores, "wrap,growx");

		JLabel lbl = new JLabel("Setor");
		add(lbl);

		lbl = new JLabel("L\u00E2mina");
		add(lbl);

		lbl = new JLabel("Setor");
		add(lbl);

		lbl = new JLabel("L\u00E2mina");
		add(lbl, "wrap");

		lbl = new JLabel("Setor 1");
		add(lbl);

		comboSetor = new JComboBox[6];

		comboSetor[0] = new JComboBox<String>(porcentagemStr);
		comboSetor[0].addActionListener(this);
		add(comboSetor[0], "growx");

		lbl = new JLabel("Setor 2");
		add(lbl);

		comboSetor[1] = new JComboBox<String>(porcentagemStr);
		comboSetor[1].addActionListener(this);
		add(comboSetor[1], "wrap,growx");

		lbl = new JLabel("Setor 3");
		add(lbl);

		comboSetor[2] = new JComboBox<String>(porcentagemStr);
		comboSetor[2].addActionListener(this);
		add(comboSetor[2], "growx");

		lbl = new JLabel("Setor 4");
		add(lbl);

		comboSetor[3] = new JComboBox<String>(porcentagemStr);
		comboSetor[3].addActionListener(this);
		add(comboSetor[3], "wrap,growx");

		lbl = new JLabel("Setor 5");
		add(lbl);

		comboSetor[4] = new JComboBox<String>(porcentagemStr);
		comboSetor[4].addActionListener(this);
		add(comboSetor[4], "growx");

		lbl = new JLabel("Setor 6");
		add(lbl);

		comboSetor[5] = new JComboBox<String>(porcentagemStr);
		comboSetor[5].addActionListener(this);
		add(comboSetor[5], "growx");
	}

	public void sync() {

		m_k = new KModbus(m_port, m_addr);

		int nrSetoreAux = m_k.getInt(Mapa.nrSetores);

		for (int i = 0; i < comboSetor.length; ++i)
			comboSetor[i].setEnabled(i < nrSetoreAux);

		comboBoxNrSetores.setSelectedIndex(nrSetoreAux - 1);

	}

	@Override
	public void actionPerformed(ActionEvent e) {

		@SuppressWarnings("rawtypes")
		JComboBox cp = (JComboBox) e.getSource();
		String aux = (String) cp.getSelectedItem();

		if (e.getSource() == comboBoxNrSetores) {
			novoNrSetores = Integer.valueOf(aux);
			System.out.println(novoNrSetores);
		} else if (e.getSource() == comboSetor[0]) {
			int p1 = (Integer) Integer.valueOf(aux);
			System.out.println(p1);
		} else if (e.getSource() == comboSetor[1]) {
			int p2 = (Integer) Integer.valueOf(aux);
			System.out.println(p2);
		} else if (e.getSource() == comboSetor[2]) {
			int p3 = (Integer) Integer.valueOf(aux);
			System.out.println(p3);
		} else if (e.getSource() == comboSetor[3]) {
			int p4 = (Integer) Integer.valueOf(aux);
			System.out.println(p4);
		} else if (e.getSource() == comboSetor[4]) {
			int p5 = (Integer) Integer.valueOf(aux);
			System.out.println(p5);
		} else if (e.getSource() == comboSetor[5]) {
			int p6 = (Integer) Integer.valueOf(aux);
			System.out.println(p6);
		}
	}

	public static void main(String[] args) {
		JFrame frame = new JFrame();

		Laminas l = new Laminas("COM8", 1, new Font("Lucida Console",
				Font.PLAIN, 18));

		frame.setContentPane(l);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.pack();
		frame.setVisible(true);

		l.sync();
	}
}
