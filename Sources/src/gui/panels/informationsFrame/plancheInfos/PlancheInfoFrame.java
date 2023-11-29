package gui.panels.informationsFrame.plancheInfos;

import gui.Fenetre;
import gui.mainPanels.PotagerPanel;
import gui.panels.informationsFrame.AbstractInformationsFrame;
import objects.Planche;

public class PlancheInfoFrame extends AbstractInformationsFrame
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	//private Planche planche;
	private PotagerPanel jardinContent;
	private int ensoleillement;
	/**
	 * Creation d'une fenetre pop up de modification de planche
	 * @param mainFrame
	 * 			Frame principale
	 * @param jop
	 * 			panel parent
	 * @param p
	 * 			potager à editer
	 * @param i
	 * 			ensoleillement du potager
	 */
	public PlancheInfoFrame(Fenetre mainFrame, PotagerPanel jop, Planche p, int i)
	{
		super(mainFrame, mainFrame.getDatas().getLangueElement("noms.informations"), p);
		ensoleillement=i;
		jardinContent=jop;
		element=p;
		initComponents();
		initFenetre();
	}
	/**
	 * Envoie l'ensoleillement du potager
	 * @return
	 * 			ensoleillement du potager
	 */
	public int getEnsoleillement()
	{
		return ensoleillement;
	}
	public boolean nomExiste(String nom)
	{
		return jardinContent.nomPlancheExiste(nom);
	}
	public void initComponents()
	{
		elementGraphicInfos=new PlancheGraphicInfos(this,(Planche) element);
	}
	/**
	 * Initialise la fenetre
	 */
	public void initFenetre()
	{
		super.initFenetre(220,190);
		setContentPane(elementGraphicInfos);
		setVisible(true);
		validate();
	}

	public void validerModification() {
	}
}
