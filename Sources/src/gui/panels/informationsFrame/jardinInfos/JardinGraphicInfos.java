package gui.panels.informationsFrame.jardinInfos;


import gui.panels.informationsFrame.AbstractGraphicInformations;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.JOptionPane;

import objects.Potager;

public class JardinGraphicInfos extends AbstractGraphicInformations
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	protected String TYPE;
	protected JardinInformations informationsPanel;
	private Potager jardin;
	private JardinInfoFrame jardinInfoFrame;

	/**
	 * Crée l'instance des éléments affichés sur la fenetre pop up
	 * @param jif
	 * 			Frame de la fenetre pop up
	 * @param p
	 * 			potager à éditer
	 */
	public JardinGraphicInfos(JardinInfoFrame jif,Potager p)
	{
		super(jif.getFrame());
		jardinInfoFrame=jif;
		TYPE=jardinInfoFrame.getFrame().getDatas().getLangueElement("type.potager");
		jardin=p;
		initComponents(p);
		initEvents();
		initPanel();
	}
	/**
	 * Initialise les composants
	 * @param p
	 * 			potager à éditer
	 */
	public void initComponents(Potager p)
	{
		super.initComponents(TYPE, jardinInfoFrame.getFrame());
		informationsPanel=new JardinInformations(jardinInfoFrame.getFrame());
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
					if(!jardinInfoFrame.nomExiste(informationsPanel.getNom()))
					{
						jardin.setValues(informationsPanel.getNom(),informationsPanel.getEnsoleillement(),informationsPanel.getAdresse());
						jardinInfoFrame.validerModification();
						jardinInfoFrame.actualiser();
						jardinInfoFrame.dispose();
					}
					else
						JOptionPane.showMessageDialog(jardinInfoFrame, DOUBLE_NOM_MESSAGE.replaceFirst("#1", TYPE), DOUBLE_NOM, JOptionPane.ERROR_MESSAGE);
				}
				else
					JOptionPane.showMessageDialog(jardinInfoFrame, CHAMP_VIDE_MESSAGE, CHAMP_VIDE, JOptionPane.ERROR_MESSAGE);
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
