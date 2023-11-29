package gui.mainPanels.accueilPanelComponents;

import gui.Fenetre;
import gui.components.tools.Button;
import gui.components.tools.Titre;
import gui.panels.AbstractDynamicPanel;
import gui.panels.ScrollablePanel;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Vector;
import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JScrollPane;
import objects.Potager;

public class ListePotagers extends AbstractDynamicPanel
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Button btnNew;
	private Button btnValidate;
	private ScrollablePanel list;
	private JScrollPane scroll;
	private Fenetre mainFrame;
	private Titre titre;
	private PotagerPanel potagerPanel;
	private ArrayList<PotagerPanel>listeJardins;
	/**
	 * Création de la liste des potagers selectionnable sur le panel d'accueil de l'application
	 * @param f
	 * 			Frame principale
	 */
	public ListePotagers(Fenetre f)
	{
		super();
		mainFrame=f;
		setPreferredSize(new Dimension(600,350));
		initComponents();
		initEvents();
		initTest();
		initPanel();
	}
	public void initComponents()
	{
		titre=new Titre(mainFrame.getDatas().getLangueElement("titres.LISTE_POTAGER"),550,30,17.4f,10, mainFrame);
		btnNew=new Button(mainFrame.getDatas().getLangueElement("boutons.NOUVEAU_POTAGER"));
		btnValidate=new Button(mainFrame.getDatas().getLangueElement("boutons.VALIDER_POTAGER"));
		listeJardins=new ArrayList<PotagerPanel>();
		load();
	}
	/**
	 * Permet de récupèrer la Frame principale
	 * @return
	 * 			Frame principale
	 */
	public Fenetre getMainFrame()
	{
		return mainFrame;
	}
	/**
	 * Charge la totalité des potagers de l'application
	 */
	public void load()
	{
		listeJardins.clear();
		Vector<Potager> liste=mainFrame.getDatas().getListePotagers();
		for(Potager p:liste)
			listeJardins.add(new ChoisirPotager(this,mainFrame,p));		
	}
	public void initEvents()
	{
		btnNew.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				mainFrame.initJardin();
			}
		});
		
		btnValidate.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				if(potagerPanel!=null)
					ouvrePotager();
			}
		});
	}
	/**
	 * Ouvre le potager selectionné
	 */
	public void ouvrePotager()
	{
		mainFrame.loadPotager(potagerPanel.getPotager());
	}
	/**
	 * Supprimer le potager selectionné
	 */
	public void removePotager()
	{
		mainFrame.removePotager(potagerPanel.getPotager());
		potagerPanel=null;
	}

	public void initPanel()
	{
		removeAll();
		setOpaque(false);
		add(titre);
		add(Box.createRigidArea(new Dimension(700,10)));
		add(scroll);
		add(Box.createRigidArea(new Dimension(700,10)));
		add(btnValidate);
		add(btnNew);
		updateUI();
	}
	/**
	 * Crée les composants affichant la liste des données
	 */
	public void initTest()
	{
		scroll= new JScrollPane()
		{
			/**
			 * 
			 */
			 
			private static final long serialVersionUID = 1L;
			private Image img=new ImageIcon(mainFrame.getDatas().getInformation("path.imagesDivers")+mainFrame.getDatas().getInformation("fonds.jardin")).getImage().getScaledInstance(550, 240, Image.SCALE_DEFAULT);
			public void paintComponent(Graphics g) 
			{
				super.paintComponent(g);
				g.drawImage(img,0,0,null);
		    }
		};
		scroll.setPreferredSize(new Dimension(550,240));
		list=new ScrollablePanel();
		loadJardinsPanel();
		scroll.setViewportView(list);
		
		list.setOpaque(false);
		scroll.setOpaque(false);
		scroll.getViewport().setOpaque(false);
		scroll.setBorder(null);
	}
	/**
	 * Chaque chaque potager et les place dans un panel
	 */
	public void loadJardinsPanel()
	{
		list.removeAll();
		GridLayout g;
		int nbrJardins=listeJardins.size()+1;
		list.setPreferredSize(new Dimension(3*177,(((nbrJardins)/3)+(nbrJardins%3>0?1:0))*120));
		g=new GridLayout(0,3);
		g.setHgap(10);
		g.setVgap(20);
		list.setLayout(g);
		for(PotagerPanel p:listeJardins)
			list.add(p);
		list.add(new NouveauPotager(this, mainFrame, null));
		btnValidate.setForeground(Color.GRAY);
	}
	/**
	 * Selectionne un panel lors du choix d'un potager
	 * @param p
	 * 			potager selectionné
	 */
	public void setSelected(Potager p)
	{
		for(PotagerPanel j : listeJardins)
		{
			if(j.getPotager()==p)
			{
				potagerPanel=j;
				btnValidate.setForeground(Color.BLACK);	
			}
		}
	}
	/**
	 * Deselectionne le potager selectionné
	 */
	public void deSelect()
	{
		if(potagerPanel!=null)
			potagerPanel.deSelect();
		potagerPanel=null;
		btnValidate.setForeground(Color.GRAY);
	}
	/**
	 * Renvoie la liste de toutes les instances graphiques de potager
	 * @return
	 * 			choix graphique de potager
	 */
	public ArrayList<PotagerPanel> getPotagerPanel()
	{
		return listeJardins;
	}
}
