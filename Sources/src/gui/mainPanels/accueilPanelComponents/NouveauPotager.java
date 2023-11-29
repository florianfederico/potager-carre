package gui.mainPanels.accueilPanelComponents;

import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import objects.Potager;
import gui.Fenetre;

public class NouveauPotager extends PotagerPanel
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * Initialise le panel représentant la création d'un nouveau potager
	 * @param liste
	 * 			Liste des potagers possibles, composant parent
	 * @param f
	 * 			Frame principale
	 * @param p
	 * 			Potager à afficher ici : p=null
	 */
	public NouveauPotager(ListePotagers liste, Fenetre f, Potager p)
	{
		super(liste, f, p);
		initEvents();
		initComponents();	
		setToolTipText(mainFrame.getDatas().getLangueElement("noms.NOUVEAU_POTAGER"));
	}
	public void initComponents() 
	{
		String image=mainFrame.getDatas().getInformation("path.imagesDivers")+mainFrame.getDatas().getInformation("default.nouveauPotager");
		ImageIcon icon = new ImageIcon(new ImageIcon(image).getImage().getScaledInstance(100, 100, Image.SCALE_DEFAULT));
		JLabel plus=new JLabel();
		plus.setBounds(35, 5, 100, 100);
		plus.setIcon(icon);
		setLayout(null);
		add(plus);
		updateUI();
	}
	/**
	 * Action lors de la selection du potager
	 */
	public void setMenuVisible(boolean b)
	{
		
	}
	public void initEvents()
	{
		this.addMouseListener
		(
			new MouseListener()
			{
				//Lorsque l'utilisateur clique
				public void mouseClicked(MouseEvent e)
				{
					mainFrame.initJardin();
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
	}
}
