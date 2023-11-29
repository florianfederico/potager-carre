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
 *  <b>RetourPotager est la classe gérant l'icône qui permet de retourner au potager à partir du mode planche.</b>
 * 	<p>
 *  	L'icône à des méthodes gérant l'affichage de l'icône et ses intéractions avec la souris. 
 *  </p>
 * 
 *
 */
public class RetourPotager extends Icone
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
	public RetourPotager(Fenetre f, HashMap<String,String> params)
	{
		super(f, params.get("img"),params.get("desc"));
		binValue=(int)Math.pow(2, Integer.parseInt(params.get("value")));
	}
	/**
	 * 
	 * Constructeur pour cloner l'icône
	 * @param i instance de RetourPotager
	 * 
	 */
	public RetourPotager(Icone i) 
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
			new MouseListener()
			{
				//Lorsque l'utilisateur clique
				public void mouseClicked(MouseEvent e)
				{
					mainFrame.updatePanel(mainFrame.getPotagerPanel());
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
	 * @return i un icône RetourPotager
	 * 
	 * */
	public Icone clone()
	{
		Icone i=new RetourPotager(this);
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
