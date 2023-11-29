package gui.components.icones;

import java.awt.Cursor;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.HashMap;

import javax.swing.BorderFactory;
import javax.swing.border.EtchedBorder;
import gui.Fenetre;

/**
 * 
 * <b>Enregistrer est la classe gérant l'icône permettant de sauvegarder le potager.</b>
 * 	<p>
 *  	L'icône à des méthodes gérant l'affichage de l'icône et ses intéractions avec la souris. 
 *  </p>
 * 
 *
 */
public class Enregistrer extends Icone
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * 
	 * binValue est un entier static
	 * Il représente la valeur binaire de l'icône.
	 * 
	 */
	public static int binValue;
	/**
	 * 
	 * Constructeur
	 * @param f fenêtre principale
	 * @param params un HashMap contenant les paramètres
	 * 
	 */
	public Enregistrer(Fenetre f, HashMap<String,String> params)
	{
		super(f, params.get("img"),params.get("desc"));
		binValue=(int)Math.pow(2, Integer.parseInt(params.get("value")));
	}
	/**
	 * 
	 * Constructeur pour cloner l'icône
	 * @param i instance de Enregistrer
	 * 
	 */
	public Enregistrer(Enregistrer i)
	{
		super(i);
	}
	
	/**
	 * 
	 * Initialise un évènement
	 * 
	 */
	public void initEvent()
	{
		/**
		 * 
		 * Utilisation de la fonction java addMouseListener
		 * 
		 */
		this.addMouseListener
		(
			/**
			 * 
			 * Création d'une instance de MouseListener
			 * 
			 */
			new MouseListener()
			{
				//Lorsque l'utilisateur clique
				/**
				 * 
				 * On utilise la fonction setAction de la classe fenêtre (le main frame).
				 * Le frame change en éxécutant la méthode adéquate
				 * @param e un MouseEvent
				 * 
				 */
				public void mouseClicked(MouseEvent e)
				{
					mainFrame.enregistrerJardin();
				}
				//Lorsque la souris entre dans le panel
				/**
				 * 
				 * Lorsque la souris passe sur l'icône, on modifie le design du curseur et la bordure du jpanel de l'îcone
				 * @param e un MouseEvent
				 * 
				 */
				public void mouseEntered(MouseEvent e)
				{
					setBorder(BorderFactory.createEtchedBorder(EtchedBorder.RAISED));
					setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
				}
				//Lorsque la souris sort du panel
				/**
				 * 
				 * Lorsque la souris sort du jpanel de l'icône, on modifie le design du curseur et la bordure du jpanel de l'îcone
				 * @param e un MouseEvent
				 * 
				 */
				public void mouseExited(MouseEvent e) 
				{
					setBorder(BorderFactory.createBevelBorder(0));
					setCursor(Cursor.getDefaultCursor());
				}
				/**
				 * 
				 * Cette méthode est implémenter par Java
				 * @param e un MouseEvent
				 * 
				 */
				public void mousePressed(MouseEvent e)
				{
				}
				/**
				 * 
				 * Cette méthode est implémenter par Java	
				 * @param e un MouseEvent
				 * 
				 * */
				public void mouseReleased(MouseEvent e) 
				{
				}
			}
		);
	}
	/**
	 * 
	 * Cette méthode clone l'icône de manière à pouvoir l'utiliser indépendamment à plusieurs reprises.
	 * @return i un icône Enregistrer
	 * 
	 * */
	public Icone clone()
	{
		Icone i=new Enregistrer(this);
		return i;
	}
	/**
	 * 
	 * 	Cette méthode permet de récuperer la valeur binaire pour gérer l'affichage de l'icône
	 * @return binValue la valeur binaire de l'icône
	 * 
	 * */
	public Integer getValue()
	{
		return binValue;
	}
}
