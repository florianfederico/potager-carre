package gui.panels.legumotheque;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Vector;
import gui.Fenetre;
import gui.panels.ScrollablePanel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import common.Main;

import objects.Plant;

public class LegumothequeContent extends JPanel
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JScrollPane scroll;
	private ScrollablePanel list;
	private Legumotheque legumotheque;
	private String famille;
	private String type;
	private ArrayList<Legume>listePlants;
	private ArrayList<Legume> legumes;
	private Plant selectedPlant;
	private Fenetre mainFrame;
	/**
	 * Création de la partie contenu de la legumothèque
	 * @param l
	 * 			Legumothèque complète
	 * @param f
	 * 			Frame principale
	 */
	public LegumothequeContent(Legumotheque l, Fenetre f)
	{
		super();
		mainFrame=f;
		listePlants=new ArrayList<Legume>();
		legumes=new ArrayList<Legume>();
		famille=Main.FAMILLE;
		type=Main.TYPE;
		legumotheque=l;
		selectedPlant=null;
		Vector<Plant> plants=legumotheque.getMainFrame().getDatas().getPlants();
		for(Plant p:plants)
			legumes.add(new Legume(p,this,mainFrame));
		initComponents();
		initPanel();
	}
	/**
	 * Permet de delectionner le plant selectionné
	 */
	public void deSelect()
	{
		legumotheque.deleteInfos();
		Iterator<Legume> it = legumes.iterator();
		Legume leg;
		while(it.hasNext())
		{
			leg=it.next();
			leg.deSelect();
		}
		selectedPlant=null;
	}
	/**
	 * Permet de selectionner un plant
	 * @param p
	 * 			plant selectionné
	 */
	public void setSelected(Plant p)
	{
		selectedPlant=p;
		legumotheque.afficheInfos(p);
	}
	/**
	 * Permet de récupérer le plant selectionné
	 * @return
	 * 			plant selectionné
	 */
	public Plant getSelectedPlant()
	{
		return selectedPlant;
	}
	/**
	 * Permet de récupérer la liste des plants graphiques de la legumothèque
	 * @return
	 * 			liste des plants graphiques
	 */
	public ArrayList<Legume> getLegumes()
	{
		return legumes;
	}
	/**
	 * Fixe une famille de plant à afficher (n'affiche pas les autres familles)
	 * @param f
	 * 			Nom de la famille à afficher
	 */
	public void setFamille(String f)
	{
		famille=f;
		listePlants.clear();
		list.removeAll();
		afficher();
	}
	/**
	 * Fixe un type de plant à afficher (n'affiche pas les autres types)
	 * @param t
	 * 			Nom du type à afficher
	 */
	public void setType(String t)
	{
		type=t;
		listePlants.clear();
		list.removeAll();
		afficher();
	}
	/**
	 * Initialise les composants
	 */
	public void initComponents()
	{
		list=new ScrollablePanel();
		scroll= new JScrollPane();
		scroll.setPreferredSize(new Dimension(190,246));
		scroll.setViewportView(list);
		GridLayout g=new GridLayout(0,1);
		list.setLayout(g);
		list.setOpaque(false);
		afficher();
	}
	/**
	 * Affiche la liste des plants graphiquement
	 */
	public void afficher()
	{
		for(Legume t:legumes)
		{
			if(!famille.equals(Main.FAMILLE))
			{
				if(t.getPlant().getFamille().equals(famille)&&(t.getPlant().getType().equals(type)||type.equals(Main.TYPE)))
					listePlants.add(t);
			}
			else if(!type.equals(Main.TYPE))
			{
				if(t.getPlant().getType().equals(type)&&(t.getPlant().getFamille().equals(famille)||famille.equals(Main.FAMILLE)))
					listePlants.add(t);
			}
			else
				listePlants.add(t);
		}
		int taille=listePlants.size();
		list.setPreferredSize(new Dimension(172,taille*50));
		for(Legume t:listePlants)
			list.add(t);
	}
	/**
	 * Initialise le panel
	 */
	public void initPanel()
	{
		setPreferredSize(new Dimension(200,250));
		setOpaque(false);
		add(scroll);
	}
	/**
	 * Signale la selection d'un plant
	 */
	public void changerSelection()
	{
		legumotheque.changerSelection();
	}
}
