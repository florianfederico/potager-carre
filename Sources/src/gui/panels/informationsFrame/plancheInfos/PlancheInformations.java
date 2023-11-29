package gui.panels.informationsFrame.plancheInfos;

import gui.Fenetre;
import gui.panels.informationsFrame.AbstractInformations;
import objects.Planche;

public class PlancheInformations extends AbstractInformations
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private PlancheInfoFrame plancheInfoFrame;
	private Fenetre mainFrame;
	/**
	 * Crée une fenetre pop up de modification de planche
	 * @param p
	 * 			Frame de la fenetre pop up
	 */
	public PlancheInformations(PlancheInfoFrame p)
	{
		super();
		mainFrame=p.getFrame();
		plancheInfoFrame=p;
		initComponents();
		initPanel();
	}
	/**
	 * Initialise les composants
	 */
	public void initComponents()
	{
		super.initComponents(mainFrame);
	}
	public void initPanel()
	{
		super.initPanel();
		add(nom);
		add(nomTextField);
		add(ensoleillement);
		add(ensoleillementComboBox);
	}
	
	public void setInfos(Object o)
	{
		Planche p=(Planche)o;
		int enso=0;
		nomTextField.setText(p.getNom());
		enso=p.getEnsoleillement()==-1?plancheInfoFrame.getEnsoleillement():p.getEnsoleillement();
		ensoleillementComboBox.setSelectedIndex(enso);
	}
}
