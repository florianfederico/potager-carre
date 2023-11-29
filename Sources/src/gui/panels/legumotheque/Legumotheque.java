package gui.panels.legumotheque;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.util.Vector;
import gui.Fenetre;
import gui.components.tools.Titre;
import gui.mainPanels.observers.LegumothequeObservable;
import gui.mainPanels.observers.Observer;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.border.EtchedBorder;
import objects.Plant;


public class Legumotheque extends JPanel implements LegumothequeObservable
{
	private LegumothequeHeader header;
	private LegumothequeContent content;
	private LegumothequeFooter footer;
	private Vector<Observer> observers;
	private Titre titre;
	private Fenetre mainFrame;
	private static final long serialVersionUID = 1L;
	
	private Image img;
	/**
	 * Cr�ation de la legumoth�que
	 * @param f
	 * 			Frame principale
	 */
	public Legumotheque(Fenetre f)
	{
		super();
		mainFrame=f;
		img=new ImageIcon(mainFrame.getDatas().getInformation("path.imagesDivers")+mainFrame.getDatas().getInformation("fonds.menu")).getImage().getScaledInstance
		(
				200, 
				499, 
				Image.SCALE_DEFAULT
		);
		observers=new Vector<Observer>();
		initComponents();
		initPanel();
	}
	/**
	 * Permet d'ajouter un observer  la legumoth�que
	 * @param o
	 * 			observeur � ajouter
	 */
	public void addObserver(Observer o)
	{
		observers.add(o);
	}
	/**
	 * Pr�vient les observeurs que le plant selectionn� � chang�
	 */
	public void changerSelection()
	{
		for(Observer o : observers)
			o.update();
	}
	/**
	 * Permet de r�cup�rer la frame principale
	 * @return
	 * 			Frame principale
	 */
	public Fenetre getMainFrame()
	{
		return mainFrame;
	}
	/**
	 * Permet de r�cup�rer le plant selectionn�
	 * @return
	 * 			Plant selectionn�
	 */
	public Plant getPlant()
	{
		return content.getSelectedPlant();
	}
	/**
	 * Initialise les composants
	 */
	public void initComponents()
	{
		titre=new Titre(mainFrame.getDatas().getLangueElement("noms.legumotheque"), 190, 30, 15.0f, 50, mainFrame);
		header=new LegumothequeHeader(mainFrame.getDatas().getFamilles().keySet(),mainFrame.getDatas().getTypes().keySet(),this);
		content=new LegumothequeContent(this,mainFrame);
		footer=new LegumothequeFooter(mainFrame);
	}
	/**
	 * Active le tri par famille
	 * @param item
	 * 			Nom de la famille � utiliser
	 */
	public void setFamilleTri(String item)
	{
		deleteInfos();
		content.setFamille(item);
	}
	/**
	 * Active le tri par type
	 * @param item
	 * 			Nom du type � utiliser
	 */
	public void setTypeTri(String item)
	{
		deleteInfos();
		content.setType(item);
	}
	/**
	 * Deselectionne le plant selectionn�
	 */
	public void deselect()
	{
		content.deSelect();
		changerSelection();
	}
	/**
	 * Initialise le panel
	 */
	public void initPanel()
	{
		setPreferredSize(new Dimension(200,499));
		setBorder(BorderFactory.createEtchedBorder(EtchedBorder.RAISED));
		setOpaque(false);
		add(titre);
		add(header);
		add(content);
		add(footer);
	}
	/**
	 * Affiche les informations d'un plant
	 * @param p
	 * 			plant � d�tailler
	 */
	public void afficheInfos(Plant p)
	{
		footer.clicPlant(p);
	}
	/**
	 * Ne plus afficher d'informations
	 */
	public void deleteInfos()
	{
		footer.invisible();
	}
	public void paintComponent(Graphics g) 
	{
		super.paintComponent(g);
		g.drawImage(img,0,0,null);
    }
}