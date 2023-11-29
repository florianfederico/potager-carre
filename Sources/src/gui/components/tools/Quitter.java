package gui.components.tools;

import gui.Fenetre;
import gui.panels.AbstractDynamicPanel;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Box;
import javax.swing.BoxLayout;

public class Quitter extends AbstractDynamicPanel
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	protected Button btnRetour;
	protected Fenetre mainFrame;
	/**
	 * Bouton pour quitter l'application
	 * @param f
	 * 			Frame principale
	 */
	public Quitter(Fenetre f)
	{
		super();
		mainFrame=f;
		initComponents();
		initEvents();
		initPanel();
	}
	/**
	 * Initialisation des composants
	 */
	public void initComponents()
	{	
		btnRetour=new Button(mainFrame.getDatas().getLangueElement("boutons.QUITTER"));
	}
	/**
	 * Initialisation des events
	 */
	public void initEvents()
	{
		btnRetour.addActionListener(new ActionListener() 
		{		
			public void actionPerformed(ActionEvent e) 
			{
				mainFrame.onClose();
			}
		});
	}
	/**
	 * initilication du Panel
	 */
	public void initPanel()
	{
		setBackground(new Color(0,0,0,0));				
		setLayout(new BoxLayout(this, BoxLayout.LINE_AXIS));
		add(Box.createRigidArea(new Dimension(700, 25)));
		add(btnRetour);		
	}
}