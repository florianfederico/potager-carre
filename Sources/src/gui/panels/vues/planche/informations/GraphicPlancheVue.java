package gui.panels.vues.planche.informations;

import gui.panels.vues.planche.PlancheVueContent;
import java.awt.Dimension;
import java.awt.GridLayout;
import javax.swing.JPanel;
import objects.Planche;

public class GraphicPlancheVue extends JPanel
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Planche planche;
	private PlancheVueContent plancheContent;
	private GraphicCarreVue[] carres;
	/**
	 * Cr�ation d'une planche graphique, en vue planche
	 * @param pc
	 * 			�l�ment contenant
	 * @param p
	 * 			planche � repr�senter
	 */
	public GraphicPlancheVue(PlancheVueContent pc,Planche p)
	{
		super();
		planche=p;
		plancheContent=pc;
		initComponents();
		initPanel();
	}
	/**
	 * Cr�e l'image de chacun des carr�s de la planche
	 */
	public void setImage()
	{
		for(GraphicCarreVue c : carres)
			c.setImage();
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
		planche.setPositions(x,y);
	}
	/**
	 * Permet de r�cup�rer la planche repr�sent�e
	 * @return
	 * 			planche repr�sent�e
	 */
	public Planche getPlanche()
	{
		return planche;
	}
	/**
	 * Permet de r�cup�rer la position x
	 * @return
	 * 			position x de la planche
	 */
	public int getPosX()
	{
		return planche.getX();
	}
	/**
	 * Permet de r�cup�rer la position y
	 * @return
	 * 			position y de la planche
	 */
	public int getPosY()
	{
		return planche.getY();
	}
	/**
	 * Permet de r�cup�rer l'�l�ment parent
	 * @return
	 * 			element parent
	 */
	public PlancheVueContent getPlancheContent()
	{
		return plancheContent;
	}
	/**
	 * Deselectionne les carr�s de la planche
	 */
	public void deSelect()
	{
		for(int i=0;i<carres.length;i++)
			carres[i].deSelect();
	}
	/**
	 * Initialise les composants
	 */
	public void initComponents()
	{
		carres=new GraphicCarreVue[planche!=null?9:0];
		for(int i=0;i<carres.length;i++)
			carres[i]=new GraphicCarreVue(this,planche.getCarre(i));
	}
	/**
	 * change la planche affich�e
	 * @param p
	 * 			Nouvelle planche affich�e
	 */
	public void setPlanche(Planche p)
	{
		planche=p;
		carres=new GraphicCarreVue[planche!=null?9:0];
		for(int i=0;i<carres.length;i++)
			carres[i]=new GraphicCarreVue(this,planche.getCarre(i));
		removeAll();
		for(int i=0;i<carres.length;i++)
			add(carres[i]);
	}
	/**
	 * Initialise le panel
	 */
	public void initPanel()
	{
		setOpaque(false);
		setPreferredSize(new Dimension(350,350));
		setLayout(new GridLayout(3,3));
		for(int i=0;i<carres.length;i++)
			add(carres[i]);
	}
	/**
	 * Permet de r�cup�rer les carr�s graphiques
	 * @return
	 * 			liste des carr�s graphiques
	 */
	public GraphicCarreVue[] getCarres()
	{
		return carres;
	}
}
