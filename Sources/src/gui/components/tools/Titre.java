package gui.components.tools;

import gui.Fenetre;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class Titre extends JPanel
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String title;
	private int dimensionX=400;
	private int dimensionY=50;
	private float size=(float)20.0;
	private int align=65;
	private Image img;
	/**
	 * Création d'une barre de titre
	 * @param label
	 * 			Texte affiché
	 * @param f
	 * 			Frame principale
	 */
	public Titre(String label, Fenetre f)
	{
		super();
		img=new ImageIcon(f.getDatas().getInformation("path.imagesDivers")+f.getDatas().getInformation("fonds.titre")).getImage().getScaledInstance(dimensionX, dimensionY, Image.SCALE_DEFAULT);;

		title=label;
		initPanel();
	}
	/**
	 * 
	 * Création d'une barre de titre
	 * @param label
	 * 			Texte affiché
	 * @param dimX
	 * 			Dimension X
	 * @param dimY
	 * 			Dimension Y
	 * @param s
	 * 			Taille de la police
	 * @param a
	 * 			Alignement du texte
	 * @param f
	 * 			Frame principale
	 */
	public Titre(String label, int dimX, int dimY, float s,int a, Fenetre f)
	{
		super();
		img=new ImageIcon(f.getDatas().getInformation("path.imagesDivers")+f.getDatas().getInformation("fonds.titre")).getImage().getScaledInstance(dimensionX, dimensionY, Image.SCALE_DEFAULT);;
		dimensionX=dimX;
		dimensionY=dimY;
		size=s;
		align=a;
		title=label;
		initPanel();
		img=img.getScaledInstance(dimensionX, dimensionY, Image.SCALE_DEFAULT);
	}
	/**
	 * Initialisation du panel
	 */
	protected void initPanel()
	{
		Dimension dimensions=new Dimension(dimensionX,dimensionY);
		setPreferredSize(dimensions);
		setBorder(BorderFactory.createBevelBorder(0));
	}
	/**
	 * Mise a jour du texte
	 * @param t
	 * 			Nouveau texte
	 */
	public void setTitre(String t)
	{
		title=t;
	}
	/**
	 * Mise a jour des valeurs
	 * @param label
	 * 			Texte affiché
	 * @param dimX
	 * 			Dimension X
	 * @param dimY
	 * 			Dimension Y
	 * @param s
	 * 			Taille de la police
	 * @param a
	 * 			Alignement du texte
	 */
	public void setValues(String label, int dimX, int dimY, float s,int a)
	{
		dimensionX=dimX;
		dimensionY=dimY;
		size=s;
		align=a;
		Dimension dimensions=new Dimension(dimensionX,dimensionY);
		setPreferredSize(dimensions);
		title=label;
	}
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		g.drawImage(img,0,0,null);
		setFont(g.getFont().deriveFont(Font.BOLD));
		setFont(g.getFont().deriveFont(size));
		g.drawString(title, align, dimensionY/2+5);
	}
}
