package gui.panels.informationsFrame;

import gui.Fenetre;
import javax.swing.JDialog;

public abstract class AbstractInformationsFrame extends JDialog
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	protected AbstractGraphicInformations elementGraphicInfos;
	protected Object element;
	protected Fenetre frame;
	/**
	 * Creation d'une fenetre pop up de demande d'informations
	 * @param mainFrame
	 * 			Frame principale
	 * @param nomFenetre
	 * 			Nom de la fenetre pop up
	 * @param o
	 * 			Element modifié par la fenetre pop up
	 */
	public AbstractInformationsFrame(Fenetre mainFrame,String nomFenetre, Object o)
	{
		super(mainFrame,nomFenetre,true);
		frame=mainFrame;
		element=o;
	}
	/**
	 * Initilialise les composants de la fenetre
	 */
	public abstract void initComponents();
	/**
	 * Donne les informations de la fenetre
	 * @param x
	 * 			taille x
	 * @param y
	 * 			taille y
	 */
	public void initFenetre(int x, int y)
	{
		setSize(x, y);
		setResizable(false);
		setLocationRelativeTo(null);
		setModalityType(ModalityType.APPLICATION_MODAL);
	}
	/**
	 * Renvoie la frame principale
	 * @return
	 * 		Frame principale
	 */
	public Fenetre getFrame()
	{
		return frame;
	}
	/**
	 * Permet de vérifier que l'objet analysé peut être modifié
	 * @param nom
	 * 			nom de l'objet
	 * @return
	 * 			vrai s'il ne peut pas être modifié
	 */
	public abstract boolean nomExiste(String nom);
	/**
	 * Enregistrer les modifications
	 */
	public abstract void validerModification();
	/**
	 * Actualise les données modifiées a partir de la fenetre pop up
	 */
	public void actualiser()
	{
		setAlwaysOnTop(true);
	}
}
