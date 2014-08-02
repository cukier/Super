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

	private int m_addr = 0;
	private int m_nrSeotres = 0;
	private int m_nrSeotresAux = 0;
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

		System.out.println(toString());

		setLayout(new MigLayout(
				"",
				"[grow,center]10[grow,center]10[grow,center]30[grow,center]10[grow,center]10[grow,center]",
				"[grow,center][grow,center][grow,center][grow,center][grow,center]"));

		JLabel lbl = new JLabel("nrSetores");
		lbl.setFont(font);
		add(lbl);

		comboBoxNrSetores = new JComboBox<String>(nrSetoresStr);
		comboBoxNrSetores.setFont(font);
		comboBoxNrSetores.setSelectedItem(String.valueOf(m_nrSeotres));
		comboBoxNrSetores.addActionListener(this);
		add(comboBoxNrSetores, "wrap");

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

		for (int i = 0; i < comboSetor.length; ++i) {
			comboSetor[i] = new JComboBox<String>(porcentagemStr);
			comboSetor[i].setEnabled(i < m_nrSeotres);
			comboSetor[i].setFont(font);
			int aux = (int) (m_porcento[i] / 10) - 1;
			if (aux > 0 && aux < porcentagemStr.length) {
				comboSetor[i].setSelectedIndex(aux);
			}
			comboSetor[i].addActionListener(this);
		}

		for (int i = 0; i < tfAngulo.length; ++i) {
			tfAngulo[i] = new JTextField();
			tfAngulo[i].setFont(font);
			tfAngulo[i].setText(String.valueOf(m_angulo[i]) + "�");
			tfAngulo[i].addActionListener(this);
		}

		add(comboSetor[0], "growx");

		add(tfAngulo[0], "w 50");

		lbl = new JLabel("Setor 2");
		lbl.setFont(font);
		add(lbl);

		add(comboSetor[1], "growx");

		add(tfAngulo[1], "w 50,wrap");

		lbl = new JLabel("Setor 3");
		lbl.setFont(font);
		add(lbl);

		add(comboSetor[2], "growx");

		add(tfAngulo[2], "w 50");

		lbl = new JLabel("Setor 4");
		lbl.setFont(font);
		add(lbl);

		add(comboSetor[3], "growx");

		add(tfAngulo[3], "w 50,wrap");

		lbl = new JLabel("Setor 5");
		lbl.setFont(font);
		add(lbl);

		add(comboSetor[4], "growx");

		add(tfAngulo[4], "w 50");

		lbl = new JLabel("Setor 6");
		lbl.setFont(font);
		add(lbl);

		add(comboSetor[5], "growx");

		add(tfAngulo[5], "w 50,wrap");

		btnSend = new JButton("Enviar");
		btnSend.addActionListener(this);
		btnSend.setBackground(Color.WHITE);
		btnSend.setFont(font);
		add(btnSend, "span,right");
	}

	public void actionPerformed(ActionEvent e) {

		if (e.getSource() instanceof JComboBox) {

			@SuppressWarnings("rawtypes")
			JComboBox cp = (JComboBox) e.getSource();
			String aux = (String) cp.getSelectedItem();
			m_k = new KModbus(m_port, m_addr);
			try {
				if (e.getSource() == comboBoxNrSetores) {
					m_nrSeotres = Integer.valueOf(aux);
					m_k.send(Mapa.nrSetores, m_nrSeotres);
				} else if (e.getSource() == comboSetor[0]) {
					m_porcento[0] = (Integer) Integer.valueOf(aux);
					m_k.send(Mapa.porcento6, m_porcento[0]);
				} else if (e.getSource() == comboSetor[1]) {
					m_porcento[1] = (Integer) Integer.valueOf(aux);
					m_k.send(Mapa.porcento6 + 1, m_porcento[1]);
				} else if (e.getSource() == comboSetor[2]) {
					m_porcento[2] = (Integer) Integer.valueOf(aux);
					m_k.send(Mapa.porcento6 + 2, m_porcento[2]);
				} else if (e.getSource() == comboSetor[3]) {
					m_porcento[3] = (Integer) Integer.valueOf(aux);
					m_k.send(Mapa.porcento6 + 3, m_porcento[3]);
				} else if (e.getSource() == comboSetor[4]) {
					m_porcento[4] = (Integer) Integer.valueOf(aux);
					m_k.send(Mapa.porcento6 + 4, m_porcento[4]);
				} else if (e.getSource() == comboSetor[5]) {
					m_porcento[5] = (Integer) Integer.valueOf(aux);
					m_k.send(Mapa.porcento6 + 5, m_porcento[5]);
				}
			} catch (Exception ex) {
				ex.printStackTrace();
			} finally {
				m_k = null;
			}
		} else if (e.getSource() instanceof JTextField) {
			JTextField tf = (JTextField) e.getSource();
			String aux = (String) tf.getText();

			m_k = new KModbus(m_port, m_addr);
			try {
				for (int i = 0; i < tfAngulo.length; ++i)
					if (e.getSource() == tfAngulo[i]) {
						m_angulo[i] = Integer.valueOf(aux);
						m_k.send(Mapa.angulo6 + i, Integer.valueOf(aux));
					}
			} catch (Exception ex) {
				ex.printStackTrace();
			} finally {
				m_k = null;
			}
		} else if (e.getSource() instanceof JButton) {

			// m_k = new KModbus(m_port, m_addr);
			//
			// try {
			// m_k.send(Mapa.nrSetores, m_nrSeotres);
			// m_k.send(Mapa.porcento6, m_porcento);
			// m_k.send(Mapa.angulo6, m_angulo);
			// } catch (Exception ex) {
			// ex.printStackTrace();
			// } finally {
			// m_k = null;
			// }
			//
			// for (int i = 0; i < comboSetor.length; ++i)
			// comboSetor[i].setEnabled(i < m_nrSeotres);
		}

		m_k = new KModbus(m_port, m_addr);

		try {
			m_nrSeotres = m_k.getInt(Mapa.nrSetores);
			m_porcento = m_k.read(Mapa.porcento6, 6);
			m_angulo = m_k.read(Mapa.angulo6, 6);
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			m_k = null;
		}

		if (m_nrSeotres != m_nrSeotresAux) {
			m_nrSeotresAux = m_nrSeotres;
			for (int i = 0; i < comboSetor.length; ++i) {
				comboSetor[i].setEnabled(i < m_nrSeotres);
			}
		}

		toString();
	}

	@Override
	public String toString() {

		StringBuffer sb = new StringBuffer();

		sb.append("Porta: " + m_port + " add: " + m_addr + " nrSetores: "
				+ m_nrSeotres);
		for (int i : m_porcento)
			sb.append(" " + i + " %");
		for (int i : m_angulo)
			sb.append(" " + i + "�");

		return sb.toString();
	}

	public static void main(String[] args) {
		JFrame frame = new JFrame();

		Laminas l = new Laminas("COM8", 1, new Font("Lucida Console",
				Font.PLAIN, 18));

		frame.setContentPane(l);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.pack();
		frame.setVisible(true);
	}
}
