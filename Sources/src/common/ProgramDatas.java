package common;

import java.io.IOException;
import java.util.HashMap;
import java.util.Vector;
import objects.Plant;
import objects.Potager;
import org.dom4j.DocumentException;
import common.datas.*;

public class ProgramDatas 
{
	private Informations informations;
	private Langues langues;
	private Plants plants;
	private Potagers potagers;
	private String langue;
	/**
	 * Creation d'un chargeur de données
	 */
	public ProgramDatas()
	{
		informations=new Informations();
		langues=new Langues();
		plants=new Plants();
		potagers=new Potagers();
		langue="";
	}
	/**
	 * Permet de récupérer la liste des potagers enregistrés
	 * @return
	 * 			Liste des potagers
	 */
	public Vector<Potager> getListeJardins()
	{
		return potagers.getListeJardins();
	}
	/**
	 * Permet de lancer le chargement des données des informations générales
	 * @throws DocumentException
	 * 				Chargement XML
	 * @throws IOException
	 * 				Chargement XML
	 */
	public void chargerInformations() throws DocumentException, IOException
	{
		informations.loadDatas();
		langue=informations.getInformation("default.langue");
	}
	/**
	 * Permet de charger les données de langues
	 * @throws DocumentException
	 * 				Chargement XML
	 */
	public void chargerLangues() throws DocumentException
	{
		langues.setPath(informations.getInformation("path.langues").replaceAll("@LANG@", langue));
		
		langues.setFichierBase
		(
			informations.getInformation("default.langueFichier"),
			informations.getInformation("default.iconesFichier"),
			informations.getInformation("default.reglesFichier")
		);
		langues.clear();
		langues.loadDatas();
		langues.loadIcones();
		langues.loadRegles();
	}
	/**
	 * Permet de charger toutes les données de plants
	 * @throws DocumentException
	 * 				Chargement XML
	 */
	public void chargerPlantsInformations() throws DocumentException
	{
		plants.setFichierBase(informations.getInformation("default.basePlantFichier"));
		plants.setFichierReglesBase(informations.getInformation("default.reglesBaseFichier"));
		plants.setPath(informations.getInformation("path.plants").replaceAll("@LANG@", langue));
		plants.clear();
		plants.loadDatas();
	}
	/**
	 * Permet de récupérer les données d'icones
	 * @return
	 * 			Données d'icones
	 */
	public HashMap<String, HashMap<String, String>> getIcones()
	{
		return langues.getIcones();
	}
	/**
	 * Permet de charger les données de plants puis les règles spécifiques
	 * @throws DocumentException
	 * 			Chargement XML
	 */
	public void chargerPlants() throws DocumentException
	{
		plants.creationPlants();
		plants.chargerRegles();
	}
	/**
	 * Charge les potagers
	 */
	public void chargerPotagers()
	{
		potagers.setPath(informations.getInformation("path.potager").replaceAll("@LANG@", langue));
		potagers.clear();
		potagers.loadDatas();
	}
	/**
	 * Permet de récupérer le texte se trouvant dans le fichier XML
	 * @param str
	 * 			Clé du texte
	 * @return
	 * 			texte correspondant à la clé
	 */
	public String getInformation(String str)
	{
		return informations.getInformation(str);
	}
	
	/**
	 * Permet de récupérer les messages de conflits de règles
	 * @param i
	 * 			ID de la règle
	 * @return
	 * 			Règle correspondante
	 */
	public String getRegle(int i)
	{
		return langues.getRegle(i);
	}
	
	/**
	 * Permet de récupérer la totalité des potagers existants
	 * @return
	 * 			Liste des potagers
	 */
	public Vector<Potager> getListePotagers()
	{
		return potagers.getListeJardins();
	}
	
	/**
	 * Permet de récupérer le texte à afficher
	 * @param str
	 * 			identifiant du texte requis
	 * @return
	 * 			Texte correspondant
	 */
	public String getLangueElement(String str)
	{
		return langues.getLangueElement(str);
	}
	/**
	 * récupère là liste des plants
	 * @return
	 * 			liste des plants
	 */
	public Vector<Plant> getPlants()
	{
		return plants.getPlants();
	}
	/**
	 * Récupère les familles avec leur couleur
	 * @return
	 * 			Liste des familles
	 */
	public HashMap<String, String> getFamilles()
	{
		return plants.getFamilles();
	}
	/**
	 * récupère là liste des types ainsi que leur image
	 * @return
	 * 			Liste des types
	 */
	public HashMap<String, String> getTypes()
	{
		return plants.getTypes();
	}
	/**
	 * Récupère la couleur d'une famille
	 * @param key
	 * 				Nom de la famille
	 * @return
	 * 				Couleur correspondante
	 */
	public String getFamilleCouleur(String key)
	{
		return plants.getFamilles().get(key);
	}
	/**
	 * Récupère l'image d'un type
	 * @param key
	 * 				Nom du type
	 * @return
	 * 				image correspondante
	 */
	public String getTypeImage(String key)
	{
		return informations.getInformation("path.imagesTypes")+plants.getTypes().get(key);
	}
}
