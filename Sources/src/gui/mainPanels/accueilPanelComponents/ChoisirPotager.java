package gui.mainPanels.accueilPanelComponents;


import gui.Fenetre;
import gui.components.icones.OuvrirPotager;
import gui.components.icones.SupprimerPotager;
import gui.components.tools.PlancheMenu;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Iterator;

import javax.swing.BorderFactory;

import objects.Potager;

public class ChoisirPotager extends PotagerPanel
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private PlancheMenu plancheMenu;
	/**
	 * Créé une instance graphique de potager lors du choix sur le panel d'accueil
	 * @param liste
	 * 			Liste des potagers possibles, composant parent
	 * @param f
	 * 			Frame principale
	 * @param p
	 * 			Potager à afficher
	 */
	public ChoisirPotager(ListePotagers liste, Fenetre f, Potager p)
	{
		super(liste, f,p);
		initComponents();
		initEvents();
		initMenu();
	}
	/**
	 * Initialisation du panel
	 */
	public void initMenu()
	{
		add(plancheMenu);
	}
	public void initComponents() 
	{
		Integer i = 
			OuvrirPotager.binValue | 
			SupprimerPotager.binValue;
		plancheMenu=new PlancheMenu(i,false,listePotagers.getMainFrame());
		plancheMenu.setVisible(false);
	}
	/**
	 * Permet d'afficher ou non le menu d'icones (ouvrir/supprimer potager)
	 * @param b
	 * 			Affiche ou non
	 */
	public void setMenuVisible(boolean b)
	{
		plancheMenu.setVisible(b);
	}
	/**
	 * Deselectionne l'élément
	 */
	public void deSelect()
	{
		super.deSelect();
		setMenuVisible(false);
	}
	
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		g.setColor(Color.WHITE);
		setFont(g.getFont().deriveFont(Font.BOLD));
		setFont(g.getFont().deriveFont(20.0f));
		g.drawString(potager.getNom(), 177/2-(potager.getNom().length()*5), 60);
	}
	@Override
	public void initEvents() 
	{
		this.addMouseListener
		(
			new MouseListener()
			{
				
				public void mouseEntered(MouseEvent e) {}
				public void mouseExited(MouseEvent e) {}
				public void mousePressed(MouseEvent e) {}
				public void mouseReleased(MouseEvent e) {}
				public void mouseClicked(MouseEvent e)
				{
					Iterator<PotagerPanel> it = listePotagers.getPotagerPanel().iterator();
					PotagerPanel jp;
					while(it.hasNext())
					{
						jp=it.next();
						if(jp.isSelected()&&jp.getPotager()!=potager)
							jp.deSelect();
					}
					setBorder(BorderFactory.createBevelBorder(0));
					if(isSelected)
						listePotagers.ouvrePotager();
					else
						listePotagers.setSelected(potager);
					isSelected=!isSelected;
					
					setMenuVisible(true);
				}
				
			}
		);
	}
}
