package cuki.gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;

import java.awt.Dimension;
import java.awt.FlowLayout;
import net.miginfocom.swing.MigLayout;

import java.awt.Component;

import javax.swing.SwingConstants;
import java.awt.Font;

@SuppressWarnings("serial")
public class Irrigar extends JPanel {

	private boolean debug = true;

	private Pizza pizza = null;
	private JLabel lblEstado = null;
	private JLabel lblSetor = null;
	private JLabel lblLmina = null;
	private JLabel lblTempo = null;

	public Irrigar() {
		setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

		pizza = new Pizza();
		add(pizza);

		JPanel panel = new JPanel();
		panel.setPreferredSize(pizza.getPreferredSize());
		add(panel);
		panel.setLayout(new MigLayout("", "[]", "[][][][]"));

		lblEstado = new JLabel("Conectando...");
		lblEstado.setFont(new Font("Lucida Console", Font.PLAIN, 22));
		lblEstado.setHorizontalTextPosition(SwingConstants.CENTER);
		lblEstado.setHorizontalAlignment(SwingConstants.CENTER);
		lblEstado.setAlignmentX(Component.CENTER_ALIGNMENT);
		lblEstado.setMinimumSize(new Dimension(pizza.getPreferredSize().width,
				pizza.getPreferredSize().height / 5));
		panel.add(lblEstado, "cell 0 0,grow");

		lblSetor = new JLabel("Conectando...");
		lblSetor.setFont(new Font("Lucida Console", Font.PLAIN, 22));
		lblSetor.setHorizontalTextPosition(SwingConstants.CENTER);
		lblSetor.setHorizontalAlignment(SwingConstants.CENTER);
		lblSetor.setAlignmentX(Component.CENTER_ALIGNMENT);
		lblSetor.setMinimumSize(new Dimension(pizza.getPreferredSize().width,
				pizza.getPreferredSize().height / 5));
		panel.add(lblSetor, "flowy,cell 0 1,grow");

		lblLmina = new JLabel("Conectando...");
		lblLmina.setFont(new Font("Lucida Console", Font.PLAIN, 22));
		lblLmina.setHorizontalTextPosition(SwingConstants.CENTER);
		lblLmina.setHorizontalAlignment(SwingConstants.CENTER);
		lblLmina.setAlignmentX(Component.CENTER_ALIGNMENT);
		lblLmina.setMinimumSize(new Dimension(pizza.getPreferredSize().width,
				pizza.getPreferredSize().height / 5));
		panel.add(lblLmina, "cell 0 2,grow");

		lblTempo = new JLabel("Conectando...");
		lblTempo.setFont(new Font("Lucida Console", Font.PLAIN, 22));
		lblTempo.setHorizontalTextPosition(SwingConstants.CENTER);
		lblTempo.setHorizontalAlignment(SwingConstants.CENTER);
		lblTempo.setAlignmentX(Component.CENTER_ALIGNMENT);
		lblTempo.setMinimumSize(new Dimension(pizza.getPreferredSize().width,
				pizza.getPreferredSize().height / 5));
		panel.add(lblTempo, "cell 0 3,grow");

	}

	private String setLblEstado(int estado) {
		String ret = "";
		switch (estado) {
		case 0:
			ret = "Estado : Parado";
			break;
		case 1:
			ret = "Estado : Motobomba";
			break;
		case 2:
			ret = "Estado : Pressurizando";
			break;
		case 3:
			ret = "Estado : Irrigando";
			break;
		case 4:
			ret = "Estado : Pico Energético";
			break;
		case 5:
			ret = "Estado : Alarme Pressão";
			break;
		case 6:
			ret = "Estado : Fim de Irrigação";
			break;
		case 7:
			ret = "Estado : Movimentando";
			break;
		case 8:
			ret = "Estado : Alarme Alinhamento";
			break;
		case 9:
			ret = "Estado : Painel no Manual";
			break;
		default:
			ret = "Estado : Desconhecido";
		}
		return ret;
	}

	public void sync(int[] resp) {

		int[] anguloAux = new int[4];
		for (int i = 0; i < 4; ++i)
			anguloAux[i] = resp[i];

		pizza.setNrSetores(resp[10]);
		pizza.setAnguloFInalSetores(anguloAux);
		pizza.setAnguloAtual(resp[9]);
		lblEstado.setText(setLblEstado(resp[11]));
		lblSetor.setText("Setor : " + resp[8] + " / " + resp[10]);
		lblLmina.setText("Lâmina : " + resp[12] + "mm - " + resp[13] + "%");
		lblTempo.setText("Tempo : " + resp[6] + "h " + resp[7] + "min");

		if (debug) {
			for (int i = 0; i < anguloAux.length; ++i)
				System.out.println("Setor Angulo" + i + " : " + anguloAux[i]);
			System.out.println("horas restantes: " + resp[6]);
			System.out.println("minutos restantes: " + resp[7]);
			System.out.println("setor atual: " + resp[8]);
			System.out.println("angulo atual: " + resp[9]);
			System.out.println("nr setores: " + resp[10]);
			System.out.println("estado irrigacao: " + resp[11] + " = "
					+ setLblEstado(resp[11]));
			System.out.println("lamina aplicada: " + resp[12]);
			System.out.println("porcentagem aplicada: " + resp[13]);
		}
	}

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				JFrame frame = new JFrame();

				Irrigar i = new Irrigar();

				frame.setContentPane(i);
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				frame.pack();
				frame.setVisible(true);
			}
		});
	}
}
