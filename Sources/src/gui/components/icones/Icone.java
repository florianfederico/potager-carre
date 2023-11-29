package gui.components.icones;

import gui.Fenetre;
import java.awt.Dimension;
import java.awt.Image;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EtchedBorder;

/**
 * 
 * Icone est la classe mère de tous les icônes.
 *  	
 * 
 *
 */

public abstract class Icone extends JPanel implements Cloneable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	protected String information;
	protected String image;
	protected boolean cadre=true;
	protected Fenetre mainFrame;

	/**
	 * 
	 * Constructeur
	 * @param f fenêtre principale
	 * @param img le chemin de l'image de l'icône
	 * @param desc les informations concernant l'icône
	 * 
	 */
	public Icone(Fenetre f, String img, String desc)
	{
		super();
		mainFrame=f;
		information=desc;
		image=mainFrame.getDatas().getInformation("path.imagesIcones")+img;
		cadre=true;
		setToolTipText(information);
		initEvent();
		initPanel();
	}
	
	/**
	 * 
	 * Met en place un cadre autour de l'icône si le paramètre a pour valeur true
	 * @param x
	 * 			true s'il y a un cadre
	 * 
	 */
	public void setCadre(boolean x)
	{
		cadre=x;
		if(!cadre)
			setBorder(null);
	}
	
	/**
	 * 
	 * Constructeur pour cloner l'icône
	 * @param i instance de Icone
	 * 
	 */
	public Icone(Icone i)
	{
		super();
		mainFrame=i.mainFrame;
		information=i.information;
		image=i.image;
		cadre=i.cadre;
		setToolTipText(information);
		initEvent();
		initPanel();
	}
	
	/**
	 * initPanel définie le panel java d'un icône
	 */
	protected void initPanel()
	{
		setOpaque(false);
		setPreferredSize(new Dimension(30,30));
		if(cadre)
			setBorder(BorderFactory.createBevelBorder(0));
		
		ImageIcon icon = new ImageIcon(new ImageIcon(image).getImage().getScaledInstance(30, 30, Image.SCALE_DEFAULT));
		JLabel label=new JLabel();
		label.setBounds(0, 0, 30, 30);
		label.setIcon(icon);
		setLayout(null);
		add(label);
	}
	
	/**
	 * 
	 * Recupère les ingormations de l'icône
	 * @return information
	 * 
	 */
	public String getInformations()
	{
		return information;
	}
	
	/**
	 * 
	 * Permet de rendre l'icône cliquable ou non en fonction de la valeur du paramètre
	 * param bool
	 * 
	 */
	public void setEnabled(boolean bool)
	{
		super.setEnabled(bool);
		if(cadre)
		{
			if(!bool)
				setBorder(BorderFactory.createEtchedBorder(EtchedBorder.RAISED));
			else
				setBorder(BorderFactory.createBevelBorder(0));
		}
	}
	/**
	 * 
	 * @return la valeur binaire de l'icône
	 * 
	 */
	public abstract Integer getValue();
	/**
	 * 
	 * Initialise les événements
	 * 
	 */
	protected abstract void initEvent();
	
	/**
	 * 
	 * clone un icône
	 * 
	 */
	public abstract Icone clone();
}
