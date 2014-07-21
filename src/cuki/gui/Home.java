package cuki.gui;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import java.awt.EventQueue;
import java.awt.FlowLayout;

import javax.swing.JButton;

import java.awt.Color;

import javax.swing.SwingConstants;

import cuki.proc.ConnectionModbus;

import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

@SuppressWarnings("serial")
public class Home extends JFrame {

	private JPanel contentPane;
	private static Home home;

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

		// try {
		// for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
		// if ("Nimbus".equals(info.getName())) {
		// UIManager.setLookAndFeel(info.getClassName());
		// break;
		// }
		// // System.out.println(info.getName());
		// }
		// } catch (Exception e) {
		// }

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 365);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

		JButton btnIrrigar = new JButton("Irrigar");
		btnIrrigar.setIcon(new ImageIcon(Home.class
				.getResource("/ico/rain18.png")));
		btnIrrigar.setSelectedIcon(new ImageIcon(Home.class
				.getResource("/ico/nrain18.png")));

		btnIrrigar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				MasterFrame mf = new MasterFrame(home, new ConnectionModbus(
						"COM1"), 1, MasterFrame.irrigar);
				mf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				mf.pack();
				mf.setVisible(true);
				mf.start();
			}
		});
		btnIrrigar.setVerticalTextPosition(SwingConstants.BOTTOM);
		btnIrrigar.setHorizontalTextPosition(SwingConstants.CENTER);
		btnIrrigar.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnIrrigar.setBackground(Color.WHITE);
		contentPane.add(btnIrrigar);

		JButton btnLamina = new JButton("L\u00E2minas");
		btnLamina.setSelectedIcon(new ImageIcon(Home.class
				.getResource("/ico/nshower5.png")));
		btnLamina.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				setVisible(false);
				// Laminas l = new Laminas(home);
				// l.setVisible(true);
			}
		});
		btnLamina.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnLamina.setHorizontalTextPosition(SwingConstants.CENTER);
		btnLamina.setVerticalTextPosition(SwingConstants.BOTTOM);
		btnLamina.setIcon(new ImageIcon(Home.class
				.getResource("/ico/shower5.png")));
		btnLamina.setBackground(Color.WHITE);
		contentPane.add(btnLamina);

		JButton btnSetores = new JButton("Setores & Canh\u00E3o");
		btnSetores.setSelectedIcon(new ImageIcon(Home.class
				.getResource("/ico/npie3.png")));
		btnSetores.setIcon(new ImageIcon(Home.class
				.getResource("/ico/pie3.png")));
		btnSetores.setVerticalTextPosition(SwingConstants.BOTTOM);
		btnSetores.setToolTipText("Inicializa / Pausa Irriga\u00E7\u00E3o");
		btnSetores.setHorizontalTextPosition(SwingConstants.CENTER);
		btnSetores.setForeground(Color.BLACK);
		btnSetores.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnSetores.setBackground(Color.WHITE);
		contentPane.add(btnSetores);

		JButton btnDeslocamento = new JButton("Deslocamento a Seco");
		btnDeslocamento.setSelectedIcon(new ImageIcon(Home.class
				.getResource("/ico/ncircular138.png")));
		btnDeslocamento.setIcon(new ImageIcon(Home.class
				.getResource("/ico/circular138.png")));
		btnDeslocamento.setVerticalTextPosition(SwingConstants.BOTTOM);
		btnDeslocamento
				.setToolTipText("Inicializa / Pausa Irriga\u00E7\u00E3o");
		btnDeslocamento.setHorizontalTextPosition(SwingConstants.CENTER);
		btnDeslocamento.setForeground(Color.BLACK);
		btnDeslocamento.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnDeslocamento.setBackground(Color.WHITE);
		contentPane.add(btnDeslocamento);

		JButton btnProgramador = new JButton("Programador");
		btnProgramador.setSelectedIcon(new ImageIcon(Home.class
				.getResource("/ico/ncalendar133.png")));
		btnProgramador.setIcon(new ImageIcon(Home.class
				.getResource("/ico/calendar133.png")));
		btnProgramador.setVerticalTextPosition(SwingConstants.BOTTOM);
		btnProgramador.setToolTipText("Inicializa / Pausa Irriga\u00E7\u00E3o");
		btnProgramador.setHorizontalTextPosition(SwingConstants.CENTER);
		btnProgramador.setForeground(Color.BLACK);
		btnProgramador.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnProgramador.setBackground(Color.WHITE);
		contentPane.add(btnProgramador);

		JButton btnConfig = new JButton("Configura\u00E7\u00F5es");
		btnConfig.setSelectedIcon(new ImageIcon(Home.class
				.getResource("/ico/nsettings3.png")));
		btnConfig.setIcon(new ImageIcon(Home.class
				.getResource("/ico/settings3.png")));
		btnConfig.setVerticalTextPosition(SwingConstants.BOTTOM);
		btnConfig.setToolTipText("Inicializa / Pausa Irriga\u00E7\u00E3o");
		btnConfig.setHorizontalTextPosition(SwingConstants.CENTER);
		btnConfig.setForeground(Color.BLACK);
		btnConfig.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnConfig.setBackground(Color.WHITE);
		contentPane.add(btnConfig);

		JButton btnAdm = new JButton("Administra\u00E7\u00E3o T\u00E9cnica");
		btnAdm.setSelectedIcon(new ImageIcon(Home.class
				.getResource("/ico/nrain15.png")));
		btnAdm.setIcon(new ImageIcon(Home.class.getResource("/ico/rain15.png")));
		btnAdm.setVerticalTextPosition(SwingConstants.BOTTOM);
		btnAdm.setToolTipText("Inicializa / Pausa Irriga\u00E7\u00E3o");
		btnAdm.setHorizontalTextPosition(SwingConstants.CENTER);
		btnAdm.setForeground(Color.BLACK);
		btnAdm.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnAdm.setBackground(Color.WHITE);
		contentPane.add(btnAdm);
	}
}
