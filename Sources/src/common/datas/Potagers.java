package common.datas;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.Vector;
import objects.Potager;

public class Potagers extends AbstractDatas
{
	private static final String ERREUR_FICHIER_NON_TROUVE="Fichier non trouvé";
	private static final String ERREUR_FICHIER_MAL_ENCODE="Encodage de potager incorrect";
	private static final String ERREUR_CLASSE_NON_TROUVEE="Classe non trouvée";
	
	private Vector<Potager> liste;
	/**
	 * Creation d'un objet de données de potagers
	 */
	public Potagers()
	{
		liste=new Vector<Potager>();
	}
	
	public void loadDatas()
	{
		File dossierJardins = new File (path); 
		if (dossierJardins.list()==null)
			return;
		if ( dossierJardins.exists() && ! dossierJardins.isDirectory())
			return;
		String[] fileList = dossierJardins.list(); 	
		ObjectInputStream fichier;
		for (int i=0; i<fileList.length; i++)
		{
			try
			{
				fichier=new ObjectInputStream(new FileInputStream(dossierJardins + File.separator + fileList[i]));
				liste.add((Potager)fichier.readObject()); 
				fichier.close();
			}
			catch(FileNotFoundException e){System.out.println(ERREUR_FICHIER_NON_TROUVE);}
			catch(IOException e){System.out.println(ERREUR_FICHIER_MAL_ENCODE);}
			catch(ClassNotFoundException e){System.out.println(ERREUR_CLASSE_NON_TROUVEE);}
		}
	}
	
	public void clear()
	{
		liste.clear();
	}
	
	/**
	 * Envoie la liste des potagers de l'application
	 * @return
	 * 			Liste des potagers
	 */
	public Vector<Potager> getListeJardins()
	{
		return liste;
	}
}
