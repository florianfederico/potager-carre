package common.datas;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import common.ProgramDatas;

public class Informations extends AbstractDatas
{
	private HashMap<String, String> datas;
	/**
	 * Création d'un objet de chargement d'informations
	 */
	public Informations()
	{
		datas=new HashMap<String,String>();
		setPath("./ressources/informations.xml");
	}
	

	@SuppressWarnings("unchecked")
	public void loadDatas() throws DocumentException, IOException
	{
		File dataFile = new File(path);
		//Variables de lecture XML
		SAXReader reader =  new SAXReader();
		Document doc;
		doc = reader.read("file:\\\\\\"+dataFile.getCanonicalPath());
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
				datas.put(e.getName()+"."+e2.getName(),(String)e2.getData());
			}
		}
	}
	/**
	 * Permet de récupérer l'information de la clé
	 * @see ProgramDatas#getInformation(String)
	 * @param key
	 * 			Clé du texte
	 * @return
	 * 			texte correspondant à la clé
	 */
	public String getInformation(String key)
	{
		return datas.get(key);
	}
	public void clear() 
	{
		datas.clear();
	}
}
