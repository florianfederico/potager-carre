package gui.panels;

import javax.swing.JPanel;

public abstract class AbstractStaticPanel extends JPanel
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * Initialise les composants du panel
	 */
	public abstract void initComponents();
	/**
	 * Initialise le panel
	 */
	public abstract void initPanel();
}
