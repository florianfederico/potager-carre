package common;

import gui.Chargement;

import java.io.FileNotFoundException;
import java.io.IOException;
import javax.swing.UIManager;
import org.dom4j.DocumentException;

import gui.Fenetre;

public class Main
{
	public static String FAMILLE;
	public static String TYPE;
	public static String ADRESSE;
	public static String NOM_POTAGER;
	public static String NOM_PLANCHE;
	public static String PATH_POTAGER;
	public static String SOLEIL_0;
	public static String SOLEIL_1;
	public static String ENSOLEILLE;
	private static Fenetre fenetre;
	private static Chargement chargement;
	/**
	 * Point d'entrée du programme
	 * @param args
	 * 				Facultatif
	 * @throws DocumentException
	 * 				Chargement XML
	 * @throws IOException
	 * 				Chargement XML
	 * @throws ClassNotFoundException
	 * 				Chargement XML
	 * @throws IllegalStateException
	 * 				Chargement XML
	 * @throws InterruptedException
	 * 				Chargement XML
	 */
	public static void main(String[] args) throws DocumentException, IOException, ClassNotFoundException, IllegalStateException, InterruptedException 
	{
		fenetre=new Fenetre();
		chargement();
		fenetre.initFenetre();
		setLookAndFeel();
	}
	/**
	 * Début du chargement des données
	 * @throws FileNotFoundException
	 * 				Chargement XML
	 * @throws ClassNotFoundException
	 * 				Chargement XML
	 * @throws DocumentException
	 * 				Chargement XML
	 * @throws IOException
	 * 				Chargement XML
	 * @throws IllegalStateException
	 * 				Chargement XML
	 * @throws InterruptedException
	 * 				Chargement XML
	 */
	public static void chargement() throws FileNotFoundException, ClassNotFoundException, DocumentException, IOException, IllegalStateException, InterruptedException
	{
		chargement=new Chargement(fenetre);
		chargement.start();
	}
	/**
	 * Permet de récupérer le nom automatique du prochain potager
	 * @return
	 * 			nom du potager
	 */
	public static String getNomPotager()
	{
		return NOM_POTAGER.replaceAll("#i",""+(chargement.getDatas().getListeJardins().size()+1));
	}
	
	/**
	 * Permet de récupérer le nom automatique de la prochaine planche
	 * @return
	 * 			nom de la planche
	 */
	public static String getNomPlanche()
	{
		return NOM_PLANCHE.replaceAll("#i",""+(fenetre.getPotager().getPlanches().size()));
	}
	/**
	 * Initialisation des variables principales (ces variables doivent rester constantes)
	 */
	public static void setConstantes()
	{
		FAMILLE=chargement.getDatas().getLangueElement("defaut.FAMILLE");
		TYPE=chargement.getDatas().getLangueElement("defaut.TYPE");
		ADRESSE=chargement.getDatas().getLangueElement("defaut.ADRESSE");
		NOM_POTAGER=chargement.getDatas().getLangueElement("defaut.NOM_POTAGER");
		NOM_PLANCHE=chargement.getDatas().getLangueElement("defaut.NOM_PLANCHE");
		SOLEIL_1=chargement.getDatas().getLangueElement("plant.soleil_requis");
		SOLEIL_0=chargement.getDatas().getLangueElement("plant.soleil_non_requis");
		ENSOLEILLE=chargement.getDatas().getLangueElement("defaut.ensoleille");
		PATH_POTAGER=chargement.getDatas().getInformation("path.potager");
	}

	/**
	 * Permet de modifier l'apparence par defaut de swing
	 */
	public static void setLookAndFeel()
	{
		// 0 à 5 choix possibles, 3 le plus adapté
		try
		{
			UIManager.setLookAndFeel((UIManager.getInstalledLookAndFeels()[3]).getClassName());
		} 
		catch (Exception e) {}
	}
}
