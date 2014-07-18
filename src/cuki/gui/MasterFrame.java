package cuki.gui;

import javax.swing.JFrame;

import java.awt.Color;
import javax.swing.JMenuBar;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.SwingWorker;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import cuki.proc.ConnectionModbus;
import cuki.proc.KModbus;
import cuki.proc.Mapa;

import javax.swing.JLabel;

import java.awt.Font;

import javax.swing.SwingConstants;

import java.awt.Component;
import java.awt.ComponentOrientation;

import javax.swing.Box;

import net.wimpi.modbus.util.ModbusUtil;

import java.awt.Dimension;

@SuppressWarnings("serial")
public class MasterFrame extends JFrame {

	public static KModbus k = null;

	protected ConnectionModbus m_con = null;

	private JPanel jp = null;
	private JLabel lblData = null;
	private JLabel lblIdPivo = null;

	public final static int irrigar = 0;

	public MasterFrame(final JFrame frame, ConnectionModbus con,
			int addrDevice, int tipoPane) {

		m_con = con;
		k = new KModbus(con, addrDevice);

		switch (tipoPane) {
		case MasterFrame.irrigar:
			jp = new Irrigar();
			jp.setBackground(Color.WHITE);
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

		Component rigidArea_2 = Box.createRigidArea(new Dimension(20, 20));
		menuBar.add(rigidArea_2);
		btnHome.setIcon(new ImageIcon(MasterFrame.class
				.getResource("/ico/house36.png")));
		menuBar.add(btnHome);

		Component horizontalGlue = Box.createHorizontalGlue();
		menuBar.add(horizontalGlue);

		lblIdPivo = new JLabel("Conectando...");
		lblIdPivo.setForeground(Color.LIGHT_GRAY);
		lblIdPivo.setHorizontalAlignment(SwingConstants.CENTER);
		lblIdPivo.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		menuBar.add(lblIdPivo);

		Component rigidArea = Box.createRigidArea(new Dimension(20, 20));
		menuBar.add(rigidArea);

		lblData = new JLabel("00 : 00");
		lblData.setForeground(Color.LIGHT_GRAY);
		lblData.setHorizontalAlignment(SwingConstants.CENTER);
		lblData.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		menuBar.add(lblData);

		setJMenuBar(menuBar);

		Component rigidArea_1 = Box.createRigidArea(new Dimension(20, 20));
		menuBar.add(rigidArea_1);

		setContentPane(jp);
	}

	public void start() {

		@SuppressWarnings("rawtypes")
		SwingWorker worker = new SwingWorker() {
			@Override
			protected Object doInBackground() throws Exception {
				int segundos = 30;
				while (true) {
					try {
						if (jp instanceof Irrigar) {
							((Irrigar) jp).sync(k.read(Mapa.irrigarPanel,
									Mapa.irrigarPanelLen));
						}

						if (segundos == 30) {
							segundos = 0;

							Thread.sleep(1000);

							int[] resp = k.read(Mapa.masterPanel,
									Mapa.masterPanelLen);

							String data = resp[1] + "/" + resp[0] + "    "
									+ resp[2] + " : " + resp[3];

							lblData.setText(data);

							int[] strId = new int[10];
							for (int i = 0; i < strId.length; ++i)
								strId[i] = resp[i + 4];

							StringBuffer sb = new StringBuffer();
							for (int i : strId) {
								byte[] b = ModbusUtil
										.shortToRegister((short) i);
								if (b[1] != 0)
									sb.append((char) b[1]);
								else
									break;
								if (b[0] != 0)
									sb.append((char) b[0]);
								else
									break;
							}

							lblIdPivo.setText(sb.toString());
						}
						repaint();
						++segundos;
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
