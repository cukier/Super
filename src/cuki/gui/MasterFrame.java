package cuki.gui;

import javax.swing.JFrame;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.FlowLayout;

import javax.swing.JMenuBar;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.SwingWorker;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import cuki.proc.ConnectionModbus;
import cuki.proc.KModbus;
import javax.swing.JLabel;

import java.awt.Font;

import javax.swing.SwingConstants;

import java.awt.Component;
import java.awt.ComponentOrientation;

import javax.swing.Box;

import java.awt.Dimension;

@SuppressWarnings("serial")
public class MasterFrame extends JFrame {

	public static KModbus k = null;

	protected ConnectionModbus m_con = null;

	private JPanel jp = null;
	private JLabel lblHora = null;
	private JLabel idPivo = null;

	public final static int irrigar = 0;

	public MasterFrame(final JFrame frame, ConnectionModbus con,
			int addrDevice, int tipoPane) {

		m_con = con;
		k = new KModbus(con, addrDevice);

		switch (tipoPane) {
		case MasterFrame.irrigar:
			jp = new Irrigar();
			break;
		}

		createGUI(frame, jp);
	}

	private void createGUI(final JFrame frame, JPanel jp) {

		JMenuBar menuBar = new JMenuBar();
		menuBar.setBorder(null);
		menuBar.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);

		menuBar.setBackground(Color.BLACK);

		JButton btnHome = new JButton("");
		btnHome.setBorder(null);
		btnHome.setBackground(Color.BLACK);
		btnHome.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				if (frame != null)
					frame.setVisible(true);
			}
		});
		btnHome.setIcon(new ImageIcon(MasterFrame.class
				.getResource("/ico/house36.png")));
		menuBar.add(btnHome);

		Component horizontalGlue = Box.createHorizontalGlue();
		menuBar.add(horizontalGlue);

		idPivo = new JLabel("Conectando...");
		idPivo.setForeground(Color.LIGHT_GRAY);
		idPivo.setHorizontalAlignment(SwingConstants.CENTER);
		idPivo.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		menuBar.add(idPivo);

		Component rigidArea = Box.createRigidArea(new Dimension(20, 20));
		menuBar.add(rigidArea);

		lblHora = new JLabel("00 : 00");
		lblHora.setForeground(Color.LIGHT_GRAY);
		lblHora.setHorizontalAlignment(SwingConstants.CENTER);
		lblHora.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		menuBar.add(lblHora);

		setJMenuBar(menuBar);

		setContentPane(jp);
	}

	public void start() {

		@SuppressWarnings("rawtypes")
		SwingWorker worker = new SwingWorker() {
			@Override
			protected Object doInBackground() throws Exception {
				while (true) {
					if (jp instanceof Irrigar) {
						// int[] resp = k.read(Mapa.horasRestantes, 8);
						Irrigar i = (Irrigar) jp;
						i.sync(k);
					}

					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		};
		worker.execute();
	}

	public static void main(String[] args) {

		EventQueue.invokeLater(new Runnable() {

			@Override
			public void run() {
				final JFrame f = new JFrame();

				JButton btn = new JButton("Irrigar");

				btn.addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent e) {

						f.setVisible(false);

						MasterFrame mf = new MasterFrame(f,
								new ConnectionModbus("COM1"), 1, 0);
						mf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
						mf.pack();
						mf.setVisible(true);
						mf.start();
					}
				});

				f.getContentPane().setLayout(new FlowLayout());
				f.getContentPane().add(btn);
				f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				f.pack();
				f.setVisible(true);
			}
		});
	}
}
