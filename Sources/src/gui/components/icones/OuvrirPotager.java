package gui.components.icones;

import java.awt.Cursor;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.HashMap;

import javax.swing.BorderFactory;
import javax.swing.JOptionPane;
import javax.swing.border.EtchedBorder;
import gui.Fenetre;

/**
 * 
 * <b>OuvrirPotager est la classe gérant l'icône qui permet d'ouvrir un potager.</b>
 * 	<p>
 *  	L'icône à des méthodes gérant l'affichage de l'icône et ses intéractions avec la souris. 
 *  </p>
 * 
 *
 */

public class OuvrirPotager extends Icone
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
	public OuvrirPotager(Fenetre f, HashMap<String,String> params)
	{
		super(f, params.get("img"),params.get("desc"));
		binValue=(int)Math.pow(2, Integer.parseInt(params.get("value")));
	}
	
	/**
	 * 
	 * Constructeur pour cloner l'icône
	 * @param i instance de OuvrirPotager
	 * 
	 */
	public OuvrirPotager(OuvrirPotager i)
	{
		super(i);
	}
	public void initEvent()
	{
		this.addMouseListener
		(
			new MouseListener()
			{
				//Lorsque l'utilisateur clique
				/**
				 * 
				 * On utilise la fonction setAction de la classe fenêtre (le main frame).
				 * On vérifie à partir d'où on a créé le potager puis on envoit les textes correspondants.
				 * @param e un MouseEvent
				 * 
				 */
				public void mouseClicked(MouseEvent e)
				{
					boolean contenu=false;
					for(int i=0;i<mainFrame.getContentPane().getComponents().length;i++)
						if(mainFrame.getContentPane().getComponents()[i]==mainFrame.getAccueilPanel())
							contenu=true;
					if(contenu)
						mainFrame.getAccueilPanel().getListePotagers().ouvrePotager();
					else
					{
						String ObjButtons[]={mainFrame.getDatas().getLangueElement("demandes.QUITTER_OPTION1"), mainFrame.getDatas().getLangueElement("demandes.QUITTER_OPTION2")};
						int PromptResult = JOptionPane.showOptionDialog(null, mainFrame.getDatas().getLangueElement("icones.sauvegarde"), mainFrame.getDatas().getLangueElement("noms.quitter"), JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE, null, ObjButtons,ObjButtons[1]);
						if(PromptResult==0) 
							mainFrame.enregistrerJardin();
						mainFrame.updatePanel(mainFrame.getAccueilPanel());
					}
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
	 * @return i un icône OuvrirPotager
	 * 
	 * */
	public Icone clone()
	{
		Icone i=new OuvrirPotager(this);
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
