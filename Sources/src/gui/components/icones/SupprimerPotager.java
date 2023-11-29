package gui.components.icones;

import java.awt.Cursor;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.HashMap;

import javax.swing.BorderFactory;
import javax.swing.JOptionPane;
import javax.swing.border.EtchedBorder;
import gui.Fenetre;
import gui.components.tools.PlancheMenu;

/**
 * 
 * <b>SupprimerPotager est la classe g�rant l'ic�ne qui permet de supprimer un potager.</b>
 * 	<p>
 *  	L'ic�ne � des m�thodes g�rant l'affichage de l'ic�ne et ses int�ractions avec la souris. 
 *  </p>
 * 
 *
 */
public class SupprimerPotager extends Icone
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static  int binValue;
	public SupprimerPotager(Fenetre f, HashMap<String,String> params)
	{
		super(f, params.get("img"),params.get("desc"));
		binValue=(int)Math.pow(2, Integer.parseInt(params.get("value")));
	}
	/**
	 * 
	 * Constructeur pour cloner l'ic�ne
	 * @param i instance de SupprimerPotager
	 * 
	 */
	public SupprimerPotager(SupprimerPotager i)
	{
		super(i);
	}
	/**
	 * 
	 * Initialise un �v�nement
	 * 
	 */
	public void initEvent()
	{
		this.addMouseListener
		(
			new MouseListener()
			{
				//Lorsque l'utilisateur clique
				public void mouseClicked(MouseEvent e)
				{
					String ObjButtons[]={mainFrame.getDatas().getLangueElement("demandes.QUITTER_OPTION1"), mainFrame.getDatas().getLangueElement("demandes.QUITTER_OPTION2")};
					int PromptResult = JOptionPane.showOptionDialog(null, mainFrame.getDatas().getLangueElement("icones.supprimerPotager"), mainFrame.getDatas().getLangueElement("noms.quitter"), JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE, null, ObjButtons,ObjButtons[1]);
					if(PromptResult==0) 
					{
						boolean contenu=false;
						for(int i=0;i<mainFrame.getContentPane().getComponents().length;i++)
							if(mainFrame.getContentPane().getComponents()[i]==mainFrame.getAccueilPanel())
								contenu=true;
						if(contenu)
							mainFrame.getAccueilPanel().getListePotagers().removePotager();
						else
						{
							if(mainFrame.getPotager().getNomFichier().equals(""))
								JOptionPane.showMessageDialog(((PlancheMenu)getParent()), mainFrame.getDatas().getLangueElement("icones.impossibleSupprPot"), mainFrame.getDatas().getLangueElement("noms.suppressionImpossible"), JOptionPane.ERROR_MESSAGE);
							else
							{
								mainFrame.removePotager(mainFrame.getPotager());
								JOptionPane.showMessageDialog(((PlancheMenu)getParent()), mainFrame.getDatas().getLangueElement("icones.potagerSuppr"), mainFrame.getDatas().getLangueElement("noms.suppressionReussie"), JOptionPane.INFORMATION_MESSAGE);
								mainFrame.updatePanel(mainFrame.getAccueilPanel());
							}
						}
					}
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
	 * Cette m�thode clone l'ic�ne de mani�re � pouvoir l'utiliser ind�pendamment � plusieurs reprises.
	 * @return i un ic�ne SupprimerPotager
	 * 
	 * */
	public Icone clone()
	{
		Icone i=new SupprimerPotager(this);
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
