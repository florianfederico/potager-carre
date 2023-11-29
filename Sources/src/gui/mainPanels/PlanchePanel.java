package gui.mainPanels;

import java.awt.Dimension;
import java.util.HashMap;
import gui.Fenetre;
import gui.mainPanels.observers.PlancheObserver;
import gui.panels.vues.PlancheVue;
import javax.swing.JPanel;
import common.ProgramDatas;
import objects.Carre;
import objects.Planche;
import objects.Potager;

public class PlanchePanel extends JPanel implements PlancheObserver
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Fenetre mainFrame;
	private PlancheVue plancheVue;
	private Planche planche;
	private Potager potager;
	/**
	 * Création d'un panel de vue de planche
	 * @param f
	 * 			Frame principale
	 */
	public PlanchePanel(Fenetre f)
	{
		super();
		mainFrame=f;
		planche=null;
		potager=null;
		initComponents();
		initPanel();
	}
	/**
	 * Change les données de la planche affichée
	 * @param p
	 * 			Nouvelle planche
	 * @param po
	 * 			Potager propriétaire de la planche
	 */
	public void setCurrentPlanche(Planche p, Potager po)
	{
		planche=p;
		potager=po;
		plancheVue.updatePlancheInfos(p,po);
	}
	/**
	 * Permet de modifier le carré observé
	 * @param c
	 * 			Nouveau carré à observer
	 */
	public void setCarre(Carre c)
	{
		plancheVue.setCarre(c);
	}
	/**
	 * Initialise les composants
	 */
	public void initComponents()
	{
		plancheVue=new PlancheVue(planche,mainFrame,potager);
	}
	/**
	 * Permet de récupérer les données de familles
	 * @return
	 * 		données de familles
	 */
	public HashMap<String, String> getFamilles()
	{
		return mainFrame.getDatas().getFamilles();
	}
	/**
	 * Permet de récupérer les données de types
	 * @return
	 * 		données de types
	 */
	public HashMap<String, String> getTypes()
	{
		return mainFrame.getDatas().getTypes();	
	}
	/**
	 * Initialise le panel
	 */
	public void initPanel()
	{
		setPreferredSize
		(
			new Dimension
			(
				Integer.parseInt(mainFrame.getDatas().getInformation("dimensions.planchePanel").split(",")[0]),
				Integer.parseInt(mainFrame.getDatas().getInformation("dimensions.planchePanel").split(",")[1])
			)
		);
		setOpaque(false);
		add(plancheVue);
	}
	
	//Quand on touche a la legumothèque
	/**
	 * Mise à jour de l'application par les observables
	 */
	public void update()
	{
		plancheVue.setCursorIcon(mainFrame.getLegumotheque().getPlant());
	}
	
	/**
	 * Permet de récupérer les données de l'application
	 */
	public ProgramDatas getDatas()
	{
		return mainFrame.getDatas();
	}
	/**
	 * Permet de vider le carré selectionné en ajoutant à l'historique
	 */
	public void viderCarre() {
		plancheVue.viderCarre();
	}
	/**
	 * Permet d'inverser l'ensoleillement du carré selectionné
	 */
	public void swapEnsoleillement() {
		plancheVue.swapEnsoleillement();
	}
	/**
	 * Permet de vider le carré selectionné sans ajouter à l'historique
	 */
	public void annulerLegumes() {
		plancheVue.annulerLegumes();
		
	}
}
