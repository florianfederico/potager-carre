package gui.mainPanels;

import java.awt.Dimension;
import gui.Fenetre;
import gui.components.tools.Quitter;
import gui.components.tools.Titre;
import gui.mainPanels.accueilPanelComponents.ListePotagers;
import gui.panels.AbstractStaticPanel;
import javax.swing.Box;

public class AccueilPanel extends AbstractStaticPanel
{
	private static final long serialVersionUID = 1L;
	private Fenetre mainFrame;
	private Titre titre;
	private ListePotagers listeJardins;
	private Quitter quitter;
	/**
	 * Création d'une instance d'accueil
	 * @param f
	 * 			Frame principale
	 */
	public AccueilPanel(Fenetre f)
	{
		mainFrame=f;
		initComponents();
		initPanel();
	}
	/**
	 * Initialisation des composants
	 */
	public void initComponents()
	{
		titre=new Titre(mainFrame.getDatas().getLangueElement("titres.ACCUEIL_PANEL"), mainFrame);
		listeJardins=new ListePotagers(mainFrame);
		quitter=new Quitter(mainFrame);
	}
	/**
	 * Initialisation du panel
	 */
	public void initPanel()
	{
		setPreferredSize
		(
			new Dimension
			(
				Integer.parseInt(mainFrame.getDatas().getInformation("dimensions.accueilPanel").split(",")[0]),
				Integer.parseInt(mainFrame.getDatas().getInformation("dimensions.accueilPanel").split(",")[1])
			)
		);
		setOpaque(false);
		add(Box.createRigidArea(new Dimension(700,10)));
		add(titre);
		add(Box.createRigidArea(new Dimension(700,20)));
		add(listeJardins);
		add(Box.createRigidArea(new Dimension(700,10)));
		add(quitter);
	}
	/**
	 * Deselection de l'élément selectionné
	 */
	public void deSelect()
	{
		listeJardins.deSelect();
	}
	/**
	 * Renvoie la liste des potagers
	 * @return
	 * 			liste des potagers
	 */
	public ListePotagers getListePotagers()
	{
		return listeJardins;
	}
	/**
	 * Met l'affichage à jour (appelé par les observables)
	 */
	public void updateListe()
	{
		listeJardins.load();
		listeJardins.initTest();
		listeJardins.initPanel();
	}
}