package cuki.gui;

import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;

import net.miginfocom.swing.MigLayout;

import java.awt.Font;

import javax.swing.JButton;
import javax.swing.ImageIcon;

import cuki.proc.ConnectionModbus;
import cuki.proc.KModbus;
import cuki.proc.Mapa;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

@SuppressWarnings("serial")
public class Irrigar extends JPanel {

	private KModbus m_k = null;
	private Pizza pizza = null;
	private JLabel lblEstado = null;
	private JLabel lblSetor = null;
	private JLabel lblLmina = null;
	private JLabel lblTempo = null;
	private JLabel lblAnguloAtual = null;
	private JPanel panel_1 = null;
	private JButton btnSentido = null;
	private JButton btnBInjetora = null;
	private JButton btnIrriga = null;
	private boolean debug = true;
	private boolean sentido = false;
	private boolean inicioIrriga = false;
	private boolean bInjetora = false;
	private Font font = null;

	public Irrigar(KModbus k) {
		m_k = k;

		setBackground(Color.WHITE);
		setLayout(new MigLayout("", "[566px]", "[][]"));

		pizza = new Pizza();
		pizza.setBackground(Color.WHITE);
		add(pizza, "cell 0 0,alignx center,aligny top");
		pizza.setLayout(null);

		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		panel.setLayout(new MigLayout("", "[grow]", "[grow][grow][grow][grow]"));

		font = new Font("Lucida Console", Font.PLAIN, 18);

		lblEstado = new JLabel("Conectando...");
		lblEstado.setFont(font);
		panel.add(lblEstado, "cell 0 0,alignx center,aligny center");

		lblSetor = new JLabel("Conectando...");
		lblSetor.setFont(font);
		panel.add(lblSetor, "flowy,cell 0 1,alignx center,aligny center");

		lblLmina = new JLabel("Conectando...");
		lblLmina.setFont(font);
		panel.add(lblLmina, "cell 0 2,alignx center,aligny center");

		lblTempo = new JLabel("Conectando...");
		lblTempo.setFont(font);
		panel.add(lblTempo, "cell 0 3,alignx center,aligny center");

		add(panel, "cell 0 0,grow");

		panel_1 = new JPanel();
		panel_1.setBackground(Color.WHITE);
		panel_1.setLayout(new MigLayout("", "[][][]", "[]"));

		// add(panel, "cell 0 1,span");
		add(panel_1, "cell 0 1");

		btnSentido = new JButton("");
		btnSentido.setSelectedIcon(new ImageIcon(Irrigar.class
				.getResource("/ico/clockwise1.png")));
		btnSentido.setIcon(new ImageIcon(Irrigar.class
				.getResource("/ico/circular2062.png")));
		btnSentido.setBackground(Color.WHITE);
		panel_1.add(btnSentido, "cell 0 0");

		lblAnguloAtual = new JLabel("New label");
		lblAnguloAtual.setFont(font);
		panel_1.add(lblAnguloAtual, "cell 1 0,alignx center,aligny center");

		btnBInjetora = new JButton("Bomba Injetora");
		btnBInjetora.setFont(font);
		btnBInjetora.setBackground(Color.WHITE);
		btnBInjetora.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				bInjetora = !bInjetora;
				m_k.sendBool(Mapa.bInjetora, Mapa.word1, bInjetora);
				if (bInjetora)
					btnBInjetora.setText("Desligar Bomba Injetora");
				else
					btnBInjetora.setText("Ligar Bomba Injetora");
				repaint();
			}
		});
		panel_1.add(btnBInjetora, "cell 2 0,alignx center,aligny center,wrap");

		btnIrriga = new JButton("Irrigar");
		btnIrriga.setFont(font);
		btnIrriga.setBackground(Color.WHITE);
		btnIrriga.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (inicioIrriga) {
					m_k.sendBool(Mapa.pararIrriga, Mapa.word0, true);
					btnIrriga.setText("Parar Irrigação");
					btnIrriga.setBackground(Color.RED);
				} else {
					m_k.sendBool(Mapa.inicioIrriga, Mapa.word0, true);
					btnIrriga.setText("Iniciar Irrigação");
					btnIrriga.setBackground(Color.WHITE);
				}
			}
		});
		panel_1.add(btnIrriga, "span");
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

	public void sync() {

		int[] word = m_k.read(0, 2);

		try {
			Thread.sleep(200);
		} catch (Exception e) {
			e.printStackTrace();
		}

		int[] resp = m_k.read(Mapa.irrigarPanel, Mapa.irrigarPanelLen);

		int[] anguloAux = new int[6];
		for (int i = 0; i < anguloAux.length; ++i)
			anguloAux[i] = resp[i];

		pizza.setNrSetores(resp[10]);
		pizza.setAnguloFInalSetores(anguloAux);
		pizza.setAnguloAtual(resp[9]);
		lblAnguloAtual.setText("Angulo Atual: " + String.valueOf(resp[9]));
		lblEstado.setText(setLblEstado(resp[11]));
		lblSetor.setText("Setor : " + resp[8] + " / " + resp[10]);
		lblLmina.setText("Lâmina : " + resp[12] + "mm - " + resp[13] + "%");
		lblTempo.setText("Tempo : " + resp[6] + "h " + resp[7] + "min");

		sentido = (word[0] & Mapa.sentdidoMask) == Mapa.sentdidoMask;
		inicioIrriga = (word[0] & Mapa.inicioMask) == Mapa.inicioMask;
		bInjetora = (word[1] & Mapa.bInjetoraMask) == Mapa.bInjetoraMask;

		if (inicioIrriga) {
			btnIrriga.setText("Parar Irrigação");
			btnIrriga.setBackground(Color.RED);
		} else {
			btnIrriga.setText("Iniciar Irrigação");
			btnIrriga.setBackground(Color.WHITE);
		}

		if (sentido) {
			btnSentido.setSelectedIcon(new ImageIcon(Irrigar.class
					.getResource("/ico/clockwise1.png")));
			btnSentido.setIcon(new ImageIcon(Irrigar.class
					.getResource("/ico/circular2062.png")));
		} else {
			btnSentido.setIcon(new ImageIcon(Irrigar.class
					.getResource("/ico/circular206.png")));
			btnSentido.setSelectedIcon(new ImageIcon(Irrigar.class
					.getResource("/ico/counterclockwise1.png")));
		}

		if (bInjetora)
			btnBInjetora.setText("Desligar Bomba Injetora");
		else
			btnBInjetora.setText("Ligar Bomba Injetora");

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
			System.out.println("inicioIrriga: " + inicioIrriga);
			System.out.println("sentido: " + sentido);
			System.out.println("bInjetora: " + bInjetora);
		}
	}

	public static void main(String[] args) {

		EventQueue.invokeLater(new Runnable() {

			@Override
			public void run() {

				JFrame frame = new JFrame();
				Irrigar contentPane = new Irrigar(new KModbus(
						new ConnectionModbus("COM8"), 1));

				frame.setContentPane(contentPane);
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				frame.pack();
				frame.setVisible(true);
			}
		});

	}
}
