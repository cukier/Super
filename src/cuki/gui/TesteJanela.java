package cuki.gui;

import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingWorker;

import net.miginfocom.swing.MigLayout;
import cuki.proc.KModbus;
import cuki.proc.Mapa;

@SuppressWarnings("serial")
public class TesteJanela extends JFrame {

	KModbus k = null;

	JPanel contentPane;

	JLabel[] labels;

	int reference = 0;
	int legenth = 0;

	public TesteJanela(int reference, int legenth) {

		this.reference = reference;
		this.legenth = legenth;

		labels = new JLabel[legenth];

		contentPane = new JPanel();
		contentPane.setLayout(new MigLayout());

		JLabel lbl;

		for (int i = 0; i < labels.length; ++i) {
			lbl = new JLabel("word " + i);
			if (i != labels.length - 1)
				contentPane.add(lbl);
			else
				contentPane.add(lbl, "wrap");
		}

		for (int i = 0; i < labels.length; ++i) {
			labels[i] = new JLabel();
			contentPane.add(labels[i], "center");
		}

		setContentPane(contentPane);
		setPreferredSize(new Dimension(650, 80));

	}

	public void start() {

		@SuppressWarnings("rawtypes")
		SwingWorker worker = new SwingWorker() {
			@Override
			protected Object doInBackground() throws InterruptedException {
				while (true) {
					int[] resp = null;

					k = new KModbus("COM8", 1);

					try {
						resp = k.read(reference, legenth);
					} catch (Exception e) {
						e.printStackTrace();
					} finally {
						k = null;
					}

					for (int i = 0; i < resp.length; ++i) {
						labels[i].setText(String.valueOf(resp[i]));
						System.out.println(resp[i]);
					}

					repaint();

					Thread.sleep(1500);
				}
			}
		};
		worker.execute();
	}

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {

			@Override
			public void run() {

				TesteJanela frame = new TesteJanela(Mapa.irrigarPanel,
						Mapa.irrigarPanelLen);

				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				frame.pack();
				frame.setVisible(true);
				frame.start();
			}
		});
	}
}
