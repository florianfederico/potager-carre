package gui.panels.informationsFrame;

import gui.Fenetre;

import java.awt.GridLayout;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public abstract class AbstractInformations extends JPanel
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	protected JLabel nom;
	protected JLabel ensoleillement;
	protected JTextField nomTextField;
	protected JComboBox ensoleillementComboBox;

	/**
	 * Initialise les composants du panel.
	 */

	protected void initComponents(Fenetre mainFrame)
	{
		nom=new JLabel("<HTML>"+mainFrame.getDatas().getLangueElement("frame.nom")+"<span style=\"color:#FF0000\"><strong>*</span></HTML>");
		ensoleillement=new JLabel("<HTML>"+mainFrame.getDatas().getLangueElement("frame.ensoleillement")+"<span style=\"color:#FF0000\"><strong>&nbsp;&nbsp;&nbsp;</strong></span></HTML>");
		nomTextField=new JTextField();
		ensoleillementComboBox=new JComboBox();
		ensoleillementComboBox.addItem(mainFrame.getDatas().getLangueElement("frame.choix1"));
		ensoleillementComboBox.addItem(mainFrame.getDatas().getLangueElement("frame.choix2"));
	}
	
	/**
	 * Initialise le panel d'information et lui ajoute la totalité des éléments le composant.
	 */
	protected void initPanel()
	{
		setLayout(new GridLayout(0,2));
	}
	

	/**
	 * Affiche les valeurs de l'objet envoyé dans les champs de texte.
	 * @param o
	 *			- objet envoyé à la frame pour l'affichage
	 */
	public abstract void setInfos(Object o);
	
	
	/**
  	 * Récupère la valeur de l'ensoleillement sous forme d'instance de String.
  	 * @return String
  	 * 			- Valeur ensoleillement
	 */
	public String getEnsoleillement()
	{
		return (String) ensoleillementComboBox.getSelectedItem();
	}
	
	/**
  	 * Récupère le nouveau nom de l'objet cible
  	 * @return String
  	 * 			- Nouveau nom de l'objet
	 */
	public String getNom()
	{
		return nomTextField.getText();
	}
}
