package cuki.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;

import net.miginfocom.swing.MigLayout;

import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;

@SuppressWarnings("serial")
public class Laminas extends JPanel implements ActionListener {

	private JComboBox<String> comboBoxNrSetores = null;
	private JComboBox<String> comboSetor1 = null;
	private JComboBox<String> comboSetor2 = null;
	private JComboBox<String> comboSetor3 = null;
	private JComboBox<String> comboSetor4 = null;
	private JComboBox<String> comboSetor5 = null;
	private JComboBox<String> comboSetor6 = null;

	private int novoNrSetores = 0;

	private String[] nrSetoresStr = { "1", "2", "3", "4", "5", "6" };
	private String[] porcentagemStr = { "10", "20", "30", "40", "50", "60",
			"70", "80", "90", "100" };

	public Laminas() {
		setLayout(new MigLayout("", "[grow,center][grow,center]",
				"[grow,center][grow,center][grow,center][grow,center][grow,center]"));

		JLabel lblNrsetores = new JLabel("nrSetores");
		add(lblNrsetores);

		comboBoxNrSetores = new JComboBox<String>(nrSetoresStr);
		comboBoxNrSetores.addActionListener(this);
		add(comboBoxNrSetores, "wrap,growx");

		JLabel lbl = new JLabel("Setor");
		add(lbl);

		lbl = new JLabel("L\u00E2mina");
		add(lbl, "wrap");

		lbl = new JLabel("Setor 1");
		add(lbl);

		comboSetor1 = new JComboBox<String>(porcentagemStr);
		comboSetor1.addActionListener(this);
		add(comboSetor1, "wrap,growx");

		lbl = new JLabel("Setor 2");
		add(lbl);

		comboSetor2 = new JComboBox<String>(porcentagemStr);
		comboSetor2.addActionListener(this);
		add(comboSetor2, "wrap,growx");

		lbl = new JLabel("Setor 3");
		add(lbl);

		comboSetor3 = new JComboBox<String>(porcentagemStr);
		comboSetor3.addActionListener(this);
		add(comboSetor3, "wrap,growx");

		lbl = new JLabel("Setor 4");
		add(lbl);

		comboSetor4 = new JComboBox<String>(porcentagemStr);
		comboSetor4.addActionListener(this);
		add(comboSetor4, "wrap,growx");

		lbl = new JLabel("Setor 5");
		add(lbl);

		comboSetor5 = new JComboBox<String>(porcentagemStr);
		comboSetor5.addActionListener(this);
		add(comboSetor5, "wrap,growx");

		lbl = new JLabel("Setor 6");
		add(lbl);

		comboSetor6 = new JComboBox<String>(porcentagemStr);
		comboSetor6.addActionListener(this);
		add(comboSetor6, "wrap,growx");
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		@SuppressWarnings("rawtypes")
		JComboBox cp = (JComboBox) e.getSource();
		String aux = (String) cp.getSelectedItem();

		if (e.getSource() == comboBoxNrSetores) {
			novoNrSetores = Integer.valueOf(aux);
			System.out.println(novoNrSetores);
		} else if (e.getSource() == comboSetor1) {
			int p1 = (Integer) Integer.valueOf(aux);
			System.out.println(p1);

		} else if (e.getSource() == comboSetor2) {
			int p1 = (Integer) Integer.valueOf(aux);
			System.out.println(p1);
		} else if (e.getSource() == comboSetor3) {
			int p1 = (Integer) Integer.valueOf(aux);
			System.out.println(p1);

		} else if (e.getSource() == comboSetor4) {
			int p1 = (Integer) Integer.valueOf(aux);
			System.out.println(p1);
		} else if (e.getSource() == comboSetor5) {
			int p1 = (Integer) Integer.valueOf(aux);
			System.out.println(p1);

		} else if (e.getSource() == comboSetor6) {
			int p1 = (Integer) Integer.valueOf(aux);
			System.out.println(p1);
		}
	}

	public static void main(String[] args) {
		JFrame frame = new JFrame();

		Laminas l = new Laminas();

		frame.setContentPane(l);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.pack();
		frame.setVisible(true);
	}
}
