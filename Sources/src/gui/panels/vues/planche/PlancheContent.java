package gui.panels.vues.planche;


import gui.panels.vues.planche.informations.GraphicPlanche;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.border.EtchedBorder;
import objects.Planche;

public class PlancheContent extends JPanel
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private GraphicPlanche planche;
	private FullPlanche fullPlanche;
	/**
	 * Cr�ation d'une planche graphique, en vue potager
	 * @param fp
	 * 			�l�ment contenant
	 * @param p
	 * 			planche � repr�senter
	 */
	public PlancheContent(FullPlanche fp, Planche p)
	{
		super();
		fullPlanche=fp;
		initComponents(p);
		initEvents();
		initPanel();
	}
	/**
	 * Envoie la repr�sentation graphique de la planche
	 * @return
	 * 			planche graphique
	 */
	public GraphicPlanche getGraphicPlanche()
	{
		return planche;
	}
	/**
	 * Envoie la planche graphique compl�te
	 * @return
	 * 			planche compl�te utilisable par la vue potager
	 */
	public FullPlanche getFullPlanche()
	{
		return fullPlanche;
	}
	/**
	 * Mets les positions de la planche � jour
	 * @param x
	 * 			position x
	 * @param y
	 * 			position y
	 */
	public void updateBounds(int x,int y)
	{
		planche.updateBounds(x,y);
	}
	/**
	 * Permet de r�cup�rer la planche repr�sent�e
	 * @return
	 * 			planche repr�sent�e
	 */
	public Planche getPlanche()
	{
		return planche.getPlanche();
	}
	/**
	 * Permet de r�cup�rer la position x
	 * @return
	 * 			position x de la planche
	 */
	public int getPosX()
	{
		return planche.getPlanche().getX();
	}
	/**
	 * Permet de r�cup�rer la position y
	 * @return
	 * 			position y de la planche
	 */
	public int getPosY()
	{
		return planche.getPlanche().getY();
	}
	/**
	 * Initialise la vision graphique de la planche
	 * @param p
	 * 			planche � afficher
	 */
	public void initComponents(Planche p)
	{
		planche=new GraphicPlanche(this, p);
	}
	/**
	 * Initialisation du panel
	 */
	public void initPanel()
	{
		setPreferredSize(new Dimension(105,105));
		setBackground(Color.BLACK);
		setBorder(BorderFactory.createEtchedBorder(EtchedBorder.RAISED));
		setOpaque(false);
		
		Box box = new Box(BoxLayout.LINE_AXIS);
		box.setAlignmentX(JComponent.CENTER_ALIGNMENT);
		box.add(planche);
		add(box);
	}
	/**
	 * Selectionne ou deselectionne la planche
	 */
	public void actionOnClic()
	{
		if(getBackground()==Color.GREEN)
			fullPlanche.deSelect();
		else
			fullPlanche.select();		
	}
	/**
	 * Selectionne la planche
	 */
	public void setFullPlanche()
	{
		fullPlanche.setFullPlanche();
	}
	/**
	 * Essaye de poser une planche
	 * @param x
	 * 			position x de la planche
	 * @param y
	 * 			position y de la planche
	 */
	public void tryPosePlanche(int x, int y)
	{
		fullPlanche.tryPosePlanche(getX()+x,getY()+y);
	}
	/**
	 * Initialise les evenements
	 */
	public void initEvents()
	{
		this.addMouseListener
		(
			new MouseListener()
			{
				//Lorsque l'utilisateur clique
				public void mouseClicked(MouseEvent e)
				{					
					if((((fullPlanche.getParent().getAction()>>0)&1)==1))
					{
						tryPosePlanche(e.getX(),e.getY());
					}
					else
						setFullPlanche();
				}
				//Lorsque la souris entre dans le panel
				public void mouseEntered(MouseEvent e)
				{
				}
				//Lorsque la souris sort du panel
				public void mouseExited(MouseEvent e) 
				{
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
	/**
	 * Change le curseur s'il est possible ou non de placer la planche
	 * @param x
	 * 			position x du curseur
	 * @param y
	 * 			position y du curseur
	 */
	public void changeIcone(int x, int y)
	{
		fullPlanche.changeIcone(x, y);
	}
	/**
	 * R�inialise la planche (supprimer les plants sans ajouter � l'historique pour chaque carr�)
	 */
	public void reinitialiserPlanche() {
		planche.reinitialiserPlanche();
	}
	/**
	 * R�inialise la planche (supprimer les plants en ajoutant � l'historique pour chaque carr�)
	 */
	public void viderPlanche() {
		planche.viderPlanche();
	}

}

