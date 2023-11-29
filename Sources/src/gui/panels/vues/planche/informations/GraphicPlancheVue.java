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
	 * Création d'une planche graphique, en vue planche
	 * @param pc
	 * 			élément contenant
	 * @param p
	 * 			planche à représenter
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
	 * Crée l'image de chacun des carrés de la planche
	 */
	public void setImage()
	{
		for(GraphicCarreVue c : carres)
			c.setImage();
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
	 * Permet de récupérer l'élément parent
	 * @return
	 * 			element parent
	 */
	public PlancheVueContent getPlancheContent()
	{
		return plancheContent;
	}
	/**
	 * Deselectionne les carrés de la planche
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
	 * change la planche affichée
	 * @param p
	 * 			Nouvelle planche affichée
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
	 * Permet de récupérer les carrés graphiques
	 * @return
	 * 			liste des carrés graphiques
	 */
	public GraphicCarreVue[] getCarres()
	{
		return carres;
	}
}
