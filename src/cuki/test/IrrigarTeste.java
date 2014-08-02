package cuki.test;

import javax.swing.JFrame;
import javax.swing.JPanel;

import cuki.gui.Pizza;

public class IrrigarTeste {

	public static void main(String[] args) {

		JFrame frame = new JFrame();
		JPanel pizza = new Pizza();

		frame.setContentPane(pizza);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.pack();
		frame.setVisible(true);
	}
}
