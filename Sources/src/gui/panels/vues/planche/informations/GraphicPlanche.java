package gui.panels.vues.planche.informations;


import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import javax.swing.JPanel;
import gui.panels.vues.planche.PlancheContent;
import objects.Planche;

public class GraphicPlanche extends JPanel
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Planche planche;
	private PlancheContent plancheContent;
	private GraphicCarre[] carres;
	/**
	 * Création d'une planche graphique, en vue potager
	 * @param pc
	 * 			élément contenant
	 * @param p
	 * 			planche à représenter
	 */
	public GraphicPlanche(PlancheContent pc,Planche p)
	{
		super();
		planche=p;
		plancheContent=pc;
		initComponents();
		initEvents();
		initPanel();
	}
	/**
	 * Mets les positions de la planche à jour
	 * @param x
	 * 			position x
	 * @param y
	 * 			position y
	 */
	public void updateBounds(int x,int y)
	{
		planche.setPositions(x,y);
	}
	/**
	 * Permet de récupérer la planche représentée
	 * @return
	 * 			planche représentée
	 */
	public Planche getPlanche()
	{
		return planche;
	}
	/**
	 * Permet de récupérer la position x
	 * @return
	 * 			position x de la planche
	 */
	public int getPosX()
	{
		return planche.getX();
	}
	/**
	 * Permet de récupérer la position y
	 * @return
	 * 			position y de la planche
	 */
	public int getPosY()
	{
		return planche.getY();
	}
	/**
	 * Initialise les composants
	 */
	public void initComponents()
	{
		carres=new GraphicCarre[9];
		for(int i=0;i<carres.length;i++)
			carres[i]=new GraphicCarre(plancheContent,planche.getCarre(i));
	}
	/**
	 * Initialise le panel
	 */
	public void initPanel()
	{
		setOpaque(false);
		setPreferredSize(new Dimension(90,90));
		setLayout(new GridLayout(3,3));
		for(int i=0;i<carres.length;i++)
			add(carres[i]);
	}
	/**
	 * Essaye de poser la planche dans le potager
	 * @param x
	 * 			position x
	 * @param y
	 * 			position y
	 */
	public void tryPosePlanche(int x, int y)
	{
		plancheContent.tryPosePlanche(getX()+x,getY()+y);
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
					if((((plancheContent.getFullPlanche().getParent().getAction()>>0)&1)==1))
					{
						tryPosePlanche(e.getX(),e.getY());
					}
					else
						plancheContent.setFullPlanche();
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
		addMouseMotionListener
		(
			new MouseMotionListener()
			{
				public void mouseDragged(MouseEvent e)
				{
		
				}

				public void mouseMoved(MouseEvent e) 
				{
					if((((plancheContent.getFullPlanche().getParent().getAction()>>0)&1)==1))
					{
						plancheContent.getFullPlanche().getParent().getFullPlanche().setBounds(plancheContent.getFullPlanche().getX()+e.getX()+5-plancheContent.getFullPlanche().getParent().getFullPlanche().getSourisPosX(),plancheContent.getFullPlanche().getY()+e.getY()+10-plancheContent.getFullPlanche().getParent().getFullPlanche().getSourisPosY(), 105, 150);
						changeIcone(plancheContent.getFullPlanche().getX()+e.getX(),plancheContent.getFullPlanche().getY()+e.getY());
					}
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
		plancheContent.changeIcone(x, y);
	}
	/**
	 * Réinialise la planche (supprimer les plants sans ajouter à l'historique pour chaque carré)
	 */
	public void reinitialiserPlanche() {
		planche.reinitialiserPlanche();
	}
	/**
	 * Réinialise la planche (supprimer les plants en ajoutant à l'historique pour chaque carré)
	 */
	public void viderPlanche() {
		planche.viderPlanche();
	}
}
