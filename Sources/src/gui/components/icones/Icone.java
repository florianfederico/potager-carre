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
 * Icone est la classe m�re de tous les ic�nes.
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
	 * @param f fen�tre principale
	 * @param img le chemin de l'image de l'ic�ne
	 * @param desc les informations concernant l'ic�ne
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
	 * Met en place un cadre autour de l'ic�ne si le param�tre a pour valeur true
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
	 * Constructeur pour cloner l'ic�ne
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
	 * initPanel d�finie le panel java d'un ic�ne
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
	 * Recup�re les ingormations de l'ic�ne
	 * @return information
	 * 
	 */
	public String getInformations()
	{
		return information;
	}
	
	/**
	 * 
	 * Permet de rendre l'ic�ne cliquable ou non en fonction de la valeur du param�tre
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
	 * @return la valeur binaire de l'ic�ne
	 * 
	 */
	public abstract Integer getValue();
	/**
	 * 
	 * Initialise les �v�nements
	 * 
	 */
	protected abstract void initEvent();
	
	/**
	 * 
	 * clone un ic�ne
	 * 
	 */
	public abstract Icone clone();
}
