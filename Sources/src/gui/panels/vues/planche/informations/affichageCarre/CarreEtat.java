package gui.panels.vues.planche.informations.affichageCarre;

import gui.Fenetre;
import gui.components.tools.Button;
import gui.html.FicheLegume;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JLabel;
import javax.swing.JPanel;
import objects.Carre;

public class CarreEtat extends JPanel
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JLabel ensoleillement;
	private JLabel etat;
	private Button plus;
	private Carre carre;
	private Fenetre mainFrame;
	/**
	 * Créé un panel d'informations sur l'état du carré
	 * @param f
	 * 			Frame principale
	 */
	public CarreEtat(Fenetre f)
	{
		mainFrame=f;
		initComponents();
		initEvents();
		initPanel();
	}
	/**
	 * Change le carré observé
	 * @param c
	 * 			nouveau carré
	 */
	public void setCarre(Carre c)
	{
		carre=c;
		remove(plus);
		ensoleillement.setText(mainFrame.getDatas().getLangueElement("defaut.situation")+(c.getEnsoleillement()==0?mainFrame.getDatas().getLangueElement("defaut.ombrage"):mainFrame.getDatas().getLangueElement("defaut.ensoleille")));
		etat.setText(mainFrame.getDatas().getLangueElement("defaut.etat")+(c.getHistorique().empilementPossible()==0?mainFrame.getDatas().getLangueElement("defaut.etat_vide"):mainFrame.getDatas().getLangueElement("defaut.etat_contient")+c.getHistorique().getSommet()));
		if(c.getHistorique().empilementPossible()!=0)
			add(plus);
	}
	/**
	 * Initialise les evenements
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
					new FicheLegume(carre.getHistorique().getSommet(), mainFrame);
				}
			}
		);
	}
	/**
	 * Initialise les composants
	 */
	public void initComponents()
	{
		ensoleillement=new JLabel();
		etat=new JLabel();
		plus=new Button(mainFrame.getDatas().getLangueElement("plant.voirFiche"));	
	}
	/**
	 * Initialise le panel
	 */
	public void initPanel()
	{
		setPreferredSize(new Dimension(160,100));
		setLayout(new FlowLayout(FlowLayout.LEFT));
		setOpaque(false);
		add(ensoleillement);
		add(etat);
	}
}
