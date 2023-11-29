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
 * <b>AfficheGrille est la classe gérant l'icône qui affiche la grille.</b>
 * 	<p>
 *  	L'icône à des méthodes gérant l'affichage de l'icône et ses intéractions avec la souris. 
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
	 * Le boolean active défini l'état de l'icône, si il réagit à un clic.
	 * Active est à 'true' quand l'icône réagit.
	 * Active est à 'false' quand l'icône ne réagit pas.
	 * 
	 */
	private boolean active = false;
	
	/**
	 * 
	 * binValue est un entier static
	 * Il représente la valeur binaire de l'icône.
	 * 
	 */
	public static int binValue = Integer.parseInt("10000000", 2);
	
	
	/**
	 * 
	 * alternative est un String
	 * Il permet de changer l'info bulle de l'icône grille en fonction de son état.
	 * 
	 */
	
	private String alternative;
	
	/**
	 * 
	 * Constructeur
	 * @param f fenêtre principale
	 * @param params un HashMap contenant les paramètres
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
	 * Constructeur pour cloner l'icône
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
	 * Initialise un évènement
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
			 * Création d'une instance de MouseListener
			 * 
			 */
			new MouseListener()
			{
				//Lorsque l'utilisateur clique
				/**
				 * 
				 * On utilise la fonction setAction de la classe fenêtre (le main frame).
				 * Le frame change en fonction de la méthode adéquate
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
				 * Lorsque la souris passe sur l'icône, on modifie le design du curseur et la bordure du jpanel de l'îcone
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
				 * Lorsque la souris sort du jpanel de l'icône, on modifie le design du curseur et la bordure du jpanel de l'îcone
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
	 * @param bool un boolean
	 * Cette méthode cache ou affiche la grille en fonction de la valeur du boolean
	 * On modifie aussi le design des bordures du jpanel de l'icône		 
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
	 * Cette méthode clone l'icône de manière à pouvoir l'utiliser indépendamment à plusieurs reprises.
	 * @return i un icône AfficherGrille
	 * 
	 */
	public Icone clone()
	{
		Icone i=new AfficherGrille(this);
		return i;
	}
	/**
	 * 
	 * 	Cette méthode permet de récuperer la valeur binaire pour gérer l'affichage de l'icône
	 * @return binValue la valeur binaire de l'icône
	 * 
	 */
	public Integer getValue()
	{
		return binValue;
	}
}
