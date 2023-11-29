package gui.mainPanels.accueilPanelComponents;


import gui.Fenetre;
import gui.panels.AbstractDynamicPanel;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.BorderFactory;
import javax.swing.border.EtchedBorder;
import objects.Potager;

public abstract class PotagerPanel extends AbstractDynamicPanel
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	protected Fenetre mainFrame;
	protected Potager potager;
	protected ListePotagers listePotagers;
	protected boolean isSelected;
	/**
	 * Initialise le panel représentant le potager
	 * @param liste
	 * 			Liste des potagers possibles, composant parent
	 * @param f
	 * 			Frame principale
	 * @param p
	 * 			Potager à afficher
	 */
	public PotagerPanel(ListePotagers liste, Fenetre f, Potager p)
	{
		mainFrame=f;
		listePotagers=liste;
		potager=p;
		isSelected=false;
		initEvent();
		initPanel();
	}
	public abstract void setMenuVisible(boolean b);

	public void initPanel() 
	{
		setPreferredSize(new Dimension(177,115));
		setOpaque(false);
		setBackground(Color.WHITE);
		setBorder(BorderFactory.createEtchedBorder(EtchedBorder.RAISED));	
	}
	/**
	 * Renvoie le potager du panel
	 * @return
	 * 			potager lié au panel
	 */
	public Potager getPotager()
	{
		return potager;
	}
	/**
	 * Permet de savoir si le panel est selectionné
	 * @return
	 * 			true s'il est selectionné
	 */
	public boolean isSelected()
	{
		return isSelected;
	}
	/**
	 * Permet de deselectionner le panel
	 */
	public void deSelect()
	{
		isSelected=false;
		setBorder(BorderFactory.createEtchedBorder(EtchedBorder.RAISED));
	}
	/**
	 * Initialise les events du panel
	 */
	public void initEvent()
	{
		this.addMouseListener
		(
			new MouseListener()
			{
				//Lorsque l'utilisateur clique
				public void mouseClicked(MouseEvent e)
				{

				}
				//Lorsque la souris entre dans le panel
				public void mouseEntered(MouseEvent e)
				{
					setBorder(BorderFactory.createBevelBorder(0));
					setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
				}
				//Lorsque la souris sort du panel
				public void mouseExited(MouseEvent e) 
				{
					if(!isSelected)
						setBorder(BorderFactory.createEtchedBorder(EtchedBorder.RAISED));
					setCursor(Cursor.getDefaultCursor());
					
				}				
				public void mousePressed(MouseEvent e)
				{
				}
				public void mouseReleased(MouseEvent e) 
				{
				}
			}
		);
	}
}
