package cuki.gui;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.SwingConstants;

import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

@SuppressWarnings("serial")
public class Home extends JFrame implements ActionListener {

	private static Home home;

	private JPanel contentPane;

	private static JButton btnIrrigar;
	private static JButton btnLamina;
	private static JButton btnSetores;
	private static JButton btnDeslocamento;
	private static JButton btnProgramador;
	private static JButton btnConfig;
	private static JButton btnAdm;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					home = new Home();
					home.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public Home() {

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 365);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

		btnIrrigar = new JButton("Irrigar");
		btnIrrigar.setIcon(new ImageIcon(Home.class
				.getResource("/ico/rain18.png")));
		btnIrrigar.setSelectedIcon(new ImageIcon(Home.class
				.getResource("/ico/nrain18.png")));

		Font font = new Font("Tahoma", Font.PLAIN, 16);

		btnIrrigar.addActionListener(this);
		btnIrrigar.setVerticalTextPosition(SwingConstants.BOTTOM);
		btnIrrigar.setHorizontalTextPosition(SwingConstants.CENTER);
		btnIrrigar.setFont(font);
		btnIrrigar.setBackground(Color.WHITE);
		contentPane.add(btnIrrigar);

		btnLamina = new JButton("L\u00E2minas");
		btnLamina.setSelectedIcon(new ImageIcon(Home.class
				.getResource("/ico/nshower5.png")));
		btnLamina.addActionListener(this);
		btnLamina.setFont(font);
		btnLamina.setHorizontalTextPosition(SwingConstants.CENTER);
		btnLamina.setVerticalTextPosition(SwingConstants.BOTTOM);
		btnLamina.setIcon(new ImageIcon(Home.class
				.getResource("/ico/shower5.png")));
		btnLamina.setBackground(Color.WHITE);
		contentPane.add(btnLamina);

		btnSetores = new JButton("Setores & Canh\u00E3o");
		btnSetores.setSelectedIcon(new ImageIcon(Home.class
				.getResource("/ico/npie3.png")));
		btnSetores.setIcon(new ImageIcon(Home.class
				.getResource("/ico/pie3.png")));
		btnSetores.setVerticalTextPosition(SwingConstants.BOTTOM);
		btnSetores.setToolTipText("Inicializa / Pausa Irriga\u00E7\u00E3o");
		btnSetores.setHorizontalTextPosition(SwingConstants.CENTER);
		btnSetores.setForeground(Color.BLACK);
		btnSetores.setFont(font);
		btnSetores.setBackground(Color.WHITE);
		contentPane.add(btnSetores);

		btnDeslocamento = new JButton("Deslocamento a Seco");
		btnDeslocamento.setSelectedIcon(new ImageIcon(Home.class
				.getResource("/ico/ncircular138.png")));
		btnDeslocamento.setIcon(new ImageIcon(Home.class
				.getResource("/ico/circular138.png")));
		btnDeslocamento.setVerticalTextPosition(SwingConstants.BOTTOM);
		btnDeslocamento
				.setToolTipText("Inicializa / Pausa Irriga\u00E7\u00E3o");
		btnDeslocamento.setHorizontalTextPosition(SwingConstants.CENTER);
		btnDeslocamento.setForeground(Color.BLACK);
		btnDeslocamento.setFont(font);
		btnDeslocamento.setBackground(Color.WHITE);
		contentPane.add(btnDeslocamento);

		btnProgramador = new JButton("Programador");
		btnProgramador.setSelectedIcon(new ImageIcon(Home.class
				.getResource("/ico/ncalendar133.png")));
		btnProgramador.setIcon(new ImageIcon(Home.class
				.getResource("/ico/calendar133.png")));
		btnProgramador.setVerticalTextPosition(SwingConstants.BOTTOM);
		btnProgramador.setToolTipText("Inicializa / Pausa Irriga\u00E7\u00E3o");
		btnProgramador.setHorizontalTextPosition(SwingConstants.CENTER);
		btnProgramador.setForeground(Color.BLACK);
		btnProgramador.setFont(font);
		btnProgramador.setBackground(Color.WHITE);
		contentPane.add(btnProgramador);

		btnConfig = new JButton("Configura\u00E7\u00F5es");
		btnConfig.setSelectedIcon(new ImageIcon(Home.class
				.getResource("/ico/nsettings3.png")));
		btnConfig.setIcon(new ImageIcon(Home.class
				.getResource("/ico/settings3.png")));
		btnConfig.setVerticalTextPosition(SwingConstants.BOTTOM);
		btnConfig.setToolTipText("Inicializa / Pausa Irriga\u00E7\u00E3o");
		btnConfig.setHorizontalTextPosition(SwingConstants.CENTER);
		btnConfig.setForeground(Color.BLACK);
		btnConfig.setFont(font);
		btnConfig.setBackground(Color.WHITE);
		contentPane.add(btnConfig);

		btnAdm = new JButton("Administra\u00E7\u00E3o T\u00E9cnica");
		btnAdm.setSelectedIcon(new ImageIcon(Home.class
				.getResource("/ico/nrain15.png")));
		btnAdm.setIcon(new ImageIcon(Home.class.getResource("/ico/rain15.png")));
		btnAdm.setVerticalTextPosition(SwingConstants.BOTTOM);
		btnAdm.setToolTipText("Inicializa / Pausa Irriga\u00E7\u00E3o");
		btnAdm.setHorizontalTextPosition(SwingConstants.CENTER);
		btnAdm.setForeground(Color.BLACK);
		btnAdm.setFont(font);
		btnAdm.setBackground(Color.WHITE);
		contentPane.add(btnAdm);
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		int tipoPainel = 0;

		if (e.getSource() == btnIrrigar)
			tipoPainel = MasterFrame.panelIrrigar;
		else if (e.getSource() == btnLamina)
			tipoPainel = MasterFrame.panellaminas;

		MasterFrame mf = new MasterFrame(home, "COM1", 1, tipoPainel);
		mf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mf.pack();
		mf.setVisible(true);
		mf.start();
	}
}
