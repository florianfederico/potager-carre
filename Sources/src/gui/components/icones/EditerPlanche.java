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
 * <b>EditerPlanche est la classe g�rant l'ic�ne qui permet d'�diter les informations de la planche selectionn�e.</b>
 * 	<p>
 *  	L'ic�ne � des m�thodes g�rant l'affichage de l'ic�ne et ses int�ractions avec la souris. 
 *  </p>
 * 
 *
 */
public class EditerPlanche extends Icone
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 
	 * binValue est un entier static
	 * Il repr�sente la valeur binaire de l'ic�ne.
	 * 
	 */
	public static int binValue;
	
	/**
	 * 
	 * Constructeur
	 * @param f fen�tre principale
	 * @param params un HashMap contenant les param�tres
	 * 
	 */
	public EditerPlanche(Fenetre f, HashMap<String,String> params)
	{
		super(f, params.get("img"),params.get("desc"));
		binValue=(int)Math.pow(2, Integer.parseInt(params.get("value")));
	}
	
	/**
	 * 
	 * Constructeur pour cloner l'ic�ne
	 * @param i instance de EditerPlanche
	 */
	public EditerPlanche(EditerPlanche i)
	{
		super(i);
	}
	
	/**
	 * 
	 * Initialise un �v�nement
	 * @param none
	 * 
	 */
	protected void initEvent()
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
			 * Cr�ation d'une instance de MouseListener
			 * 
			 */
			new MouseListener()
			{
				
				//Lorsque l'utilisateur clique
				/**
				 * 
				 * On utilise la fonction setAction de la classe fen�tre (le main frame).
				 * Le frame change en fonction de la m�thode ad�quate
				 * @param e un MouseEvent
				 * 
				 */
				public void mouseClicked(MouseEvent e)
				{
					if(isEnabled())
						mainFrame.updatePlancheInfos();
				}
				//Lorsque la souris entre dans le panel
				
				/**
				 * 
				 * Lorsque la souris passe sur l'ic�ne, on modifie le design du curseur et la bordure du jpanel de l'�cone
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
				 * Lorsque la souris sort du jpanel de l'ic�ne, on modifie le design du curseur et la bordure du jpanel de l'�cone
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
				/**
				 * 
				 * Cette m�thode est impl�menter par Java
				 * @param e un MouseEvent
				 * 
				 */
				public void mousePressed(MouseEvent e)
				{
				}
				/**
				 * 
				 * Cette m�thode est impl�menter par Java	
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
	 * Cette m�thode clone l'ic�ne de mani�re � pouvoir l'utiliser ind�pendamment � plusieurs reprises.
	 * @return i un ic�ne EditerPlanche
	 * 
	 * */
	public Icone clone()
	{
		Icone i=new EditerPlanche(this);
		return i;
	}
	
	/**
	 * 
	 * 	Cette m�thode permet de r�cuperer la valeur binaire pour g�rer l'affichage de l'ic�ne
	 * @return binValue la valeur binaire de l'ic�ne
	 * 
	 * */
	public Integer getValue()
	{
		return binValue;
	}
}
