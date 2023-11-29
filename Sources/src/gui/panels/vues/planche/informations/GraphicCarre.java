package gui.panels.vues.planche.informations;


import java.awt.Color;
import java.awt.Image;
import java.util.Observable;
import java.util.Observer;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import objects.Carre;
import gui.panels.vues.planche.PlancheContent;

public class GraphicCarre extends JPanel implements Observer
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Carre carre;
	private PlancheContent plancheContent;
	private JLabel image;
	/**
	 * Création d'un carré graphique de planche, en vue potager
	 * @param pc
	 * 			élément contenant
	 * @param c
	 * 			carré à représenter
	 */
	public GraphicCarre(PlancheContent pc, Carre c)
	{
		carre=c;
		carre.addObserver(this);
		plancheContent=pc;
		image=new JLabel();
		image.setBounds(0,0,20,20);
		if(carre.getHistorique().empilementPossible()==0)
			//setOpaque(false);
			setBackground(Color.decode("#A52A2A"));
		else
		{
			//setOpaque(true);
			setBackground(Color.decode(plancheContent.getFullPlanche().getParent().getMainFrame().getDatas().getFamilleCouleur(carre.getHistorique().getSommet().getFamille())));
		}
		setBorder(BorderFactory.createLineBorder(Color.BLACK));
		add(image);
		setImage();
	}
	/**
	 * Modifie l'image du carré par l'image du plant s'y trouvant, s'il y en a un
	 */
	public void setImage()
	{
		if(((carre.getHistorique().empilementPossible())&1)==1)
		{
			ImageIcon icon = new ImageIcon
			(
				new ImageIcon(plancheContent.getFullPlanche().getParent().getMainFrame().getDatas().getInformation("path.imagesPlants")+carre.getHistorique().getSommet().getImage()).getImage().getScaledInstance(20, 20, Image.SCALE_DEFAULT)
			);
			image.setIcon(icon);
		}
		else
			image.setIcon(null);
		
		if(carre.getHistorique().empilementPossible()==0)
		{
			//setOpaque(false);
			setBackground(Color.decode("#A52A2A"));
		}
		else
		{
			//setOpaque(true);
			setBackground(Color.decode(plancheContent.getFullPlanche().getParent().getMainFrame().getDatas().getFamilleCouleur(carre.getHistorique().getSommet().getFamille())));
		}
	}
	public void update(Observable o, Object arg) 
	{
		setImage();
	}
}
