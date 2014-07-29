package cuki.gui;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.Box;
import javax.swing.JMenuBar;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.SwingWorker;

import net.wimpi.modbus.util.ModbusUtil;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Font;
import java.awt.Component;
import java.awt.ComponentOrientation;
import java.awt.Dimension;

import cuki.proc.KModbus;
import cuki.proc.Mapa;

@SuppressWarnings("serial")
public class MasterFrame extends JFrame {

	public static KModbus k = null;

	private String m_port;
	private int m_addr;

	private JPanel jp = null;
	private JLabel lblData = null;
	private JLabel lblIdPivo = null;

	private Font m_font = null;

	public final static int panelNull = 0;
	public final static int panelIrrigar = 1;
	public final static int panellaminas = 2;
	public final static int panelSetores = 3;
	public final static int panelDeslocamento = 4;
	public final static int panelProgramador = 5;
	public final static int panelConfig = 6;
	public final static int panelAdm = 7;

	public MasterFrame(final JFrame frame, String port, int addrDevice,
			int tipoPane) {

		m_port = port;
		m_addr = addrDevice;
		m_font = new Font("Lucida Console", Font.PLAIN, 18);

		switch (tipoPane) {
		case MasterFrame.panelIrrigar:
			jp = new Irrigar(m_port, m_addr, m_font);
			jp.setBackground(Color.WHITE);
			break;
		case MasterFrame.panellaminas:
			jp = new Laminas(m_port, m_addr, m_font);
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
			protected Object doInBackground() {
				while (true) {
					int[] resp = null;
					resp = k.read(Mapa.masterPanel, Mapa.masterPanelLen);

					if (resp != null) {
						lblData.setText(resp[1] + "/" + resp[0] + " " + resp[2]
								+ ":" + resp[3]);

						int[] strId = new int[10];
						for (int i = 0; i < strId.length; ++i)
							strId[i] = resp[i + 4];

						StringBuffer sb = new StringBuffer();
						for (int i : strId) {
							byte[] b = ModbusUtil.shortToRegister((short) i);
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

					try {
						Thread.sleep(1500);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}

					if (jp instanceof Irrigar)
						((Irrigar) jp).sync();
					else if (jp instanceof Laminas)
						((Laminas) jp).sync();

					try {
						Thread.sleep(1500);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		};
		worker.execute();
	}
}
