package gui.panels.vues.planche;

import gui.Fenetre;
import gui.panels.vues.planche.informations.GraphicPlancheVue;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EtchedBorder;
import objects.Carre;
import objects.Planche;
import objects.Plant;
import objects.Potager;
import tools.Paire;

public class PlancheVueContent extends JPanel
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static final int TAILLE=80;
	private GraphicPlancheVue plancheGraphic;
	private Planche planche;
	private Plant plant;
	private Fenetre mainFrame;
	private JLabel label;	
	private Potager potager;
	private JLabel sud;
	private Paire<Integer,Integer> posSud;
	private Image imgPlanche;
	private Image imgFond;
	public void paintComponent(Graphics g) 
	{
		super.paintComponent(g);
		g.drawImage(imgFond,0,0,null);
		g.drawImage(imgPlanche,18,18,null);
    }
	/**
	 * Crée le partie graphique et modifiable d'une planche
	 * @param f
	 * 			Frame principale
	 * @param p
	 * 			planche observée
	 * @param po
	 * 			potager contenant la planche
	 */
	public PlancheVueContent(Fenetre f, Planche p, Potager po)
	{
		super();
		planche=p;
		potager=po;
		plant=null;
		mainFrame=f;
		imgPlanche=new ImageIcon(mainFrame.getDatas().getInformation("path.imagesDivers")+mainFrame.getDatas().getInformation("fonds.planche")).getImage().getScaledInstance
		(
				365, 
				360, 
				Image.SCALE_DEFAULT
		);
		
		
		
		imgFond=new ImageIcon(mainFrame.getDatas().getInformation("path.imagesDivers")+mainFrame.getDatas().getInformation("fonds.jardin")).getImage().getScaledInstance
		(
				400, 
				400, 
				Image.SCALE_DEFAULT
		);
		initComponents();
		initEvents();
		initPanel();
	}
	/**
	 * renvoie le label contenant l'image du plant à ajouter
	 * @return
	 * 			label contenant l'image du plant
	 */
	public JLabel getPlantLabel()
	{
		return label;
	}
	/**
	 * Renvoie le plant selectionné
	 * @return
	 * 			plant selectionné
	 */
	public Plant getPlant()
	{
		return plant;
	}
	/**
	 * Renvoie la frame principale
	 * @return
	 * 			Frame principale
	 */
	public Fenetre getMainFrame()
	{
		return mainFrame;
	}
	/**
	 * Change la planche observée
	 * @param p
	 * 			nouvelle planche
	 * @param po
	 * 			potager contenant la planche
	 */
	public void setPlanche(Planche p, Potager po)
	{
		planche=p;
		potager=po;
		plancheGraphic.setPlanche(planche);

		posSud=potager.positionBoussole();
		if(plant!=null)
		{
			for(Carre carre : planche.getCarres())
				carre.updatePosePossible(plant, planche, posSud);
		}
		int posX=posSud.getPremier()+1;
		int posY=posSud.getSecond()+1;
		sud.setBounds(posX*175,posY*175,50,50);
	}
	/**
	 * Initialise les composants
	 */
	public void initComponents()
	{
		plancheGraphic=new GraphicPlancheVue(this, planche);
		label=new JLabel();
		sud=new JLabel();
		String image=mainFrame.getDatas().getInformation("path.imagesIcones")+mainFrame.getDatas().getInformation("default.sud");
		ImageIcon icon = new ImageIcon(new ImageIcon(image).getImage().getScaledInstance(40, 40-10, Image.SCALE_DEFAULT));
		sud.setIcon(icon);
	}
	
	/**
	 * Mets les positions de la planche à jour
	 * @param x
	 * 			position x
	 * @param y
	 * 			position y
	 */
	public void updateBounds(int x,int y)
	{
		plancheGraphic.updateBounds(x,y);
	}
	/**
	 * Renvoie la planche observée
	 * @return
	 * 			planche observée
	 */
	public Planche getPlanche()
	{
		return planche;
	}
	/**
	 * Renvoie la position x de la planche
	 * @return
	 * 			position x
	 */
	public int getPosX()
	{
		return plancheGraphic.getPlanche().getX();
	}
	/**
	 * Renvoie la position y de la planche
	 * @return
	 * 			position y
	 */
	public int getPosY()
	{
		return plancheGraphic.getPlanche().getY();
	}
	

	/**
	 * Initialise le panel
	 */
	public void initPanel()
	{
		setPreferredSize(new Dimension(400,400));
		setBorder(BorderFactory.createEtchedBorder(EtchedBorder.RAISED));
		setLayout(null);
		plancheGraphic.setBounds(new Rectangle(45,45,310,310));
		add(label);
		add(plancheGraphic);
		add(sud);
	}

	/**
	 * Crée l'image de chacun des carrés de la planche
	 */
	public void setImage()
	{
		plancheGraphic.setImage();
	}
	/**
	 * Changement du carré observé
	 * @param c
	 * 			carré a observer
	 */
	public void setCarre(Carre c)
	{
		mainFrame.setCarre(c);
	}
	/**
	 * Initialisation des evenements
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
					plancheGraphic.deSelect();
					setCarre(null);
				}
				//Lorsque la souris entre dans le panel
				public void mouseEntered(MouseEvent e)
				{
				}
				//Lorsque la souris sort du panel
				public void mouseExited(MouseEvent e) 
				{
				}
				public void mousePressed(MouseEvent arg0) 
				{
					
				}
				public void mouseReleased(MouseEvent arg0) 
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
					label.setBounds(e.getX()-TAILLE/2,e.getY()-TAILLE/2, TAILLE, TAILLE);
				}
			}
		);
	}
	/**
	 * Envoie la position du sud
	 * @return
	 * 		position (x,y) du sud sur l'application
	 */
	public Paire<Integer, Integer> getPosSud()
	{
		return posSud;
	}
	/**
	 * Change le curseur par l'image du plant selectionné
	 * @param p
	 * 			plant selectionné
	 */
	public void setCursorIcon(Plant p)
	{
		Cursor c;
		if(plant==p || p==null)
		{
			plant=null;
			c=Cursor.getDefaultCursor();
			label.setIcon(null);
		}
		else
		{
			plant=p;
			ImageIcon icon = new ImageIcon
			(
				new ImageIcon(mainFrame.getDatas().getInformation("path.imagesPlants")+plant.getImage()).getImage().getScaledInstance(TAILLE, TAILLE, Image.SCALE_DEFAULT)
			);
			label.setIcon(icon);
			Toolkit toolkit = Toolkit.getDefaultToolkit(); 
			Image image = toolkit.getImage("null");
			Point hotSpot = new Point(20,20); 
			c=toolkit.createCustomCursor(image, hotSpot, "Pencil");
		}
		if(planche!=null)
		{
			for(Carre carre : planche.getCarres())
				carre.updatePosePossible(p, planche, posSud);
		}
		setCursor(c);
		updateUI();
	}
}