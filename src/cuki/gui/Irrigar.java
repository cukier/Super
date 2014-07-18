package cuki.gui;

import java.awt.Color;
import javax.swing.JPanel;
import javax.swing.JLabel;

import net.miginfocom.swing.MigLayout;

import java.awt.Font;

@SuppressWarnings("serial")
public class Irrigar extends JPanel {

	private boolean debug = false;

	private Pizza pizza = null;
	private JLabel lblEstado = null;
	private JLabel lblSetor = null;
	private JLabel lblLmina = null;
	private JLabel lblTempo = null;

	public Irrigar() {

		setLayout(new MigLayout("", "[300px,grow][300px,grow]", "[grow]"));

		pizza = new Pizza();
		pizza.setBackground(Color.WHITE);
		add(pizza, "cell 0 0,alignx center,aligny center");

		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		panel.setLayout(new MigLayout("", "[grow]", "[grow][grow][grow][grow]"));

		lblEstado = new JLabel("Conectando...");
		lblEstado.setFont(new Font("Lucida Console", Font.PLAIN, 18));
		panel.add(lblEstado, "cell 0 0,alignx center,aligny center");

		lblSetor = new JLabel("Conectando...");
		lblSetor.setFont(new Font("Lucida Console", Font.PLAIN, 18));
		panel.add(lblSetor, "flowy,cell 0 1,alignx center,aligny center");

		lblLmina = new JLabel("Conectando...");
		lblLmina.setFont(new Font("Lucida Console", Font.PLAIN, 18));
		panel.add(lblLmina, "cell 0 2,alignx center,aligny center");

		lblTempo = new JLabel("Conectando...");
		lblTempo.setFont(new Font("Lucida Console", Font.PLAIN, 18));
		panel.add(lblTempo, "cell 0 3,alignx center,aligny center");

		add(panel, "cell 1 0,grow");
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

		int[] anguloAux = new int[6];
		for (int i = 0; i < anguloAux.length; ++i)
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
}
