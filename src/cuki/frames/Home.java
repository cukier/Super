package cuki.frames;

import java.awt.EventQueue;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.EmptyBorder;

import java.awt.FlowLayout;

import javax.swing.JButton;

import java.awt.Color;

import javax.swing.SwingConstants;

import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

@SuppressWarnings("serial")
public class Home extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Home frame = new Home();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * 
	 * 
	 */
	public Home() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 325);
		contentPane = new JPanel();
		contentPane.setForeground(Color.BLACK);
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

		for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
			if ("Nimbus".equals(info.getName())) {
				try {
					UIManager.setLookAndFeel(info.getClassName());
				} catch (ClassNotFoundException e) {
					e.printStackTrace();
				} catch (InstantiationException e) {
					e.printStackTrace();
				} catch (IllegalAccessException e) {
					e.printStackTrace();
				} catch (UnsupportedLookAndFeelException e) {
					e.printStackTrace();
				}
				break;
			}
		}

		JButton btnIrrigar = new JButton("Irrigar");
		btnIrrigar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				dispose();
				MasterFrame i = new MasterFrame();
				i.setVisible(true);
			}
		});
		btnIrrigar.setIcon(new ImageIcon(Home.class
				.getResource("/imgs/bt-irrigar.png")));
		btnIrrigar.setVerticalTextPosition(SwingConstants.BOTTOM);
		btnIrrigar.setHorizontalTextPosition(SwingConstants.CENTER);
		btnIrrigar.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnIrrigar.setBackground(Color.WHITE);
		contentPane.add(btnIrrigar);

		JButton btnLamina = new JButton("L\u00E2minas");
		btnLamina.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnLamina.setHorizontalTextPosition(SwingConstants.CENTER);
		btnLamina.setVerticalTextPosition(SwingConstants.BOTTOM);
		btnLamina.setIcon(new ImageIcon(Home.class
				.getResource("/imgs/bt-laminas.png")));
		btnLamina.setSelectedIcon(new ImageIcon(Home.class
				.getResource("/imgs/bt-laminas.png")));
		btnLamina.setBackground(Color.WHITE);
		contentPane.add(btnLamina);

		JButton btnSetores = new JButton("Setores & Canh\u00E3o");
		btnSetores.setIcon(new ImageIcon(Home.class
				.getResource("/imgs/bt-setores.png")));
		btnSetores.setVerticalTextPosition(SwingConstants.BOTTOM);
		btnSetores.setToolTipText("Inicializa / Pausa Irriga\u00E7\u00E3o");
		btnSetores.setHorizontalTextPosition(SwingConstants.CENTER);
		btnSetores.setForeground(Color.BLACK);
		btnSetores.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnSetores.setBackground(Color.WHITE);
		contentPane.add(btnSetores);

		JButton btnDeslocamento = new JButton("Deslocamento a Seco");
		btnDeslocamento.setIcon(new ImageIcon(Home.class
				.getResource("/imgs/bt-deslocamento.png")));
		btnDeslocamento.setVerticalTextPosition(SwingConstants.BOTTOM);
		btnDeslocamento
				.setToolTipText("Inicializa / Pausa Irriga\u00E7\u00E3o");
		btnDeslocamento.setHorizontalTextPosition(SwingConstants.CENTER);
		btnDeslocamento.setForeground(Color.BLACK);
		btnDeslocamento.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnDeslocamento.setBackground(Color.WHITE);
		contentPane.add(btnDeslocamento);

		JButton btnProgramador = new JButton("Programador");
		btnProgramador.setIcon(new ImageIcon(Home.class
				.getResource("/imgs/bt-programador.png")));
		btnProgramador.setVerticalTextPosition(SwingConstants.BOTTOM);
		btnProgramador.setToolTipText("Inicializa / Pausa Irriga\u00E7\u00E3o");
		btnProgramador.setHorizontalTextPosition(SwingConstants.CENTER);
		btnProgramador.setForeground(Color.BLACK);
		btnProgramador.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnProgramador.setBackground(Color.WHITE);
		contentPane.add(btnProgramador);

		JButton btnConfig = new JButton("Configura\u00E7\u00F5es");
		btnConfig.setIcon(new ImageIcon(Home.class
				.getResource("/imgs/bt-config.png")));
		btnConfig.setVerticalTextPosition(SwingConstants.BOTTOM);
		btnConfig.setToolTipText("Inicializa / Pausa Irriga\u00E7\u00E3o");
		btnConfig.setHorizontalTextPosition(SwingConstants.CENTER);
		btnConfig.setForeground(Color.BLACK);
		btnConfig.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnConfig.setBackground(Color.WHITE);
		contentPane.add(btnConfig);

		JButton btnAdm = new JButton("Administra\u00E7\u00E3o T\u00E9cnica");
		btnAdm.setIcon(new ImageIcon(Home.class
				.getResource("/imgs/bt-tecnino.png")));
		btnAdm.setVerticalTextPosition(SwingConstants.BOTTOM);
		btnAdm.setToolTipText("Inicializa / Pausa Irriga\u00E7\u00E3o");
		btnAdm.setHorizontalTextPosition(SwingConstants.CENTER);
		btnAdm.setForeground(Color.BLACK);
		btnAdm.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnAdm.setBackground(Color.WHITE);
		contentPane.add(btnAdm);
	}
}
