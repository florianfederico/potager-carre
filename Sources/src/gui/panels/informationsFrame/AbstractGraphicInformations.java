package gui.panels.informationsFrame;

import gui.Fenetre;
import gui.components.tools.Button;
import javax.swing.JLabel;
import javax.swing.JPanel;

public abstract class AbstractGraphicInformations extends JPanel
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	protected String DOUBLE_NOM;
	protected String DOUBLE_NOM_MESSAGE;
	protected String CHAMP_VIDE;
	protected String CHAMP_VIDE_MESSAGE;
	protected String TYPE;
	protected JLabel informations;
	protected AbstractInformations informationsPanel;
	protected JLabel obligatoire;
	protected Button valider;
	protected AbstractInformationsFrame jardinInfoFrame;
	
	/**
	 * Crée l'instance des éléments affichés sur la fenetre pop up
	 * @param f
	 * 			Frame principale
	 */
	public AbstractGraphicInformations(Fenetre f)
	{
		super();
		DOUBLE_NOM=f.getDatas().getLangueElement("nom.doubleNom");
		DOUBLE_NOM_MESSAGE=f.getDatas().getLangueElement("erreurs.doubleNom");
		CHAMP_VIDE=f.getDatas().getLangueElement("nom.champVide");
		CHAMP_VIDE_MESSAGE=f.getDatas().getLangueElement("erreurs.champVide");
		TYPE="type";
	}

	/**
	 * Initialise les composants
	 * @param type
	 * 			Type de l'objet analysé (classe)
	 * @param f
	 * 			Frame principale
	 */
	protected void initComponents(String type, Fenetre f)
	{
		informations=new JLabel("<HTML>"+f.getDatas().getLangueElement("frame.informations1")+"<br>"+f.getDatas().getLangueElement("frame.informations2")+type+".</HTML>");
		obligatoire=new JLabel("<HTML><span style=\"color:#FF0000\"><strong>"+f.getDatas().getLangueElement("frame.obligatoire")+"</strong></span><br/></HTML>");
		valider=new Button("Valider");
	}
	/**
	 * Initialise les events
	 */
	protected abstract void initEvents();
	/**
	 * Initialise le panel
	 */
	protected abstract void initPanel();
}