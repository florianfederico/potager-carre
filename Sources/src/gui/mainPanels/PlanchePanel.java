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
	 * Cr�ation d'un panel de vue de planche
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
	 * Change les donn�es de la planche affich�e
	 * @param p
	 * 			Nouvelle planche
	 * @param po
	 * 			Potager propri�taire de la planche
	 */
	public void setCurrentPlanche(Planche p, Potager po)
	{
		planche=p;
		potager=po;
		plancheVue.updatePlancheInfos(p,po);
	}
	/**
	 * Permet de modifier le carr� observ�
	 * @param c
	 * 			Nouveau carr� � observer
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
	 * Permet de r�cup�rer les donn�es de familles
	 * @return
	 * 		donn�es de familles
	 */
	public HashMap<String, String> getFamilles()
	{
		return mainFrame.getDatas().getFamilles();
	}
	/**
	 * Permet de r�cup�rer les donn�es de types
	 * @return
	 * 		donn�es de types
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
	
	//Quand on touche a la legumoth�que
	/**
	 * Mise � jour de l'application par les observables
	 */
	public void update()
	{
		plancheVue.setCursorIcon(mainFrame.getLegumotheque().getPlant());
	}
	
	/**
	 * Permet de r�cup�rer les donn�es de l'application
	 */
	public ProgramDatas getDatas()
	{
		return mainFrame.getDatas();
	}
	/**
	 * Permet de vider le carr� selectionn� en ajoutant � l'historique
	 */
	public void viderCarre() {
		plancheVue.viderCarre();
	}
	/**
	 * Permet d'inverser l'ensoleillement du carr� selectionn�
	 */
	public void swapEnsoleillement() {
		plancheVue.swapEnsoleillement();
	}
	/**
	 * Permet de vider le carr� selectionn� sans ajouter � l'historique
	 */
	public void annulerLegumes() {
		plancheVue.annulerLegumes();
		
	}
}
