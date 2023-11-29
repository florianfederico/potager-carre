package gui.panels.vues.potager;

import java.awt.AWTException;
import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Vector;
import gui.Fenetre;
import gui.components.tools.Boussole;
import gui.panels.informationsFrame.plancheInfos.PlancheInfoFrame;
import gui.panels.vues.PotagerVue;
import gui.panels.vues.planche.FullPlanche;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EtchedBorder;
import objects.Planche;
import objects.Potager;

public class PotagerContent  extends JPanel
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Image img;
	private Potager potager;
	private PotagerVue potagerVue;
	private ArrayList<FullPlanche> planches;
	private FullPlanche fullPlanche;
	private Boussole boussole;
	private int action;//0=rien, 1=déplacer, 2=déplacer+nommer,3=grille
	/**
	 * Crée l'apperçu graphique du contenu du potager
	 * @param p
	 * 			potager observé
	 * @param v
	 * 			vue potager globale : element parent
	 */
	public PotagerContent(Potager p, PotagerVue v)
	{
		super();
		action=0;
		potager=p;
		potagerVue=v;
		planches=new ArrayList<FullPlanche>();
		fullPlanche=null;
		img=new ImageIcon(v.getMainFrame().getDatas().getInformation("path.imagesDivers")+v.getMainFrame().getDatas().getInformation("fonds.jardin")).getImage().getScaledInstance
		(
				580, 
				402, 
				Image.SCALE_DEFAULT
		);
		initComponents();
		initPanel();
		initEvents();
	}
	/**
	 * Envoie la frame principale de l'application
	 * @return
	 * 			Frame principale
	 */
	public Fenetre getMainFrame()
	{
		return potagerVue.getMainFrame();
	}
	/**
	 * Initisalise les composants
	 */
	public void initComponents()
	{
		Vector<Planche> jardinPlanchesList=potager.getPlanches();
		for(Planche p:jardinPlanchesList)
			planches.add(new FullPlanche(this,p));
		boussole=new Boussole(this,580,402,potager.isMovable());
		boussole.setBounds(potager.getBoussolePosX(),potager.getBoussolePosY(),Boussole.BOUSSOLE_SIZE,Boussole.BOUSSOLE_SIZE);

	}

	/**
	 * Affiche le message de pose de la boussole si elle n'a jamais été déplacée
	 */
	public void setFirstPosBoussole()
	{
		if(potager.isMovable())
			JOptionPane.showMessageDialog(potagerVue.getMainFrame(), potagerVue.getMainFrame().getDatas().getLangueElement("potager.messageBoussole"), potagerVue.getMainFrame().getDatas().getLangueElement("noms.boussole"), JOptionPane.INFORMATION_MESSAGE);
	}
	/**
	 * Initialisation du panel
	 */
	public void initPanel()
	{
		setLayout(null);
		setBorder(BorderFactory.createEtchedBorder(EtchedBorder.RAISED));
		setPreferredSize(new Dimension(580,402));
		add(boussole);
		for(FullPlanche gp:planches)
			add(gp);
	}

	/**
	 * Change l'action en cour de l'utilisateur sur la vue potager et annule les autres
	 * @param i
	 * 			ID de l'action
	 */
	public void setAction(int i)
	{
		action^=i;
		updateAction();
	}
	/**
	 * Change l'action en cour de l'utilisateur sur la vue potager sans annuler les autres
	 * @param i
	 * 			ID de l'action
	 */
	public void setActionUnique(int i)
	{
		action|=i;
		updateAction();
	}
	/**
	 * Met a jour les actions effectuées
	 */
	private void updateAction()
	{
		if(((action>>0)&1)==1)
		{	
			try
			{
				int posX=fullPlanche.getLocationOnScreen().x+fullPlanche.getSourisPosX()+2;
				int posY=fullPlanche.getLocationOnScreen().y+fullPlanche.getSourisPosY()+2;
				Robot placementSouris = new Robot();
				placementSouris.mouseMove(posX, posY);
				fullPlanche.getPlancheMenu().setVisible(false);
				potagerVue.setPlancheSelectionnee(true);
			}
			catch (AWTException e)
			{
				e.printStackTrace();
			}
		}
		potagerVue.setIconeGrilleActive(((action>>1)&1)==1);
		updateUI();
	}
	
	/**
	 * Renvoie l'objet contenant
	 * @return
	 * 			vue potager globale
	 */
	public PotagerVue getPotagerVue()
	{
		return potagerVue;
	}
	/**
	 * Ajout d'une planche au potager
	 */
	public void ajoutPlanche()
	{
		Planche p=new Planche("",0,0,-1);
		potager.ajoutPlanche(p);
		FullPlanche gp=new FullPlanche(this,p);
		planches.add(gp);
		add(gp);
		setFullPlanche(gp);
		setActionUnique(3);
	}
	/**
	 * Créée un pop up de modification d'informations de planche
	 */
	public void updatePlancheInfos()
	{
		new PlancheInfoFrame(potagerVue.getMainFrame(), potagerVue.getMainFrame().getPotagerPanel(),fullPlanche.getPlanche(),potager.getEnsoleillement());
	}
	/**
	 * Supprime la planche selectionnée
	 */
	public void supprimerPlanche()
	{
		potager.removePlanche(fullPlanche.getPlanche());
		planches.remove(fullPlanche);
		fullPlanche.deSelect();
		potagerVue.setPlancheSelectionnee(false);
		remove(fullPlanche);
		action=0;
		fullPlanche=null;
		updateUI();
	}
	/**
	 * met a jour l'affichage des planches
	 */
	public void updatePlanches()
	{
		if(fullPlanche!=null)
		{
			fullPlanche.deSelect();
			potagerVue.setPlancheSelectionnee(false);
			fullPlanche=null;
		}
		planches.clear();
		removeAll();
		add(boussole);
		updateUI();
	}
	/**
	 * Change la planche selectionnée
	 * @param planche
	 * 			nouvelle planche selectionnée
	 */
	public void setFullPlanche(FullPlanche planche)
	{
		if(fullPlanche!=null)
			fullPlanche.deSelect();
		if(fullPlanche!=planche&&planche!=null)
		{
			planche.select();
			fullPlanche=planche;
			Component[] p=getComponents();
			for(int x=0;x<p.length;x++)
			{
				if(p[x]!=fullPlanche)
				{
					remove(p[x]);
					add(p[x]);
				}
			}
			potagerVue.setPlancheSelectionnee(true);
		}
		else
		{
			if(fullPlanche != null)
			{
				fullPlanche=null;
				potagerVue.setPlancheSelectionnee(false);
			}
		}
	}
	/**
	 * Initialialise les events
	 */
	public void initEvents()
	{
		this.addMouseListener
		(
			new MouseListener()
			{
				//Lorsque l'utilisateur clique
				public void mouseClicked(MouseEvent e)
				{
					if(fullPlanche!=null&&(((action>>0)&1)==1))
						tryPosePlanche(e.getX(),e.getY());
					else
						setFullPlanche(null);
				}
				//Lorsque la souris entre dans le panel
				public void mouseEntered(MouseEvent e)
				{
					setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));		
				}
				//Lorsque la souris sort du panel
				public void mouseExited(MouseEvent e) 
				{			
				}
				
				public void mousePressed(MouseEvent e)
				{
				}
				public void mouseReleased(MouseEvent e) 
				{
				}
			}
		);
		addMouseMotionListener
		(
			new MouseMotionListener()
			{
				public void mouseDragged(MouseEvent e)
				{
		
				}

				public void mouseMoved(MouseEvent e) 
				{
					if(fullPlanche!=null&&(((action>>0)&1)==1))
					{
						remove(fullPlanche);
						add(fullPlanche);
						fullPlanche.setBounds(e.getX()-fullPlanche.getSourisPosX(),e.getY()-fullPlanche.getSourisPosY(), 105, 150);

						changeIcone(e.getX(),e.getY());
					}
					
				}
			}
		);
	}
	/**
	 * Change l'icone du curseur
	 * @param x
	 * 			position x du curseur
	 * @param y
	 * 			position y du curseur
	 */
	public void changeIcone(int x, int y)
	{
		boolean possible=isZoneLibre(x,y); 
		Toolkit toolkit = Toolkit.getDefaultToolkit(); 
		Image image = toolkit.getImage(potagerVue.getMainFrame().getDatas().getInformation("path.imagesCurseur")+potagerVue.getMainFrame().getDatas().getInformation("curseur."+(possible?"valide":"remove")));
		fullPlanche.setColor(possible?Color.GREEN:Color.RED);
		Point hotSpot = new Point(16,16); 
		Cursor cursor = toolkit.createCustomCursor(image, hotSpot, "Pencil");
		setCursor(cursor);
	}
	/**
	 * Permet de voir s'il est possible de placer une planche
	 * @param x
	 * 			position x
	 * @param y
	 * 			position y
	 * @return
	 * 			true si possible
	 */
	private boolean isZoneLibre(int x, int y)
	{
		boolean possible=true;

		int posX;
		int posY;
		FullPlanche p=null;
		if(x-fullPlanche.getPlancheContent().getWidth()/2<-10||x+fullPlanche.getPlancheContent().getWidth()/2>getWidth()-10)
		{
			possible=false;
		}
		if(y-fullPlanche.getPlancheContent().getHeight()/2<-10||y+fullPlanche.getPlancheContent().getHeight()/2>getHeight()-10)
		{
			possible=false;
		}
		for(Iterator<FullPlanche> it=planches.iterator();it.hasNext()&&possible;)
		{
			p=it.next();
			if(p!=fullPlanche)
			{
				posX=p.getX()+p.getPlancheContent().getWidth()/2;
				posY=p.getY()+p.getPlancheContent().getHeight()/2;
				possible=!(x<(posX+p.getPlancheContent().getWidth())&&x>(posX-p.getPlancheContent().getWidth())&&(y<(posY+p.getPlancheContent().getHeight())&&y>(posY-p.getPlancheContent().getHeight())));
			}
		}
		
		return possible;
	}
	/**
	 * Essaye de poser une planche
	 * @param x
	 * 			position x de la planche
	 * @param y
	 * 			position y de la planche
	 */
	public void tryPosePlanche(int x, int y)
	{
		boolean possible=isZoneLibre(x,y);
		if(possible)
		{
			fullPlanche.setBounds(x-fullPlanche.getSourisPosX()+5,y-fullPlanche.getSourisPosY()+5, 105, 150);
			fullPlanche.updateBounds(fullPlanche.getX(),fullPlanche.getY());
			fullPlanche.getPlancheMenu().setVisible(true);
			action=0;
			potagerVue.setIconeGrilleActive(false);
			setCursor(Cursor.getDefaultCursor());	
			if(fullPlanche.getPlanche().getEnsoleillement()==-1)
				updatePlancheInfos();
		}
		updateUI();
	}
	/**
	 * permet de récupérer l'action en cour
	 * @return
	 * 			action en cour
	 */
	public int getAction()
	{
		return action;
	}

	/**
	 * Permet de récupérer la planche selectionnée
	 * @return
	 * 			affichage graphique de la planche selectionnée
	 */
	public FullPlanche getFullPlanche()
	{
		return fullPlanche;
	}
	
	
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		g.drawImage(img,0,0,null);
		g.setColor(Color.decode("#CC6600"));
		if(((action>>1)&1)==1)
		{
			for(int i=0;i<580;i+=30)
				g.drawLine(i, 0, i, 402);
			for(int i=0;i<402;i+=30)
				g.drawLine(0, i, 580, i);
		}
	}
	/**
	 * Réinialise la planche (supprimer les plants sans ajouter à l'historique pour chaque carré)
	 */
	public void reinitialiserPlanche() {
		fullPlanche.reinitialiserPlanche();
	}
	/**
	 * Réinialise la planche (supprimer les plants en ajoutant à l'historique pour chaque carré)
	 */
	public void viderPlanche() {
		fullPlanche.viderPlanche();
	}
}
