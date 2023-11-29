package gui.components.tools;

import gui.Fenetre;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Image;
import java.util.HashMap;
import java.util.Iterator;
import java.util.TreeMap;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.border.EtchedBorder;

public class EditorMenu extends JPanel
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private TreeMap<Integer,PlancheMenu> planches;
	private TreeMap<Integer,Integer> icones;
	private boolean movable;
	private Fenetre mainFrame;
	private Image img;
	/**
	 * Création d'une barre d'icones
	 * @param p
	 * 			Liste des icones à afficher
	 * @param m
	 * 			Icones sans bordures
	 * @param f
	 * 			Frame principale
	 */
	public EditorMenu(HashMap<Integer,Integer> p, boolean m, Fenetre f)
	{
		super();
		mainFrame=f;
		img=new ImageIcon(f.getDatas().getInformation("path.imagesDivers")+f.getDatas().getInformation("fonds.icones")).getImage().getScaledInstance
		(
				580, 
				43, 
				Image.SCALE_DEFAULT
		);
		movable=m;
		icones=new TreeMap<Integer,Integer>();
		planches=new TreeMap<Integer,PlancheMenu>();
		icones.putAll(p);
		initComponents();
		initPanel();
	}
	/**
	 * Initialisation des composants de la barre d'icones
	 */
	public void initComponents()
	{
		for(Integer i : icones.keySet())
			planches.put(i, new PlancheMenu(icones.get(i), movable, mainFrame));

	}
	/**
	 * Initialisation du panel
	 */
	public void initPanel()
	{
		setPreferredSize(new Dimension(580,43));
		setBorder(BorderFactory.createEtchedBorder(EtchedBorder.RAISED));
		setLayout(new FlowLayout(FlowLayout.LEFT));
		Iterator<Integer> it=planches.keySet().iterator();
		while(it.hasNext())
		{
			add(planches.get(it.next()));
			if(it.hasNext())
				add(Box.createRigidArea(new Dimension(5,5)));
		}
	}
	/**
	 * Actualise l'affichage des icones si une planche est selectionnée ou non
	 * @param bool
	 * 			planche selectionnée ou non
	 */
	public void setPlancheSelectionnee(boolean bool)
	{
		for(PlancheMenu p : planches.values())
			p.setPlancheSelectionnee(bool);
	}
	/**
	 * Permet de bloquer l'utilisation de certaines icones
	 * @param bool
	 * 			blocage ou non
	 */
	public void setLockIcones(boolean bool)
	{
		for(PlancheMenu p : planches.values())
			p.setLockIcones(bool);
	}
	/**
	 * Active ou non les icones liées au carré, s'il y en a un selectionné
	 * @param bool
	 * 			Si un carré est selectionné
	 */
	public void setCarreActivate(boolean bool)
	{
		for(PlancheMenu p : planches.values())
			p.setCarreActivate(bool);
	}
	/**
	 * Active ou non la grille
	 * @param bool
	 * 			Active ou desactive la grille
	 */
	public void setIconeGrilleActive(boolean bool)
	{
		for(PlancheMenu p : planches.values())
			p.setIconeGrilleActive(bool);
	}
	public void paintComponent(Graphics g) 
	{
		super.paintComponent(g);
		g.drawImage(img,0,0,null);
    }

}