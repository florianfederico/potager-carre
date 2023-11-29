package gui.panels.vues;

import java.awt.Dimension;
import java.util.HashMap;
import gui.Fenetre;
import gui.components.icones.*;
import gui.components.tools.EditorMenu;
import gui.components.tools.Titre;
import gui.panels.vues.planche.PlancheVueContent;
import gui.panels.vues.planche.informations.affichageCarre.CarreInformations;
import javax.swing.JPanel;
import objects.Carre;
import objects.Planche;
import objects.Plant;
import objects.Potager;

public class PlancheVue extends JPanel
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Planche planche;
	private Titre plancheDatas;
	private EditorMenu editorMenu;
	private PlancheVueContent plancheContent;
	private Fenetre mainFrame;
	private CarreInformations carre;
	private Potager potager;
	/**
	 * Cr�ation de la vue d'une planche
	 * @param p
	 * 			planche observ�e
	 * @param f
	 * 			Frame principale
	 * @param po
	 * 			potager contenant la planche
	 * 
	 */
	public PlancheVue(Planche p, Fenetre f, Potager po)
	{
		super();
		potager=po;
		mainFrame=f;
		planche=p;
		plancheContent=new PlancheVueContent(mainFrame, p, po);
		initComponents();
	}
	/**
	 * Envoie la planche observ�e
	 * @return
	 * 			planche observ�e
	 */
	public Planche getPlanche()
	{
		return planche;
	}
	/**
	 * Change la planche observ�e
	 * @param p
	 * 			nouvelle planche
	 * @param po
	 * 			potager contenant la planche
	 */
	public void updatePlancheInfos(Planche p, Potager po)
	{
		planche=p;
		potager=po;
		plancheContent.setPlanche(planche, potager);
		setCarre(null);
		String jardinDatas=plancheContent.getPlanche().getNom();
		plancheDatas.setTitre(jardinDatas);//(jardinDatas, 580, 35, 18.0f, 5);
		initPanel();
	}
	/**
	 * Permet de r�cup�rer la frame principale
	 * @return
	 * 			Frame principale
	 */
	public Fenetre getMainFrame()
	{
		return mainFrame;
	}
	/**
	 * Initialise les composants de la vue
	 */
	public void initComponents()
	{
		HashMap<Integer,Integer> iconesList=new HashMap<Integer,Integer>();
		iconesList.put(1, RetourPotager.binValue |
						  Enregistrer.binValue |
						  FermerPotager.binValue
					   );
		
		iconesList.put(2, ViderPlanche.binValue |
						  ReinitialiserPlanche.binValue
					   );
		
		iconesList.put(3, ModifierEnsoleillement.binValue |
						  AnnulerLegume.binValue |
						  ViderCarre.binValue
				  	   );
		
		editorMenu=new EditorMenu(iconesList, true, mainFrame);
		String jardinDatas="";
		plancheDatas=new Titre(jardinDatas, 580, 35, 18.0f, 5, mainFrame);
		carre=new CarreInformations(this, mainFrame);

		editorMenu.setCarreActivate(false);
	}
	/**
	 * Change de le car� observ�
	 * @param c
	 * 			nouveau carr�
	 */
	public void setCarre(Carre c)
	{
		editorMenu.setCarreActivate(carre.setCarre(c,0,planche));
	}
	/**
	 * Cr�e l'image de chacun des carr�s de la planche
	 */
	public void setImage()
	{
		plancheContent.setImage();
	}
	/**
	 * Actualise les informations ayant �t� modifi�es de la planche
	 */
	public void actualiser()
	{
		String jardinDatas=plancheContent.getPlanche().getNom();
		plancheDatas.setValues(jardinDatas, 580, 35, 18.0f, 5);	
	}
	/**
	 * Change le curseur par l'image du plant selectionn�
	 * @param p
	 * 			plant selectionn�
	 */
	public void setCursorIcon(Plant p)
	{
		plancheContent.setCursorIcon(p);
		carre.setAction(3,p);
	}
	/**
	 * Renvoie l'�l�ment parent
	 * @return
	 * 			element parent
	 */
	public PlancheVueContent getPlancheContent()
	{
		return plancheContent;
	}
	/**
	 * Initialise le panel
	 */
	public void initPanel()
	{
		setPreferredSize(new Dimension(584,500));
		setOpaque(false);
		add(plancheDatas);
		add(editorMenu);
		add(plancheContent);
		add(carre);
	}
	/**
	 * Vide le carr� en ajoutant le dernier plant ajout� � l'historique
	 */
	public void viderCarre() {
		carre.viderCarre();
	}
	/**
	 * Inverse l'ensoleillement du carr�
	 */
	public void swapEnsoleillement() {
		carre.swapEnsoleillement();
	}
	/**
	 * Annule la mise en place du plant dans le carr�
	 */
	public void annulerLegumes() {
		carre.annuler();
	}
}
