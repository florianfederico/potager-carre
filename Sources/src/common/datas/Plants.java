package common.datas;

import java.io.File;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;
import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import objects.Plant;

public class Plants extends AbstractDatas
{
	private HashMap<String,String> listeFamilles;
	private HashMap<String,String> listeTypes;
	private Vector<File> listeFichiers;
	private Vector<Plant> liste;
	private String fichierBase;
	private String fichierReglesBase;
	/**
	 * Création d'un objet de chargement de données de plants
	 */
	public Plants()
	{
		liste=new Vector<Plant>();
		listeFamilles=new HashMap<String,String>();
		listeTypes=new HashMap<String,String>();
		listeFichiers=new Vector<File>();
	}
	/**
	 * Fixe le nom du fichier à interroger
	 * @param fichier
	 * 			Nom du fichier de données
	 */
	public void setFichierBase(String fichier)
	{
		fichierBase=fichier;
	}

	/**
	 * Fixe le nom du fichier de règles à interroger
	 * @param fichier
	 * 			Nom du fichier de données de règles
	 */
	public void setFichierReglesBase(String fichier)
	{
		fichierReglesBase=fichier;
	}
	
	public void loadDatas() throws DocumentException
	{
		parseDataFile();
	}
	
	/**
	 * Parse le fichier de données de plants global
	 * @throws DocumentException
	 * 			Chargement XML
	 */
	@SuppressWarnings("unchecked")
	public void parseDataFile() throws DocumentException
	{
		File dataFile = new File (path+fichierBase);
		SAXReader reader =  new SAXReader();
		Document doc = reader.read("file:\\\\\\"+dataFile.getAbsolutePath());
		Element root = doc.getRootElement();
		List<Element> elements = root.elements();
		Element e=null;
		for(Iterator<Element>it=elements.iterator();it.hasNext();)
		{
			e=it.next();
			if(e.getName().equals("plants"))
				parsePlants(e.elements());
			else if (e.getName().equals("familles"))
				parseFamilles(e.elements());
			else if (e.getName().equals("types"))
				parseTypes(e.elements());
		}
	}
	/**
	 * Récupère la liste des fichiers de plants
	 * @param listeElements
	 * 			Element XML
	 * @throws DocumentException
	 * 			Chargement XML
	 */
	public void parsePlants(List<Element> listeElements) throws DocumentException
	{
		Element e=null;
		for(Iterator<Element>it=listeElements.iterator();it.hasNext();)
		{
			e=it.next();
			listeFichiers.add(new File(path+e.getData().toString()));
		}
	}
	/**
	 * Récupère la liste des familles
	 * @param listeElements
	 * 			Element XML
	 * 			Chargement XML
	 */
	public void parseFamilles(List<Element> listeElements)
	{
		Element e=null;
		for(Iterator<Element>it=listeElements.iterator();it.hasNext();)
		{
			e=it.next();
			listeFamilles.put(e.getData().toString(),e.attributeValue("color"));
		}
	}
	/**
	 * Récupère la liste des types
	 * @param listeElements
	 * 			Element XML
	 */
	public void parseTypes(List<Element> listeElements)
	{
		Element e=null;
		for(Iterator<Element>it=listeElements.iterator();it.hasNext();)
		{
			e=it.next();
			listeTypes.put(e.getData().toString(),e.attributeValue("image"));
		}
	}
	/**
	 * Charge la totalité des plants
	 * @throws DocumentException
	 * 			Chargement XML
	 */
	public void creationPlants() throws DocumentException
	{
		File f=null;
		for(Iterator<File>it=listeFichiers.iterator();it.hasNext();)
		{
			f=it.next();
			try
			{
				liste.add(xmlToPlant(f));
			}
			catch(Exception e)
			{
				System.out.println("Le fichier "+f+" n'existe pas.");
			}
		}
	}
	
	/**
	 * Créé un plant à partir de la ligne XML
	 * @param f
	 * 			Fichier de plant
	 * @return
	 * 			Nouveau plant
	 * @throws DocumentException
	 * 			Chargement XML
	 */
	@SuppressWarnings("unchecked")
	public Plant xmlToPlant(File f) throws DocumentException
	{
		HashMap<String,String> donnees=new HashMap<String,String>();
		Plant p;
		String nom;
		String image;
		String description;
		String famille;
		String type;
		int taille;
		int superficie;
		int position;
		int ensoleillement;
		int rotation;
		SAXReader reader =  new SAXReader();
		Document doc = reader.read(f);
		Element root = doc.getRootElement();
		List<Attribute> attributes = root.attributes();
		List<Element> elements = root.elements();		
		Element e=null;
		for(Iterator<Element>it=elements.iterator();it.hasNext();)
		{
			e=it.next();
			donnees.put(e.getName(),e.getData().toString());
		}
		
		
		nom=attributes.iterator().next().getValue();
		image=donnees.get("image");
		description=donnees.get("description");
		famille=donnees.get("famille");
		type=donnees.get("type");
		taille=Integer.parseInt(donnees.get("taille"));
		superficie=Integer.parseInt(donnees.get("superficie"));
		position=Integer.parseInt(donnees.get("position"),2);
		ensoleillement=Integer.parseInt(donnees.get("ensoleillement"));
		rotation=Integer.parseInt(donnees.get("rotation"));
		p=new Plant(nom,image,description,famille,type,taille,superficie,position,ensoleillement,rotation);
		
		return p;
	}
	
	/**
	 * Ajoute les règles à chaque plant
	 * @throws DocumentException
	 * 			Chargement XML
	 */
	@SuppressWarnings("unchecked")
	public void chargerRegles() throws DocumentException
	{
		File dataFile = new File (path+fichierReglesBase);
		SAXReader reader =  new SAXReader();
		Document doc = reader.read("file:\\\\\\"+dataFile.getAbsolutePath());
		Element root = doc.getRootElement();
		List<Element> elements = root.elements();
		Element e=null;
		for(Iterator<Element>it=elements.iterator();it.hasNext();)
		{
			e=it.next();
			if(e.getName().equals("cohabitation"))
				parseAdj(e.elements());
			else if (e.getName().equals("rotations"))
				parseSuite(e.elements());
		}
	}
	/**
	 * Génère les règles de cohabitation
	 * @param listeElements
	 * 			Liste de règles de cohabitation
	 */
	public void parseAdj(List<Element> listeElements)
	{
		Element e=null;
		Plant legume1=null;
		Plant legume2=null;
		int sens;
		for(Iterator<Element>it=listeElements.iterator();it.hasNext();)
		{
			e=it.next();
			for(int i=0;i<liste.size();i++)
			{
				if(liste.get(i).getNom().equals(e.attributeValue("legume1")))
					legume1=liste.get(i);
				if(liste.get(i).getNom().equals(e.attributeValue("legume2")))
					legume2=liste.get(i);
			}
			sens=Integer.parseInt(e.attributeValue("direction"));
			legume1.addAdjRegle(legume2,Boolean.parseBoolean(e.attributeValue("reponse")));
			if(sens==1)
				legume2.addAdjRegle(legume1,Boolean.parseBoolean(e.attributeValue("reponse")));
		}
	}

	/**
	 * Génère les règles de suite
	 * @param listeElements
	 * 			Liste de règles de suite
	 */
	public void parseSuite(List<Element> listeElements)
	{
		Element e=null;
		Plant legume1=null;
		Plant legume2=null;
		int sens;
		for(Iterator<Element>it=listeElements.iterator();it.hasNext();)
		{
			e=it.next();
			for(int i=0;i<liste.size();i++)
			{
				if(liste.get(i).getNom().equals(e.attributeValue("legume1")))
					legume1=liste.get(i);
				if(liste.get(i).getNom().equals(e.attributeValue("legume2")))
					legume2=liste.get(i);
			}
			sens=Integer.parseInt(e.attributeValue("direction"));
			legume1.addSuiteRegle(legume2,Boolean.parseBoolean(e.attributeValue("reponse")));
			if(sens==1)
				legume2.addSuiteRegle(legume1,Boolean.parseBoolean(e.attributeValue("reponse")));
		}
	}
	
	public void clear()
	{
		listeFamilles.clear();
		listeTypes.clear();
		listeFichiers.clear();
		liste.clear();		
	}
	
	/**
	 * Récupère la liste des plants
	 * @return
	 * 			liste des plants
	 */
	public Vector<Plant> getPlants()
	{
		return liste;
	}
	/**
	 * Récupère la liste des familles
	 * @return
	 * 			liste des familles
	 */
	public HashMap<String,String> getFamilles() 
	{
		return listeFamilles;
	}
	/**
	 * Récupère la liste des types
	 * @return
	 * 			liste des types
	 */
	public HashMap<String, String> getTypes() 
	{
		return listeTypes;
	}
}
