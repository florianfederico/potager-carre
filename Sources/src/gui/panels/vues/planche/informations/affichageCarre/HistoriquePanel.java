package gui.panels.vues.planche.informations.affichageCarre;

import gui.Fenetre;
import gui.components.tools.Button;
import gui.panels.informationsFrame.carres.HistoriqueFrame;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Vector;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import objects.Carre;
import objects.Plant;
import tools.Date;
import tools.Paire;

public class HistoriquePanel extends JPanel
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private JTable historique;
	private JScrollPane scrollpane;
	private Button afficher;
	private Carre carre;
	private int limite;
	private Dimension dimension;
	private boolean aff=true;
	private Fenetre mainFrame;
	/**
	 * Affiche l'historique de plantation dans un carré
	 * @param c
	 * 			Carré choisi
	 * @param i
	 * 			nombre de plants à afficher (ordre déchronologique), -1 pour tout afficher
	 * @param f
	 * 			Frame principale
	 */
	public HistoriquePanel(Carre c, int i, Fenetre f)
	{
		carre=c;
		mainFrame=f;
		limite=i;
		dimension=new Dimension(155,71);
		if(limite==-1)
		{
			limite=carre.getHistorique().size();
			aff=false;
		}
		initComponent();
		initPanel();
		initEvent();
	}
	/**
	 * Affiche l'historique de plantation dans un carré
	 * @param c
	 * 			Carré choisi
	 * @param i
	 * 			nombre de plants à afficher (ordre déchronologique), -1 pour tout afficher
	 * @param d
	 * 			dimension du panel d'historique
	 * @param f
	 * 			Frame principale
	 */
	public HistoriquePanel(Carre c, int i, Dimension d, Fenetre f)
	{
		carre=c;
		mainFrame=f;
		limite=i;
		dimension=d;
		if(limite==-1)
		{
			limite=carre.getHistorique().size();
			aff=false;
		}
		initComponent();
		initPanel();
		initEvent();
	}
	/**
	 * Change le carré selectionné
	 * @param c
	 * 			nouveau carré
	 */
	public void setCarre(Carre c)
	{
		carre=c;
		ArrayList<Paire<Plant,Date>> historiqueAff=carre.getAffichageHistorique();
		String premier="", second="";
		for(int i=0;i<limite;i++)
		{
			if(historiqueAff.size()>i)
			{
				premier=historiqueAff.get(i).getPremier().toString();
				second=historiqueAff.get(i).getSecond().toString();
			}
			else
			{
				premier="";
				second="";
			}
			historique.setValueAt(premier, i, 0);
			historique.setValueAt(second, i, 1);
		}
	}
	/**
	 * Initialise les composants
	 */
	public void initComponent()
	{
		Vector<Vector<String>> rowData = new Vector<Vector<String>>();
		for(int i=0;i<limite;i++)
			rowData.add(new Vector<String>());
	    Vector<String> columnNames = new Vector<String>();
	    columnNames.addElement(mainFrame.getDatas().getLangueElement("historique.col_plant"));
	    columnNames.addElement(mainFrame.getDatas().getLangueElement("historique.col_date"));
	    historique = new JTable(rowData, columnNames);
		historique.getColumnModel().getColumn(0).setPreferredWidth(90);
		historique.setEnabled(false);
		scrollpane = new JScrollPane(historique);
	    scrollpane.setPreferredSize(dimension);
	    historique.setFillsViewportHeight(true);
		afficher=new Button(mainFrame.getDatas().getLangueElement("historique.complet"));
	}
	/**
	 * Initialise le panel
	 */
	public void initPanel()
	{
		setPreferredSize(new Dimension(163,113));
		setOpaque(false);
		add(scrollpane);
		if(aff)
			add(afficher);
	}
	/**
	 * Initialise les events
	 */
	public void initEvent()
	{
		afficher.addActionListener
		(
			new ActionListener() 
			{
				@Override
				public void actionPerformed(ActionEvent arg0) 
				{
					new HistoriqueFrame(carre, mainFrame);
				}
			}
		);
	}
}
