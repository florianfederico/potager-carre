package gui.panels.legumotheque;

import java.awt.Dimension;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.Set;
import java.util.Vector;
import javax.swing.JComboBox;
import javax.swing.JPanel;

public class LegumothequeHeader extends JPanel
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JComboBox familles;
	private JComboBox types;
	private Vector<String> famillesList;
	private Vector<String> typesList;
	private Legumotheque legumotheque;
	/**
	 * Crée la partie header de la legumothèque (les combobox)
	 * @param f
	 * 			Liste des familles
	 * @param t
	 * 			Liste des types
	 * @param l
	 * 			legumothèque complète
	 */
	public LegumothequeHeader(Set<String>f, Set<String> t,Legumotheque l)
	{
		super();
		legumotheque=l;
		famillesList=new Vector<String>(f);
		typesList=new Vector<String>(t);
		initComponents();
		initEvents();
		initPanel();
	}
	/**
	 * Initialisation des events
	 */
	public void initEvents()
	{
		familles.addItemListener
		(
			new ItemListener()
			{
				public void itemStateChanged(ItemEvent arg0) 
				{
					legumotheque.setFamilleTri((String)arg0.getItem());
				}
			}
		);
		types.addItemListener
		(
			new ItemListener()
			{
				public void itemStateChanged(ItemEvent arg0) 
				{
					legumotheque.setTypeTri((String)arg0.getItem());
				}
			}
		);
	}
	/**
	 * Initialisation des composants
	 */
	public void initComponents()
	{
		familles=new JComboBox();
		types=new JComboBox();
		familles.setPreferredSize(new Dimension(190,25));
		types.setPreferredSize(new Dimension(190,25));
		familles.addItem(legumotheque.getMainFrame().getDatas().getLangueElement("defaut.FAMILLE"));
		types.addItem(legumotheque.getMainFrame().getDatas().getLangueElement("defaut.TYPE"));

		for(String i:famillesList)
			familles.addItem(i);
		for(String i:typesList)
			types.addItem(i);
	}
	/**
	 * Initialisation du panel
	 */
	public void initPanel()
	{
		setPreferredSize(new Dimension(200,60));
		setOpaque(false);
		add(familles);
		add(types);
	}
}
