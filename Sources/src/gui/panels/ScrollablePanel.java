package gui.panels;

import java.awt.Dimension;
import java.awt.Rectangle;

import javax.swing.JPanel;
import javax.swing.Scrollable;

public class ScrollablePanel extends JPanel implements Scrollable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public Dimension getPreferredScrollableViewportSize()
	{
		return null;
	}
	public int getScrollableBlockIncrement(Rectangle arg0, int arg1, int arg2)
	{
		return 20;
	}
	public boolean getScrollableTracksViewportHeight()
	{
		return false;
	}
	public boolean getScrollableTracksViewportWidth()
	{
		return false;
	}
	public int getScrollableUnitIncrement(Rectangle arg0, int arg1, int arg2) 
	{
		return 20;
	}
}
