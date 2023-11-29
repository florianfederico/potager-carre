package common.datas;

import java.io.IOException;

import org.dom4j.DocumentException;

public abstract class AbstractDatas 
{
	protected String path;
	
	/**
	 * Permet de fixer le chemin d'accès au fichier
	 * @param p
	 * 			chemin d'accès
	 */
	public void setPath(String p)
	{
		path=p;
	}
	/**
	 * Réinitialise les données
	 * TODO : A utiliser pour le changement de langue
	 */
	public abstract void clear();
	/**
	 * Permet de charger les données
	 * @throws DocumentException
	 * 			Chargement XML
	 * @throws IOException
	 * 			Chargement XML
	 */
	public abstract void loadDatas() throws DocumentException, IOException;
}
