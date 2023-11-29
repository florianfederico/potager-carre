package gui.panels.vues.planche.informations;

import gui.panels.vues.planche.PlancheVueContent;

import java.awt.Color;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.Observable;
import java.util.Observer;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import objects.Carre;
public class GraphicCarreVue extends JPanel implements Observer
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ImageIcon IMPOSSIBLE;
	private ImageIcon POSSIBLE;
	private ImageIcon WARNING;
	private Carre carre;
	private boolean isSelected;
	private GraphicPlancheVue plancheContent;
	private JLabel image;
	private JLabel message;
	/**
	 * Création d'un carré graphique de planche, en vue planche
	 * @param pc
	 * 			élément contenant
	 * @param c
	 * 			carré à représenter
	 */
	public GraphicCarreVue(GraphicPlancheVue pc, Carre c)
	{
		carre=c;
		carre.addObserver(this);
		plancheContent=pc;
		IMPOSSIBLE=new ImageIcon(new ImageIcon
				(
					plancheContent.getPlancheContent().getMainFrame().getDatas().getInformation("path.imagesDivers")+
					plancheContent.getPlancheContent().getMainFrame().getDatas().getInformation("possibilites.impossible")
				).getImage().getScaledInstance(30, 30, Image.SCALE_DEFAULT));

		POSSIBLE=new ImageIcon(new ImageIcon
				(
					plancheContent.getPlancheContent().getMainFrame().getDatas().getInformation("path.imagesDivers")+
					plancheContent.getPlancheContent().getMainFrame().getDatas().getInformation("possibilites.possible")
				).getImage().getScaledInstance(30, 30, Image.SCALE_DEFAULT));

		WARNING=new ImageIcon(new ImageIcon
				(
					plancheContent.getPlancheContent().getMainFrame().getDatas().getInformation("path.imagesDivers")+
					plancheContent.getPlancheContent().getMainFrame().getDatas().getInformation("possibilites.warning")
				).getImage().getScaledInstance(30, 30, Image.SCALE_DEFAULT));

		isSelected=false;
		image=new JLabel();
		message=new JLabel();
		message.setBounds(70,70,30,30);
		setImage();
		image.setBounds((100-80)/2,(100-80)/2,80,80);
		setBorder(BorderFactory.createLineBorder(Color.BLACK));
		initEvents();
		setLayout(null);
		add(image);
		add(message);

	}
	/**
	 * Modifie l'image du carré par l'image du plant s'y trouvant, s'il y en a un
	 */
	public void setImage()
	{
		if((carre.getHistorique().empilementPossible()&1)==1)
		{
			ImageIcon icon = new ImageIcon
			(
				new ImageIcon
				(
					plancheContent.getPlancheContent().getMainFrame().getDatas().getInformation("path.imagesPlants")+
					carre.getHistorique().getSommet().getImage()
				).getImage().getScaledInstance
				(
					PlancheVueContent.TAILLE, 
					PlancheVueContent.TAILLE, 
					Image.SCALE_DEFAULT
				)
			);	
			image.setIcon(icon);
		}
		else
			image.setIcon(null);
		if(carre.getHistorique().empilementPossible()==0)
			setBackground(Color.decode("#A52A2A"));
		else
			setBackground(Color.decode(plancheContent.getPlancheContent().getMainFrame().getDatas().getFamilleCouleur(carre.getHistorique().getSommet().getFamille())));

	}
	/**
	 * Place l'icone de conseil de pose dans le carré (aucune icone | warning | impossible | possible)
	 */
	public void setMessage()
	{
		if(carre.getPossibilites()!=-1)
		{
			ImageIcon icon=null;
			if(carre.getPossibilites()==1)
				icon=IMPOSSIBLE;
			else if(carre.getPossibilites()==0)
				icon=POSSIBLE;
			else
				icon=WARNING;
			message.setIcon(icon);
			add(message);
		}
		else
			remove(message);	
	}
	/**
	 * Permet de savoir si le carré est selectionné
	 * @return
	 * 			true si le carré est selectionné
	 */
	public boolean isSelected()
	{
		return isSelected;
	}
	/**
	 * Permet de deselectionner le carré
	 */
	public void deSelect()
	{
		isSelected=false;
		setBorder(BorderFactory.createLineBorder(Color.BLACK));
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
					if(plancheContent.getPlancheContent().getPlant()==null)
					{
						GraphicCarreVue liste[] = plancheContent.getCarres();
						for(int i=0;i<liste.length;i++)
						{
							if(liste[i].isSelected()&&liste[i].carre!=carre)
								liste[i].deSelect();
						}
						setBorder(BorderFactory.createBevelBorder(0));
						plancheContent.getPlancheContent().setCarre(carre);
						isSelected=!isSelected;
					}
					else
					{
						int p=carre.getPossibilites();
						boolean warningChoix=true;
						if((p&1)==1)
							JOptionPane.showMessageDialog(plancheContent, plancheContent.getPlancheContent().getMainFrame().getDatas().getLangueElement("erreurs.dejaPresent"),plancheContent.getPlancheContent().getMainFrame().getDatas().getLangueElement("noms.erreur"),JOptionPane.ERROR_MESSAGE);						
						else
						{
							if(p!=0)
							{
								String choix[]={"Oui","Non"};
								int PromptResult = JOptionPane.showOptionDialog(null,plancheContent.getPlancheContent().getMainFrame().getDatas().getLangueElement("erreurs.continuer"), plancheContent.getPlancheContent().getMainFrame().getDatas().getLangueElement("noms.warning"), JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, choix, choix);
								if(PromptResult==1)
									warningChoix=false;
							}
							if(warningChoix)
							{
								carre.ajouterHistorique(plancheContent.getPlancheContent().getPlant());
								plancheContent.getPlancheContent().getMainFrame().getLegumotheque().deselect();
							}
						}
					}
				}
				//Lorsque la souris entre dans le panel
				public void mouseEntered(MouseEvent e)
				{
					if(!isSelected)
						setBorder(BorderFactory.createEtchedBorder(0));
				}
				//Lorsque la souris sort du panel
				public void mouseExited(MouseEvent e) 
				{
					if(!isSelected)
						setBorder(BorderFactory.createLineBorder(Color.BLACK));
					
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
					int taille=PlancheVueContent.TAILLE;
					plancheContent.getPlancheContent().getPlantLabel().setBounds(getX()+e.getX()+5,getY()+e.getY()+5, taille, taille);
				}
			}
		);
	}
	@Override
	public void update(Observable o, Object arg) 
	{
		setImage();
		setMessage();
	}
}
