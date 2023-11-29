package gui.panels.vues;


import java.awt.Dimension;
import java.util.HashMap;
import gui.Fenetre;
import gui.components.icones.*;
import gui.components.tools.EditorMenu;
import gui.components.tools.Titre;
import gui.mainPanels.observers.PotagerObserver;
import gui.panels.vues.potager.PotagerContent;
import javax.swing.JPanel;
import objects.Potager;

public class PotagerVue extends JPanel
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private PotagerObserver potager;
	private Titre potagerDatas;
	private EditorMenu editorMenu;
	private PotagerContent potagerContent;
	private Fenetre mainFrame;
	/**
	 * Crée la vue potager de l'application
	 * @param p
	 * 			Observeur du potager
	 * @param f
	 * 			Frame principale
	 */
	public PotagerVue(PotagerObserver p, Fenetre f)
	{
		super();
		mainFrame=f;
		potager=p;
		initComponents();
		initPanel();
	}
	/**
	 * Permet d'ajouter une planche
	 */
	public void ajoutPlanche()
	{
		potagerContent.ajoutPlanche();
		editorMenu.setPlancheSelectionnee(true);
	}
	/**
	 * Affiche le message de position de la boussole et bloque les icones de planches si la boussole n'a jamais été déplacée
	 */
	public void setFirstPosBoussole()
	{
		if(potager.getPotager().isMovable())
		{
			potagerContent.setFirstPosBoussole();
			editorMenu.setLockIcones(true);
		}
	}
	/**
	 * Supprime la planche selectionnée
	 */
	public void supprimerPlanche()
	{
		potagerContent.supprimerPlanche();
	}
	/**
	 * Renvoie le potager observé
	 * @return
	 * 			potager en cour d'édition
	 */
	public Potager getPotager()
	{
		return potager.getPotager();
	}
	/**
	 * Renvoie l'interface d'édition de potager
	 * @return
	 * 			interface d'édition du potager
	 */
	public PotagerContent getPotagerContent()
	{
		return potagerContent;
	}


	/**
	 * Change l'action en cour de l'utilisateur sur la vue potager et annule les autres
	 * @param i
	 * 			ID de l'action
	 */
	public void setAction(int i)
	{
		potagerContent.setAction(i);
	}
	/**
	 * Change l'action en cour de l'utilisateur sur la vue potager sans annuler les autres
	 * @param i
	 * 			ID de l'action
	 */
	public void setActionUnique(int i)
	{
		potagerContent.setActionUnique(i);
	}
	/**
	 * Active ou non la grille
	 * @param bool
	 * 			Active ou desactive la grille
	 */
	public void setIconeGrilleActive(boolean bool)
	{
		editorMenu.setIconeGrilleActive(bool);
	}
	/**
	 * Actualise l'affichage des icones si une planche est selectionnée ou non
	 * @param bool
	 * 			planche selectionnée ou non
	 */
	public void setPlancheSelectionnee(boolean bool)
	{
		editorMenu.setPlancheSelectionnee(bool);
	}
	/**
	 * Créée un pop up de modification d'informations de planche
	 */
	public void updatePlancheInfos()
	{
		potagerContent.updatePlancheInfos();	
	}
	/**
	 * Renvoie la frame principale
	 * @return
	 * 			Frame principale
	 */
	public Fenetre getMainFrame()
	{
		return mainFrame;
	}
	/**
	 * Affiche le message de pose de la boussole si elle n'a jamais été déplacée
	 */
	public void setPosBoussole()
	{
		potagerContent.setFirstPosBoussole();
	}
	/**
	 * Initialise les composants
	 */
	public void initComponents()
	{

		HashMap<Integer,Integer> iconesList=new HashMap<Integer,Integer>();
		iconesList.put(1, NouveauPotager.binValue | 
						  OuvrirPotager.binValue | 
						  Enregistrer.binValue |
						  SupprimerPotager.binValue |
						  EditerPotager.binValue |
						  FermerPotager.binValue |
						  AfficherGrille.binValue);
		
		iconesList.put(2, NouvellePlanche.binValue | 
						  SupprimerPlanche.binValue | 
						  DeplacerPlanche.binValue |
						  EditerPlanche.binValue |
						  ReinitialiserPlanche.binValue |
						  ViderPlanche.binValue |
						  OuvrirPlanche.binValue);
		
		editorMenu=new EditorMenu(iconesList, true, mainFrame);
		String jardinDatas=potager.getPotager().getNom()+" | "+potager.getPotager().getAdresse();
		potagerDatas=new Titre(jardinDatas, 580, 35, 18.0f, 5, mainFrame);
		potagerContent=new PotagerContent(potager.getPotager(),this);
		editorMenu.setPlancheSelectionnee(false);
	}
	/**
	 * Actualise les informations modifiées du potager
	 */
	public void actualiser()
	{
		String jardinDatas=potager.getPotager().getNom()+" | "+potager.getPotager().getAdresse();
		potagerDatas.setValues(jardinDatas, 580, 35, 18.0f, 5);	
	}
	/**
	 * Initialise le panel
	 */
	public void initPanel()
	{
		setPreferredSize(new Dimension(584,500));
		setOpaque(false);
		add(potagerDatas);
		add(editorMenu);
		add(potagerContent);
	}
	/**
	 * Réinialise la planche (supprimer les plants sans ajouter à l'historique pour chaque carré)
	 */
	public void reinitialiserPlanche() {
		potagerContent.reinitialiserPlanche();
	}
	/**
	 * Réinialise la planche (supprimer les plants en ajoutant à l'historique pour chaque carré)
	 */
	public void viderPlanche() {
		potagerContent.viderPlanche();
	}
}
