package gui.panels.informationsFrame.jardinInfos;

import gui.Fenetre;
import gui.panels.informationsFrame.AbstractInformations;
import javax.swing.JLabel;
import javax.swing.JTextField;
import objects.Potager;


public class JardinInformations extends AbstractInformations
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JLabel adresse;
	private JTextField adresseText;
	private String ensoleillementStr;
	private String adresseStr;
	private Fenetre mainFrame;
	/**
	 * Crée une fenetre pop up de modification de potager
	 * @param f
	 * 			Frame principale
	 */
	public JardinInformations(Fenetre f)
	{
		super();
		mainFrame=f;
		ensoleillementStr=f.getDatas().getLangueElement("frame.ensoleillement");
		adresseStr=f.getDatas().getLangueElement("frame.adresse");
		initComponents();
		initPanel();
	}
	/**
	 * Initialise les composants
	 */
	public void initComponents()
	{
		super.initComponents(mainFrame);
		adresse=new JLabel(adresseStr);
		adresseText=new JTextField();
		ensoleillement=new JLabel(ensoleillementStr);
	}
	
	public void initPanel()
	{
		super.initPanel();
		add(nom);
		add(nomTextField);
		add(adresse);
		add(adresseText);
		add(ensoleillement);
		add(ensoleillementComboBox);
	}
	
	public void setInfos(Object o)
	{
		Potager p=(Potager)o;
		nomTextField.setText(p.getNom());
		adresseText.setText(p.getAdresse());
	}
	/**
	 * Envoie l'adresse du potager
	 * @return
	 * 			adresse du potager
	 */
	public String getAdresse()
	{
		return adresseText.getText();
	}
}