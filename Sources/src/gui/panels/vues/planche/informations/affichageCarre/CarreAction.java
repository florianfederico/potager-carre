package gui.panels.vues.planche.informations.affichageCarre;

import gui.Fenetre;
import gui.components.tools.Button;
import gui.panels.AbstractDynamicPanel;
import gui.panels.ScrollablePanel;
import gui.panels.informationsFrame.carres.ReglesFrame;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Iterator;
import java.util.Vector;
import javax.swing.BorderFactory;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EtchedBorder;
import objects.Carre;
import objects.Planche;
import objects.Plant;

public class CarreAction extends AbstractDynamicPanel
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Button viderCarre;
	private Button annulerContenu;
	private JLabel ajout;
	private CarreInformations parent;
	private JComboBox liste;
	private Button ajouter;
	private Fenetre mainFrame;
	private Carre carre;
	private Planche planche;
	private JPanel pane;
	private Button titreRegle;
	/**
	 * Crée la partie des actions possibles à effectuer sur un carré
	 * @param f
	 * 			Frame principale
	 * @param p
	 * 			Planche éditée
	 * @param info
	 * 			Informations sur le carré
	 */
	public CarreAction(Fenetre f, Planche p, CarreInformations info)
	{
		parent=info;
		mainFrame=f;
		planche=p;
		initComponents();
		initPanel();
		initEvents();
	}
	/**
	 * Initialise les composants
	 */
	public void initComponents()
	{	
		viderCarre=new Button(mainFrame.getDatas().getLangueElement("carre.vider"));
		annulerContenu=new Button(mainFrame.getDatas().getLangueElement("carre.annuler"));
		ajout=new JLabel(mainFrame.getDatas().getLangueElement("carre.ajout"));
		pane=new ScrollablePanel();
		pane.setPreferredSize(new Dimension(160,150));
		pane.setLayout(new FlowLayout(FlowLayout.LEFT));
		pane.setOpaque(false);
		liste=new JComboBox();
		ajouter=new Button(mainFrame.getDatas().getLangueElement("carre.ajouter"));
		titreRegle=new Button(mainFrame.getDatas().getLangueElement("carre.conflits"));
		liste.setPreferredSize(new Dimension(160,25));
	}
	/**
	 * Change le carré observé
	 * @param c
	 * 			nouveau carré
	 * @param p
	 * 			planche contenant le carré
	 */
	public void setCarre(Carre c,Planche p)
	{
		carre=c;
		planche=p;
		if(carre!=null&&planche!=null)
			creationPossibilites();
	}
	/**
	 * Cherche les plants pouvant être placés dans le carré
	 */
	public void creationPossibilites()
	{
		liste.removeAllItems();
		Vector<Plant> possibles= new Vector<Plant>();
		Vector<Plant> total= mainFrame.getDatas().getPlants();
		int i=0;
		Plant p=null;
		for(Iterator<Plant> it=total.iterator();it.hasNext() && i<3;)
		{
			p=it.next();
			if(carre.posePossible(p, planche, parent.getPlancheVue().getPlancheContent().getPosSud())==0)
			{
				possibles.add(p);
				i++;
			}
		}
		liste.addItem(mainFrame.getDatas().getLangueElement("carre.possible"));
		for(i=0;i<possibles.size();i++)
			liste.addItem(possibles.get(i));
	}
	/**
	 * Initialise le panel
	 */
	public void initPanel()
	{
		setPreferredSize(new Dimension(165, 170));
		setBorder(BorderFactory.createEtchedBorder(EtchedBorder.RAISED));
		setOpaque(false);
		setLayout(new FlowLayout(FlowLayout.CENTER));
	}
	/**
	 * Initialise les events
	 */
	public void initEvents()
	{
		ajouter.addActionListener
		(
			new ActionListener()
			{			
				@Override
				public void actionPerformed(ActionEvent arg0) 
				{
					try
					{
						Plant p=(Plant)liste.getSelectedItem();
						carre.ajouterHistorique(p);
						setAction(0);
					}
					catch(Exception e)
					{
						JOptionPane.showMessageDialog(mainFrame, mainFrame.getDatas().getLangueElement("erreurs.plantInvalide"), mainFrame.getDatas().getLangueElement("noms.erreur"),JOptionPane.ERROR_MESSAGE);
					}
				}
			}
		);
		titreRegle.addActionListener
		(
			new ActionListener() 
			{
				
				@Override
				public void actionPerformed(ActionEvent arg0) 
				{
					new ReglesFrame(carre.getPossibilites(), mainFrame);
				}
			}
		);
		viderCarre.addActionListener
		(
			new ActionListener()
			{			
				@Override
				public void actionPerformed(ActionEvent arg0) 
				{
					parent.vider();
				}
			}
		);
		annulerContenu.addActionListener
		(
			new ActionListener()
			{
				@Override
				public void actionPerformed(ActionEvent arg0) 
				{
					parent.annuler();
				}
			}
		);
	}
	/**
	 * Change les infortmations
	 * Impossible de planter le plant, conseil ou deconseil
	 * @param p
	 * 			plant que l'utilisateur cherche à planter
	 */
	public void updateLabel(Plant p)
	{
		pane.removeAll();
		if(p!=null)
		{
			int possibilites=carre.posePossible(p, planche, parent.getPlancheVue().getPlancheContent().getPosSud());
			carre.setPossibilites(possibilites);

			String conseil="";
			String color="";

			
			
			if(possibilites==0)
				color="style=\"color:#04B404\">"+mainFrame.getDatas().getLangueElement("carre.possibilite");
			else
				color="style=\"color:#FF0000\">"+mainFrame.getDatas().getLangueElement("carre.deconseil");
			conseil="<html>"+p.getNom()+" : <br><span "+color+"</span></html>";
			ajout.setText(conseil);
	
			pane.add(ajout);
			if(possibilites!=0)
				pane.add(titreRegle);
		}
	}
	/**
	 * Change l'action voulue
	 * @param i
	 * 			identifiant de l'action
	 * @param p
	 * 			plant selectionné
	 * @see CarreAction#setAction(int)
	 */
	public void setAction(int i, Plant p)
	{
		updateLabel(p);
		setAction(i);
	}
	/**
	 * Change l'action voulue
	 * @param i
	 * 			identifiant de l'action
	 */
	public void setAction(int i)
	{
		removeAll();
		if(carre.getHistorique().empilementPossible()==1)//Carré plein
		{
			add(viderCarre);
			add(annulerContenu);
		}
		else
		{
			add(liste);
			add(ajouter);
			if(((i>>1)&1)==1)//On affiche les règles pouvant ne pas être respectées
				add(pane);
		}
		updateUI();
	}
}
