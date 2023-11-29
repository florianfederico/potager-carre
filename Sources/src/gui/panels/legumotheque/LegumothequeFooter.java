package gui.panels.legumotheque;


import gui.Fenetre;
import gui.components.tools.Button;
import gui.components.tools.Titre;
import gui.html.FicheLegume;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EtchedBorder;
import objects.Plant;

public class LegumothequeFooter extends JPanel
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Image img;
	private Plant plant;
	private Titre titre;
	private JLabel type;
	private JLabel famille;
	private JLabel exposition;
	private Button plus;
	private Fenetre mainFrame;
	/**
	 * Création de la partie footer de la legumothèque (informations du legume selectionné)
	 * @param f
	 * 			Frame principale
	 */
	public LegumothequeFooter(Fenetre f)
	{
		super();
		mainFrame=f;
		img=new ImageIcon(mainFrame.getDatas().getInformation("path.imagesDivers")+mainFrame.getDatas().getInformation("fonds.footer")).getImage().getScaledInstance
		(
				190, 
				125, 
				Image.SCALE_DEFAULT
		);
		initComponents();
		initPanel();
		initEvents();
	}
	/**
	 * Action lors d'un clic sur un plant
	 * @param p
	 * 			Plant à afficher
	 */
	public void clicPlant(Plant p)
	{
		plant=p;
		titre.setTitre(plant.getNom());
		type.setText(mainFrame.getDatas().getLangueElement("legumotheque.type")+plant.getType());
		famille.setText(mainFrame.getDatas().getLangueElement("legumotheque.famille")+plant.getFamille());
		exposition.setText(mainFrame.getDatas().getLangueElement("legumotheque.exposition")+plant.getExposition());
		plus.setText(mainFrame.getDatas().getLangueElement("legumotheque.fiche"));
		setVisible(true);
	}
	/**
	 * Permet de cacher le footer lorsqu'aucun plant n'est selectionné
	 */
	public void invisible()
	{
		setVisible(false);	
	}
	/**
	 * Initialise les composants
	 */
	public void initComponents()
	{
		titre=new Titre(mainFrame.getDatas().getLangueElement("noms.informations"), 190, 25, 15.0f, 10, mainFrame);
		type=new JLabel();
		exposition=new JLabel();
		famille=new JLabel();
		plus=new Button();
	}
	/**
	 * Initialise les events
	 */
	public void initEvents()
	{
		plus.addActionListener
		(
			new ActionListener() 
			{
				@Override
				public void actionPerformed(ActionEvent arg0) 
				{
					new FicheLegume(plant, mainFrame);
				}
			}
		);
	}
	/**
	 * Initialise le panel
	 */
	public void initPanel()
	{
		setVisible(false);
		setPreferredSize(new Dimension(190,125));
		setBorder(BorderFactory.createEtchedBorder(EtchedBorder.RAISED));
		setLayout(new GridLayout(0,1));
		add(titre);
		add(type);
		add(famille);
		add(exposition);
		add(plus);
	}
	
	public void paintComponent(Graphics g) 
	{
		super.paintComponent(g);
		g.drawImage(img,0,0,null);
    }
}
