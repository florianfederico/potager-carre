package common.datas;

import java.io.File;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import common.ProgramDatas;

public class Langues extends AbstractDatas
{
	
	private HashMap<String,String> liste;
	private HashMap<Integer,String> regles;
	private HashMap<String,HashMap<String,String>> icones;
	private String fichierLangs;
	private String fichierIcones;
	private String fichierRegles;
	/**
	 * Création d'un objet de chargement de fichier de langues
	 */
	public Langues()
	{
		liste=new HashMap<String,String>();
		icones=new HashMap<String,HashMap<String,String>>();
		regles=new HashMap<Integer,String>();
	}
	
	/**
	 * Fixe tous les chemins d'accès
	 * @param lang
	 * 			Chemin d'accès du fichier de langues
	 * @param icone
	 * 			Chemin d'accès du fichier d'icones
	 * @param regles
	 * 			Chemin d'accès du fichier de message de conflits de règles
	 */
	public void setFichierBase(String lang, String icone, String regles)
	{
		fichierLangs=lang;
		fichierIcones=icone;
		fichierRegles=regles;
	}
	
	@SuppressWarnings("unchecked")
	public void loadDatas() throws DocumentException
	{
		File dataFile = new File (path+fichierLangs);
		SAXReader reader =  new SAXReader();
		Document doc = reader.read("file:\\\\\\"+dataFile.getAbsolutePath());
		Element root = doc.getRootElement();
		List<Element> elements = root.elements();
		Element e=null;
		for(Iterator<Element>it=elements.iterator();it.hasNext();)
		{
			e=it.next();
			Element e2=null;
			for(Iterator<Element>it2=e.elements().iterator();it2.hasNext();)
			{
				e2=it2.next();
				liste.put(e.getName()+"."+e2.getName(),e2.getData().toString());
			}
		}
	}
	
	/**
	 * Chargement des règles
	 * @throws DocumentException
	 * 			Chargement XML
	 * @see Langues#loadDatas()
	 */
	@SuppressWarnings("unchecked")
	public void loadRegles() throws DocumentException
	{
		File dataFile = new File (path+fichierRegles);
		SAXReader reader =  new SAXReader();
		Document doc = reader.read("file:\\\\\\"+dataFile.getAbsolutePath());
		Element root = doc.getRootElement();
		List<Element> elements = root.elements();
		Element e=null;
		for(Iterator<Element>it=elements.iterator();it.hasNext();)
		{
			e=it.next();
			regles.put(Integer.parseInt(e.attribute("value").getData().toString()), e.getData().toString());
		}
	}
	
	/**
	 * Chargement des icones
	 * @throws DocumentException
	 * 			Chargement XML
	 * @see Langues#loadDatas()
	 */
	@SuppressWarnings("unchecked")
	public void loadIcones() throws DocumentException
	{
		File dataFile = new File (path+fichierIcones);
		SAXReader reader =  new SAXReader();
		Document doc = reader.read("file:\\\\\\"+dataFile.getAbsolutePath());
		Element root = doc.getRootElement();
		List<Element> elements = root.elements();
		Element e=null;
		Attribute e2=null;
		for(Iterator<Element>it=elements.iterator();it.hasNext();)
		{
			e=it.next();
			icones.put(e.getName(),new HashMap<String,String>());
			for(Iterator<Attribute>it2=e.attributeIterator();it2.hasNext();)
			{
				e2=it2.next();
				icones.get(e.getName()).put(e2.getName(), e2.getData().toString());
			}
		}
	}
	
	/**
	 * Envoie les données des icones
	 * @return
	 * 			Toutes les données d'icones
	 */
	public HashMap<String, HashMap<String, String>> getIcones()
	{
		return icones;
	}
	
	/**
	 * Envoie la règle correspondante
	 * @param i
	 * 			ID de la règle
	 * @return
	 * 			Texte du conflit correspondant
	 */
	public String getRegle(int i)
	{
		return regles.get(i);
	}
	
	public void clear() 
	{
		liste.clear();
	}
	
	/**
	 * Permet de récupérer le texte voulu
	 * @param str
	 * 			ID du texte
	 * @return
	 * 			texte correspondant à l'ID
	 * @see ProgramDatas#getLangueElement(String)
	 */
	public String getLangueElement(String str)
	{
		return liste.get(str);
	}
}
