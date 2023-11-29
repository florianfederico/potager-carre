package gui.components.icones;

import java.awt.Cursor;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.HashMap;

import gui.Fenetre;
import javax.swing.BorderFactory;
import javax.swing.border.EtchedBorder;

/**
 * 
 * <b>AfficheGrille est la classe g�rant l'ic�ne qui affiche la grille.</b>
 * 	<p>
 *  	L'ic�ne � des m�thodes g�rant l'affichage de l'ic�ne et ses int�ractions avec la souris. 
 *  </p>
 * 
 *
 */

public class AfficherGrille extends Icone
{
	/**
	 * 
	 * 
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * 
	 * Le boolean active d�fini l'�tat de l'ic�ne, si il r�agit � un clic.
	 * Active est � 'true' quand l'ic�ne r�agit.
	 * Active est � 'false' quand l'ic�ne ne r�agit pas.
	 * 
	 */
	private boolean active = false;
	
	/**
	 * 
	 * binValue est un entier static
	 * Il repr�sente la valeur binaire de l'ic�ne.
	 * 
	 */
	public static int binValue = Integer.parseInt("10000000", 2);
	
	
	/**
	 * 
	 * alternative est un String
	 * Il permet de changer l'info bulle de l'ic�ne grille en fonction de son �tat.
	 * 
	 */
	
	private String alternative;
	
	/**
	 * 
	 * Constructeur
	 * @param f fen�tre principale
	 * @param params un HashMap contenant les param�tres
	 * 
	 */
	public AfficherGrille(Fenetre f, HashMap<String,String> params)
	{
		super(f, params.get("img"),params.get("desc"));
		alternative=params.get("descAlt");
		binValue=(int)Math.pow(2, Integer.parseInt(params.get("value")));
	}
	/**
	 * 
	 * Constructeur pour cloner l'ic�ne
	 * @param i instance de AfficherGrille
	 * 
	 */
	private AfficherGrille(AfficherGrille i)
	{
		super(i);
		alternative=i.alternative;
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
					mainFrame.setAction(2);
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
					if(!active)
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
					if(!active)
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
	 * @param bool un boolean
	 * Cette m�thode cache ou affiche la grille en fonction de la valeur du boolean
	 * On modifie aussi le design des bordures du jpanel de l'ic�ne		 
	 * 
	 */
	public void setEnabled(boolean bool)
	{
		active=!bool;
		if(active)
		{
			setToolTipText(alternative);
			if(cadre)
				setBorder(BorderFactory.createEtchedBorder(EtchedBorder.RAISED));
		}
		else
		{
			if(cadre)
				setBorder(BorderFactory.createBevelBorder(0));
			setToolTipText(information);
		}
	}
	/**
	 * 
	 * Cette m�thode clone l'ic�ne de mani�re � pouvoir l'utiliser ind�pendamment � plusieurs reprises.
	 * @return i un ic�ne AfficherGrille
	 * 
	 */
	public Icone clone()
	{
		Icone i=new AfficherGrille(this);
		return i;
	}
	/**
	 * 
	 * 	Cette m�thode permet de r�cuperer la valeur binaire pour g�rer l'affichage de l'ic�ne
	 * @return binValue la valeur binaire de l'ic�ne
	 * 
	 */
	public Integer getValue()
	{
		return binValue;
	}
}
