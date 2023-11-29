package gui.components.tools;


import gui.panels.vues.potager.PotagerContent;

import java.awt.AWTException;
import java.awt.Cursor;
import java.awt.Image;
import java.awt.Robot;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 * Boussole est la classe permettant la gestion de la boussole dans l'application
 * @author PotagerTeam
 *
 * @see PotagerContent
 */
public class Boussole extends JPanel
{
	/**
	 * Taille de la boussole. Elle est non modifiable.
	 */
	public static final int BOUSSOLE_SIZE=50;
	/**
	 * Identifiant de sérialisation du JPanel.
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * Position de la boussole dans le référentiel x.
	 */
	private int maxX;
	/**
	 * Position de la boussole dans le référentiel Y.
	 */
	private int maxY;
	/**
	 * Détermine si la boussole peut être déplacer.
	 */
	private boolean movable;
	/**
	 * Permet la représentation graphique de la boussole et du jardin.
	 */
	private PotagerContent parent;
	/**
	 * Constructeur Boussole.
	 * <p>
	 * A la construction d'un objet Boussole, l'image de la boussole est chargé.
	 * La boussole est placé à l'endroit indiqué.
	 * </p>
	 * 
	 * @param p
	 * 				Correspond à la représentation graphique de la boussole et du jardin.
	 * @param x
	 * 				Correspond à la position en pixel du point X de la boussole.
	 * @param y
	 * 				Correspond à la position en pixel du point Y de la boussole.
	 * @param b	
	 * 				Correspond à l'autorisation de déplacement de la boussole. 
	 */
	public Boussole(PotagerContent p, int x, int y, boolean b)
	{
		super();
		parent=p;
		movable=b;
		maxX=x-BOUSSOLE_SIZE;
		maxY=y-BOUSSOLE_SIZE;
		initEvents();
		
		//Image de la boussole
		String image=parent.getMainFrame().getDatas().getInformation("path.imagesIcones")+parent.getMainFrame().getDatas().getInformation("default.sud");
		ImageIcon icon = new ImageIcon(new ImageIcon(image).getImage().getScaledInstance(BOUSSOLE_SIZE, BOUSSOLE_SIZE-10, Image.SCALE_DEFAULT));
		JLabel label=new JLabel();
		label.setBounds(0, 10, BOUSSOLE_SIZE, BOUSSOLE_SIZE-10);
		label.setIcon(icon);
		setOpaque(false);
		setLayout(null);
		add(label);
	}
	/**
	 * Ajoute les évenement pour déplacer la boussole. 
	 */
	public void initEvents()
	{
		addMouseListener
		(
			new MouseListener()
			{
				public void mouseClicked(MouseEvent e)
				{
					if(movable)
					{
						try 
						{
							Robot placementSouris = new Robot();
							placementSouris.mouseMove(getLocationOnScreen().x+getWidth()/2, getLocationOnScreen().y+getHeight()/2);
						} 
						catch (AWTException e1) 
						{
							e1.printStackTrace();
						}
					}
				}
				public void mouseEntered(MouseEvent e)
				{
					
					if(movable)
						setCursor(Cursor.getPredefinedCursor(Cursor.MOVE_CURSOR));
				}
				public void mouseExited(MouseEvent e) 
				{
					setCursor(Cursor.getDefaultCursor());
				}
				public void mousePressed(MouseEvent e)
				{
					if(movable)
						parent.setFullPlanche(null);
				}
				public void mouseReleased(MouseEvent e) 
				{
					if(movable)
					{
						String choix[]={parent.getMainFrame().getDatas().getLangueElement("demandes.QUITTER_OPTION1"), parent.getMainFrame().getDatas().getLangueElement("demandes.QUITTER_OPTION2")};
						int PromptResult = JOptionPane.showOptionDialog(null, parent.getMainFrame().getDatas().getLangueElement("potager.placerBoussole"), parent.getMainFrame().getDatas().getLangueElement("noms.boussole"), JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE, null, choix, choix);
						if(PromptResult==0)
						{
							parent.getPotagerVue().getPotager().setBoussole(getX(),getY());
							movable=false;
							parent.getPotagerVue().setPlancheSelectionnee(false);
						}
						else
							setBounds(0,0,50,50);
					}
				}
			}
		);
		addMouseMotionListener
		(
			new MouseMotionListener()
			{
				public void mouseDragged(MouseEvent e)
				{
					if(movable)
					{
						int x=getX();
						int y=getY();
						int posSourisX=e.getX();
						int posSourisY=e.getY();
						int newX=x;
						int newY=y;
						if(x==0&&y==maxY)
							newY=y+posSourisY-getHeight()/2;
						else if(y==0&&x==maxX)
							newX=x+posSourisX-getWidth()/2;
						else if(y==maxY&&x==maxX)
						{
							if(posSourisX<posSourisY)
								newX=x+posSourisX-getWidth()/2;
							else
								newY=y+posSourisY-getHeight()/2;
						}
						
						if(((x==0&&y>0)||(x==maxX&&y>=0))&&(y<maxY))
						{
							newY=y+posSourisY-getHeight()/2;
						}
						else if(((y==0&&x>0)||(y==maxY&&x>=0))&&(x<maxX))
						{
							newX=x+posSourisX-getWidth()/2;
						}
						else if(x==y)
						{
							if(e.getX()>e.getY())
								newX=x+posSourisX-getWidth()/2;						
							else
								newY=y+posSourisY-getHeight()/2;
						}
						if(newX<0)
							newX=0;
						else if(newX>maxX)
							newX=maxX;
						if(newY<0)
							newY=0;
						else if(newY>maxY)
							newY=maxY;
						setBounds(newX,newY,50,50);
	
						if(getX()<maxX&&getY()<maxY&&getX()>0&&getY()>0)
							setBounds(0,0,50,50);
					}
				}
				public void mouseMoved(MouseEvent e) 
				{
				}
			}
		);
	}
}
