package cuki.frames;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.SwingWorker;

import net.miginfocom.swing.MigLayout;
import cuki.proc.Mapa;

@SuppressWarnings("serial")
public class Irrigar extends MasterFrame {

	private Pizza pizza = null;

	public Irrigar(JFrame frame) {
		super(frame);
		getContentPane().setLayout(new MigLayout("", "[grow]", "[grow]"));

		pizza = new Pizza();
		pizza.setAnguloAtual(k.getInt(Mapa.anguloAtual));
		getContentPane().add(pizza, "cell 0 0,alignx left,aligny top");
		pack();
	}

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {

			@Override
			public void run() {
				Irrigar frame = new Irrigar(null);
				frame.setVisible(true);
				frame.start();
			}
		});
	}

	public void start() {

		@SuppressWarnings("rawtypes")
		SwingWorker worker = new SwingWorker() {
			@Override
			protected Object doInBackground() throws Exception {
				while (true) {

					pizza.setAnguloAtual(k.getInt(Mapa.anguloAtual));
					repaint();

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
}
