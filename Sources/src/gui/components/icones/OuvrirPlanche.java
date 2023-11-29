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
 * <b>OuvrirPlanche est la classe gérant l'icône qui permet d'ouvrir la planche.</b>
 * 	<p>
 *  	L'icône à des méthodes gérant l'affichage de l'icône et ses intéractions avec la souris. 
 *  </p>
 * 
 *
 */
public class OuvrirPlanche extends Icone
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static int binValue;

	public OuvrirPlanche(Fenetre f, HashMap<String,String> params)
	{
		super(f, params.get("img"),params.get("desc"));
		binValue=(int)Math.pow(2, Integer.parseInt(params.get("value")));
	}
	
	/**
	 * 
	 * Constructeur pour cloner l'icône
	 * @param i instance de OuvrirPlanche
	 * 
	 */
	public OuvrirPlanche(OuvrirPlanche i)
	{
		super(i);
	}
	
	/**
	 * 
	 * Initialise un évènement
	 * @param none
	 * 
	 */
	protected void initEvent() 
	{
		this.addMouseListener
		(
			/**
			 * 
			 * On utilise la fonction setAction de la classe fenêtre (le main frame).
			 * Le frame change en fonction de la méthode adéquate
			 * @param e un MouseEvent
			 * 
			 */
			new MouseListener()
			{
				//Lorsque l'utilisateur clique
				public void mouseClicked(MouseEvent e)
				{
					if(isEnabled())
						mainFrame.ouvrirPlanche();
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
					if(isEnabled())
					{
						if(cadre)
							setBorder(BorderFactory.createEtchedBorder(EtchedBorder.RAISED));
						setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
					}
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
					if(isEnabled())
					{
						if(cadre)
							setBorder(BorderFactory.createBevelBorder(0));
						setCursor(Cursor.getDefaultCursor());
					}
				}
				public void mousePressed(MouseEvent e)
				{
				}
				public void mouseReleased(MouseEvent e) 
				{
				}				
			}
		);
	}
	/**
	 * 
	 * Cette méthode clone l'icône de manière à pouvoir l'utiliser indépendamment à plusieurs reprises.
	 * @return i un icône OuvrirPlanche
	 * 
	 * */
	public Icone clone()
	{
		Icone i=new OuvrirPlanche(this);
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
