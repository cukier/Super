package cuki.gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;

import net.miginfocom.swing.MigLayout;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import cuki.proc.KModbus;
import cuki.proc.Mapa;

@SuppressWarnings("serial")
public class Laminas extends JPanel implements ActionListener {

	private KModbus m_k = null;

	private JComboBox<String> comboBoxNrSetores = null;
	@SuppressWarnings("rawtypes")
	private JComboBox[] comboSetor = null;

	private JTextField[] tfAngulo = null;

	private JButton btnSend = null;

	private int m_addr;
	private int m_nrSeotres;
	private int[] m_porcento = new int[6];
	private int[] m_angulo = new int[6];

	private String m_port;

	private String[] nrSetoresStr = { "1", "2", "3", "4", "5", "6" };
	private String[] porcentagemStr = { "10", "20", "30", "40", "50", "60",
			"70", "80", "90", "100" };

	public Laminas(String port, int addr, Font font) {

		m_port = port;
		m_addr = addr;

		m_k = new KModbus(m_port, m_addr);

		try {
			m_nrSeotres = m_k.getInt(Mapa.nrSetores);
			m_porcento = m_k.read(Mapa.porcento6, 6);
			m_angulo = m_k.read(Mapa.angulo6, 6);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			m_k = null;
		}

		setLayout(new MigLayout(
				"",
				"[grow,center]10[grow,center]10[grow,center]30[grow,center]10[grow,center]10[grow,center]",
				"[grow,center][grow,center][grow,center][grow,center][grow,center]"));

		JLabel lbl = new JLabel("nrSetores");
		lbl.setFont(font);
		add(lbl);

		comboBoxNrSetores = new JComboBox<String>(nrSetoresStr);
		comboBoxNrSetores.setFont(font);
		// comboBoxNrSetores.addActionListener(this);
		add(comboBoxNrSetores, "wrap,growx");

		lbl = new JLabel("Setor");
		lbl.setFont(font);
		add(lbl);

		lbl = new JLabel("L\u00E2mina");
		lbl.setFont(font);
		add(lbl);

		lbl = new JLabel("Angulo");
		lbl.setFont(font);
		add(lbl);

		lbl = new JLabel("Setor");
		lbl.setFont(font);
		add(lbl);

		lbl = new JLabel("L\u00E2mina");
		lbl.setFont(font);
		add(lbl);

		lbl = new JLabel("Angulo");
		lbl.setFont(font);
		add(lbl, "wrap");

		comboSetor = new JComboBox[6];
		tfAngulo = new JTextField[6];

		lbl = new JLabel("Setor 1");
		lbl.setFont(font);
		add(lbl);

		comboSetor[0] = new JComboBox<String>(porcentagemStr);
		comboSetor[0].setFont(font);
		comboSetor[0].addActionListener(this);
		add(comboSetor[0], "growx");

		tfAngulo[0] = new JTextField(m_angulo[0]);
		tfAngulo[0].setFont(font);
		tfAngulo[0].setText(String.valueOf(m_angulo[0]));
		tfAngulo[0].addActionListener(this);
		add(tfAngulo[0], "w 50");

		lbl = new JLabel("Setor 2");
		lbl.setFont(font);
		add(lbl);

		comboSetor[1] = new JComboBox<String>(porcentagemStr);
		comboSetor[1].setFont(font);
		comboSetor[1].addActionListener(this);
		add(comboSetor[1], "growx");

		tfAngulo[1] = new JTextField(m_angulo[1]);
		tfAngulo[1].setFont(font);
		tfAngulo[1].setText(String.valueOf(m_angulo[1]));
		tfAngulo[1].addActionListener(this);
		add(tfAngulo[1], "w 50,wrap");

		lbl = new JLabel("Setor 3");
		lbl.setFont(font);
		add(lbl);

		comboSetor[2] = new JComboBox<String>(porcentagemStr);
		comboSetor[2].setFont(font);
		comboSetor[2].addActionListener(this);
		add(comboSetor[2], "growx");

		tfAngulo[2] = new JTextField(m_angulo[2]);
		tfAngulo[2].setFont(font);
		tfAngulo[2].setText(String.valueOf(m_angulo[2]));
		tfAngulo[2].addActionListener(this);
		add(tfAngulo[2], "w 50");

		lbl = new JLabel("Setor 4");
		lbl.setFont(font);
		add(lbl);

		comboSetor[3] = new JComboBox<String>(porcentagemStr);
		comboSetor[3].setFont(font);
		comboSetor[3].addActionListener(this);
		add(comboSetor[3], "growx");

		tfAngulo[3] = new JTextField(m_angulo[3]);
		tfAngulo[3].setFont(font);
		tfAngulo[3].setText(String.valueOf(m_angulo[3]));
		tfAngulo[3].addActionListener(this);
		add(tfAngulo[3], "w 50,wrap");

		lbl = new JLabel("Setor 5");
		lbl.setFont(font);
		add(lbl);

		comboSetor[4] = new JComboBox<String>(porcentagemStr);
		comboSetor[4].setFont(font);
		comboSetor[4].addActionListener(this);
		add(comboSetor[4], "growx");

		tfAngulo[4] = new JTextField(m_angulo[4]);
		tfAngulo[4].setFont(font);
		tfAngulo[4].setText(String.valueOf(m_angulo[4]));
		tfAngulo[4].addActionListener(this);
		add(tfAngulo[4], "w 50");

		lbl = new JLabel("Setor 6");
		lbl.setFont(font);
		add(lbl);

		comboSetor[5] = new JComboBox<String>(porcentagemStr);
		comboSetor[5].setFont(font);
		comboSetor[5].addActionListener(this);
		add(comboSetor[5], "growx");

		tfAngulo[5] = new JTextField(m_angulo[5]);
		tfAngulo[5].setFont(font);
		tfAngulo[5].setText(String.valueOf(m_angulo[5]));
		tfAngulo[5].addActionListener(this);
		add(tfAngulo[5], "w 50,wrap");

		btnSend = new JButton("Enviar");
		btnSend.addActionListener(this);
		btnSend.setBackground(Color.WHITE);
		btnSend.setFont(font);
		add(btnSend, "span,right");
	}

	public void sync() {

		m_k = new KModbus(m_port, m_addr);

		try {
			m_nrSeotres = m_k.getInt(Mapa.nrSetores);
			m_porcento = m_k.read(Mapa.porcento6, 6);
			m_angulo = m_k.read(Mapa.angulo6, 6);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			m_k = null;
		}

		for (int i = 0; i < comboSetor.length; ++i) {
			comboSetor[i].setEnabled(i < m_nrSeotres);
			int aux = (int) (m_porcento[i] / 10) - 1;
			if (aux > 0 && aux < porcentagemStr.length) {
				comboSetor[i].setSelectedIndex(aux);
			}
		}

		comboBoxNrSetores.setSelectedIndex(m_nrSeotres - 1);
		comboBoxNrSetores.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		if (e.getSource() instanceof JComboBox) {

			@SuppressWarnings("rawtypes")
			JComboBox cp = (JComboBox) e.getSource();
			String aux = (String) cp.getSelectedItem();

			if (e.getSource() == comboBoxNrSetores) {
				m_nrSeotres = Integer.valueOf(aux);
			} else if (e.getSource() == comboSetor[0]) {
				m_porcento[0] = (Integer) Integer.valueOf(aux);
			} else if (e.getSource() == comboSetor[1]) {
				m_porcento[1] = (Integer) Integer.valueOf(aux);
			} else if (e.getSource() == comboSetor[2]) {
				m_porcento[2] = (Integer) Integer.valueOf(aux);
			} else if (e.getSource() == comboSetor[3]) {
				m_porcento[3] = (Integer) Integer.valueOf(aux);
			} else if (e.getSource() == comboSetor[4]) {
				m_porcento[4] = (Integer) Integer.valueOf(aux);
			} else if (e.getSource() == comboSetor[5]) {
				m_porcento[5] = (Integer) Integer.valueOf(aux);
			}
		} else if (e.getSource() instanceof JTextField) {
			JTextField tf = (JTextField) e.getSource();
			String aux = (String) tf.getText();
			if (e.getSource() == tfAngulo[0]) {
				m_angulo[0] = (Integer) Integer.valueOf(aux);
			}
		} else if (e.getSource() instanceof JButton) {

			m_k = new KModbus(m_port, m_addr);

			try {
				m_k.send(Mapa.nrSetores, m_nrSeotres);
				System.out.println("Escrito " + m_nrSeotres + " em "
						+ Mapa.nrSetores);
				m_k.send(Mapa.porcento6, m_porcento);
				for (int i : m_porcento)
					System.out.println("Escrito % " + i);
				m_k.send(Mapa.angulo6, m_angulo);
				for (int i : m_angulo)
					System.out.println("Escrito ° " + i);
			} catch (Exception ex) {
				ex.printStackTrace();
			} finally {
				m_k = null;
			}

			for (int i = 0; i < comboSetor.length; ++i)
				comboSetor[i].setEnabled(i < m_nrSeotres);
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
