package common.datas;

import java.io.IOException;

import org.dom4j.DocumentException;

public abstract class AbstractDatas 
{
	protected String path;
	
	/**
	 * Permet de fixer le chemin d'acc�s au fichier
	 * @param p
	 * 			chemin d'acc�s
	 */
	public void setPath(String p)
	{
		path=p;
	}
	/**
	 * R�initialise les donn�es
	 * TODO : A utiliser pour le changement de langue
	 */
	public abstract void clear();
	/**
	 * Permet de charger les donn�es
	 * @throws DocumentException
	 * 			Chargement XML
	 * @throws IOException
	 * 			Chargement XML
	 */
	public abstract void loadDatas() throws DocumentException, IOException;
}
