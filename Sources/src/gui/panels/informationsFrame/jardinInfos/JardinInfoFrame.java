package gui.panels.informationsFrame.jardinInfos;

import gui.Fenetre;
import gui.mainPanels.PotagerPanel;
import gui.panels.informationsFrame.AbstractInformationsFrame;
import objects.Potager;

public class JardinInfoFrame extends AbstractInformationsFrame
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private PotagerPanel potagerPanel;
	/**
	 * Creation d'une fenetre pop up de modification de potager
	 * @param mainFrame
	 * 			Frame principale
	 * @param jop
	 * 			panel parent
	 * @param p
	 * 			potager à editer
	 */
	public JardinInfoFrame(Fenetre mainFrame,PotagerPanel jop,Potager p)
	{
		super(mainFrame, mainFrame.getDatas().getLangueElement("noms.informations"), p);
		potagerPanel=jop;
		element=p;
		initComponents();
		initFenetre();
	}
	/**
	 * Essaye d'afficher le message de positionnement de la boussole (n'affiche rien si elle a déjà été placée)
	 */
	public void setPosBoussole()
	{
		potagerPanel.setPosBoussole();
	}
	public void actualiser()
	{
		super.actualiser();
		potagerPanel.actualiser();
	}
	public boolean nomExiste(String nom)
	{
		return potagerPanel.nomPotagerExiste(nom);
	}
	public void initComponents()
	{
		elementGraphicInfos=new JardinGraphicInfos(this,(Potager)element);
	}
	public void validerModification()
	{
		potagerPanel.validerPotager();
	}
	/**
	 * Initialise la fenetre
	 */
	public void initFenetre()
	{
		super.initFenetre(220,210);
		setContentPane(elementGraphicInfos);
		setVisible(true);
		validate();
	}
}
