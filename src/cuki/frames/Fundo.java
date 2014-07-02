package cuki.frames;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class Fundo extends JPanel {

	public Fundo(Dimension size) {
		setPreferredSize(size);
	}

	@Override
	public void paint(Graphics g) {
		super.paint(g);

		Graphics2D g2 = (Graphics2D) g;

		g2.setColor(Color.BLACK);
		g2.fillRect(0, 0, getWidth(), 60);

		g2.setColor(Color.WHITE);
		g2.fillRect(0, 60, getWidth(), getHeight());
	}
}
