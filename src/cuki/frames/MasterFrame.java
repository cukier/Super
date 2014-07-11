package cuki.frames;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import java.awt.Color;

import javax.swing.JMenuBar;
import javax.swing.JButton;
import javax.swing.ImageIcon;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import net.miginfocom.swing.MigLayout;
import cuki.proc.ConnectionModbus;
import cuki.proc.DeviceModbus;
import cuki.proc.KModbus;
import cuki.proc.Mapa;

import javax.swing.JLabel;

@SuppressWarnings("serial")
public class MasterFrame extends JFrame {

	private JPanel contentPane;

	protected ConnectionModbus con = null;
	protected DeviceModbus plc = null;
	public KModbus k = null;

	private StringBuffer sb = null;
	private JLabel lblHora = null;

	private void createGUI(final JFrame frame) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);

		JMenuBar menuBar = new JMenuBar();
		menuBar.setBackground(Color.WHITE);
		setJMenuBar(menuBar);

		JButton btnHome = new JButton("");
		btnHome.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				dispose();
				if (frame != null)
					frame.setVisible(true);
			}
		});
		btnHome.setBackground(Color.WHITE);
		btnHome.setIcon(new ImageIcon(MasterFrame.class
				.getResource("/imgs/home.png")));
		menuBar.add(btnHome);

		sb = new StringBuffer();
		sb.append(k.getInt(Mapa.hora));
		sb.append(":");
		sb.append(k.getInt(Mapa.minuto));

		JLabel lblIdPivo = new JLabel(k.getString(Mapa.idPivo, 9));
		menuBar.add(lblIdPivo);

		lblHora = new JLabel(sb.toString());
		menuBar.add(lblHora);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new MigLayout("", "[]", "[]"));

	}

	public MasterFrame(final JFrame frame) {

		con = new ConnectionModbus("COM8");
		plc = new DeviceModbus(1, 130);
		k = new KModbus(con, plc);

		createGUI(frame);
	}
}
