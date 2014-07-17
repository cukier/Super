package cuki.gui;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import net.miginfocom.swing.MigLayout;
import cuki.proc.KModbus;
import cuki.proc.Mapa;

import javax.swing.JLabel;

@SuppressWarnings("serial")
public class Irrigar extends JPanel {

	private Pizza pizza = null;

	private JLabel lblEstado;
	private JLabel lblSetor;
	private JLabel lblLmina;
	private JLabel lblTempo;

	public Irrigar() {

		setLayout(new MigLayout());

		pizza = new Pizza();
		add(pizza, "span 1 4");

		String str = "No Conn";

		lblEstado = new JLabel(str);
		add(lblEstado, "wrap");

		lblSetor = new JLabel(str);
		add(lblSetor, "wrap");

		lblLmina = new JLabel(str);
		add(lblLmina, "wrap");

		lblTempo = new JLabel(str);
		add(lblTempo, "wrap");
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

	public void sync(final KModbus m_k) {

		int[] resp = m_k.read(Mapa.horasRestantes, 8);

		pizza.setAnguloAtual(resp[3]);
		lblEstado.setText(setLblEstado(resp[5]));
		lblSetor.setText("Setor : " + resp[2] + " / " + resp[4]);
		lblLmina.setText("Lâmina : " + resp[6] + "mm - " + resp[7] + "%");
		lblTempo.setText("Tempo : " + resp[0] + "h " + resp[1] + "min");

		repaint();
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
