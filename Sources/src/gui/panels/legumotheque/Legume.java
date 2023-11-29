package gui.panels.legumotheque;


import gui.Fenetre;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Iterator;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EtchedBorder;
import objects.Plant;

public class Legume extends JPanel
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Plant plant;
	private boolean isSelected;
	private LegumothequeContent legumothequeContent;
	private Fenetre mainFrame;
	private JLabel nom;
	private JLabel imageType;
	/**
	 * Création d'une instance graphique d'un plant dans la légumothèque
	 * @param t
	 * 			plant à représenter
	 * @param l
	 * 			partie du contenu de la legumothèque
	 * @param f
	 * 			Frame principale
	 */
	public Legume(Plant t,LegumothequeContent l, Fenetre f)
	{
		super();
		mainFrame=f;
		legumothequeContent=l;
		plant=t;
		isSelected=false;
		initComponents();
		initEvents();
		initPanel();
	}
	/**
	 * Initialisation des composants
	 */
	public void initComponents()
	{
		nom=new JLabel("<html><span style=\"color:#000000; font-size: 10px;\">"+plant+"</span></html>");
		String nomImage=mainFrame.getDatas().getTypeImage(plant.getType());
		ImageIcon icon = new ImageIcon(new ImageIcon(nomImage).getImage().getScaledInstance(30, 30, Image.SCALE_DEFAULT));
		imageType=new JLabel();
		imageType.setIcon(icon);	
	}
	/**
	 * Initialisation du panel
	 */
	public void initPanel()
	{
		setPreferredSize(new Dimension(170,50));
		setBackground(Color.decode(mainFrame.getDatas().getFamilleCouleur(plant.getFamille())));
		setBorder(BorderFactory.createEtchedBorder(EtchedBorder.RAISED));
		setLayout(null);
		add(nom);
		add(imageType);
		imageType.setBounds(140, 10, 30, 30);
		nom.setBounds(5,0,170,50);
	}
	/**
	 * Récupère le plant représenté
	 * @return
	 * 			plant correspondant au panel
	 */
	public Plant getPlant()
	{
		return plant;
	}
	/**
	 * Permet de savoir si le plant est selectionné
	 * @return
	 * 			true si selectionné
	 */
	public boolean isSelected()
	{
		return isSelected;
	}
	/**
	 * Permet de deselectionner le plant
	 */
	public void deSelect()
	{
		isSelected=false;
		setBorder(BorderFactory.createEtchedBorder(EtchedBorder.RAISED));
	}
	/**
	 * Initialise les events
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
					Iterator<Legume> it = legumothequeContent.getLegumes().iterator();
					Legume leg;
					while(it.hasNext())
					{
						leg=it.next();
						if(leg.isSelected()&&leg.getPlant()!=plant)
							leg.deSelect();
					}
					setBorder(BorderFactory.createBevelBorder(0));
					if(isSelected)
					{
						deSelect();
						legumothequeContent.deSelect();
					}
					else
					{
						legumothequeContent.setSelected(plant);
						isSelected=!isSelected;
					}
					legumothequeContent.changerSelection();
				}
				//Lorsque la souris entre dans le panel
				public void mouseEntered(MouseEvent e)
				{
					setBorder(BorderFactory.createBevelBorder(0));
					setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
				}
				//Lorsque la souris sort du panel
				public void mouseExited(MouseEvent e) 
				{
					if(!isSelected)
						setBorder(BorderFactory.createEtchedBorder(EtchedBorder.RAISED));
					setCursor(Cursor.getDefaultCursor());
					
				}
				
				public void mousePressed(MouseEvent e)
				{
				}
				public void mouseReleased(MouseEvent e) 
				{
				}
			}
		);
	}
}
