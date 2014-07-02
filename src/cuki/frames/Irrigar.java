package cuki.frames;

import javax.swing.JFrame;
import net.miginfocom.swing.MigLayout;

@SuppressWarnings("serial")
public class Irrigar extends MasterFrame {

	public Irrigar(JFrame frame) {
		super(frame);
		getContentPane().setLayout(new MigLayout("", "[grow]", "[grow]"));

		Pizza pizza = new Pizza();
		getContentPane().add(pizza, "cell 0 0,alignx left,aligny top");

		pack();
	}

}
