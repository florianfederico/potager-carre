package gui.panels.informationsFrame.plancheInfos;


import gui.panels.informationsFrame.AbstractGraphicInformations;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Box;
import javax.swing.JOptionPane;

import objects.Planche;

public class PlancheGraphicInfos extends AbstractGraphicInformations
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String TYPE;
	private PlancheInformations informationsPanel;
	private Planche planche;
	private PlancheInfoFrame plancheInfoFrame;

	/**
	 * Crée l'instance des éléments affichés sur la fenetre pop up
	 * @param pif
	 * 			Frame de la fenetre pop up
	 * @param p
	 * 			planche à éditer
	 */
	public PlancheGraphicInfos(PlancheInfoFrame pif,Planche p)
	{
		super(pif.getFrame());
		TYPE=pif.getFrame().getDatas().getLangueElement("type.planche");
		plancheInfoFrame=pif;
		planche=p;
		initComponents(p);
		initEvents();
		initPanel();
	}
	/**
	 * Initialise les composants
	 * @param p
	 * 			planche à éditer
	 */
	public void initComponents(Planche p)
	{
		super.initComponents(TYPE, plancheInfoFrame.getFrame());
		informationsPanel=new PlancheInformations(plancheInfoFrame);
		informationsPanel.setInfos(p);
	}
	public void initEvents()
	{
		valider.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e) 
			{
				if(!informationsPanel.getNom().equals(""))
				{
					if(!plancheInfoFrame.nomExiste(informationsPanel.getNom()))
					{
						planche.setValues(informationsPanel.getNom(),informationsPanel.getEnsoleillement());
						plancheInfoFrame.actualiser();
						plancheInfoFrame.dispose();
					}				
					else
						JOptionPane.showMessageDialog(plancheInfoFrame, DOUBLE_NOM_MESSAGE.replaceAll("#1", TYPE), DOUBLE_NOM, JOptionPane.ERROR_MESSAGE);
				}
				else
					JOptionPane.showMessageDialog(plancheInfoFrame, CHAMP_VIDE_MESSAGE, CHAMP_VIDE, JOptionPane.ERROR_MESSAGE);
			}
		});
	}
	public void initPanel()
	{
		add(informations);
		add(informationsPanel);
		add(obligatoire);
		add(Box.createRigidArea(new Dimension(77,20)));
		add(valider);
	}
}
