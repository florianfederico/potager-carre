package gui.panels.vues.planche.informations.affichageCarre;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Image;
import java.util.Observable;
import java.util.Observer;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.border.EtchedBorder;
import objects.Carre;
import objects.Planche;
import objects.Plant;
import gui.Fenetre;
import gui.panels.AbstractStaticPanel;
import gui.panels.vues.PlancheVue;


public class CarreInformations extends AbstractStaticPanel implements Observer
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Carre carre;
	private Planche planche;
	private PlancheVue plancheVue;
	private CarreEtat carreEtat;
	private CarreAction carreAction;
	private HistoriquePanel historique;
	private Fenetre mainFrame;
	private Image img;
	/**
	 * Crée le menu d'information du carré selectionné
	 * @param p
	 * 			Vue planche
	 * @param f
	 * 			Frame principale
	 */
	public CarreInformations(PlancheVue p, Fenetre f)
	{
		mainFrame=f;
		img=new ImageIcon(mainFrame.getDatas().getInformation("path.imagesDivers")+mainFrame.getDatas().getInformation("fonds.menu")).getImage().getScaledInstance
		(
				174, 
				402, 
				Image.SCALE_DEFAULT
		);
		plancheVue=p;
		planche=plancheVue.getPlanche();
		initComponents();
		initPanel();
	}
	/**
	 * Retourne l'élément parent
	 * @return
	 * 			élément parent
	 */
	public PlancheVue getPlancheVue()
	{
		return plancheVue;
	}
	/**
	 * Change le carré observé
	 * @param c
	 * 			nouveau carré
	 * @param i
	 * 			indice d'informations (0 ou 1)
	 * @param p
	 * 			planche contenant le carré
	 * @return
	 * 			true s'il faut afficher les informations du carré
	 */
	public boolean setCarre(Carre c, int i, Planche p)
	{
		planche=p;
		boolean b=true;
		if(c!=null&&(carre!=c||(carre==c&&i==1)))
		{
			carre=c;
			carre.addObserver(this);
			carreEtat.setCarre(c);
			carreAction.setCarre(carre,planche);
			removeAll();
			initContentPanel();
		}
		else
		{
			b=false;
			if(carre!=null)
				carre.deleteObserver(this);
			carre=null;
			carreAction.setCarre(carre,planche);
			removeAll();
		}
		updateUI();
		return b;
	}
	/**
	 * Vider le carré en ajoutant le plant à l'historique
	 */
	public void vider()
	{
		carre.vider();
		plancheVue.setImage();
		setCarre(carre,1,planche);	
	}
	/**
	 * Vider le carré sans ajouter le plant à l'historique
	 */
	public void annuler()
	{
		carre.annulerContenu();
		plancheVue.setImage();
		setCarre(carre,1,planche);
	}
	/**
	 * Initialise le panel en fonction du carré
	 */
	public void initContentPanel()
	{
		add(carreEtat);
		if(carre.getHistorique().empilementPossible()==1)
			carreAction.setAction(0);
		else
			carreAction.setAction(1);
		add(carreAction);
		historique.setCarre(carre);
		add(historique);
		updateUI();
	}
	/**
	 * Initialise les composants
	 */
	public void initComponents() 
	{
		carreEtat=new CarreEtat(mainFrame);
		carreAction=new CarreAction(mainFrame, planche, this);
		historique=new HistoriquePanel(carre, 3, mainFrame);
	}
	/**
	 * Initialise le panel
	 */
	public void initPanel()
	{
		setLayout(new FlowLayout(FlowLayout.LEFT));
		setBorder(BorderFactory.createEtchedBorder(EtchedBorder.RAISED));
		setPreferredSize(new Dimension(174,402));
	}
	/**
	 * Change l'action d'affichage
	 * @param i
	 * 			identifiant
	 * @param p
	 * 			plant selectionné
	 */
	public void setAction(int i, Plant p)
	{
		if(carre!=null)
		{
			carreAction.setCarre(carre, planche);
			carreAction.setAction(i, p);
		}
	}
	/**
	 * Change l'action d'affichage
	 * @param i
	 * 			identifiant
	 */
	public void setAction(int i)
	{
		if(carre!=null)
		{
			carreAction.setCarre(carre, planche);
			carreAction.setAction(i);
		}
	}
	public void update(Observable o, Object arg) 
	{
		if(carre!=null)
		{
			carreEtat.setCarre(carre);
			initContentPanel();
			setAction(3, plancheVue.getPlancheContent().getPlant());
		}
	}
	public void paintComponent(Graphics g) 
	{
		super.paintComponent(g);
		g.drawImage(img,0,0,null);
    }

	/**
	 * Vider le carré en ajoutant le plant à l'historique
	 */
	public void viderCarre() {
		carre.vider();
	}
	/**
	 * Inverse l'ensoleillement du carré
	 */
	public void swapEnsoleillement() {
		carre.swapEnsoleillement();
	}
}
