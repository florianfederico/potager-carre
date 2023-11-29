package gui.panels.vues.planche;

import gui.components.icones.*;
import gui.components.tools.PlancheMenu;
import gui.panels.vues.planche.PlancheContent;
import gui.panels.vues.potager.PotagerContent;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import objects.Planche;

public class FullPlanche extends JPanel
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private PlancheMenu plancheMenu;
	private PlancheContent plancheContent;
	private PotagerContent parent;
	private int posSourisX;
	private int posSourisY;
	private Image img;
	public void paintComponent(Graphics g) 
	{
		super.paintComponent(g);
		g.drawImage(img,2,5,null);
    }
	/**
	 * Cr�e une planche graphique modifiable et d�pla�able dans la vue potager
	 * @param jc
	 * 			vue potager, �l�ment contenant
	 * @param p
	 * 			planche associ�e
	 */
	public FullPlanche(PotagerContent jc,Planche p)
	{
		super();
		parent=jc;
		
		img=new ImageIcon(jc.getMainFrame().getDatas().getInformation("path.imagesDivers")+jc.getMainFrame().getDatas().getInformation("default.imagePlanche")).getImage().getScaledInstance
		(
				101, 
				103, 
				Image.SCALE_DEFAULT
		);
		initComponents(p);
		initEvents();
		initPanel();
		setSourisPos();
		setToolTipText(plancheContent.getPlanche().getNom());
	}
	/**
	 * Permet de renvoyer l'objet contenant les icones associ�es �a la planche
	 * @return
	 * 			liste des icones associ�es
	 */
	public PlancheMenu getPlancheMenu()
	{
		return plancheMenu;
	}
	/**
	 * Modifie la position de la souris. Utilis� lors de l'utilisation des maccros pour la d�placer au centre de la planche
	 */
	public void setSourisPos()
	{
		plancheMenu.setVisible(false);
		posSourisX=105/2;
		posSourisY=105/2;
	}
	/**
	 * R�cup�re la position x de la souris
	 * @return
	 * 			position x de la souris
	 */
	public int getSourisPosX()
	{
		return posSourisX;
	}
	/**
	 * R�cup�re la position y de la souris
	 * @return
	 * 			position y de la souris
	 */
	public int getSourisPosY()
	{
		return posSourisY;
	}
	/**
	 * Renvoie la planche observ�e
	 * @return
	 * 			planche observ�e
	 */
	public Planche getPlanche()
	{
		return plancheContent.getPlanche();
	}
	/**
	 * Mets les positions de la planche � jour
	 * @param x
	 * 			position x
	 * @param y
	 * 			position y
	 */
	public void updateBounds(int x,int y)
	{
		plancheContent.updateBounds(x,y);
	}
	/**
	 * Se d�signe en tant que planche selectionn�e dans l'�l�ment parent
	 */
	public void setFullPlanche()
	{
		parent.setFullPlanche(this);
	}
	/**
	 * Se supprimer
	 */
	public void supprimerPlanche()
	{
		parent.supprimerPlanche();			
	}	
	/**
	 * Change l'action en cour de l'utilisateur sur la vue potager sans annuler les autres
	 * @param i
	 * 			ID de l'action
	 */
	public void setActionUnique(int i)
	{
		parent.setActionUnique(i);
	}
	/**
	 * Change l'action en cour de l'utilisateur sur la vue potager et annule les autres
	 * @param i
	 * 			ID de l'action
	 */
	public void setAction(int i)
	{
		parent.setAction(i);
	}	
	/**
	 * Cr��e un pop up de modification d'informations de planche
	 */
	public void updatePlancheInfos()
	{
		parent.updatePlancheInfos();		
	}
	/**
	 * Initialise les composants
	 * @param p
	 * 			planche observ�e
	 */
	public void initComponents(Planche p)
	{
		Integer i = 
					SupprimerPlanche. binValue | 
					DeplacerPlanche.binValue |
					OuvrirPlanche.binValue;
		plancheMenu=new PlancheMenu(i,false,parent.getMainFrame());

		plancheContent=new PlancheContent(this,p);
		plancheMenu.setVisible(false);
	}
	/**
	 * Se selectionne
	 */
	public void select()
	{
		plancheContent.setBackground(Color.GREEN);
		plancheMenu.setVisible(true);
	}
	/**
	 * Se deselectionne
	 */
	public void deSelect()
	{
		plancheContent.setBackground(Color.BLACK);
		plancheMenu.setVisible(false);
	}
	/**
	 * Change la couleur du cadre
	 * @param c
	 * 			couleur du cadre (vert si placement possible, rouge sinon)
	 */
	public void setColor(Color c)
	{
		plancheContent.setBackground(c);
	}
	/**
	 * Initialisation du panel
	 */
	public void initPanel()
	{
		setBounds(plancheContent.getPosX(),plancheContent.getPosY(), 105, 150);	
		setOpaque(false);
		setBackground(Color.BLACK);
		setPreferredSize(new Dimension(105,150));
		if(plancheContent.getY()>44)
		{
			add(plancheMenu);
			add(plancheContent);
		}
		else
		{
			add(plancheContent);
			add(plancheMenu);
		}
	}
	/**
	 * Renvoie la planche graphique compl�te sans le menu d'icones
	 * @return
	 * 			planche graphique
	 */
	public PlancheContent getPlancheContent()
	{
		return plancheContent;
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
		parent.tryPosePlanche(getX()+x,getY()+y);
	}
	/**
	 * Initialise les evenements
	 */
	public void initEvents()
	{
		addMouseListener
		(
			new MouseListener()
			{
				//Lorsque l'utilisateur clique
				public void mouseClicked(MouseEvent e)
				{
					if((((parent.getAction()>>0)&1)==1))
					{
						tryPosePlanche(e.getX(),e.getY());
					}
				}
				//Lorsque la souris entre dans le panel
				public void mouseEntered(MouseEvent e)
				{
					
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
					if((((parent.getAction()>>0)&1)==1))
					{
						parent.getFullPlanche().setBounds(getX()+e.getX()-parent.getFullPlanche().getSourisPosX(),getY()+e.getY()-parent.getFullPlanche().getSourisPosY(), 105, 150);
						changeIcone(getX()+e.getX(),getY()+e.getY());
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
		parent.changeIcone(x, y);
	}
	/**
	 * Envoie l'�l�ment parent
	 * @return
	 * 			�l�ment parent
	 */
	public PotagerContent getParent()
	{
		return parent;
	}
	/**
	 * R�inialise la planche (supprimer les plants sans ajouter � l'historique pour chaque carr�)
	 */
	public void reinitialiserPlanche() {
		plancheContent.reinitialiserPlanche();
	}
	/**
	 * R�inialise la planche (supprimer les plants en ajoutant � l'historique pour chaque carr�)
	 */
	public void viderPlanche() {
		plancheContent.viderPlanche();		
	}
}
