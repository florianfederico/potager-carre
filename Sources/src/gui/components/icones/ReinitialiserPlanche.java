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
 * <b>ReinitialiserPlanche est la classe g�rant l'ic�ne qui permet de r�initialiser une planche.</b>
 * 	<p>
 *  	L'ic�ne � des m�thodes g�rant l'affichage de l'ic�ne et ses int�ractions avec la souris. 
 *  </p>
 * 
 *
 */
public class ReinitialiserPlanche extends Icone
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
	public ReinitialiserPlanche(Fenetre f, HashMap<String,String> params)
	{
		super(f, params.get("img"),params.get("desc"));
		binValue=(int)Math.pow(2, Integer.parseInt(params.get("value")));
	}
	/**
	 * 
	 * Constructeur pour cloner l'ic�ne
	 * @param i instance de ReinitialiserPlanche
	 * 
	 */
	public ReinitialiserPlanche(ReinitialiserPlanche i)
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
		this.addMouseListener
		(
			new MouseListener()
			{
				//Lorsque l'utilisateur clique
				public void mouseClicked(MouseEvent e)
				{
					if(isEnabled())
						mainFrame.reinitialiserPlanche();
				}
				
				//Lorsque la souris entre dans le panel
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
				public void mouseExited(MouseEvent e) 
				{
					if(isEnabled())
					{
						if(cadre)
							setBorder(BorderFactory.createBevelBorder(0));
						setCursor(Cursor.getDefaultCursor());
					}
				}
				
				public void mousePressed(MouseEvent e) {
					
				}
				
				public void mouseReleased(MouseEvent e) {
					
				}
			}
		);
	}
	/**
	 * 
	 * Cette m�thode clone l'ic�ne de mani�re � pouvoir l'utiliser ind�pendamment � plusieurs reprises.
	 * @return i un ic�ne ReinitialiserPlanche
	 * 
	 * */
	public Icone clone()
	{
		Icone i=new ReinitialiserPlanche(this);
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
