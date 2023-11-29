package gui.mainPanels;

import java.awt.Dimension;
import java.util.HashMap;
import gui.Fenetre;
import gui.mainPanels.observers.PotagerObserver;
import gui.panels.informationsFrame.jardinInfos.JardinInfoFrame;
import gui.panels.vues.PotagerVue;
import javax.swing.JPanel;
import common.ProgramDatas;
import objects.Potager;

public class PotagerPanel extends JPanel implements PotagerObserver
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Fenetre mainFrame;
	private PotagerVue potagerVue;
	private Potager potager;
	/**
	 * Création d'une instance de vue de potager
	 * @param f
	 * 			Frame principale
	 */
	public PotagerPanel(Fenetre f)
	{
		super();
		mainFrame=f;
		potager=new Potager();
		initComponents();
		initPanel();
	}
	
	/**
	 * Ajout d'une planche
	 */
	public void ajoutPlanche()
	{
		potagerVue.ajoutPlanche();
	}
	/**
	 * Renvoie une vue du potager (sans les menus)
	 * @return
	 * 		vue de potager sans icones
	 */
	public PotagerVue getPotagerVue()
	{
		return potagerVue;
	}

	/**
	 * Change l'action en cour de l'utilisateur sur la vue potager et annule les autres
	 * @param i
	 * 			ID de l'action
	 */
	public void setAction(int i)
	{
		potagerVue.setAction(i);
	}
	/**
	 * Change l'action en cour de l'utilisateur sur la vue potager sans annuler les autres
	 * @param i
	 * 			ID de l'action
	 */
	public void setActionUnique(int i)
	{
		potagerVue.setActionUnique(i);		
	}

	/**
	 * Affiche le message de placement de la boussole
	 */
	public void validerPotager()
	{
		mainFrame.validerPotager();
	}
	/**
	 * Lance un pop up demandant les informations du potager
	 */
	public void jardinPopUp()
	{
		new JardinInfoFrame(mainFrame, this, potager);
	}
	/**
	 * Affiche le message de placement de la boussole
	 */
	public void boussolePopUp()
	{
		potagerVue.setFirstPosBoussole();
	}
	/**
	 * Création d'une instance de vue de potager
	 * @param f
	 * 			Frame principale
	 * @param p
	 * 			Potager observé
	 */
	public PotagerPanel(Fenetre f, Potager p)
	{
		super();
		mainFrame=f;
		potager=p;
		initComponents();
		initPanel();
	}
	
	/**
	 * Positionne la boussole
	 */
	public void setPosBoussole()
	{
		potagerVue.setPosBoussole();
	}
	/**
	 * Actualise la vue
	 */
	public void actualiser()
	{
		potagerVue.actualiser();
	}
	/**
	 * Mets à jour les informations de la planche
	 */
	public void updatePlancheInfos()
	{
		potagerVue.updatePlancheInfos();	
	}
	/**
	 * Mets à jour les informations du potager
	 */
	public void updatePotagerInfos()
	{
		new JardinInfoFrame(mainFrame, this, potager);	
	}
	/**
	 * Permet de supprimer la planche selectionnée
	 */
	public void supprimerPlanche()
	{
		potagerVue.supprimerPlanche();
	}
	/**
	 * Permet de regarder si le nom du potager existe
	 * @param nom
	 * 			nom du potager
	 * @return
	 * 			true s'il existe déjà
	 */
	public boolean nomPotagerExiste(String nom)
	{
		return mainFrame.nomPotagerExiste(nom);
	}
	/**
	 * Permet de regarder si le nom de la planche existe
	 * @param nom
	 * 			nom de la planche
	 * @return
	 * 			true s'il existe
	 */
	public boolean nomPlancheExiste(String nom)
	{
		return mainFrame.nomPlancheExiste(nom);
	}
	/**
	 * Initialise les composants
	 */
	public void initComponents()
	{
		potagerVue=new PotagerVue(this,mainFrame);
	}
	/**
	 * Envoie les données de familles
	 * @return
	 * 			données des familles
	 */
	public HashMap<String, String> getFamilles()
	{
		return mainFrame.getDatas().getFamilles();
	}
	/**
	 * Envoie les données de types
	 * @return
	 * 			données des types
	 */
	public HashMap<String, String> getTypes()
	{
		return mainFrame.getDatas().getTypes();	
	}
	
	/**
	 * Initialise les panels
	 */
	public void initPanel()
	{
		setPreferredSize
		(
			new Dimension
			(
				Integer.parseInt(mainFrame.getDatas().getInformation("dimensions.potagerPanel").split(",")[0]),
				Integer.parseInt(mainFrame.getDatas().getInformation("dimensions.potagerPanel").split(",")[1])
			)
		);
		setOpaque(false);
		add(potagerVue);
	}
	

	/**
	 * Permet de récupérer les données de l'application
	 */
	public ProgramDatas getDatas()
	{
		return mainFrame.getDatas();
	}
	/**
	 * Envoie le potager en cour d'édition
	 */
	public Potager getPotager()
	{
		return potager;
	}
	/**
	 * Mise à jour de la vue via les observables
	 */
	public void update() 
	{
	}

	/**
	 * Supprime tous les plants de chaque carré de la planche selectionnée sans les ajouter à leur historique
	 */
	public void reinitialiserPlanche() 
	{
		potagerVue.reinitialiserPlanche();
	}

	/**
	 * Ajoute tous les plants de chaque carré à l'historique
	 */
	public void viderPlanche() 
	{
		potagerVue.viderPlanche();
	}

}
