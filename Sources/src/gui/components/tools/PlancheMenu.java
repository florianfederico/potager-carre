package gui.components.tools;

import gui.Fenetre;
import gui.components.icones.*;
import java.awt.GridLayout;
import java.util.ArrayList;
import javax.swing.JPanel;


public class PlancheMenu extends JPanel
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ArrayList<Icone> icones;
	private Fenetre mainFrame;
	/**
	 * Créé un groupement d'icones
	 * @param f
	 * 			Frame principale
	 */
	public PlancheMenu(Fenetre f)
	{
		super();
		mainFrame=f;
		icones=new ArrayList<Icone>();
		setLayout(new GridLayout(1,0));
		setOpaque(false);
		initComponents();
	}
	/**
	 * Créé un groupement d'icones
	 * @param i
	 * 			Liste d'icones (bitmask)
	 * @param x
	 * 			icones possedant un cadre
	 * @param f
	 * 			Frame principale
	 */
	public PlancheMenu(Integer i, boolean x, Fenetre f)
	{
		super();
		mainFrame=f;
		icones=new ArrayList<Icone>();
		icones=mainFrame.getIconeManager().getList(i);
		for(int ic=0;ic<icones.size();ic++)
			icones.get(ic).setCadre(x);
		setLayout(new GridLayout(1,0));
		setOpaque(false);
		initComponents();
	}
	/**
	 * Ininitalise les icones
	 */
	public void initComponents()
	{
		for(Icone i:icones)
			add(i);
	}
	/**
	 * Actualise l'affichage des icones si une planche est selectionnée ou non
	 * @param bool
	 * 			planche selectionnée ou non
	 */
	public void setPlancheSelectionnee(boolean bool)
	{
		setIconesActive(!bool, (SupprimerPlanche.binValue|DeplacerPlanche.binValue|EditerPlanche.binValue|ReinitialiserPlanche.binValue|ViderPlanche.binValue|OuvrirPlanche.binValue));
		setIconesActive(bool, NouvellePlanche.binValue);
	}
	/**
	 * Permet de bloquer l'utilisation de certaines icones
	 * @param bool
	 * 			blocage ou non
	 */
	public void setLockIcones(boolean bool)
	{
		setIconesActive(bool, (NouvellePlanche.binValue|SupprimerPlanche.binValue|DeplacerPlanche.binValue|EditerPlanche.binValue|ReinitialiserPlanche.binValue|ViderPlanche.binValue|OuvrirPlanche.binValue));
	}

	/**
	 * Active ou non les icones liées au carré, s'il y en a un selectionné
	 * @param bool
	 * 			Si un carré est selectionné
	 */
	public void setCarreActivate(boolean bool)
	{
		setIconesActive(!bool, (ModifierEnsoleillement.binValue|ViderCarre.binValue|AnnulerLegume.binValue));
	}
	/**
	 * Active ou non la grille
	 * @param bool
	 * 			Active ou desactive la grille
	 */
	public void setIconeGrilleActive(boolean bool)
	{
		setIconesActive(bool, AfficherGrille.binValue);
	}
	/**
	 * Active ou desactive des icones
	 * @param bool
	 * 			Active ou desactive
	 * @param iconList
	 * 			Liste d'icones concernées (bitmask)
	 */
	public void setIconesActive(boolean bool, Integer iconList)
	{
		for(Icone i:icones)
		{
			if((i.getValue()&iconList)!=0)
				i.setEnabled(!bool);
		}
	}
}
